// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.customer.orgnotice.datamodel.OrgNoticeFile;
import portal.customer.orgnotice.datamodel.OrgNoticeInfo;
import java.util.Vector;
import portal.customer.orgnotice.datamodel.OrgNoticeView;
import portal.customer.orgnotice.datamodel.OrgNoticeList;
import portal.log.portalLog;
import javax.naming.InitialContext;
import portal.customer.orgnotice.session.OrgNoticeHome;
import portal.customer.orgnotice.session.OrgNotice;

public class OrgNoticeConn
{
    protected final String LOGFILE = "OrgNoticeErr.log";
    protected final boolean IS_LOG = false;
    protected final String THIS_CLASS_NAME;
    private OrgNotice org;
    
    public OrgNoticeConn() {
        this.THIS_CLASS_NAME = this.getClass().getName();
        this.org = null;
        try {
            this.org = ((OrgNoticeHome)new InitialContext().lookup("OrgNotice")).create();
        }
        catch (Exception ex) {
            portalLog.log("OrgNoticeErr.log", "[" + this.THIS_CLASS_NAME + "]" + ex.toString());
        }
    }
    
    public int noticeDiv(final String s) {
        int itemDiv = 1;
        try {
            itemDiv = this.org.itemDiv(s);
        }
        catch (Exception ex) {
            portalLog.log("OrgNoticeErr.log", "[" + this.THIS_CLASS_NAME + "]" + ex.toString());
        }
        return itemDiv;
    }
    
    public OrgNoticeList noticeList(final int n, final int n2, final int n3, final String s, final String s2) {
        OrgNoticeList itemList = null;
        try {
            itemList = this.org.itemList(n, n2, n3, s, s2);
        }
        catch (Exception ex) {
            portalLog.log("OrgNoticeErr.log", "[" + this.THIS_CLASS_NAME + "]" + ex.toString());
        }
        return itemList;
    }
    
    public OrgNoticeList noticeListSearch(final int n, final int n2, final int n3, final String s, final String s2, final String s3, final String s4) {
        OrgNoticeList itemListSearch = null;
        try {
            itemListSearch = this.org.itemListSearch(n, n2, n3, s, s2, s3, s4);
        }
        catch (Exception ex) {
            portalLog.log("OrgNoticeErr.log", "[" + this.THIS_CLASS_NAME + "]" + ex.toString());
        }
        return itemListSearch;
    }
    
    public OrgNoticeView noticeGet(final int n) {
        OrgNoticeView itemGet = null;
        try {
            itemGet = this.org.itemGet(n);
        }
        catch (Exception ex) {
            portalLog.log("OrgNoticeErr.log", "[" + this.THIS_CLASS_NAME + "]" + ex.toString());
        }
        return itemGet;
    }
    
    public OrgNoticeView noticeView(final int n, final int n2, final boolean b, final String s, final String s2) {
        OrgNoticeView itemView = null;
        try {
            itemView = this.org.itemView(n, n2, b, s, s2);
        }
        catch (Exception ex) {
            portalLog.log("OrgNoticeErr.log", "[" + this.THIS_CLASS_NAME + "]" + ex.toString());
        }
        return itemView;
    }
    
    public Vector noticeViewSel(final String s) {
        Vector itemViewSel = null;
        try {
            itemViewSel = this.org.itemViewSel(s);
        }
        catch (Exception ex) {
            portalLog.log("OrgNoticeErr.log", "[" + this.THIS_CLASS_NAME + "]" + ex.toString());
        }
        return itemViewSel;
    }
    
    public int noticeAdd(final OrgNoticeInfo orgNoticeInfo, final Vector vector) {
        int itemAdd = 0;
        try {
            itemAdd = this.org.itemAdd(orgNoticeInfo, vector);
        }
        catch (Exception ex) {
            portalLog.log("OrgNoticeErr.log", "[" + this.THIS_CLASS_NAME + "]" + ex.toString());
        }
        return itemAdd;
    }
    
    public int noticeEdit(final OrgNoticeInfo orgNoticeInfo, final Vector vector) {
        int itemEdit = 0;
        try {
            itemEdit = this.org.itemEdit(orgNoticeInfo, vector);
        }
        catch (Exception ex) {
            portalLog.log("OrgNoticeErr.log", "[" + this.THIS_CLASS_NAME + "]" + ex.toString());
        }
        return itemEdit;
    }
    
    public int noticeDel(final int n, final String s) {
        int itemDel = 0;
        try {
            itemDel = this.org.itemDel(n, s);
        }
        catch (Exception ex) {
            portalLog.log("OrgNoticeErr.log", "[" + this.THIS_CLASS_NAME + "]" + ex.toString());
        }
        return itemDel;
    }
    
    public int noticeDelSel(final String s, final String s2) {
        int itemDelSel = 0;
        try {
            itemDelSel = this.org.itemDelSel(s, s2);
        }
        catch (Exception ex) {
            portalLog.log("OrgNoticeErr.log", "[" + this.THIS_CLASS_NAME + "]" + ex.toString());
        }
        return itemDelSel;
    }
    
    public Vector noticeRecent(final int n, final int n2) {
        Vector itemRecent = null;
        try {
            itemRecent = this.org.itemRecent(n, n2);
        }
        catch (Exception ex) {
            portalLog.log("OrgNoticeErr.log", "[" + this.THIS_CLASS_NAME + "]" + ex.toString());
        }
        return itemRecent;
    }
    
    public OrgNoticeFile noticeDown(final int n, final int n2) {
        OrgNoticeFile itemDown = null;
        try {
            itemDown = this.org.itemDown(n, n2);
        }
        catch (Exception ex) {
            portalLog.log("OrgNoticeErr.log", "[" + this.THIS_CLASS_NAME + "]" + ex.toString());
        }
        return itemDown;
    }
    
    public boolean authCheck(final int n, final String s) {
        boolean authCheck = false;
        try {
            authCheck = this.org.authCheck(n, s);
        }
        catch (Exception ex) {
            portalLog.log("OrgNoticeErr.log", "[" + this.THIS_CLASS_NAME + "]" + ex.toString());
        }
        return authCheck;
    }
}
