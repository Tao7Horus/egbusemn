// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class ExplorerControl
{
    public static String TYPE_PC;
    public static String TYPE_SYNC;
    public static String TYPE_PDA;
    public static String Type_2002;
    public static String Type_2003;
    public static String Type_2005;
    public static String Type_2006;
    
    static {
        ExplorerControl.TYPE_PC = "PC";
        ExplorerControl.TYPE_SYNC = "SYNC";
        ExplorerControl.TYPE_PDA = "PDA";
        ExplorerControl.Type_2002 = "P_2002";
        ExplorerControl.Type_2003 = "P_2003";
        ExplorerControl.Type_2005 = "P_2005";
        ExplorerControl.Type_2006 = "P_2006";
    }
    
    public static String getBrowerType(final HttpServletRequest req) throws ServletException, IOException {
        final String sBrowserType = req.getHeader("user-agent");
        if (sBrowserType == null || sBrowserType.equals("")) {
            return ExplorerControl.TYPE_PC;
        }
        if (sBrowserType.indexOf("ESCAPE") >= 0) {
            return ExplorerControl.TYPE_SYNC;
        }
        if (sBrowserType.indexOf("Windows CE") >= 0) {
            return ExplorerControl.TYPE_PDA;
        }
        return ExplorerControl.TYPE_PC;
    }
    
    public static String getOsType(final HttpServletRequest req) throws ServletException, IOException {
        final String sOsType = req.getHeader("ua-os");
        if (sOsType == null || sOsType.equals("")) {
            return ExplorerControl.TYPE_PC;
        }
        System.out.println(" sOsType : " + sOsType);
        if (sOsType.indexOf("3.") >= 0) {
            return ExplorerControl.Type_2002;
        }
        if (sOsType.indexOf("4.") >= 0) {
            return ExplorerControl.Type_2003;
        }
        if (sOsType.indexOf("5.") >= 0) {
            return ExplorerControl.Type_2005;
        }
        if (sOsType.indexOf("6.") >= 0) {
            return ExplorerControl.Type_2006;
        }
        return ExplorerControl.TYPE_PC;
    }
    
    public static String getIP(final HttpServletRequest req) throws ServletException, IOException {
        String IP = null;
        IP = req.getHeader("x-forwarded-for");
        if (IP == null) {
            IP = req.getHeader("WL-Proxy-Client-IP");
        }
        if (IP == null) {
            IP = req.getRemoteAddr();
        }
        if (IP == null) {
            IP = "IP조회못함";
        }
        return IP;
    }
}
