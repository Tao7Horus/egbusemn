// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import common.BoardUtil;
import java.io.IOException;
import java.util.Vector;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Entity;
import common.Trx;
import javax.servlet.http.HttpServletRequest;

public class UM_KIJ_201w
{
    public void addCode(final HttpServletRequest entityFromRequest) {
        final UM_KIJ_201e setEntityFromRequest = this.setEntityFromRequest(entityFromRequest);
        Trx trx = null;
        Connection connection = null;
        try {
            if (this.DataChk(setEntityFromRequest.getCodeGbn(), setEntityFromRequest.getCode())) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                final UM_KIJ_201e um_KIJ_201e = (UM_KIJ_201e)new UM_KIJ_201d(connection).insert(setEntityFromRequest);
                connection.commit();
                if (um_KIJ_201e != null) {
                    entityFromRequest.setAttribute("code", (Object)um_KIJ_201e);
                    entityFromRequest.setAttribute("ChkRst", (Object)"ok");
                }
            }
            else {
                entityFromRequest.setAttribute("ChkRst", (Object)"no");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_KIJ_201w:addCode() :::: " + ex.getMessage());
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_KIJ_201w.addCode block Exception : ");
            Log.debug("Exception발생 사유 : " + ex2.toString());
        }
        finally {
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void updateCode(final HttpServletRequest entityFromRequest) {
        final UM_KIJ_201e setEntityFromRequest = this.setEntityFromRequest(entityFromRequest);
        Trx trx = null;
        Connection connection = null;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            connection.setAutoCommit(false);
            final UM_KIJ_201e um_KIJ_201e = (UM_KIJ_201e)new UM_KIJ_201d(connection).update(setEntityFromRequest);
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_KIJ_201w:updateCode() :::: " + ex.getMessage());
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_KIJ_201w.updateCode block Exception : ");
            Log.debug("Exception발생 사유 : " + ex2.toString());
        }
        finally {
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public void selectCode(final HttpServletRequest entityFromRequest) {
        final UM_KIJ_201e setEntityFromRequest = this.setEntityFromRequest(entityFromRequest);
        Trx trx = null;
        Connection connection = null;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            final UM_KIJ_201e um_KIJ_201e = (UM_KIJ_201e)new UM_KIJ_201d(connection).select(setEntityFromRequest);
            connection.commit();
            if (um_KIJ_201e != null) {
                entityFromRequest.setAttribute("code", (Object)um_KIJ_201e);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_KIJ_201w:addCode() :::: " + ex.getMessage());
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_KIJ_201w.addCode block Exception : ");
            Log.debug("Exception발생 사유 : " + ex2.toString());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (Exception ex3) {}
            }
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex4) {}
            }
        }
    }
    
