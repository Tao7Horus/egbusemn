// 
// Decompiled by Procyon v0.5.30
// 

package exms.upload;

import common.Log;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import common.Trx;
import java.sql.Connection;

public class RcvIsscoa
{
    Connection pconn;
    private long serialNumber;
    private String sourceName;
    
    public RcvIsscoa() {
        this.pconn = null;
        this.serialNumber = 0L;
        this.sourceName = "RcvIsscoa";
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
            this.SaveLog(" ## main_process start ");
            this.pconn.setAutoCommit(false);
            this.SaveLog(" ## UpdatePunish start ");
            this.UpdatePunish(this.pconn, s2, s8, s6, s7, s9, s10, s20, s11, s15, s16, s17, s18, s19, s, s8, s3, s, s21);
            this.SaveLog(" ## UpdatePunish end ");
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
            this.SaveLog(" ## main_process end ");
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
                return "-2[RcvIsscoa - 부정당업체등록통보서]oracle error 0001";
            }
            return ex.getErrorCode() + "[RcvIsscoa - 부정당업체등록통보서]oracle error " + ex.getErrorCode() + ":" + ex.toString();
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
            return "-2[RcvIsscoa - 부정당업체등록통보서]DB Update Error" + ex2.getMessage();
        }
    }
    
    private void UpdatePunish(final Connection connection, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final String s12, final String s13, final String s14, final String s15, final String s16, final String s17, final String s18) throws Exception {
        String string = null;
        PreparedStatement prepareStatement = null;
        try {
            string = " UPDATE UM_VIOLATE_COM SET                                                                                                                      \n     UPDATE_DT = SYSDATE,                                                                                                                             \n     DOC_NO        = '" + s + "',                                 \n" + "     CHRG_NM        = '" + s3 + "',           \n" + "     CHRG_PHONE_NO  = '" + s4 + "',   \n" + "     BIZ_NM          = '" + s5 + "',     \n" + "     ADDR            = '" + s6 + "',         \n" + "     CONTR_TYPE    = '" + s7 + "',                                                  \n" + "     BIZ_CAT        = '" + s8 + "',                            \n" + "     PUNISH_BASIS1       = '" + s9 + "',                                   \n" + "     PUNISH_DT      = '" + s10 + "',                                     \n" + "     EXPIRE_DT      = '" + s11 + "',                                   \n" + "     PUNISH_PERIOD        = '" + s12 + "',                                 \n" + "     CANCEL_DT      = '" + s13 + "',                              \n" + "     UPDATE_UM_ID        = '" + s14 + "',                      \n" + "     PUNISH_INSTITU_CD    = '" + s15 + "',                                         \n" + "     INSTITU_NM      = '" + s16 + "',   \n" + "     INSTITU_CD    = '" + s17 + "', \n" + "     NOTE\t        = '" + s18 + "'                                                    \n" + " WHERE BIZ_REG_NO = '" + s2 + "' \n" + "   AND PUNISH_COUNT      = (select nvl(max(PUNISH_COUNT),0) from UM_VIOLATE_COM where BIZ_REG_NO = '" + s2 + "')";
            prepareStatement = connection.prepareStatement(string);
            prepareStatement.executeUpdate();
        }
        catch (Exception ex) {
            this.SaveLog(" UpdatePunish() query[" + string + "] :" + ex.toString());
            throw ex;
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    private void SaveLog(final String s) {
        Log.info("ISSCOA" + this.sourceName + "[" + this.serialNumber + "]:" + s);
    }
}
