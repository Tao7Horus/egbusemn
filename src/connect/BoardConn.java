// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import java.util.Vector;
import portal.common.board.datamodel.BoardView;
import portal.common.board.datamodel.BoardInfo;
import portal.common.board.datamodel.BoardList;
import common.Log;
import javax.naming.InitialContext;
import portal.common.board.session.Board;
import portal.common.board.session.BoardHome;

public class BoardConn
{
    private BoardHome boardHome;
    private Board board;
    
    public BoardConn() {
        this.boardHome = null;
        this.board = null;
        try {
            this.boardHome = (BoardHome)new InitialContext().lookup("Board");
            this.board = this.boardHome.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public BoardList boardList(final String s, final int n, final int n2) {
        BoardList itemList = null;
        try {
            itemList = this.board.itemList(s, n, n2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemList;
    }
    
    public BoardList boardListSearch(final String s, final int n, final int n2, final String s2, final String s3) {
        BoardList itemListSearch = null;
        try {
            itemListSearch = this.board.itemListSearch(s, n, n2, s2, s3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemListSearch;
    }
    
    public BoardInfo boardGet(final String s, final int n) {
        BoardInfo itemGet = null;
        try {
            itemGet = this.board.itemGet(s, n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemGet;
    }
    
    public BoardView boardView(final String s, final int n, final boolean b, final String s2) {
        BoardView itemView = null;
        try {
            itemView = this.board.itemView(s, n, b, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemView;
    }
    
    public Vector boardViewSel(final String s, final String s2) {
        Vector itemViewSel = null;
        try {
            itemViewSel = this.board.itemViewSel(s, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemViewSel;
    }
    
    public int boardAdd(final String s, final String s2, final String s3, final String s4, final String s5) {
        int itemAdd = 0;
        try {
            itemAdd = this.board.itemAdd(s, s2, s3, s4, s5);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemAdd;
    }
    
    public int boardEdit(final String s, final int n, final String s2, final String s3) {
        int itemEdit = 0;
        try {
            itemEdit = this.board.itemEdit(s, n, s2, s3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemEdit;
    }
    
    public int boardDel(final String s, final int n, final String s2) {
        int itemDel = 0;
        try {
            itemDel = this.board.itemDel(s, n, s2);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDel;
    }
    
    public int boardDelSel(final String s, final String s2, final String s3) {
        int itemDelSel = 0;
        try {
            itemDelSel = this.board.itemDelSel(s, s2, s3);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemDelSel;
    }
    
    public Vector boardRecent(final String s, final int n) {
        Vector itemRecent = null;
        try {
            itemRecent = this.board.itemRecent(s, n);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemRecent;
    }
    
    public String boardTitle(final String s) {
        String itemTitle = null;
        try {
            itemTitle = this.board.itemTitle(s);
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
        return itemTitle;
    }
}
