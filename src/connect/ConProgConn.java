// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.mypage.conprog.datamodel.ConProgInfo;
import portal.log.portalLog;
import javax.naming.InitialContext;
import portal.mypage.conprog.session.ConProg;
import portal.mypage.conprog.session.ConProgHome;

public class ConProgConn
{
    private ConProgHome cpHome;
    private ConProg cpRemote;
    protected final boolean IS_LOG = false;
    protected final String LOGFILE = "ConProg.log";
    protected final String ERRFILE = "ConProgErr.log";
    protected String THIS_CLASS_NAME;
    
    public ConProgConn() {
        this.cpHome = null;
        this.cpRemote = null;
        this.THIS_CLASS_NAME = "ConProgConn";
        try {
            this.cpHome = (ConProgHome)new InitialContext().lookup("ConProg");
            this.cpRemote = this.cpHome.create();
        }
        catch (Exception ex) {
            portalLog.log("ConProg.log", "[" + this.THIS_CLASS_NAME + ":ConProgConn-Exception]" + ex.toString());
        }
    }
    
    public ConProgInfo getConProgComItemList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5) {
        ConProgInfo conProgComItemList = null;
        try {
            conProgComItemList = this.cpRemote.getConProgComItemList(n, n2, s, s2, s3, s4, s5);
            return conProgComItemList;
        }
        catch (Exception ex) {
            portalLog.log("ConProg.log", "[" + this.THIS_CLASS_NAME + ":getConProgComItemList] Exception\n" + ex.toString());
        }
        finally {
            return conProgComItemList;
        }
    }
    
    public ConProgInfo getConProgComSislList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5) {
        ConProgInfo conProgComSislList = null;
        try {
            conProgComSislList = this.cpRemote.getConProgComSislList(n, n2, s, s2, s3, s4, s5);
            return conProgComSislList;
        }
        catch (Exception ex) {
            portalLog.log("ConProg.log", "[" + this.THIS_CLASS_NAME + ":getConProgComSislList] Exception\n" + ex.toString());
        }
        finally {
            return conProgComSislList;
        }
    }
    
    public ConProgInfo getConProgComYongList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5) {
        ConProgInfo conProgComYongList = null;
        try {
            conProgComYongList = this.cpRemote.getConProgComYongList(n, n2, s, s2, s3, s4, s5);
            return conProgComYongList;
        }
        catch (Exception ex) {
            portalLog.log("ConProg.log", "[" + this.THIS_CLASS_NAME + ":getConProgComYongList] Exception\n" + ex.toString());
        }
        finally {
            return conProgComYongList;
        }
    }
    
    public ConProgInfo getConProgComItemDtl(final String s, final String s2, final String s3) {
        ConProgInfo conProgComItemDtl = null;
        try {
            conProgComItemDtl = this.cpRemote.getConProgComItemDtl(s, s2, s3);
            return conProgComItemDtl;
        }
        catch (Exception ex) {
            portalLog.log("ConProg.log", "[" + this.THIS_CLASS_NAME + ":getConProgComItemDtl] Exception\n" + ex.toString());
        }
        finally {
            return conProgComItemDtl;
        }
    }
    
    public ConProgInfo getConProgComSislDtl(final String s, final String s2, final String s3) {
        ConProgInfo conProgComSislDtl = null;
        try {
            conProgComSislDtl = this.cpRemote.getConProgComSislDtl(s, s2, s3);
            return conProgComSislDtl;
        }
        catch (Exception ex) {
            portalLog.log("ConProg.log", "[" + this.THIS_CLASS_NAME + ":getConProgComSislDtl] Exception\n" + ex.toString());
        }
        finally {
            return conProgComSislDtl;
        }
    }
    
    public ConProgInfo getConProgComYongDtl(final String s, final String s2, final String s3) {
        ConProgInfo conProgComYongDtl = null;
        try {
            conProgComYongDtl = this.cpRemote.getConProgComYongDtl(s, s2, s3);
            return conProgComYongDtl;
        }
        catch (Exception ex) {
            portalLog.log("ConProg.log", "[" + this.THIS_CLASS_NAME + ":getConProgComYongDtl] Exception\n" + ex.toString());
        }
        finally {
            return conProgComYongDtl;
        }
    }
    
    public ConProgInfo getConProgOrgItemList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5) {
        ConProgInfo conProgOrgItemList = null;
        try {
            conProgOrgItemList = this.cpRemote.getConProgOrgItemList(n, n2, s, s2, s3, s4, s5);
            return conProgOrgItemList;
        }
        catch (Exception ex) {
            portalLog.log("ConProg.log", "[" + this.THIS_CLASS_NAME + ":getConProgOrgItemList] Exception\n" + ex.toString());
        }
        finally {
            return conProgOrgItemList;
        }
    }
    
    public ConProgInfo getConProgOrgSislList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5) {
        ConProgInfo conProgOrgSislList = null;
        try {
            conProgOrgSislList = this.cpRemote.getConProgOrgSislList(n, n2, s, s2, s3, s4, s5);
            return conProgOrgSislList;
        }
        catch (Exception ex) {
            portalLog.log("ConProg.log", "[" + this.THIS_CLASS_NAME + ":getConProgOrgSislList] Exception\n" + ex.toString());
        }
        finally {
            return conProgOrgSislList;
        }
    }
    
    public ConProgInfo getConProgOrgYongList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5) {
        ConProgInfo conProgOrgYongList = null;
        try {
            conProgOrgYongList = this.cpRemote.getConProgOrgYongList(n, n2, s, s2, s3, s4, s5);
            return conProgOrgYongList;
        }
        catch (Exception ex) {
            portalLog.log("ConProg.log", "[" + this.THIS_CLASS_NAME + ":getConProgOrgYongList] Exception\n" + ex.toString());
        }
        finally {
            return conProgOrgYongList;
        }
    }
    
    public ConProgInfo getConProgOrgItemDtl(final String s, final String s2) {
        ConProgInfo conProgOrgItemDtl = null;
        try {
            conProgOrgItemDtl = this.cpRemote.getConProgOrgItemDtl(s, s2);
            return conProgOrgItemDtl;
        }
        catch (Exception ex) {
            portalLog.log("ConProg.log", "[" + this.THIS_CLASS_NAME + ":getConProgOrgItemDtl] Exception\n" + ex.toString());
        }
        finally {
            return conProgOrgItemDtl;
        }
    }
    
    public ConProgInfo getConProgOrgSislDtl(final String s, final String s2) {
        ConProgInfo conProgOrgSislDtl = null;
        try {
            conProgOrgSislDtl = this.cpRemote.getConProgOrgSislDtl(s, s2);
            return conProgOrgSislDtl;
        }
        catch (Exception ex) {
            portalLog.log("ConProg.log", "[" + this.THIS_CLASS_NAME + ":getConProgOrgSislDtl] Exception\n" + ex.toString());
        }
        finally {
            return conProgOrgSislDtl;
        }
    }
    
    public ConProgInfo getConProgOrgYongDtl(final String s, final String s2) {
        ConProgInfo conProgOrgYongDtl = null;
        try {
            conProgOrgYongDtl = this.cpRemote.getConProgOrgYongDtl(s, s2);
            return conProgOrgYongDtl;
        }
        catch (Exception ex) {
            portalLog.log("ConProg.log", "[" + this.THIS_CLASS_NAME + ":getConProgOrgYongDtl] Exception\n" + ex.toString());
        }
        finally {
            return conProgOrgYongDtl;
        }
    }
}
