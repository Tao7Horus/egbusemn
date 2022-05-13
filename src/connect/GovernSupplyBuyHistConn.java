// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.mypage.GovernSupplyBuyHist.datamodel.GovernSupplyBuyHistInfo;
import java.util.Vector;
import portal.log.portalLog;
import javax.naming.InitialContext;
import portal.mypage.GovernSupplyBuyHist.session.GovernSupplyBuyHist;
import portal.mypage.GovernSupplyBuyHist.session.GovernSupplyBuyHistHome;

public class GovernSupplyBuyHistConn
{
    private GovernSupplyBuyHistHome mpHome;
    private GovernSupplyBuyHist mpRemote;
    protected final boolean IS_LOG = false;
    protected final String LOGFILE = "GovernSupplyBuyHist.log";
    protected final String ERRFILE = "GovernSupplyBuyHistErr.log";
    protected String THIS_CLASS_NAME;
    
    public GovernSupplyBuyHistConn() {
        this.mpHome = null;
        this.mpRemote = null;
        this.THIS_CLASS_NAME = "GovernSupplyBuyHistConn";
        try {
            this.mpHome = (GovernSupplyBuyHistHome)new InitialContext().lookup("GovernSupplyBuyHist");
            this.mpRemote = this.mpHome.create();
        }
        catch (Exception ex) {
            portalLog.log("GovernSupplyBuyHistErr.log", "[" + this.THIS_CLASS_NAME + ":BusinConn] Exception\n" + ex.toString());
        }
    }
    
