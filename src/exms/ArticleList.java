// 
// Decompiled by Procyon v0.5.30
// 

package exms;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Vector;

public class ArticleList
{
    private Vector articles;
    private String bbsid;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;
    private String tableName;
    private int row;
    private int pg;
    private int total;
    
    public ArticleList(final String t, final int p) {
        this.DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:okjsp";
        this.DB_USER = "scott";
        this.DB_PASSWORD = "tiger";
        this.tableName = "kboard";
        this.row = 10;
        this.bbsid = t;
        this.pg = p;
        this.articles = new Vector();
    }
    
    public ArticleList(final String t, final int p, final int r) {
        this.DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:okjsp";
        this.DB_USER = "scott";
        this.DB_PASSWORD = "tiger";
        this.tableName = "kboard";
        this.row = 10;
        this.bbsid = t;
        this.pg = p;
        this.row = r;
        this.articles = new Vector();
    }
    
    public Vector getArticles() {
        return this.articles;
    }
    
    public String getBbsid() {
        return this.bbsid;
    }
    
    public int getTotal() {
        return this.total;
    }
    
    public void setNewList() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection pconn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            int seq = 0;
            int ref = 0;
            int step = 0;
            int lev = 0;
            String bwriter = "";
            String subject = "";
            String password = "";
            String email = "";
            int read = 0;
            String fileName = "";
            String maskName = "";
            long fileSize = 0L;
            int download = 0;
            Timestamp when = null;
            String ip = "";
            pconn = DriverManager.getConnection(this.DB_URL, this.DB_USER, this.DB_PASSWORD);
            final String indexq = "/*+ index_desc(" + this.tableName + " idx_" + this.tableName + ") */";
            String query = "select " + indexq + " rownum rseq, seq, ref, lev, writer, subject, password, email, read, filename, maskname, filesize, download, when, ip from " + this.tableName + " where bbsid=? order by when desc";
            query = "select * from (" + query + ") where rownum <=?";
            final int start = this.pg * this.row;
            final int end = start + this.row;
            pstmt = pconn.prepareStatement(query);
            pstmt.setString(1, this.bbsid);
            pstmt.setInt(2, this.row);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                seq = rs.getInt("SEQ");
                ref = rs.getInt("REF");
                step = 0;
                lev = rs.getInt("LEV");
                bwriter = rs.getString("WRITER");
                subject = rs.getString("SUBJECT");
                password = rs.getString("PASSWORD");
                email = rs.getString("EMAIL");
                read = rs.getInt("READ");
                fileName = rs.getString("FILENAME");
                maskName = rs.getString("MASKNAME");
                fileSize = rs.getLong("FILESIZE");
                download = rs.getInt("DOWNLOAD");
                when = rs.getTimestamp("WHEN");
                ip = rs.getString("IP");
                final Article article = new Article(seq, ref, step, lev, bwriter, subject, password, email, read, fileName, maskName, fileSize, download, when, ip);
                this.articles.add(article);
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
            if (pconn != null) {
                pconn.close();
            }
        }
        if (rs != null) {
            rs.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }
        if (pconn != null) {
            pconn.close();
        }
    }
}
