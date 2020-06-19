package net.ajaskey.market.tools.SIP.BigDB.dataio;

import java.util.ArrayList;
import java.util.List;

import net.ajaskey.common.TextUtils;
import net.ajaskey.common.Utils;
import net.ajaskey.market.tools.SIP.SipOutput;
import net.ajaskey.market.tools.SIP.SipUtils;

/**
 * This class contains procedures for reading SIP balance sheet data and writing
 * to the DB.
 *
 * @author Andy Askey
 *         <p>
 *         Copyright (c) 2020, Andy Askey. All rights reserved.
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
public class BalSheetFileData {

  private static List<BalSheetFileData> bfdList = new ArrayList<>();

  /**
   *
   * @param ticker
   * @return
   */
  public static BalSheetFileData find(String ticker) {
    for (final BalSheetFileData bs : BalSheetFileData.bfdList) {
      if (bs.getTicker().equalsIgnoreCase(ticker)) {
        return bs;
      }
    }
    return null;
  }

  public static int getListCount() {
    return BalSheetFileData.bfdList.size();
  }

  /**
   *
   * @return
   */
  public static String listToString() {
    String ret = "";
    for (final BalSheetFileData bs : BalSheetFileData.bfdList) {
      ret += bs.toString();
    }
    return ret;
  }

  public static BalSheetFileData readFromDb(List<String> data) {

    final BalSheetFileData bfd = new BalSheetFileData();

    for (final String s : data) {

      final String[] tfld = s.split(":");

      final String fld = tfld[0].trim();

      // System.out.println(fld);

      if (tfld.length > 1) {
        tfld[1].trim();
      }

      if (fld.equals("acct payable Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.acctPayableQtr = dArr;
      }
      else if (fld.equals("acct payable Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.acctPayableYr = dArr;
      }
      else if (fld.equals("acct rx Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.acctRxQtr = dArr;
      }
      else if (fld.equals("acct rx Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.acctRxYr = dArr;
      }
      else if (fld.equals("bvps Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.bvpsQtr = dArr;
      }
      else if (fld.equals("bvps Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.bvpsYr = dArr;
      }
      else if (fld.equals("cash Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.cashQtr = dArr;
      }
      else if (fld.equals("cash Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.cashYr = dArr;
      }
      else if (fld.equals("curr assets Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.currAssetsQtr = dArr;
      }
      else if (fld.equals("curr assets Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.currAssetsYr = dArr;
      }
      else if (fld.equals("curr liab Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.currLiabQtr = dArr;
      }
      else if (fld.equals("curr liab Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.currLiabYr = dArr;
      }
      else if (fld.equals("equity Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.equityQtr = dArr;
      }
      else if (fld.equals("equity Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.equityYr = dArr;
      }
      else if (fld.equals("goodwill Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.goodwillQtr = dArr;
      }
      else if (fld.equals("goodwill Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.goodwillYr = dArr;
      }
      else if (fld.equals("inventory Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.inventoryQtr = dArr;
      }
      else if (fld.equals("inventory Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.inventoryYr = dArr;
      }
      else if (fld.equals("liab equity Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.liabEquityQtr = dArr;
      }
      else if (fld.equals("liab equity Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.liabEquityYr = dArr;
      }
      else if (fld.equals("lt debt Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.ltDebtQtr = dArr;
      }
      else if (fld.equals("lt debt Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.ltDebtYr = dArr;
      }
      else if (fld.equals("lt invest Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.ltInvestQtr = dArr;
      }
      else if (fld.equals("lt invest Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.ltInvestYr = dArr;
      }
      else if (fld.equals("net fixed assets Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.netFixedAssetsQtr = dArr;
      }
      else if (fld.equals("net fixed assets Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.netFixedAssetsYr = dArr;
      }
      else if (fld.equals("curr assets Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.currAssetsQtr = dArr;
      }
      else if (fld.equals("curr assets Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.currAssetsYr = dArr;
      }
      else if (fld.equals("curr liab Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.currLiabQtr = dArr;
      }
      else if (fld.equals("curr liab Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.currLiabYr = dArr;
      }
      else if (fld.equals("other curr assets Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.otherCurrAssetsQtr = dArr;
      }
      else if (fld.equals("other curr assets Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.otherCurrAssetsYr = dArr;
      }
      else if (fld.equals("other lt assets Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.otherLtAssetsQtr = dArr;
      }
      else if (fld.equals("other lt assets Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.otherLtAssetsYr = dArr;
      }
      else if (fld.equals("other curr liab Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.otherCurrLiabQtr = dArr;
      }
      else if (fld.equals("other curr liab Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.otherCurrLiabYr = dArr;
      }
      else if (fld.equals("other lt liab Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.otherLtLiabQtr = dArr;
      }
      else if (fld.equals("other lt liab Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.otherLtLiabYr = dArr;
      }
      else if (fld.equals("pref stock Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.prefStockQtr = dArr;
      }
      else if (fld.equals("pref stock Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.prefStockYr = dArr;
      }
      else if (fld.equals("st debt Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.stDebtQtr = dArr;
      }
      else if (fld.equals("st debt Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.stDebtYr = dArr;
      }
      else if (fld.equals("st invest Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.stInvestQtr = dArr;
      }
      else if (fld.equals("st invest Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.stInvestYr = dArr;
      }
      else if (fld.equals("total assets Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.totalAssetsQtr = dArr;
      }
      else if (fld.equals("total assets Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.totalAssetsYr = dArr;
      }
      else if (fld.equals("total liab Qtr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.totalLiabQtr = dArr;
      }
      else if (fld.equals("total liab Yr")) {
        final double[] dArr = SipUtils.parseArrayDoubles(tfld[1], 1);
        bfd.totalLiabYr = dArr;
      }

    }
    return bfd;
  }

  /**
   *
   * @param filenameQtr
   * @param filenameYr
   * @return
   */
  public static void readSipData(String filenameQtr, String filenameYr) {

    final List<String> bsdDataQtr = TextUtils.readTextFile(filenameQtr, true);
    final List<String> bsdDataYr = TextUtils.readTextFile(filenameYr, true);

    for (int i = 0; i < bsdDataQtr.size(); i++) {

      final String[] fldQtr = bsdDataQtr.get(i).replace("\"", "").split(Utils.TAB);
      final String[] fldYr = bsdDataYr.get(i).replace("\"", "").split(Utils.TAB);

      if (!fldQtr[1].equals(fldYr[1])) {

        System.out.printf("BSD Not equal : %s : %s%n", fldQtr[1], fldYr[1]);

      }
      else {

        final BalSheetFileData bsd = new BalSheetFileData(fldQtr, fldYr);
        BalSheetFileData.bfdList.add(bsd);

      }
    }
  }

  private double[] acctPayableQtr;
  private double[] acctPayableYr;
  private double[] acctRxQtr;
  private double[] acctRxYr;
  private double[] bvpsQtr;
  private double[] bvpsYr;
  private double[] cashQtr;
  private double[] cashYr;
  private double[] currAssetsQtr;
  private double[] currAssetsYr;
  private double[] currLiabQtr;
  private double[] currLiabYr;
  private double[] equityQtr;
  private double[] equityYr;
  private ExchEnum exchange;
  private double[] goodwillQtr;
  private double[] goodwillYr;
  private String   industry;
  private double[] inventoryQtr;
  private double[] inventoryYr;
  private double[] liabEquityQtr;
  private double[] liabEquityYr;
  private double[] ltDebtQtr;
  private double[] ltDebtYr;
  private double[] ltInvestQtr;
  private double[] ltInvestYr;
  private String   name;
  private double[] netFixedAssetsQtr;
  private double[] netFixedAssetsYr;
  private double[] otherCurrAssetsQtr;
  private double[] otherCurrAssetsYr;
  private double[] otherCurrLiabQtr;
  private double[] otherCurrLiabYr;
  private double[] otherLtAssetsQtr;
  private double[] otherLtAssetsYr;
  private double[] otherLtLiabQtr;
  private double[] otherLtLiabYr;
  private double[] prefStockQtr;
  private double[] prefStockYr;
  private String   sector;
  private double[] stDebtQtr;
  private double[] stDebtYr;
  private double[] stInvestQtr;

  private double[] stInvestYr;

  private String ticker;

  private double[] totalAssetsQtr;

  private double[] totalAssetsYr;

  private double[] totalLiabQtr;

  private double[] totalLiabYr;

  public BalSheetFileData() {
    // TODO Auto-generated constructor stub
  }

  /**
   *
   * @param fldQtr
   * @param fldYr
   */
  public BalSheetFileData(String[] fldQtr, String[] fldYr) {

    this.name = fldQtr[0].trim();
    this.ticker = fldQtr[1].trim();
    this.exchange = FieldData.convertExchange(fldQtr[2].trim());
    this.sector = fldQtr[3].trim();
    this.industry = fldQtr[4].trim();

    int ptrQtr = 5;
    int ptrYr = 5;
    this.cashQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.cashYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);
    // debug("MSFT", ticker, "Cash", cashYr, cashQtr);

    ptrQtr += 8;
    ptrYr += 7;
    this.stInvestQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.stInvestYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);
    // debug("MSFT", ticker, "stInvest", stInvestYr, stInvestQtr);

    ptrQtr += 8;
    ptrYr += 7;
    this.acctRxQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.acctRxYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.inventoryQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.inventoryYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.otherCurrAssetsQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.otherCurrAssetsYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.currAssetsQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.currAssetsYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.netFixedAssetsQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.netFixedAssetsYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.ltInvestQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.ltInvestYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.goodwillQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.goodwillYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.otherLtAssetsQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.otherLtAssetsYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.totalAssetsQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.totalAssetsYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.acctPayableQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.acctPayableYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.stDebtQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.stDebtYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.otherCurrLiabQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.otherCurrLiabYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.currLiabQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.currLiabYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.ltDebtQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.ltDebtYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.otherLtLiabQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.otherLtLiabYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.totalLiabQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.totalLiabYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.prefStockQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.prefStockYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.equityQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.equityYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.liabEquityQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.liabEquityYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);

    ptrQtr += 8;
    ptrYr += 7;
    this.bvpsQtr = SipUtils.parseDoubles(fldQtr, ptrQtr, 8);
    this.bvpsYr = SipUtils.parseDoubles(fldYr, ptrYr, 7);
  }

  public double[] getAcctPayableQtr() {
    return this.acctPayableQtr;
  }

  public double[] getAcctPayableYr() {
    return this.acctPayableYr;
  }

  public double[] getAcctRxQtr() {
    return this.acctRxQtr;
  }

  public double[] getAcctRxYr() {
    return this.acctRxYr;
  }

  public double[] getBvpsQtr() {
    return this.bvpsQtr;
  }

  public double[] getBvpsYr() {
    return this.bvpsYr;
  }

  public double[] getCashQtr() {
    return this.cashQtr;
  }

  public double[] getCashYr() {
    return this.cashYr;
  }

  public double[] getCurrAssetsQtr() {
    return this.currAssetsQtr;
  }

  public double[] getCurrAssetsYr() {
    return this.currAssetsYr;
  }

  public double[] getCurrLiabQtr() {
    return this.currLiabQtr;
  }

  public double[] getCurrLiabYr() {
    return this.currLiabYr;
  }

  public double[] getEquityQtr() {
    return this.equityQtr;
  }

  public double[] getEquityYr() {
    return this.equityYr;
  }

  public String getExchange() {
    return this.exchange.toString().toUpperCase();
  }

  public double[] getGoodwillQtr() {
    return this.goodwillQtr;
  }

  public double[] getGoodwillYr() {
    return this.goodwillYr;
  }

  public String getIndustry() {
    return this.industry;
  }

  public double[] getInventoryQtr() {
    return this.inventoryQtr;
  }

  public double[] getInventoryYr() {
    return this.inventoryYr;
  }

  public double[] getLiabEquityQtr() {
    return this.liabEquityQtr;
  }

  public double[] getLiabEquityYr() {
    return this.liabEquityYr;
  }

  public double[] getLtDebtQtr() {
    return this.ltDebtQtr;
  }

  public double[] getLtDebtYr() {
    return this.ltDebtYr;
  }

  public double[] getLtInvestQtr() {
    return this.ltInvestQtr;
  }

  public double[] getLtInvestYr() {
    return this.ltInvestYr;
  }

  public String getName() {
    return this.name;
  }

  public double[] getNetFixedAssetsQtr() {
    return this.netFixedAssetsQtr;
  }

  public double[] getNetFixedAssetsYr() {
    return this.netFixedAssetsYr;
  }

  public double[] getOtherCurrAssetsQtr() {
    return this.otherCurrAssetsQtr;
  }

  public double[] getOtherCurrAssetsYr() {
    return this.otherCurrAssetsYr;
  }

  public double[] getOtherCurrLiabQtr() {
    return this.otherCurrLiabQtr;
  }

  public double[] getOtherCurrLiabYr() {
    return this.otherCurrLiabYr;
  }

  public double[] getOtherLtAssetsQtr() {
    return this.otherLtAssetsQtr;
  }

  public double[] getOtherLtAssetsYr() {
    return this.otherLtAssetsYr;
  }

  public double[] getOtherLtLiabQtr() {
    return this.otherLtLiabQtr;
  }

  public double[] getOtherLtLiabYr() {
    return this.otherLtLiabYr;
  }

  public double[] getPrefStockQtr() {
    return this.prefStockQtr;
  }

  public double[] getPrefStockYr() {
    return this.prefStockYr;
  }

  public String getSector() {
    return this.sector;
  }

  public double[] getStDebtQtr() {
    return this.stDebtQtr;
  }

  public double[] getStDebtYr() {
    return this.stDebtYr;
  }

  public double[] getStInvestQtr() {
    return this.stInvestQtr;
  }

  public double[] getStInvestYr() {
    return this.stInvestYr;
  }

  public String getTicker() {
    return this.ticker;
  }

  public double[] getTotalAssetsQtr() {
    return this.totalAssetsQtr;
  }

  public double[] getTotalAssetsYr() {
    return this.totalAssetsYr;
  }

  public double[] getTotalLiabQtr() {
    return this.totalLiabQtr;
  }

  public double[] getTotalLiabYr() {
    return this.totalLiabYr;
  }

  public void setNameFields(CompanyFileData cfd) {
    this.ticker = cfd.getTicker();
    this.name = cfd.getName();
    this.sector = cfd.getSector();
    this.industry = cfd.getIndustry();
    this.exchange = cfd.getExchange();

  }

  public String toDbOutput() {
    String ret = "";
    ret += String.format("  acct payable Qtr      : %s%n", SipOutput.buildArray("", this.acctPayableQtr, 12, 3, 1));
    ret += String.format("  acct payable Yr       : %s%n", SipOutput.buildArray("", this.acctPayableYr, 12, 3, 1));
    ret += String.format("  acct rx Qtr           : %s%n", SipOutput.buildArray("", this.acctRxQtr, 12, 3, 1));
    ret += String.format("  acct rx Yr            : %s%n", SipOutput.buildArray("", this.acctRxYr, 12, 3, 1));
    ret += String.format("  bvps Qtr              : %s%n", SipOutput.buildArray("", this.bvpsQtr, 12, 4, 1));
    ret += String.format("  bvps Yr               : %s%n", SipOutput.buildArray("", this.bvpsYr, 12, 4, 1));
    ret += String.format("  cash Qtr              : %s%n", SipOutput.buildArray("", this.cashQtr, 12, 3, 1));
    ret += String.format("  cash Yr               : %s%n", SipOutput.buildArray("", this.cashYr, 12, 3, 1));
    ret += String.format("  curr assets Qtr       : %s%n", SipOutput.buildArray("", this.currAssetsQtr, 12, 3, 1));
    ret += String.format("  curr assets Yr        : %s%n", SipOutput.buildArray("", this.currAssetsYr, 12, 3, 1));
    ret += String.format("  curr liab Qtr         : %s%n", SipOutput.buildArray("", this.currLiabQtr, 12, 3, 1));
    ret += String.format("  curr liab Yr          : %s%n", SipOutput.buildArray("", this.currLiabYr, 12, 3, 1));
    ret += String.format("  equity Qtr            : %s%n", SipOutput.buildArray("", this.equityQtr, 12, 3, 1));
    ret += String.format("  equity Yr             : %s%n", SipOutput.buildArray("", this.equityYr, 12, 3, 1));
    ret += String.format("  goodwill Qtr          : %s%n", SipOutput.buildArray("", this.goodwillQtr, 12, 3, 1));
    ret += String.format("  goodwill Yr           : %s%n", SipOutput.buildArray("", this.goodwillYr, 12, 3, 1));
    ret += String.format("  inventory Qtr         : %s%n", SipOutput.buildArray("", this.inventoryQtr, 12, 3, 1));
    ret += String.format("  inventory Yr          : %s%n", SipOutput.buildArray("", this.inventoryYr, 12, 3, 1));
    ret += String.format("  liab equity Qtr       : %s%n", SipOutput.buildArray("", this.liabEquityQtr, 12, 3, 1));
    ret += String.format("  liab equity Yr        : %s%n", SipOutput.buildArray("", this.liabEquityYr, 12, 3, 1));
    ret += String.format("  lt debt Qtr           : %s%n", SipOutput.buildArray("", this.ltDebtQtr, 12, 3, 1));
    ret += String.format("  lt debt Yr            : %s%n", SipOutput.buildArray("", this.ltDebtYr, 12, 3, 1));
    ret += String.format("  lt invest Qtr         : %s%n", SipOutput.buildArray("", this.ltInvestQtr, 12, 3, 1));
    ret += String.format("  lt invest Yr          : %s%n", SipOutput.buildArray("", this.ltInvestYr, 12, 3, 1));
    ret += String.format("  net fixed assets Qtr  : %s%n", SipOutput.buildArray("", this.netFixedAssetsQtr, 12, 3, 1));
    ret += String.format("  net fixed assets Yr   : %s%n", SipOutput.buildArray("", this.netFixedAssetsYr, 12, 3, 1));
    ret += String.format("  curr assets Qtr       : %s%n", SipOutput.buildArray("", this.otherCurrAssetsQtr, 12, 3, 1));
    ret += String.format("  curr assest Yr        : %s%n", SipOutput.buildArray("", this.otherCurrAssetsYr, 12, 3, 1));
    ret += String.format("  curr liab Qtr         : %s%n", SipOutput.buildArray("", this.otherCurrLiabQtr, 12, 3, 1));
    ret += String.format("  curr liab Yr          : %s%n", SipOutput.buildArray("", this.otherCurrLiabYr, 12, 3, 1));
    ret += String.format("  other curr assets Qtr : %s%n", SipOutput.buildArray("", this.otherCurrAssetsQtr, 12, 3, 1));
    ret += String.format("  other curr assets Yr  : %s%n", SipOutput.buildArray("", this.otherCurrAssetsYr, 12, 3, 1));
    ret += String.format("  other lt assets Qtr   : %s%n", SipOutput.buildArray("", this.otherLtAssetsQtr, 12, 3, 1));
    ret += String.format("  other lt assets Yr    : %s%n", SipOutput.buildArray("", this.otherLtAssetsYr, 12, 3, 1));
    ret += String.format("  other curr liab Qtr   : %s%n", SipOutput.buildArray("", this.otherCurrLiabQtr, 12, 3, 1));
    ret += String.format("  other curr liab Yr    : %s%n", SipOutput.buildArray("", this.otherCurrLiabYr, 12, 3, 1));
    ret += String.format("  other lt liab Qtr     : %s%n", SipOutput.buildArray("", this.otherLtLiabQtr, 12, 3, 1));
    ret += String.format("  other lt liab Yr      : %s%n", SipOutput.buildArray("", this.otherLtLiabYr, 12, 3, 1));
    ret += String.format("  pref stock Qtr        : %s%n", SipOutput.buildArray("", this.prefStockQtr, 12, 3, 1));
    ret += String.format("  pref stock Yr         : %s%n", SipOutput.buildArray("", this.prefStockYr, 12, 3, 1));
    ret += String.format("  st debt Qtr           : %s%n", SipOutput.buildArray("", this.stDebtQtr, 12, 3, 1));
    ret += String.format("  st debt Yr            : %s%n", SipOutput.buildArray("", this.stDebtYr, 12, 3, 1));
    ret += String.format("  st invest Qtr         : %s%n", SipOutput.buildArray("", this.stInvestQtr, 12, 3, 1));
    ret += String.format("  st invest Yr          : %s%n", SipOutput.buildArray("", this.stInvestYr, 12, 3, 1));
    ret += String.format("  total assets Qtr      : %s%n", SipOutput.buildArray("", this.totalAssetsQtr, 12, 3, 1));
    ret += String.format("  total assets Yr       : %s%n", SipOutput.buildArray("", this.totalAssetsYr, 12, 3, 1));
    ret += String.format("  total liab Qtr        : %s%n", SipOutput.buildArray("", this.totalLiabQtr, 12, 3, 1));
    ret += String.format("  total liab Yr         : %s%n", SipOutput.buildArray("", this.totalLiabYr, 12, 3, 1));

    return ret;
  }

  @Override
  public String toString() {
    String ret = SipOutput.SipHeader(this.ticker, this.name, this.getExchange(), this.sector, this.industry);
    ret += SipOutput.buildArray("  CashQtr        : ", this.cashQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  CashYr         : ", this.cashYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  stInvestQtr    : ", this.stInvestQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  stInvestYr     : ", this.stInvestYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  acctRxQtr          : ", this.acctRxQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  acctRxYr           : ", this.acctRxYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  inventoryQtr   : ", this.inventoryQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  inventoryYr    : ", this.inventoryYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  oCurrAssetsQtr : ", this.otherCurrAssetsQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  oCurrAssetsYr  : ", this.otherCurrAssetsYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  CurrAssetsQtr  : ", this.currAssetsQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  CurrAssetsYr   : ", this.currAssetsYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  NetFixedAssQtr : ", this.netFixedAssetsQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  NetFixedAssYr  : ", this.netFixedAssetsYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  ltInvestQtr    : ", this.ltInvestQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  ltInvestYr     : ", this.ltInvestYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  goodwillQtr    : ", this.goodwillQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  goodwillYr     : ", this.goodwillYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  oLtAssetsQtr   : ", this.otherLtAssetsQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  oLtAssetsYr    : ", this.otherLtAssetsYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  totalAssetsQtr : ", this.totalAssetsQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  totalAssetsYr  : ", this.totalAssetsYr, 1, 0) + Utils.NL;

    ret += SipOutput.buildArray("  acctPayableQtr : ", this.acctPayableQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  acctPayableYr  : ", this.acctPayableYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  stDebtQtr      : ", this.stDebtQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  stDebtYr       : ", this.stDebtYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  oCurrLiabQtr   : ", this.otherCurrLiabQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  oCurrLiabYr    : ", this.otherCurrLiabYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  currLiabQtr    : ", this.currLiabQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  currLiabYr     : ", this.currLiabYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  ltDebtQtr      : ", this.ltDebtQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  ltDebtYr       : ", this.ltDebtYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  oLtLiabQtr     : ", this.otherLtLiabQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  oLtLiabYr      : ", this.otherLtLiabYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  totalLiabQtr   : ", this.totalLiabQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  totalLiabYr    : ", this.totalLiabYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  prefStockQtr   : ", this.prefStockQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  prefStockYr    : ", this.prefStockYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  equityQtr      : ", this.equityQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  equityYr       : ", this.equityYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  liabEquityQtr  : ", this.liabEquityQtr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  liabEquityYr   : ", this.liabEquityYr, 1, 0) + Utils.NL;
    ret += SipOutput.buildArray("  bvpsQtr        : ", this.bvpsQtr, 1, 4) + Utils.NL;
    ret += SipOutput.buildArray("  bvpsYr         : ", this.bvpsYr, 1, 2) + Utils.NL;

    return ret;
  }
}
