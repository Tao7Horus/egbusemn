// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import common.Log;
import common.util.UM_COB_GTQ904;
import java.sql.Connection;

public class UM_ADB_OrgStandInfo
{
    public boolean isStandCodeExist(final String s, final Connection connection) throws Exception {
        boolean b = false;
        if (new UM_COB_GTQ904().getCount(this, "select count(*) from UM_ADMIN_STD_INFOR where STD_INSTITU_CD='" + s + "'", connection) > 0) {
            b = true;
        }
        return b;
    }
    
    public void insertOrgStandInfo(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String s9 = "\n insert into UM_ADMIN_STD_INFOR(\n         STD_INSTITU_CD, FULL_NM,          LOWEST_RANK_NM,\n         REPR_CD,      ZIP_CD,       RESIDENCE,     LAND_LOT_NO,           PHONE_NO,\n         FAX,           DIRECTOR_POSITION_NM,       DIRECTOR_POSITION,   VER_NO,         RANK,\n         UPPER_INSTITU_CD,  TOP_INSTITU_CD,   INSTITU_CLS_L,  INSTITU_CLS_M,\n         INSTITU_CLS_S,       CREATE_DT,           STOP_DT,        STOP_RSON,\n         MOD_DATE,      MOD_TIME,           MOD_CLS\n ) values(\n ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, ?, ?,\n ?, sysdate, ?, ?,\n ?, ?, ? )";
        try {
            prepareStatement = connection.prepareStatement(s9);
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.setString(3, "");
            prepareStatement.setString(4, "");
            prepareStatement.setString(5, s3);
            prepareStatement.setString(6, "");
            prepareStatement.setString(7, "");
            prepareStatement.setString(8, s4);
            prepareStatement.setString(9, s5);
            prepareStatement.setString(10, "");
            prepareStatement.setString(11, "");
            prepareStatement.setString(12, s6);
            prepareStatement.setString(13, "");
            prepareStatement.setString(14, s7);
            prepareStatement.setString(15, s8);
            prepareStatement.setString(16, "");
            prepareStatement.setString(17, "");
            prepareStatement.setString(18, "");
            prepareStatement.setString(19, "");
            prepareStatement.setString(20, "0");
            prepareStatement.setString(21, "");
            prepareStatement.setString(22, "");
            prepareStatement.setString(23, "");
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("사용_행정표준정보 입력 갯수 1이 아님");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_OrgStandInfo.insertOrgStandInfo():" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public void insertOrgStandInfo2(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String s12 = "\n insert into UM_ADMIN_STD_INFOR(\n         STD_INSTITU_CD, FULL_NM,          LOWEST_RANK_NM,\n         REPR_CD,      ZIP_CD,       RESIDENCE,     LAND_LOT_NO,           PHONE_NO,\n         FAX,           DIRECTOR_POSITION_NM,       DIRECTOR_POSITION,   VER_NO,         RANK,\n         UPPER_INSTITU_CD,  TOP_INSTITU_CD,   INSTITU_CLS_L,  INSTITU_CLS_M,\n         INSTITU_CLS_S,       CREATE_DT,           STOP_DT,        STOP_RSON,\n         MOD_DATE,      MOD_TIME,           MOD_CLS\n ) values(\n ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, ?, ?, ?,\n ?, ?, ?, ?,\n ?, sysdate, ?, ?,\n ?, ?, ? )";
        try {
            prepareStatement = connection.prepareStatement(s12);
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.setString(3, "");
            prepareStatement.setString(4, "");
            prepareStatement.setString(5, s3);
            prepareStatement.setString(6, "");
            prepareStatement.setString(7, "");
            prepareStatement.setString(8, s4);
            prepareStatement.setString(9, s5);
            prepareStatement.setString(10, "");
            prepareStatement.setString(11, "");
            prepareStatement.setString(12, s6);
            prepareStatement.setString(13, "");
            prepareStatement.setString(14, s7);
            prepareStatement.setString(15, s8);
            prepareStatement.setString(16, s9);
            prepareStatement.setString(17, s10);
            prepareStatement.setString(18, s11);
            prepareStatement.setString(19, "");
            prepareStatement.setString(20, "0");
            prepareStatement.setString(21, "");
            prepareStatement.setString(22, "");
            prepareStatement.setString(23, "");
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("사용_행정표준정보 입력 갯수 1이 아님");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_OrgStandInfo.insertOrgStandInfo():" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public void updateOrgStandInfo(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String s11 = "\n UPDATE UM_ADMIN_STD_INFOR SET\n         FULL_NM=?, LOWEST_RANK_NM=?, ZIP_CD=?, PHONE_NO=?, \n         FAX=?, VER_NO=?, RANK = ?, UPPER_INSTITU_CD=?, \n         TOP_INSTITU_CD=?, MOD_DATE=sysdate \n WHERE STD_INSTITU_CD= ? ";
        try {
            prepareStatement = connection.prepareStatement(s11);
            prepareStatement.setString(1, s2);
            prepareStatement.setString(2, s3);
            prepareStatement.setString(3, s4);
            prepareStatement.setString(4, s5);
            prepareStatement.setString(5, s6);
            prepareStatement.setString(6, s7);
            prepareStatement.setString(7, s8);
            prepareStatement.setString(8, s9);
            prepareStatement.setString(9, s10);
            prepareStatement.setString(10, s);
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("사용_행정표준정보 수정 실패");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_OrgStandInfo.updateOrgStandInfo():SQL : " + s11 + "" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public void updateOrgStandInfo1(final String s, final String s2, final String s3, final String s4, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String s5 = "\n UPDATE UM_ADMIN_STD_INFOR SET\n        VER_NO=?, UPPER_INSTITU_CD=?, \n        TOP_INSTITU_CD=?, MOD_DATE=sysdate \n WHERE STD_INSTITU_CD= ? ";
        try {
            prepareStatement = connection.prepareStatement(s5);
            prepareStatement.setString(1, s2);
            prepareStatement.setString(2, s3);
            prepareStatement.setString(3, s4);
            prepareStatement.setString(4, s);
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("사용_행정표준정보 수정 실패");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_OrgStandInfo.updateOrgStandInfo1():SQL : " + s5 + "" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public void updateOrgStandInfo2(final String s, final String s2, final String s3, final String s4, final String s5, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String s6 = "\n UPDATE UM_ADMIN_STD_INFOR SET\n        VER_NO=?, UPPER_INSTITU_CD=?, \n        TOP_INSTITU_CD=?, FULL_NM = ?, MOD_DATE=sysdate \n WHERE STD_INSTITU_CD= ? ";
        try {
            prepareStatement = connection.prepareStatement(s6);
            prepareStatement.setString(1, s2);
            prepareStatement.setString(2, s3);
            prepareStatement.setString(3, s4);
            prepareStatement.setString(4, s5);
            prepareStatement.setString(5, s);
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("사용_행정표준정보 수정 실패");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_OrgStandInfo.updateOrgStandInfo1():SQL : " + s6 + "" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public void deleteOrgStandInfo(final String s, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String s2 = "\n Update UM_ADMIN_STD_INFOR set\n        STOP_RSON = '1', STOP_DT = sysdate, MOD_DATE = sysdate \n where STD_INSTITU_CD = ? ";
        try {
            prepareStatement = connection.prepareStatement(s2);
            prepareStatement.setString(1, s);
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("사용_행정표준정보 삭제처리 실패");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_OrgStandInfo.deleteOrgStandInfo():SQL : " + s2 + "" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public void updatebeforeInfo(final String s, final String s2, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String s3 = "\n UPDATE UM_PUB_INSTITU_MAST SET\n        HIST_CD=? \n WHERE INSTITU_CD= ?";
        try {
            prepareStatement = connection.prepareStatement(s3);
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("사용_공공기관마스터 이력코드 자기자신 추가");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_OrgStandInfo.updatebeforeInfo():SQL : " + s3 + "" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex2) {}
        }
    }
}
