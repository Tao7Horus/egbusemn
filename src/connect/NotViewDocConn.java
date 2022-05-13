// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.mypage.notviewdoc.datamodel.NotViewDocInfo;
import portal.log.portalLog;
import javax.naming.InitialContext;
import portal.mypage.notviewdoc.session.NotViewDoc;
import portal.mypage.notviewdoc.session.NotViewDocHome;

public class NotViewDocConn
{
    private NotViewDocHome scHome;
    private NotViewDoc scRemote;
    protected final boolean IS_LOG = true;
    protected final String LOGFILE = "NotViewDoc.log";
    protected final String ERRFILE = "NotViewDocErr.log";
    protected String THIS_CLASS_NAME;
    
    public NotViewDocConn() {
        this.scHome = null;
        this.scRemote = null;
        this.THIS_CLASS_NAME = this.getClass().getName();
        try {
            this.scHome = (NotViewDocHome)new InitialContext().lookup("NotViewDoc");
            this.scRemote = this.scHome.create();
        }
        catch (Exception ex) {
            portalLog.log("NotViewDoc.log", "[" + this.THIS_CLASS_NAME + ":NotViewDocConn-Exception]" + ex.toString());
        }
    }
    
    public NotViewDocInfo getNotViewDocList(final String s) {
        NotViewDocInfo notViewDocList = null;
        try {
            notViewDocList = this.scRemote.getNotViewDocList(s);
        }
        catch (Exception ex) {
            portalLog.log("NotViewDoc.log", "[" + this.THIS_CLASS_NAME + ":getNotViewDocList] Exception\n" + ex.toString());
        }
        finally {
            portalLog.log("NotViewDoc.log", "[" + this.THIS_CLASS_NAME + ":getNotViewDocList] 완료");
            return notViewDocList;
        }
    }
}
