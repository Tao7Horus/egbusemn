// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.customer.listPds.datamodel.ListPdsFile;
import portal.customer.listPds.datamodel.ListPdsInfo;
import java.util.Vector;
import portal.customer.listPds.datamodel.ListPdsView;
import portal.customer.listPds.datamodel.ListPdsList;
import common.Log;
import javax.naming.InitialContext;
import portal.customer.listPds.session.ListPdsHome;
import portal.customer.listPds.session.ListPds;

public class ListPdsConn
{
    private ListPds ListPds_remote;
    
    public ListPdsConn() {
        this.ListPds_remote = null;
        try {
            this.ListPds_remote = ((ListPdsHome)new InitialContext().lookup("ListPds")).create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public ListPdsList iListPdsList(final String s, final int n, final int n2) {
        ListPdsList itemList = null;
        try {
            itemList = this.ListPds_remote.itemList(s, n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemList;
    }
    
    public ListPdsList storeListSearch(final String s, final int n, final int n2, final String s2, final String s3) {
        ListPdsList itemListSearch = null;
        try {
            itemListSearch = this.ListPds_remote.itemListSearch(s, n, n2, s2, s3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemListSearch;
    }
    
    public ListPdsView ListPdsitemView(final String s, final int n, final boolean b, final String s2) {
        ListPdsView itemView = null;
        try {
            itemView = this.ListPds_remote.itemView(s, n, b, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemView;
    }
    
    public int ListPdsAdd(final String s, final String s2, final String s3, final String s4, final String s5, final Vector vector) {
        int itemAdd = 0;
        try {
            itemAdd = this.ListPds_remote.itemAdd(s, s2, s3, s4, s5, vector);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemAdd;
    }
    
    public ListPdsInfo ListPdsGet(final String s, final int n) {
        ListPdsInfo itemGet = null;
        try {
            itemGet = this.ListPds_remote.itemGet(s, n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemGet;
    }
    
    public int ListPdsEdit(final String s, final int n, final String s2, final String s3, final Vector vector) {
        int itemEdit = 0;
        try {
            itemEdit = this.ListPds_remote.itemEdit(s, n, s2, s3, vector);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemEdit;
    }
    
    public Vector ListPdsViewSel(final String s, final String s2) {
        Vector itemViewSel = null;
        try {
            itemViewSel = this.ListPds_remote.itemViewSel(s, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemViewSel;
    }
    
    public int ListPdsDel(final String s, final int n, final String s2) {
        int itemDel = 0;
        try {
            itemDel = this.ListPds_remote.itemDel(s, n, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDel;
    }
    
    public int ListPdsDelSel(final String s, final String s2, final String s3) {
        int itemDelSel = 0;
        try {
            itemDelSel = this.ListPds_remote.itemDelSel(s, s2, s3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDelSel;
    }
    
    public ListPdsFile ListPdsDown(final String s, final int n, final int n2) {
        ListPdsFile itemDown = null;
        try {
            itemDown = this.ListPds_remote.itemDown(s, n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDown;
    }
    
    public Vector iproItemlist(final String s) {
        Vector proItemlist = null;
        try {
            proItemlist = this.ListPds_remote.proItemlist(s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return proItemlist;
    }
}