    public void deleteCode(final HttpServletRequest entityFromRequest) {
        final UM_KIJ_201e setEntityFromRequest = this.setEntityFromRequest(entityFromRequest);
        Trx trx = null;
        Connection connection = null;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            new UM_KIJ_201d(connection).delete(setEntityFromRequest);
            connection.commit();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_KIJ_201w:deleteCode() :::: " + ex.getMessage());
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_KIJ_201w.deleteCode block Exception : ");
            Log.debug("Exception발생 사유 : " + ex2.toString());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (Exception ex3) {}
            }
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex4) {}
            }
        }
    }
    
    public void selectCodes(final HttpServletRequest entityFromRequest) {
        this.setEntityFromRequest(entityFromRequest);
        Vector selectByCondition = null;
        final String where = "";
        Trx trx = null;
        Connection connection = null;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            selectByCondition = new UM_KIJ_201d(connection).selectByCondition(where);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_KIJ_201w:selectCodeGbns() :::: " + ex.getMessage());
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_KIJ_201w.selectCodeGbns block Exception : ");
            Log.debug("Exception발생 사유 : " + ex2.toString());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (Exception ex3) {}
            }
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex4) {}
            }
        }
        if (selectByCondition == null) {
            selectByCondition = new Vector(1, 1);
        }
        entityFromRequest.setAttribute("list", (Object)selectByCondition);
    }
    
    private UM_KIJ_201e setEntityFromRequest(final HttpServletRequest httpServletRequest) {
        String parameter = httpServletRequest.getParameter("codeGbn");
        String parameter2 = httpServletRequest.getParameter("code");
        String parameter3 = httpServletRequest.getParameter("codeNm1");
        String parameter4 = httpServletRequest.getParameter("codeNm2");
        String parameter5 = httpServletRequest.getParameter("useYn");
        String parameter6 = httpServletRequest.getParameter("bigo");
        String parameter7 = httpServletRequest.getParameter("regId");
        String parameter8 = httpServletRequest.getParameter("regDate");
        String parameter9 = httpServletRequest.getParameter("attrCode");
        String parameter10 = httpServletRequest.getParameter("attrValue");
        String parameter11 = httpServletRequest.getParameter("copYn");
        if (parameter == null) {
            parameter = "";
        }
        if (parameter2 == null) {
            parameter2 = "";
        }
        if (parameter3 == null) {
            parameter3 = "";
        }
        if (parameter4 == null) {
            parameter4 = "";
        }
        if (parameter5 == null) {
            parameter5 = "";
        }
        if (parameter6 == null) {
            parameter6 = "";
        }
        if (parameter7 == null) {
            parameter7 = "";
        }
        if (parameter8 == null) {
            parameter8 = "";
        }
        if (parameter9 == null) {
            parameter9 = "";
        }
        if (parameter10 == null) {
            parameter10 = "";
        }
        if (parameter11 == null) {
            parameter11 = "";
        }
        return new UM_KIJ_201e(parameter, parameter2, parameter3, parameter4, parameter5, parameter6, parameter7, parameter8, parameter9, parameter10, parameter11);
    }
    
    private String toHangle(final String s) throws IOException {
        if (s != null) {
            return new String(s.getBytes("8859_1"), "KSC5601");
        }
        return null;
    }
    
    public void selectCodeList(final HttpServletRequest entityFromRequest) {
        final UM_KIJ_201e setEntityFromRequest = this.setEntityFromRequest(entityFromRequest);
        Object selectByCondition = null;
        Object indexList = null;
        final String parameter = entityFromRequest.getParameter("page");
        System.out.println("pageNo ==> " + parameter);
        int int1;
        if (parameter == null) {
            int1 = 1;
        }
        else {
            int1 = Integer.parseInt(parameter);
        }
        System.out.println("code.getCodeGbn() ==> " + setEntityFromRequest.getCodeGbn());
        final String string = " WHERE 코드구분 = '" + setEntityFromRequest.getCodeGbn() + "'";
        System.out.println(" where_cond ==> " + string);
        Trx trx = null;
        Connection connection = null;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            final UM_KIJ_201d um_KIJ_201d = new UM_KIJ_201d(connection);
            final int selectCountByCondition = um_KIJ_201d.selectCountByCondition(string);
            final int[] array = new int[3];
            final int[] rowNums = this.getRowNums(selectCountByCondition, int1);
            final int n = rowNums[0];
            final int n2 = rowNums[1];
            final int total_page = rowNums[2];
            final String string2 = "WHERE RNUM BETWEEN " + n + " AND " + n2;
            System.out.println("1.where_rownum ===> " + string2);
            selectByCondition = um_KIJ_201d.selectByCondition(string, string2);
            indexList = new BoardUtil().indexList(int1, total_page, "/servlet/beans.UM_KIJ_201s?actionStr=selectCodeList&codeGbn=" + setEntityFromRequest.getCodeGbn());
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_KIJ_201w:addCode() :::: " + ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_URV_UserD020p.select_userlist block Exception : ");
            Log.debug("Exception발생 사유 : " + ex2.toString());
            ex2.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (Exception ex3) {}
            }
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex4) {}
            }
        }
        if (selectByCondition == null) {
            selectByCondition = new Vector(1, 1);
        }
        entityFromRequest.setAttribute("list", selectByCondition);
        entityFromRequest.setAttribute("index", indexList);
    }
    
    private int[] getRowNums(final int n, final int n2) {
        final int n3 = 10;
        int n4;
        if (n % n3 == 0) {
            n4 = n / n3;
        }
        else {
            n4 = n / n3 + 1;
        }
        int n5;
        if (n2 == 1) {
            n5 = 1;
        }
        else {
            n5 = n2 * n3 - n3 + 1;
        }
        return new int[] { n5, n5 + n3 - 1, n4 };
    }
    
    public boolean DataChk(final String s, final String s2) {
        boolean b = false;
        String string = "";
        if (s != null && !s.equals("")) {
            string = " WHERE 코드구분 = '" + s + "'" + "\n" + "   AND 코드     = '" + s2 + "'";
        }
        Trx trx = null;
        Connection connection = null;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            b = (new UM_KIJ_201d(connection).selectByCondition(string).size() == 0);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("UM_KIJ_201w:DataCheck() :::: " + ex.getMessage());
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_URV_UserD020p.DataChk block Exception : ");
            Log.debug("Exception발생 사유 : " + ex2.toString());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (Exception ex3) {}
            }
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex4) {}
            }
        }
        return b;
    }
}
