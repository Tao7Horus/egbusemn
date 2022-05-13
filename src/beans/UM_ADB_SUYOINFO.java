// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import common.Log;
import common.util.UM_COB_GTQ904;
import java.sql.Connection;

public class UM_ADB_SUYOINFO
{
    private String getEdiDataFirst(final String s, final Connection connection) throws Exception {
        String s2;
        if (s.equals("07")) {
            s2 = "SELECT max(수요기관코드)+1 FROM syn_수요기관 WHERE 수요기관코드 like '5%'";
        }
        else {
            s2 = "SELECT max(수요기관코드)+1 FROM syn_수요기관 WHERE 수요기관코드 like '8%'";
        }
        return new UM_COB_GTQ904().getSqlDataOne(this, s2, connection);
    }
    
    private String getEdiDataSecode(final String s, final Connection connection) throws Exception {
        String s2 = "";
        if (s.equals("Z0") || s.equals("ZC")) {
            s2 = " select decode(sign(CC-899999999),-1,CC+1,    decode(sign(BB-799999999),-1,BB+1, \t     decode(sign(AA-699999999),-1,AA+1),'NO')) from ( select sum(A) AA, sum(B) BB, sum(C) CC from (  select max(수요기관코드) A,'0' B,'0' C from syn_수요기관 where 수요기관코드 like '6%' union all select '0' A, max(수요기관코드) B,'0' C from syn_수요기관 where 수요기관코드 like '7%' union all select '0' A,'0' B,max(수요기관코드) C from syn_수요기관 where 수요기관코드 like '8%'))";
        }
        else if (s.equals("ZD")) {
            s2 = "select max(수요기관코드)+1 from syn_수요기관 where 수요기관코드 like '124%'";
        }
        else if (s.equals("ZI")) {
            s2 = "select max(수요기관코드)+1 from syn_수요기관 where 수요기관코드 like '103%'";
        }
        else if (s.equals("ZP")) {
            s2 = "select max(수요기관코드)+1 from syn_수요기관 where 수요기관코드 like '152%'";
        }
        else if (s.equals("ZR")) {
            s2 = "SELECT max(수요기관코드)+1 FROM syn_수요기관 where 수요기관코드 like '233%'";
        }
        return new UM_COB_GTQ904().getSqlDataOne(this, s2, connection);
    }
    
    private String getEdiDataThird(final String s, final Connection connection) throws Exception {
        return new UM_COB_GTQ904().getSqlDataOne(this, " SELECT max(수요기관코드)+1  FROM syn_매핑코드기관  where 수요기관코드 like (SELECT substr(수요기관코드,1,3)||'______'  FROM syn_매핑코드기관  WHERE 공공기관코드 = '" + s + "')\t", connection);
    }
    
    public void insertSuyoInfo(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final String s12, final String s13, final String s14, final String s15, final String s16, final String s17, final String s18, final String s19, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String s20 = "\n\tinsert into syn_수요기관(\n  \t수요기관코드,       지청구분,       수요기관명,         영문기관명,      \t우편번호,\n  \t통반_번지,          수요기관주소,   전화번호,           물품관리관명,    \t소관구분,\n  \t지역코드,\t\t\t양허코드,\t\tFAX번호,\t\t\t운송거리,\t\t\t업태,\n  \t종목,               사업자등록번호,\t관할지청,\t\t\t특정관리기관,\n  \t삭제여부,입력일자)\n  \tvalues(\n\t?,?,?,?,?,\n\t?,?,?,?,?,\n\t?,?,?,?,?,\n\t?,?,?,?,\n\t'N',sysdate)";
        try {
            prepareStatement = connection.prepareStatement(s20);
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.setString(3, s3);
            prepareStatement.setString(4, s4);
            prepareStatement.setString(5, s5);
            prepareStatement.setString(6, s6);
            prepareStatement.setString(7, s7);
            prepareStatement.setString(8, s8);
            prepareStatement.setString(9, s9);
            prepareStatement.setString(10, s10);
            prepareStatement.setString(11, s11);
            prepareStatement.setString(12, s12);
            prepareStatement.setString(13, s13);
            prepareStatement.setString(14, s14);
            prepareStatement.setString(15, s15);
            prepareStatement.setString(16, s16);
            prepareStatement.setString(17, s17);
            prepareStatement.setString(18, s18);
            prepareStatement.setString(19, s19);
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("syn_수요기관 입력 갯수 1이 아님");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_SUYOINFO.updateSuyoInfo() :" + ex.getMessage());
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
