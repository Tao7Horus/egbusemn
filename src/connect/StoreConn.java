// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.common.store.datamodel.StoreFile;
import java.util.Vector;
import portal.common.store.datamodel.StoreView;
import portal.common.store.datamodel.StoreEdit;
import portal.common.store.datamodel.StoreList;
import common.Log;
import javax.naming.InitialContext;
import portal.common.store.session.StoreHome;
import portal.common.store.session.Store;

public class StoreConn
{
    private Store store;
    
    public StoreConn() {
        this.store = null;
        try {
            this.store = ((StoreHome)new InitialContext().lookup("Store")).create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public StoreList storeList(final String s, final int n, final int n2) {
        StoreList itemList = null;
        try {
            itemList = this.store.itemList(s, n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemList;
    }
    
    public StoreList storeListSearch(final String s, final int n, final int n2, final String s2, final String s3) {
        StoreList itemListSearch = null;
        try {
            itemListSearch = this.store.itemListSearch(s, n, n2, s2, s3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemListSearch;
    }
    
    public StoreEdit storeGet(final String s, final int n) {
        StoreEdit itemGet = null;
        try {
            itemGet = this.store.itemGet(s, n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemGet;
    }
    
    public StoreView storeView(final String s, final int n, final boolean b, final String s2) {
        StoreView itemView = null;
        try {
            itemView = this.store.itemView(s, n, b, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemView;
    }
    
    public Vector storeViewSel(final String s, final String s2) {
        Vector itemViewSel = null;
        try {
            itemViewSel = this.store.itemViewSel(s, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemViewSel;
    }
    
    public int storeAdd(final String s, final String s2, final String s3, final String s4, final String s5, final Vector vector) {
        int itemAdd = 0;
        try {
            itemAdd = this.store.itemAdd(s, s2, s3, s4, s5, vector);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemAdd;
    }
    
    public int storeEdit(final String s, final int n, final String s2, final String s3, final Vector vector) {
        int itemEdit = 0;
        try {
            itemEdit = this.store.itemEdit(s, n, s2, s3, vector);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemEdit;
    }
    
    public int storeDel(final String s, final int n, final String s2) {
        int itemDel = 0;
        try {
            itemDel = this.store.itemDel(s, n, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDel;
    }
    
    public int storeDelSel(final String s, final String s2, final String s3) {
        int itemDelSel = 0;
        try {
            itemDelSel = this.store.itemDelSel(s, s2, s3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDelSel;
    }
    
    public StoreFile storeDown(final String s, final int n, final int n2) {
        StoreFile itemDown = null;
        try {
            itemDown = this.store.itemDown(s, n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDown;
    }
    
    public StoreList storeListDiv(final String s, final int n, final int n2) {
        StoreList itemListDiv = null;
        try {
            itemListDiv = this.store.itemListDiv(s, n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemListDiv;
    }
    
    public StoreList storeListSearchDiv(final String s, final int n, final int n2, final String s2, final String s3) {
        StoreList itemListSearchDiv = null;
        try {
            itemListSearchDiv = this.store.itemListSearchDiv(s, n, n2, s2, s3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemListSearchDiv;
    }
    
    public StoreList storeListDivPart(final String s, final int n, final int n2, final String s2) {
        StoreList itemListDivPart = null;
        try {
            itemListDivPart = this.store.itemListDivPart(s, n, n2, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemListDivPart;
    }
    
    public StoreList storeListSearchDivPart(final String s, final int n, final int n2, final String s2, final String s3, final String s4) {
        StoreList itemListSearchDivPart = null;
        try {
            itemListSearchDivPart = this.store.itemListSearchDivPart(s, n, n2, s2, s3, s4);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemListSearchDivPart;
    }
    
    public StoreEdit storeGetDiv(final String s, final int n) {
        StoreEdit itemGetDiv = null;
        try {
            itemGetDiv = this.store.itemGetDiv(s, n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemGetDiv;
    }
    
    public StoreView storeViewDiv(final String s, final int n, final boolean b, final String s2) {
        StoreView itemViewDiv = null;
        try {
            itemViewDiv = this.store.itemViewDiv(s, n, b, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemViewDiv;
    }
    
    public Vector storeViewSelDiv(final String s, final String s2) {
        Vector itemViewSelDiv = null;
        try {
            itemViewSelDiv = this.store.itemViewSelDiv(s, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemViewSelDiv;
    }
    
    public int storeAddDiv(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final Vector vector) {
        int itemAddDiv = 0;
        try {
            itemAddDiv = this.store.itemAddDiv(s, s2, s3, s4, s5, s6, vector);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemAddDiv;
    }
    
    public int storeEditDiv(final String s, final int n, final String s2, final String s3, final String s4, final Vector vector) {
        int itemEditDiv = 0;
        try {
            itemEditDiv = this.store.itemEditDiv(s, n, s2, s3, s4, vector);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemEditDiv;
    }
    
    public String storeTitle(final String s) {
        String itemTitle = null;
        try {
            itemTitle = this.store.itemTitle(s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemTitle;
    }
}
