// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import common.Log;
import javax.naming.InitialContext;
import portal.community.popup.session.Popup;
import portal.community.popup.session.PopupHome;

public class PopupServlet
{
    private PopupHome pop_home;
    private Popup pop_remote;
    
    public PopupServlet() {
        this.pop_home = null;
        this.pop_remote = null;
        try {
            this.pop_home = (PopupHome)new InitialContext().lookup("Popup");
            this.pop_remote = this.pop_home.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public String chkNotice() {
        String chkNotice = null;
        try {
            chkNotice = this.pop_remote.chkNotice();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return chkNotice;
    }
}
