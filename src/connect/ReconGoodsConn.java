// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import java.util.Vector;
import common.Log;
import javax.naming.InitialContext;
import portal.mypage.recongoods.session.ReconGoods;
import portal.mypage.recongoods.session.ReconGoodsHome;

public class ReconGoodsConn
{
    private ReconGoodsHome recongoodshome;
    private ReconGoods recongoods;
    
    public ReconGoodsConn() {
        this.recongoodshome = null;
        this.recongoods = null;
        try {
            this.recongoodshome = (ReconGoodsHome)new InitialContext().lookup("ReconGoods");
            this.recongoods = this.recongoodshome.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public Vector getReconGoodsList(final String s, final int n) {
        Vector reconGoodsList = new Vector();
        try {
            reconGoodsList = this.recongoods.getReconGoodsList(s, n);
        }
        catch (Exception ex) {
            System.out.println("getReconGoodsList_servlet : " + ex);
        }
        return reconGoodsList;
    }
    
    public Vector getSaveGoodsList(final String s, final int n) {
        Vector saveGoodsList = new Vector();
        try {
            saveGoodsList = this.recongoods.getSaveGoodsList(s, n);
        }
        catch (Exception ex) {
            System.out.println("getSaveGoodsList_servlet : " + ex);
        }
        return saveGoodsList;
    }
    
    public Vector getReconGoodsList2(final String s, final int n, final String s2) {
        Vector reconGoodsList2 = new Vector();
        try {
            reconGoodsList2 = this.recongoods.getReconGoodsList2(s, n, s2);
        }
        catch (Exception ex) {
            System.out.println("getReconGoodsList2_servlet : " + ex);
        }
        return reconGoodsList2;
    }
    
    public Vector getSaveGoodsList2(final String s, final int n, final String s2) {
        Vector saveGoodsList2 = new Vector();
        try {
            saveGoodsList2 = this.recongoods.getSaveGoodsList2(s, n, s2);
        }
        catch (Exception ex) {
            System.out.println("getSaveGoodsList2_servlet : " + ex);
        }
        return saveGoodsList2;
    }
    
    public Vector getGoodsListMain(final String s) {
        Vector goodsListMain = new Vector();
        try {
            goodsListMain = this.recongoods.getGoodsListMain(s);
        }
        catch (Exception ex) {
            System.out.println("getGoodsListMain_servlet : " + ex);
        }
        return goodsListMain;
    }
    
    public int getGoodsExist(final String s, final String s2, final String s3) {
        int goodsExist = 0;
        try {
            goodsExist = this.recongoods.getGoodsExist(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println("getGoodsExist_servlet : " + ex);
        }
        return goodsExist;
    }
    
    public int insertGoods(final String s, final String s2, final String s3, final int n, final String s4, final String s5) {
        int insertGoods = 0;
        try {
            insertGoods = this.recongoods.insertGoods(s, s2, s3, n, s4, s5);
        }
        catch (Exception ex) {
            System.out.println("insertGoods_servlet : " + ex);
        }
        return insertGoods;
    }
    
    public int updateGoods(final String s, final String s2, final String s3, final int n) {
        int updateGoods = 0;
        try {
            updateGoods = this.recongoods.updateGoods(s, s2, s3, n);
        }
        catch (Exception ex) {
            System.out.println("updateGoods_servlet : " + ex);
        }
        return updateGoods;
    }
}
