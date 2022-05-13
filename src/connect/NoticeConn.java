// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.common.notice.datamodel.NoticeFile;
import portal.common.notice.datamodel.NoticeInfo;
import portal.common.notice.datamodel.NoticeView;
import portal.common.notice.datamodel.NoticeList;
import java.util.Vector;
import common.Log;
import javax.naming.InitialContext;
import portal.common.notice.session.NoticeHome;
import portal.common.notice.session.Notice;

public class NoticeConn
{
    private Notice notice;
    
    public NoticeConn() {
        this.notice = null;
        try {
            this.notice = ((NoticeHome)new InitialContext().lookup("Notice")).create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public Vector noticeDiv() {
        Vector itemDiv = null;
        try {
            itemDiv = this.notice.itemDiv();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDiv;
    }
    
    public Vector noticeDiv(final String s) {
        Vector itemDiv = null;
        try {
            itemDiv = this.notice.itemDiv(s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDiv;
    }
    
    public Vector noticeDiv(final String s, final String s2) {
        Vector itemDiv = null;
        try {
            itemDiv = this.notice.itemDiv(s, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDiv;
    }
    
    public NoticeList noticeList(final String s, final int n, final int n2) {
        NoticeList itemList = null;
        try {
            itemList = this.notice.itemList(s, n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemList;
    }
    
    public NoticeList noticeListPart(final Vector vector, final int n, final int n2) {
        NoticeList itemListPart = null;
        try {
            itemListPart = this.notice.itemListPart(vector, n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemListPart;
    }
    
    public NoticeList noticeList(final String s, final String s2, final String s3, final int n, final int n2, final String s4, final String s5) {
        NoticeList noticeList = null;
        try {
            noticeList = this.notice.noticeList(s, s2, s3, n, n2, s4, s5);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return noticeList;
    }
    
    public NoticeList noticeListSearch(final String s, final int n, final int n2, final String s2, final String s3) {
        NoticeList itemListSearch = null;
        try {
            itemListSearch = this.notice.itemListSearch(s, n, n2, s2, s3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemListSearch;
    }
    
    public NoticeList noticeListSearchPart(final Vector vector, final int n, final int n2, final String s, final String s2) {
        NoticeList itemListSearchPart = null;
        try {
            itemListSearchPart = this.notice.itemListSearchPart(vector, n, n2, s, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemListSearchPart;
    }
    
    public NoticeView noticeGet(final String s, final int n) {
        NoticeView itemGet = null;
        try {
            itemGet = this.notice.itemGet(s, n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemGet;
    }
    
    public NoticeView noticeView(final String s, final int n, final boolean b, final String s2) {
        NoticeView itemView = null;
        try {
            itemView = this.notice.itemView(s, n, b, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemView;
    }
    
    public NoticeView noticeViewPart(final String s, final int n, final Vector vector, final boolean b, final String s2) {
        NoticeView itemViewPart = null;
        try {
            itemViewPart = this.notice.itemViewPart(s, n, vector, b, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemViewPart;
    }
    
    public Vector noticeViewSel(final String s, final String s2) {
        Vector itemViewSel = null;
        try {
            itemViewSel = this.notice.itemViewSel(s, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemViewSel;
    }
    
    public Vector noticeViewSelPart(final Vector vector) {
        Vector itemViewSelPart = null;
        try {
            itemViewSelPart = this.notice.itemViewSelPart(vector);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemViewSelPart;
    }
    
    public int noticeDivEdit(final String s, final String s2) {
        int itemDivEdit = 0;
        try {
            itemDivEdit = this.notice.itemDivEdit(s, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDivEdit;
    }
    
    public int noticeDivAdd(final String s, final String s2) {
        int itemDivAdd = 0;
        try {
            itemDivAdd = this.notice.itemDivAdd(s, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDivAdd;
    }
    
    public boolean noticeDivCheck(final String s, final String s2) {
        boolean itemDivCheck = false;
        try {
            itemDivCheck = this.notice.itemDivCheck(s, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDivCheck;
    }
    
    public int noticeAdd(final NoticeInfo noticeInfo, final Vector vector) {
        int itemAdd = 0;
        try {
            itemAdd = this.notice.itemAdd(noticeInfo, vector);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemAdd;
    }
    
    public int noticeEdit(final NoticeInfo noticeInfo, final Vector vector) {
        int itemEdit = 0;
        try {
            itemEdit = this.notice.itemEdit(noticeInfo, vector);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemEdit;
    }
    
    public int noticeDel(final String s, final int n, final String s2) {
        int itemDel = 0;
        try {
            itemDel = this.notice.itemDel(s, n, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDel;
    }
    
    public int noticeDelSel(final String s, final String s2, final String s3) {
        int itemDelSel = 0;
        try {
            itemDelSel = this.notice.itemDelSel(s, s2, s3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDelSel;
    }
    
    public int noticeDelSelPart(final Vector vector, final String s) {
        int itemDelSelPart = 0;
        try {
            itemDelSelPart = this.notice.itemDelSelPart(vector, s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDelSelPart;
    }
    
    public Vector noticeRecent(final String s, final int n) {
        Vector itemRecent = null;
        try {
            itemRecent = this.notice.itemRecent(s, n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemRecent;
    }
    
    public Vector noticeRecentPart(final int n) {
        Vector itemRecentPart = null;
        try {
            itemRecentPart = this.notice.itemRecentPart(n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemRecentPart;
    }
    
    public NoticeFile noticeDown(final String s, final int n, final int n2) {
        NoticeFile itemDown = null;
        try {
            itemDown = this.notice.itemDown(s, n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDown;
    }
    
    public String noticeTitle(final String s) {
        String itemTitle = null;
        try {
            itemTitle = this.notice.itemTitle(s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemTitle;
    }
    
    public String noticeDivName(final String s) {
        String itemDivName = null;
        try {
            itemDivName = this.notice.itemDivName(s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDivName;
    }
}
