// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import java.util.Vector;
import common.Log;
import javax.naming.InitialContext;
import portal.community.club.session.Club;
import portal.community.club.session.ClubHome;

public class ClubServlet
{
    private ClubHome club_home;
    private Club club_remote;
    
    public ClubServlet() {
        this.club_home = null;
        this.club_remote = null;
        try {
            this.club_home = (ClubHome)new InitialContext().lookup("Club");
            this.club_remote = this.club_home.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public String chkNotice() {
        String chkNotice = null;
        try {
            chkNotice = this.club_remote.chkNotice();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return chkNotice;
    }
    
    public Vector getCategory() {
        Vector category = new Vector();
        try {
            category = this.club_remote.getCategory();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return category;
    }
    
    public String compareName(final String s) {
        String compareName = null;
        try {
            compareName = this.club_remote.compareName(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return compareName;
    }
    
    public String insertClub(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10) {
        String insertClub = null;
        try {
            insertClub = this.club_remote.insertClub(s, s2, s3, s4, s5, s6, s7, s8, s9, s10);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return insertClub;
    }
    
    public void updateClubInfo(final String s, final String s2, final String s3, final String s4, final String s5) {
        try {
            this.club_remote.updateClubInfo(s, s2, s3, s4, s5);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void clubClose(final String s, final String s2) {
        try {
            this.club_remote.clubClose(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public Vector getClubInfo(final String s) {
        Vector clubInfo = new Vector();
        try {
            clubInfo = this.club_remote.getClubInfo(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return clubInfo;
    }
    
    public Vector getClubList(final String s, final String s2, final String s3, final String s4, final int n, final int n2) {
        Vector clubList = new Vector();
        try {
            clubList = this.club_remote.getClubList(s, s2, s3, s4, n, n2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return clubList;
    }
    
    public void clubRegiste(final String s, final String s2, final String s3, final String s4) {
        try {
            this.club_remote.clubRegiste(s, s2, s3, s4);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public int getClubCount(final String s, final String s2, final String s3, final String s4) {
        int clubCount = 0;
        try {
            clubCount = this.club_remote.getClubCount(s, s2, s3, s4);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return clubCount;
    }
    
    public Vector getCateCount(final String s) {
        Vector cateCount = new Vector();
        try {
            cateCount = this.club_remote.getCateCount(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return cateCount;
    }
    
    public String getClubTotal() {
        String clubTotal = null;
        try {
            clubTotal = this.club_remote.getClubTotal();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return clubTotal;
    }
    
    public String getClubMemTotal(final String s) {
        String clubMemTotal = null;
        try {
            clubMemTotal = this.club_remote.getClubMemTotal(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return clubMemTotal;
    }
    
    public Vector getCateList(final String s, final int n, final int n2) {
        Vector cateList = new Vector();
        try {
            cateList = this.club_remote.getCateList(s, n, n2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return cateList;
    }
    
    public String getCateListCount(final String s) {
        String cateListCount = null;
        try {
            cateListCount = this.club_remote.getCateListCount(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return cateListCount;
    }
    
    public void insertClubMem(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        try {
            this.club_remote.insertClubMem(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void updateMemRule(final String s, final String s2, final String s3) {
        try {
            this.club_remote.updateMemRule(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void updateMemRule(final String s, final String s2) {
        try {
            this.club_remote.updateMemRule(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public Vector getJoinClubList(final String s) {
        Vector joinClubList = new Vector();
        try {
            joinClubList = this.club_remote.getJoinClubList(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return joinClubList;
    }
    
    public int getClubMemCount(final String s, final String s2, final String s3) {
        int clubMemCount = 0;
        try {
            clubMemCount = this.club_remote.getClubMemCount(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return clubMemCount;
    }
    
    public Vector getClubMemList(final String s, final String s2, final int n, final int n2, final String s3) {
        Vector clubMemList = new Vector();
        try {
            clubMemList = this.club_remote.getClubMemList(s, s2, n, n2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return clubMemList;
    }
    
    public Vector getClubMemInfo(final String s, final String s2) {
        Vector clubMemInfo = new Vector();
        try {
            clubMemInfo = this.club_remote.getClubMemInfo(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return clubMemInfo;
    }
    
    public void updateClubMemStatus(final String s, final String s2, final String s3, final String s4) {
        try {
            this.club_remote.updateClubMemStatus(s, s2, s3, s4);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public Vector searchClubMem(final String s, final String s2) {
        Vector searchClubMem = new Vector();
        try {
            searchClubMem = this.club_remote.searchClubMem(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return searchClubMem;
    }
    
    public void updateClubAdmin(final String s, final String s2, final String s3) {
        try {
            this.club_remote.updateClubAdmin(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public String getClubMemCode(final String s, final String s2) {
        String clubMemCode = null;
        try {
            clubMemCode = this.club_remote.getClubMemCode(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return clubMemCode;
    }
    
    public String compareClubReg(final String s, final String s2) {
        String compareClubReg = null;
        try {
            compareClubReg = this.club_remote.compareClubReg(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return compareClubReg;
    }
    
    public Vector getLastClubList(final int n) {
        Vector lastClubList = new Vector();
        try {
            lastClubList = this.club_remote.getLastClubList(n);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return lastClubList;
    }
    
    public void updateClubCategory(final String s) {
        try {
            this.club_remote.updateClubCategory(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void deleteCategory(final String s, final String s2) {
        try {
            this.club_remote.deleteCategory(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public String compareClubReg(final String s) {
        String compareClubReg = null;
        try {
            compareClubReg = this.club_remote.compareClubReg(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return compareClubReg;
    }
    
    public int getRegMemDayCount() {
        int regMemDayCount = 0;
        try {
            regMemDayCount = this.club_remote.getRegMemDayCount();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return regMemDayCount;
    }
    
    public int getRegMemMonthCount() {
        int regMemMonthCount = 0;
        try {
            regMemMonthCount = this.club_remote.getRegMemMonthCount();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return regMemMonthCount;
    }
    
    public int getRegClubDayCount() {
        int regClubDayCount = 0;
        try {
            regClubDayCount = this.club_remote.getRegClubDayCount();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return regClubDayCount;
    }
    
    public int getRegClubMonthCount() {
        int regClubMonthCount = 0;
        try {
            regClubMonthCount = this.club_remote.getRegClubMonthCount();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return regClubMonthCount;
    }
    
    public String checkSysop(final String s, final String s2) {
        String checkSysop = null;
        try {
            checkSysop = this.club_remote.checkSysop(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return checkSysop;
    }
    
    public String checkClubMem(final String s, final String s2) {
        String checkClubMem = null;
        try {
            checkClubMem = this.club_remote.checkClubMem(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return checkClubMem;
    }
    
    public Vector getUserClubInfo(final String s) {
        Vector userClubInfo = new Vector();
        try {
            userClubInfo = this.club_remote.getUserClubInfo(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return userClubInfo;
    }
    
    public Vector getMemClubInfo(final String s) {
        Vector memClubInfo = new Vector();
        try {
            memClubInfo = this.club_remote.getMemClubInfo(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return memClubInfo;
    }
    
    public Vector getClubMemMail(final String s) {
        Vector clubMemMail = new Vector();
        try {
            clubMemMail = this.club_remote.getClubMemMail(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return clubMemMail;
    }
    
    public void retryClub(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9) {
        try {
            this.club_remote.retryClub(s, s2, s3, s4, s5, s6, s7, s8, s9);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
