// 
// Decompiled by Procyon v0.5.30
// 

package exms;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

public class NewArticles
{
    private Vector articles;
    private String bbsid;
    private String tableName;
    private int row;
    
    public NewArticles(final String t, final int r) {
        this.tableName = "kboard";
        this.row = 10;
        this.bbsid = t;
        this.row = r;
        this.articles = new Vector();
    }
    
    public Vector getArticles() {
        return this.articles;
    }
    
    public void setNewList() throws ClassNotFoundException, SQLException {
        final Connection pconn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            int seq = 0;
            final int ref = 0;
            final int step = 0;
            int lev = 0;
            String bwriter = "";
            String subject = "";
            final String password = "";
            final String email = "";
            final int read = 0;
            final String fileName = "";
            final String maskName = "";
            final long fileSize = 0L;
            final int download = 0;
            Timestamp when = null;
            final String ip = "";
            String query = "SELECT SEQ, LEV, WRITER, SUBJECT, WHEN FROM " + this.tableName + " WHERE BBSID=? ORDER BY WHEN DESC";
            query = "SELECT SEQ, LEV, WRITER, SUBJECT, WHEN FROM (" + query + ") WHERE ROWNUM <= ?";
            pstmt = pconn.prepareStatement(query);
            pstmt.setString(1, this.bbsid);
            pstmt.setInt(2, this.row);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                seq = rs.getInt("SEQ");
                lev = rs.getInt("LEV");
                bwriter = rs.getString("WRITER");
                subject = rs.getString("SUBJECT");
                when = rs.getTimestamp("WHEN");
            }
        }
        catch (SQLException e) {
            throw e;
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
        if (rs != null) {
            rs.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }
    }
}
