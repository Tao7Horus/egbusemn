// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import common.ComDbQuery;
import common.CommEntity;
import common.Log;
import common.Trx;
import java.util.Hashtable;
import java.sql.Connection;

public class UM_COB_ConiC100
{
    public Hashtable getTotalData(final String saupNo, Connection conn) throws Exception {
        Trx resource = null;
        final Hashtable returnHash = new Hashtable();
        boolean flag = false;
        try {
            if (conn == null) {
                resource = new Trx(this, "usemn");
                conn = resource.getConnection();
                flag = true;
            }
            returnHash.put("mainEntity", this.getMainData(saupNo, conn));
            returnHash.put("idEntity", this.getIDData(saupNo, conn));
            returnHash.put("gpEntity", this.getGPData(saupNo, conn));
            returnHash.put("jpEntity", this.getJPData(saupNo, conn));
            returnHash.put("msEntity", this.getMSData(saupNo, conn));
            returnHash.put("dpEntity", this.getDPData(saupNo, conn));
            returnHash.put("gjEntity", this.getGJData(saupNo, conn));
            returnHash.put("brEntity", this.getBRData(saupNo, conn));
            return returnHash;
        }
        catch (Exception ex) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getTotalData() saupNo[" + saupNo + "] :" + ex.toString());
            throw ex;
        }
        finally {
            try {
                if (conn != null && flag) {
                    resource.close();
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public CommEntity[] getMainData(final String saupNo, final Connection conn) throws Exception {
        final String query = " SELECT  BIZ_REG_NO,  NATIONALITY,  BIZ_NM,  BIZ_EN_NM,  TO_CHAR(COMMENCEMENT_DT, 'dd/MM/yyyy'),  TO_CHAR(ESTABLISH_DT, 'dd/MM/yyyy'),  BIZ_CLS,  PRODUCT_CLS,  CORP_REG_NO,  BIZ_CLS1,  BIZ_CLS2,  BIZ_CLS_YEAR,  CAPITAL,  EMPLOYEE_COUNT,  LAST_SETTLE_DT,  ZIP_CD,  AREA_CD,  ADDR,  DETAIL_ADDR,  PHONE_NO,  FAX,  WEBSITE,  SPECAIL_GOODS_YN,  TO_CHAR(REG_DT, 'dd/MM/yyyy'),  TO_CHAR(UPDATE_DT, 'dd/MM/yyyy'),  REPR_BIZ_APPROVE_YN,  MANAGER_ID,  PERMIT_BRANCH,  PERMIT_BRANCH/*, gt_query('J02',국적,'2') 국적명*/ FROM UM_SUPPLIER_ENTER_MAST  WHERE BIZ_REG_NO = ? ";
        final String[] parameter = { saupNo };
        try {
            return new ComDbQuery().getList(this, "usemn", query, parameter, conn);
        }
        catch (Exception exf) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getMainData() saupNo[" + saupNo + "]:" + exf.toString());
            throw exf;
        }
    }
    
    public CommEntity[] getIDData(final String saupNo, final Connection conn) throws Exception {
        final String query = " SELECT  IDENT_NO,  NM,  DEPART,  POSITION,  PHONE_NO,  EMAIL,  MOBILE,  FAX,  BIDDING_AGENT_YN  FROM UM_BID_AGENT  WHERE BIZ_REG_NO = ? ";
        final String[] parameter = { saupNo };
        try {
            return new ComDbQuery().getList(this, "usemn", query, parameter, conn);
        }
        catch (Exception exf) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getIDData() saupNo[" + saupNo + "]:" + exf.toString());
            throw exf;
        }
    }
    
    public CommEntity[] getJPData(final String saupNo, final Connection conn) throws Exception {
        final String query = " SELECT /*+ ordered  use_nl(A B) */ DISTINCT  A.GOOD_CLS_NO,  A.PERMIT_NO,  A.PERMIT_INSTITU,  TO_CHAR(A.PERMIT_DT, 'dd/MM/yyyy'),  A.INCOME_3YEARS,  A.MAST_GOODS_YN,  A.DIRECT_PRODUCTION_DOC,  TO_CHAR(A.AVAIL_PERIOD_START_DT,'YYYYMMDD'),  TO_CHAR(A.AVAIL_PERIOD_END_DT,'YYYYMMDD'),  A.INDUSTRY_CLS_CD,  A.ISSUE_INSTITU,  A.DOC_NM  FROM UM_SUPPLIER_ENTER_ITEMS A  WHERE A.BIZ_REG_NO = ?  AND A.DIRECT_PRODUCTION_YN = 'Y'  AND A.RESERVED_ITEM_OPTION is null ";
        final String[] parameter = { saupNo };
        try {
            return new ComDbQuery().getList(this, "usemn", query, parameter, conn);
        }
        catch (Exception exf) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getJPData() saupNo[" + saupNo + "]:" + exf.toString());
            throw exf;
        }
    }
    
