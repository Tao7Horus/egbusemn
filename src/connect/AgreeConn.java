// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import java.util.Vector;
import common.Log;
import javax.naming.InitialContext;
import portal.mypage.agree.session.Agree;
import portal.mypage.agree.session.AgreeHome;

public class AgreeConn
{
    private AgreeHome agreehome;
    private Agree agree;
    
    public AgreeConn() {
        this.agreehome = null;
        this.agree = null;
        try {
            this.agreehome = (AgreeHome)new InitialContext().lookup("Agree");
            this.agree = this.agreehome.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public Vector getAgreeList(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        Vector agreeList = new Vector();
        try {
            agreeList = this.agree.getAgreeList(s, s2, s3, s4, s5, s6, s7);
        }
        catch (Exception ex) {
            System.out.println("getAgreeList_servlet : " + ex);
        }
        return agreeList;
    }
    
    public int getAgreeCnt1(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        int agreeCnt1 = 0;
        try {
            agreeCnt1 = this.agree.getAgreeCnt1(s, s2, s3, s4, s5, s6, s7);
        }
        catch (Exception ex) {
            System.out.println("getAgreeCnt1_servlet : " + ex);
        }
        return agreeCnt1;
    }
    
    public int getAgreeCnt2(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        int agreeCnt2 = 0;
        try {
            agreeCnt2 = this.agree.getAgreeCnt2(s, s2, s3, s4, s5, s6, s7);
        }
        catch (Exception ex) {
            System.out.println("getAgreeCnt2_servlet : " + ex);
        }
        return agreeCnt2;
    }
    
    public int getAgreeCnt3(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        int agreeCnt3 = 0;
        try {
            agreeCnt3 = this.agree.getAgreeCnt3(s, s2, s3, s4, s5, s6, s7);
        }
        catch (Exception ex) {
            System.out.println("getAgreeCnt3_servlet : " + ex);
        }
        return agreeCnt3;
    }
}
