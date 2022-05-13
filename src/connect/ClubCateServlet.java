// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import java.util.Vector;
import common.Log;
import javax.naming.InitialContext;
import portal.community.clubcate.session.ClubCate;
import portal.community.clubcate.session.ClubCateHome;

public class ClubCateServlet
{
    private ClubCateHome clubcate_home;
    private ClubCate clubcate_remote;
    
    public ClubCateServlet() {
        this.clubcate_home = null;
        this.clubcate_remote = null;
        try {
            this.clubcate_home = (ClubCateHome)new InitialContext().lookup("ClubCate");
            this.clubcate_remote = this.clubcate_home.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public Vector getClubCateList() {
        Vector clubCateList = new Vector();
        try {
            clubCateList = this.clubcate_remote.getClubCateList();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return clubCateList;
    }
    
    public void insertClubCate(final String s) {
        try {
            this.clubcate_remote.insertClubCate(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void updateClubCate(final String s, final String s2) {
        try {
            this.clubcate_remote.updateClubCate(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void updateCatePos(final String s, final String s2, final String s3) {
        try {
            this.clubcate_remote.updateCatePos(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public int getCateClubCount(final String s) {
        int cateClubCount = 0;
        try {
            cateClubCount = this.clubcate_remote.getCateClubCount(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return cateClubCount;
    }
}
