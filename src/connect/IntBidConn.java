// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.mypage.intbid.datamodel.IntBidInfo;
import portal.log.portalLog;
import javax.naming.InitialContext;
import portal.mypage.intbid.session.IntBid;
import portal.mypage.intbid.session.IntBidHome;

public class IntBidConn
{
    private IntBidHome mpHome;
    private IntBid mpRemote;
    protected final boolean IS_LOG = false;
    protected final String LOGFILE = "IntBid.log";
    protected final String ERRFILE = "IntBidErr.log";
    protected String THIS_CLASS_NAME;
    
    public IntBidConn() {
        this.mpHome = null;
        this.mpRemote = null;
        this.THIS_CLASS_NAME = "IntBidConn";
        try {
            this.mpHome = (IntBidHome)new InitialContext().lookup("IntBid");
            this.mpRemote = this.mpHome.create();
        }
        catch (Exception ex) {
            portalLog.log("IntBidErr.log", "[" + this.THIS_CLASS_NAME + ":BusinConn] Exception\n" + ex.toString());
        }
    }
    
    public IntBidInfo getIntBidList(final int n, final int n2, final String s, final String s2, final String s3, final int n3, final String s4, final String s5, final String s6) {
        IntBidInfo intBidList = null;
        try {
            intBidList = this.mpRemote.getIntBidList(n, n2, s, s2, s3, n3, s4, s5, s6);
            return intBidList;
        }
        catch (Exception ex) {
            portalLog.log("IntBidErr.log", "[" + this.THIS_CLASS_NAME + ":getIntBidList] Exception\n" + ex.toString());
        }
        finally {
            return intBidList;
        }
    }
    
    public boolean isSetup(final String s) {
        boolean setup = false;
        try {
            setup = this.mpRemote.isSetup(s);
            return setup;
        }
        catch (Exception ex) {
            portalLog.log("IntBidErr.log", "[" + this.THIS_CLASS_NAME + ":isSetup] Exception\n" + ex.toString());
        }
        finally {
            return setup;
        }
    }
}
