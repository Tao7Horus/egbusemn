// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import common.Log;
import common.ComDbQuery;
import java.util.Vector;
import common.CommEntity;
import java.sql.Connection;

public class UM_Emp_search
{
    public CommEntity[] getEmpList(final String s, final String s2, final String s3, final String s4, final int n, final int n2, final Connection conn) throws Exception {
        final Vector vector = new Vector<String>(1, 1);
        String s5 = " select *  from   ( select rownum t,  소속, 성명 , 직명 , 전화번호, 주요임무            from ( select rownum t, 코드명2||'   '||코드명 as 소속,성명, 직명, 전화번호, 주요임무,  \n                        직급, 직책코드, fax번호,핸드폰,인터넷계정, 코드명, a.입력일자  \n                   from 담당자@DBL_USEMN_P_CUST A,syn_공동코드 b   \n                  where 코드구분='J01'          \n                    and A.부서코드=b.코드       \n                    and 사용여부='Y'            \n                    and 퇴직구분='N'            \n";
        try {
            if (!s.equals("")) {
                s5 += " and 코드명2 like '%'||?||'%' \n";
                vector.addElement(s);
            }
            if (!s2.equals("")) {
                s5 += " and 코드명 like '%'||?||'%' \n";
                vector.addElement(s2);
            }
            if (!s3.equals("")) {
                s5 += " and 주요임무 like '%'||?||'%' \n";
                vector.addElement(s3);
            }
            if (!s4.equals("")) {
                s5 += " and 성명 like '%'||?||'%' \n";
                vector.addElement(s4);
            }
            final String string = s5 + " order by 코드, 직급,직책코드,성명 ) \n" + " where rownum <= ? ) " + " where t between ? and ? ";
            vector.addElement(Integer.toString(n2 * n));
            vector.addElement(Integer.toString(n2 * (n - 1) + 1));
            vector.addElement(Integer.toString(n2 * n));
            final String[] parameter = new String[vector.size()];
            vector.copyInto(parameter);
            return new ComDbQuery().getList(this, "usemn", string, parameter, conn);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".getEmpList() :" + ex.toString());
            throw ex;
        }
    }
    
    public int getEmpCount(final String s, final String s2, final String s3, final String s4, final Connection conn) throws Exception {
        final Vector vector = new Vector<String>(1, 1);
        String sql = " select count(*) \n   from 담당자@DBL_USEMN_P_CUST A,syn_공동코드 b   \n  where 코드구분='J01'          \n    and A.부서코드=b.코드       \n    and 사용여부='Y'            \n    and 퇴직구분='N'            \n";
        try {
            if (!s.equals("")) {
                sql += " and 코드명2 like '%'||?||'%' \n";
                vector.addElement(s);
            }
            if (!s2.equals("")) {
                sql += " and 코드명 like '%'||?||'%' \n";
                vector.addElement(s2);
            }
            if (!s3.equals("")) {
                sql += " and 주요임무 like '%'||?||'%' \n";
                vector.addElement(s3);
            }
            if (!s4.equals("")) {
                sql += " and 성명 like '%'||?||'%' \n";
                vector.addElement(s4);
            }
            final String[] parameter = new String[vector.size()];
            vector.copyInto(parameter);
            return new ComDbQuery().getCount(this, "usemn", sql, parameter, conn);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".getEmpCount() :" + ex.toString());
            throw ex;
        }
    }
}
