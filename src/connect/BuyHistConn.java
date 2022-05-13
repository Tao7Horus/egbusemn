// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import java.util.Vector;
import portal.log.portalLog;
import javax.naming.InitialContext;
import portal.mypage.buyhist.session.BuyHist;
import portal.mypage.buyhist.session.BuyHistHome;

public class BuyHistConn
{
    private BuyHistHome mpHome;
    private BuyHist mpRemote;
    protected final boolean IS_LOG = false;
    protected final String LOGFILE = "BuyHist.log";
    protected final String ERRFILE = "BuyHistErr.log";
    protected String THIS_CLASS_NAME;
    
    public BuyHistConn() {
        this.mpHome = null;
        this.mpRemote = null;
        this.THIS_CLASS_NAME = "BuyHistConn";
        try {
            this.mpHome = (BuyHistHome)new InitialContext().lookup("BuyHist");
            this.mpRemote = this.mpHome.create();
        }
        catch (Exception ex) {
            portalLog.log("BuyHistErr.log", "[" + this.THIS_CLASS_NAME + ":BusinConn] Exception\n" + ex.toString());
        }
    }
    
    public Vector getBuyHistList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5) {
        Vector buyHistList = new Vector();
        try {
            buyHistList = this.mpRemote.getBuyHistList(n, n2, s, s2, s3, s4, s5);
            return buyHistList;
        }
        catch (Exception ex) {
            portalLog.log("BuyHistErr.log", "[" + this.THIS_CLASS_NAME + ":getBuyHistList] Exception\n" + ex.toString());
        }
        finally {
            return buyHistList;
        }
    }
    
    public int getBuyHistListCnt(final String s, final String s2, final String s3, final String s4, final String s5) {
        int buyHistListCnt = 0;
        try {
            buyHistListCnt = this.mpRemote.getBuyHistListCnt(s, s2, s3, s4, s5);
        }
        catch (Exception ex) {
            System.out.println("getBuyHistListCnt_servlet : " + ex);
        }
        return buyHistListCnt;
    }
}
