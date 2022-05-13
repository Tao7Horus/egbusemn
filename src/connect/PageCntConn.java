// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.common.pagecnt.datamodel.PageCntInfo;
import portal.log.portalLog;
import javax.naming.InitialContext;
import portal.common.pagecnt.session.PageCnt;
import portal.common.pagecnt.session.PageCntHome;

public class PageCntConn
{
    private PageCntHome pcHome;
    private PageCnt pcRemote;
    protected final boolean IS_LOG = false;
    protected final String LOGFILE = "PageCnt.log";
    protected final String ERRFILE = "PageCntErr.log";
    protected String THIS_CLASS_NAME;
    
    public PageCntConn() {
        this.pcHome = null;
        this.pcRemote = null;
        this.THIS_CLASS_NAME = this.getClass().getName();
        try {
            this.pcHome = (PageCntHome)new InitialContext().lookup("PageCnt");
            this.pcRemote = this.pcHome.create();
        }
        catch (Exception ex) {
            portalLog.log("PageCntErr.log", "[" + this.THIS_CLASS_NAME + ":PageCntConn()] Exception\n" + ex.toString());
        }
    }
    
    public int setPageCnt(final String s) {
        int insertPageDtl = -1;
        try {
            insertPageDtl = this.pcRemote.insertPageDtl(s);
        }
        catch (Exception ex) {
            portalLog.log("PageCntErr.log", "[" + this.THIS_CLASS_NAME + ":setPageCnt(String)] Exception\n" + ex.toString());
        }
        return insertPageDtl;
    }
    
    public long getPageCnt(final String s) {
        long selectPageCnt = -1L;
        try {
            selectPageCnt = this.pcRemote.selectPageCnt(s);
        }
        catch (Exception ex) {
            portalLog.log("PageCntErr.log", "[" + this.THIS_CLASS_NAME + ":getPageCnt(String)] Exception\n" + ex.toString());
        }
        return selectPageCnt;
    }
    
    public long getPageCnt(final String s, final String s2) {
        long selectPageCnt = -1L;
        try {
            selectPageCnt = this.pcRemote.selectPageCnt(s, s2);
        }
        catch (Exception ex) {
            portalLog.log("PageCntErr.log", "[" + this.THIS_CLASS_NAME + ":getPageCnt(String)] Exception\n" + ex.toString());
        }
        return selectPageCnt;
    }
    
    public PageCntInfo getPageInfo(final String s) {
        PageCntInfo selectPageMst = null;
        try {
            selectPageMst = this.pcRemote.selectPageMst(s);
        }
        catch (Exception ex) {
            portalLog.log("PageCntErr.log", "[" + this.THIS_CLASS_NAME + ":getPageInfo(String)] Exception\n" + ex.toString());
        }
        return selectPageMst;
    }
    
    public PageCntInfo getPageCntInfo(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        PageCntInfo selectPageDtl = null;
        try {
            selectPageDtl = this.pcRemote.selectPageDtl(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            portalLog.log("PageCntErr.log", "[" + this.THIS_CLASS_NAME + ":getPageInfo(String)] Exception\n" + ex.toString());
        }
        return selectPageDtl;
    }
}
