// 
// Decompiled by Procyon v0.5.30
// 

package common.util;

import java.text.NumberFormat;
import java.util.Locale;

public class CommMath
{
    public static String MakeComma(final String gve_money) {
        String result = " ";
        final NumberFormat nf = NumberFormat.getInstance(Locale.KOREAN);
        if (gve_money == null || gve_money.equals("")) {
            result = "";
        }
        else {
            result = nf.format(Double.parseDouble(gve_money));
        }
        return result.trim();
    }
    
    public static String MakeComma(final double gve_money) {
        String result = " ";
        final NumberFormat nf = NumberFormat.getInstance(Locale.KOREAN);
        result = nf.format(gve_money);
        return result.trim();
    }
    
    public static String eraseComma(final String gve_money) {
        String result = "";
        final String temp = null;
        if (gve_money == null || gve_money.equals("")) {
            result = "";
        }
        else {
            for (int i = 0; i < gve_money.length(); ++i) {
                final char c = gve_money.charAt(i);
                if (c != ',') {
                    result = String.valueOf(result) + c;
                }
            }
        }
        return result.trim();
    }
}
