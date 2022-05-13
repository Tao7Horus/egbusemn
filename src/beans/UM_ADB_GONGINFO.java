// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import common.ComDbQuery;
import common.OneRowEntity;
import common.util.UM_COB_GTQ904;
import entity.UM_ADE_HistA050b;
import java.sql.PreparedStatement;
import common.Log;
import entity.UM_RAE_GovuA010b;
import java.sql.Connection;

public class UM_ADB_GONGINFO
{
    public void updateGongInfo(final Connection conn, final UM_RAE_GovuA010b ett) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = "UPDATE UM_PUB_INSTITU_MAST  SET INSTITU_FULL_NM = ?,   CHRGR_NM = ?,   CHRGR_DEPART = ?,  CHRGR_PHONE_NO = ?,  CHRGR_FAX= ?,  CHRGR_EMAIL= ?,   ZIP_CD= ?,   ADDR= ?,   INSTITU_PHONE_NO= ?,  FAX= ?,  UPDATE_DT= SYSDATE,  INSTITU_SHORT_NM= ?,   INSTITU_EN_NM= ?,  WEBSITE= ?,   INSTITU_TYPE= ?,   MOBILE= ?,  INSTITU_CLS= ?,   LOCAL_INSTITU=?,  INSTITU_CONDITION= ?,   PERMIT_BRANCH= ?,   GROUP_NO = ?,   DEPARTMENT_NO= ?,  CREDITOR_NM= ? WHERE INSTITU_CD= ? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ett.getgoNameFull());
            pstmt.setString(2, ett.gettaskmaster());
            pstmt.setString(3, ett.getmasterPost());
            pstmt.setString(4, ett.getmasterTel());
            pstmt.setString(5, ett.getmasterFax());
            pstmt.setString(6, ett.getmasterMail());
            pstmt.setString(7, ett.getZIPCODE());
            pstmt.setString(8, ett.getADDR());
            pstmt.setString(9, ett.gettelNum());
            pstmt.setString(10, ett.getfaxNum());
            pstmt.setString(11, ett.getgoNameShort());
            pstmt.setString(12, ett.getgoNameEn());
            pstmt.setString(13, ett.gethomepage());
            pstmt.setString(14, ett.getInsType());
            pstmt.setString(15, ett.getmasterhp());
            pstmt.setString(16, ett.getInsType());
            pstmt.setString(17, ett.getZIPCODE());
            pstmt.setString(18, ett.getbuConditon());
            pstmt.setString(19, ett.getperOff());
            pstmt.setInt(20, ett.getGroupNo());
            pstmt.setInt(21, ett.getDepId());
            pstmt.setString(22, ett.getcreditName());
            Log.debug("ett.getperOff(): " + ett.getperOff());
            pstmt.setString(23, ett.getg2bCode());
            pstmt.executeUpdate();
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_GONGINFO.updateGongInfo():SQL : " + sql + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex3) {}
    }
    
    public void updateGongInfo(final UM_ADE_HistA050b ett, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = "\n UPDATE UM_PUB_INSTITU_MAST SET\n         INSTITU_SHORT_NM=?, INSTITU_EN_NM=?, BIZ_REG_NO=?,\n         INSTITU_CONDITION=?, INSTITU_TYPE=?, CHRGR_NM=?, CHRGR_DEPART=?, CHRGR_PHONE_NO=?,\n         CHRGR_FAX=?, CHRGR_EMAIL=?, CREDITOR_NM=?, INSTITU_CLS=?, ZIP_CD=?,\n         ADDR=?, DETAIL_ADDR=?, INSTITU_PHONE_NO=?, FAX=?, WEBSITE=?,\n         UPDATE_DT=sysdate, AREA_CD=?, GOODS_MNG_NM=?, MANAGER_ID=?, MOBILE=?, PERMIT_BRANCH=?, DEPARTMENT_NO=?, GROUP_NO=? \n WHERE INSTITU_CD= ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ett.getgoNameShort());
            pstmt.setString(2, ett.getgoNameEn());
            pstmt.setString(3, ett.getsaupNo());
            pstmt.setString(4, ett.getbuConditon());
            pstmt.setString(5, ett.getchgType());
            pstmt.setString(6, ett.gettaskmaster());
            pstmt.setString(7, ett.getmasterPost());
            pstmt.setString(8, ett.getmasterTel());
            pstmt.setString(9, ett.getmasterFax());
            pstmt.setString(10, ett.getmasterMail());
            pstmt.setString(11, ett.getcreditName());
            pstmt.setString(12, ett.getrelation());
            pstmt.setString(13, ett.getZIPCODE());
            pstmt.setString(14, ett.getADDR());
            pstmt.setString(15, ett.getaddress2());
            pstmt.setString(16, ett.getInstituPhone());
            pstmt.setString(17, ett.getfaxNum());
            pstmt.setString(18, ett.gethomepage());
            pstmt.setString(19, ett.getlocaCode());
            pstmt.setString(20, ett.getgoodsMaster());
            pstmt.setString(21, ett.getManagerId());
            pstmt.setString(22, ett.gettelNum());
            pstmt.setString(23, ett.getpermitCode());
            pstmt.setString(24, ett.getDepId());
            pstmt.setString(25, ett.getGroupNo());
            pstmt.setString(26, ett.getg2bCode());
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Không update thành công dữ liệu bên mời thầu.");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_GONGINFO.updateGongInfo():SQL : " + sql + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex3) {}
    }
    
    public void updateGongInfo(final String goNameShort, final String goNameEn, final String saupjaNumber, final String upTae, final String upJong, final String damdangName, final String damdangBuseo, final String damdangTel, final String damdangFax, final String damdangMail, final String bondholder, final String sogwanGubun, final String zipCode, final String address, final String extraAddress, final String telephone, final String faxNumber, final String homepage, final String regionCode, final String mulpumPerson, final String mostUperCode, final String mCode, final String userID, final String personPhone, final String idCharge, final int departmentNo, final int groupNo, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = "\n UPDATE UM_PUB_INSTITU_MAST SET\n         INSTITU_SHORT_NM=?, INSTITU_EN_NM=?, BIZ_REG_NO=?,\n         INSTITU_CONDITION=?, INSTITU_TYPE=?, CHRGR_NM=?, CHRGR_DEPART=?, CHRGR_PHONE_NO=?,\n         CHRGR_FAX=?, CHRGR_EMAIL=?, CREDITOR_NM=?, INSTITU_CLS=?, ZIP_CD=?,\n         ADDR=?, DETAIL_ADDR=?, INSTITU_PHONE_NO=?, FAX=?, WEBSITE=?,\n         UPDATE_DT=sysdate, AREA_CD=?, GOODS_MNG_NM=?, MANAGER_ID=?, MOBILE=?, PERMIT_BRANCH=?, DEPARTMENT_NO=?, GROUP_NO=? \n WHERE INSTITU_CD= ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goNameShort);
            pstmt.setString(2, goNameEn);
            pstmt.setString(3, saupjaNumber);
            pstmt.setString(4, upTae);
            pstmt.setString(5, upJong);
            pstmt.setString(6, damdangName);
            pstmt.setString(7, damdangBuseo);
            pstmt.setString(8, damdangTel);
            pstmt.setString(9, damdangFax);
            pstmt.setString(10, damdangMail);
            pstmt.setString(11, idCharge);
            pstmt.setString(12, sogwanGubun);
            pstmt.setString(13, zipCode);
            pstmt.setString(14, address);
            pstmt.setString(15, extraAddress);
            pstmt.setString(16, telephone);
            pstmt.setString(17, faxNumber);
            pstmt.setString(18, homepage);
            pstmt.setString(19, "0");
            pstmt.setString(20, mulpumPerson);
            pstmt.setString(21, userID);
            pstmt.setString(22, personPhone);
            pstmt.setString(23, bondholder);
            pstmt.setInt(24, departmentNo);
            pstmt.setInt(25, groupNo);
            pstmt.setString(26, mCode);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Không update thành công dữ liệu bên mời thầu.");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_GONGINFO.updateGongInfo():SQL : " + sql + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex3) {}
    }
    
    public void updateGongInfo_backup(final String goNameShort, final String goNameEn, final String saupjaNumber, final String orgDiv1, final String orgDiv2, final String orgDiv3, final String upTae, final String upJong, final String damdangName, final String damdangBuseo, final String damdangTel, final String damdangFax, final String damdangMail, final String bondholder, final String sogwanGubun, final String zipCode, final String address, final String extraAddress, final String telephone, final String faxNumber, final String homepage, final String regionCode, final String mulpumPerson, final String mCode, final String spOffice, final String eCitation, final String province, final String permitCode, final String branchOffi, final String trDistance, final String goNameFul, final String mostUperCode, final String userID, final String law, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = "\n UPDATE UM_PUB_INSTITU_MAST SET\n        INSTITU_SHORT_NM=?,  \tINSTITU_EN_NM=?,\t\t\tBIZ_REG_NO=?,\n        INSTITU_CONDITION=?,    INSTITU_TYPE=?,             CHRGR_NM=?,       CHRGR_DEPART=?,     \t\tCHRGR_PHONE_NO=?,\n        CHRGR_FAX=?,   \t\tCHRGR_EMAIL=?,   \t\t\tCREDITOR_NM=?,         \tINSTITU_CLS=?,         \t\tZIP_CD=?,\n        ADDR=?,             DETAIL_ADDR=?,       \t\tINSTITU_PHONE_NO=?,     CHRGR_FAX=?,         \t\tWEBSITE=?,\n        AREA_CD=?,\t\t\tGOODS_MNG_NM=?,\t\t\t\tSPECMNG_AGENT=?,\t\tE_AUTHENT_YN=?,\t\t\t\tLOCAL_INSTITU=?,\n\t\tAPPROVAL_CD=?,\t\t\tINSTITU_LOCALDIVISION=?,\t\t\t\t\tTRANSPORTDISTANCE=?,\t\t\t\t\tUPDATE_DT=sysdate,\n        INSTITU_TYPE_CLASSIFY=?,             ORG_TYPE_M=?,                  ORG_TYPE_S=?,              TOP_ORGID=?,           MANAGER_ID=?";
        if (this.isG2BSupplyCode(mCode)) {
            if (goNameFul == null) {
                throw new Exception("Dữ liệu có sẵn INSTITU_EN_NM.");
            }
            sql = String.valueOf(sql) + "\n  ,INSTITU_FULL_NM='" + goNameFul + "'";
        }
        sql = String.valueOf(sql) + "\n WHERE INSTITU_CD= ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goNameShort);
            pstmt.setString(2, goNameEn);
            pstmt.setString(3, saupjaNumber);
            pstmt.setString(4, upTae);
            pstmt.setString(5, upJong);
            pstmt.setString(6, damdangName);
            pstmt.setString(7, damdangBuseo);
            pstmt.setString(8, damdangTel);
            pstmt.setString(9, damdangFax);
            pstmt.setString(10, damdangMail);
            pstmt.setString(11, bondholder);
            if (sogwanGubun.equals("51") || sogwanGubun.equals("52") || sogwanGubun.equals("53")) {
                pstmt.setString(12, "05");
            }
            else if (sogwanGubun.equals("71") || sogwanGubun.equals("72")) {
                pstmt.setString(12, "07");
            }
            else {
                pstmt.setString(12, sogwanGubun);
            }
            pstmt.setString(13, zipCode);
            pstmt.setString(14, address);
            pstmt.setString(15, extraAddress);
            pstmt.setString(16, telephone);
            pstmt.setString(17, faxNumber);
            pstmt.setString(18, homepage);
            pstmt.setString(19, regionCode);
            pstmt.setString(20, mulpumPerson);
            pstmt.setString(21, spOffice);
            pstmt.setString(22, eCitation);
            pstmt.setString(23, province);
            pstmt.setString(24, permitCode);
            pstmt.setString(25, branchOffi);
            pstmt.setString(26, trDistance);
            pstmt.setString(27, orgDiv1);
            pstmt.setString(28, orgDiv2);
            pstmt.setString(29, orgDiv3);
            pstmt.setString(30, mostUperCode);
            pstmt.setString(31, userID);
            pstmt.setString(32, law);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("UM_PUB_INSTITU_MAST Sửa đổi số 1 hoặc 2");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_GONGINFO.updateGongInfo():SQL : " + sql + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex3) {}
    }
    
    public void updateGongInfoUpdateDTOnly(final String mCode, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = "UPDATE UM_PUB_INSTITU_MAST SET UPDATE_DT=sysdate WHERE INSTITU_CD= ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mCode);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Không cập nhật thông tin bên mời thầu thành công.");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_GONGINFO.updateGongInfo():SQL : " + sql + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex3) {}
    }
    
    public void updateRecPubInstituCertFullName(final String masterCode, final String gofullname, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = "\n UPDATE UM_REC_PUB_INSTITU_CERT SET INSTITU_FULL_NM = ? WHERE INSTITU_CD= ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, gofullname);
            pstmt.setString(2, masterCode);
            pstmt.executeUpdate();
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_GONGINFO.updateRecPubInstituMastFullName():SQL : " + sql + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex3) {}
    }
    
    public void updateRecPubInstituMastFullName(final String masterCode, final String gofullname, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = "\n UPDATE um_rec_pub_institu_mast SET INSTITU_FULL_NM =? WHERE INSTITU_CD= ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, gofullname);
            pstmt.setString(2, masterCode);
            pstmt.executeUpdate();
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_GONGINFO.updateRecPubInstituMastFullName():SQL : " + sql + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex3) {}
    }
    
    public void updateGongInfo(final String goNameShort, final String goNameEn, final String saupjaNumber, final String orgDiv1, final String orgDiv2, final String orgDiv3, final String buConditon, final String buType, final String taskmaster, final String masterPost, final String personTel, final String personFax, final String masterMail, final String bondholder, final String sogwanGubun, final String zipCode, final String address, final String extraAddress, final String telephone, final String faxNumber, final String homepage, final String regionCode, final String mulpumPerson, final String mCode, final String spOffice, final String eCitation, final String province, final String permitCode, final String branchOffi, final String trDistance, final String goNameFul, final String mostUperCode, final String userID, final String law, final String personPhone, final String idCharge, final int departmentNo, final int groupNo, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = "\n UPDATE UM_PUB_INSTITU_MAST SET\n   INSTITU_SHORT_NM=?, INSTITU_EN_NM=?, BIZ_REG_NO=?,\n   INSTITU_CONDITION=?, INSTITU_TYPE=?, CHRGR_NM=?, CHRGR_DEPART=?, CHRGR_PHONE_NO=?,\n   CHRGR_FAX=?, CHRGR_EMAIL=?, CREDITOR_NM=?, INSTITU_CLS=?, ZIP_CD=?,\n   ADDR=?, DETAIL_ADDR=?, INSTITU_PHONE_NO=?, FAX=?, WEBSITE=?,\n   AREA_CD=?, GOODS_MNG_NM=?, SPECIAL_MNG_AGENT=?, E_AUTHENT_YN=?, LOCAL_INSTITU=?,\n\tAPPROVAL_CD=?, LOCAL_INSTITU_DIV=?, TRANS_DIST=?, UPDATE_DT=sysdate,\n   INSTITU_CLS_DIV=?, MANAGER_ID=?, MOBILE=?, PERMIT_BRANCH=?, DEPARTMENT_NO=?, GROUP_NO=?,INSTITU_FULL_NM =? ";
        sql = String.valueOf(sql) + "\n WHERE INSTITU_CD= ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goNameShort);
            pstmt.setString(2, goNameEn);
            pstmt.setString(3, saupjaNumber);
            pstmt.setString(4, buConditon);
            pstmt.setString(5, buType);
            pstmt.setString(6, taskmaster);
            pstmt.setString(7, masterPost);
            pstmt.setString(8, personTel);
            pstmt.setString(9, personFax);
            pstmt.setString(10, masterMail);
            pstmt.setString(11, idCharge);
            pstmt.setString(12, sogwanGubun);
            pstmt.setString(13, zipCode);
            pstmt.setString(14, address);
            pstmt.setString(15, extraAddress);
            pstmt.setString(16, telephone);
            pstmt.setString(17, faxNumber);
            pstmt.setString(18, homepage);
            pstmt.setString(19, "0");
            pstmt.setString(20, mulpumPerson);
            pstmt.setString(21, spOffice);
            pstmt.setString(22, eCitation);
            pstmt.setString(23, zipCode);
            pstmt.setString(24, permitCode);
            pstmt.setString(25, branchOffi);
            pstmt.setString(26, trDistance);
            pstmt.setString(27, "01");
            pstmt.setString(28, userID);
            pstmt.setString(29, personPhone);
            pstmt.setString(30, bondholder);
            pstmt.setInt(31, departmentNo);
            pstmt.setInt(32, groupNo);
            pstmt.setString(33, goNameFul);
            pstmt.setString(34, mCode);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Không cập nhật thông tin bên mời thầu thành công.");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_GONGINFO.updateGongInfo():SQL : " + sql + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex3) {}
    }
    
    public void updateGongInfo1(final String goNameShort, final String goNameEn, final String saupjaNumber, final String orgDiv1, final String orgDiv2, final String orgDiv3, final String buConditon, final String buType, final String taskmaster, final String masterPost, final String personTel, final String personFax, final String masterMail, final String bondholder, final String sogwanGubun, final String zipCode, final String address, final String extraAddress, final String telephone, final String faxNumber, final String homepage, final String regionCode, final String mulpumPerson, final String mCode, final String spOffice, final String eCitation, final String province, final String permitCode, final String branchOffi, final String trDistance, final String goNameFul, final String mostUperCode, final String userID, final String law, final String personPhone, final String idCharge, final int departmentNo, final int groupNo, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = "\n UPDATE UM_PUB_INSTITU_MAST SET\n   INSTITU_SHORT_NM=?, INSTITU_EN_NM=?, BIZ_REG_NO=?,\n   INSTITU_CONDITION=?, INSTITU_TYPE=?, CHRGR_NM=?, CHRGR_DEPART=?, CHRGR_PHONE_NO=?,\n   CHRGR_FAX=?, CHRGR_EMAIL=?, CREDITOR_NM=?, INSTITU_CLS=?, ZIP_CD=?,\n   ADDR=?, DETAIL_ADDR=?, INSTITU_PHONE_NO=?, FAX=?, WEBSITE=?,\n   AREA_CD=?, GOODS_MNG_NM=?, SPECIAL_MNG_AGENT=?, E_AUTHENT_YN=?, LOCAL_INSTITU=?,\n\tAPPROVAL_CD=?, LOCAL_INSTITU_DIV=?, TRANS_DIST=?, UPDATE_DT=sysdate,\n   INSTITU_CLS_DIV=?, MANAGER_ID=?, MOBILE=?, PERMIT_BRANCH=?, DEPARTMENT_NO=?, GROUP_NO=?, INSTITU_FULL_NM=? ";
        sql = String.valueOf(sql) + "\n WHERE INSTITU_CD= ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goNameShort);
            pstmt.setString(2, goNameEn);
            pstmt.setString(3, saupjaNumber);
            pstmt.setString(4, buConditon);
            pstmt.setString(5, buType);
            pstmt.setString(6, taskmaster);
            pstmt.setString(7, masterPost);
            pstmt.setString(8, personTel);
            pstmt.setString(9, personFax);
            pstmt.setString(10, masterMail);
            pstmt.setString(11, idCharge);
            pstmt.setString(12, sogwanGubun);
            pstmt.setString(13, zipCode);
            pstmt.setString(14, address);
            pstmt.setString(15, extraAddress);
            pstmt.setString(16, telephone);
            pstmt.setString(17, faxNumber);
            pstmt.setString(18, homepage);
            pstmt.setString(19, "0");
            pstmt.setString(20, mulpumPerson);
            pstmt.setString(21, spOffice);
            pstmt.setString(22, eCitation);
            pstmt.setString(23, zipCode);
            pstmt.setString(24, permitCode);
            pstmt.setString(25, branchOffi);
            pstmt.setString(26, trDistance);
            pstmt.setString(27, "01");
            pstmt.setString(28, userID);
            pstmt.setString(29, personPhone);
            pstmt.setString(30, bondholder);
            pstmt.setInt(31, departmentNo);
            pstmt.setInt(32, groupNo);
            pstmt.setString(33, goNameFul);
            pstmt.setString(34, mCode);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Không cập nhật thông tin bên mời thầu thành công.");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_GONGINFO.updateGongInfo():SQL : " + sql + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex3) {}
    }
    
    public boolean isMasterCodeExist(final String code, final Connection conn) throws Exception {
        boolean result = false;
        final String sql = "select count(*) from UM_PUB_INSTITU_MAST where INSTITU_CD='" + code + "'";
        final int count = new UM_COB_GTQ904().getCount(this, sql, conn);
        if (count > 0) {
            result = true;
        }
        return result;
    }
    
    public boolean isComRegNumExist(final String companyRegNum, final Connection conn) throws Exception {
        boolean result = false;
        String sql = null;
        sql = " select count(*) from UM_PUB_INSTITU_MAST where BIZ_REG_NO= '" + companyRegNum + "' and substr(INSTITU_CD, 1, 2) <> 'ZZ' ";
        final int count = new UM_COB_GTQ904().getCount(this, sql, conn);
        if (count > 0) {
            result = true;
        }
        return result;
    }
    
    public OneRowEntity getRegModCount(final String code, final String receptNumber, final Connection conn) throws Exception {
        if (conn == null) {
            throw new Exception("Connection 은 null 일수 없습니다.");
        }
        final String sql = "";
        try {
            final String[] parameter = { code, receptNumber, code, code, receptNumber, code };
            return new ComDbQuery().getOneRowList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getRegModCount() : Mã [" + code + "]:Số tiếp nhận[" + receptNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public void insertGongInfo(final String mCode, final String goNameFul, final String goNameShort, final String goNameEn, final String saupjaNumber, final String upTae, final String upJong, final String damdangName, final String damdangBuseo, final String damdangTel, final String damdangFax, final String damdangMail, final String bondholder, final String sogwanGubun, final String zipCode, final String address, final String extraAddress, final String telephone, final String faxNumber, final String mulpumPerson, final String permitCode, final String province, final String eCitation, final String spOffice, final String branchOffi, final String trDistance, final String homepage, final String userID, final String regionCode, final String receptNumber, final String mostUperCode, final String gigwanType, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = "\n insert into UM_PUB_INSTITU_MAST(\n  INSTITU_CD,\t\tINSTITU_FULL_NM,\tINSTITU_SHORT_NM,\tINSTITU_EN_NM,\tBIZ_REG_NO,\n  INSTITU_CONDITION,           \tINSTITU_TYPE,               CHRGR_NM,           CHRGR_DEPART,     \tCHRGR_PHONE_NO,\n  CHRGR_FAX, \tCHRGR_EMAIL,     CREDITOR_NM,           INSTITU_CLS,         \tZIP_CD,\n  ADDR,           \tDETAIL_ADDR,         INSTITU_PHONE_NO,           FAX,         \tGOODS_MNG_NM,\n  APPROVAL_CD,       \tLOCAL_INSTITU,           E_AUTHENT_YN,       SPECMNG_AGENT,     \tINSTITU_LOCALDIVISION,\n  TRANSPORTDISTANCE,       \tWEBSITE,       MANAGER_ID,           AREA_CD,      \t\tRECV_NO,\n  TOP_ORGID, \tINSTITU_TYPE_CLASSIFY,   \tCREATED_DT,\t\t\tUPDATE_DT,\t\t\tDELETE_YN\n )\n values(\n ?,\t?, ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, sysdate, sysdate, 'N'\n )";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mCode);
            pstmt.setString(2, goNameFul);
            pstmt.setString(3, goNameShort);
            pstmt.setString(4, goNameEn);
            pstmt.setString(5, saupjaNumber);
            pstmt.setString(6, upTae);
            pstmt.setString(7, upJong);
            pstmt.setString(8, damdangName);
            pstmt.setString(9, damdangBuseo);
            pstmt.setString(10, damdangTel);
            pstmt.setString(11, damdangFax);
            pstmt.setString(12, damdangMail);
            pstmt.setString(13, bondholder);
            pstmt.setString(14, sogwanGubun);
            pstmt.setString(15, zipCode);
            pstmt.setString(16, address);
            pstmt.setString(17, extraAddress);
            pstmt.setString(18, telephone);
            pstmt.setString(19, faxNumber);
            pstmt.setString(20, mulpumPerson);
            pstmt.setString(21, permitCode);
            pstmt.setString(22, province);
            pstmt.setString(23, eCitation);
            pstmt.setString(24, spOffice);
            pstmt.setString(25, branchOffi);
            pstmt.setString(26, trDistance);
            pstmt.setString(27, homepage);
            pstmt.setString(28, userID);
            pstmt.setString(29, regionCode);
            pstmt.setString(30, receptNumber);
            pstmt.setString(31, mostUperCode);
            pstmt.setString(32, gigwanType);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("UM_PUB_INSTITU_MAST Nhập số 1 hoặc 2");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_GONGINFO.insertGongInfo():" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex3) {}
    }
    
    public boolean isG2BSupplyCode(final String mCode) {
        boolean result = false;
        if (mCode.substring(0, 2).equals("ZD") || mCode.substring(0, 2).equals("ZI") || mCode.substring(0, 2).equals("ZP") || mCode.substring(0, 2).equals("ZR") || mCode.substring(0, 2).equals("ZC") || mCode.substring(0, 2).equals("Z0")) {
            result = true;
        }
        return result;
    }
    
    public void deleteGongInfo(final String mCode, final String userID, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = "\n UPDATE UM_PUB_INSTITU_MAST SET\n         DELETE_YN='Y', \tMANAGER_ID=?, UPDATE_DT=sysdate\n WHERE INSTITU_CD= ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);
            pstmt.setString(2, mCode);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("UM_PUB_INSTITU_MAST Không cập nhật xóa thành công.");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_GONGINFO.deleteGongInfo():SQL : " + sql + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex3) {}
    }
    
    public void insertGongInfo2(final String mCode, final String goNameFul, final String goNameShort, final String goNameEn, final String saupjaNumber, final String upTae, final String upJong, final String damdangName, final String damdangBuseo, final String damdangTel, final String damdangFax, final String damdangMail, final String bondholder, final String sogwanGubun, final String zipCode, final String address, final String extraAddress, final String telephone, final String faxNumber, final String mulpumPerson, final String permitCode, final String province, final String eCitation, final String spOffice, final String branchOffi, final String trDistance, final String homepage, final String userID, final String regionCode, final String receptNumber, final String mostUperCode, final String gigwanType, final String beforecode, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = "\n insert into UM_PUB_INSTITU_MAST(\n  INSTITU_CD,\t\tINSTITU_FULL_NM,\tINSTITU_SHORT_NM,\tINSTITU_EN_NM,\tBIZ_REG_NO,\n  INSTITU_CONDITION,           \t\tINSTITU_TYPE,               CHRGR_NM,           CHRGR_DEPART,     \tCHRGR_PHONE_NO,\n  CHRGR_FAX, \tCHRGR_EMAIL,     CHRGR_EMAIL,           INSTITU_CLS,         \tZIP_CD,\n  ADDR,           \tDETAIL_ADDR,         INSTITU_PHONE_NO,           FAX,         \tGOODS_MNG_NM,\n  APPROVAL_CD,      LOCAL_INSTITU,        E_AUTHENT_YN,       SPECMNG_AGENT,     \tINSTITU_LOCALDIVISION,\n  \tTRANSPORTDISTANCE,       \tWEBSITE,       MANAGER_ID,           AREA_CD,      \t\tRECV_NO,\n  TOP_ORGID, \tINSTITU_TYPE_CLASSIFY,   \tCREATED_DT,\t\t\tUPDATE_DT,\t\t\tDELETE_YN, HIST_CD\n )\n values(\n ?,\t?, ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, sysdate, sysdate, 'N',?\n )";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mCode);
            pstmt.setString(2, goNameFul);
            pstmt.setString(3, goNameShort);
            pstmt.setString(4, goNameEn);
            pstmt.setString(5, saupjaNumber);
            pstmt.setString(6, upTae);
            pstmt.setString(7, upJong);
            pstmt.setString(8, damdangName);
            pstmt.setString(9, damdangBuseo);
            pstmt.setString(10, damdangTel);
            pstmt.setString(11, damdangFax);
            pstmt.setString(12, damdangMail);
            pstmt.setString(13, bondholder);
            pstmt.setString(14, sogwanGubun);
            pstmt.setString(15, zipCode);
            pstmt.setString(16, address);
            pstmt.setString(17, extraAddress);
            pstmt.setString(18, telephone);
            pstmt.setString(19, faxNumber);
            pstmt.setString(20, mulpumPerson);
            pstmt.setString(21, permitCode);
            pstmt.setString(22, province);
            pstmt.setString(23, eCitation);
            pstmt.setString(24, spOffice);
            pstmt.setString(25, branchOffi);
            pstmt.setString(26, trDistance);
            pstmt.setString(27, homepage);
            pstmt.setString(28, userID);
            pstmt.setString(29, regionCode);
            pstmt.setString(30, receptNumber);
            pstmt.setString(31, mostUperCode);
            pstmt.setString(32, gigwanType);
            pstmt.setString(33, beforecode);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("UM_PUB_INSTITU_MAST Nhập số 1 hoặc 2");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_GONGINFO.insertGongInfo2():" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex3) {}
    }
    
    public void insertGongInfo3(final String mCode, final String goNameFul, final String goNameShort, final String goNameEn, final String saupjaNumber, final String upTae, final String upJong, final String damdangName, final String damdangBuseo, final String damdangTel, final String damdangFax, final String damdangMail, final String bondholder, final String sogwanGubun, final String zipCode, final String address, final String extraAddress, final String telephone, final String faxNumber, final String mulpumPerson, final String permitCode, final String province, final String eCitation, final String spOffice, final String branchOffi, final String trDistance, final String homepage, final String userID, final String regionCode, final String receptNumber, final String mostUperCode, final String gigwanType, final String beforecode, final String orgDiv1, final String orgDiv2, final String orgDiv3, final String HP, final String smsCheck, final String relation1, final String law, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = "\n insert into UM_PUB_INSTITU_MAST(\n  INSTITU_CD,\t\tINSTITU_FULL_NM,\tINSTITU_SHORT_NM,\tINSTITU_EN_NM,\t\tBIZ_REG_NO,\n  INSTITU_CONDITION,INSTITU_TYPE,       CHRGR_NM,           CHRGR_DEPART,     \tCHRGR_PHONE_NO,\n  CHRGR_FAX, \tCHRGR_EMAIL,     CREDITOR_NM,           INSTITU_CLS,         \tZIP_CD,\n  ADDR,           \tDETAIL_ADDR,   INSTITU_PHONE_NO,           FAX,         \tGOODS_MNG_NM,\n  APPROVAL_CD,      LOCAL_INSTITU,E_AUTHENT_YN,       SPECMNG_AGENT,     \tINSTITU_LOCALDIVISION,\n  TRANSPORTDISTANCE,       \tWEBSITE,       \tMANAGER_ID,           AREA_CD,      \t\tRECV_NO,\n  TOP_ORGID, \tINSTITU_TYPE_CLASSIFY,   \tCREATED_DT,\t\t\tUPDATE_DT,\t\t\tDELETE_YN, HIST_CD,\n  ORG_TYPE_M,       ORG_TYPE_S,      CHECK_CD_YN,  MOBILE,  SMS_RECV_YN \n )\n values(\n ?,\t?, ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, sysdate, sysdate, 'N',?,\n ?, ?, 'N',?, ?\n )";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mCode);
            pstmt.setString(2, goNameFul);
            pstmt.setString(3, goNameShort);
            pstmt.setString(4, goNameEn);
            pstmt.setString(5, saupjaNumber);
            pstmt.setString(6, upTae);
            pstmt.setString(7, upJong);
            pstmt.setString(8, damdangName);
            pstmt.setString(9, damdangBuseo);
            pstmt.setString(10, damdangTel);
            pstmt.setString(11, damdangFax);
            pstmt.setString(12, damdangMail);
            pstmt.setString(13, bondholder);
            if (sogwanGubun.equals("51") || sogwanGubun.equals("52") || sogwanGubun.equals("53")) {
                pstmt.setString(14, "05");
            }
            else if (sogwanGubun.equals("71") || sogwanGubun.equals("72")) {
                pstmt.setString(14, "07");
            }
            else {
                pstmt.setString(14, sogwanGubun);
            }
            pstmt.setString(15, zipCode);
            pstmt.setString(16, address);
            pstmt.setString(17, extraAddress);
            pstmt.setString(18, telephone);
            pstmt.setString(19, faxNumber);
            pstmt.setString(20, mulpumPerson);
            pstmt.setString(21, permitCode);
            pstmt.setString(22, province);
            pstmt.setString(23, eCitation);
            pstmt.setString(24, spOffice);
            pstmt.setString(25, branchOffi);
            pstmt.setString(26, trDistance);
            pstmt.setString(27, homepage);
            pstmt.setString(28, userID);
            pstmt.setString(29, regionCode);
            pstmt.setString(30, receptNumber);
            pstmt.setString(31, mostUperCode);
            pstmt.setString(32, orgDiv1);
            pstmt.setString(33, beforecode);
            pstmt.setString(34, orgDiv2);
            pstmt.setString(35, orgDiv3);
            pstmt.setString(36, HP);
            pstmt.setString(37, smsCheck);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("UM_PUB_INSTITU_MAST Nhập số 1 hoặc 2");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_GONGINFO.insertGongInfo2():" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex3) {}
    }
    
    public void insertGongInfoNew(final UM_RAE_GovuA010b ettcode, final String relation, final Connection conn, final String userId, final String test_option_yn) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " INSERT INTO UM_PUB_INSTITU_MAST(INSTITU_FULL_NM, CHRGR_NM, CHRGR_DEPART,CHRGR_PHONE_NO,CHRGR_FAX,CHRGR_EMAIL, ZIP_CD, ADDR, DETAIL_ADDR, INSTITU_PHONE_NO,FAX,GOODS_MNG_NM, REG_DT, INSTITU_CD, INSTITU_SHORT_NM, INSTITU_EN_NM, BIZ_REG_NO,WEBSITE, INSTITU_TYPE, MOBILE,RECV_NO, MANAGER_ID, INSTITU_CLS, AREA_CD, LOCAL_INSTITU,E_AUTHENT_YN, DELETE_YN, INSTITU_CONDITION, CREDITOR_NM, PERMIT_BRANCH,  GROUP_NO, DEPARTMENT_NO, TEST_OPTION_YN ) VALUES(?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,sysdate,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?  )";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ettcode.getgoNameFull());
            pstmt.setString(2, ettcode.gettaskmaster());
            pstmt.setString(3, ettcode.getmasterPost());
            pstmt.setString(4, ettcode.getmasterTel());
            pstmt.setString(5, ettcode.getmasterFax());
            pstmt.setString(6, ettcode.getmasterMail());
            pstmt.setString(7, ettcode.getZIPCODE());
            pstmt.setString(8, ettcode.getADDR());
            pstmt.setString(9, ettcode.getaddress2());
            pstmt.setString(10, ettcode.gettelNum());
            pstmt.setString(11, ettcode.getfaxNum());
            pstmt.setString(12, ettcode.getgoodsMaster());
            pstmt.setString(13, ettcode.getg2bCode());
            pstmt.setString(14, ettcode.getgoNameShort());
            pstmt.setString(15, ettcode.getgoNameEn());
            pstmt.setString(16, ettcode.getsaupNo());
            pstmt.setString(17, ettcode.gethomepage());
            pstmt.setString(18, ettcode.getbuType());
            pstmt.setString(19, ettcode.getmasterhp());
            pstmt.setString(20, ettcode.getrecept());
            pstmt.setString(21, userId);
            pstmt.setString(22, ettcode.getInsType());
            pstmt.setString(23, ettcode.getZIPCODE());
            pstmt.setString(24, "MPI");
            pstmt.setString(25, "N");
            pstmt.setString(26, "N");
            pstmt.setString(27, ettcode.getbuConditon());
            pstmt.setString(28, ettcode.getcreditName());
            pstmt.setString(29, ettcode.getperOff());
            pstmt.setInt(30, ettcode.getGroupNo());
            pstmt.setInt(31, ettcode.getDepId());
            pstmt.setString(32, test_option_yn);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Cập nhật dữ liệu lỗi.");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_GONGINFO.insertGongInfo2():" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex3) {}
    }
}
