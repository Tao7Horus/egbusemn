// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.customer.online.datamodel.OnlineFile;
import portal.customer.online.datamodel.OnlineEdit;
import java.util.Vector;
import portal.customer.online.datamodel.OnlineView;
import portal.customer.online.datamodel.OnlineList;
import common.Log;
import javax.naming.InitialContext;
import portal.customer.online.session.OnlineHome;
import portal.customer.online.session.Online;

public class OnlineConn
{
    private Online online_remote;
    private OnlineHome online_Home;
    
    public OnlineConn() {
        this.online_remote = null;
        this.online_Home = null;
        try {
            this.online_Home = (OnlineHome)new InitialContext().lookup("Online");
            this.online_remote = this.online_Home.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public OnlineList iOnlineList(final String s, final int n, final int n2) {
        OnlineList itemList = null;
        try {
            itemList = this.online_remote.itemList(s, n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemList;
    }
    
    public OnlineList storeListSearch(final String s, final int n, final int n2, final String s2, final String s3) {
        OnlineList itemListSearch = null;
        try {
            itemListSearch = this.online_remote.itemListSearch(s, n, n2, s2, s3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemListSearch;
    }
    
    public OnlineView OnlineitemView(final String s, final int n, final boolean b, final String s2) {
        OnlineView itemView = null;
        try {
            itemView = this.online_remote.itemView(s, n, b, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemView;
    }
    
    public int onlineAdd(final String s, final String s2, final String s3, final String s4, final String s5, final Vector vector) {
        int itemAdd = 0;
        try {
            itemAdd = this.online_remote.itemAdd(s, s2, s3, s4, s5, vector);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemAdd;
    }
    
    public OnlineEdit OnlineGet(final String s, final int n) {
        OnlineEdit itemGet = null;
        try {
            itemGet = this.online_remote.itemGet(s, n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemGet;
    }
    
    public int OnlineEdit(final String s, final int n, final String s2, final String s3, final Vector vector) {
        int itemEdit = 0;
        try {
            itemEdit = this.online_remote.itemEdit(s, n, s2, s3, vector);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemEdit;
    }
    
    public Vector OnlineViewSel(final String s, final String s2) {
        Vector itemViewSel = null;
        try {
            itemViewSel = this.online_remote.itemViewSel(s, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemViewSel;
    }
    
    public int OnlineDel(final String s, final int n, final String s2) {
        int itemDel = 0;
        try {
            itemDel = this.online_remote.itemDel(s, n, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDel;
    }
    
    public int OnlineDelSel(final String s, final String s2, final String s3) {
        int itemDelSel = 0;
        try {
            itemDelSel = this.online_remote.itemDelSel(s, s2, s3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDelSel;
    }
    
    public OnlineFile OnlineDown(final String s, final int n, final int n2) {
        OnlineFile itemDown = null;
        try {
            itemDown = this.online_remote.itemDown(s, n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDown;
    }
}
