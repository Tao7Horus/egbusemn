// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.mypage.pageset.datamodel.PagesetInfo;
import java.util.Vector;
import common.Log;
import javax.naming.InitialContext;
import portal.mypage.pageset.session.Pageset;
import portal.mypage.pageset.session.PagesetHome;

public class PagesetConn
{
    private PagesetHome pagesethome;
    private Pageset pageset;
    
    public PagesetConn() {
        this.pagesethome = null;
        this.pageset = null;
        try {
            this.pagesethome = (PagesetHome)new InitialContext().lookup("Pageset");
            this.pageset = this.pagesethome.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public Vector getPagesetList(final String s) {
        Vector pagesetList = new Vector();
        try {
            pagesetList = this.pageset.getPagesetList(s);
        }
        catch (Exception ex) {
            System.out.println("getPagesetList_servlet : " + ex);
        }
        return pagesetList;
    }
    
    public Vector getMypageSelect(final String s, final String s2) {
        Vector mypageSelect = new Vector();
        try {
            mypageSelect = this.pageset.getMypageSelect(s, s2);
        }
        catch (Exception ex) {
            System.out.println("getMypageSelect_servlet : " + ex);
        }
        return mypageSelect;
    }
    
    public String getMypageUser(final String s) {
        String mypageUser = "";
        try {
            mypageUser = this.pageset.getMypageUser(s);
        }
        catch (Exception ex) {
            System.out.println("getMypageUser_servlet : " + ex);
        }
        return mypageUser;
    }
    
    public int insertMypage(final String s, final String s2) {
        int insertMypage = 0;
        try {
            insertMypage = this.pageset.insertMypage(s, s2);
        }
        catch (Exception ex) {
            System.out.println("insertMypage_servlet : " + ex);
        }
        return insertMypage;
    }
    
    public Vector getPagesetSelect(final String s, final String s2) {
        Vector pagesetSelect = new Vector();
        try {
            pagesetSelect = this.pageset.getPagesetSelect(s, s2);
        }
        catch (Exception ex) {
            System.out.println("getPagesetSelect_servlet : " + ex);
        }
        return pagesetSelect;
    }
    
    public Vector getMysetSelect(final String s, final String s2) {
        Vector mysetSelect = new Vector();
        try {
            mysetSelect = this.pageset.getMysetSelect(s, s2);
        }
        catch (Exception ex) {
            System.out.println("getMysetSelect_servlet : " + ex);
        }
        return mysetSelect;
    }
    
    public int getMyPageExist(final String s) {
        int myPageExist = 0;
        try {
            myPageExist = this.pageset.getMyPageExist(s);
        }
        catch (Exception ex) {
            System.out.println("getMyPageExist_servlet : " + ex);
        }
        return myPageExist;
    }
    
    public int insertPageset(final String s, final String s2) {
        int insertPageset = 0;
        try {
            insertPageset = this.pageset.insertPageset(s, s2);
        }
        catch (Exception ex) {
            System.out.println("insertPageset_servlet : " + ex);
        }
        return insertPageset;
    }
    
    public int deletePageset(final String s, final String s2) {
        int deletePageset = 0;
        try {
            deletePageset = this.pageset.deletePageset(s, s2);
        }
        catch (Exception ex) {
            System.out.println("insertPageset_servlet : " + ex);
        }
        return deletePageset;
    }
    
    public Vector getNoticeList(final String s) {
        Vector noticeList = new Vector();
        try {
            noticeList = this.pageset.getNoticeList(s);
        }
        catch (Exception ex) {
            System.out.println("getNoticeList_servlet : " + ex);
        }
        return noticeList;
    }
    
    public Vector getMyNoticeSelect(final String s, final String s2) {
        Vector myNoticeSelect = new Vector();
        try {
            myNoticeSelect = this.pageset.getMyNoticeSelect(s, s2);
        }
        catch (Exception ex) {
            System.out.println("getMyNoticeSelect_servlet : " + ex);
        }
        return myNoticeSelect;
    }
    
    public int getMyNoticeExist(final String s) {
        int myNoticeExist = 0;
        try {
            myNoticeExist = this.pageset.getMyNoticeExist(s);
        }
        catch (Exception ex) {
            System.out.println("getMyNoticeExist_servlet : " + ex);
        }
        return myNoticeExist;
    }
    
    public int insertMypub(final String s, final String s2, final String s3) {
        int insertMypub = 0;
        try {
            insertMypub = this.pageset.insertMypub(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println("insertMypub_servlet : " + ex);
        }
        return insertMypub;
    }
    
    public int deleteMypub(final String s) {
        int deleteMypub = 0;
        try {
            deleteMypub = this.pageset.deleteMypub(s);
        }
        catch (Exception ex) {
            System.out.println("deleteMypub_servlet : " + ex);
        }
        return deleteMypub;
    }
    
    public PagesetInfo getMypub(final String s) {
        PagesetInfo mypub = null;
        try {
            mypub = this.pageset.getMypub(s);
        }
        catch (Exception ex) {
            System.out.println("getMypub_servlet : " + ex);
        }
        return mypub;
    }
    
    public int getMypubExist(final String s) {
        int mypubExist = 0;
        try {
            mypubExist = this.pageset.getMypubExist(s);
        }
        catch (Exception ex) {
            System.out.println("getMypubExist_servlet : " + ex);
        }
        return mypubExist;
    }
    
    public int getMyMessageExist(final String s, final String s2) {
        int myMessageExist = 0;
        try {
            myMessageExist = this.pageset.getMyMessageExist(s, s2);
        }
        catch (Exception ex) {
            System.out.println("getMyMessageExist_servlet : " + ex);
        }
        return myMessageExist;
    }
    
    public Vector getMyMessageAll() {
        Vector myMessageAll = new Vector();
        try {
            myMessageAll = this.pageset.getMyMessageAll();
        }
        catch (Exception ex) {
            System.out.println("getMyMessageAll_servlet : " + ex);
        }
        return myMessageAll;
    }
    
    public String getMyPubname(final String s) {
        String myPubname = "";
        try {
            myPubname = this.pageset.getMyPubname(s);
        }
        catch (Exception ex) {
            System.out.println("getMyPubname_servlet : " + ex);
        }
        return myPubname;
    }
    
    public PagesetInfo getMyPersoninfo(final String s) {
        PagesetInfo myPersoninfo = null;
        try {
            myPersoninfo = this.pageset.getMyPersoninfo(s);
        }
        catch (Exception ex) {
            System.out.println("getMyPersoninfo_servlet : " + ex);
        }
        return myPersoninfo;
    }
}
