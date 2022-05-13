// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.mypage.salehist.datamodel.SaleHistInfo;
import portal.log.portalLog;
import javax.naming.InitialContext;
import portal.mypage.salehist.session.SaleHist;
import portal.mypage.salehist.session.SaleHistHome;

public class SaleHistConn
{
    private SaleHistHome mpHome;
    private SaleHist mpRemote;
    protected final boolean IS_LOG = true;
    protected final String LOGFILE = "SaleHist.log";
    protected final String ERRFILE = "SaleHistErr.log";
    protected String THIS_CLASS_NAME;
    
    public SaleHistConn() {
        this.mpHome = null;
        this.mpRemote = null;
        this.THIS_CLASS_NAME = "SaleHistConn";
        try {
            this.mpHome = (SaleHistHome)new InitialContext().lookup("SaleHist");
            this.mpRemote = this.mpHome.create();
        }
        catch (Exception ex) {
            portalLog.log("SaleHistErr.log", "[" + this.THIS_CLASS_NAME + ":BusinConn] Exception\n" + ex.toString());
        }
    }
    
    public SaleHistInfo getSaleHistList(final int n, final int n2, final String s, final String s2, final String s3, final String s4) {
        SaleHistInfo saleHistList = null;
        try {
            saleHistList = this.mpRemote.getSaleHistList(n, n2, s, s2, s3, s4);
        }
        catch (Exception ex) {
            portalLog.log("SaleHistErr.log", "[" + this.THIS_CLASS_NAME + ":getSaleHistList] Exception\n" + ex.toString());
        }
        finally {
            portalLog.log("SaleHist.log", "[" + this.THIS_CLASS_NAME + ":getSaleHistList] 완료");
            return saleHistList;
        }
    }
}
