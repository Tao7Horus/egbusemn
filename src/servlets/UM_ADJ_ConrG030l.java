// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;

public class UM_ADJ_ConrG030l
{
    int currentMode;
    String saupNo;
    String ceoJuminNo;
    String frmDate;
    public final int MODE_MAIN = 100;
    public final int MODE_CEO = 200;
    public final int MODE_ETC = 300;
    
    public UM_ADJ_ConrG030l() {
        this.saupNo = "";
        this.ceoJuminNo = "";
        this.frmDate = "";
    }
    
    public void setSaupNo(final String saupNo) {
        this.saupNo = saupNo;
    }
    
    public void setCeoJuminNo(final String ceoJuminNo) {
        this.ceoJuminNo = ceoJuminNo;
    }
    
    public void setFrmDate(final String frmDate) {
        this.frmDate = frmDate;
    }
    
    public void setCurrentMode(final int currentMode) {
        this.currentMode = currentMode;
    }
    
    public String[][] getMainData() {
        this.setCurrentMode(100);
        final String[][] mainData = this.getList();
        return mainData;
    }
    
    public String[][] getCeoData() {
        this.setCurrentMode(200);
        final String[][] ceoData = this.getList();
        return ceoData;
    }
    
    public String[][] getEtcData() {
        this.setCurrentMode(300);
        final String[][] etcData = this.getList();
        return etcData;
    }
    
    public String[][] getList() {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String[][] returnList = (String[][])null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            final String query = this.getQuery();
            psmt = this.getPsmt(query, con);
            psmt = this.setQueryCondition(psmt);
            rs = psmt.executeQuery();
            final ResultSetMetaData rsmd = rs.getMetaData();
            returnList = new String[this.getRowCount(rs)][this.getColumnCount(rs)];
            int i = 0;
            while (rs.next()) {
                for (int j = 0; j < this.getColumnCount(rs); ++j) {
                    returnList[i][j] = ((rs.getString(j + 1) == null) ? "" : rs.getString(j + 1));
                }
                ++i;
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADJ_ConrG030l block SQLException : ");
            Log.debug("Exception발생 사유 : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            sqle.printStackTrace();
        }
        catch (Exception exc) {
            Log.debug("UM_ADJ_ConrG030l block Exception : ");
            Log.debug("Exception발생 사유 : " + exc.toString());
            exc.printStackTrace();
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (Exception ex2) {}
            }
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex4) {}
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return returnList;
    }
    
