// 
// Decompiled by Procyon v0.5.30
// 

package common.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;

public class EP_COB_GTQ805
{
    public String selectOptName(final String codegubun, final String code) {
        Trx resource = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String result = null;
        Label_0416: {
            try {
                resource = new Trx(this, "bid");
                conn = resource.getConnection();
                stmt = conn.createStatement();
                rset = stmt.executeQuery("select substrb(nvl(코드명,' '),1) from 공동코드 where 코드구분 = '" + codegubun + "' and 코드 = '" + code + "' and 사용여부 = 'Y'");
                if (rset.next()) {
                    result = rset.getString(1);
                    break Label_0416;
                }
                result = " ";
            }
            catch (SQLException exc) {
                Log.debug("EP_COB_GTQ805.selectOptName block SQLException : 코드구분=" + codegubun + ",코드=" + code + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                exc.printStackTrace();
                Log.debug("------------------------------------------------------------------------------------");
            }
            catch (Exception exc2) {
                Log.debug("EP_COB_GTQ805.selectOptName block Exception : 코드구분=" + codegubun + ",코드=" + code + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc2.toString());
                exc2.printStackTrace();
                Log.debug("------------------------------------------------------------------------------------");
            }
            finally {
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (Exception ex) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex2) {}
                }
                if (conn != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        if (rset != null) {
            try {
                rset.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (conn != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return result;
    }
    
    public String getSuyoInfo(final String gubun, final String code) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String result = null;
        Trx resource = null;
        Label_1063: {
            try {
                resource = new Trx(this, "bid");
                conn = resource.getConnection();
                stmt = conn.createStatement();
                if (gubun.equalsIgnoreCase("name")) {
                    switch (code.length()) {
                        case 8: {
                            rset = stmt.executeQuery("select substrb(nvl(상호명,' '),1) from syn_사용_조달업체마스터 where 사업자등록번호 = '" + code + "'");
                            break;
                        }
                        case 10: {
                            rset = stmt.executeQuery("select substrb(nvl(업체명,' '),1) from 방출업체_M where 사업자등록번호 = '" + code + "'");
                            break;
                        }
                        default: {
                            rset = stmt.executeQuery("select substrb(nvl(공공기관명_전체,' '),1) from syn_사용_공공기관마스터 where 공공기관코드 = '" + code + "' ");
                            break;
                        }
                    }
                }
                else if (gubun.equalsIgnoreCase("addr")) {
                    switch (code.length()) {
                        case 8: {
                            rset = stmt.executeQuery("select substrb(nvl(주소,' '),1) from syn_사용_조달업체마스터 where 사업자등록번호 = '" + code + "'");
                            break;
                        }
                        case 10: {
                            break;
                        }
                        default: {
                            rset = stmt.executeQuery("select substrb(nvl(주소,' '),1) from syn_사용_공공기관마스터 where 공공기관코드 = substr('" + code + "',1,9)");
                            break;
                        }
                    }
                }
                else if (gubun.equalsIgnoreCase("zipcode")) {
                    switch (code.length()) {
                        case 8: {
                            rset = stmt.executeQuery("select substrb(nvl(우편번호,' '),1) from syn_사용_조달업체마스터 where 사업자등록번호 = '" + code + "'");
                            break;
                        }
                        case 10: {
                            break;
                        }
                        default: {
                            rset = stmt.executeQuery("select substrb(nvl(우편번호,' '),1) from syn_사용_공공기관마스터 where 공공기관코드 = '" + code + "'");
                            break;
                        }
                    }
                }
                else if (gubun.equalsIgnoreCase("daepyo")) {
                    switch (code.length()) {
                        case 8: {
                            rset = stmt.executeQuery("select substrb(nvl(대표자명,' '),1) from syn_사용_대표자 where 사업자등록번호 = '" + code + "'");
                            break;
                        }
                        case 10: {
                            break;
                        }
                        default: {
                            rset = stmt.executeQuery("select ' ' from syn_사용_공공기관마스터 where 공공기관코드 = substr('" + code + "',1,9)");
                            rset.next();
                            break;
                        }
                    }
                }
                else if (gubun.equalsIgnoreCase("tel")) {
                    switch (code.length()) {
                        case 8: {
                            rset = stmt.executeQuery("select substrb(nvl(전화번호,' '),1) from syn_사용_조달업체마스터 where 사업자등록번호 = '" + code + "'");
                            break;
                        }
                        case 10: {
                            break;
                        }
                        default: {
                            rset = stmt.executeQuery("select substrb(nvl(전화번호,' '),1) from syn_사용_공공기관마스터 where 공공기관코드 = '" + code + "'");
                            break;
                        }
                    }
                }
                else {
                    switch (code.length()) {
                        case 8: {
                            rset = stmt.executeQuery("select substrb(nvl(FAX번호,' '),1) from syn_사용_조달업체마스터 where 사업자등록번호 = '" + code + "'");
                            break;
                        }
                        case 10: {
                            rset.next();
                            break;
                        }
                        default: {
                            rset = stmt.executeQuery("select substrb(nvl(팩스번호,' '),1) from syn_사용_공공기관마스터 where 공공기관코드 = substr('" + code + "'");
                            break;
                        }
                    }
                }
                if (rset.next()) {
                    result = rset.getString(1);
                    break Label_1063;
                }
                result = " ";
            }
            catch (SQLException exc) {
                Log.debug("EP_COB_GTQ805.getSuyoInfo block SQLException : 구분값=" + gubun + ",코드=" + code + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                exc.printStackTrace();
                Log.debug("------------------------------------------------------------------------------------");
            }
            catch (Exception exc2) {
                Log.debug("EP_COB_GTQ805.getSuyoInfo block Exception : 구분값=" + gubun + ",코드=" + code + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc2.toString());
                exc2.printStackTrace();
                Log.debug("------------------------------------------------------------------------------------");
            }
            finally {
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (Exception ex) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex2) {}
                }
                if (conn != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        if (rset != null) {
            try {
                rset.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (conn != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return result;
    }
    
    public String getAcctName(final String upch_code, final String bank_code, final String acct_code, final String acct_ser) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String result = null;
        Trx resource = null;
        Label_0469: {
            try {
                resource = new Trx(this, "bid");
                conn = resource.getConnection();
                stmt = conn.createStatement();
                rset = stmt.executeQuery("select substrb(nvl(예금주,' '),1) from SYN_지불_입금계좌 where 기관코드 = '" + upch_code + "'" + " and 은행코드 = '" + bank_code + "' and 계좌번호 = '" + acct_code + "'" + " 계좌순번 ='" + acct_ser + "'");
                if (rset.next()) {
                    result = rset.getString(1);
                    break Label_0469;
                }
                result = " ";
            }
            catch (SQLException exc) {
                Log.debug("EP_COB_GTQ805.getAcctName block SQLException : 사업자등록번호=" + upch_code + ",은행코드=" + bank_code + ",업체계좌번호=" + acct_code + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                exc.printStackTrace();
                Log.debug("------------------------------------------------------------------------------------");
            }
            catch (Exception exc2) {
                Log.debug("EP_COB_GTQ805.getAcctName block Exception : 사업자등록번호=" + upch_code + ",은행코드=" + bank_code + ",업체계좌번호=" + acct_code + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc2.toString());
                exc2.printStackTrace();
                Log.debug("------------------------------------------------------------------------------------");
            }
            finally {
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (Exception ex) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex2) {}
                }
                if (conn != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        if (rset != null) {
            try {
                rset.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (conn != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return result;
    }
    
    public String getPoomName(final String poom_code) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String result = null;
        Trx resource = null;
        Label_0380: {
            try {
                resource = new Trx(this, "cust");
                conn = resource.getConnection();
                stmt = conn.createStatement();
                rset = stmt.executeQuery("select substrb(nvl(품명,' '),1) from 물품분류 where 물품분류번호 = '" + poom_code + "'");
                if (rset.next()) {
                    result = rset.getString(1);
                    break Label_0380;
                }
                result = " ";
            }
            catch (SQLException exc) {
                Log.debug("EP_COB_GTQ805.getPoomName block SQLException : 물품분류번호=" + poom_code + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                exc.printStackTrace();
                Log.debug("------------------------------------------------------------------------------------");
            }
            catch (Exception exc2) {
                Log.debug("EP_COB_GTQ805.getPoomName block Exception : 물품분류번호=" + poom_code + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc2.toString());
                exc2.printStackTrace();
                Log.debug("------------------------------------------------------------------------------------");
            }
            finally {
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (Exception ex) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex2) {}
                }
                if (conn != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        if (rset != null) {
            try {
                rset.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (conn != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return result;
    }
    
    public String getJichName(final String jich_code) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String result = null;
        Trx resource = null;
        Label_0380: {
            try {
                resource = new Trx(this, "cust");
                conn = resource.getConnection();
                stmt = conn.createStatement();
                rset = stmt.executeQuery("select substrb(nvl(지청명,' '),1) from 지청 where 지청구분 = '" + jich_code + "'");
                if (rset.next()) {
                    result = rset.getString(1);
                    break Label_0380;
                }
                result = " ";
            }
            catch (SQLException exc) {
                Log.debug("EP_COB_GTQ805.getJichName block SQLException : 지청구분=" + jich_code + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                exc.printStackTrace();
                Log.debug("------------------------------------------------------------------------------------");
            }
            catch (Exception exc2) {
                Log.debug("EP_COB_GTQ805.getJichName block Exception : 지청구분=" + jich_code + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc2.toString());
                exc2.printStackTrace();
                Log.debug("------------------------------------------------------------------------------------");
            }
            finally {
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (Exception ex) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex2) {}
                }
                if (conn != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        if (rset != null) {
            try {
                rset.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (conn != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return result;
    }
    
    public String getBankName(final String bank_code) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String result = null;
        Trx resource = null;
        Label_0380: {
            try {
                resource = new Trx(this, "bid");
                conn = resource.getConnection();
                stmt = conn.createStatement();
                rset = stmt.executeQuery("select 코드명 은행명 from 공동코드 where 코드구분 = 'J06' and 코드 = '" + bank_code + "' and 사용여부 = 'Y' ");
                if (rset.next()) {
                    result = rset.getString(1);
                    break Label_0380;
                }
                result = " ";
            }
            catch (SQLException exc) {
                Log.debug("EP_COB_GTQ805.getBankName block SQLException : 은행우체국코드=" + bank_code + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                exc.printStackTrace();
                Log.debug("------------------------------------------------------------------------------------");
            }
            catch (Exception exc2) {
                Log.debug("EP_COB_GTQ805.getBankName block Exception : 은행우체국코드=" + bank_code + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc2.toString());
                exc2.printStackTrace();
                Log.debug("------------------------------------------------------------------------------------");
            }
            finally {
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (Exception ex) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex2) {}
                }
                if (conn != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        if (rset != null) {
            try {
                rset.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (conn != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return result;
    }
    
    public String getCurrDate() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String result = null;
        Trx resource = null;
        Label_0313: {
            try {
                resource = new Trx(this, "bid");
                conn = resource.getConnection();
                stmt = conn.createStatement();
                rset = stmt.executeQuery("select to_char(sysdate, 'yyyy/mm/dd') from dual");
                if (rset.next()) {
                    result = rset.getString(1);
                    break Label_0313;
                }
                result = " ";
            }
            catch (SQLException exc) {
                Log.debug("EP_COB_GTQ805.getCurrDate block SQLException : ");
                Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                exc.printStackTrace();
                Log.debug("------------------------------------------------------------------------------------");
            }
            catch (Exception exc2) {
                Log.debug("EP_COB_GTQ805.getCurrDate block Exception : ");
                Log.debug("Exception발생 사유 : " + exc2.toString());
                exc2.printStackTrace();
                Log.debug("------------------------------------------------------------------------------------");
            }
            finally {
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (Exception ex) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex2) {}
                }
                if (conn != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        if (rset != null) {
            try {
                rset.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (conn != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return result;
    }
    
    public String getSawonName(String sabun) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String result = null;
        Trx resource = null;
        while (true) {
            if (sabun == null) {
                sabun = " ";
                Label_0387: {
                    try {
                        resource = new Trx(this, "bid");
                        conn = resource.getConnection();
                        stmt = conn.createStatement();
                        rset = stmt.executeQuery("select substrb(nvl(성명,' '),1) from syn_사용_사용자 where 사용자ID = '" + sabun + "'");
                        if (rset.next()) {
                            result = rset.getString(1);
                            break Label_0387;
                        }
                        result = " ";
                    }
                    catch (SQLException exc) {
                        Log.debug("EP_COB_GTQ805.getSawonName block SQLException : 사번=" + sabun + " 일때 발생함.");
                        Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                        exc.printStackTrace();
                        Log.debug("------------------------------------------------------------------------------------");
                    }
                    catch (Exception exc2) {
                        Log.debug("EP_COB_GTQ805.getSawonName block Exception : 사번=" + sabun + " 일때 발생함.");
                        Log.debug("Exception발생 사유 : " + exc2.toString());
                        exc2.printStackTrace();
                        Log.debug("------------------------------------------------------------------------------------");
                    }
                    finally {
                        if (rset != null) {
                            try {
                                rset.close();
                            }
                            catch (Exception ex) {}
                        }
                        if (stmt != null) {
                            try {
                                stmt.close();
                            }
                            catch (Exception ex2) {}
                        }
                        if (conn != null) {
                            try {
                                resource.close();
                            }
                            catch (Exception ex3) {}
                        }
                    }
                }
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (Exception ex4) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex5) {}
                }
                if (conn != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex6) {}
                }
                return result;
            }
            continue;
        }
    }
    
    public String getSaupName(String saup) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String result = null;
        Trx resource = null;
        while (true) {
            if (saup == null) {
                saup = " ";
                Label_0387: {
                    try {
                        resource = new Trx(this, "bid");
                        conn = resource.getConnection();
                        stmt = conn.createStatement();
                        rset = stmt.executeQuery("select substrb(nvl(상호명,' '),1) from syn_사용_조달업체마스터 where 사업자등록번호 = '" + saup + "'");
                        if (rset.next()) {
                            result = rset.getString(1);
                            break Label_0387;
                        }
                        result = " ";
                    }
                    catch (SQLException exc) {
                        Log.debug("EP_COB_GTQ805.getSawonName block SQLException : 사업자등록번호=" + saup + " 일때 발생함.");
                        Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                        exc.printStackTrace();
                        Log.debug("------------------------------------------------------------------------------------");
                    }
                    catch (Exception exc2) {
                        Log.debug("EP_COB_GTQ805.getSawonName block Exception : 사업자등록번호=" + saup + " 일때 발생함.");
                        Log.debug("Exception발생 사유 : " + exc2.toString());
                        exc2.printStackTrace();
                        Log.debug("------------------------------------------------------------------------------------");
                    }
                    finally {
                        if (rset != null) {
                            try {
                                rset.close();
                            }
                            catch (Exception ex) {}
                        }
                        if (stmt != null) {
                            try {
                                stmt.close();
                            }
                            catch (Exception ex2) {}
                        }
                        if (conn != null) {
                            try {
                                resource.close();
                            }
                            catch (Exception ex3) {}
                        }
                    }
                }
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (Exception ex4) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex5) {}
                }
                if (conn != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex6) {}
                }
                return result;
            }
            continue;
        }
    }
    
    public String[] getHolidays() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        int i = 0;
        String[] holidays = (String[])null;
        Trx resource = null;
        try {
            resource = new Trx(this, "bid");
            conn = resource.getConnection();
            stmt = conn.createStatement();
            rset = stmt.executeQuery("select count(*) from 공휴일 order by 일자");
            if (!rset.next()) {
                i = -1;
            }
            else {
                i = rset.getInt(1);
            }
            rset = stmt.executeQuery("select 일자 from 공휴일 order by 일자");
            if (i != -1) {
                holidays = new String[i];
                int k = 0;
                while (rset.next()) {
                    holidays[k] = rset.getString(1);
                    ++k;
                }
            }
        }
        catch (SQLException exc) {
            Log.debug("EP_COB_GTQ805.getHolidays block SQLException : ");
            Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
            exc.printStackTrace();
            Log.debug("------------------------------------------------------------------------------------");
        }
        catch (Exception exc2) {
            Log.debug("EP_COB_GTQ805.getHolidays block Exception : ");
            Log.debug("Exception발생 사유 : " + exc2.toString());
            exc2.printStackTrace();
            Log.debug("------------------------------------------------------------------------------------");
        }
        finally {
            if (rset != null) {
                try {
                    rset.close();
                }
                catch (Exception ex) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (Exception ex2) {}
            }
            if (conn != null) {
                try {
                    resource.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (rset != null) {
            try {
                rset.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (conn != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return holidays;
    }
    
    public String getSuyoName(String code) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String result = null;
        Trx resource = null;
        while (true) {
            if (code == null) {
                code = " ";
                Label_0389: {
                    try {
                        resource = new Trx(this, "bid");
                        conn = resource.getConnection();
                        stmt = conn.createStatement();
                        rset = stmt.executeQuery("select substrb(nvl(공공기관명_전체,' '),1) from syn_사용_공공기관마스터 where 공공기관코드 = '" + code + "'");
                        if (rset.next()) {
                            result = rset.getString(1);
                            break Label_0389;
                        }
                        result = " ";
                    }
                    catch (SQLException exc) {
                        Log.debug("EP_COB_GTQ805.getSuyoName block SQLException : 코드=" + code + " 일때 발생함.");
                        Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                        exc.printStackTrace();
                        Log.debug("------------------------------------------------------------------------------------");
                    }
                    catch (Exception exc2) {
                        Log.debug("EP_COB_GTQ805.getSuyoName block Exception : 코드=" + code + " 일때 발생함.");
                        Log.debug("Exception발생 사유 : " + exc2.toString());
                        exc2.printStackTrace();
                        Log.debug("------------------------------------------------------------------------------------");
                    }
                    finally {
                        if (rset != null) {
                            try {
                                rset.close();
                            }
                            catch (Exception ex) {}
                        }
                        if (stmt != null) {
                            try {
                                stmt.close();
                            }
                            catch (Exception ex2) {}
                        }
                        if (conn != null) {
                            try {
                                resource.close();
                            }
                            catch (Exception ex3) {}
                        }
                    }
                }
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (Exception ex4) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex5) {}
                }
                if (conn != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex6) {}
                }
                return result;
            }
            continue;
        }
    }
    
    public String getCurrDateTime() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String result = null;
        Trx resource = null;
        Label_0314: {
            try {
                resource = new Trx(this);
                conn = resource.getConnection();
                stmt = conn.createStatement();
                rset = stmt.executeQuery("select to_char(sysdate,'yyyy/mm/dd hh24:mi') from dual");
                if (rset.next()) {
                    result = rset.getString(1);
                    break Label_0314;
                }
                result = " ";
            }
            catch (SQLException exc) {
                Log.debug("EP_COB_GTQ805.getCurrDateTime block SQLException : ");
                Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                exc.printStackTrace();
                Log.debug("------------------------------------------------------------------------------------");
            }
            catch (Exception exc2) {
                Log.debug("EP_COB_GTQ805.getCurrDateTime block Exception : ");
                Log.debug("Exception발생 사유 : " + exc2.toString());
                exc2.printStackTrace();
                Log.debug("------------------------------------------------------------------------------------");
            }
            finally {
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (Exception ex) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex2) {}
                }
                if (conn != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        if (rset != null) {
            try {
                rset.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (conn != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return result;
    }
    
    public String retNull(String s) {
        try {
            if (s == null || s.equals("")) {
                s = null;
            }
        }
        catch (Exception e) {
            System.out.println("retNull Err : " + e.toString());
            e.printStackTrace();
        }
        return s;
    }
    
    public String[] retNulls(final String[] s) {
        try {
            if (s != null) {
                for (int i = 0; i < s.length; ++i) {
                    if (s[i] == null || s[i].equals("")) {
                        s[i] = null;
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("retNulls Err : " + e.toString());
            e.printStackTrace();
        }
        return s;
    }
    
    public String retSpace(String s) {
        try {
            if (s == null || s.equals("")) {
                s = "";
            }
        }
        catch (Exception e) {
            System.out.println("retSpace Err : " + e.toString());
            e.printStackTrace();
        }
        return s;
    }
    
    public String getCreditor(final String id) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String result = null;
        Trx resource = null;
        Label_0386: {
            try {
                resource = new Trx(this, "bid");
                conn = resource.getConnection();
                stmt = conn.createStatement();
                rset = stmt.executeQuery("select substrb(nvl(a.채권자명,' '),1) from syn_사용_공공기관마스터 a, syn_사용_사용자 b  \twhere \tb.사용자ID = '" + id + "'\t\t\t" + "\tand\t\tb.사용자구분 = 'g'\t\t\t\t" + "\tand \tb.마스터코드 = a.공공기관코드 \t");
                if (rset.next()) {
                    result = rset.getString(1);
                    break Label_0386;
                }
                result = " ";
            }
            catch (SQLException exc) {
                Log.debug("EP_COB_GTQ805.getCreditor block SQLException : 로그인id=" + id + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                Log.debug("------------------------------------------------------------------------------------");
            }
            catch (Exception exc2) {
                Log.debug("EP_COB_GTQ805.getCreditor block Exception : 로그인id=" + id + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc2.toString());
                Log.debug("------------------------------------------------------------------------------------");
            }
            finally {
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (Exception ex) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex2) {}
                }
                if (conn != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        if (rset != null) {
            try {
                rset.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (conn != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return result;
    }
    
    public String GongdongCode(final String codegubun, final String code, final Connection conn) throws SQLException {
        final String sql = "select substrb(nvl(코드명,' '),1) from 공동코드 where 코드구분=? and 코드=? ";
        String returnResult = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, codegubun);
        pstmt.setString(2, code);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            returnResult = rs.getString(1);
        }
        if (rs != null) {
            rs.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }
        return returnResult;
    }
    
    public String damdangName(final String code, final Connection conn) throws SQLException {
        final String sql = "select substrb(nvl(담당자명,' '),1) from syn_사용_사용자 where 사용자ID='" + code + "'";
        String returnResult = "";
        Statement stmt = null;
        ResultSet rs = null;
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            returnResult = rs.getString(1);
        }
        rs.close();
        stmt.close();
        return returnResult;
    }
    
    public String[] getSuyoCodeName(final String id) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        final String[] result = new String[2];
        Trx resource = null;
        Label_0417: {
            try {
                resource = new Trx(this, "bid");
                conn = resource.getConnection();
                stmt = conn.createStatement();
                final String sql = "select a.공공기관코드, substrb(nvl(a.공공기관명_전체,' '),1) from syn_사용_공공기관마스터 a,  syn_사용_사용자 b  \twhere \tb.사용자ID ='" + id + "'\t\t\t\t\t\t" + "\tand\t\tb.사용자구분 = 'g'\t\t\t\t\t\t\t" + "\tand \tb.마스터코드 = a.공공기관코드\t\t\t\t";
                rset = stmt.executeQuery(sql);
                if (rset.next()) {
                    result[0] = rset.getString(1);
                    result[1] = rset.getString(2);
                    break Label_0417;
                }
                result[1] = (result[0] = "");
            }
            catch (SQLException exc) {
                Log.debug("EP_COB_GTQ805.getSuyoCodeName block SQLException : 로그인id=" + id + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                Log.debug("------------------------------------------------------------------------------------");
            }
            catch (Exception exc2) {
                Log.debug("EP_COB_GTQ805.getSuyoCodeName block Exception : 로그인id=" + id + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc2.toString());
                Log.debug("------------------------------------------------------------------------------------");
            }
            finally {
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (Exception ex) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex2) {}
                }
                if (conn != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        if (rset != null) {
            try {
                rset.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (conn != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return result;
    }
    
    public String[] getDamdangInfo(final String id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        final String[] result = new String[7];
        Trx resource = null;
        Label_0493: {
            try {
                resource = new Trx(this, "bid");
                con = resource.getConnection();
                final String sql = "select 마스터코드 , 담당자명, 담당자전화번호, 담당자팩스번호,  담당자이동전화번호, 담당자메일주소, 담당자부서명 \tfrom syn_사용_사용자\t\twhere 사용자ID = ? ";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, id);
                rset = pstmt.executeQuery();
                if (rset.next()) {
                    result[0] = rset.getString(1);
                    result[1] = rset.getString(2);
                    result[2] = rset.getString(3);
                    result[3] = rset.getString(4);
                    result[4] = rset.getString(5);
                    result[5] = rset.getString(6);
                    result[6] = rset.getString(7);
                    break Label_0493;
                }
                result[0] = "";
                result[2] = (result[1] = "");
                result[4] = (result[3] = "");
                result[6] = (result[5] = "");
            }
            catch (SQLException exc) {
                Log.debug("EP_COB_GTQ805.getDamdangInfo block SQLException : 로그인id=" + id + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                Log.debug("------------------------------------------------------------------------------------");
            }
            catch (Exception exc2) {
                Log.debug("EP_COB_GTQ805.getDamdangInfo block Exception : 로그인id=" + id + " 일때 발생함.");
                Log.debug("Exception발생 사유 : " + exc2.toString());
                Log.debug("------------------------------------------------------------------------------------");
            }
            finally {
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (Exception ex) {}
                }
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    }
                    catch (Exception ex2) {}
                }
                if (con != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        if (rset != null) {
            try {
                rset.close();
            }
            catch (Exception ex4) {}
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return result;
    }
    
    public String beforeMonth(String str) {
        final String month = str.substring(5, 7);
        final String year = str.substring(0, 4);
        int mm = Integer.parseInt(month);
        int yyyy = Integer.parseInt(year);
        yyyy = ((mm == 1) ? (yyyy - 1) : yyyy);
        mm = ((mm == 1) ? 12 : (mm - 1));
        if (mm < 10) {
            str = (yyyy + "/" + "0" + mm + "/" + str.substring(8, 10)).trim();
        }
        else {
            str = (yyyy + "/" + mm + "/" + str.substring(8, 10)).trim();
        }
        return str;
    }
}
