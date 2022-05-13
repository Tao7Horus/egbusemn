// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.mypage.collectset.datamodel.CollectsetInfo;
import java.util.Vector;
import common.Log;
import javax.naming.InitialContext;
import portal.mypage.collectset.session.Collectset;
import portal.mypage.collectset.session.CollectsetHome;

public class CollectsetConn
{
    private CollectsetHome collectsethome;
    private Collectset collectset;
    
    public CollectsetConn() {
        this.collectsethome = null;
        this.collectset = null;
        try {
            this.collectsethome = (CollectsetHome)new InitialContext().lookup("Collectset");
            this.collectset = this.collectsethome.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public int getCollectMaxCode(final String s, final String s2) {
        int collectMaxCode = 0;
        try {
            collectMaxCode = this.collectset.getCollectMaxCode(s, s2);
        }
        catch (Exception ex) {
            System.out.println("mycollect : " + ex);
        }
        return collectMaxCode;
    }
    
    public int insertMycollect(final String s, final String s2, final int n, final String s3, final int n2, final int n3) {
        int insertMycollect = 0;
        try {
            insertMycollect = this.collectset.insertMycollect(s, s2, n, s3, n2, n3);
        }
        catch (Exception ex) {
            System.out.println("mycollect : " + ex);
        }
        return insertMycollect;
    }
    
    public Vector getCollectsetSelect(final String s, final String s2, final String s3) {
        Vector collectsetSelect = new Vector();
        try {
            collectsetSelect = this.collectset.getCollectsetSelect(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println("mycollect : " + ex);
        }
        return collectsetSelect;
    }
    
    public CollectsetInfo getCollectsetRefer(final String s, final int n, final String s2, final int n2) {
        CollectsetInfo collectsetRefer = new CollectsetInfo();
        try {
            collectsetRefer = this.collectset.getCollectsetRefer(s, n, s2, n2);
        }
        catch (Exception ex) {
            System.out.println("mycollect : " + ex);
        }
        return collectsetRefer;
    }
    
    public Vector getCollectsetFile(final String s, final String s2, final int n) {
        Vector collectsetFile = new Vector();
        try {
            collectsetFile = this.collectset.getCollectsetFile(s, s2, n);
        }
        catch (Exception ex) {
            System.out.println("mycollect : " + ex);
        }
        return collectsetFile;
    }
    
    public int deleteCollectset(final String s, final String s2, final int n) {
        int deleteCollectset = 0;
        try {
            deleteCollectset = this.collectset.deleteCollectset(s, s2, n);
        }
        catch (Exception ex) {
            System.out.println("mycollect : " + ex);
        }
        return deleteCollectset;
    }
    
    public Vector getBulletinsetSelect(final String s, final String s2, final String s3) {
        Vector bulletinsetSelect = new Vector();
        try {
            bulletinsetSelect = this.collectset.getBulletinsetSelect(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println("mycollect : " + ex);
        }
        return bulletinsetSelect;
    }
    
    public Vector getNoticeSearchList(final String s, final String s2, final String s3, final String s4) {
        Vector noticeSearchList = new Vector();
        try {
            noticeSearchList = this.collectset.getNoticeSearchList(s, s2, s3, s4);
        }
        catch (Exception ex) {
            System.out.println("mycollect : " + ex);
        }
        return noticeSearchList;
    }
    
    public Vector getNoticeMainList(final String s) {
        Vector noticeMainList = new Vector();
        try {
            noticeMainList = this.collectset.getNoticeMainList(s);
        }
        catch (Exception ex) {
            System.out.println("mycollect : " + ex);
        }
        return noticeMainList;
    }
    
    public Vector getNoticeBeforeList(final String s) {
        Vector noticeBeforeList = new Vector();
        try {
            noticeBeforeList = this.collectset.getNoticeBeforeList(s);
        }
        catch (Exception ex) {
            System.out.println("mycollect : " + ex);
        }
        return noticeBeforeList;
    }
}
