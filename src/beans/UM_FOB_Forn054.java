// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import common.util.CommUtil;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import java.util.Vector;
import entity.UM_FOE_CommInfo;

public class UM_FOB_Forn054
{
    public UM_FOE_CommInfo[] getUpchInfo(final UM_FOE_CommInfo um_FOE_CommInfo, final int n, final int n2, final String s) {
        Trx tr = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String string = null;
        String s2 = "";
        final String s3 = "";
        final Vector vector = new Vector<UM_FOE_CommInfo>(1, 1);
        Object[] array = null;
        try {
            tr = new Trx(this, "usemn");
            connection = tr.getConnection();
            final String string2 = um_FOE_CommInfo.getString1();
            if (um_FOE_CommInfo.getString1().length() > 0) {
                s2 = s2 + "  a.사업자등록번호 like '" + string2 + "%'";
                new StringBuffer().append(s3).append("  and a.사업자등록번호 like '").append(string2).append("%'").toString();
            }
            if (um_FOE_CommInfo.getString2().length() > 0) {
                s2 = s2 + "   a.상호명 like '%" + um_FOE_CommInfo.getString2() + "%'";
            }
            if (um_FOE_CommInfo.getString3().equals("1")) {
                string = " select  사업자등록번호, 상호명\t\t\t\t\t\t\t\t\t\t\t\t\n  from ( select a.사업자등록번호, a.상호명, a.개업일자 ,a.법인등록번호, rownum num\t\t\n           from 사용_조달업체마스터 a, 사용_업체구분 b\t\t\t\t\t\t\t\t\t\t\n            where\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" + s2 + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" + "            and 입찰참가자격여부 = 'N'\t\t\t\t\t\t\t\t\t\t\n" + "            and a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\n" + "            and b.업체구분 = '" + s + "'\t\t\t\t\t\t\t\t\t\t\t\n" + "       ) a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" + "  where a.num >= " + n + "\t\t\t\t\t\t\t\t\t\t\t\n" + "       and a.num <=" + n2 + "\t\t\t\t\t\t\t\t\t\t\n";
            }
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                final UM_FOE_CommInfo um_FOE_CommInfo2 = new UM_FOE_CommInfo();
                um_FOE_CommInfo2.setString1(executeQuery.getString(1));
                um_FOE_CommInfo2.setString2(executeQuery.getString(2));
                vector.addElement(um_FOE_CommInfo2);
            }
            array = new UM_FOE_CommInfo[vector.size()];
            vector.copyInto(array);
            vector.removeAllElements();
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn053.getUpchInfo(): sql= " + string);
            Log.debug("UM_FOB_Forn053.getUpchInfo(): sqlErrMsg= " + ex.toString());
            Log.debug("UM_FOB_Forn053.getUpchInfo(): sqlErrCode= " + ex.getErrorCode());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn053.getUpchInfo(): = " + ex2.toString());
        }
        finally {
            CommUtil.close(executeQuery);
            CommUtil.close(prepareStatement);
            CommUtil.close(connection);
            CommUtil.close(tr);
        }
        Log.debug(string);
        return (UM_FOE_CommInfo[])array;
    }
    
    public int getCount(final UM_FOE_CommInfo um_FOE_CommInfo, final String s) {
        Trx tr = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String string = null;
        String s2 = "";
        int int1 = 0;
        try {
            tr = new Trx(this, "usemn");
            connection = tr.getConnection();
            final String string2 = um_FOE_CommInfo.getString1();
            if (um_FOE_CommInfo.getString1().length() > 0) {
                s2 = s2 + "  and a.사업자등록번호 like '" + string2 + "%'";
            }
            if (um_FOE_CommInfo.getString2().length() > 0) {
                s2 = s2 + "  and a.상호명 like '%" + um_FOE_CommInfo.getString2() + "%'";
            }
            if (um_FOE_CommInfo.getString3().equals("1")) {
                string = "   select count(*)\t\t\t\t\t\t\t\t\t\n     from 사용_조달업체마스터\ta, 사용_업체구분 b\t\t\n    where 입찰참가자격여부 = 'N'\t\t\t\t\t\t\n      and a.사업자등록번호 = b.사업자등록번호 \t\t\t\n      and b.업체구분 = '" + s + "'\t\t\t\t\n" + s2 + "\t\t\t\t\n";
            }
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn053.getCount(): sql= " + string);
            Log.debug("UM_FOB_Forn053.getCount(): sqlErrMsg= " + ex.toString());
            Log.debug("UM_FOB_Forn053.getCount(): sqlErrCode= " + ex.getErrorCode());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn053.getCount(): = " + ex2.toString());
        }
        finally {
            CommUtil.close(executeQuery);
            CommUtil.close(prepareStatement);
            CommUtil.close(connection);
            CommUtil.close(tr);
        }
        return int1;
    }
    
    public UM_FOE_CommInfo[] getUpchInfoE(final UM_FOE_CommInfo um_FOE_CommInfo, final int n, final int n2) {
        Trx tr = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String string = null;
        String s = "";
        String string2 = "";
        final Vector vector = new Vector<UM_FOE_CommInfo>(1, 1);
        Object[] array = null;
        try {
            tr = new Trx(this, "usemn");
            connection = tr.getConnection();
            final String string3 = "F" + um_FOE_CommInfo.getString1();
            if (um_FOE_CommInfo.getString1().length() > 0) {
                s = s + "  and 사업자등록번호 like '" + string3 + "%'";
                string2 = string2 + "  and 사업자등록번호 like '" + string3 + "%'";
            }
            if (um_FOE_CommInfo.getString2().length() > 0) {
                s = s + "  and 상호명 like '%" + um_FOE_CommInfo.getString2() + "%'";
            }
            if (um_FOE_CommInfo.getString3().equals("1")) {
                string = " select  사업자등록번호, 상호명                                                 \n        ,( select 대표자명                                                      \n            from 사용_대표자                                                    \n           where 사업자등록번호 = a.사업자등록번호                               \n             and substr(사업자등록번호,1,1) = 'F'                               \n             and 대표대표자여부 = 'Y'                                            \n             and rownum = 1                                                      \n" + string2 + "\n" + "         )                                                                      \n" + "       , to_char(개업일자,'YYYY/MM/DD'), 법인등록번호                           \n" + "       , ( select 면허번호                                                      \n" + "            from 사용_면허사항                                              \n" + "           where 사업자등록번호 = a.사업자등록번호                              \n" + "             and substr(사업자등록번호,1,1) = 'F'                               \n" + "             and 대표면허여부 = 'Y'                                             \n" + "             and rownum = 1                                                     \n" + string2 + "\n" + "         )                                                                      \n" + "  from ( select 사업자등록번호, 상호명, 개업일자 ,법인등록번호, rownum num      \n" + "           from 사용_조달업체마스터이력                                         \n" + "            where 1=1                                                           \n" + "             and substr(사업자등록번호,1,1) = 'F'                               \n" + s + "\n" + "       ) a                                                             \n" + "  where a.num >= " + n + "                                     \n" + "       and a.num <=" + n2 + "\n";
            }
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                final UM_FOE_CommInfo um_FOE_CommInfo2 = new UM_FOE_CommInfo();
                um_FOE_CommInfo2.setString1(executeQuery.getString(1));
                um_FOE_CommInfo2.setString2(executeQuery.getString(2));
                um_FOE_CommInfo2.setString3(executeQuery.getString(3));
                um_FOE_CommInfo2.setString4(executeQuery.getString(4));
                um_FOE_CommInfo2.setString5(executeQuery.getString(5));
                um_FOE_CommInfo2.setString6(executeQuery.getString(6));
                vector.addElement(um_FOE_CommInfo2);
            }
            array = new UM_FOE_CommInfo[vector.size()];
            vector.copyInto(array);
            vector.removeAllElements();
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn053.getUpchInfo(): sql= " + string);
            Log.debug("UM_FOB_Forn053.getUpchInfo(): sqlErrMsg= " + ex.toString());
            Log.debug("UM_FOB_Forn053.getUpchInfo(): sqlErrCode= " + ex.getErrorCode());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn053.getUpchInfo(): = " + ex2.toString());
        }
        finally {
            CommUtil.close(executeQuery);
            CommUtil.close(prepareStatement);
            CommUtil.close(connection);
            CommUtil.close(tr);
        }
        return (UM_FOE_CommInfo[])array;
    }
    
    public int getCountE(final UM_FOE_CommInfo um_FOE_CommInfo) {
        Trx tr = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String string = null;
        String s = "";
        int int1 = 0;
        try {
            tr = new Trx(this, "usemn");
            connection = tr.getConnection();
            final String string2 = "F" + um_FOE_CommInfo.getString1();
            if (um_FOE_CommInfo.getString1().length() > 0) {
                s = s + " and 사업자등록번호 like '" + string2 + "%'";
            }
            if (um_FOE_CommInfo.getString2().length() > 0) {
                s = s + " and 상호명 like '%" + um_FOE_CommInfo.getString2() + "%'";
            }
            if (um_FOE_CommInfo.getString3().equals("1")) {
                string = "   select count(*)                   \n     from 사용_조달업체마스터이력    \n    where 1=1                        \n             and substr(사업자등록번호,1,1) = 'F'                               \n" + s + "\n";
            }
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn053.getCount(): sql= " + string);
            Log.debug("UM_FOB_Forn053.getCount(): sqlErrMsg= " + ex.toString());
            Log.debug("UM_FOB_Forn053.getCount(): sqlErrCode= " + ex.getErrorCode());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn053.getCount(): = " + ex2.toString());
        }
        finally {
            CommUtil.close(executeQuery);
            CommUtil.close(prepareStatement);
            CommUtil.close(connection);
            CommUtil.close(tr);
        }
        return int1;
    }
    
    public UM_FOE_CommInfo[] getUpchInfoB(final UM_FOE_CommInfo um_FOE_CommInfo, final int n, final int n2, final String s) {
        Trx tr = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String string = null;
        String s2 = "";
        final String s3 = "";
        final Vector vector = new Vector<UM_FOE_CommInfo>(1, 1);
        Object[] array = null;
        try {
            tr = new Trx(this, "usemn");
            connection = tr.getConnection();
            final String string2 = um_FOE_CommInfo.getString1();
            if (um_FOE_CommInfo.getString1().length() > 0) {
                s2 = s2 + "  and a.사업자등록번호 like '" + string2 + "%'";
                new StringBuffer().append(s3).append("  and a.사업자등록번호 like '").append(string2).append("%'").toString();
            }
            if (um_FOE_CommInfo.getString2().length() > 0) {
                s2 = s2 + "   and a.상호명 like '%" + um_FOE_CommInfo.getString2() + "%'";
            }
            if (um_FOE_CommInfo.getString3().equals("1")) {
                string = " select  사업자등록번호, 상호명\t\t\t\t\t\t\t\t\t\t\t\t\t\n  from ( select a.사업자등록번호, a.상호명, a.개업일자 ,a.법인등록번호, rownum num\t\n           from 사용_접수조달업체마스터 a, 사용_접수업체구분 b\t\t\t\t\t\t\n          where a.입찰참가자격여부\t= 'N'\t\t\t\t\t\t\t\t\t\t\n            and a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\n            and b.업체구분\t= '" + s + "'\t\t\t\t\t\t\t\t\t\n" + s2 + "\n" + "       ) a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" + "  where a.num >= " + n + "\t\t\t\t\t\t\t\t\t\t\t\t\n" + "       and a.num <=" + n2 + "\n";
            }
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                final UM_FOE_CommInfo um_FOE_CommInfo2 = new UM_FOE_CommInfo();
                um_FOE_CommInfo2.setString1(executeQuery.getString(1));
                um_FOE_CommInfo2.setString2(executeQuery.getString(2));
                vector.addElement(um_FOE_CommInfo2);
            }
            array = new UM_FOE_CommInfo[vector.size()];
            vector.copyInto(array);
            vector.removeAllElements();
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn053.getUpchInfo(): sql= " + string);
            Log.debug("UM_FOB_Forn053.getUpchInfo(): sqlErrMsg= " + ex.toString());
            Log.debug("UM_FOB_Forn053.getUpchInfo(): sqlErrCode= " + ex.getErrorCode());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn053.getUpchInfo(): = " + ex2.toString());
        }
        finally {
            CommUtil.close(executeQuery);
            CommUtil.close(prepareStatement);
            CommUtil.close(connection);
            CommUtil.close(tr);
        }
        return (UM_FOE_CommInfo[])array;
    }
    
    public int getCountB(final UM_FOE_CommInfo um_FOE_CommInfo, final String s) {
        Trx tr = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String string = null;
        String s2 = "";
        int int1 = 0;
        try {
            tr = new Trx(this, "usemn");
            connection = tr.getConnection();
            final String string2 = um_FOE_CommInfo.getString1();
            if (um_FOE_CommInfo.getString1().length() > 0) {
                s2 = s2 + "  and a.사업자등록번호 like '" + string2 + "%'";
            }
            if (um_FOE_CommInfo.getString2().length() > 0) {
                s2 = s2 + "  and a.상호명 like '%" + um_FOE_CommInfo.getString2() + "%'";
            }
            if (um_FOE_CommInfo.getString3().equals("1")) {
                string = "   select count(*)                   \n     from 사용_접수조달업체마스터 a, 사용_접수업체구분 b  \n    where 입찰참가자격여부\t= 'N'\t                    \n      and a.사업자등록번호 =  b.사업자등록번호\t\t\t\t\n      and b.업체구분 = '" + s + "'\t\t\t\t\n" + s2 + "\n";
            }
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn053.getCount(): sql= " + string);
            Log.debug("UM_FOB_Forn053.getCount(): sqlErrMsg= " + ex.toString());
            Log.debug("UM_FOB_Forn053.getCount(): sqlErrCode= " + ex.getErrorCode());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn053.getCount(): = " + ex2.toString());
        }
        finally {
            CommUtil.close(executeQuery);
            CommUtil.close(prepareStatement);
            CommUtil.close(connection);
            CommUtil.close(tr);
        }
        return int1;
    }
}
