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

public class UM_KIJ_201d extends AbstractDbWrap
{
    protected final String COMMA = ", ";
    
    public UM_KIJ_201d(final Connection conn) throws SQLException {
        super(conn);
    }
    
    protected String getInsertSQL(final Entity entity) throws SQLException {
        final UM_KIJ_201e um_KIJ_201e = (UM_KIJ_201e)entity;
        if (this.isEntityNull(um_KIJ_201e)) {
            throw new SQLException("UM_KIJ_201e should not null or Some data missing in UM_KIJ_201e");
        }
        return " INSERT INTO syn_공동코드 ( \t코드구분,\t\n\t\t\t\t\t\t\t\t코드, \t\t\n\t\t\t\t\t\t\t\t코드명, \t\n\t\t\t\t\t\t\t\t코드명2,\t\n\t\t\t\t\t\t\t\t사용여부,\t\n\t\t\t\t\t\t\t\t적요,\t\t\n\t\t\t\t\t\t\t\t입력자사번,\t\n\t\t\t\t\t\t\t\t속성코드,\t\n\t\t\t\t\t\t\t\t속성값,\t\t\n\t\t\t\t\t\t\t\t연계여부,\t\n\t\t\t\t\t\t\t\t입력일자\t\n\t\t\t\t\t\t\t) \t\t\t\t\n \t\tVALUES ( \t\t\t\t\t\t\t\n" + this.word(um_KIJ_201e.getCodeGbn()) + ", " + "\n" + this.word(um_KIJ_201e.getCode()) + ", " + "\n" + this.word(um_KIJ_201e.getCodeNm1()) + ", " + "\n" + this.word(um_KIJ_201e.getCodeNm2()) + ", " + "\n" + this.word(um_KIJ_201e.getUseYn()) + ", " + "\n" + this.word(um_KIJ_201e.getBigo()) + ", " + "\n" + this.word(um_KIJ_201e.getRegId()) + ", " + "\n" + this.word(um_KIJ_201e.getAttrCode()) + ", " + "\n" + this.word(um_KIJ_201e.getAttrValue()) + ", " + "\n" + this.word(um_KIJ_201e.getCopYn()) + ", " + "\n" + "SYSDATE)";
    }
    
    protected String getSelectSQL(final Entity entity) throws SQLException {
        final UM_KIJ_201e um_KIJ_201e = (UM_KIJ_201e)entity;
        if (this.isEntityNull(um_KIJ_201e)) {
            throw new SQLException("UM_KIJ_201e should not null or Some data missing in UM_KIJ_201e");
        }
        return " SELECT \t코드구분,\t\t\t\t\n\t\t\t코드,\t\t\t\t\t\n\t\t\t코드명,\t\t\t\t\t\n\t\t\t코드명2,\t\t\t\t\n\t\t\t사용여부,\t\t\t\t\n\t\t\t입력자사번,\t\t\t\t\n\t\t\t입력일자,\t\t\t\t\n\t\t\t적요, \t\t\t\t\t\n\t\t\t속성코드, \t\t\t\t\n\t\t\t속성값, \t\t\t\t\n\t\t\t연계여부 \t\t\t\t\n  FROM \tsyn_공동코드 \t\t\t\n WHERE \t코드구분 = " + this.word(um_KIJ_201e.getCodeGbn()) + "\n" + "   AND    코드\t = " + this.word(um_KIJ_201e.getCode());
    }
    
    protected String getUpdateSQL(final Entity entity) throws SQLException {
        final UM_KIJ_201e um_KIJ_201e = (UM_KIJ_201e)entity;
        if (this.isEntityNull(um_KIJ_201e)) {
            throw new SQLException("UM_KIJ_201e should not null or Some data missing in UM_KIJ_201e");
        }
        final StringBuffer sb = new StringBuffer();
        sb.append(" UPDATE syn_공동코드 SET \t");
        sb.append(" 코드명\t= " + this.word(um_KIJ_201e.getCodeNm1()) + ", " + "\n");
        sb.append(" 코드명2 \t= " + this.word(um_KIJ_201e.getCodeNm2()) + ", " + "\n");
        sb.append(" 사용여부 \t= " + this.word(um_KIJ_201e.getUseYn()) + ", " + "\n");
        sb.append(" 적요\t= " + this.word(um_KIJ_201e.getBigo()) + ", " + "\n");
        sb.append(" 속성코드 \t= " + this.word(um_KIJ_201e.getAttrCode()) + ", " + "\n");
        sb.append(" 수정일자 \t= SYSDATE , \n");
        sb.append(" 속성값 \t= " + this.word(um_KIJ_201e.getAttrValue()) + ", " + "\n");
        sb.append(" 연계여부 \t= " + this.word(um_KIJ_201e.getCopYn()) + "\n");
        sb.append(" WHERE  코드구분 \t= " + this.word(um_KIJ_201e.getCodeGbn()) + "\n");
        sb.append("  AND   코드\t= " + this.word(um_KIJ_201e.getCode()));
        return sb.toString();
    }
    
