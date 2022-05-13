// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.mypage.businessset.datamodel.pubInfoList;
import portal.mypage.businessset.datamodel.businList;
import portal.log.portalLog;
import javax.naming.InitialContext;
import portal.mypage.businessset.session.BusinessSet;
import portal.mypage.businessset.session.BusinessSetHome;

public class BusinConn
{
    private BusinessSetHome busin_Home;
    private BusinessSet busin_remote;
    
    public BusinConn() {
        this.busin_Home = null;
        this.busin_remote = null;
        try {
            this.busin_Home = (BusinessSetHome)new InitialContext().lookup("BusinessSet");
            this.busin_remote = this.busin_Home.create();
        }
        catch (Exception ex) {
            portalLog.log("[BusinConn:BusinConn-Exception]" + ex.toString());
        }
    }
    
    public businList docList(final int n, final int n2, final String s) {
        businList itemList = null;
        try {
            itemList = this.busin_remote.itemList(n, n2, s);
        }
        catch (Exception ex) {
            portalLog.log("[BusinConn:docList-Exception]" + ex.toString());
        }
        return itemList;
    }
    
    public businList payList(final int n, final int n2, final String s) {
        businList payitemList = null;
        try {
            payitemList = this.busin_remote.payitemList(n, n2, s);
        }
        catch (Exception ex) {
            portalLog.log("[BusinConn:payList-Exception]" + ex.toString());
        }
        return payitemList;
    }
    
    public businList bidList(final int n, final int n2, final String s, final String s2, final String s3, final String s4) {
        businList biditemList = null;
        try {
            biditemList = this.busin_remote.biditemList(n, n2, s, s2, s3, s4);
        }
        catch (Exception ex) {
            portalLog.log("[BusinConn:bidList-Exception]" + ex.toString());
        }
        return biditemList;
    }
    
    public businList serbidList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5) {
        businList serbiditemList = null;
        try {
            serbiditemList = this.busin_remote.serbiditemList(n, n2, s, s2, s3, s4, s5);
        }
        catch (Exception ex) {
            portalLog.log("[BusinConn:serbidList-Exception]" + ex.toString());
        }
        return serbiditemList;
    }
    
    public businList pubitemList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final int n3) {
        businList agreeitemList = null;
        try {
            agreeitemList = this.busin_remote.agreeitemList(n, n2, s, s2, s3, s4, s5, n3);
        }
        catch (Exception ex) {
            portalLog.log("[BusinConn:pubitemList-Exception]" + ex.toString());
        }
        return agreeitemList;
    }
    
    public businList comagreeList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final int n3) {
        businList comagreeList = null;
        try {
            comagreeList = this.busin_remote.comagreeList(n, n2, s, s2, s3, s4, s5, n3);
        }
        catch (Exception ex) {
            portalLog.log("[BusinConn:comagreeList-Exception]" + ex.toString());
        }
        return comagreeList;
    }
    
    public pubInfoList getpubInfo(final String s, final int n) {
        pubInfoList getpubInfo = null;
        try {
            getpubInfo = this.busin_remote.getpubInfo(s, n);
        }
        catch (Exception ex) {
            portalLog.log("[BusinConn:getpubInfo-Exception]" + ex.toString());
        }
        return getpubInfo;
    }
    
    public pubInfoList comgetpubInfo(final String s, final String s2, final int n) {
        pubInfoList comgetpubInfo = null;
        try {
            comgetpubInfo = this.busin_remote.comgetpubInfo(s, s2, n);
        }
        catch (Exception ex) {
            portalLog.log("[BusinConn:comgetpubInfo-Exception]" + ex.toString());
        }
        return comgetpubInfo;
    }
    
    public businList bussellList(final int n, final int n2, final String s, final String s2, final String s3, final String s4) {
        businList bussellList = null;
        try {
            bussellList = this.busin_remote.bussellList(n, n2, s, s2, s3, s4);
        }
        catch (Exception ex) {
            portalLog.log("[BusinConn:bussellList-Exception]" + ex.toString());
        }
        return bussellList;
    }
    
    public String doState(final String s, final String s2, final String s3) {
        String doState = "";
        try {
            doState = this.busin_remote.doState(s, s2, s3);
        }
        catch (Exception ex) {
            portalLog.log("[BusinConn:doState-Exception]" + ex.toString());
        }
        return doState;
    }
    
    public businList ibusbidList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5) {
        businList busbidList = null;
        try {
            busbidList = this.busin_remote.busbidList(n, n2, s, s2, s3, s4, s5);
        }
        catch (Exception ex) {
            portalLog.log("[BusinConn:ibusbidList-Exception]" + ex.toString());
        }
        return busbidList;
    }
    
    public businList ipubbidList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5) {
        businList pubbidList = null;
        try {
            pubbidList = this.busin_remote.pubbidList(n, n2, s, s2, s3, s4, s5);
        }
        catch (Exception ex) {
            portalLog.log("[BusinConn:ipubbidList-Exception]" + ex.toString());
        }
        return pubbidList;
    }
}
