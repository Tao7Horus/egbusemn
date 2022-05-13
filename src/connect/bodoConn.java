// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.information.bodofile.datamodel.bodoFile;
import java.util.Vector;
import portal.information.bodofile.datamodel.bodoView;
import portal.information.bodofile.datamodel.bodoList;
import common.Log;
import javax.naming.InitialContext;
import portal.information.bodofile.session.Bodo;
import portal.information.bodofile.session.BodoListHome;

public class bodoConn
{
    private BodoListHome bodoListHome;
    private Bodo bodo;
    
    public bodoConn() {
        this.bodoListHome = null;
        this.bodo = null;
        try {
            this.bodoListHome = (BodoListHome)new InitialContext().lookup("Bodo");
            this.bodo = this.bodoListHome.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public bodoList ibodoList(final String s, final int n, final int n2) {
        bodoList itemList = null;
        try {
            itemList = this.bodo.itemList(s, n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemList;
    }
    
    public bodoList storeListSearch(final String s, final int n, final int n2, final String s2, final String s3) {
        bodoList itemListSearch = null;
        try {
            itemListSearch = this.bodo.itemListSearch(s, n, n2, s2, s3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemListSearch;
    }
    
    public bodoView bodoitemView(final String s, final int n, final boolean b, final String s2) {
        bodoView itemView = null;
        try {
            itemView = this.bodo.itemView(s, n, b, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemView;
    }
    
    public int bodoAdd(final String s, final String s2, final String s3, final String s4, final String s5, final Vector vector) {
        int itemAdd = 0;
        try {
            itemAdd = this.bodo.itemAdd(s, s2, s3, s4, s5, vector);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemAdd;
    }
    
    public bodoView bodoGet(final String s, final int n) {
        bodoView itemGet = null;
        try {
            itemGet = this.bodo.itemGet(s, n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemGet;
    }
    
    public int bodoEdit(final String s, final int n, final String s2, final String s3, final Vector vector) {
        int itemEdit = 0;
        try {
            itemEdit = this.bodo.itemEdit(s, n, s2, s3, vector);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemEdit;
    }
    
    public Vector bodoViewSel(final String s, final String s2) {
        Vector itemViewSel = null;
        try {
            itemViewSel = this.bodo.itemViewSel(s, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemViewSel;
    }
    
    public int bodoDel(final String s, final int n, final String s2) {
        int itemDel = 0;
        try {
            itemDel = this.bodo.itemDel(s, n, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDel;
    }
    
    public int bodoDelSel(final String s, final String s2, final String s3) {
        int itemDelSel = 0;
        try {
            itemDelSel = this.bodo.itemDelSel(s, s2, s3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDelSel;
    }
    
    public bodoFile bodoDown(final String s, final int n, final int n2) {
        bodoFile itemDown = null;
        try {
            itemDown = this.bodo.itemDown(s, n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDown;
    }
}
