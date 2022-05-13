// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import java.util.Vector;
import common.Log;
import javax.naming.InitialContext;
import portal.community.clubboard.session.ClubBoard;
import portal.community.clubboard.session.ClubBoardHome;

public class ClubBoardServlet
{
    private ClubBoardHome board_home;
    private ClubBoard board_remote;
    
    public ClubBoardServlet() {
        this.board_home = null;
        this.board_remote = null;
        try {
            this.board_home = (ClubBoardHome)new InitialContext().lookup("ClubBoard");
            this.board_remote = this.board_home.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public int getMaxBoardCode(final String s, final String s2) {
        int maxBoardCode = 1;
        try {
            maxBoardCode = this.board_remote.getMaxBoardCode(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return maxBoardCode;
    }
    
    public String getBoardName(final String s, final String s2) {
        String boardName = null;
        try {
            boardName = this.board_remote.getBoardName(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return boardName;
    }
    
    public void createBoard(final String s, final String s2, final String s3, final String s4) {
        try {
            this.board_remote.createBoard(s, s2, s3, s4);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public Vector getBoardList(final String s, final String s2) {
        Vector boardList = new Vector();
        try {
            boardList = this.board_remote.getBoardList(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return boardList;
    }
    
    public void updateBoardPos(final String s, final String s2, final String s3, final String s4) {
        try {
            this.board_remote.updateBoardPos(s, s2, s3, s4);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public String getNextPosition(final String s, final String s2) {
        String nextPosition = null;
        try {
            nextPosition = this.board_remote.getNextPosition(s, s2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return nextPosition;
    }
    
    public void deleteBoard(final String s, final String s2, final String s3) {
        try {
            this.board_remote.deleteBoard(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void updateBoard(final String s, final String s2, final String s3) {
        try {
            this.board_remote.updateBoard(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public int getBoardCount(final String s, final String s2, final String s3, final String s4) {
        int boardCount = 0;
        try {
            boardCount = this.board_remote.getBoardCount(s, s2, s3, s4);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return boardCount;
    }
    
    public Vector getBoardContList(final String s, final String s2, final String s3, final String s4, final int n, final int n2) {
        Vector boardContList = new Vector();
        try {
            boardContList = this.board_remote.getBoardContList(s, s2, s3, s4, n, n2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return boardContList;
    }
    
    public void insertCont(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        try {
            this.board_remote.insertCont(s, s2, s3, s4, s5, s6, s7);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void insertPdsCont(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10) {
        try {
            this.board_remote.insertPdsCont(s, s2, s3, s4, s5, s6, s7, s8, s9, s10);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void updateCont(final String s, final String s2, final String s3, final String s4, final String s5) {
        try {
            this.board_remote.updateCont(s, s2, s3, s4, s5);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void updatePds(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) {
        try {
            this.board_remote.updatePds(s, s2, s3, s4, s5, s6, s7, s8);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void deleteCont(final String s, final String s2, final String s3, final String s4) {
        try {
            this.board_remote.deleteCont(s, s2, s3, s4);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void deleteCont(final String s, final String s2, final String s3) {
        try {
            this.board_remote.deleteCont(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public Vector getContInfo(final String s, final String s2, final String s3) {
        Vector contInfo = new Vector();
        try {
            contInfo = this.board_remote.getContInfo(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return contInfo;
    }
    
    public void addViewCount(final String s, final String s2, final String s3) {
        try {
            this.board_remote.addViewCount(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public Vector getNextCode(final String s, final String s2, final String s3) {
        Vector nextCode = new Vector();
        try {
            nextCode = this.board_remote.getNextCode(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return nextCode;
    }
    
    public Vector getPrevCode(final String s, final String s2, final String s3) {
        Vector prevCode = new Vector();
        try {
            prevCode = this.board_remote.getPrevCode(s, s2, s3);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return prevCode;
    }
    
    public Vector getLastContList(final String s, final String s2, final int n) {
        Vector lastContList = new Vector();
        try {
            lastContList = this.board_remote.getLastContList(s, s2, n);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return lastContList;
    }
}
