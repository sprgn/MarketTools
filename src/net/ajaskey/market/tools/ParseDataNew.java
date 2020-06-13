package net.ajaskey.market.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.ajaskey.common.TextUtils;
import net.ajaskey.market.optuma.TickerPriceData;

public class ParseDataNew {

  public static void main(final String[] args) {

    try {
      final List<String> l = getTickerList("data/PreFiltered.csv");
      for (final String s : l) {
        System.out.println(s);
        TickerPriceData pd = getPriceData(s);
      }
      System.out.println(l.size());
    } catch (final IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static TickerPriceData getPriceData(String ticker) {

    String fld[] = ticker.split(":");
    TickerPriceData tpd = new TickerPriceData(fld[1], fld[0]);

    return tpd;
  }

  /**
   * 
   * @param filename
   * @return
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static List<String> getTickerList(final String filename) throws FileNotFoundException, IOException {

    final List<String> list = new ArrayList<>();

    final File f = new File(filename);
    List<String> data = TextUtils.readTextFile(f, true);

    for (String line : data) {
      if (line != null && line.trim().length() > 0) {
        final String sline = line.trim().toLowerCase();
        if (!sline.contains("code") && !sline.contains("last")) {
          final String fld[] = line.trim().split(",");
          if (fld[0].trim().length() > 0) {
            String str = String.format("%s:%s", fld[0].trim(), fld[1].trim());
            list.add(str);
          }
        }
      }
    }
    return list;
  }

}
