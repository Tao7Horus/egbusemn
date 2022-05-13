// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import java.util.Vector;
import common.Log;
import javax.naming.InitialContext;
import portal.community.forum.session.Forum;
import portal.community.forum.session.ForumHome;

public class ForumServlet
{
    private ForumHome forum_home;
    private Forum forum_remote;
    
    public ForumServlet() {
        this.forum_home = null;
        this.forum_remote = null;
        try {
            this.forum_home = (ForumHome)new InitialContext().lookup("Forum");
            this.forum_remote = this.forum_home.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public Vector getForumRoomList(final String s, final String s2, final int n, final int n2, final String s3) {
        Vector forumRoomList = new Vector();
        try {
            forumRoomList = this.forum_remote.getForumRoomList(s, s2, n, n2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return forumRoomList;
    }
    
    public void insertForumRoom(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) {
        try {
            this.forum_remote.insertForumRoom(s, s2, s3, s4, s5, s6, s7, s8);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public Vector getForumRoomInfo(final String s) {
        Vector forumRoomInfo = new Vector();
        try {
            forumRoomInfo = this.forum_remote.getForumRoomInfo(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return forumRoomInfo;
    }
    
    public void updateForumStatus(final String s, final String s2) {
        try {
            this.forum_remote.updateForumStatus(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void updateRegCont(final String s, final String s2) {
        try {
            this.forum_remote.updateRegCont(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public String comparePass(final String s, final String s2, final String s3) {
        String comparePass = null;
        try {
            comparePass = this.forum_remote.comparePass(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return comparePass;
    }
    
    public int getForumRoomListCount(final String s, final String s2, final String s3) {
        int forumRoomListCount = 0;
        try {
            forumRoomListCount = this.forum_remote.getForumRoomListCount(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return forumRoomListCount;
    }
    
    public void insertForum(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        try {
            this.forum_remote.insertForum(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public int getForumListCount(final String s, final String s2, final String s3) {
        int forumListCount = 0;
        try {
            forumListCount = this.forum_remote.getForumListCount(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return forumListCount;
    }
    
    public Vector getForumList(final String s, final String s2, final int n, final int n2, final String s3) {
        Vector forumList = new Vector();
        try {
            forumList = this.forum_remote.getForumList(s, s2, n, n2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return forumList;
    }
    
    public Vector getForumListInfo(final String s) {
        Vector forumListInfo = new Vector();
        try {
            forumListInfo = this.forum_remote.getForumListInfo(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return forumListInfo;
    }
    
    public void addViewCount(final String s) {
        try {
            this.forum_remote.addViewCount(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void modifyForum(final String s, final String s2, final String s3, final String s4) {
        try {
            this.forum_remote.modifyForum(s, s2, s3, s4);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void deleteForum(final String s, final String s2, final String s3) {
        try {
            this.forum_remote.deleteForum(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public Vector getNextCode(final String s, final String s2) {
        Vector nextCode = new Vector();
        try {
            nextCode = this.forum_remote.getNextCode(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return nextCode;
    }
    
    public Vector getPrevCode(final String s, final String s2) {
        Vector prevCode = new Vector();
        try {
            prevCode = this.forum_remote.getPrevCode(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return prevCode;
    }
    
    public Vector getUserForumList(final String s) {
        Vector userForumList = new Vector();
        try {
            userForumList = this.forum_remote.getUserForumList(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return userForumList;
    }
}
