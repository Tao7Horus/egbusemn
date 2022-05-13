// 
// Decompiled by Procyon v0.5.30
// 

package left;

import common.Log;
import common.CommDbQuery;
import common.CommEntity;

public class LeftMenuDao
{
    protected String CLF;
    
    public LeftMenuDao() {
        this.CLF = " \n";
    }
    
    public CommEntity[] getMenuQuery(final String s, final String s2) {
        new StringBuffer().append("SELECT 메뉴이름, NVL((SELECT 상위메뉴ID||'-'||메뉴ID||'-'||도움말||'-'||메뉴링크 FROM 포탈_업무_운영메뉴 B\t").append(this.CLF).append(" WHERE B.메뉴ID = A.운영메뉴ID AND B.사용자구분 = A.사용자구분 AND B.사용여부='Y' ),\t\t\t\t\t\t\t").append(this.CLF).append(" '000000-000000--http://www.g2b.go.kr/common/menu_guide.html') 메뉴링크, HIT,\t\t\t\t\t\t\t\t\t").append(this.CLF).append("\t\t 레벨, 메뉴타입, 상위메뉴ID, 메뉴ID\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t").append(this.CLF).append("  FROM 포탈_업무_개인메뉴 A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t").append(this.CLF).append(" START WITH 레벨='1' AND 사용자구분 = ? AND 사용자ID = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t").append(this.CLF).append(" CONNECT BY PRIOR 메뉴ID = 상위메뉴ID\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t").append(this.CLF).append(" AND 사용자구분 = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t").append(this.CLF).append(" AND 사용자ID = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t").append(this.CLF).append(" ORDER SIBLINGS BY TO_NUMBER(HIT)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t").toString();
        final CommEntity[] array = null;
        try {
            final String[] array2 = { s2, s, s2, s };
            final CommDbQuery commDbQuery = new CommDbQuery();
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + " : " + ex.toString());
        }
        return array;
    }
    
    public CommEntity[] getAdminMenuQuery(final String s) {
        new StringBuffer().append("SELECT 메뉴이름, 상위메뉴ID||'-'||메뉴ID||'-'||도움말||'-'||메뉴링크, HIT,\t").append(this.CLF).append("\t 레벨, 메뉴타입, 상위메뉴ID, 메뉴ID\t\t\t\t\t\t\t\t\t\t\t").append(this.CLF).append(" FROM 포탈_업무_운영메뉴\t\t\t\t\t\t\t\t\t\t\t\t\t\t").append(this.CLF).append(" START WITH 레벨='1' AND 사용자구분 = ?  AND 사용여부='Y'\t\t\t\t\t\t").append(this.CLF).append(" CONNECT BY PRIOR 메뉴ID = 상위메뉴ID\t\t\t\t\t\t\t\t\t\t\t").append(this.CLF).append(" AND 사용자구분 = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t").append(this.CLF).append(" AND 사용여부='Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t").append(this.CLF).append(" ORDER SIBLINGS BY TO_NUMBER(HIT)\t\t\t\t\t\t\t\t\t\t\t\t").toString();
        final CommEntity[] array = null;
        try {
            final String[] array2 = { s, s };
            final CommDbQuery commDbQuery = new CommDbQuery();
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + " : " + ex.toString());
        }
        return array;
    }
    
    public CommEntity[] getGroupMenuQuery(final String s, final String s2) {
        new StringBuffer().append("SELECT 메뉴이름, 상위메뉴ID||'-'||메뉴ID||'-'||도움말||'-'||메뉴링크, HIT,\t\t\t\t").append(this.CLF).append("\t 레벨, 메뉴타입, 상위메뉴ID, 메뉴ID, 설명\t\t\t\t\t\t\t\t\t\t\t\t").append(this.CLF).append(" FROM 포탈_업무_운영메뉴\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t").append(this.CLF).append(" START WITH 레벨='1' AND 업무구분 =?  AND 사용자구분 = ? AND 사용여부='Y'\t\t\t\t\t").append(this.CLF).append(" CONNECT BY PRIOR 메뉴ID = 상위메뉴ID\t\t\t\t\t\t\t\t\t\t\t\t\t\t").append(this.CLF).append(" AND 사용자구분=?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t").append(this.CLF).append(" AND 업무구분=?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t").append(this.CLF).append(" AND 사용여부='Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t").append(this.CLF).append(" ORDER SIBLINGS BY TO_NUMBER(HIT)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t").toString();
        final CommEntity[] array = null;
        try {
            final String[] array2 = { s2, s, s, s2 };
            final CommDbQuery commDbQuery = new CommDbQuery();
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + " : " + ex.toString());
        }
        return array;
    }
}
