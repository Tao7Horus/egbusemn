// 
// Decompiled by Procyon v0.5.30
// 

package common.util;

import java.sql.ResultSetMetaData;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;

public class EP_COB_GTQ809
{
    public int getCount(final String sql) {
        Trx resource = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int nReturn = 0;
        try {
            resource = new Trx(this);
            con = resource.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                nReturn = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("EP_COV_GTQ809.getCount(): sql= " + sql);
            Log.debug("EP_COV_GTQ809.getCount(): sqlErrMsg= " + sqle.toString());
            Log.debug("EP_COV_GTQ809.getCount(): sqlErrCode= " + sqle.getErrorCode());
        }
        catch (Exception e) {
            Log.debug("sbdgg115b.getCount(): = " + e.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    resource.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return nReturn;
    }
    
    public boolean isDoubleDefeatMethod(final String gonggo_num, final String gonggo_cha) {
        Trx resource = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int intResult = -1;
        boolean resultReturn = false;
        final String sql = " select count(*) from 입찰공고현황  where 입찰공고번호='" + gonggo_num + "' and 입찰공고차수='" + gonggo_cha + "'" + " and 계약방법 in ('3330','3230','2330','2230','1330','1230')";
        try {
            resource = new Trx(this);
            con = resource.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                intResult = rs.getInt(1);
            }
            resultReturn = (intResult == 1);
        }
        catch (SQLException sqle) {
            Log.debug("EP_COV_GTQ809.isDoubleDefeatMethod(): sqlErrCode= " + sqle.getErrorCode());
            Log.debug("EP_COV_GTQ809.isDoubleDefeatMethod(): sqlErrMsg= " + sqle.toString());
            Log.debug("EP_COV_GTQ809.isDoubleDefeatMethod(): sql= " + sql);
        }
        catch (Exception e) {
            Log.debug("EP_COV_GTQ809.isDoubleDefeatMethod(): = " + e.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    resource.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return resultReturn;
    }
    
    public Vector getList(final String sql, final int page, final int page_size) {
        Trx resource = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        final Vector vReturn = new Vector();
        int count = 0;
        final int index = (page - 1) * page_size;
        try {
            resource = new Trx(this);
            con = resource.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            for (int i = 0; i < index; ++i) {
                rs.next();
            }
            while (rs.next()) {
                final Vector vec = new Vector();
                vec.addElement(rs.getString(1));
                vec.addElement(rs.getString(2));
                vec.addElement(rs.getString(3));
                vec.addElement(rs.getString(4));
                vec.addElement(rs.getString(5));
                vec.addElement(rs.getString(6));
                vec.addElement(rs.getString(7));
                vec.addElement(rs.getString(8));
                vec.addElement(rs.getString(9));
                vec.addElement(rs.getString(10));
                vec.addElement(rs.getString(11));
                vec.addElement(rs.getString(12));
                vec.addElement(rs.getString(13));
                vec.addElement(rs.getString(14));
                vec.addElement(rs.getString(15));
                vec.addElement(rs.getString(16));
                vReturn.addElement(vec);
                if (++count >= page_size) {
                    break;
                }
            }
        }
        catch (SQLException sqle) {
            Log.debug("EP_COV_GTQ809.getList(): sql= " + sql);
            Log.debug("EP_COV_GTQ809.getList(): sqlErrMsg= " + sqle.toString());
            Log.debug("EP_COV_GTQ809.getList(): sqlErrCode= " + sqle.getErrorCode());
        }
        catch (Exception e) {
            Log.debug("EP_COV_GTQ509.getList(): = " + e.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    resource.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return vReturn;
    }
    
    public int getCountDiv(final String sql) {
        Statement stmt = null;
        Trx resource = null;
        Connection con = null;
        ResultSet rs = null;
        int nReturn = 0;
        try {
            resource = new Trx(this);
            con = resource.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                nReturn = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("EP_COV_GTQ809.getCountDiv(): sql= " + sql);
            Log.debug("EP_COV_GTQ809.getCountDiv(): sqlErrMsg= " + sqle.toString());
            Log.debug("EP_COV_GTQ809.getCountDiv(): sqlErrCode= " + sqle.getErrorCode());
        }
        catch (Exception e) {
            Log.debug("EP_COV_GTQ809.getCountDiv(): = " + e.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    resource.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return nReturn;
    }
    
    public Vector getListDiv(final String sql, final int page, final int page_size) {
        Statement stmt = null;
        Trx resource = null;
        Connection con = null;
        ResultSet rs = null;
        final Vector vReturn = new Vector();
        int count = 0;
        final int index = (page - 1) * page_size;
        try {
            resource = new Trx(this);
            con = resource.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            for (int i = 0; i < index; ++i) {
                rs.next();
            }
            while (rs.next()) {
                final Vector vec = new Vector();
                vec.addElement(rs.getString(1));
                vec.addElement(rs.getString(2));
                vec.addElement(rs.getString(3));
                vec.addElement(rs.getString(4));
                vec.addElement(rs.getString(5));
                vec.addElement(rs.getString(6));
                vec.addElement(rs.getString(7));
                vec.addElement(rs.getString(8));
                vReturn.addElement(vec);
                if (++count >= page_size) {
                    break;
                }
            }
        }
        catch (SQLException sqle) {
            Log.debug("EP_COV_GTQ809.getListDiv(): sql= " + sql);
            Log.debug("EP_COV_GTQ809.getListDiv(): sqlErrMsg= " + sqle.toString());
            Log.debug("EP_COV_GTQ809.getListDiv(): sqlErrCode= " + sqle.getErrorCode());
        }
        catch (Exception e) {
            Log.debug("EP_COV_GTQ809.getListDiv(): = " + e.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    resource.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return vReturn;
    }
    
    public Vector getListData(final String sql, final int size, final int page_no) {
        Trx resource = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        final Vector vReturn = new Vector();
        final int index = (page_no - 1) * size;
        Label_0495: {
            try {
                resource = new Trx(this);
                con = resource.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                final ResultSetMetaData rsmd = rs.getMetaData();
                final int count = rsmd.getColumnCount();
                if (page_no == 1) {
                    for (int j = 0; j < size; ++j) {
                        rs.next();
                        final Vector vec = new Vector();
                        for (int i = 1; i <= count; ++i) {
                            vec.addElement(rs.getString(i));
                        }
                        vReturn.addElement(vec);
                    }
                    break Label_0495;
                }
                for (int k = 0; k < index; ++k) {
                    rs.next();
                }
                while (rs.next()) {
                    final Vector vec2 = new Vector();
                    for (int l = 1; l <= count; ++l) {
                        vec2.addElement(rs.getString(l));
                    }
                    vReturn.addElement(vec2);
                }
            }
            catch (SQLException sqle) {
                Log.debug("EP_COV_GTQ809.getList(): sql= " + sql);
                Log.debug("EP_COV_GTQ809.getList(): sqlErrMsg= " + sqle.toString());
                Log.debug("EP_COV_GTQ809.getList(): sqlErrCode= " + sqle.getErrorCode());
            }
            catch (Exception e) {
                Log.debug("EP_COV_GTQ809.getList(): = " + e.toString());
            }
            finally {
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex2) {}
                }
                if (con != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return vReturn;
    }
    
    public Vector getList(final String sql) {
        Trx resource = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        final Vector vReturn = new Vector();
        try {
            resource = new Trx(this);
            con = resource.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            final ResultSetMetaData rsmd = rs.getMetaData();
            final int count = rsmd.getColumnCount();
            while (rs.next()) {
                final Vector vec = new Vector();
                for (int i = 1; i <= count; ++i) {
                    vec.addElement(rs.getString(i));
                }
                vReturn.addElement(vec);
            }
        }
        catch (SQLException sqle) {
            Log.debug("EP_COV_GTQ809.getList(): sql= " + sql);
            Log.debug("EP_COV_GTQ809.getList(): sqlErrMsg= " + sqle.toString());
            Log.debug("EP_COV_GTQ809.getList(): sqlErrCode= " + sqle.getErrorCode());
        }
        catch (Exception e) {
            Log.debug("EP_COV_GTQ809.getList(): = " + e.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    resource.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return vReturn;
    }
    
    public static String getNextPageIndexes(final String url, final int totalRow, final int page_size, final int page_no) {
        String sReturn = "";
        if (totalRow <= page_size) {
            return sReturn;
        }
        final int startPage = ((page_no % 10 == 0) ? (page_no - 1) : page_no) / 10 * 10 + 1;
        final int totalPage = totalRow / page_size + ((totalRow % page_size != 0) ? 1 : 0);
        final int endPage = (totalPage - startPage >= 10) ? (startPage + 9) : totalPage;
        sReturn = String.valueOf(sReturn) + "<table width=95% cellpadding=2 cellspacing=2><tr><td align=center background=/img/dotlines.gif height=1></td></tr><tr><td align=center class=redc>";
        if (page_no > 10) {
            sReturn = String.valueOf(sReturn) + "<a href='" + url + "&page_no=" + (startPage - 1) + "'><IMG ALIGN=absmiddle SRC=/img/bu_backarw.gif BORDER=0></a>";
        }
        else {
            sReturn = String.valueOf(sReturn) + "<IMG ALIGN=absmiddle SRC=/img/bu_backarw.gif BORDER=0>";
        }
        for (int i = startPage; i <= endPage; ++i) {
            sReturn = String.valueOf(sReturn) + ((i == page_no) ? ("<font size=2 color='#FF6600'><b><a href='" + url + "&page_no=" + i + "'>[" + i + "]</a></b></font>") : ("<a href='" + url + "&page_no=" + i + "'>[" + i + "]</a>"));
        }
        if (endPage != totalPage) {
            sReturn = String.valueOf(sReturn) + "<a href='" + url + "&page_no=" + (endPage + 1) + "'><IMG ALIGN=absmiddle SRC=/img/bu_nextarw.gif BORDER=0></a>";
        }
        else {
            sReturn = String.valueOf(sReturn) + "<IMG ALIGN=absmiddle SRC=/img/bu_nextarw.gif BORDER=0>";
        }
        sReturn = String.valueOf(sReturn) + "</tr><tr><td align=center background=/img/dotlines.gif height=1></td></tr></table>";
        return sReturn;
    }
    
    public String getPage_html(final int tot_cnt, final int pg_row, final int now_pg) {
        final StringBuffer sb_html = new StringBuffer();
        final int vw_pg_cnt = 4;
        final int tot_pg_cnt = tot_cnt / pg_row + ((tot_cnt % pg_row > 0) ? 1 : 0);
        final int prev_pg = now_pg - 1;
        final int next_pg = now_pg + 1;
        int prev_pg_10 = 0;
        int next_pg_10 = 0;
        int vw_end_pg = 0;
        int vw_start_pg = 0;
        if (now_pg % vw_pg_cnt == 0) {
            vw_start_pg = now_pg - vw_pg_cnt + 1;
        }
        else {
            vw_start_pg = now_pg / vw_pg_cnt * vw_pg_cnt + 1;
        }
        prev_pg_10 = vw_start_pg - vw_pg_cnt;
        vw_end_pg = ((vw_start_pg + vw_pg_cnt - 1 > tot_pg_cnt) ? tot_pg_cnt : (vw_start_pg + vw_pg_cnt - 1));
        next_pg_10 = ((tot_pg_cnt - vw_end_pg > 0) ? (vw_end_pg + 1) : tot_pg_cnt);
        sb_html.append("<table width=95% cellpadding=2 cellspacing=2><tr>");
        sb_html.append("<td align=center background=/img/dotlines.gif height=1></td></tr><tr><td align=center class=redc>");
        if (prev_pg_10 > 0) {
            sb_html.append("<a href=javascript:setPage_Change('").append(prev_pg_10);
            sb_html.append("')><font size=2 color=blue>[이전 ").append(vw_pg_cnt);
            sb_html.append("페이지]</font></a>&nbsp;&nbsp;\n");
        }
        if (prev_pg > 0) {
            sb_html.append("<a href=javascript:setPage_Change('").append(prev_pg).append("')><font size=2 color=blue>[이전]</font></a>\n");
        }
        for (int i = vw_start_pg; i <= vw_end_pg; ++i) {
            if (i - now_pg == 0 && vw_end_pg > 1) {
                sb_html.append("<font size=2 color=#cccccc>&nbsp;").append(i).append("&nbsp;");
            }
            if (i - now_pg != 0) {
                sb_html.append("[<a href=javascript:setPage_Change('").append(i).append("')>").append(i).append("</a>]");
            }
        }
        if (next_pg <= tot_pg_cnt) {
            sb_html.append("\n<a href=javascript:setPage_Change('").append(next_pg);
            sb_html.append("')><font size=2 color=blue>[다음]</font></a>&nbsp;&nbsp;\n");
        }
        if (vw_end_pg + 1 <= tot_pg_cnt) {
            sb_html.append("<a href=javascript:setPage_Change('").append(vw_end_pg + 1);
            sb_html.append("')><font size=2 color=blue>[다음 ").append(vw_pg_cnt);
            sb_html.append("페이지]</font></a>\n");
        }
        sb_html.append("</tr><tr><td align=center background=/img/dotlines.gif height=1></td></tr></table>");
        return sb_html.toString();
    }
}
