// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import java.util.Vector;
import entity.UM_CL_Regi_001l;
import java.sql.PreparedStatement;
import common.util.CommUtil;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;

public class UM_CL_Regi_001p
{
    public void insertCode(final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " INSERT INTO syn_pub_code  (cd_cls, cd, cd_nm, cd_nm2,  use_yn, input_dt  )  VALUES( ?, ?, ?, ?, 'Y', sysdate  )";
        final CommUtil cu = new CommUtil();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, CommUtil.retSpace(req.getParameter("cdCls")));
            pstmt.setString(2, CommUtil.retSpace(req.getParameter("cd")));
            pstmt.setString(3, CommUtil.retSpace(req.getParameter("cdNm")));
            pstmt.setString(4, CommUtil.retSpace(req.getParameter("cdNm2")));
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Cập nhật dữ liệu lỗi.");
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex2) {}
    }
    
    public void updateCode(final HttpServletRequest req, final Connection con) throws Exception {
        final CommUtil cu = new CommUtil();
        PreparedStatement pstmt = null;
        final String cdCls = CommUtil.retSpace(req.getParameter("cdCls"));
        final String cd = CommUtil.retSpace(req.getParameter("cd"));
        final String sql = " UPDATE syn_pub_code  SET cd_nm = ?, cd_nm2 = ? WHERE CD_CLS = '" + cdCls + "'" + "AND CD = '" + cd + "'";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, CommUtil.retSpace(req.getParameter("cdNm")));
            pstmt.setString(2, CommUtil.retSpace(req.getParameter("cdNm2")));
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Cập nhật dữ liệu lỗi.");
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex2) {}
    }
    
    public UM_CL_Regi_001l[] selectCodeList(final int pagenum, final int PAGEMAX, final String cdCls, final String cdNm) throws Exception {
        Connection con = null;
        Trx trx = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_CL_Regi_001l[] codeList = (UM_CL_Regi_001l[])null;
        UM_CL_Regi_001l item = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        sb.append("SELECT cd_cls, cd, cd_nm, cd_nm2,\t\n");
        sb.append(" input_dt, NUM\t\n");
        sb.append(" FROM ( \t\n");
        sb.append("\tSELECT a.*, ROWNUM NUM\t\n");
        sb.append(" FROM ( SELECT B.cd_cls, B.cd, B.cd_nm, B.cd_nm2,\t\n");
        sb.append(" to_char(B.input_dt,'DD-MM-YYYY') input_dt\t\n");
        sb.append(" FROM syn_pub_code\tB \n");
        sb.append(" WHERE B.CD_CLS = ? \n");
        if (cdNm != null && cdNm.trim().length() >= 1) {
            sb.append(" AND lower(B.CD_NM) like '%'||?||'%'\t\n");
        }
        sb.append(" ) a\t\n");
        sb.append(" ) \t\n");
        sb.append(" WHERE  NUM BETWEEN (((" + pagenum + " - 1) * \t\n");
        sb.append(String.valueOf(PAGEMAX) + ")+1) AND (" + pagenum + " * " + PAGEMAX + ")\t\n");
        try {
            sql = sb.toString();
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, cdCls);
            if (cdNm != null && cdNm.trim().length() >= 1) {
                pstm.setString(2, cdNm.trim());
            }
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                item = new UM_CL_Regi_001l();
                item.setCdCls(rs.getString(1));
                item.setCd(rs.getString(2));
                item.setCdNm(rs.getString(3));
                item.setCdNm2(rs.getString(4));
                item.setInputDt(rs.getString(5));
                vec.addElement(item);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_CL_Regi_001p.select_userlist block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_CL_Regi_001p.select_userlist block Exception : " + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    trx.close();
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
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        if (vec.size() > 0) {
            codeList = new UM_CL_Regi_001l[vec.size()];
            vec.copyInto(codeList);
            return codeList;
        }
        return null;
    }
    
    public int codeCount(final String cdCls, final String cdNm) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        try {
            sb.append("\n SELECT count(*) FROM syn_pub_code \t\n");
            sb.append("\n WHERE CD_CLS = ? \t\n");
            if (cdNm.length() >= 1) {
                sb.append(" AND lower(CD_NM) like '%'||?||'%'\t\n");
            }
            sql = sb.toString();
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, cdCls);
            if (cdNm.length() >= 1) {
                pstm.setString(2, cdNm);
            }
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_CL_Regi_001p.codeCount('" + cdCls + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_CL_Regi_001p.codeCount('" + cdCls + "'):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    trx.close();
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
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return count;
    }
    
    public UM_CL_Regi_001l selectCode(final String cdCls, final String cd) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        final UM_CL_Regi_001l item = new UM_CL_Regi_001l();
        final String sql = "SELECT cd_cls, cd, cd_nm, cd_nm2, to_char(input_dt,'DD-MM-YYYY') input_dt FROM syn_pub_code WHERE CD_CLS = '" + cdCls + "'" + "AND CD = '" + cd + "'";
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                item.setCdCls(rs.getString(1));
                item.setCd(rs.getString(2));
                item.setCdNm(rs.getString(3));
                item.setCdNm2(rs.getString(4));
                item.setInputDt(rs.getString(5));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_CL_Regi_001p.select_userlist block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_CL_Regi_001p.select_userlist block Exception : " + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    trx.close();
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
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return item;
    }
    
    public void delCodeList(final String cdList, final String cdCls, final Connection conn) {
        final ResultSet rs = null;
        PreparedStatement pstm = null;
        final String sql = "DELETE  FROM syn_pub_code WHERE CD_CLS = '" + cdCls + "'" + "AND CD in(" + cdList + ")";
        try {
            pstm = conn.prepareStatement(sql);
            if (pstm.executeUpdate() != 1) {
                throw new Exception("Xử lý xóa dữ liệu lỗi.");
            }
            pstm.clearParameters();
        }
        catch (SQLException sqle) {
            Log.debug("UM_CL_Regi_001p.select_userlist block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_CL_Regi_001p.select_userlist block Exception : " + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex3) {}
        }
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex4) {}
        }
    }
}
