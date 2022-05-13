// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import entity.UM_GOJ_GovuA010b;
import java.util.Vector;
import entity.UM_ADE_HistA050b;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;

public class UM_ADB_HistA040p
{
    public int Max_count(final String g2bCode, final String goNameFull) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "\n SELECT count(*) FROM \n (select INSTITU_CD,INSTITU_FULL_NM, max(MOD_DATE) \n     from  UM_PUB_INSTITU_HIST \n     where INSTITU_CD like '" + g2bCode + "%' and INSTITU_FULL_NM like '%" + goNameFull + "%' " + "\n     GROUP BY INSTITU_CD,INSTITU_FULL_NM)" + "\n UM_PUB_INSTITU_HIST " + "\n WHERE INSTITU_CD like '" + g2bCode + "%' " + "\n AND INSTITU_FULL_NM like '%" + goNameFull + "%' ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_HistA040p.Max_count('" + g2bCode + "','" + goNameFull + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_HistA040p.Max_count('" + g2bCode + "','" + goNameFull + "'):" + exc.toString());
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
    
    public UM_ADE_HistA050b[] select_gohist(final int pagenum, final int PAGEMAX, final String g2bCode, final String goNameFull) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_ADE_HistA050b[] etthist = (UM_ADE_HistA050b[])null;
        try {
            final String sql = "select  INSTITU_CD,INSTITU_FULL_NM, BIZ_REG_NO, N \n\tfrom (select INSTITU_CD,INSTITU_FULL_NM, BIZ_REG_NO, ROWNUM N  \n      from( select INSTITU_CD,INSTITU_FULL_NM, max(MOD_DATE),MAX(BIZ_REG_NO) BIZ_REG_NO \n        from  UM_PUB_INSTITU_HIST \n           where INSTITU_CD like '" + g2bCode + "%' and INSTITU_FULL_NM like '%" + goNameFull + "%' " + "\n             GROUP BY INSTITU_CD,INSTITU_FULL_NM )" + "\n     UM_PUB_INSTITU_HIST " + "\n     where INSTITU_CD like '" + g2bCode + "%' and INSTITU_FULL_NM like '%" + goNameFull + "%')" + "\n\twhere N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            UM_ADE_HistA050b et = null;
            rs = pstm.executeQuery();
            while (rs.next()) {
                et = new UM_ADE_HistA050b();
                et.setg2bCode(rs.getString(1));
                et.setgoNameFull(rs.getString(2));
                et.setgoNameEn(rs.getString(3));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_HistA040p.select_gohist(3'" + g2bCode + "',4'" + goNameFull + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_HistA040p.select_gohist(3'" + g2bCode + "',4'" + goNameFull + "'):" + exc.toString());
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
        etthist = new UM_ADE_HistA050b[vec.size()];
        vec.copyInto(etthist);
        return etthist;
    }
    
    public UM_ADE_HistA050b[] select_gosangse(final int pagenum, final int PAGEMAX, final String g2bCode, final String goNameFull) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_ADE_HistA050b[] etthist = (UM_ADE_HistA050b[])null;
        try {
            final String sql = "    select  MOD_DT, MANAGER_ID, N \n\tfrom  (select /*+ index_desc(UM_PUB_INSTITU_HIST PK_UM_PUB_INSTITU_HIST ) */  \n                  MOD_DT,MANAGER_ID,ROWNUM N  \n             from UM_PUB_INSTITU_HIST \n            where INSTITU_CD like '" + g2bCode + "%' " + "\n            order by MOD_DT desc ) " + "\n\twhere N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            UM_ADE_HistA050b et = null;
            rs = pstm.executeQuery();
            while (rs.next()) {
                et = new UM_ADE_HistA050b();
                et.setmoDate(rs.getString(1));
                et.setmasterID(rs.getString(2));
                et.setVer(rs.getString(3));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_HistA040p.select_gosangse(3'" + g2bCode + "',4'" + goNameFull + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_HistA040p.select_gosangse(3'" + g2bCode + "',4'" + goNameFull + "'):" + exc.toString());
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
        etthist = new UM_ADE_HistA050b[vec.size()];
        vec.copyInto(etthist);
        return etthist;
    }
    
    public int Max_number(final String g2bCode, final String goNameFull) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "\n SELECT count(*) FROM \n UM_PUB_INSTITU_HIST \n WHERE INSTITU_CD like '" + g2bCode + "%' ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_HistA040p.Max_number('" + g2bCode + "','" + goNameFull + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_HistA040p.Max_number('" + g2bCode + "','" + goNameFull + "'):" + exc.toString());
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
    
    public UM_GOJ_GovuA010b[] Be_G2BCode(final String g2bCode) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        final Vector vec = new Vector();
        UM_GOJ_GovuA010b[] ettlist = (UM_GOJ_GovuA010b[])null;
        try {
            final String sql = " SELECT BEFORE_CD,         (SELECT INSTITU_FULL_NM FROM UM_PUB_INSTITU_MAST WHERE INSTITU_CD = BEFORE_CD)  FROM UM_PUB_INSTITU_MAST_MOD  WHERE INSTITU_CD = '" + g2bCode + "' " + "   AND HIST_CLS = 'NEW' ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            UM_GOJ_GovuA010b ett = null;
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                ett = new UM_GOJ_GovuA010b();
                ett.setg2bCode(rs.getString(1));
                ett.setgoNameFull(rs.getString(2));
                vec.addElement(ett);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_HistA040p.Be_G2BCode('" + g2bCode + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_HistA040p.Be_G2BCode('" + g2bCode + "'):" + exc.toString());
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
    
    public int Be_G2BCode_Count(final String g2bCode) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = " SELECT COUNT(*) FROM         (SELECT BEFORE_CD,                (SELECT INSTITU_FULL_NM FROM UM_PUB_INSTITU_MAST WHERE INSTITU_CD = BEFORE_CD)         FROM UM_PUB_INSTITU_MAST_MOD         WHERE INSTITU_CD = '" + g2bCode + "' " + "          AND HIST_CLS = 'NEW') ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_HistA040p.Be_G2BCode_Count('" + g2bCode + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_HistA040p.Be_G2BCode_Count('" + g2bCode + "'):" + exc.toString());
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
