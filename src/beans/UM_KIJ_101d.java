// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.Vector;
import java.sql.ResultSet;
import common.Entity;
import java.sql.SQLException;
import java.sql.Connection;
import common.AbstractDbWrap;

public class UM_KIJ_101d extends AbstractDbWrap
{
    protected final String COMMA = ", ";
    
    public UM_KIJ_101d(final Connection conn) throws SQLException {
        super(conn);
    }
    
    protected String getInsertSQL(final Entity entity) throws SQLException {
        final UM_KIJ_101e um_KIJ_101e = (UM_KIJ_101e)entity;
        if (this.isEntityNull(um_KIJ_101e)) {
            throw new SQLException("UM_KIJ_101e should not null or Some data missing in UM_KIJ_101e");
        }
        return " INSERT INTO syn_공동코드분류 ( \t코드구분,\t\n\t\t\t\t\t코드구분명, \t\n\t\t\t\t\t형태, \t\t\n\t\t\t\t\t길이,\t\t\n\t\t\t\t\t사용여부,\t\n\t\t\t\t\t입력자사번,\t\n\t\t\t\t\t입력일자 ) \t\n VALUES ( \t\t\t\t\t\n" + this.word(um_KIJ_101e.getCodeGbn()) + ", " + "\n" + this.word(um_KIJ_101e.getCodeGbnNm()) + ", " + "\n" + this.word(um_KIJ_101e.getType()) + ", " + "\n" + um_KIJ_101e.getLeng() + ", " + "\n" + this.word(um_KIJ_101e.getUseYn()) + ", " + "\n" + this.word(um_KIJ_101e.getRegId()) + ", " + "\n" + "SYSDATE)";
    }
    
    protected String getSelectSQL(final Entity entity) throws SQLException {
        final UM_KIJ_101e um_KIJ_101e = (UM_KIJ_101e)entity;
        if (this.isEntityNull(um_KIJ_101e)) {
            throw new SQLException("UM_KIJ_101e should not null or Some data missing in UM_KIJ_101e");
        }
        return " SELECT \t코드구분,\t\t\t\t\n\t\t코드구분명,\t\t\t\t\n\t\t형태,\t\t\t\t\t\n\t\t길이,\t\t\t\t\t\n\t\t사용여부,\t\t\t\t\n\t\t입력자사번,\t\t\t\t\n\t\t입력일자 \t\t\t\t\n FROM \tsyn_공동코드분류 \t\t\t\t\n WHERE \t코드구분 = " + this.word(um_KIJ_101e.getCodeGbn());
    }
    
    protected String getUpdateSQL(final Entity entity) throws SQLException {
        final UM_KIJ_101e um_KIJ_101e = (UM_KIJ_101e)entity;
        if (this.isEntityNull(um_KIJ_101e)) {
            throw new SQLException("UM_KIJ_101e should not null or Some data missing in UM_KIJ_101e");
        }
        final StringBuffer sb = new StringBuffer();
        sb.append(" UPDATE syn_공동코드분류 SET ");
        sb.append(" 코드구분명\t= " + this.word(um_KIJ_101e.getCodeGbnNm()) + ", " + "\n");
        sb.append(" 형태 \t= " + this.word(um_KIJ_101e.getType()) + ", " + "\n");
        sb.append(" 길이 \t= " + um_KIJ_101e.getLeng() + ", " + "\n");
        sb.append(" 사용여부\t= " + this.word(um_KIJ_101e.getUseYn()) + ", " + "\n");
        sb.append(" 수정일자\t= SYSDATE \n");
        sb.append(" WHERE \t코드구분 = " + this.word(um_KIJ_101e.getCodeGbn()));
        return sb.toString();
    }
    
    protected String getDeleteSQL(final Entity entity) throws SQLException {
        final UM_KIJ_101e um_KIJ_101e = (UM_KIJ_101e)entity;
        if (this.isEntityNull(um_KIJ_101e)) {
            throw new SQLException("UM_KIJ_101e should not null or Some data missing in UM_KIJ_101e");
        }
        return " DELETE syn_공동코드분류  \n WHERE \t코드구분 = " + this.word(um_KIJ_101e.getCodeGbn());
    }
    