    protected String getDeleteSQL(final Entity entity) throws SQLException {
        final UM_KIJ_201e um_KIJ_201e = (UM_KIJ_201e)entity;
        if (this.isEntityNull(um_KIJ_201e)) {
            throw new SQLException("UM_KIJ_201e should not null or Some data missing in UM_KIJ_201e");
        }
        String s;
        if (um_KIJ_201e.getCode() == "" || um_KIJ_201e.getCode().equals("")) {
            s = " DELETE syn_공동코드  \n WHERE 코드구분 = " + this.word(um_KIJ_201e.getCodeGbn());
        }
        else {
            s = " DELETE syn_공동코드  \n WHERE \t코드구분 = " + this.word(um_KIJ_201e.getCodeGbn()) + "\n" + "   AND    코드\t = " + this.word(um_KIJ_201e.getCode());
        }
        return s;
    }
    
    protected String getQuerySQL(final String s) throws SQLException {
        String string = " SELECT \t코드구분,\t\t\t\n\t\t\t코드,\t\t\t\t\n\t\t\t코드명,\t\t\t\t\n\t\t\t코드명2,\t\t\t\n\t\t\t사용여부,\t\t\t\n\t\t\t입력자사번,\t\t\t\n\t\t\t입력일자,\t\t\t\n\t\t\t적요,\t\t\t\t\n\t\t\t속성코드,\t\t\t\n\t\t\t속성값,\t\t\t\t\n\t\t\t연계여부\t\t\t\n FROM \tsyn_공동코드 ";
        if (s != null && !s.equals("")) {
            string = string + "\n" + s;
        }
        return string;
    }
    
    protected String getQueryListSQL(final String s, final String s2) throws SQLException {
        return " SELECT 코드구분,\t\t\t\t\n\t \t코드,\t\t\t\t\t\n\t \t코드명,\t\t\t\t\t\n\t \t코드명2,\t\t\t\t\n\t \t사용여부,\t\t\t\t\n\t \t입력자사번,\t\t\t\t\n\t \tto_char(입력일자,'yyyy/mm/dd hh24:mi') 입력일자,\n \t \t적요,\t\t\t\t\t\n \t \t속성코드, \t\t\t\t\n \t \t속성값, \t\t\t\t\n \t \t연계여부, \t\t\t\t\n \t \tRNUM\t \t\t\t\t\n FROM (SELECT 코드구분,\t\t\n\t \t코드,\t\t\t\t\t\n\t \t코드명,\t\t\t\t\t\n\t \t코드명2,\t\t\t\t\n\t \t사용여부,\t\t\t\t\n\t \t입력자사번,\t\t\t\t\n \t \t입력일자, \t\t\t\t\n \t \t적요, \t\t\t\t\t\n \t \t속성코드, \t\t\t\t\n \t \t속성값, \t\t\t\t\n \t \t연계여부,\t \t\t\t\n \t \tROWNUM RNUM\t \t\t\t\n \tFROM (SELECT 코드구분,\t\t\n\t \t\t코드,\t\t\t\t\n\t \t\t코드명,\t\t\t\t\n\t \t\t코드명2,\t\t\t\n\t \t\t사용여부,\t\t\t\n\t \t\t입력자사번,\t\t\t\n\t \t\t입력일자,\t\t\t\n \t \t\t적요, \t\t\t\t\n \t \t\t속성코드, \t\t\t\n \t \t\t속성값, \t\t\t\n \t \t\t연계여부 \t\t\t\n \t\tFROM syn_공동코드\t\t\n" + s + " GROUP BY \t코드구분,\t\t\t" + "\n" + "\t\t코드,\t\t\t\t\t" + "\n" + "\t\t코드명,\t\t\t\t\t" + "\n" + "\t\t코드명2,\t\t\t\t" + "\n" + "\t\t사용여부,\t\t\t\t" + "\n" + "\t\t입력자사번,\t\t\t\t" + "\n" + "\t\t입력일자,\t\t\t\t" + "\n" + " \t \t적요, \t\t\t\t\t" + "\n" + " \t \t속성코드,\t \t\t\t" + "\n" + " \t \t속성값, \t\t\t\t" + "\n" + " \t \t연계여부)) \t\t\t\t" + "\n" + s2;
    }
    
    protected String getQuerySQLForCount(final String s) throws SQLException {
        return " SELECT COUNT(*) FROM syn_공동코드 " + s;
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
        final String string2 = set.getString("코드");
        String string3 = set.getString("코드명");
        String string4 = set.getString("코드명2");
        String string5 = set.getString("사용여부");
        String string6 = set.getString("적요");
        final String string7 = set.getString("입력자사번");
        final String string8 = set.getString("입력일자");
        String string9 = set.getString("속성코드");
        String string10 = set.getString("속성값");
        String string11 = set.getString("연계여부");
        if (string3 == null || string3.equals("")) {
            string3 = "";
        }
        if (string4 == null || string4.equals("")) {
            string4 = "";
        }
        if (string5 == null || string5.equals("")) {
            string5 = "";
        }
        if (string6 == null || string6.equals("")) {
            string6 = "";
        }
        if (string9 == null || string9.equals("")) {
            string9 = "";
        }
        if (string10 == null || string10.equals("")) {
            string10 = "";
        }
        if (string11 == null || string11.equals("")) {
            string11 = "";
        }
        return new UM_KIJ_201e(string, string2, string3, string4, string5, string6, string7, string8, string9, string10, string11);
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
    
    private boolean isEntityNull(final UM_KIJ_201e um_KIJ_201e) {
        boolean b = false;
        if (um_KIJ_201e == null) {
            b = true;
        }
        else if (um_KIJ_201e.getCodeGbn() == null || um_KIJ_201e.getCodeGbn().equals("")) {
            b = true;
        }
        return b;
    }
}
