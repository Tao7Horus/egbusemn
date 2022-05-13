// 
// Decompiled by Procyon v0.5.30
// 

package exms.upload;

import common.Log;
import java.sql.ResultSet;
import java.util.Vector;
import java.sql.SQLException;
import servlets.UM_GOV_ConiA021c;
import java.sql.PreparedStatement;
import common.Trx;
import java.sql.Connection;

public class RcvDfisan1
{
    private Connection pconn;
    Trx trx;
    PreparedStatement psmt;
    private long serialNumber;
    private String sourceName;
    private int repeatCount;
    private String bizRegNo;
    UM_GOV_ConiA021c um_gov_conia021c;
    
    public RcvDfisan1() {
        this.pconn = null;
        this.psmt = null;
        this.serialNumber = 0L;
        this.sourceName = "RcvDfisan";
        this.repeatCount = 0;
        this.bizRegNo = "";
        this.um_gov_conia021c = new UM_GOV_ConiA021c();
        try {
            this.pconn = new Trx(this, "usemn").getConnection();
        }
        catch (Exception ex) {
            try {
                this.pconn.rollback();
            }
            catch (Exception ex2) {}
        }
        this.serialNumber = System.currentTimeMillis();
    }
    
    public String main_process(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final String s12, final String s13, final String s14, final String s15, final String s16, final String s17, final String s18, final String s19, final String s20, final String s21) {
        try {
            this.SaveLog("main_process start");
            this.pconn.setAutoCommit(false);
            this.SaveLog(" ## updateMaster1 start ");
            this.updateMaster1(this.pconn, s2, s8, s6, s7, s9, s10, s20, s11, s15, s16, s17, s18, s19, s, s8, s3, s, s21);
            this.SaveLog(" ## updateMaster1  end");
            this.SaveLog(" ## updateDaepyo start ");
            this.updateDaepyo(this.pconn, s13, s12, s14);
            this.SaveLog(" ## updateDaepyo end");
            final String saupjaNumber = this.getSaupjaNumber(this.pconn, s13);
            this.SaveLog(" ## 대표자가 같은 다른 본사 ");
            this.updateMaster2(this.pconn, saupjaNumber, s16, s17, s);
            final String brSaupjaNumber = this.getBRSaupjaNumber(this.pconn);
            this.SaveLog(" ## 본사의 지사 ");
            this.updateMaster2(this.pconn, brSaupjaNumber, s16, s17, s);
            final String ceobrSaupjaNumber = this.getCEOBRSaupjaNumber(this.pconn, s13);
            this.SaveLog(" ## 대표자가 같은 다른 본사의 지사 ");
            this.updateMaster2(this.pconn, ceobrSaupjaNumber, s16, s17, s);
            if (this.pconn != null) {
                try {
                    this.pconn.commit();
                }
                catch (Exception ex3) {}
            }
            if (this.pconn != null) {
                try {
                    this.pconn.setAutoCommit(true);
                }
                catch (Exception ex4) {}
            }
            this.SaveLog("## main process end");
            return "true";
        }
        catch (SQLException ex) {
            if (this.pconn != null) {
                try {
                    this.pconn.rollback();
                }
                catch (Exception ex5) {}
            }
            if (this.pconn != null) {
                try {
                    this.pconn.setAutoCommit(true);
                }
                catch (Exception ex6) {}
            }
            this.SaveLog("SQLException : " + ex.toString());
            if (ex.getErrorCode() == 1) {
                return "-2[RcvDfisan - 부정당업체등록통보서]oracle error 0001";
            }
            return ex.getErrorCode() + "[RcvDfisan - 부정당업체등록통보서]oracle error " + ex.getErrorCode() + ":" + ex.toString();
        }
        catch (Exception ex2) {
            if (this.pconn != null) {
                try {
                    this.pconn.rollback();
                }
                catch (Exception ex7) {}
            }
            if (this.pconn != null) {
                try {
                    this.pconn.setAutoCommit(true);
                }
                catch (Exception ex8) {}
            }
            this.SaveLog("Exception : " + ex2.toString());
            return "-2[RcvDfisan - 부정당업체등록통보서]DB Update Error" + ex2.getMessage();
        }
    }
    
