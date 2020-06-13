
package net.ajaskey.market.tools.SIP;

/**
 * This class...
 *
 * @author Andy
 *         <p>
 *         PTV-Parser Copyright (c) 2015, Andy Askey. All rights reserved.
 *         </p>
 *         <p>
 *         Permission is hereby granted, free of charge, to any person obtaining
 *         a copy of this software and associated documentation files (the
 *         "Software"), to deal in the Software without restriction, including
 *         without limitation the rights to use, copy, modify, merge, publish,
 *         distribute, sublicense, and/or sell copies of the Software, and to
 *         permit persons to whom the Software is furnished to do so, subject to
 *         the following conditions:
 *
 *         The above copyright notice and this permission notice shall be
 *         included in all copies or substantial portions of the Software.
 *         </p>
 *
 *         <p>
 *         THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *         EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *         MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *         NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 *         BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 *         ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *         CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *         SOFTWARE.
 *         </p>
 *
 */
public class ZombieScore {

  /**
   *
   * net.ajaskey.market.tools.SIP.calculate
   *
   * @param cd
   * @return
   */
  public static ZombieScore calculate(final CompanyData cd) {

    final ZombieScore zc = new ZombieScore();

    double d = 0.0;
    double scr = 0;

    if ((cd.marketCap > 0.0) && (cd.id.sales.getMostRecent() > 0.0)) {

      if ((cd.opMargin < 0) && (cd.netMargin < 0.0)) {
        d = Math.abs(cd.opMargin + cd.netMargin) / 10.0;
        final double margin = Math.min(d, 35.0);
        scr += margin;
        zc.margin = margin;
      }

      zc.interest = Math.min(cd.interestRate, 35.0);
      scr += zc.interest;

      double ltdtoe = 0.0;
      double eqPercent = 0.0;

      d = (cd.bsd.equity.getMostRecent() / cd.marketCap) * 100.0;
      if (d < 0.0) {
        eqPercent = Math.abs(Math.max(d, -30.0));
      } else {
        if (cd.bsd.equity.dd.qoqGrowth > 0.0) {
          eqPercent = -1.0 * Math.min(d, 10.0);
        }
      }
      scr += eqPercent;
      zc.equitytoMC = eqPercent;

      if (cd.bsd.equity.getMostRecent() > 0.0) {
        if (cd.ltDebtEquity > 2.0) {
          ltdtoe = Math.min((cd.ltDebtEquity * 2.5), 35.0);
        }
      } else if (cd.bsd.ltDebt.getMostRecent() > 0.0) {
        ltdtoe = 5;
      }
      zc.ltDebt = ltdtoe;
      scr += ltdtoe;

      d = cd.cashData.cashFromOps.getTtm() + cd.workingCapital;
      if (d < 0.0) {
        zc.availCash = Math.min(Math.abs((d / cd.marketCap) * 100.0), 35.0);
        scr += zc.availCash;
      }

      d = cd.freeCashFlow + cd.workingCapital;
      if (d < 0.0) {
        zc.wcfcf = Math.min(Math.abs((d / cd.marketCap) * 100.0), 35.0);
        scr += zc.wcfcf;
      }

      d = cd.id.sales.dd.qoqGrowth;
      if (cd.id.sales.dd.qoqGrowth < 0.0) {
        zc.sales = Math.min(Math.abs(d), 35.0);
      } else {
        zc.sales = Math.max(-d, -15.0);
      }
      scr += zc.sales;

      if (cd.shares.dd.qoqGrowth < 0.0) {
        zc.shares = Math.min(Math.abs(cd.shares.dd.qoqGrowth * 2.5), 15.0);
        scr += zc.shares;
      }

      zc.growth3y = cd.opInc3yrGrowth;
      if (cd.opInc3yrGrowth < 0.0) {
        zc.growth = Math.min(Math.abs(cd.opInc3yrGrowth), 35.0);
        scr += zc.growth;
      }

      if (cd.rs < 0) {
        zc.priceVsSpx = Math.min(Math.abs(cd.rs), 25.0);
        scr += Math.abs(zc.priceVsSpx);
      }

      if (cd.q0EstGrowth < 0.0) {
        zc.earnings += 7.5;
      }
      if (cd.y1EstGrowth < 0.0) {
        zc.earnings += 7.5;
      }
      scr += zc.earnings;

//      if (cd.ticker.contains("SNBR")) {
//        System.out.println();
//      }

      final double MAXCHG = -15.0;
      final double MINCHG = 15.0;

      d = cd.bsd.currentAssets.get1QBack();
      double c = cd.bsd.currentAssets.getMostRecent();
      double chgA = 0.0;
      double chgL = 0.0;
      if (Math.abs(d) > 0.0) {
        chgA = (c - d) / Math.abs(d);
      }
      d = cd.bsd.currLiab.get1QBack();
      c = cd.bsd.currLiab.getMostRecent();
      if (Math.abs(d) > 0.0) {
        chgL = (c - d) / Math.abs(d);
      }
      d = (chgL - chgA) * 100.0;
      if (d < 0.0) {
        zc.assetLiabChg = Math.max(d, MAXCHG);
      } else {
        zc.assetLiabChg = Math.min(d, MINCHG);
      }
      scr += zc.assetLiabChg;

      double chgD = 0.0;
      d = cd.bsd.ltDebt.get1QBack();
      c = cd.bsd.ltDebt.getMostRecent();
      if (Math.abs(d) > 0.0) {
        chgD = (c - d) / Math.abs(d);
      }
      d = chgD * 100.0;
      if (d < 0.0) {
        zc.debtChg = Math.max(d, MAXCHG);
      } else {
        zc.debtChg = Math.min(d, MINCHG);
      }
      scr += zc.debtChg;

      double chgE = 0.0;
      d = cd.bsd.equity.get1QBack();
      c = cd.bsd.equity.getMostRecent();
      if (Math.abs(d) > 0.0) {
        chgE = (c - d) / Math.abs(d);
      }
      d = chgE * -100.0;
      if (d < 0.0) {
        if (cd.bsd.equity.getMostRecent() > 0.0) {
          zc.eqtyChg = Math.max(d, MAXCHG);
        }
      } else {
        zc.eqtyChg = Math.min(d, MINCHG);
      }
      scr += zc.eqtyChg;

      c = cd.id.grossOpIncome.getTtm();
      d = cd.id.grossOpIncome.getPrevTtm();
      if (c < d) {
        double chgInc = 0.0;
        if (Math.abs(d) > 0.0) {
          chgInc = (c - d) / Math.abs(d) * -100.0;
          zc.incChg = Math.min(chgInc, MINCHG);
          // System.out.printf("%s\t%f\t%f\t%f\t%f%n", cd.ticker, c, d, chgInc,
          // zc.incChg);
        }
      }
      scr += zc.incChg;

      zc.score = scr;

    }
    return zc;
  }

