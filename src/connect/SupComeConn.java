// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.mypage.supcome.datamodel.SupComeInfo;
import portal.log.portalLog;
import javax.naming.InitialContext;
import portal.mypage.supcome.session.SupCome;
import portal.mypage.supcome.session.SupComeHome;

public class SupComeConn
{
    private SupComeHome scHome;
    private SupCome scRemote;
    protected final boolean IS_LOG = true;
    protected final String LOGFILE = "SupCome.log";
    protected final String ERRFILE = "SupComeErr.log";
    protected String THIS_CLASS_NAME;
    
    public SupComeConn() {
        this.scHome = null;
        this.scRemote = null;
        this.THIS_CLASS_NAME = "SupComeConn";
        try {
            this.scHome = (SupComeHome)new InitialContext().lookup("SupCome");
            this.scRemote = this.scHome.create();
        }
        catch (Exception ex) {
            portalLog.log("SupCome.log", "[" + this.THIS_CLASS_NAME + ":SupComeConn-Exception]" + ex.toString());
        }
    }
    
    public SupComeInfo getSupComeList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final boolean b, final boolean b2) {
        SupComeInfo supComeList = null;
        try {
            supComeList = this.scRemote.getSupComeList(n, n2, s, s2, s3, s4, s5, b, b2);
        }
        catch (Exception ex) {
            portalLog.log("SupCome.log", "[" + this.THIS_CLASS_NAME + ":getSupComeList] Exception\n" + ex.toString());
        }
        finally {
            portalLog.log("SupCome.log", "[" + this.THIS_CLASS_NAME + ":getSupComeList] 완료");
            return supComeList;
        }
    }
    
    public int getSupComeOnlyCNT(final String s, final String s2, final String s3, final String s4, final String s5, final boolean b, final boolean b2) {
        int supComeOnlyCNT = 0;
        try {
            supComeOnlyCNT = this.scRemote.getSupComeOnlyCNT(s, s2, s3, s4, s5, b, b2);
        }
        catch (Exception ex) {
            portalLog.log("SupCome.log", "[" + this.THIS_CLASS_NAME + ":getSupComeOnlyCntCNT] Exception\n" + ex.toString());
        }
        finally {
            portalLog.log("SupCome.log", "[" + this.THIS_CLASS_NAME + ":getSupComeOnlyCntCNT] 완료");
            return supComeOnlyCNT;
        }
    }
    
    public SupComeInfo getSupComeOnlyList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final boolean b, final boolean b2) {
        SupComeInfo supComeOnlyList = null;
        try {
            supComeOnlyList = this.scRemote.getSupComeOnlyList(n, n2, s, s2, s3, s4, s5, b, b2);
        }
        catch (Exception ex) {
            portalLog.log("SupCome.log", "[" + this.THIS_CLASS_NAME + ":getSupComeOnlyList] Exception\n" + ex.toString());
        }
        finally {
            portalLog.log("SupCome.log", "[" + this.THIS_CLASS_NAME + ":getSupComeOnlyList] 완료");
            return supComeOnlyList;
        }
    }
}