    public Vector getGovernSupplyBuyHistList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Vector governSupplyBuyHistList = new Vector();
        try {
            governSupplyBuyHistList = this.mpRemote.getGovernSupplyBuyHistList(n, n2, s, s2, s3, s4, s5, s6);
            return governSupplyBuyHistList;
        }
        catch (Exception ex) {
            portalLog.log("GovernSupplyBuyHistErr.log", "[" + this.THIS_CLASS_NAME + ":getGovernSupplyBuyHistList] Exception\n" + ex.toString());
        }
        finally {
            return governSupplyBuyHistList;
        }
    }
    
    public int getGovernSupplyBuyHistListCnt(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        int governSupplyBuyHistListCnt = 0;
        try {
            governSupplyBuyHistListCnt = this.mpRemote.getGovernSupplyBuyHistListCnt(s, s2, s3, s4, s5, s6);
            return governSupplyBuyHistListCnt;
        }
        catch (Exception ex) {
            portalLog.log("GovernSupplyBuyHistErr.log", "[" + this.THIS_CLASS_NAME + ":getGovernSupplyBuyHistListCnt] Exception\n" + ex.toString());
        }
        finally {
            return governSupplyBuyHistListCnt;
        }
    }
    
    public GovernSupplyBuyHistInfo getGovernSupplyBuyHistMaster(final String s) {
        GovernSupplyBuyHistInfo governSupplyBuyHistMaster = new GovernSupplyBuyHistInfo();
        try {
            governSupplyBuyHistMaster = this.mpRemote.getGovernSupplyBuyHistMaster(s);
            return governSupplyBuyHistMaster;
        }
        catch (Exception ex) {
            portalLog.log("GovernSupplyBuyHistErr.log", "[" + this.THIS_CLASS_NAME + ":getGovernSupplyBuyHistMaster] Exception\n" + ex.toString());
        }
        finally {
            return governSupplyBuyHistMaster;
        }
    }
    
    public Vector getNapYoGuHistoty(final String s) {
        Vector napYoGuHistoty = new Vector();
        try {
            napYoGuHistoty = this.mpRemote.getNapYoGuHistoty(s);
            return napYoGuHistoty;
        }
        catch (Exception ex) {
            portalLog.log("GovernSupplyBuyHistErr.log", "[" + this.THIS_CLASS_NAME + ":getNapYoGuHistoty] Exception\n" + ex.toString());
        }
        finally {
            return napYoGuHistoty;
        }
    }
    
    public Vector getNapYoGuStatus(final String s) {
        Vector napYoGuStatus = new Vector();
        try {
            napYoGuStatus = this.mpRemote.getNapYoGuStatus(s);
            return napYoGuStatus;
        }
        catch (Exception ex) {
            portalLog.log("GovernSupplyBuyHistErr.log", "[" + this.THIS_CLASS_NAME + ":getNapYoGuStatus] Exception\n" + ex.toString());
        }
        finally {
            return napYoGuStatus;
        }
    }
    
    public Vector getEndStatus(final String s) {
        Vector endStatus = new Vector();
        try {
            endStatus = this.mpRemote.getEndStatus(s);
            return endStatus;
        }
        catch (Exception ex) {
            portalLog.log("GovernSupplyBuyHistErr.log", "[" + this.THIS_CLASS_NAME + ":getEndStatus] Exception\n" + ex.toString());
        }
        finally {
            return endStatus;
        }
    }
    
    public Vector getGojiHist(final String s) {
        Vector gojiHist = new Vector();
        try {
            gojiHist = this.mpRemote.getGojiHist(s);
            return gojiHist;
        }
        catch (Exception ex) {
            portalLog.log("GovernSupplyBuyHistErr.log", "[" + this.THIS_CLASS_NAME + ":getGojiHist] Exception\n" + ex.toString());
        }
        finally {
            return gojiHist;
        }
    }
    
    public GovernSupplyBuyHistInfo getCompactMaster(final String s, final String s2) {
        GovernSupplyBuyHistInfo compactMaster = new GovernSupplyBuyHistInfo();
        try {
            compactMaster = this.mpRemote.getCompactMaster(s, s2);
            return compactMaster;
        }
        catch (Exception ex) {
            portalLog.log("GovernSupplyBuyHistErr.log", "[" + this.THIS_CLASS_NAME + ":getCompactMaster] Exception\n" + ex.toString());
        }
        finally {
            return compactMaster;
        }
    }
    
    public Vector getCompactHistoty(final String s, final String s2) {
        Vector compactHistoty = new Vector();
        try {
            compactHistoty = this.mpRemote.getCompactHistoty(s, s2);
            return compactHistoty;
        }
        catch (Exception ex) {
            portalLog.log("GovernSupplyBuyHistErr.log", "[" + this.THIS_CLASS_NAME + ":getCompactHistoty] Exception\n" + ex.toString());
        }
        finally {
            return compactHistoty;
        }
    }
    
    public Vector getCompactStatus(final String s, final String s2) {
        Vector compactStatus = new Vector();
        try {
            compactStatus = this.mpRemote.getCompactStatus(s, s2);
            return compactStatus;
        }
        catch (Exception ex) {
            portalLog.log("GovernSupplyBuyHistErr.log", "[" + this.THIS_CLASS_NAME + ":getCompactStatus] Exception\n" + ex.toString());
        }
        finally {
            return compactStatus;
        }
    }
    
    public Vector getCompactEndStatus(final String s) {
        Vector compactEndStatus = new Vector();
        try {
            compactEndStatus = this.mpRemote.getCompactEndStatus(s);
            return compactEndStatus;
        }
        catch (Exception ex) {
            portalLog.log("GovernSupplyBuyHistErr.log", "[" + this.THIS_CLASS_NAME + ":getCompactEndStatus] Exception\n" + ex.toString());
        }
        finally {
            return compactEndStatus;
        }
    }
    
    public Vector getCompactGojiHist(final String s) {
        Vector compactGojiHist = new Vector();
        try {
            compactGojiHist = this.mpRemote.getCompactGojiHist(s);
            return compactGojiHist;
        }
        catch (Exception ex) {
            portalLog.log("GovernSupplyBuyHistErr.log", "[" + this.THIS_CLASS_NAME + ":getCompactGojiHist] Exception\n" + ex.toString());
        }
        finally {
            return compactGojiHist;
        }
    }
    
    public String[] getMCode(final String s) {
        String[] mCode = null;
        try {
            mCode = this.mpRemote.getMCode(s);
            return mCode;
        }
        catch (Exception ex) {
            portalLog.log("GovernSupplyBuyHistErr.log", "[" + this.THIS_CLASS_NAME + ":getMCode] Exception\n" + ex.toString());
        }
        finally {
            return mCode;
        }
    }
}
