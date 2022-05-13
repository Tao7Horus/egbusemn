// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import javax.servlet.http.HttpServletRequest;
import java.util.Vector;
import entity.UM_ADE_DepartmentE010b;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;

public class UM_ADB_ConrDepartmentDAO
{
    public int max_count(final String s, final String s2) {
        Connection connection = null;
        Trx trx = null;
        ResultSet set = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        try {
            String s3 = "select count(*)  from um_department  where 1=1 ";
            if (s != null && s.length() != 0) {
                s3 = s3 + " and lower(dep_name) = '" + s.toLowerCase().trim() + "'";
            }
            if (!"".equals(s2) && s2 != null) {
                s3 = s3 + " and  lower(dep_description) like '%" + s2.toLowerCase().trim() + "%' ";
            }
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s3);
            set = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            set = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (set.next()) {
                int1 = set.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_ConrDepartmentDAO.Max_count block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_ConrDepartmentDAO.Max_count block Exception : " + ex2.toString());
        }
        finally {
            if (set != null) {
                try {
                    set.close();
                }
                catch (Exception ex4) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex5) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {
                    ex3.printStackTrace();
                }
            }
        }
        return int1;
    }
    
    public UM_ADE_DepartmentE010b[] getResultList(final int n, final int n2, final String s, final String s2) {
        Connection connection = null;
        Trx trx = null;
        ResultSet set = null;
        PreparedStatement prepareStatement = null;
        final Vector vector = new Vector<UM_ADE_DepartmentE010b>();
        final int n3 = (n - 1) * n2 + 1;
        final int n4 = n * n2;
        try {
            String s3 = " select DEPARTMENT_NO, DEP_NAME, DEP_DESCRIPTION  from (select DEPARTMENT_NO, DEP_NAME, DEP_DESCRIPTION, ROWNUM N from UM_DEPARTMENT  where 1=1 ";
            if (s != null && s.length() != 0) {
                s3 = s3 + " and lower(DEP_NAME) like '%" + s.toLowerCase().trim() + "%'";
            }
            if (!"".equals(s2) && s2 != null) {
                s3 = s3 + " and lower(DEP_DESCRIPTION) like '%" + s2.toLowerCase().trim() + "%' ";
            }
            final String string = s3 + " ) t3 where t3.N between " + n3 + " and " + n4;
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            set = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            set = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (set.next()) {
                final UM_ADE_DepartmentE010b um_ADE_DepartmentE010b = new UM_ADE_DepartmentE010b();
                um_ADE_DepartmentE010b.setId(Integer.parseInt(set.getString(1)));
                um_ADE_DepartmentE010b.setName(set.getString(2));
                um_ADE_DepartmentE010b.setDescription(set.getString(3));
                vector.addElement(um_ADE_DepartmentE010b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_ConrDepartmentDAO.Max_count block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_ConrDepartmentDAO.Max_count block Exception : " + ex2.toString());
        }
        finally {
            if (set != null) {
                try {
                    set.close();
                }
                catch (Exception ex4) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex5) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {
                    ex3.printStackTrace();
                }
            }
        }
        if (0 < vector.size()) {
            final UM_ADE_DepartmentE010b[] array = new UM_ADE_DepartmentE010b[vector.size()];
            vector.copyInto(array);
            return array;
        }
        return null;
    }
    
    public void insert(final String s, final String s2, final HttpServletRequest httpServletRequest, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        String string = " insert into UM_DEPARTMENT (DEPARTMENT_NO, DEP_NAME, DEP_DESCRIPTION) \n";
        if (!"".equals(s)) {
            string = string + " values (seq_department.nextval, '" + s.trim() + "', '" + s2.trim() + "') \n";
        }
        try {
            prepareStatement = connection.prepareStatement(string);
            prepareStatement.executeUpdate();
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void update(final int n, final String s, final String s2, final HttpServletRequest httpServletRequest, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String string = " update um_department set DEP_NAME = '" + s.trim() + "', DEP_DESCRIPTION = '" + s2.trim() + "' where DEPARTMENT_NO = " + n;
        try {
            prepareStatement = connection.prepareStatement(string);
            prepareStatement.executeUpdate();
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void delete(final String s, final HttpServletRequest httpServletRequest, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final int int1 = Integer.parseInt(s);
        String string = "";
        if (!"".equals(s) && s != null) {
            string = string + " delete from um_department where DEPARTMENT_NO = " + int1 + " \n";
        }
        try {
            prepareStatement = connection.prepareStatement(string);
            prepareStatement.executeUpdate();
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex) {}
        }
    }
}