    public String getQuery() {
        String query = "";
        switch (this.currentMode) {
            case 100: {
                query = " SELECT DISTINCT aa.사업자등록번호, aa.공공기관명, aa.담당자전화번호, TO_CHAR(aa.입력일자, 'YYYY-MM-DD'),\t\t                TO_CHAR(aa.제재연월일, 'YYYY-MM-DD'), TO_CHAR(aa.만료연월일, 'YYYY-MM-DD')\t\t\t\t\t\t\t\t\t FROM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      (SELECT 사업자등록번호, 공공기관명, 담당자전화번호, 입력일자, 제재연월일, 만료연월일, 제재기간,\t\t\t\t\t\t\t              제재횟수, 정지구분, 재개구분, 해제구분\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t       FROM 사용_부정당업자\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t       WHERE (정지구분 = 'N' OR (정지구분 = 'Y' AND 재개구분 = 'Y'))\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t         AND 해제구분 = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t         AND TO_CHAR(제재연월일, 'yyyy-mm-dd') <= TO_CHAR(sysdate, 'yyyy-mm-dd')\t\t\t\t\t\t\t\t\t\t\t         AND TO_CHAR(만료연월일, 'yyyy-mm-dd') >= TO_CHAR(sysdate, 'yyyy-mm-dd')\t\t\t\t\t\t\t\t\t\t\t         AND NVL(TO_CHAR(재개일자, 'yyyy-mm-dd'), '1800-01-01') <= TO_CHAR(sysdate, 'yyyy-mm-dd')) aa,\t\t\t      (SELECT a.사업자등록번호, a.제재횟수\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t       FROM 사용_부정당대표자 a ) bb\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t WHERE aa.사업자등록번호 = bb.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t AND bb.사업자등록번호\t= ? \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t AND aa.제재횟수 = bb.제재횟수\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t ORDER BY aa.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                break;
            }
            case 200: {
                query = " SELECT /*+ ordered use_nl(a b c ) */ DISTINCT\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t        b.사업자등록번호, b.공공기관명, b.담당자전화번호, TO_CHAR(b.입력일자, 'YYYY-MM-DD'),\t\t\t\t\t\t\t           TO_CHAR(b.제재연월일, 'YYYY-MM-DD'), TO_CHAR(b.만료연월일, 'YYYY-MM-DD')\t\t\t\t\t\t\t\t\t FROM 사용_부정당대표자 a, 사용_부정당업자 b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t WHERE a.주민등록번호 = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   AND a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   AND a.제재횟수 = b.제재횟수\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   AND (b.정지구분 = 'N' OR (b.정지구분 = 'Y' AND b.재개구분 = 'Y'))\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   AND b.해제구분 = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   AND TO_CHAR(b.제재연월일, 'yyyy-mm-dd') <= TO_CHAR(sysdate, 'yyyy-mm-dd')\t\t\t\t\t\t\t\t\t\t   AND TO_CHAR(b.만료연월일, 'yyyy-mm-dd') >= TO_CHAR(sysdate, 'yyyy-mm-dd')\t\t\t\t\t\t\t\t\t\t   AND NVL(TO_CHAR(b.재개일자, 'yyyy-mm-dd'), '1800-01-01') <= TO_CHAR(sysdate, 'yyyy-mm-dd')\t\t\t ORDER BY b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                break;
            }
            case 300: {
                query = "  SELECT a.사업자등록번호, a.상호명, TO_CHAR(b.발생일자, 'YYYY-MM-DD'),\t\t\t\t\t\t\t\t\t\t\t\t\t              TO_CHAR(b.시작일자, 'YYYY-MM-DD'), TO_CHAR(b.종료일자, 'YYYY-MM-DD')\t\t\t\t\t\t\t\t\t\t  FROM 사용_조달업체마스터 a, 사용_업체상태 b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  WHERE a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND b.상태구분 = '05'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND b.비고 IN (SELECT 비고 FROM 사용_업체상태 WHERE 사업자등록번호 = ? AND 상태구분 = '05')\t\t\t\t\t  ORDER BY a.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                break;
            }
        }
        return query;
    }
    
    public PreparedStatement setQueryCondition(final PreparedStatement psmt) throws SQLException {
        switch (this.currentMode) {
            case 100: {
                psmt.setString(1, this.saupNo);
                break;
            }
            case 200: {
                psmt.setString(1, this.ceoJuminNo);
                break;
            }
            case 300: {
                psmt.setString(1, this.saupNo);
                break;
            }
        }
        return psmt;
    }
    
    public PreparedStatement getPsmt(final String query, final Connection con) throws SQLException {
        PreparedStatement psmt = null;
        psmt = con.prepareStatement(query, 1004, 1007);
        return psmt;
    }
    
    public int getRowCount(final ResultSet rs) throws SQLException {
        int rowNum = 0;
        if (rs == null) {
            return 0;
        }
        if (rs.last()) {
            rowNum = rs.getRow();
        }
        rs.beforeFirst();
        return rowNum;
    }
    
    public int getColumnCount(final ResultSet rs) throws SQLException {
        int columnCount = 0;
        if (rs == null) {
            return 0;
        }
        final ResultSetMetaData rsmd = rs.getMetaData();
        columnCount = rsmd.getColumnCount();
        return columnCount;
    }
}
