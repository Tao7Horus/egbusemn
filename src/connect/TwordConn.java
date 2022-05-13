// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.info.tword.datamodel.TwordInfo;
import portal.info.tword.datamodel.TwordList;
import common.Log;
import javax.naming.InitialContext;
import portal.info.tword.session.TwordHome;
import portal.info.tword.session.Tword;

public class TwordConn
{
    private Tword tword_remote;
    private TwordHome tword_Home;
    
    public TwordConn() {
        this.tword_remote = null;
        this.tword_Home = null;
        try {
            this.tword_Home = (TwordHome)new InitialContext().lookup("Tword");
            this.tword_remote = this.tword_Home.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public TwordList twordList(final int n, final int n2) {
        TwordList itemList = null;
        try {
            itemList = this.tword_remote.itemList(n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemList;
    }
    
    public TwordList twordListSearch(final int n, final int n2, final String s, final String s2, final String s3) {
        TwordList itemListSearch = null;
        try {
            itemListSearch = this.tword_remote.itemListSearch(n, n2, s, s2, s3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemListSearch;
    }
    
    public TwordList twordListSearch2(final int n, final int n2, final String s, final int n3) {
        TwordList itemkeySearch = null;
        try {
            itemkeySearch = this.tword_remote.itemkeySearch(n, n2, s, n3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemkeySearch;
    }
    
    public int TwordAdd(final String s, final String s2) {
        int itemAdd = 0;
        try {
            itemAdd = this.tword_remote.itemAdd(s, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemAdd;
    }
    
    public TwordInfo twordGet(final int n) {
        TwordInfo twordInfo = null;
        try {
            twordInfo = this.tword_remote.getTwordInfo(n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return twordInfo;
    }
    
    public int twordEdit(final int n, final String s, final String s2) {
        int itemEdit = 0;
        try {
            itemEdit = this.tword_remote.itemEdit(n, s, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemEdit;
    }
    
    public int twordDel(final int n) {
        int itemDel = 0;
        try {
            itemDel = this.tword_remote.itemDel(n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDel;
    }
    
    public TwordList testList(final int n, final int n2) {
        TwordList testitemList = null;
        try {
            testitemList = this.tword_remote.testitemList(n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return testitemList;
    }
}
