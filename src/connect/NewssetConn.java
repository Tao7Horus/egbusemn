// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import java.util.Vector;
import common.Log;
import javax.naming.InitialContext;
import portal.mypage.newsset.session.Newsset;
import portal.mypage.newsset.session.NewssetHome;

public class NewssetConn
{
    private NewssetHome newssethome;
    private Newsset newsset;
    
    public NewssetConn() {
        this.newssethome = null;
        this.newsset = null;
        try {
            this.newssethome = (NewssetHome)new InitialContext().lookup("Newsset");
            this.newsset = this.newssethome.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public Vector getUserSelect(final String s) {
        Vector userSelect = new Vector();
        try {
            userSelect = this.newsset.getUserSelect(s);
        }
        catch (Exception ex) {
            System.out.println("newsletter : " + ex);
        }
        return userSelect;
    }
    
    public Vector getUserItem(final String s) {
        Vector userItem = new Vector();
        try {
            userItem = this.newsset.getUserItem(s);
        }
        catch (Exception ex) {
            System.out.println("newsletter : " + ex);
        }
        return userItem;
    }
    
    public Vector getGoodsBidSelect(final String s) {
        Vector goodsBidSelect = new Vector();
        try {
            goodsBidSelect = this.newsset.getGoodsBidSelect(s);
        }
        catch (Exception ex) {
            System.out.println("newsletter : " + ex);
        }
        return goodsBidSelect;
    }
    
    public Vector getGoodsAmountSelect(final String s) {
        Vector goodsAmountSelect = new Vector();
        try {
            goodsAmountSelect = this.newsset.getGoodsAmountSelect(s);
        }
        catch (Exception ex) {
            System.out.println("newsletter : " + ex);
        }
        return goodsAmountSelect;
    }
    
    public Vector getInstBidSelect(final String s) {
        Vector instBidSelect = new Vector();
        try {
            instBidSelect = this.newsset.getInstBidSelect(s);
        }
        catch (Exception ex) {
            System.out.println("newsletter : " + ex);
        }
        return instBidSelect;
    }
    
    public Vector getInstPQSelect(final String s) {
        Vector instPQSelect = new Vector();
        try {
            instPQSelect = this.newsset.getInstPQSelect(s);
        }
        catch (Exception ex) {
            System.out.println("newsletter : " + ex);
        }
        return instPQSelect;
    }
    
    public Vector getInstAmountSelect(final String s) {
        Vector instAmountSelect = new Vector();
        try {
            instAmountSelect = this.newsset.getInstAmountSelect(s);
        }
        catch (Exception ex) {
            System.out.println("newsletter : " + ex);
        }
        return instAmountSelect;
    }
    
    public Vector getInstPreSelect(final String s) {
        Vector instPreSelect = new Vector();
        try {
            instPreSelect = this.newsset.getInstPreSelect(s);
        }
        catch (Exception ex) {
            System.out.println("newsletter : " + ex);
        }
        return instPreSelect;
    }
    
    public Vector getYongBidSelect(final String s) {
        Vector yongBidSelect = new Vector();
        try {
            yongBidSelect = this.newsset.getYongBidSelect(s);
        }
        catch (Exception ex) {
            System.out.println("newsletter : " + ex);
        }
        return yongBidSelect;
    }
    
    public Vector getYongPQSelect(final String s) {
        Vector yongPQSelect = new Vector();
        try {
            yongPQSelect = this.newsset.getYongPQSelect(s);
        }
        catch (Exception ex) {
            System.out.println("newsletter : " + ex);
        }
        return yongPQSelect;
    }
    
    public Vector getYongAmountSelect(final String s) {
        Vector yongAmountSelect = new Vector();
        try {
            yongAmountSelect = this.newsset.getYongAmountSelect(s);
        }
        catch (Exception ex) {
            System.out.println("newsletter : " + ex);
        }
        return yongAmountSelect;
    }
    
    public Vector getYongPreSelect(final String s) {
        Vector yongPreSelect = new Vector();
        try {
            yongPreSelect = this.newsset.getYongPreSelect(s);
        }
        catch (Exception ex) {
            System.out.println("newsletter : " + ex);
        }
        return yongPreSelect;
    }
    
    public Vector getGoodsBid2Select(final String s) {
        Vector goodsBid2Select = new Vector();
        try {
            goodsBid2Select = this.newsset.getGoodsBid2Select(s);
        }
        catch (Exception ex) {
            System.out.println("newsletter : " + ex);
        }
        return goodsBid2Select;
    }
}
