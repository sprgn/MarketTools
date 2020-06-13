package net.ajaskey.market.tools.options;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import net.ajaskey.common.DateTime;
import net.ajaskey.common.TextUtils;

public class PriceRiskRange {

  public static void main(String[] args) throws FileNotFoundException {

    final double minBuyPrice = 0.10;
    final int minOI = 1;
    final double sellExtension = 0.075;

    final DateTime buyDate = new DateTime();
    buyDate.add(DateTime.DATE, 1);

    final DateTime sellDate = new DateTime(buyDate);
    sellDate.add(DateTime.DATE, 15);

    final DateTime firstExpiry = new DateTime(sellDate);
    firstExpiry.add(DateTime.DATE, 1);

    final List<String> data = TextUtils.readTextFile("data/options/DMPData.csv", true);
    for (String s : data) {

      for (int activeType = 1; activeType < 3; activeType++) {

        String activeTrade = "";
        if (activeType == OptionsProcessor.ACALL) {
          activeTrade = "CALL";
        } else {
          activeTrade = "PUT";
        }

        RiskRange rr = new RiskRange(s);
        if (rr.isValid()) {
          String activeCode = rr.code;

          double ulBuy = 0.0;
          double ulSell = 0.0;
          if (activeType == OptionsProcessor.ACALL) {
            ulBuy = rr.dmp2l;
            ulSell = rr.dmp2u + (rr.dmp2u * sellExtension);
          } else {
            ulBuy = rr.dmp2u;
            ulSell = rr.dmp2l - (rr.dmp2l * sellExtension);
          }

          String dbgFname = String.format("out/options/%s.dbg", activeCode);
          try (PrintWriter pwDbg = new PrintWriter(dbgFname)) {

            pwDbg.printf("%s%n", rr);
            pwDbg.printf("%nType        : %s%n", activeTrade);

            final String fname = String.format("data/options/%s-options.dat", activeCode);
            final List<CboeOptionData> dil = CallPutList.readCboeData(fname, firstExpiry, buyDate, minOI);

            for (CboeOptionData cd : dil) {
              System.out.printf("%s\t%.2f\t%d\t%d%n", activeCode, cd.strike, cd.call.oi, cd.put.oi);
            }

            String outfile = String.format("out/options/%s-%s-%d.csv", activeCode, activeTrade, (int) ulBuy);
            try (PrintWriter pw = new PrintWriter(outfile)) {

              pw.printf("Id,Expiry,Strike,Opt Buy,Opt Sell,Profit,IV,%s,%s,%.2f,%.2f%n", buyDate, sellDate, ulBuy,
                  ulSell);
              for (final CboeOptionData cod : dil) {
                CboeCallPutData option = null;
                if (activeType == OptionsProcessor.ACALL) {
                  option = cod.call;
                } else {
                  option = cod.put;
                }
                String id = option.id;
                OptionsProcessor op = new OptionsProcessor(option.optionData);
                op.setUlPrice(ulBuy);
                op.setSellDate(buyDate);
                if (activeCode.equalsIgnoreCase("VIX")) {
                  double newIv = option.iv * 1.75;
                  op.setIv(newIv);
                }
                double buyPrice = op.getPrice();
                pwDbg.printf("%nInitial buy : %s\t%.2f\t%.2f\tStrike : %.2f\tIV : %.4f\tDIL IV : %.4f%n", buyDate,
                    buyPrice, ulBuy, op.getStrike(), op.getIv(), op.getIv());

                if ((buyPrice >= minBuyPrice) && (option.oi >= minOI)) {
                  op.setSellDate(sellDate);
                  op.setUlPrice(ulSell);
                  double sellPrice = op.getPrice();

                  double chg = (sellPrice - buyPrice) / buyPrice * 100.0;
                  pwDbg.printf("Sell        : %s\t%.2f\t%.2f%nProfit      : %.2f%%%n%s%n  OI        : %d%n", sellDate,
                      sellPrice, ulSell, chg, op, option.oi);

                  pw.printf("%s,%s,%.2f,%.2f,%.2f,%.2f%%,%.4f%n", id, op.getExpiry(), op.getStrike(), buyPrice,
                      sellPrice, chg, option.iv);
                }
              }
            }
          }
        }
      }
    }
  }

}