    protected String getQuerySQL(final String s) throws SQLException {
        String string = " SELECT \t코드구분,\t\t\t\n\t\t코드구분명,\t\t\t\n\t\t형태,\t\t\t\t\n\t\t길이,\t\t\t\t\n\t\t사용여부,\t\t\t\n\t\t입력자사번,\t\t\t\n\t\t입력일자\t\t\t\n FROM \tsyn_공동코드분류 ";
        if (s != null && !s.equals("")) {
            string = string + "\n" + s;
        }
        return string;
    }
    
    protected String getQueryListSQL(final String s, final String s2) throws SQLException {
        return " SELECT 코드구분,\t\t\t\t\t\n\t 코드구분명,\t\t\t\t\t\n\t 형태,\t\t\t\t\t\t\n\t 길이,\t\t\t\t\t\t\n\t 사용여부,\t\t\t\t\t\n\t 입력자사번,\t\t\t\t\t\n \t to_char(입력일자,'yyyy/mm/dd hh24:mi') 입력일자,\n \t RNUM\t \t\t\t\t\t\n FROM \t(SELECT 코드구분,\t\t\t\t\n\t \t코드구분명,\t\t\t\t\n\t \t형태,\t\t\t\t\t\n\t \t길이,\t\t\t\t\t\n\t \t사용여부,\t\t\t\t\n\t \t입력자사번,\t\t\t\t\n \t \t입력일자, \t\t\t\t\n \t \tROWNUM RNUM\t \t\t\t\n \tFROM \t(SELECT 코드구분,\t\t\t\n\t \t\t코드구분명,\t\t\t\n\t \t\t형태,\t\t\t\t\n\t \t\t길이,\t\t\t\t\n\t \t\t사용여부,\t\t\t\n\t \t\t입력자사번,\t\t\t\n \t \t\t입력일자 \t\t\t\n \t\tFROM \tsyn_공동코드분류\t\t\n" + s + " GROUP BY \t코드구분,\t\t\t\t" + "\n" + "\t\t코드구분명,\t\t\t\t" + "\n" + "\t\t형태,\t\t\t\t\t" + "\n" + "\t\t길이,\t\t\t\t\t" + "\n" + "\t\t사용여부,\t\t\t\t" + "\n" + "\t\t입력자사번,\t\t\t\t" + "\n" + "\t\t입력일자)) \t\t\t\t" + "\n" + s2;
    }
    
    protected String getQuerySQLForCount(final String s) throws SQLException {
        return " SELECT COUNT(*) FROM syn_공동코드분류 " + s;
    }
    
    protected int parseResultCount(final ResultSet set) throws SQLException {
        int int1 = 0;
        while (set.next()) {
            int1 = set.getInt(1);
        }
        return int1;
    }
    
    protected Entity parseResultSet(final ResultSet set) throws SQLException {
        final String string = set.getString("코드구분");
        String string2 = set.getString("코드구분명");
        String string3 = set.getString("형태");
        String string4 = set.getString("길이");
        String string5 = set.getString("사용여부");
        final String string6 = set.getString("입력자사번");
        final String string7 = set.getString("입력일자");
        if (string2 == null || string2.equals("")) {
            string2 = "";
        }
        if (string3 == null || string3.equals("")) {
            string3 = "";
        }
        if (string4 == null || string4.equals("")) {
            string4 = "";
        }
        if (string5 == null || string5.equals("")) {
            string5 = "";
        }
        return new UM_KIJ_101e(string, string2, string3, string4, string5, string6, string7);
    }
    
    protected Vector parseResultSets(final ResultSet set) throws SQLException {
        final Vector<Entity> vector = new Vector<Entity>(1, 1);
        while (set.next()) {
            vector.addElement(this.parseResultSet(set));
        }
        return vector;
    }
    
    private String word(final String s) {
        return "'" + s + "'";
    }
    
    private String word(final int n) {
        return "" + n;
    }
    
    private boolean isEntityNull(final UM_KIJ_101e um_KIJ_101e) {
        boolean b = false;
        if (um_KIJ_101e == null) {
            b = true;
        }
        else if (um_KIJ_101e.getCodeGbn() == null || um_KIJ_101e.getCodeGbn().equals("")) {
            b = true;
        }
        return b;
    }
}
