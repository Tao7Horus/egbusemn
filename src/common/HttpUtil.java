// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.util.StringTokenizer;

public class HttpUtil
{
    private static String META;
    private static String SCRIPT_ON;
    private static String SCRIPT_OFF;
    private static String HISTORY_BACK;
    
    static {
        HttpUtil.META = "<meta http-equiv=\"Content-type\" content=\"text/html; charset=euc-kr\"> \n";
        HttpUtil.SCRIPT_ON = "<script Language=\"JavaScript\">\n<!--  \n";
        HttpUtil.SCRIPT_OFF = "\n// -->\n</script>\n ";
        HttpUtil.HISTORY_BACK = "\thistory.back() ; \n ";
    }
    
    public static String[] split(final String str, final String spliter) throws Exception {
        if (str != null) {
            str.equals("");
        }
        try {
            final StringTokenizer st = new StringTokenizer(str, spliter);
            final int len = st.countTokens();
            final String[] bufArr = new String[len];
            for (int i = 0; i < len; ++i) {
                bufArr[i] = (String)st.nextElement();
            }
            return bufArr;
        }
        catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
    
    public String[] jobgu(final String str) {
        final String[] gubun = new String[5];
        for (int i = 0; i < str.length(); ++i) {
            gubun[i] = str.substring(i, i + 1);
        }
        return gubun;
    }
    
    public static int[] Intsplit(final String str, final String spliter) throws Exception {
        if (str != null) {
            str.equals("");
        }
        try {
            final StringTokenizer st = new StringTokenizer(str, spliter);
            final int len = st.countTokens();
            final int[] bufArr = new int[len];
            String bufVar = new String();
            for (int i = 0; i < len; ++i) {
                bufVar = (String)st.nextElement();
                bufArr[i] = Integer.parseInt(bufVar);
            }
            return bufArr;
        }
        catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
}
