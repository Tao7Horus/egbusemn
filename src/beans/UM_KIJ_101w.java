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

public class UM_KIJ_101w
{
    public void addCodeGbn(final HttpServletRequest entityFromRequest) {
        Connection connection = null;
        Trx trx = null;
        final UM_KIJ_101e setEntityFromRequest = this.setEntityFromRequest(entityFromRequest);
        try {
            if (this.DataChk(setEntityFromRequest.getCodeGbn())) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                final UM_KIJ_101e um_KIJ_101e = (UM_KIJ_101e)new UM_KIJ_101d(connection).insert(setEntityFromRequest);
                connection.commit();
                if (um_KIJ_101e != null) {
                    entityFromRequest.setAttribute("codeGbn", (Object)um_KIJ_101e);
                    entityFromRequest.setAttribute("ChkRst", (Object)"ok");
                }
            }
            else {
                entityFromRequest.setAttribute("ChkRst", (Object)"no");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_KIJ_101w:addCodeGbn111() :::: " + ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_KIJ_101w.addCodeGbn block Exception : ");
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
    }
    
    public void updateCodeGbn(final HttpServletRequest entityFromRequest) {
        Connection connection = null;
        Trx trx = null;
        final UM_KIJ_101e setEntityFromRequest = this.setEntityFromRequest(entityFromRequest);
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            final UM_KIJ_101e um_KIJ_101e = (UM_KIJ_101e)new UM_KIJ_101d(connection).update(setEntityFromRequest);
            connection.commit();
            if (um_KIJ_101e != null) {
                entityFromRequest.setAttribute("codeGbn", (Object)um_KIJ_101e);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_KIJ_101w:updateCodeGbn() :::: " + ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_KIJ_101w.updateCodeGbn block Exception : ");
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
    }
    
    public void selectCodeGbn(final HttpServletRequest entityFromRequest) {
        Connection connection = null;
        Trx trx = null;
        System.out.println("selectCodeGbn실행");
        final UM_KIJ_101e setEntityFromRequest = this.setEntityFromRequest(entityFromRequest);
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            final UM_KIJ_101e um_KIJ_101e = (UM_KIJ_101e)new UM_KIJ_101d(connection).select(setEntityFromRequest);
            connection.commit();
            if (um_KIJ_101e != null) {
                entityFromRequest.setAttribute("codeGbn", (Object)um_KIJ_101e);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_KIJ_101w:selectCodeGbn() :::: " + ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_KIJ_101w.selectCodeGbn block Exception : ");
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
    }
    
    public void deleteCodeGbn(final HttpServletRequest entityFromRequest) {
        Connection connection = null;
        Trx trx = null;
        final UM_KIJ_101e setEntityFromRequest = this.setEntityFromRequest(entityFromRequest);
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            new UM_KIJ_101d(connection).delete(setEntityFromRequest);
            connection.commit();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_KIJ_101w:deleteCodeGbn() :::: " + ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_KIJ_101w.deleteCodeGbn block Exception : ");
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
    }
    
    public void selectCodeGbns(final HttpServletRequest entityFromRequest) {
        Connection connection = null;
        Trx trx = null;
        this.setEntityFromRequest(entityFromRequest);
        Vector selectByCondition = null;
        final String where = "";
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            selectByCondition = new UM_KIJ_101d(connection).selectByCondition(where);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_KIJ_101w:selectCodeGbns() :::: " + ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_KIJ_101w.selectCodeGbns block Exception : ");
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
        entityFromRequest.setAttribute("list", (Object)selectByCondition);
    }
    
    private UM_KIJ_101e setEntityFromRequest(final HttpServletRequest httpServletRequest) {
        String parameter = httpServletRequest.getParameter("codeGbn");
        String parameter2 = httpServletRequest.getParameter("codeGbnNm");
        String parameter3 = httpServletRequest.getParameter("type");
        String parameter4 = httpServletRequest.getParameter("leng");
        String parameter5 = httpServletRequest.getParameter("useYn");
        String parameter6 = httpServletRequest.getParameter("regId");
        String parameter7 = httpServletRequest.getParameter("regDate");
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
        return new UM_KIJ_101e(parameter, parameter2, parameter3, parameter4, parameter5, parameter6, parameter7);
    }
    
    private String toHangle(final String s) throws IOException {
        if (s != null) {
            return new String(s.getBytes("8859_1"), "KSC5601");
        }
        return null;
    }
    
    public void selectCodeGbnList(final HttpServletRequest entityFromRequest) {
        Connection connection = null;
        Trx trx = null;
        Log.debug("1111111111111111-------");
        this.setEntityFromRequest(entityFromRequest);
        Object selectByCondition = null;
        Object indexList = null;
        final String parameter = entityFromRequest.getParameter("page");
        int int1;
        if (parameter == null) {
            int1 = 1;
        }
        else {
            int1 = Integer.parseInt(parameter);
        }
        final String parameter2 = entityFromRequest.getParameter("SearchMethod");
        final String parameter3 = entityFromRequest.getParameter("SearchWord");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        String s;
        if (parameter2 == "code" || parameter2.equals("code")) {
            s = " WHERE 코드구분 like '%" + parameter3 + "%'";
        }
        else if (parameter2 == "name" || parameter2.equals("name")) {
            s = " WHERE 코드구분명 like '%" + parameter3 + "%'";
        }
        else {
            s = "";
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            final UM_KIJ_101d um_KIJ_101d = new UM_KIJ_101d(connection);
            final int selectCountByCondition = um_KIJ_101d.selectCountByCondition(s);
            final int[] array = new int[3];
            final int[] rowNums = this.getRowNums(selectCountByCondition, int1);
            final int n = rowNums[0];
            final int n2 = rowNums[1];
            final int total_page = rowNums[2];
            selectByCondition = um_KIJ_101d.selectByCondition(s, "WHERE RNUM BETWEEN " + n + " AND " + n2);
            indexList = new BoardUtil().indexList(int1, total_page, "/servlet/beans.UM_KIJ_101s?actionStr=selectCodeGbnList&SearchMethod=" + parameter2 + "&SearchWord=" + parameter3);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_KIJ_101w:selectCodeGbnList() :::: " + ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_KIJ_101w:selectCodeGbnList() block Exception : ");
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
    
    public boolean DataChk(final String s) {
        Trx trx = null;
        boolean b = false;
        String string = "";
        while (true) {
            if (s != null && !s.equals("")) {
                string = " WHERE 코드구분 = '" + s + "'";
                try {
                    trx = new Trx(this, "usemn");
                    b = (new UM_KIJ_101d(trx.getConnection()).selectByCondition(string).size() == 0);
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("UM_KIJ_101w:DataCheck() :::: " + ex.getMessage());
                }
                catch (Exception ex2) {
                    Log.debug("UM_URV_UserD020p.DataChk block Exception : ");
                    Log.debug("Exception발생 사유 : " + ex2.toString());
                    ex2.printStackTrace();
                }
                finally {
                    if (trx != null) {
                        try {
                            trx.close();
                        }
                        catch (Exception ex3) {}
                    }
                }
                return b;
            }
            continue;
        }
    }
}
