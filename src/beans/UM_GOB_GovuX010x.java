// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.ResultSet;
import common.Trx;
import java.sql.PreparedStatement;
import common.Log;
import java.sql.Connection;

public class UM_GOB_GovuX010x
{
    public void updateGongInfoOnlyName(final String s, final String s2, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String s3 = "\n update 사용_공공기관마스터\n set 공공기관명_전체=?, 갱신일자=sysdate\n where 공공기관코드= ?";
        try {
            prepareStatement = connection.prepareStatement(s3);
            prepareStatement.setString(1, s2);
            prepareStatement.setString(2, s);
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("사용_공공기관마스터 수정 갯수 1이 아님");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_GOB_GovuX010x.updateGongInfoOnlyName():" + ex.getMessage());
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
    
    public void updateSuyoOnlyName(final String s, final String s2, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String s3 = "\n update syn_수요기관\n set 수요기관명=?, 수정일자=sysdate\n where 수요기관코드= ?";
        try {
            prepareStatement = connection.prepareStatement(s3);
            prepareStatement.setString(1, s2);
            prepareStatement.setString(2, s);
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("syn_수요기관 수정 갯수 1이 아님");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_GOB_GovuX010x.updateSuyoOnlyName():" + ex.getMessage());
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
    
    public String getDataUsemnStandardInfo(final String s, Connection connection) throws Exception {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        boolean b = false;
        final String s2 = "select 최상위기관코드 from 사용_행정표준정보 where 행정표준기관코드 =? and 폐지구분='0'";
        String string = null;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            prepareStatement = connection.prepareStatement(s2);
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                string = executeQuery.getString(1);
                if (executeQuery.wasNull()) {
                    string = null;
                }
            }
            if (string == null) {
                Log.debug("사용_행정표준정보 테이블에서 [" + s + "] 에 대한 최상위기관코드를 구할 수 없음");
            }
            return string;
        }
        catch (Exception ex) {
            Log.debug("UM_GOB_GovuX010x.getDataUsemnStandardInfo():" + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex2) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (connection != null && b) {
                    trx.close();
                }
            }
            catch (Exception ex4) {}
        }
    }
    
    public void insertGongNewCode(final String s, final String s2, final String s3, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String s4 = " insert into 사용_공공기관마스터     (공공기관코드, 공공기관명_전체, 공공기관명_약어, 공공기관명_영문, 사업자등록번호, \t   \t\t업태, 업종, 담당자명, 담당자부서명, 담당자전화번호, 담당자팩스번호, 담당자메일주소, \t\t\t채권자명, 소관구분, 지역코드, 우편번호, 주소, 나머지주소, 전화번호, 팩스번호, 전수요기관코드, \t\t\t물품관리관명, 양허코드, 관할지청, 전자인증여부, 특정관리기관, 지청구분, 운송거리, 홈페이지주소, \t\t\t금결원승인번호, 등록일자, 처리자ID, 삭제여부, 갱신일자, 접수번호, 최상위기관코드, 기관유형구분) select ?, 공공기관명_전체, 공공기관명_약어, 공공기관명_영문, 사업자등록번호, \t   \t\t업태, 업종, 담당자명, 담당자부서명, 담당자전화번호, 담당자팩스번호, 담당자메일주소, \t\t\t채권자명, 소관구분, 지역코드, 우편번호, 주소, 나머지주소, 전화번호, 팩스번호, 전수요기관코드, \t\t\t물품관리관명, 양허코드, 관할지청, 전자인증여부, 특정관리기관, 지청구분, 운송거리, 홈페이지주소, \t\t\t금결원승인번호, 등록일자, 처리자ID, 'N', SYSDATE, 접수번호, ?, 기관유형구분 from 사용_공공기관마스터 where 공공기관코드=?";
        try {
            prepareStatement = connection.prepareStatement(s4);
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.setString(3, s3);
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("사용_공공기관마스터 입력 갯수 1이 아님");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_GOB_GovuX010x.insertGongNewCode():" + ex.getMessage());
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
    
    public void insertGongNewCode(final String s, final String s2, final String s3, final String s4, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String s5 = " insert into 사용_공공기관마스터     (공공기관코드, 공공기관명_전체, 공공기관명_약어, 공공기관명_영문, 사업자등록번호, \t   \t\t업태, 업종, 담당자명, 담당자부서명, 담당자전화번호, 담당자팩스번호, 담당자메일주소, \t\t\t채권자명, 소관구분, 지역코드, 우편번호, 주소, 나머지주소, 전화번호, 팩스번호, 전수요기관코드, \t\t\t물품관리관명, 양허코드, 관할지청, 전자인증여부, 특정관리기관, 지청구분, 운송거리, 홈페이지주소, \t\t\t금결원승인번호, 등록일자, 처리자ID, 삭제여부, 갱신일자, 접수번호, 최상위기관코드, 기관유형구분) select ?, ?, 공공기관명_약어, 공공기관명_영문, 사업자등록번호, \t   \t\t업태, 업종, 담당자명, 담당자부서명, 담당자전화번호, 담당자팩스번호, 담당자메일주소, \t\t\t채권자명, 소관구분, 지역코드, 우편번호, 주소, 나머지주소, 전화번호, 팩스번호, 전수요기관코드, \t\t\t물품관리관명, 양허코드, 관할지청, 전자인증여부, 특정관리기관, 지청구분, 운송거리, 홈페이지주소, \t\t\t금결원승인번호, 등록일자, 처리자ID, 'N', SYSDATE, 접수번호, ?, 기관유형구분 from 사용_공공기관마스터 where 공공기관코드=?";
        try {
            prepareStatement = connection.prepareStatement(s5);
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s4);
            prepareStatement.setString(3, s2);
            prepareStatement.setString(4, s3);
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("사용_공공기관마스터 입력 갯수 1이 아님");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_GOB_GovuX010x.insertGongNewCode():" + ex.getMessage());
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
    
    public void updateGongInfoDeleteYN(final String s, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String s2 = "\n update 사용_공공기관마스터\n set 삭제여부='Y', 갱신일자=sysdate\n where 공공기관코드= ?";
        try {
            prepareStatement = connection.prepareStatement(s2);
            prepareStatement.setString(1, s);
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("사용_공공기관마스터 수정 갯수 1이 아님");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_GOB_GovuX010x.updateGongInfoDeleteYN():" + ex.getMessage());
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
    
    public void updateGongInfoUser(final String s, final String s2, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String s3 = "\n update 사용_사용자\n set 마스터코드=? \n where 마스터코드=?";
        try {
            prepareStatement = connection.prepareStatement(s3);
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.executeUpdate();
        }
        catch (Exception ex) {
            Log.debug("UM_GOB_GovuX010x.updateGongInfoUser():" + ex.getMessage());
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
    
    public void updateGongInfoFundCode(final String s, final String s2, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String s3 = "\n update 사용_공공기관회계코드\n set 공공기관코드=? \n where 공공기관코드=?";
        try {
            prepareStatement = connection.prepareStatement(s3);
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.executeUpdate();
        }
        catch (Exception ex) {
            Log.debug("UM_GOB_GovuX010x.updateGongInfoFundCode():" + ex.getMessage());
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
    
    public void updateGongInfoMoneyCode(final String s, final String s2, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String s3 = "\n update syn_지불_입금계좌\n set 기관코드=? \n where 기관코드=?";
        try {
            prepareStatement = connection.prepareStatement(s3);
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.executeUpdate();
        }
        catch (Exception ex) {
            Log.debug("UM_GOB_GovuX010x.updateGongInfoMoneyCode():" + ex.getMessage());
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
