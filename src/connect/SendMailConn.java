// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.common.sendmail.datamodel.SendMailInfo;
import portal.log.portalLog;
import javax.naming.InitialContext;
import portal.common.sendmail.session.SendMail;
import portal.common.sendmail.session.SendMailHome;

public class SendMailConn
{
    private SendMailHome smHome;
    private SendMail smRemote;
    protected final boolean IS_LOG = true;
    protected final String LOGFILE = "SendMail.log";
    protected final String ERRFILE = "SendMailErr.log";
    protected String THIS_CLASS_NAME;
    
    public SendMailConn() {
        this.smHome = null;
        this.smRemote = null;
        this.THIS_CLASS_NAME = "SendMailConn";
        try {
            this.smHome = (SendMailHome)new InitialContext().lookup("SendMail");
            this.smRemote = this.smHome.create();
        }
        catch (Exception ex) {
            portalLog.log("SendMail.log", "[" + this.THIS_CLASS_NAME + ":SendMailConn-Exception]" + ex.toString());
        }
    }
    
    public int setSendMail(final SendMailInfo sendMail) {
        int setSendMail = 0;
        try {
            setSendMail = this.smRemote.setSendMail(sendMail);
        }
        catch (Exception ex) {
            portalLog.log("SendMail.log", "[" + this.THIS_CLASS_NAME + ":setSendMail] Exception\n" + ex.toString());
        }
        finally {
            portalLog.log("SendMail.log", "[" + this.THIS_CLASS_NAME + ":setSendMail] 완료");
            return setSendMail;
        }
    }
}
