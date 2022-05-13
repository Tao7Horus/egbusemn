// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import java.util.Vector;
import portal.log.portalLog;
import javax.naming.InitialContext;
import portal.mypage.compactstatus.session.CompactStatus;
import portal.mypage.compactstatus.session.CompactStatusHome;

public class CompactStatusConn
{
    private CompactStatusHome mpHome;
    private CompactStatus mpRemote;
    protected final boolean IS_LOG = false;
    protected final String LOGFILE = "CompactStatus.log";
    protected final String ERRFILE = "CompactStatusErr.log";
    protected String THIS_CLASS_NAME;
    
    public CompactStatusConn() {
        this.mpHome = null;
        this.mpRemote = null;
        this.THIS_CLASS_NAME = "CompactStatusConn";
        try {
            this.mpHome = (CompactStatusHome)new InitialContext().lookup("CompactStatus");
            this.mpRemote = this.mpHome.create();
        }
        catch (Exception ex) {
            portalLog.log("CompactStatusErr.log", "[" + this.THIS_CLASS_NAME + ":BusinConn] Exception\n" + ex.toString());
        }
    }
    
    public Vector getCompactStatusList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Vector compactStatusList = new Vector();
        try {
            compactStatusList = this.mpRemote.getCompactStatusList(n, n2, s, s2, s3, s4, s5, s6);
            return compactStatusList;
        }
        catch (Exception ex) {
            portalLog.log("CompactStatusErr.log", "[" + this.THIS_CLASS_NAME + ":getCompactStatusList] Exception\n" + ex.toString());
        }
        finally {
            return compactStatusList;
        }
    }
    
    public int getCompactStatusListCnt(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        int compactStatusListCnt = 0;
        try {
            compactStatusListCnt = this.mpRemote.getCompactStatusListCnt(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println("getCompactStatusListCnt_servlet : " + ex);
        }
        return compactStatusListCnt;
    }
    
    public Vector getCompactStatusDetailDown(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Vector compactStatusDetailDown = new Vector();
        try {
            compactStatusDetailDown = this.mpRemote.getCompactStatusDetailDown(s, s2, s3, s4, s5, s6);
            return compactStatusDetailDown;
        }
        catch (Exception ex) {
            portalLog.log("CompactStatusErr.log", "[" + this.THIS_CLASS_NAME + ":getCompactStatusDetailDown] Exception\n" + ex.toString());
        }
        finally {
            return compactStatusDetailDown;
        }
    }
    
    public Vector getCompactStatusDetail(final String s, final String s2, final String s3) {
        Vector compactStatusDetail = new Vector();
        try {
            compactStatusDetail = this.mpRemote.getCompactStatusDetail(s, s2, s3);
            return compactStatusDetail;
        }
        catch (Exception ex) {
            portalLog.log("CompactStatusErr.log", "[" + this.THIS_CLASS_NAME + ":getCompactStatusDetail] Exception\n" + ex.toString());
        }
        finally {
            return compactStatusDetail;
        }
    }
    
    public boolean masterCodeCheck(final String s) {
        boolean masterCodeCheck = false;
        try {
            masterCodeCheck = this.mpRemote.masterCodeCheck(s);
            return masterCodeCheck;
        }
        catch (Exception ex) {
            portalLog.log("CompactStatusErr.log", "[" + this.THIS_CLASS_NAME + ":masterCodeCheck] Exception\n" + ex.toString());
        }
        finally {
            return masterCodeCheck;
        }
    }
    
    public Vector getSelCompactStatusList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Vector selCompactStatusList = new Vector();
        try {
            selCompactStatusList = this.mpRemote.getSelCompactStatusList(n, n2, s, s2, s3, s4, s5, s6);
            return selCompactStatusList;
        }
        catch (Exception ex) {
            portalLog.log("CompactStatusErr.log", "[" + this.THIS_CLASS_NAME + ":getSelCompactStatusList] Exception\n" + ex.toString());
        }
        finally {
            return selCompactStatusList;
        }
    }
    
    public int getSelCompactStatusListCnt(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        int selCompactStatusListCnt = 0;
        try {
            selCompactStatusListCnt = this.mpRemote.getSelCompactStatusListCnt(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println("getSelCompactStatusListCnt_servlet : " + ex);
        }
        return selCompactStatusListCnt;
    }
    
    public Vector getSelCompactStatusDetailDown(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Vector selCompactStatusDetailDown = new Vector();
        try {
            selCompactStatusDetailDown = this.mpRemote.getSelCompactStatusDetailDown(s, s2, s3, s4, s5, s6);
            return selCompactStatusDetailDown;
        }
        catch (Exception ex) {
            portalLog.log("CompactStatusErr.log", "[" + this.THIS_CLASS_NAME + ":getSelCompactStatusDetailDown] Exception\n" + ex.toString());
        }
        finally {
            return selCompactStatusDetailDown;
        }
    }
}
