// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import entity.UM_URE_UserB020b;

public class UM_URB_UserB030p
{
    public UM_URE_UserB020b select_master(final String s) {
        Trx trx = null;
        Connection connection = null;
        UM_URE_UserB020b um_URE_UserB020b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        try {
            final String string = "select a.BIZ_NM, a.BIZ_CLS, b.REPR_NM, a.BIZ_REG_NO, a.PHONE_NO, a.WEBSITE, decode(ADDR,'*',' ',ADDR),decode(DETAIL_ADDR,'*',' ',DETAIL_ADDR) from UM_SUPPLIER_ENTER_MAST a, UM_REPR b  where a.BIZ_REG_NO='" + s + "' and a.BIZ_REG_NO=b.BIZ_REG_NO and b.MAST_REPR_YN='Y'";
            trx = new Trx(this);
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_URE_UserB020b = new UM_URE_UserB020b();
                um_URE_UserB020b.setCompName(executeQuery.getString(1));
                um_URE_UserB020b.setCompClass(executeQuery.getString(2));
                um_URE_UserB020b.setCompRepresentative(executeQuery.getString(3));
                um_URE_UserB020b.setCompCode(executeQuery.getString(4));
                um_URE_UserB020b.setCompTelephone(executeQuery.getString(5));
                um_URE_UserB020b.setCompHomepage(executeQuery.getString(6));
                um_URE_UserB020b.setCompAddress(executeQuery.getString(7));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_UserB030p.select_master('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_UserB030p.select_master('" + s + "'):" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return um_URE_UserB020b;
    }
    
    public UM_URE_UserB020b select_user(final String s, final String s2) {
        Trx trx = null;
        Connection connection = null;
        UM_URE_UserB020b um_URE_UserB020b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        try {
            final String string = "select USER_ID, USER_CLS, MAST_CD, PASSWORD, CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_MOBILE, CHRGR_FAX, CHRGR_EMAIL, USER_GRP, CON_TYPE, PERMIT_YN,to_char(  FIRST_REG_DT,'DD/MM/YYYY HH24:MI'), MINISTRY_ID, PAY_ID, PAY_SERVER_ID, INSTITU_FUNC_APPLY_YN, MAST_RECV_YN, SWITCH_MNG_AUTHOR  from UM_USER where USER_ID='" + s + "' and USER_CLS='" + s2 + "'";
            trx = new Trx(this);
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_URE_UserB020b = new UM_URE_UserB020b();
                um_URE_UserB020b.setUserId(executeQuery.getString(1));
                um_URE_UserB020b.setUserClass(executeQuery.getString(2));
                um_URE_UserB020b.setMasterCode(executeQuery.getString(3));
                um_URE_UserB020b.setPassword(executeQuery.getString(4));
                um_URE_UserB020b.setUserName(executeQuery.getString(5));
                um_URE_UserB020b.setUserPost(executeQuery.getString(6));
                um_URE_UserB020b.setUserTelephone(executeQuery.getString(7));
                um_URE_UserB020b.setUserMobilePhone(executeQuery.getString(8));
                um_URE_UserB020b.setUserFax(executeQuery.getString(9));
                um_URE_UserB020b.setUserEmail(executeQuery.getString(10));
                um_URE_UserB020b.setUserGroup(executeQuery.getString(11));
                um_URE_UserB020b.setConnectionType(executeQuery.getString(12));
                um_URE_UserB020b.setApproval(executeQuery.getString(13));
                um_URE_UserB020b.setRegDate(executeQuery.getString(14));
                um_URE_UserB020b.setMofeId(executeQuery.getString(15));
                um_URE_UserB020b.setPayId(executeQuery.getString(16));
                um_URE_UserB020b.setPaySystemId(executeQuery.getString(17));
                um_URE_UserB020b.setNationalFunction(executeQuery.getString(18));
                um_URE_UserB020b.setKeySender(executeQuery.getString(19));
                um_URE_UserB020b.setChangeAdminRight(executeQuery.getString(20));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_UserB030p.select_user('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_UserB030p.select_user('" + s + "'):" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return um_URE_UserB020b;
    }
    
    public UM_URE_UserB020b[] select_cuserlist(final String s) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_URE_UserB020b>();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        try {
            final String string = "select a.MAST_CD, b.BIZ_NM, a.USER_ID, a.CHRGR_DEPART, a.CHRGR_NM, a.FIRST_REG_DT from UM_USER a, UM_SUPPLIER_ENTER_MAST b where a.MAST_CD=b.BIZ_REG_NO and a.PERMIT_YN='2' and a.MAST_CD='" + s + "' order by a.USER_ID asc";
            trx = new Trx(this, "USEMN");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_URE_UserB020b um_URE_UserB020b = new UM_URE_UserB020b();
                um_URE_UserB020b.setMasterCode(executeQuery.getString(1));
                um_URE_UserB020b.setCompName(executeQuery.getString(2));
                um_URE_UserB020b.setUserId(executeQuery.getString(3));
                um_URE_UserB020b.setUserPost(executeQuery.getString(4));
                um_URE_UserB020b.setUserName(executeQuery.getString(5));
                um_URE_UserB020b.setRegDate(executeQuery.getString(6));
                vector.addElement(um_URE_UserB020b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_UserB030p.select_cuserlist('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_UserB030p.select_cuserlist('" + s + "'):" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_URE_UserB020b[] array = new UM_URE_UserB020b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public UM_URE_UserB020b[] selectUser(final String s, final int n, final int n2) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_URE_UserB020b>();
        Object[] array = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        sb.append(" select MAST_CD, BIZ_NM, USER_ID,CHRGR_DEPART,CHRGR_NM, to_char(FIRST_REG_DT,'dd/mm/yyyy hh24:mi'), USER_CLS, t ").append("from (").append("\t select MAST_CD, BIZ_NM, USER_ID,CHRGR_DEPART,CHRGR_NM, FIRST_REG_DT, USER_CLS, rownum t").append("\t from (").append("\t \t  select a.MAST_CD, b.BIZ_NM, a.USER_ID,a.CHRGR_DEPART, a.CHRGR_NM, a.FIRST_REG_DT, a.USER_CLS ").append("\t\t  from UM_USER a, UM_SUPPLIER_ENTER_MAST b ").append("\t\t  where a.MAST_CD=b.BIZ_REG_NO ").append("\t\t  and a.PERMIT_YN='2' ").append("\t\t  and a.MAST_CD=?").append("\t\t  order by 5 ").append("\t\t  )").append("\t )").append("where t between " + Integer.toString(n2 * (n - 1) + 1) + " and " + Integer.toString(n2 * n));
        try {
            trx = new Trx(this, "USEMN");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                final UM_URE_UserB020b um_URE_UserB020b = new UM_URE_UserB020b();
                um_URE_UserB020b.setMasterCode(executeQuery.getString(1));
                um_URE_UserB020b.setCompName(executeQuery.getString(2));
                um_URE_UserB020b.setUserId(executeQuery.getString(3));
                um_URE_UserB020b.setUserPost(executeQuery.getString(4));
                um_URE_UserB020b.setUserName(executeQuery.getString(5));
                um_URE_UserB020b.setRegDate(executeQuery.getString(6));
                um_URE_UserB020b.setUserClass(executeQuery.getString(7));
                vector.addElement(um_URE_UserB020b);
            }
            array = new UM_URE_UserB020b[vector.size()];
            vector.copyInto(array);
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_UserB030p.selectUser('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_UserB030p.selectUser('" + s + "'):" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return (UM_URE_UserB020b[])array;
    }
    
    public int Max_count(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        try {
            final String string = "select count(*) from UM_USER where PERMIT_YN='2' and MAST_CD='" + s + "'";
            trx = new Trx(this, "USEMN");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_UserB030p.Max_count('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_UserB030p.Max_count('" + s + "'):" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public String getUserName(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String string = "";
        try {
            final String string2 = "select a.repr_nm from um_rec_pub_institu_cert a, um_cert_info b where b.user_id = '" + s + "' and a.cert_nm = b.cert_nm";
            trx = new Trx(this);
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string2);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_UserB030p.userName('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_UserB030p.userName('" + s + "'):" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return string;
    }
}
