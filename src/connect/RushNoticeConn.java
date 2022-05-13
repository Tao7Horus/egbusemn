// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.customer.rushnotice.datamodel.RushNoticeFile;
import portal.customer.rushnotice.datamodel.RushNoticeInfo;
import java.util.Vector;
import portal.customer.rushnotice.datamodel.RushNoticeView;
import portal.customer.rushnotice.datamodel.RushNoticeList;
import common.Log;
import javax.naming.InitialContext;
import portal.customer.rushnotice.session.RushNotice;
import portal.customer.rushnotice.session.RushNoticeHome;

public class RushNoticeConn
{
    private RushNoticeHome oHome;
    private RushNotice rush;
    
    public RushNoticeConn() {
        this.oHome = null;
        this.rush = null;
        try {
            this.oHome = (RushNoticeHome)new InitialContext().lookup("RushNotice");
            this.rush = this.oHome.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public RushNoticeList noticeList(final int n, final int n2) {
        RushNoticeList itemList = null;
        try {
            itemList = this.rush.itemList(n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemList;
    }
    
    public RushNoticeList noticeListSearch(final int n, final int n2, final String s, final String s2) {
        RushNoticeList itemListSearch = null;
        try {
            itemListSearch = this.rush.itemListSearch(n, n2, s, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemListSearch;
    }
    
    public RushNoticeView noticeGet(final int n) {
        RushNoticeView itemGet = null;
        try {
            itemGet = this.rush.itemGet(n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemGet;
    }
    
    public RushNoticeView noticeView(final int n, final boolean b, final String s) {
        RushNoticeView itemView = null;
        try {
            itemView = this.rush.itemView(n, b, s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemView;
    }
    
    public Vector noticeViewSel(final String s) {
        Vector itemViewSel = null;
        try {
            itemViewSel = this.rush.itemViewSel(s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemViewSel;
    }
    
    public int noticeAdd(final RushNoticeInfo rushNoticeInfo, final Vector vector) {
        int itemAdd = 0;
        try {
            itemAdd = this.rush.itemAdd(rushNoticeInfo, vector);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemAdd;
    }
    
    public int noticeEdit(final RushNoticeInfo rushNoticeInfo, final Vector vector) {
        int itemEdit = 0;
        try {
            itemEdit = this.rush.itemEdit(rushNoticeInfo, vector);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemEdit;
    }
    
    public int noticeDel(final int n, final String s) {
        int itemDel = 0;
        try {
            itemDel = this.rush.itemDel(n, s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDel;
    }
    
    public int noticeDelSel(final String s, final String s2) {
        int itemDelSel = 0;
        try {
            itemDelSel = this.rush.itemDelSel(s, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDelSel;
    }
    
    public RushNoticeFile noticeDown(final int n, final int n2) {
        RushNoticeFile itemDown = null;
        try {
            itemDown = this.rush.itemDown(n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDown;
    }
}