    private String getSaupjaNumber(final Connection connection, final String s) throws Exception {
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String string = "";
        final Vector<String> vector = new Vector<String>(1, 1);
        final String s2 = "SELECT BIZ_REG_NO FROM UM_REPR WHERE REPR_IDENT_NO=?";
        try {
            prepareStatement = connection.prepareStatement(s2);
            vector.addElement(this.bizRegNo);
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                if (!executeQuery.getString(1).equals(this.bizRegNo)) {
                    string = executeQuery.getString(1);
                }
            }
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex2) {}
        }
        return string;
    }
    
    private String getBRSaupjaNumber(final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String string = "";
        final String s = "  SELECT BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t  FROM  UM_SUPPLIER_ENTER_MAST\t\t\t\t\t\t\t\t\t\t\t\t\t  WHERE CORP_REG_NO IN (  SELECT CORP_REG_NO\t\t\t\t\t\t                                        FROM UM_SUPPLIER_ENTER_MAST\t\t\t\t                                        WHERE BIZ_REG_NO  = ?)\t\t\t";
        try {
            prepareStatement = connection.prepareStatement(s);
            prepareStatement.setString(1, this.bizRegNo);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                if (!executeQuery.getString(1).equals(this.bizRegNo)) {
                    string = executeQuery.getString(1);
                }
            }
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex2) {}
        }
        return string;
    }
    
    private String getCEOBRSaupjaNumber(final Connection connection, final String s) throws Exception {
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String string = "";
        final Vector<String> vector = new Vector<String>(1, 1);
        final String s2 = "\t\tSELECT BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tFROM  UM_SUPPLIER_ENTER_MAST\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE CORP_REG_NO IN (  SELECT CORP_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  FROM UM_SUPPLIER_ENTER_MAST x, UM_REPR y        \t\t\t\t\t\t\t\t\t\t\t  WHERE x.BIZ_REG_NO = y.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t  AND  y.REPR_IDENT_NO=?)\t\t\t\t\t\t\t\t\t";
        try {
            prepareStatement = connection.prepareStatement(s2);
            vector.addElement(this.bizRegNo);
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                if (!executeQuery.getString(1).equals(this.bizRegNo)) {
                    string = executeQuery.getString(1);
                }
            }
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex2) {}
        }
        return string;
    }
    
    private void updateMaster1(final Connection connection, final String s, final String bizRegNo, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final String s12, final String s13, final String s14, final String s15, final String s16, final String s17) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet executeQuery = null;
        try {
            final String string = " select nvl(max(PUNISH_COUNT),0)+1 from UM_VIOLATE_COM where BIZ_REG_NO = '" + bizRegNo + "'";
            this.bizRegNo = bizRegNo;
            preparedStatement = connection.prepareStatement(string);
            executeQuery = preparedStatement.executeQuery();
            if (executeQuery.next()) {
                this.repeatCount = executeQuery.getInt(1);
            }
            this.SaveLog("BIZ_REG_NO[" + this.bizRegNo + "], PUNISH_COUNT[" + this.repeatCount + "]");
            final String string2 = " INSERT INTO UM_VIOLATE_COM (  BIZ_REG_NO,\n INPUT_DT,\n DOC_NO, \n CHRG_NM, \n CHRG_PHONE_NO, \n BIZ_NM,  ADDR, CONTR_TYPE,\n BIZ_CAT,\n PUNISH_BASIS1,  PUNISH_DT,\t\n EXPIRE_DT,\n PUNISH_PERIOD,\n CANCEL_DT, \n PUNISH_COUNT,\n INPUT_UM_ID,\n INSTITU_NM, INSTITU_CD,\n SUSPEND_CLS, NOTE,\t\t\n RESUMPTION_CLS,\n RELEASE_CLS                                        \t\t\n ) VALUES \n('" + bizRegNo + "', " + "sysdate,  " + "'" + s + "', " + "'" + s2 + "', " + "'" + s3 + "', \n" + "'" + s4 + "', " + "'" + s5 + "', " + "'" + s6 + "', " + "'" + s7 + "', " + "'" + s8 + "', " + "to_date('" + s9 + "','DDMMYYYY'), \n" + "to_date('" + s10 + "','DDMMYYYY'), " + "'" + s11 + "', " + "to_date('" + s12 + "','DDMMYYYY'), " + "" + Integer.toString(this.repeatCount) + ", \n" + "'" + s13 + "', " + "'" + s15 + "', " + "'" + s16 + "', " + "'N', " + "'" + s17 + "', " + "'N', " + "'N')";
            Log.debug(string2);
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (Exception ex2) {}
            }
            preparedStatement = connection.prepareStatement(string2);
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("[RcvDfisan-부정당업체등록통보서]DB UPDATE 에러가 발생했습니다");
            }
            this.SaveLog("UM_VIOLATE_COM 입력 : BIZ_REG_NO[" + bizRegNo + "]");
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
            catch (Exception ex4) {}
        }
    }
    
    private void updateDaepyo(final Connection connection, final String s, final String s2, final String s3) throws Exception {
        PreparedStatement prepareStatement = null;
        try {
            prepareStatement = connection.prepareStatement(" INSERT INTO UM_VIOLATE_REPR ( BIZ_REG_NO, PUNISH_COUNT,  SERIAL_NO,  INPUT_DT,  IDENT_NO,  REPR_NM,  REPR_ADDR ) VALUES (?,?,SERIAL_NO.nextval,sysdate,?,?,?)");
            prepareStatement.setString(1, this.bizRegNo);
            prepareStatement.setString(2, Integer.toString(this.repeatCount));
            prepareStatement.setString(3, s);
            prepareStatement.setString(4, s2);
            prepareStatement.setString(5, s3);
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("[RcvDfisan-UM_VIOLATE_REPR] DB UPDATE 에러가 발생했습니다");
            }
            this.SaveLog("UM_VIOLATE_REPR 입력 : IDENT_NO[]insert 완료");
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
    
    private void updateMaster2(final Connection connection, final String s, final String s2, final String s3, final String s4) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        int int1 = 0;
        String string = "";
        String string2 = "";
        String string3 = "";
        try {
            preparedStatement = connection.prepareStatement(" SELECT count(*) FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO ='" + s + "'");
            set = preparedStatement.executeQuery();
            if (set.next() && set.getInt(1) == 0) {
                return;
            }
            final String string4 = " SELECT COUNT(*) aa FROM UM_ENTER_STATE WHERE BIZ_REG_NO='" + s + "' and STATE_CLS='05'";
            if (set != null) {
                try {
                    set.close();
                }
                catch (Exception ex) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (Exception ex2) {}
            }
            preparedStatement = connection.prepareStatement(string4);
            set = preparedStatement.executeQuery();
            if (set.next()) {
                int1 = set.getInt(1);
            }
            Log.debug("" + int1);
            if (int1 == 0) {
                if (set != null) {
                    try {
                        set.close();
                    }
                    catch (Exception ex3) {}
                }
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    }
                    catch (Exception ex4) {}
                }
                preparedStatement = connection.prepareStatement(" SELECT TO_CHAR(sysdate, 'yyyymmdd') FROM dual ");
                set = preparedStatement.executeQuery();
                if (set.next()) {
                    string = set.getString(1);
                }
                Log.debug(s2);
                if (Integer.parseInt(s2) <= Integer.parseInt(string)) {
                    Log.debug("SaupNo: " + s + "startDate: " + s2 + "endDate: " + s3);
                    final String string5 = " INSERT INTO UM_ENTER_STATE (  BIZ_REG_NO, RAISED_DT, START_DT, END_DT, STATE_CLS, REMARK, MANAGER_ID, PROCESS_DT ) VALUES ( '" + s + "', " + "sysdate," + "'" + s2 + "'," + "'" + s3 + "','05','" + this.bizRegNo + "/" + this.repeatCount + "'," + "'" + s4 + "', " + "sysdate)";
                    if (set != null) {
                        try {
                            set.close();
                        }
                        catch (Exception ex5) {}
                    }
                    if (preparedStatement != null) {
                        try {
                            preparedStatement.close();
                        }
                        catch (Exception ex6) {}
                    }
                    preparedStatement = connection.prepareStatement(string5);
                    if (preparedStatement.executeUpdate() != 1) {
                        throw new Exception("[RcvDfisan-부정당업체등록통보서]DB INSERT 에러가 발생했습니다");
                    }
                    this.SaveLog("UM_ENTER_STATE 제재 입력 : BIZ_REG_NO[" + s + "]");
                }
            }
            else {
                final String string6 = " SELECT TO_CHAR(START_DT, 'yyyymmdd'), TO_CHAR(END_DT, 'yyyymmdd')  FROM UM_ENTER_STATE  WHERE BIZ_REG_NO = '" + s + "' " + "   AND STATE_CLS = '05' ";
                if (set != null) {
                    try {
                        set.close();
                    }
                    catch (Exception ex7) {}
                }
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    }
                    catch (Exception ex8) {}
                }
                preparedStatement = connection.prepareStatement(string6);
                set = preparedStatement.executeQuery();
                if (set.next()) {
                    string2 = set.getString(1);
                    string3 = set.getString(2);
                }
                String s5;
                if (Integer.parseInt(s2) <= Integer.parseInt(string2)) {
                    s5 = s2;
                }
                else {
                    s5 = string2;
                }
                String s6;
                if (Integer.parseInt(string3) <= Integer.parseInt(s3)) {
                    s6 = s3;
                }
                else {
                    s6 = string3;
                }
                final String string7 = " update UM_ENTER_STATE set RAISED_DT=sysdate, START_DT='" + s5 + "',END_DT='" + s6 + "',REMARK='" + this.bizRegNo + "/" + this.repeatCount + "'," + "     MANAGER_ID='" + s4 + "'," + "     PROCESS_DT=sysdate" + " where BIZ_REG_NO='" + s + "'" + " and STATE_CLS='05'";
                if (set != null) {
                    try {
                        set.close();
                    }
                    catch (Exception ex9) {}
                }
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    }
                    catch (Exception ex10) {}
                }
                preparedStatement = connection.prepareStatement(string7);
                preparedStatement.executeUpdate();
                this.SaveLog("UM_ENTER_STATE 제재 수정 : BIZ_REG_NO[" + s + "]");
            }
        }
        finally {
            try {
                if (set != null) {
                    set.close();
                }
            }
            catch (Exception ex11) {}
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
            catch (Exception ex12) {}
        }
    }
    
    public int getPushCount(final String s) {
        int int1 = 0;
        try {
            final ResultSet executeQuery = this.pconn.prepareStatement(" select nvl(max(PUNISH_COUNT),0) from UM_VIOLATE_COM where BIZ_REG_NO = '" + s + "'").executeQuery();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        return int1;
    }
    
    private void SaveLog(final String s) {
        Log.info("xmlFileName: Dfisan" + this.sourceName + "[" + this.serialNumber + "]:" + s + "\n");
    }
}
