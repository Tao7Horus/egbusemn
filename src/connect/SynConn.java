// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.syn.datamodel.SynUser;
import java.util.Vector;
import common.Log;
import javax.naming.InitialContext;
import portal.syn.session.SynHome;
import portal.syn.session.Syn;

public class SynConn
{
    private Syn syn;
    
    public SynConn() {
        this.syn = null;
        try {
            this.syn = ((SynHome)new InitialContext().lookup("Syn")).create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public Vector getCataRecent(final int n) {
        Vector itemCataRecent = null;
        try {
            itemCataRecent = this.syn.itemCataRecent(n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemCataRecent;
    }
    
    public Vector getTgongRecent(final int n) {
        Vector itemTgongRecent = null;
        try {
            itemTgongRecent = this.syn.itemTgongRecent(n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemTgongRecent;
    }
    
    public String getOrgName(final String s) {
        String itemOrgName = null;
        try {
            itemOrgName = this.syn.itemOrgName(s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemOrgName;
    }
    
    public SynUser getUser(final String s) {
        SynUser itemUser = null;
        try {
            itemUser = this.syn.itemUser(s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemUser;
    }
    
    public String getUserDiv(final String s) {
        String itemUserDiv = null;
        try {
            itemUserDiv = this.syn.itemUserDiv(s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemUserDiv;
    }
    
    public String getUserName(final String s) {
        String itemUserName = null;
        try {
            itemUserName = this.syn.itemUserName(s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemUserName;
    }
    
    public String getUserMail(final String s) {
        String itemUserMail = null;
        try {
            itemUserMail = this.syn.itemUserMail(s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemUserMail;
    }
}
