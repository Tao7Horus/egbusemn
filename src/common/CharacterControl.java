// 
// Decompiled by Procyon v0.5.30
// 

package common;

import GT.EP_COB_GTQ805;

public class CharacterControl
{
    public static String encode(final String s) {
        try {
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < s.length(); ++i) {
                final String hexString = Integer.toHexString(s.charAt(i));
                sb.append("\\u" + ((hexString.length() == 4) ? hexString : ("00" + hexString)));
            }
            return sb.toString();
        }
        catch (Exception ex) {
            Log.debug("CharacterControl.encode(" + s + ") :" + ex.toString());
            return s;
        }
    }
    
    public static String decode(String substring) {
        if (EP_COB_GTQ805.retNull(substring) == null) {
            return substring;
        }
        try {
            final StringBuffer sb = new StringBuffer();
            for (int i = substring.indexOf("\\u"); i > -1; i = substring.indexOf("\\u")) {
                sb.append(substring.substring(0, i));
                sb.append(String.valueOf((char)Integer.parseInt(substring.substring(i + 2, i + 6), 16)));
                substring = substring.substring(i + 6);
            }
            sb.append(substring);
            return EP_COB_GTQ805.replace(sb.toString(), "\\0d\\0a", "\n");
        }
        catch (Exception ex) {
            Log.debug("CharacterControl.decode(" + substring + ") :" + ex.toString());
            return substring;
        }
    }
    
    public static String convert(final String s) {
        try {
            return EP_COB_GTQ805.replace(EP_COB_GTQ805.replace(EP_COB_GTQ805.replace(EP_COB_GTQ805.replace(s.toLowerCase().replace('%', '\\'), "\\u", "***m"), "\\", "###u00"), "***m", "\\u"), "###", "\\");
        }
        catch (Exception ex) {
            Log.debug("convert() has errors in the conversion process:" + ex.toString());
            return s;
        }
    }
}
