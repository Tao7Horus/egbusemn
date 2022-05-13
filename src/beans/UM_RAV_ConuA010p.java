// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import common.util.UM_COB_GTQ904;
import java.sql.SQLException;
import java.util.Vector;
import entity.UM_RAE_ConuA010b;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import common.Log;
import common.Trx;
import java.sql.Connection;

public class UM_RAV_ConuA010p
{
    public String getGondongName(final String codeGubun, final String code, final int type, final Object caller_object, Connection conn) {
        Trx resource = null;
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        boolean flag = false;
        final String sql = " select CD_NM,CD_NM2 from SYN_PUB_CODE where CD_CLS=?  and CD=? and USE_YN='Y'";
        String result = "";
        try {
            if (conn == null) {
                resource = new Trx(this, "USEMN");
                conn = resource.getConnection();
                flag = true;
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, codeGubun);
            pstmt.setString(2, code);
            rest = pstmt.executeQuery();
            if (rest.next()) {
                result = rest.getString(type);
            }
        }
        catch (Exception ex) {
            String caller = "unknown";
            if (caller_object != null) {
                caller = caller_object.getClass().getName();
            }
            Log.debug("UM_RAV_ConuA010p.java [getGondongName('" + codeGubun + "','" + code + "','" + type + "','" + caller + "')] : " + ex.toString());
        }
        finally {
            try {
                if (rest != null) {
                    rest.close();
                }
            }
            catch (Exception ex2) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (conn != null && flag) {
                    resource.close();
                }
            }
            catch (Exception ex4) {}
        }
        try {
            if (rest != null) {
                rest.close();
            }
        }
        catch (Exception ex5) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex6) {}
        try {
            if (conn != null && flag) {
                resource.close();
            }
        }
        catch (Exception ex7) {}
        return result;
    }
    
    public UM_RAE_ConuA010b[] License_list(final int pagenum, final int PAGEMAX, final String code, final String codeName, final String buruCode) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        UM_RAE_ConuA010b[] ett = (UM_RAE_ConuA010b[])null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final Vector vec = new Vector();
        final int inx = 1;
        try {
            sql = "select  a , CD, CD_NM from (\n";
            sql = String.valueOf(sql) + "\tselect rownum a, CD_CLS, CD, CD_NM, CD_NM2, USE_YN, INPUT_STAFF_NO, INPUT_DT, SUMMARY, PROPERTY_CD, PROPERTY_VALUE, LINK_YN, EDI_NATION, EDI_INTER, EDI_CIVIL, EDI_RESERVE, EDI_GOV_GOODS, G2B_GOODS, G2B_CIVIL, G2B_SERVICE, G2B_BIDDING, UPDATE_DT";
            sql = String.valueOf(sql) + "\tfrom SYN_PUB_CODE \n";
            sql = String.valueOf(sql) + "\twhere CD_CLS = 'GZ8' \n";
            if (!code.equals("")) {
                sql = String.valueOf(sql) + "\tand CD LIKE '%" + code + "%' \n";
            }
            if (!codeName.equals("")) {
                sql = String.valueOf(sql) + "\tand lower(CD_NM) LIKE lower('%" + codeName + "%')   \n";
            }
            final int start = (pagenum - 1) * PAGEMAX + 1;
            final int end = pagenum * PAGEMAX;
            System.out.println("start = " + start + " : end = " + end);
            sql = String.valueOf(sql) + "\torder by CD \n";
            sql = String.valueOf(sql) + ") where a between " + start + " and " + end;
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            UM_RAE_ConuA010b ett_list = null;
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett_list = new UM_RAE_ConuA010b();
                ett_list.setCode(rs.getString(2));
                ett_list.setCodeName(rs.getString(3));
                vec.addElement(ett_list);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuA010p.License_list(3[" + code + "],4[" + codeName + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuA010p.License_list(3[" + code + "],4[" + codeName + "]):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
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
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        ett = new UM_RAE_ConuA010b[vec.size()];
        vec.copyInto(ett);
        return ett;
    }
    
    public UM_RAE_ConuA010b[] License_list2() {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        UM_RAE_ConuA010b[] ett = (UM_RAE_ConuA010b[])null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final Vector vec = new Vector();
        try {
            sb.append(" SELECT CD , CD_NM, DECODE(CD_NM2,'3','f04','4','s04','5','f04/s04','6','gs'), CD_CLS  FROM SYN_PUB_CODE ");
            sb.append(" WHERE CD_CLS = 'GU9'\t");
            sb.append(" AND USE_YN not in('N') ");
            sb.append(" AND CD_NM LIKE '%services%' ");
            sql = sb.toString();
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            UM_RAE_ConuA010b ett_list = null;
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett_list = new UM_RAE_ConuA010b();
                ett_list.setCode(rs.getString(1));
                ett_list.setCodeName(rs.getString(2));
                ett_list.setCodeName2(rs.getString(3));
                vec.addElement(ett_list);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuA010p.License_list2():" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuA010p.License_list2():" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
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
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        ett = new UM_RAE_ConuA010b[vec.size()];
        vec.copyInto(ett);
        return ett;
    }
    
    public int Max_count2() throws Exception {
        final String sql = " SELECT COUNT(*)\t\t\t FROM SYN_PUB_CODE\t\t where CD_CLS='GU9'\t\t AND USE_YN not in('N')\t AND CD_NM LIKE '%services%' ";
        return new UM_COB_GTQ904().getCount(this, sql, null);
    }
    
    public int Max_count(final String code, final String codeName, final String buruCode) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count = 0;
        final int inx = 1;
        try {
            sql = "select count(CD) from SYN_PUB_CODE where CD_CLS = 'GZ8'";
            if (!code.equals("")) {
                sql = String.valueOf(sql) + "\tand CD LIKE '%" + code + "%' \n";
            }
            if (!codeName.equals("")) {
                sql = String.valueOf(sql) + "\tand lower(CD_NM) LIKE lower('%" + codeName + "%')   \n";
            }
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuA010p.Max_count([" + code + "],[" + codeName + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuA010p.Max_count([" + code + "],[" + codeName + "]):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
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
        if (psmt != null) {
            try {
                psmt.close();
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
    
    public UM_RAE_ConuA010b[] local_list(final int pagenum, final int PAGEMAX, final String code, final String codeName) {
        Trx trx = null;
        Connection con = null;
        UM_RAE_ConuA010b[] ett = (UM_RAE_ConuA010b[])null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final Vector vec = new Vector();
        try {
            sb.append("SELECT\tCD_CLS,  CD,  CD_NM, \n");
            sb.append("CD_NM2, ROWNUM N                                                           \n");
            sb.append("\t FROM (\tSELECT  CD_CLS, Substr(CD,1,5) CD,CD_NM, CD_NM2, ROWNUM N  FROM   SYN_PUB_CODE   \n");
            sb.append("  WHERE CD_CLS='GUC' \t\t\t\t\t\t\t\t\t\t\t\t      \n");
            if (!code.equals("")) {
                sb.append(" AND CD LIKE '" + code + "%'  \n");
            }
            if (!codeName.equals("")) {
                sb.append(" AND CD_NM LIKE '%'||?||'%'   \n");
            }
            sb.append(" ) sub  \n");
            sb.append(" WHERE N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")  \n");
            sql = sb.toString();
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            if (!codeName.equals("")) {
                psmt.setString(1, codeName);
            }
            UM_RAE_ConuA010b ett_list = null;
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett_list = new UM_RAE_ConuA010b();
                ett_list.setCodeGubun(rs.getString(1));
                ett_list.setCode(rs.getString(2));
                ett_list.setCodeName(rs.getString(3));
                ett_list.setCodeName2(rs.getString(4));
                vec.addElement(ett_list);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuA010p.local_list(3[" + code + "],4[" + codeName + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuA010p.local_list(3[" + code + "],4[" + codeName + "]):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
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
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        ett = new UM_RAE_ConuA010b[vec.size()];
        vec.copyInto(ett);
        return ett;
    }
    
    public int Max_count7(final String code, final String codeName) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count7 = 0;
        try {
            sb.append(" SELECT\tCOUNT(CD) \t                                  \t\t    \t\t\n");
            sb.append("\t FROM (\tSELECT CD_CLS, Substr(CD,1,5) CD, CD_NM, CD_NM2, ROWNUM N  FROM   SYN_PUB_CODE \n");
            sb.append("  WHERE CD_CLS='GUC' \t\t\t\t\t\t\t\t\t\t\t    \t\n");
            if (!code.equals("")) {
                sb.append(" AND CD LIKE '" + code + "%'  \n");
            }
            if (!codeName.equals("")) {
                sb.append(" AND CD_NM LIKE '%'||?||'%'   \n");
            }
            sb.append(" ) sub  \n");
            sql = sb.toString();
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            if (!codeName.equals("")) {
                psmt.setString(1, codeName);
            }
            rs = psmt.executeQuery();
            while (rs.next()) {
                count7 = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuA010p.Max_count7([" + code + "],[" + codeName + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuA010p.Max_count7([" + code + "],[" + codeName + "]):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
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
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return count7;
    }
    
    public int Max_count7(final String codeName) throws Exception {
        final String sql = " select count(*)  from SYN_PUB_CODE where CD_CLS='GUC' and substr(CD, 6,5) !='00000' and USE_YN='Y' and CD_NM like '%" + codeName + "%'";
        return new UM_COB_GTQ904().getCount(this, sql, null);
    }
    
    public UM_RAE_ConuA010b[] local_list(final int page_no, final int page_size, String codeName) throws Exception {
        Trx trx = null;
        Connection con = null;
        UM_RAE_ConuA010b[] ett = (UM_RAE_ConuA010b[])null;
        UM_RAE_ConuA010b ett_list = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        String sql = null;
        final Vector vec = new Vector(1, 1);
        Label_0050: {
            if (codeName != null && !codeName.equals("")) {
                break Label_0050;
            }
            codeName = null;
            try {
                sql = " select CD_CLS,CD, CD_NM,CD_NM2 from  ( \tselect CD_CLS,CD, CD_NM,CD_NM2, rownum t  \tfrom ( \t\tselect CD_CLS, substr(CD,1,5) CD,CD_NM, CD_NM2  \t\tfrom SYN_PUB_CODE \t\twhere CD_CLS='GUC' \t\tand substr(CD, 6,5) !='00000' \t\tand USE_YN='Y'";
                if (codeName != null) {
                    sql = String.valueOf(sql) + " \t\tand CD_NM like '%'||?||'%'";
                }
                sql = String.valueOf(sql) + " \t\torder by CD_NM \t)  ) where t >" + Integer.toString(page_size * (page_no - 1)) + " and t< " + Integer.toString(page_size * page_no + 1);
                trx = new Trx(this, "USEMN");
                con = trx.getConnection();
                psmt = con.prepareStatement(sql);
                if (codeName != null) {
                    psmt.setString(1, codeName);
                }
                rs = psmt.executeQuery();
                while (rs.next()) {
                    ett_list = new UM_RAE_ConuA010b();
                    ett_list.setCodeGubun(rs.getString(1));
                    ett_list.setCode(rs.getString(2));
                    ett_list.setCodeName(rs.getString(3));
                    ett_list.setCodeName2(rs.getString(4));
                    vec.addElement(ett_list);
                    ett_list = null;
                }
                ett = new UM_RAE_ConuA010b[vec.size()];
                vec.copyInto(ett);
                return ett;
            }
            catch (SQLException sqle) {
                Log.debug("UM_RAV_ConuA010p.local_list(3[" + codeName + "]):" + sqle.toString());
                throw new Exception(sqle.getMessage());
            }
            catch (Exception exc) {
                Log.debug("UM_RAV_ConuA010p.local_list(3[" + codeName + "]):" + exc.toString());
                throw new Exception(exc.getMessage());
            }
            finally {
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex) {}
                }
                if (psmt != null) {
                    try {
                        psmt.close();
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
        }
    }
    
    public UM_RAE_ConuA010b[] nation_list(final int pagenum, final int PAGEMAX, final String code, final String codeName) {
        Trx trx = null;
        Connection con = null;
        UM_RAE_ConuA010b[] ett = (UM_RAE_ConuA010b[])null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final Vector vec = new Vector();
        try {
            sb.append("SELECT\tCD_CLS, CD, CD_NM, CD_NM2, ROWNUM N \t    \t\t    \t\t\n");
            sb.append("\t FROM (\tSELECT      CD_CLS,CD,CD_NM,CD_NM2, ROWNUM N  FROM   SYN_PUB_CODE   \n");
            sb.append("  WHERE CD_CLS='J02' \t\t\t\t\t\t\t\t\t\t\t\t\n");
            if (!code.equals("")) {
                sb.append(" AND CD LIKE '" + code + "%'  \n");
            }
            if (!codeName.equals("")) {
                sb.append(" AND CD_NM LIKE '%'||?||'%'   \n");
            }
            sb.append(" ) sub  \n");
            sb.append(" WHERE N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")  \n");
            sql = sb.toString();
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            if (!codeName.equals("")) {
                psmt.setString(1, codeName);
            }
            UM_RAE_ConuA010b ett_list = null;
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett_list = new UM_RAE_ConuA010b();
                ett_list.setCodeGubun(rs.getString(1));
                ett_list.setCode(rs.getString(2));
                ett_list.setCodeName(rs.getString(3));
                ett_list.setCodeName2(rs.getString(4));
                vec.addElement(ett_list);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuA010p.nation_list(3[" + code + "],4[" + codeName + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuA010p.nation_list(3[" + code + "],4[" + codeName + "]):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
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
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        ett = new UM_RAE_ConuA010b[vec.size()];
        vec.copyInto(ett);
        return ett;
    }
    
    public int Max_count8(final String code, final String codeName) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count8 = 0;
        try {
            sb.append("SELECT\tCOUNT(CD) \t    \t\t    \t\t\n");
            sb.append("\t FROM (\tSELECT      CD_CLS,CD,CD_NM,CD_NM2, ROWNUM N  FROM   SYN_PUB_CODE   \n");
            sb.append("  WHERE CD_CLS='J02' \t\t\t\t\t\t\t\t\t\t\t\t\n");
            if (!code.equals("")) {
                sb.append(" AND CD LIKE '" + code + "%'  \n");
            }
            if (!codeName.equals("")) {
                sb.append(" AND CD_NM LIKE '%'||?||'%'   \n");
            }
            sb.append(" ) sub  \n");
            sql = sb.toString();
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            if (!codeName.equals("")) {
                psmt.setString(1, codeName);
            }
            rs = psmt.executeQuery();
            while (rs.next()) {
                count8 = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuA010p.Max_count8([" + code + "],[" + codeName + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuA010p.Max_count8([" + code + "],[" + codeName + "]):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
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
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return count8;
    }
    
    public UM_RAE_ConuA010b[] upjong_list(final int pagenum, final int PAGEMAX, final String code, final String codeName) {
        Trx trx = null;
        Connection con = null;
        UM_RAE_ConuA010b[] ett = (UM_RAE_ConuA010b[])null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final Vector vec = new Vector();
        try {
            sb.append("SELECT\tCD_CLS, CD, CD_NM, CD_NM2, ROWNUM N \t    \t\t    \t\t\n");
            sb.append("\t FROM (\tSELECT      CD_CLS, CD, CD_NM,CD_NM2, ROWNUM N  FROM   SYN_PUB_CODE   \n");
            sb.append("  WHERE CD_CLS='GU8' \t\t\t\t\t\t\t\t\t\t\t\t\n");
            if (!code.equals("")) {
                sb.append(" AND CD LIKE '" + code + "%'  \n");
            }
            if (!codeName.equals("")) {
                sb.append(" AND CD_NM LIKE '%'||?||'%'   \n");
            }
            sb.append(" ) sub  \n");
            sb.append(" WHERE N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")  \n");
            sql = sb.toString();
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            if (!codeName.equals("")) {
                psmt.setString(1, codeName);
            }
            UM_RAE_ConuA010b ett_list = null;
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett_list = new UM_RAE_ConuA010b();
                ett_list.setCodeGubun(rs.getString(1));
                ett_list.setCode(rs.getString(2));
                ett_list.setCodeName(rs.getString(3));
                ett_list.setCodeName2(rs.getString(4));
                vec.addElement(ett_list);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuA010p.upjong_list(3[" + code + "],4[" + codeName + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuA010p.upjong_list(3[" + code + "],4[" + codeName + "]):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
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
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        ett = new UM_RAE_ConuA010b[vec.size()];
        vec.copyInto(ett);
        return ett;
    }
    
    public int Max_count9(final String code, final String codeName) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count9 = 0;
        try {
            sb.append("SELECT\tCOUNT(CD) \t    \t\t    \t\t\n");
            sb.append("\t FROM (\tSELECT      CD_CLS, CD, CD_NM, CD_NM2, ROWNUM N  FROM   SYN_PUB_CODE   \n");
            sb.append("  WHERE CD_CLS='GU8' \t\t\t\t\t\t\t\t\t\t\t\t\n");
            if (!code.equals("")) {
                sb.append(" AND CD LIKE '" + code + "%'  \n");
            }
            if (!codeName.equals("")) {
                sb.append(" AND CD_NM LIKE '%'||?||'%'   \n");
            }
            sb.append(" ) sub  \n");
            sql = sb.toString();
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            if (!codeName.equals("")) {
                psmt.setString(1, codeName);
            }
            rs = psmt.executeQuery();
            while (rs.next()) {
                count9 = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuA010p.Max_count9([" + code + "],[" + codeName + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuA010p.Max_count9([" + code + "],[" + codeName + "]):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
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
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return count9;
    }
    
    public UM_RAE_ConuA010b[] jichung_list(final int pagenum, final int PAGEMAX, final String code, final String codeName) {
        Trx trx = null;
        Connection con = null;
        UM_RAE_ConuA010b[] ett = (UM_RAE_ConuA010b[])null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final Vector vec = new Vector();
        try {
            sb.append(" SELECT\tCD_CLS, CD, CD_NM, CD_NM2,  N  FROM  (    \t\t    \t\t\n");
            sb.append(" SELECT\tCD_CLS, CD, CD_NM, CD_NM2, ROWNUM N \t    \t\t    \t\t\n");
            sb.append(" FROM (\tSELECT      CD_CLS,CD,CD_NM,CD_NM2, ROWNUM N  FROM   SYN_PUB_CODE   \n");
            sb.append("         WHERE CD_CLS='GUJ' AND CD IN('32','38','24','23','21','12','22','00','35','39','33','25','91')  \n");
            if (!code.equals("")) {
                sb.append(" AND CD LIKE '" + code + "%'  \n");
            }
            if (!codeName.equals("")) {
                sb.append(" AND CD_NM LIKE '%'||?||'%'   \n");
            }
            sb.append(" )) sub  \n");
            sb.append(" WHERE N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ") ORDER BY 코드  \n");
            sql = sb.toString();
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            if (!codeName.equals("")) {
                psmt.setString(1, codeName);
            }
            UM_RAE_ConuA010b ett_list = null;
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett_list = new UM_RAE_ConuA010b();
                ett_list.setCodeGubun(rs.getString(1));
                ett_list.setCode(rs.getString(2));
                ett_list.setCodeName(rs.getString(3));
                ett_list.setCodeName2(rs.getString(4));
                vec.addElement(ett_list);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuA010p.jichung_list(3[" + code + "],4[" + codeName + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuA010p.jichung_list(3[" + code + "],4[" + codeName + "]):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
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
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        ett = new UM_RAE_ConuA010b[vec.size()];
        vec.copyInto(ett);
        return ett;
    }
    
    public int Max_count11(final String code, final String codeName) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count11 = 0;
        try {
            sb.append("SELECT\tCOUNT(CD) \t    \t\t    \t\t\n");
            sb.append("\t FROM (\tSELECT      CD_CLS,CD,CD_NM,CD_NM2, ROWNUM N  FROM   SYN_PUB_CODE   \n");
            sb.append("  WHERE CD_CLS='GUJ' AND CD IN('32','38','24','23','21','12','22','00','35','39','33','25','91')\t\n");
            if (!code.equals("")) {
                sb.append(" AND CD LIKE '" + code + "%'  \n");
            }
            if (!codeName.equals("")) {
                sb.append(" AND CD_NM LIKE '%'||?||'%'   \n");
            }
            sb.append(" ) sub  \n");
            sql = sb.toString();
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            if (!codeName.equals("")) {
                psmt.setString(1, codeName);
            }
            rs = psmt.executeQuery();
            while (rs.next()) {
                count11 = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuA010p.Max_count11([" + code + "],[" + codeName + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuA010p.Max_count11([" + code + "],[" + codeName + "]):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
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
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return count11;
    }
    
    public UM_RAE_ConuA010b[] good_list(final int pagenum, final int PAGEMAX, final String code, final String codeName) {
        Trx trx = null;
        Connection con = null;
        UM_RAE_ConuA010b[] ett = (UM_RAE_ConuA010b[])null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final Vector vec = new Vector();
        try {
            sb.append("SELECT\tCATE_ID, CATE_NAME, ROWNUM N \t    \t\t    \t\t                     \n");
            sb.append("\t FROM (\tSELECT     CATE_ID, CATE_NAME, ROWNUM N  FROM  xzcate010m@dbl_usemn_c_mokmn   \n");
            if (!code.equals("") && !codeName.equals("")) {
                sb.append("WHERE CATE_ID LIKE '" + code + "%' AND CATE_NAME LIKE '%" + codeName + "%'   \n");
            }
            if (!codeName.equals("") && code.equals("")) {
                sb.append(" WHERE CATE_NAME LIKE '%" + codeName + "%'   \n");
            }
            if (codeName.equals("") && !code.equals("")) {
                sb.append(" WHERE CATE_ID LIKE '%" + code + "%'   \n");
            }
            sb.append(" ) sub  \n");
            sb.append(" WHERE N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")  \n");
            sql = sb.toString();
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            UM_RAE_ConuA010b ett_list = null;
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett_list = new UM_RAE_ConuA010b();
                ett_list.setCode(rs.getString(1));
                ett_list.setCodeName(rs.getString(2));
                vec.addElement(ett_list);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuA010p.good_list(3[" + code + "],4[" + codeName + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuA010p.good_list(3[" + code + "],4[" + codeName + "]):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
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
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        ett = new UM_RAE_ConuA010b[vec.size()];
        vec.copyInto(ett);
        return ett;
    }
    
    public int Max_count10(final String code, final String codeName) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count10 = 0;
        try {
            sb.append("SELECT\tCOUNT(CATE_ID) \t    \t\t    \t\t\n");
            sb.append("\t FROM (\tSELECT     CATE_ID, CATE_NAME, ROWNUM N  FROM  xzcate010m@dbl_usemn_c_mokmn   \n");
            if (!code.equals("") && !codeName.equals("")) {
                sb.append("WHERE CATE_ID LIKE '" + code + "%' AND CATE_NAME LIKE '%" + codeName + "%'   \n");
            }
            if (!codeName.equals("") && code.equals("")) {
                sb.append(" WHERE CATE_NAME LIKE '%" + codeName + "%'   \n");
            }
            if (codeName.equals("") && !code.equals("")) {
                sb.append(" WHERE CATE_ID LIKE '%" + code + "%'   \n");
            }
            sb.append(" ) sub  \n");
            sql = sb.toString();
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                count10 = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuA010p.Max_count10([" + code + "],[" + codeName + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuA010p.Max_count10([" + code + "],[" + codeName + "]):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
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
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return count10;
    }
}
