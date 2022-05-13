// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.text.NumberFormat;
import java.util.Locale;

public class ComStrVN
{
    public static String MakeDot(final String gve_money) {
        String result = " ";
        final NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
        if (gve_money == null || gve_money.equals("")) {
            result = "";
        }
        else {
            result = nf.format(Double.parseDouble(gve_money));
        }
        return result.trim();
    }
}
