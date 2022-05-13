// 
// Decompiled by Procyon v0.5.30
// 

package RexUtil;

import java.io.InputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Blob;
import java.util.Calendar;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import javax.sql.DataSource;
import javax.naming.InitialContext;

public class CRexData
{
    InitialContext context;
    DataSource ds;
    Connection oConn;
    Statement oStmt;
    ResultSet oRs;
    ResultSetMetaData oRsmd;
    
    public CRexData() {
        this.context = null;
        this.ds = null;
        this.oConn = null;
        this.oStmt = null;
        this.oRs = null;
        this.oRsmd = null;
    }
    
    public String getData(final String[] array, final String s) {
        final StringBuffer sb = new StringBuffer("");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.oConn = DriverManager.getConnection("jdbc:oracle:thin:@fishgari:1521:orcl", "prdqa", "prdqa");
            this.oStmt = this.oConn.createStatement();
            if (s.equals("XML") || s.equals("")) {
                sb.append("<?xml version='1.0' encoding='EUC-KR'?>");
                sb.append("<gubun>");
            }
            for (int i = 0; i < array.length; ++i) {
                if (!array[i].trim().equals("")) {
                    this.oRs = this.oStmt.executeQuery(array[i]);
                    this.oRsmd = this.oRs.getMetaData();
                    final int columnCount = this.oRsmd.getColumnCount();
                    if (s.equals("XML") || s.equals("")) {
                        sb.append("<rpt" + (i + 1) + ">");
                        sb.append("<rexdataset>");
                    }
                    while (this.oRs.next()) {
                        if (s.equals("XML") || s.equals("")) {
                            sb.append("<rexrow>");
                        }
                        for (int j = 1; j <= columnCount; ++j) {
                            if (s.equals("XML") || s.equals("")) {
                                sb.append("<" + this.oRsmd.getColumnName(j) + ">");
                                if (this.oRsmd.getColumnType(j) == 2004) {
                                    sb.append("<![CDATA[" + this.fnBlobToHex(this.oRs.getBlob(j)) + "]]>");
                                }
                                else {
                                    sb.append("<![CDATA[" + ((this.oRs.getString(j) == null) ? "" : this.oRs.getString(j)) + "]]>");
                                }
                                sb.append("</" + this.oRsmd.getColumnName(j) + ">");
                            }
                            else if (s.equals("CSV")) {
                                if (this.oRsmd.getColumnType(j) == 2004) {
                                    sb.append("" + this.fnBlobToHex(this.oRs.getBlob(j)));
                                }
                                else {
                                    sb.append("" + ((this.oRs.getString(j) == null) ? "" : this.oRs.getString(j)));
                                }
                                sb.append("|*|");
                            }
                        }
                        if (s.equals("XML") || s.equals("")) {
                            sb.append("</rexrow>");
                        }
                    }
                    if (s.equals("XML") || s.equals("")) {
                        sb.append("</rexdataset>");
                        sb.append("</rpt" + (i + 1) + ">");
                    }
                    else if (s.equals("CSV")) {
                        if (!sb.toString().trim().equals("")) {
                            sb.delete(sb.length() - 3, sb.length());
                        }
                        if (i < array.length - 1) {
                            sb.append("|@|");
                        }
                    }
                }
                else if (array[i].trim().equals("") && (s.equals("XML") || s.equals(""))) {
                    sb.append("<rpt" + (i + 1) + ">");
                    sb.append("<rexdataset>");
                    sb.append("<rexrow>");
                    sb.append("</rexrow>");
                    sb.append("</rexdataset>");
                    sb.append("</rpt" + (i + 1) + ">");
                }
            }
            if (s.equals("XML") || s.equals("")) {
                sb.append("</gubun>");
            }
            this.oRs.close();
            this.oStmt.close();
            this.oConn.close();
            if (!s.equals("XML")) {
                if (!s.equals("")) {
                    if (sb.toString().trim().equals("")) {
                        sb.append("|*|");
                    }
                }
            }
            return sb.toString();
        }
        catch (Exception ex) {
            final StringBuffer sb2 = new StringBuffer("");
            if (s.equals("XML") || s.equals("")) {
                sb2.append("<?xml version='1.0' encoding='EUC-KR'?>");
                sb2.append("<error>");
                sb2.append("<msg>");
                sb2.append("<![CDATA[" + ((ex == null) ? "" : ex.toString()) + "]]>");
                sb2.append("</msg>");
                sb2.append("</error>");
            }
            else if (s.equals("CSV")) {
                sb2.append((ex == null) ? "" : ex.toString());
            }
            return sb2.toString();
        }
    }
    
    public String setData(final String s, final String s2, final String s3) {
        final StringBuffer sb = new StringBuffer("");
        try {
            Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
            this.oConn = DriverManager.getConnection("jdbc:microsoft:sqlserver://211.238.42.152:1433;DatabaseName=test;User=sa;Password=clip815mail");
            this.oStmt = this.oConn.createStatement();
            final Calendar instance = Calendar.getInstance();
            final String string = Integer.toString(instance.get(1));
            String s4 = Integer.toString(instance.get(2) + 1);
            String s5 = Integer.toString(instance.get(5));
            String s6 = Integer.toString(instance.get(10));
            String s7 = Integer.toString(instance.get(12));
            String s8 = Integer.toString(instance.get(13));
            if (Integer.parseInt(s4) < 10) {
                s4 = "0" + s4;
            }
            if (Integer.parseInt(s5) < 10) {
                s5 = "0" + s5;
            }
            if (Integer.parseInt(s6) < 10) {
                s6 = "0" + s6;
            }
            if (Integer.parseInt(s7) < 10) {
                s7 = "0" + s7;
            }
            if (Integer.parseInt(s8) < 10) {
                s8 = "0" + s8;
            }
            this.oStmt.executeUpdate("insert into rexlog values('" + s + "','" + s2 + "','" + (string + s4 + s5 + s6 + s7 + s8) + "','" + s3 + "')");
            this.oConn.commit();
            this.oStmt.close();
            this.oConn.close();
        }
        catch (Exception ex) {
            sb.append("<?xml version='1.0' encoding='EUC-KR'?>");
            sb.append("<gubun>");
            sb.append("<error>");
            sb.append("<msg>");
            sb.append("<![CDATA[" + ((ex == null) ? "" : ex.toString()) + "]]>");
            sb.append("</msg>");
            sb.append("</error>");
            sb.append("</gubun>");
            return sb.toString();
        }
        return "";
    }
    
    public String fnBlobToHex(final Blob blob) {
        if (blob == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer("");
        try {
            final InputStream binaryStream = blob.getBinaryStream();
            int read;
            while ((read = binaryStream.read()) != -1) {
                if (read < 16) {
                    sb.append("0" + Integer.toHexString(read));
                }
                else {
                    sb.append(Integer.toHexString(read));
                }
            }
            binaryStream.close();
        }
        catch (SQLException ex) {}
        catch (IOException ex2) {}
        return sb.toString();
    }
}