  public double cashOpstoMC;
  public double margin;
  public double interest;
  public double equitytoMC;
  public double ltDebt;
  public double sales;
  public double availCash;
  public double wcfcf;
  public double shares;
  public double growth;
  // public double price;
  public double priceVsSpx;
  public double earnings;
  public double score;
  private double growth3y;

  // new
  public double assetLiabChg;
  public double debtChg;
  public double eqtyChg;
  public double incChg;

  /**
   * This method serves as a constructor for the class.
   *
   */
  public ZombieScore() {

    this.cashOpstoMC = 0.0;
    this.margin = 0.0;
    this.interest = 0.0;
    this.equitytoMC = 0.0;
    this.ltDebt = 0.0;
    this.availCash = 0.0;
    this.wcfcf = 0.0;
    this.sales = 0.0;
    this.shares = 0.0;
    this.growth = 0.0;
    // this.price = 0.0;
    this.priceVsSpx = 0.0;
    this.earnings = 0.0;
    this.score = 0.0;
    this.growth3y = 0.0;
    assetLiabChg = 0.0;
    debtChg = 0.0;
    eqtyChg = 0.0;
    incChg = 0.0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {

    String ret = String.format("%n\tZombie Score : %.2f", this.score);
    ret += String.format("%n\t\tNegative Sales        : %7.2f", this.sales);
    ret += String.format("%n\t\tMargins               : %7.2f", this.margin);
    ret += String.format("%n\t\tAvailable Cash to MC  : %7.2f", this.availCash);
    ret += String.format("%n\t\tWC plus FCF to MC     : %7.2f", this.wcfcf);
    ret += String.format("%n\t\tInterest Paid         : %7.2f", this.interest);
    ret += String.format("%n\t\tLT Debt to Equity     : %7.2f", this.ltDebt);
    ret += String.format("%n\t\tEquity to MC          : %7.2f", this.equitytoMC);
    ret += String.format("%n\t\tOpsInc Growth 3Yr     : %7.2f : (%.2f%%)", this.growth, this.growth3y);
    ret += String.format("%n\t\tShare Decline         : %7.2f", this.shares);
    // ret += String.format("%n\t\tPrice : %7.2f", this.price);
    ret += String.format("%n\t\tPrice vs SPX          : %7.2f", this.priceVsSpx);
    ret += String.format("%n\t\tAsset-Liab Change     : %7.2f", this.assetLiabChg);
    ret += String.format("%n\t\tLT Debt Change        : %7.2f", this.debtChg);
    ret += String.format("%n\t\tShrHldr Equity Change : %7.2f", this.eqtyChg);
    ret += String.format("%n\t\tGross Income Change   : %7.2f", this.incChg);
    if (this.earnings > 0.0) {
      ret += String.format("%n\t\tEarnings weakness     : %7.2f", this.earnings);
    }

    return ret;
  }

}
