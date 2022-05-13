// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import common.util.CommUtil;
import java.util.Vector;
import entity.UM_FOE_CommInfo;

public class Report_Code_05b
{
    public UM_FOE_CommInfo[] getUpchInfo(final UM_FOE_CommInfo um_FOE_CommInfo, final int n, final int n2) {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String string = null;
        String s = "";
        String string2 = "";
        final Vector vector = new Vector<UM_FOE_CommInfo>(1, 1);
        Object[] array = null;
        final CommUtil commUtil = new CommUtil();
        try {
            connection = new Trx(this, "usemn").getConnection();
            if (um_FOE_CommInfo.getString1().length() > 0) {
                s = s + "  and 사업자등록번호 like '" + um_FOE_CommInfo.getString1() + "%'";
                string2 = string2 + "  and 사업자등록번호 like '" + um_FOE_CommInfo.getString1() + "%'";
            }
            if (um_FOE_CommInfo.getString2().length() > 0) {
                s = s + "  and 상호명 like '%" + um_FOE_CommInfo.getString2() + "%'";
            }
            if (um_FOE_CommInfo.getString3().equals("1")) {
                string = " select  사업자등록번호, 상호명                                                 \n        ,( select 대표자명                                                      \n            from 사용_대표자                                                \n           where 사업자등록번호 = a.사업자등록번호                              \n             and 대표대표자여부 = 'Y'                                           \n             and rownum = 1                                                     \n" + string2 + "\n" + "         )                                                                      \n" + "       , to_char(개업일자,'YYYY/MM/DD'), 법인등록번호                           \n" + "       , ( select 면허번호                                                      \n" + "            from 사용_면허사항                                              \n" + "           where 사업자등록번호 = a.사업자등록번호                              \n" + "             and 대표면허여부 = 'Y'                                             \n" + "             and rownum = 1                                                     \n" + string2 + "\n" + "         )                                                                      \n" + "  from ( select 사업자등록번호, 상호명, 개업일자 ,법인등록번호, rownum num      \n" + "           from 사용_조달업체마스터                                         \n" + "            where 1=1                                                           \n" + s + "\n" + "       ) a where a.num >= " + n + "\n" + "             and a.num <=" + n2 + "\n";
            }
            Log.debug("sql================\n" + string);
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
            Log.debug("Report_Code_05b.getUpchInfo(): sql= " + string);
            Log.debug("Report_Code_05b.getUpchInfo(): sqlErrMsg= " + ex.toString());
            Log.debug("Report_Code_05b.getUpchInfo(): sqlErrCode= " + ex.getErrorCode());
        }
        catch (Exception ex2) {
            Log.debug("Report_Code_05b.getUpchInfo(): = " + ex2.toString());
        }
        finally {
            CommUtil.close(executeQuery);
            CommUtil.close(prepareStatement);
            CommUtil.close(connection);
        }
        return (UM_FOE_CommInfo[])array;
    }
    
    public int getCount(final UM_FOE_CommInfo um_FOE_CommInfo) {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String string = null;
        String s = "";
        final CommUtil commUtil = new CommUtil();
        int int1 = 0;
        try {
            connection = new Trx(this, "usemn").getConnection();
            if (um_FOE_CommInfo.getString1().length() > 0) {
                s = s + " and 사업자등록번호 like '" + um_FOE_CommInfo.getString1() + "%'";
            }
            if (um_FOE_CommInfo.getString2().length() > 0) {
                s = s + " and 상호명 like '%" + um_FOE_CommInfo.getString2() + "%'";
            }
            if (um_FOE_CommInfo.getString3().equals("1")) {
                string = "   select count(*)                   \n     from 사용_조달업체마스터    \n    where 1=1                        \n" + s + "\n";
            }
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("Report_Code_05b.getCount(): sql= " + string);
            Log.debug("Report_Code_05b.getCount(): sqlErrMsg= " + ex.toString());
            Log.debug("Report_Code_05b.getCount(): sqlErrCode= " + ex.getErrorCode());
        }
        catch (Exception ex2) {
            Log.debug("Report_Code_05b.getCount(): = " + ex2.toString());
        }
        finally {
            CommUtil.close(executeQuery);
            CommUtil.close(prepareStatement);
            CommUtil.close(connection);
        }
        return int1;
    }
}