    public CommEntity[] getGPData(final String saupNo, final Connection conn) throws Exception {
        final String query = " SELECT /*+ ordered  use_nl(A B) */  DISTINCT  A.GOOD_CLS_NO,/*B.분류명, */ A.GOOD_CLS_NO,  A.INCOME_3YEARS,  A.MAST_GOODS_YN  FROM UM_SUPPLIER_ENTER_ITEMS A  WHERE A.BIZ_REG_NO = ?  /*AND A.GOOD_CLS_NO = B.물품분류 */ AND A.DIRECT_PRODUCTION_YN = 'N'  AND A.RESERVED_ITEM_OPTION is null ";
        final String[] parameter = { saupNo };
        try {
            return new ComDbQuery().getList(this, "usemn", query, parameter, conn);
        }
        catch (Exception exf) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getGPData() saupNo[" + saupNo + "]:" + exf.toString());
            throw exf;
        }
    }
    
    public CommEntity[] getMSData(final String saupNo, final Connection conn) throws Exception {
        final String query = " SELECT  A.STD_CLS_CD,  B.CD_NM,  A.STD_CLS_NO ,  TO_CHAR(A.ISSUED_DT, 'dd/MM/yyyy'),  TO_CHAR(A.STD_CLS_EXPIRY_DT, 'dd/MM/yyyy'),  A.CONST_ABIL_EVAL_AMT,  A.EVAL_STD_YEAR,  A.MAST_STD_CLS_YN, A.STD_CLS_NAME  FROM um_enter_std_cls A, SYN_PUB_CODE B  WHERE A.BIZ_REG_NO = ?    AND B.CD_CLS = 'GZ8'    AND A.STD_CLS_CD = B.CD ";
        final String[] parameter = { saupNo };
        try {
            return new ComDbQuery().getList(this, "usemn", query, parameter, conn);
        }
        catch (Exception exf) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getMSData() saupNo[" + saupNo + "]:" + exf.toString());
            throw exf;
        }
    }
    
    public CommEntity[] getDPData(final String saupNo, final Connection conn) throws Exception {
        final String query = " SELECT  REPR_IDENT_NO,  REPR_NM,  REPR_EMAIL,  MAST_REPR_YN,  REPR_MOBILE,  REPR_CLS  FROM UM_REPR  WHERE BIZ_REG_NO = ? ";
        final String[] parameter = { saupNo };
        try {
            return new ComDbQuery().getList(this, "usemn", query, parameter, conn);
        }
        catch (Exception exf) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getDPData() saupNo[" + saupNo + "]:" + exf.toString());
            throw exf;
        }
    }
    
    public CommEntity[] getGJData(final String saupNo, final Connection conn) throws Exception {
        final String query = " SELECT  FACTORY_NM,  FACTORY_ZIP_CD,  FACTORY_ADDR ,  FACTORY_ADDR2 ,  FACTORY_PHONE_NO  ,  FACTORY_FAX,  SERIAL_NO,  FACTORY_RENT_YN,  to_char(FACTORY_RENT_START_DT, 'dd/MM/yyyy'),  to_char(FACTORY_RENT_END_DT, 'dd/MM/yyyy')  FROM UM_FACTORY_INFO  WHERE BIZ_REG_NO = ? ";
        final String[] parameter = { saupNo };
        try {
            return new ComDbQuery().getList(this, "usemn", query, parameter, conn);
        }
        catch (Exception exf) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getGJData() saupNo[" + saupNo + "]:" + exf.toString());
            throw exf;
        }
    }
    
    public CommEntity[] getBRData(final String saupNo, final Connection conn) throws Exception {
        final String query = " SELECT a.BIZ_REG_NO, BIZ_NM, ZIP_CD, AREA_CD, ADDR, DETAIL_ADDR, PHONE_NO, FAX,     b.REPR_NM, b.REPR_IDENT_NO, DECODE(c.STATE_CLS,'07','Y') STATE_CLS   FROM  UM_SUPPLIER_ENTER_MAST a, UM_REPR b, UM_ENTER_STATE c   WHERE a.BIZ_REG_NO = b.BIZ_REG_NO   AND a.BIZ_REG_NO = c.BIZ_REG_NO(+)  AND c.STATE_CLS(+) = '07'  AND b.MAST_REPR_YN = 'Y'   AND a.CORP_REG_NO IN (  SELECT CORP_REG_NO        FROM UM_SUPPLIER_ENTER_MAST         WHERE BIZ_REG_NO  =?)   AND HEADQUARTER_YN = 'N'   ORDER BY a.BIZ_REG_NO ASC";
        final String[] parameter = { saupNo };
        try {
            return new ComDbQuery().getList(this, "usemn", query, parameter, conn);
        }
        catch (Exception exf) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getBRData() saupNo[" + saupNo + "]:" + exf.toString());
            throw exf;
        }
    }
}
