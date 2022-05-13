// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import java.util.Vector;
import common.Log;
import javax.naming.InitialContext;
import portal.mypage.itemset.session.Itemset;
import portal.mypage.itemset.session.ItemsetHome;

public class ItemsetConn
{
    private ItemsetHome itemsethome;
    private Itemset itemset;
    
    public ItemsetConn() {
        this.itemsethome = null;
        this.itemset = null;
        try {
            this.itemsethome = (ItemsetHome)new InitialContext().lookup("Itemset");
            this.itemset = this.itemsethome.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public Vector getGoodsSelect(final String s, final String s2, final String s3, final String s4) {
        Vector goodsSelect = new Vector();
        try {
            goodsSelect = this.itemset.getGoodsSelect(s, s2, s3, s4);
        }
        catch (Exception ex) {
            System.out.println("myitem : " + ex);
        }
        return goodsSelect;
    }
    
    public Vector getInstSelect(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Vector instSelect = new Vector();
        try {
            instSelect = this.itemset.getInstSelect(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println("myitem : " + ex);
        }
        return instSelect;
    }
    
    public Vector getLicenseSearch(final String s, final String s2, final String s3) {
        Vector licenseSearch = new Vector();
        try {
            licenseSearch = this.itemset.getLicenseSearch(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println("myitem : " + ex);
        }
        return licenseSearch;
    }
    
    public Vector getServSelect(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Vector servSelect = new Vector();
        try {
            servSelect = this.itemset.getServSelect(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println("myitem : " + ex);
        }
        return servSelect;
    }
    
    public Vector getSortSelect(final int n) {
        Vector sortSelect = new Vector();
        try {
            sortSelect = this.itemset.getSortSelect(n);
        }
        catch (Exception ex) {
            System.out.println("myitem : " + ex);
        }
        return sortSelect;
    }
    
    public Vector getSortCate(final int n, final String s, final int n2) {
        Vector sortCate = new Vector();
        try {
            sortCate = this.itemset.getSortCate(n, s, n2);
        }
        catch (Exception ex) {
            System.out.println("myitem : " + ex);
        }
        return sortCate;
    }
    
    public Vector getSortCateDetail(final int n, final String s, final int n2, final String s2) {
        Vector sortCateDetail = new Vector();
        try {
            sortCateDetail = this.itemset.getSortCateDetail(n, s, n2, s2);
        }
        catch (Exception ex) {
            System.out.println("myitem : " + ex);
        }
        return sortCateDetail;
    }
    
    public Vector getGoodsSearch(final int n, final int n2, final String s, final String s2) {
        Vector goodsSearch = new Vector();
        try {
            goodsSearch = this.itemset.getGoodsSearch(n, n2, s, s2);
        }
        catch (Exception ex) {
            System.out.println("myitem : " + ex);
        }
        return goodsSearch;
    }
    
    public int getItemMaxCode(final String s, final String s2) {
        int itemMaxCode = 0;
        try {
            itemMaxCode = this.itemset.getItemMaxCode(s, s2);
        }
        catch (Exception ex) {
            System.out.println("myitem : " + ex);
        }
        return itemMaxCode;
    }
    
    public int insertMyitem(final String s, final String s2, final int n, final String s3, final String s4) {
        int insertMyitem = 0;
        try {
            insertMyitem = this.itemset.insertMyitem(s, s2, n, s3, s4);
        }
        catch (Exception ex) {
            System.out.println("myitem : " + ex);
        }
        return insertMyitem;
    }
    
    public Vector getItemsetSelect(final String s, final String s2) {
        Vector itemsetSelect = new Vector();
        try {
            itemsetSelect = this.itemset.getItemsetSelect(s, s2);
        }
        catch (Exception ex) {
            System.out.println("myitem : " + ex);
        }
        return itemsetSelect;
    }
    
    public int deleteItemset(final String s, final String s2, final String s3) {
        int deleteItemset = 0;
        try {
            deleteItemset = this.itemset.deleteItemset(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println("myitem : " + ex);
        }
        return deleteItemset;
    }
    
    public int deleteItemset(final String s, final String s2) {
        int deleteItemset = 0;
        try {
            deleteItemset = this.itemset.deleteItemset(s, s2);
        }
        catch (Exception ex) {
            System.out.println("myitem : " + ex);
        }
        return deleteItemset;
    }
    
    public int NoLicence(final String s, final String s2) {
        int noLicence = 0;
        try {
            noLicence = this.itemset.NoLicence(s, s2);
        }
        catch (Exception ex) {
            System.out.println("myitem : " + ex);
        }
        return noLicence;
    }
    
    public int updateMyarea(final String s, final String s2, final String s3, final String s4, final String s5) {
        int updateMyarea = 0;
        try {
            updateMyarea = this.itemset.updateMyarea(s, s2, s3, s4, s5);
        }
        catch (Exception ex) {
            System.out.println("myitem : " + ex);
        }
        return updateMyarea;
    }
}
