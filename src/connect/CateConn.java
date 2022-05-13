// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.community.category.datamodel.CateInfo;
import portal.community.category.datamodel.CateList;
import common.Log;
import javax.naming.InitialContext;
import portal.community.category.session.Category;
import portal.community.category.session.CateHome;

public class CateConn
{
    private CateHome cate_Home;
    private Category cate_remote;
    
    public CateConn() {
        this.cate_Home = null;
        this.cate_remote = null;
        try {
            this.cate_Home = (CateHome)new InitialContext().lookup("Category");
            this.cate_remote = this.cate_Home.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public CateList cateList() {
        CateList clubList = null;
        try {
            clubList = this.cate_remote.clubList();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return clubList;
    }
    
    public int CateAdd(final String s) {
        int itemAdd = 0;
        try {
            itemAdd = this.cate_remote.itemAdd(s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemAdd;
    }
    
    public CateInfo cateGet(final int n) {
        CateInfo cateInfo = null;
        try {
            cateInfo = this.cate_remote.getCateInfo(n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return cateInfo;
    }
    
    public int cateEdit(final int n, final String s) {
        int itemEdit = 0;
        try {
            itemEdit = this.cate_remote.itemEdit(n, s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemEdit;
    }
}
