// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import entity.UM_GOJ_GovuA010b;
import entity.UM_ADE_HistA050b;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import entity.UM_ADV_GovuA030b;

public class UM_URB_GovuA020c
{
    public UM_ADV_GovuA030b select_code(final String g2bCode) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_ADV_GovuA030b ett = null;
        try {
            final String sql = "SELECT INSTITU_FULL_NM, CHRGR_NM, CHRGR_PHONE_NO, ADDR, WEBSITE    FROM UM_PUB_INSTITU_MAST    where INSTITU_CD = ? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, g2bCode);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                ett = new UM_ADV_GovuA030b();
                ett.setgoNameFull(rs.getString(1));
                ett.settaskmaster(rs.getString(2));
                ett.setmasterTel(rs.getString(3));
                ett.setADDR(rs.getString(4));
                ett.sethomepage(rs.getString(5));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.select_code('" + g2bCode + "') : " + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.select_code('" + g2bCode + "') : " + exc.toString());
            throw new Exception(exc.getMessage());
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
        return ett;
    }
    
    public UM_ADV_GovuA030b select_code1(final String recept, final String goNameFull) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_ADV_GovuA030b ett = null;
        try {
            final String sql = " SELECT INSTITU_FULL_NM, CHRGR_NM, CHRGR_PHONE_NO, ADDR, WEBSITE    FROM UM_PUB_INSTITU_MAST    where INSTITU_FULL_NM = ? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, goNameFull);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                ett = new UM_ADV_GovuA030b();
                ett.setgoNameFull(rs.getString(1));
                ett.settaskmaster(rs.getString(2));
                ett.setmasterTel(rs.getString(3));
                ett.setADDR(rs.getString(4));
                ett.sethomepage(rs.getString(5));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.select_code1('" + recept + "','" + goNameFull + "') : " + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.select_code1('" + recept + "','" + goNameFull + "') : " + exc.toString());
            throw new Exception(exc.getMessage());
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
        return ett;
    }
    
    public int Max_count(final String g2bCode, final String goNameFull) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        final StringBuffer sb = new StringBuffer();
        int count = 0;
        try {
            sb.append(" SELECT count(*) FROM UM_PUB_INSTITU_MAST ").append(" WHERE lower(INSTITU_CD) like ?||'%' ");
            if (!goNameFull.equals("")) {
                sb.append(" AND lower(INSTITU_FULL_NM) like '%'||?||'%' ");
            }
            sb.append(" and DELETE_YN='N'").append("");
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sb.toString());
            pstm.setString(1, g2bCode.toLowerCase());
            if (!goNameFull.equals("")) {
                pstm.setString(2, goNameFull.toLowerCase());
            }
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.Max_count('" + g2bCode + "','" + goNameFull + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.Max_count('" + g2bCode + "','" + goNameFull + "'):" + exc.toString());
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
    
    public int Max_count1(final String g2bCode, final String goNameFull) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            final String sql = " SELECT count(*) FROM UM_PUB_INSTITU_MAST  WHERE INSTITU_CD like ?||'%'  AND INSTITU_FULL_NM like '%'||?||'%' ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, g2bCode);
            pstm.setString(2, goNameFull);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.Max_count1('" + g2bCode + "','" + goNameFull + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.Max_count1('" + g2bCode + "','" + goNameFull + "'):" + exc.toString());
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
    
    public UM_ADV_GovuA030b select_goma(final String code) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        UM_ADV_GovuA030b ettall = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = " SELECT INSTITU_CD, INSTITU_FULL_NM, INSTITU_SHORT_NM, INSTITU_EN_NM, BIZ_REG_NO,  INSTITU_CLS, BIZ_CONDITION, BIZ_SALE_CAT, CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_FAX,  CHRGR_EMAIL, ZIP_CD,  ADDR, DETAIL_ADDR, PHONE_NO,  FAX,  WEBSITE,  OLD_INSTITU_CD,  GOODS_MNG_NM, APPROVAL_CD,  LOCAL_INSTITU,SPECIAL_MNG_INSTITU,  INSTITU_LOCALDIVISION,  TRANSPORTDISTANCE, E_AUTHENT_YN,MANAGER_ID, AREA_CD   FROM UM_PUB_INSTITU_MAST   WHERE INSTITU_CD = ? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, code);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettall = new UM_ADV_GovuA030b();
                ettall.setg2bCode(rs.getString(1));
                ettall.setgoNameFull(rs.getString(2));
                ettall.setgoNameShort(rs.getString(3));
                ettall.setgoNameEn(rs.getString(4));
                ettall.setsaupNo(rs.getString(5));
                ettall.setrelation(rs.getString(6));
                ettall.setbuConditon(rs.getString(7));
                ettall.setbuType(rs.getString(8));
                ettall.settaskmaster(rs.getString(9));
                ettall.setmasterPost(rs.getString(10));
                ettall.setmasterTel(rs.getString(11));
                ettall.setmasterFax(rs.getString(12));
                ettall.setmasterMail(rs.getString(13));
                ettall.setZIPCODE(rs.getString(14));
                ettall.setADDR(rs.getString(15));
                ettall.setaddress2(rs.getString(16));
                ettall.settelNum(rs.getString(17));
                ettall.setfaxNum(rs.getString(18));
                ettall.sethomepage(rs.getString(19));
                ettall.setorganCode(rs.getString(20));
                ettall.setgoodsMaster(rs.getString(21));
                ettall.setpermitCode(rs.getString(22));
                ettall.setprovince(rs.getString(23));
                ettall.setspOffice(rs.getString(24));
                ettall.setbranchOffi(rs.getString(25));
                ettall.settrDistance(rs.getString(26));
                ettall.seteCitation(rs.getString(27));
                ettall.setmasterID(rs.getString(28));
                ettall.setlocaCode(rs.getString(29));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.select_goma('" + code + "'):" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.select_goma('" + code + "'):" + exc.toString());
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
        return ettall;
    }
    
    public UM_ADV_GovuA030b[] select_golist5(final int pagenum, final int PAGEMAX, final String g2bCode, final String goNameFull) {
        Connection con = null;
        Trx trx = null;
        UM_ADV_GovuA030b[] ettlist = (UM_ADV_GovuA030b[])null;
        ResultSet rs = null;
        final StringBuffer sqlB = new StringBuffer();
        final String sql = null;
        final Vector vec = new Vector();
        PreparedStatement psmt = null;
        try {
            sqlB.append(" select INSTITU_CD,INSTITU_FULL_NM, CHRGR_PHONE_NO,N , USER_ID").append("\t\tfrom (  ").append("\t\t\tselect a.INSTITU_CD,a.INSTITU_FULL_NM, a.CHRGR_PHONE_NO, ROWNUM N , b.USER_ID  from UM_PUB_INSTITU_MAST a  LEFT JOIN UM_USER b ON a.INSTITU_CD = b.MAST_CD  ").append(" WHERE 1=1 AND  lower(a.INSTITU_CD) LIKE ?||'%'  ");
            if (!goNameFull.equals("")) {
                sqlB.append("\t\t  \t and lower(a.INSTITU_FULL_NM) like '%'||?||'%' ");
            }
            sqlB.append("\t\t\t\t    and a.DELETE_YN = 'N' order by a.INSTITU_CD ASC").append("\t\t \t)").append(" where N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ") ").append("");
            Log.debug("TanNMTest:" + sqlB.toString());
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sqlB.toString());
            psmt.setString(1, g2bCode.toLowerCase());
            if (!goNameFull.equals("")) {
                psmt.setString(2, goNameFull.toLowerCase());
            }
            UM_ADV_GovuA030b et = null;
            rs = psmt.executeQuery();
            while (rs.next()) {
                et = new UM_ADV_GovuA030b();
                et.setg2bCode(rs.getString(1));
                et.setgoNameFull(rs.getString(2));
                et.settelNum(rs.getString(3));
                et.setmasterID(rs.getString(5));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.select_golist('" + Integer.toString(pagenum) + "','" + Integer.toString(PAGEMAX) + "','" + g2bCode + "','" + goNameFull + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.select_golist('" + Integer.toString(pagenum) + "','" + Integer.toString(PAGEMAX) + "','" + g2bCode + "','" + goNameFull + "'):" + exc.toString());
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
        ettlist = new UM_ADV_GovuA030b[vec.size()];
        vec.copyInto(ettlist);
        return ettlist;
    }
    
    public UM_ADV_GovuA030b[] select_golist(final int pagenum, final int PAGEMAX, final String g2bCode, final String goNameFull) {
        Connection con = null;
        Trx trx = null;
        UM_ADV_GovuA030b[] ettlist = (UM_ADV_GovuA030b[])null;
        ResultSet rs = null;
        final StringBuffer sqlB = new StringBuffer();
        final String sql = null;
        final Vector vec = new Vector();
        PreparedStatement psmt = null;
        try {
            sqlB.append(" select INSTITU_CD,INSTITU_FULL_NM, CHRGR_PHONE_NO,N ").append("\t\tfrom (  ").append("\t\t\tselect INSTITU_CD,INSTITU_FULL_NM, CHRGR_PHONE_NO, ROWNUM N from UM_PUB_INSTITU_MAST").append("          WHERE 1=1 AND  lower(INSTITU_CD) LIKE ?||'%'  ");
            if (!goNameFull.equals("")) {
                sqlB.append("\t\t  \t and lower(INSTITU_FULL_NM) like '%'||?||'%' ");
            }
            sqlB.append("\t\t\t\t    and DELETE_YN = 'N' order by INSTITU_CD ASC").append("\t\t \t)").append(" where N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ") ").append("");
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sqlB.toString());
            psmt.setString(1, g2bCode.toLowerCase());
            if (!goNameFull.equals("")) {
                psmt.setString(2, goNameFull.toLowerCase());
            }
            UM_ADV_GovuA030b et = null;
            rs = psmt.executeQuery();
            while (rs.next()) {
                et = new UM_ADV_GovuA030b();
                et.setg2bCode(rs.getString(1));
                et.setgoNameFull(rs.getString(2));
                et.settelNum(rs.getString(3));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.select_golist('" + Integer.toString(pagenum) + "','" + Integer.toString(PAGEMAX) + "','" + g2bCode + "','" + goNameFull + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.select_golist('" + Integer.toString(pagenum) + "','" + Integer.toString(PAGEMAX) + "','" + g2bCode + "','" + goNameFull + "'):" + exc.toString());
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
        ettlist = new UM_ADV_GovuA030b[vec.size()];
        vec.copyInto(ettlist);
        return ettlist;
    }
    
    public UM_ADV_GovuA030b[] select_gopresent(final int pagenum, final int PAGEMAX, final String g2bCode, final String goNameFull) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_ADV_GovuA030b[] ettlist = (UM_ADV_GovuA030b[])null;
        try {
            final String sql = "select INSTITU_CD,INSTITU_FULL_NM, PHONE_NO,N     from (select INSTITU_CD,INSTITU_FULL_NM, PHONE_NO, ROWNUM N from UM_PUB_INSTITU_MAST    where INSTITU_CD like ?||'%' and INSTITU_FULL_NM like '%'||?||'%'     order by INSTITU_CD ASC)     where N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")" + "    order by INSTITU_CD ASC";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, g2bCode);
            pstm.setString(2, goNameFull);
            UM_ADV_GovuA030b et = null;
            rs = pstm.executeQuery();
            while (rs.next()) {
                et = new UM_ADV_GovuA030b();
                et.setg2bCode(rs.getString(1));
                et.setgoNameFull(rs.getString(2));
                et.settelNum(rs.getString(3));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.select_gopresent('" + Integer.toString(pagenum) + "','" + Integer.toString(PAGEMAX) + "','" + g2bCode + "','" + goNameFull + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.select_gopresent('" + Integer.toString(pagenum) + "','" + Integer.toString(PAGEMAX) + "','" + g2bCode + "','" + goNameFull + "'):" + exc.toString());
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
        ettlist = new UM_ADV_GovuA030b[vec.size()];
        vec.copyInto(ettlist);
        return ettlist;
    }
    
    public UM_ADV_GovuA030b select_mody(final String code) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        UM_ADV_GovuA030b ettall = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = " SELECT INSTITU_CD, INSTITU_FULL_NM,BIZ_REG_NO,CHRGR_NM,CHRGR_DEPART,  CHRGR_PHONE_NO,CHRGR_FAX,CHRGR_EMAIL,ADDR,DETAIL_ADDR,LOCAL_INSTITU,GOODS_MNG_NM,WEBSITE,DELETE_YN  from UM_PUB_INSTITU_MAST   where INSTITU_CD = ? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, code);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettall = new UM_ADV_GovuA030b();
                ettall.setg2bCode(rs.getString(1));
                ettall.setgoNameFull(rs.getString(2));
                ettall.setsaupNo(rs.getString(3));
                ettall.settaskmaster(rs.getString(4));
                ettall.setmasterPost(rs.getString(5));
                ettall.setmasterTel(rs.getString(6));
                ettall.setmasterFax(rs.getString(7));
                ettall.setmasterMail(rs.getString(8));
                ettall.setADDR(rs.getString(9));
                ettall.setaddress2(rs.getString(10));
                ettall.setprovince(rs.getString(11));
                ettall.setgoodsMaster(rs.getString(12));
                ettall.sethomepage(rs.getString(13));
                ettall.setdelete(rs.getString(14));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.select_mody('" + code + "'):" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.select_mody('" + code + "'):" + exc.toString());
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
        return ettall;
    }
    
    public UM_ADE_HistA050b select_goruk(final String code, final String date2) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        UM_ADE_HistA050b ettlist = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = " SELECT INSTITU_CD, MOD_DATE, INSTITU_FULL_NM, INSTITU_EN_NM, BIZ_REG_NO, DETAIL_ADDR, MANAGER_ID, SPECIAL_MNG_INSTITU    FROM UM_PUB_INSTITU_HIST   WHERE INSTITU_CD = ? and TO_CHAR(MOD_DATE, 'YYYY-MM-DD HH24:MI:SS') = ? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, code);
            psmt.setString(2, date2);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettlist = new UM_ADE_HistA050b();
                ettlist.setg2bCode(rs.getString(1));
                ettlist.setmoDate(rs.getString(2));
                ettlist.setgoNameFull(rs.getString(3));
                ettlist.setgoNameEn(rs.getString(4));
                ettlist.setsaupNo(rs.getString(5));
                ettlist.setaddress2(rs.getString(6));
                ettlist.setmasterID(rs.getString(7));
                ettlist.setspOffice(rs.getString(8));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.select_goruk('" + code + "','" + date2 + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.select_goruk('" + code + "','" + date2 + "'):" + exc.toString());
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
        return ettlist;
    }
    
    public UM_ADE_HistA050b select_goruk1(final String code, final String date2) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        UM_ADE_HistA050b ettall = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = "select a.INSTITU_CD,   a.INSTITU_FULL_NM, a.INSTITU_SHORT_NM, a.INSTITU_EN_NM, a.BIZ_REG_NO,        a.BIZ_CONDITION,           a.BIZ_SALE_CAT,            a.CHRGR_NM,        a.CHRGR_DEPART,    a.CHRGR_PHONE_NO,        a.CHRGR_FAX, a.CHRGR_EMAIL,  a.CREDITOR_NM,        a.INSTITU_CLS,        a.ZIP_CD,              a.ADDR,           a.DETAIL_ADDR,      a.PHONE_NO,        a.FAX,        a.OLD_INSTITU_CD,        a.GOODS_MNG_NM,   a.APPROVAL_CD,        a.LOCAL_INSTITU,        a.E_AUTHENT_YN,    a.SPECIAL_MNG_INSTITU,          a.INSTITU_LOCALDIVISION,       a.TRANSPORTDISTANCE,        a.WEBSITE,    a.CREATE_DT,        a.MANAGER_ID,              a.DELETE_YN,       a.UPDATE_DT,        a.AREA_CD,        a.INSTITU_TYPE_CLASSIFY,    a.TOP_INSTITU_CD   from UM_PUB_INSTITU_HIST a  where a.INSTITU_CD = ?    and to_char(a.UPDATE_DT, 'YYYY-MM-DD HH24:MI:SS') = ? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, code);
            psmt.setString(2, date2);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettall = new UM_ADE_HistA050b();
                ettall.setg2bCode(rs.getString(1));
                ettall.setgoNameFull(rs.getString(2));
                ettall.setgoNameShort(rs.getString(3));
                ettall.setgoNameEn(rs.getString(4));
                ettall.setsaupNo(rs.getString(5));
                ettall.setbuConditon(rs.getString(6));
                ettall.setbuType(rs.getString(7));
                ettall.settaskmaster(rs.getString(8));
                ettall.setmasterPost(rs.getString(9));
                ettall.setmasterTel(rs.getString(10));
                ettall.setmasterFax(rs.getString(11));
                ettall.setmasterMail(rs.getString(12));
                ettall.setcreditName(rs.getString(13));
                ettall.setrelation(rs.getString(14));
                ettall.setZIPCODE(rs.getString(15));
                ettall.setADDR(rs.getString(16));
                ettall.setaddress2(rs.getString(17));
                ettall.settelNum(rs.getString(18));
                ettall.setfaxNum(rs.getString(19));
                ettall.setgoodsMaster(rs.getString(21));
                ettall.setpermitCode(rs.getString(22));
                ettall.setprovince(rs.getString(23));
                ettall.seteCitation(rs.getString(24));
                ettall.setspOffice(rs.getString(25));
                ettall.setbranchOffi(rs.getString(26));
                ettall.settrDistance(rs.getString(27));
                ettall.sethomepage(rs.getString(28));
                ettall.setmasterID(rs.getString(30));
                ettall.setdelete(rs.getString(31));
                ettall.setmoDate(rs.getString(32));
                ettall.setlocaCode(rs.getString(33));
                ettall.setchgType(rs.getString(34));
                ettall.setsubCode2(rs.getString(35));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.select_goruk1('" + code + "','" + date2 + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.select_goruk1('" + code + "','" + date2 + "'):" + exc.toString());
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
        return ettall;
    }
    
    public UM_ADE_HistA050b select_goruk2(final String code, final String date2) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        UM_ADE_HistA050b ettall = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = "select a.INSTITU_CD,   a.INSTITU_FULL_NM, a.INSTITU_SHORT_NM, a.INSTITU_EN_NM, a.BIZ_REG_NO,        a.INSTITU_CONDITION,           '' BIZ_SALE_CAT,            a.CHRGR_NM,        a.CHRGR_DEPART,    a.CHRGR_PHONE_NO,        a.CHRGR_FAX, a.CHRGR_EMAIL,  a.CREDITOR_NM,        a.INSTITU_CLS,        a.ZIP_CD,              a.ADDR,           a.DETAIL_ADDR,      a.MOBILE,        a.FAX,        a.OLD_INSTITU_CD,        a.GOODS_MNG_NM,   a.APPROVAL_CD,        a.LOCAL_INSTITU,        a.E_AUTHENT_YN,    a.SPECIAL_MNG_INSTITU,          a.LOCAL_INSTITU_DIV,       a.TRANS_DIST,        a.WEBSITE,    a.REG_DT,        a.MANAGER_ID,              a.DELETE_YN,       a.MOD_DT,        a.AREA_CD,        a.INSTITU_TYPE,    a.TOP_INSTITU_CD   from UM_PUB_INSTITU_HIST a  where a.INSTITU_CD = ?    and to_char(a.MOD_DT, 'YYYY-MM-DD HH24:MI:SS') = ? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, code);
            psmt.setString(2, date2);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettall = new UM_ADE_HistA050b();
                ettall.setg2bCode(rs.getString(1));
                ettall.setgoNameFull(rs.getString(2));
                ettall.setgoNameShort(rs.getString(3));
                ettall.setgoNameEn(rs.getString(4));
                ettall.setsaupNo(rs.getString(5));
                ettall.setbuConditon(rs.getString(6));
                ettall.setbuType(rs.getString(7));
                ettall.settaskmaster(rs.getString(8));
                ettall.setmasterPost(rs.getString(9));
                ettall.setmasterTel(rs.getString(10));
                ettall.setmasterFax(rs.getString(11));
                ettall.setmasterMail(rs.getString(12));
                ettall.setcreditName(rs.getString(13));
                ettall.setrelation(rs.getString(14));
                ettall.setZIPCODE(rs.getString(15));
                ettall.setADDR(rs.getString(16));
                ettall.setaddress2(rs.getString(17));
                ettall.settelNum(rs.getString(18));
                ettall.setfaxNum(rs.getString(19));
                ettall.setgoodsMaster(rs.getString(21));
                ettall.setpermitCode(rs.getString(22));
                ettall.setprovince(rs.getString(23));
                ettall.seteCitation(rs.getString(24));
                ettall.setspOffice(rs.getString(25));
                ettall.setbranchOffi(rs.getString(26));
                ettall.settrDistance(rs.getString(27));
                ettall.sethomepage(rs.getString(28));
                ettall.setmasterID(rs.getString(30));
                ettall.setdelete(rs.getString(31));
                ettall.setmoDate(rs.getString(32));
                ettall.setlocaCode(rs.getString(33));
                ettall.setchgType(rs.getString(34));
                ettall.setsubCode2(rs.getString(35));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.select_goruk1('" + code + "','" + date2 + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.select_goruk1('" + code + "','" + date2 + "'):" + exc.toString());
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
        return ettall;
    }
    
    public UM_ADE_HistA050b select_goruk2(final String code) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        UM_ADE_HistA050b ettall = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = "SELECT a.INSTITU_CD,   a.INSTITU_FULL_NM, a.INSTITU_SHORT_NM, a.INSTITU_EN_NM, a.BIZ_REG_NO,        a.INSTITU_CONDITION,           '' BIZ_SALE_CAT,            a.CHRGR_NM,        a.CHRGR_DEPART,    a.CHRGR_PHONE_NO,        a.CHRGR_FAX, a.CHRGR_EMAIL,  a.CREDITOR_NM,        a.INSTITU_CLS,        a.ZIP_CD,              a.ADDR,           a.DETAIL_ADDR,      a.MOBILE,        a.FAX,        a.OLD_INSTITU_CD,        a.GOODS_MNG_NM,   a.APPROVAL_CD,        a.LOCAL_INSTITU,        a.E_AUTHENT_YN,    a.SPECIAL_MNG_INSTITU,          a.LOCAL_INSTITU_DIV,       a.TRANS_DIST,        a.WEBSITE,    a.REG_DT,        a.MANAGER_ID,              a.DELETE_YN,       a.MOD_DT,        a.AREA_CD,        a.INSTITU_TYPE,    a.TOP_INSTITU_CD, a.APPROVE_YN   FROM UM_PUB_INSTITU_HIST a  WHERE a.INSTITU_CD = ?    AND a.VER=(SELECT MAX(VER) FROM UM_PUB_INSTITU_HIST WHERE a.VER=VER AND INSTITU_CD= a.INSTITU_CD ) ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, code);
            rs = psmt.executeQuery();
            while (rs.next()) {
                ettall = new UM_ADE_HistA050b();
                ettall.setg2bCode(rs.getString(1));
                ettall.setgoNameFull(rs.getString(2));
                ettall.setgoNameShort(rs.getString(3));
                ettall.setgoNameEn(rs.getString(4));
                ettall.setsaupNo(rs.getString(5));
                ettall.setbuConditon(rs.getString(6));
                ettall.setbuType(rs.getString(7));
                ettall.settaskmaster(rs.getString(8));
                ettall.setmasterPost(rs.getString(9));
                ettall.setmasterTel(rs.getString(10));
                ettall.setmasterFax(rs.getString(11));
                ettall.setmasterMail(rs.getString(12));
                ettall.setcreditName(rs.getString(13));
                ettall.setrelation(rs.getString(14));
                ettall.setZIPCODE(rs.getString(15));
                ettall.setADDR(rs.getString(16));
                ettall.setaddress2(rs.getString(17));
                ettall.settelNum(rs.getString(18));
                ettall.setfaxNum(rs.getString(19));
                ettall.setgoodsMaster(rs.getString(21));
                ettall.setpermitCode(rs.getString(22));
                ettall.setprovince(rs.getString(23));
                ettall.seteCitation(rs.getString(24));
                ettall.setspOffice(rs.getString(25));
                ettall.setbranchOffi(rs.getString(26));
                ettall.settrDistance(rs.getString(27));
                ettall.sethomepage(rs.getString(28));
                ettall.setmasterID(rs.getString(30));
                ettall.setdelete(rs.getString(31));
                ettall.setmoDate(rs.getString(32));
                ettall.setlocaCode(rs.getString(33));
                ettall.setchgType(rs.getString(34));
                ettall.setsubCode2(rs.getString(35));
                ettall.setApprove_yn(rs.getString(36));
            }
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, "");
        }
        catch (Exception exc) {
            Log.errors(this, exc, "");
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
        return ettall;
    }
    
    public int Max_count2(final String g2bCode, final String goNameFull) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = " SELECT count(*) FROM UM_ADMIN_STD_INFOR  WHERE STD_INSTITU_CD like ?||'%'  AND FULL_NM like '%'||?||'%'  AND STOP_RSON = '0' ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, g2bCode);
            pstm.setString(2, goNameFull);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.Max_count2('" + g2bCode + "','" + goNameFull + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.Max_count2('" + g2bCode + "','" + goNameFull + "'):" + exc.toString());
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
    
    public UM_GOJ_GovuA010b[] select_golist1(final int pagenum, final int PAGEMAX, final String g2bCode, final String goNameFull) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_GOJ_GovuA010b[] ettlist = (UM_GOJ_GovuA010b[])null;
        try {
            final String sql = "select STD_INSTITU_CD,DIRECTOR_POSITION_NM, DIRECTOR_POSITION,VER_NO,RANK,UPPER_INSTITU_CD,TOP_INSTITU_CD, INSTITU_CLS_L,     INSTITU_CLS_M, INSTITU_CLS_S, CREATE_DT, STOP_DT, STOP_RSON, MOD_DATE, MOD_TIME, MOD_CLS, n     from (select STD_INSTITU_CD,DIRECTOR_POSITION_NM, DIRECTOR_POSITION, VER_NO,RANK,UPPER_INSTITU_CD,TOP_INSTITU_CD,     INSTITU_CLS_L, INSTITU_CLS_M, INSTITU_CLS_S, CREATE_DT, STOP_DT, STOP_RSON, MOD_DATE, MOD_TIME, MOD_CLS, ROWNUM N from UM_ADMIN_STD_INFOR    where STD_INSTITU_CD like ?||'%')     where N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, g2bCode);
            UM_GOJ_GovuA010b et = null;
            rs = pstm.executeQuery();
            while (rs.next()) {
                et = new UM_GOJ_GovuA010b();
                et.setg2Code(rs.getString(1));
                et.setposition(rs.getString(2));
                et.setpositionRank(rs.getString(3));
                et.setdisparity(rs.getString(4));
                et.setgrade(rs.getString(5));
                et.setultraCode(rs.getString(6));
                et.setsubCode(rs.getString(7));
                et.setorganL(rs.getString(8));
                et.setorganM(rs.getString(9));
                et.setorganS(rs.getString(10));
                et.setcreateDate(rs.getString(11));
                et.setabDate(rs.getString(12));
                et.setabDivision(rs.getString(13));
                et.setmodiDate(rs.getString(14));
                et.setmoTime(rs.getString(15));
                et.setmoDivision(rs.getString(16));
                et.setg2bCode(rs.getString(17));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.select_golist1('" + Integer.toString(pagenum) + "','" + Integer.toString(PAGEMAX) + "','" + g2bCode + "','" + goNameFull + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.select_golist1('" + Integer.toString(pagenum) + "','" + Integer.toString(PAGEMAX) + "','" + g2bCode + "','" + goNameFull + "'):" + exc.toString());
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
        ettlist = new UM_GOJ_GovuA010b[vec.size()];
        vec.copyInto(ettlist);
        return ettlist;
    }
    
    public UM_GOJ_GovuA010b[] select_golist2(final int pagenum, final int PAGEMAX, final String g2bCode, final String goNameFull) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_GOJ_GovuA010b[] ettlist = (UM_GOJ_GovuA010b[])null;
        try {
            final String sql = " select    a.STD_INSTITU_CD, a.DIRECTOR_POSITION_NM, a.DIRECTOR_POSITION,   a.VER_NO,         a.RANK,         a.UPPER_INSTITU_CD,         a.TOP_INSTITU_CD,   a.INSTITU_CLS_L,  a.INSTITU_CLS_M,  a.INSTITU_CLS_S,  a.CREATE_DT,     a.STOP_DT,     a.STOP_RSON,         a.MOD_DATE,         a.MOD_TIME,     a.MOD_CLS ,a.FULL_NM, a.ZIP_CD,a.RESIDENCE,a.LAND_LOT_NO,a.PHONE_NO,a.FAX, b.INSTITU_CLS, b.INSTITU_CD  from (select STD_INSTITU_CD,DIRECTOR_POSITION_NM, DIRECTOR_POSITION, VER_NO,RANK,UPPER_INSTITU_CD,TOP_INSTITU_CD,               INSTITU_CLS_L, INSTITU_CLS_M, INSTITU_CLS_S, CREATE_DT, STOP_DT, STOP_RSON, MOD_DATE, MOD_TIME,               MOD_CLS, FULL_NM, ZIP_CD, RESIDENCE, LAND_LOT_NO, PHONE_NO, FAX, ROWNUM N from UM_ADMIN_STD_INFOR        where STD_INSTITU_CD like ?||'%' and FULL_NM like '%'||?||'%' and STOP_RSON = '0') a, UM_PUB_INSTITU_MAST b  where a.STD_INSTITU_CD = b.INSTITU_CD(+)  and   N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, g2bCode);
            pstm.setString(2, goNameFull);
            UM_GOJ_GovuA010b et = null;
            rs = pstm.executeQuery();
            while (rs.next()) {
                et = new UM_GOJ_GovuA010b();
                et.setg2Code(rs.getString(1));
                et.setposition(rs.getString(2));
                et.setpositionRank(rs.getString(3));
                et.setdisparity(rs.getString(4));
                et.setgrade(rs.getString(5));
                et.setultraCode(rs.getString(6));
                et.setsubCode(rs.getString(7));
                et.setorganL(rs.getString(8));
                et.setorganM(rs.getString(9));
                et.setorganS(rs.getString(10));
                et.setcreateDate(rs.getString(11));
                et.setabDate(rs.getString(12));
                et.setabDivision(rs.getString(13));
                et.setmodiDate(rs.getString(14));
                et.setmoTime(rs.getString(15));
                et.setmoDivision(rs.getString(16));
                et.setgoNameFull(rs.getString(17));
                et.setZIPCODE(rs.getString(18));
                et.setADDR(rs.getString(19));
                et.setaddress2(rs.getString(20));
                et.settelNum(rs.getString(21));
                et.setfaxNum(rs.getString(22));
                et.setrelation(rs.getString(23));
                et.setg2bCode(rs.getString(24));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.select_golist2('" + Integer.toString(pagenum) + "','" + Integer.toString(PAGEMAX) + "','" + g2bCode + "','" + goNameFull + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.select_golist2('" + Integer.toString(pagenum) + "','" + Integer.toString(PAGEMAX) + "','" + g2bCode + "','" + goNameFull + "'):" + exc.toString());
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
        ettlist = new UM_GOJ_GovuA010b[vec.size()];
        vec.copyInto(ettlist);
        return ettlist;
    }
    
    public UM_ADV_GovuA030b approvalYN(final String recept, final String g2bCode) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        UM_ADV_GovuA030b ett = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            final String sql = "SELECT RECV_NO, INSTITU_CD, INSTITU_FULL_NM, BIZ_REG_NO, CHRGR_NM, ADDR, DETAIL_ADDR, REG_YN, REJECTED_RSON, DEPARTMENT_NO, GROUP_NO FROM UM_REC_PUB_INSTITU_MAST WHERE RECV_NO = ? AND INSTITU_CD = ? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, recept);
            pstm.setString(2, g2bCode);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            if (rs.next()) {
                ett = new UM_ADV_GovuA030b();
                ett.setrecept(rs.getString(1));
                ett.setg2bCode(rs.getString(2));
                ett.setgoNameFull(rs.getString(3));
                ett.setsaupNo(rs.getString(4));
                ett.settaskmaster(rs.getString(5));
                ett.setADDR(rs.getString(6));
                ett.setaddress2(rs.getString(7));
                ett.setenYn(rs.getString(8));
                ett.setback(rs.getString(9));
                ett.setDepartmentNo(rs.getInt(10));
                ett.setGroupNo(rs.getInt(11));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.approvalYN('" + recept + "','" + g2bCode + "'):" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.approvalYN('" + recept + "','" + g2bCode + "'):" + exc.toString());
            throw new Exception(exc.getMessage());
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
        return ett;
    }
    
    public int approval_count(final String recept, final String g2bCode) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "SELECT COUNT(*) FROM UM_REC_PUB_INSTITU_MAST WHERE RECV_NO = ? AND INSTITU_CD = ? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, recept);
            pstm.setString(2, g2bCode);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.approval_count('" + recept + "','" + g2bCode + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.approval_count('" + recept + "','" + g2bCode + "'):" + exc.toString());
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
    
    public UM_GOJ_GovuA010b[] select_golist3(final int pagenum, final int PAGEMAX, final String g2bCode, final String goNameFull) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_GOJ_GovuA010b[] ettlist = (UM_GOJ_GovuA010b[])null;
        try {
            final String sql = " SELECT w.CD, w.NM  FROM (        SELECT q.CD, q.NM, ROWNUM N        FROM (              SELECT a.STD_INSTITU_CD CD, a.FULL_NM NM              FROM (SELECT STD_INSTITU_CD, FULL_NM                    FROM UM_ADMIN_STD_INFOR                    WHERE STD_INSTITU_CD LIKE ?||'%'                      AND FULL_NM LIKE '%'||?||'%'                      AND STOP_RSON = '0'                    ) a,                    UM_PUB_INSTITU_MAST b              WHERE a.STD_INSTITU_CD = b.INSTITU_CD(+)              UNION              SELECT INSTITU_CD CD, INSTITU_FULL_NM NM              FROM UM_PUB_INSTITU_MAST              WHERE INSTITU_CD LIKE ?||'%'                AND INSTITU_FULL_NM LIKE '%'||?||'%'                AND INSTITU_CD like 'Z%'                AND DELETE_YN = 'N'              ) q        ) w  WHERE N BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + ") ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, g2bCode);
            pstm.setString(2, goNameFull);
            pstm.setString(3, g2bCode);
            pstm.setString(4, goNameFull);
            UM_GOJ_GovuA010b et = null;
            rs = pstm.executeQuery();
            while (rs.next()) {
                et = new UM_GOJ_GovuA010b();
                et.setg2Code(rs.getString(1));
                et.setgoNameFull(rs.getString(2));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.select_golist3('" + Integer.toString(pagenum) + "','" + Integer.toString(PAGEMAX) + "','" + g2bCode + "','" + goNameFull + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.select_golist3('" + Integer.toString(pagenum) + "','" + Integer.toString(PAGEMAX) + "','" + g2bCode + "','" + goNameFull + "'):" + exc.toString());
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
        ettlist = new UM_GOJ_GovuA010b[vec.size()];
        vec.copyInto(ettlist);
        return ettlist;
    }
    
    public int Max_count3(final String g2bCode, final String goNameFull) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = " SELECT COUNT(q.CD)  FROM (        SELECT a.STD_INSTITU_CD CD, a.FULL_NM NM        FROM (SELECT STD_INSTITU_CD, FULL_NM               FROM UM_ADMIN_STD_INFOR              WHERE STD_INSTITU_CD LIKE ?||'%'                AND FULL_NM LIKE '%'||?||'%'                AND STOP_RSON = '0'              ) a,              UM_PUB_INSTITU_MAST b        WHERE a.STD_INSTITU_CD = b.INSTITU_CD(+)        UNION        SELECT INSTITU_CD CD, INSTITU_FULL_NM NM        FROM UM_PUB_INSTITU_MAST        WHERE INSTITU_CD LIKE ?||'%'          AND INSTITU_FULL_NM LIKE '%'||?||'%'          AND INSTITU_CD like 'Z%'          AND DELETE_YN = 'N'        ) q ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, g2bCode);
            pstm.setString(2, goNameFull);
            pstm.setString(3, g2bCode);
            pstm.setString(4, goNameFull);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA020c.Max_count3('" + g2bCode + "','" + goNameFull + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA020c.Max_count3('" + g2bCode + "','" + goNameFull + "'):" + exc.toString());
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
}
