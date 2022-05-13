// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import java.util.Vector;
import common.Log;
import javax.naming.InitialContext;
import portal.mypage.agreeedi.session.AgreeEdi;
import portal.mypage.agreeedi.session.AgreeEdiHome;

public class AgreeEdiConn
{
    private AgreeEdiHome agreeedihome;
    private AgreeEdi agreeedi;
    
    public AgreeEdiConn() {
        this.agreeedihome = null;
        this.agreeedi = null;
        try {
            this.agreeedihome = (AgreeEdiHome)new InitialContext().lookup("AgreeEdi");
            this.agreeedi = this.agreeedihome.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public Vector getAgreeEdiList1(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Vector agreeEdiList1 = new Vector();
        try {
            agreeEdiList1 = this.agreeedi.getAgreeEdiList1(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println("getAgreeEdiList1_servlet : " + ex);
        }
        return agreeEdiList1;
    }
    
    public Vector getAgreeEdiList2(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Vector agreeEdiList2 = new Vector();
        try {
            agreeEdiList2 = this.agreeedi.getAgreeEdiList2(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println("getAgreeEdiList2_servlet : " + ex);
        }
        return agreeEdiList2;
    }
    
    public Vector getAgreeEdiList3(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Vector agreeEdiList3 = new Vector();
        try {
            agreeEdiList3 = this.agreeedi.getAgreeEdiList3(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println("getAgreeEdiList3_servlet : " + ex);
        }
        return agreeEdiList3;
    }
    
    public Vector getAgreeEdiList4(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Vector agreeEdiList4 = new Vector();
        try {
            agreeEdiList4 = this.agreeedi.getAgreeEdiList4(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println("getAgreeEdiList4_servlet : " + ex);
        }
        return agreeEdiList4;
    }
    
    public int getAgreeEdiCnt1(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        int agreeEdiCnt1 = 0;
        try {
            agreeEdiCnt1 = this.agreeedi.getAgreeEdiCnt1(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println("getAgreeEdiCnt1_servlet : " + ex);
        }
        return agreeEdiCnt1;
    }
    
    public int getAgreeEdiCnt2(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        int agreeEdiCnt2 = 0;
        try {
            agreeEdiCnt2 = this.agreeedi.getAgreeEdiCnt2(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println("getAgreeEdiCnt2_servlet : " + ex);
        }
        return agreeEdiCnt2;
    }
    
    public int getAgreeEdiCnt3(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        int agreeEdiCnt3 = 0;
        try {
            agreeEdiCnt3 = this.agreeedi.getAgreeEdiCnt3(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println("getAgreeEdiCnt3_servlet : " + ex);
        }
        return agreeEdiCnt3;
    }
    
    public int getAgreeEdiCnt4(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        int agreeEdiCnt4 = 0;
        try {
            agreeEdiCnt4 = this.agreeedi.getAgreeEdiCnt4(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println("getAgreeEdiCnt4_servlet : " + ex);
        }
        return agreeEdiCnt4;
    }
}
