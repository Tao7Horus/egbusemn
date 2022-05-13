// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import common.Log;
import common.CommDbQuery;
import common.OneRowEntity;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class UM_RAB_MastSupplier
{
    public String getChodalReceptionNumber(final Connection con) throws Exception {
        String result = "";
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        final String sql = "SELECT to_char(sysdate,'yyyymmddHHMMSS')||lpad((substr(max(RECV_NO), 5, 10)+1), 2, '0') FROM UM_SUPPLIER_ENTER_MAST";
        try {
            pstmt = con.prepareStatement(sql);
            rest = pstmt.executeQuery();
            if (!rest.next()) {
                throw new Exception("Không tồn tại mã số tiếp nhận");
            }
            result = rest.getString(1);
        }
        finally {
            try {
                if (rest != null) {
                    rest.close();
                }
            }
            catch (Exception ex) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (rest != null) {
                rest.close();
            }
        }
        catch (Exception ex3) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex4) {}
        return result;
    }
    
    public OneRowEntity selectDetail(final String BizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, to_char(COMMENCEMENT_DT, 'dd/MM/yyyy') COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, to_char(REG_DT, 'dd/MM/yyyy') REG_DT, UPDATE_DT, DOC_NO, to_char(DOC_CREAT_DT, 'dd/mm/yy') DOC_CREAT_DT, RECV_NO");
            sql.append(" from UM_SUPPLIER_ENTER_MAST");
            sql.append(" where BIZ_REG_NO = ? ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { BizRegNo }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + BizRegNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public OneRowEntity selectDetail2(final String BizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, BIZ_NM, BIZ_EN_NM ,"//BID_COUNT, "
            		+ "COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX,");
            sql.append(" NATIONALITY, AREA_CD, WEBSITE ,CAPITAL, BIZ_CLS, BIZ_CLS1, DECODE(SIGN(TO_DATE(PM_DATE,'DD/MM/YYYY')-SYSDATE),-1,'Tài khoản chưa nộp phí duy trì. Sau ngày '||DECODE(SIGN(TO_DATE(PM_DATE,'DD/MM/YYYY')-TO_DATE('31/03/2016','DD/MM/YYYY')),-1,'31/03/2016',PM_DATE)||' tài khoản sẽ hết hiệu lực.','Tài khoản còn hiệu lực đến ngày '||PM_DATE) PM_DATE ");
            sql.append(" FROM (");
            sql.append(" select BIZ_REG_NO, BIZ_NM, BIZ_EN_NM ,");
            //sql.append(" (SELECT COUNT(BIZ_REG_NO) FROM USEMN.SYN_XML_RECV WHERE BIZ_REG_NO=? ) BID_COUNT, ");
            sql.append(" to_char(COMMENCEMENT_DT, 'dd/MM/yyyy') COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, ");
            sql.append(" NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, USEMN.GET_EXTEND_PAYMENT_DATE(BIZ_REG_NO) PM_DATE");
            sql.append(" from UM_SUPPLIER_ENTER_MAST");
            sql.append(" where BIZ_REG_NO = ? ");
            sql.append(" )");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { BizRegNo}, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + BizRegNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public OneRowEntity selectDetailRec(final String BizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, to_char(COMMENCEMENT_DT, 'dd/MM/yyyy') COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, to_char(REG_DT, 'dd/MM/yyyy') REG_DT, UPDATE_DT, DOC_NO, to_char(DOC_CREAT_DT, 'dd/mm/yy') DOC_CREAT_DT");
            sql.append(" from UM_REC_SUPPLIER_ENTER_MAST");
            sql.append(" where BIZ_REG_NO = ? ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { BizRegNo }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + BizRegNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public OneRowEntity selectDetail(final String BizRegNo, final String recvCode, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, WEBSITE, CAPITAL, BIZ_CLS, RECV_NO, REG_YN, REJECTED_RSON, REG_DT, UPDATE_DT, PERMIT_BRANCH, DOC_NO, DOC_CREAT_DT ");
            sql.append(" from UM_SUPPLIER_ENTER_MAST t1 ");
            sql.append(" where t1.BIZ_REG_NO = ? ");
            sql.append(" and t1.RECV_NO = ? ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { BizRegNo, recvCode }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + BizRegNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public int maxCount(final String bizRegNo, final Connection conn) {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        final int count = 0;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" select count (*) from UM_SUPPLIER_ENTER_MAST ");
            sql.append(" where BIZ_REG_NO = ? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            final ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + bizRegNo + ") : " + e.toString());
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex2) {}
        }
        return count;
    }
    
    public void delete(final String BizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE FROM UM_SUPPLIER_ENTER_MAST ");
            sql.append(" where BIZ_REG_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, BizRegNo);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Xóa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + BizRegNo + ") : " + e.toString());
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void save(final String bizRegNo, final String bizName, final String bizEnName, final String commDate, final String addr, final String phoneNo, final String employeeCount, final String fax, final String nationality, final String area, final String website, final String capital, final String bizCls, final String bizCls1, final String permitBranch, final String docNo, final String recvNo, final String test_option_yn, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        Log.debug("****bizRegNo: " + bizRegNo);
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" insert into UM_SUPPLIER_ENTER_MAST (BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, REG_DT, PERMIT_BRANCH, DOC_NO, DOC_CREAT_DT, RECV_NO, TEST_OPTION_YN) ");
            sql.append(" values (?, ?, ?, to_date(?,'dd/MM/yyyy'), ?, ?, to_number(?), ?, ?, ?, ?, to_number(?), ?, ?, SYSDATE, ?, ?, SYSDATE, ?, ?)");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, bizName);
            pstmt.setString(3, bizEnName);
            pstmt.setString(4, commDate);
            pstmt.setString(5, addr);
            pstmt.setString(6, phoneNo);
            pstmt.setString(7, employeeCount);
            pstmt.setString(8, fax);
            pstmt.setString(9, nationality);
            pstmt.setString(10, area);
            pstmt.setString(11, website);
            pstmt.setString(12, capital);
            pstmt.setString(13, bizCls);
            pstmt.setString(14, bizCls1);
            pstmt.setString(15, permitBranch);
            pstmt.setString(16, docNo);
            pstmt.setString(17, recvNo);
            pstmt.setString(18, test_option_yn);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Lỗi đăng ký]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".save(" + bizRegNo + ", " + bizName + ", " + commDate + ") : " + e.toString());
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
    }
    
    /**
     * Chèn dữ liệu vào bảng UM_SUPPLIER_ENTER_MAST ở bước phê duyệt thành công đăng ký nhà thầu, lấy thông tin từ bảng UM_REC_SUPPLIER_ENTER_MAST
     * @param bizRegNo
     * @param bizName
     * @param bizEnName
     * @param commDate
     * @param addr
     * @param phoneNo
     * @param employeeCount
     * @param fax
     * @param nationality
     * @param area
     * @param website
     * @param capital
     * @param bizCls
     * @param bizCls1
     * @param permitBranch
     * @param docNo
     * @param recvNo
     * @param test_option_yn
     * @param conn
     * @throws Exception
     */
    public void saveFromRec(final String bizRegNo, final String test_option_yn, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        Log.debug("****bizRegNo: " + bizRegNo);
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append("INSERT INTO UM_SUPPLIER_ENTER_MAST ");
            sql.append("	(BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, REG_DT, PERMIT_BRANCH, DOC_NO, DOC_CREAT_DT, RECV_NO, TEST_OPTION_YN) ");
            sql.append("SELECT ");
            sql.append("	 BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, SYSDATE, PERMIT_BRANCH, DOC_NO, SYSDATE, RECV_NO, ?  ");
            sql.append("FROM UM_REC_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO = ?");
            
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, test_option_yn);
            pstmt.setString(2, bizRegNo);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Lỗi ghi nhận thông tin đăng ký chung của nhà thầu");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".saveFromRec(" + bizRegNo + ") : " + e.toString());
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {
                	Log.debug(String.valueOf(this.getClass().getName()) + ".saveFromRec(" + bizRegNo + ") : " + ex.toString());
                    throw ex;
                }
            }
        }
    }
    
    public void update(final String bizRegNo, final String bizName, final String bizEnName, final String commDate, final String addr, final String phoneNo, final String employeeCount, final String fax, final String nationality, final String area, final String website, final String capital, final String bizCls, final String bizCls1, final String permitBranch, final String docNo, final String recvNo, final String test_option_yn, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            //Khong thuc hien update TEST_OPTION_YN
            sql.append(" UPDATE UM_SUPPLIER_ENTER_MAST   SET BIZ_REG_NO = ?, BIZ_NM = ? , BIZ_EN_NM = ?, COMMENCEMENT_DT = to_date(?,'dd/MM/yyyy'),  ADDR = ?,  PHONE_NO =? ,  EMPLOYEE_COUNT = ?, FAX= ?, NATIONALITY= ?, AREA_CD= ?, WEBSITE = ?, CAPITAL = ?, BIZ_CLS = ?, BIZ_CLS1 = ?, PERMIT_BRANCH = ?, DOC_NO= ?, RECV_NO = ?, " //TEST_OPTION_YN = ?,  
            			+ " UPDATE_DT = SYSDATE WHERE BIZ_REG_NO= ? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, bizName);
            pstmt.setString(3, bizEnName);
            pstmt.setString(4, commDate);
            pstmt.setString(5, addr);
            pstmt.setString(6, phoneNo);
            pstmt.setString(7, employeeCount);
            pstmt.setString(8, fax);
            pstmt.setString(9, nationality);
            pstmt.setString(10, area);
            pstmt.setString(11, website);
            pstmt.setString(12, capital);
            pstmt.setString(13, bizCls);
            pstmt.setString(14, bizCls1);
            pstmt.setString(15, permitBranch);
            pstmt.setString(16, docNo);
            pstmt.setString(17, recvNo);
//            pstmt.setString(18, test_option_yn);
            pstmt.setString(18, bizRegNo);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Lỗi đăng ký]");
            }
            //Luon cap nhat thong tin rec_sup khi cap nhat thong tin dang ky
            CapNhatTTDangKyRecSupp capNhatTTDangKy = new CapNhatTTDangKyRecSupp();
            capNhatTTDangKy.capnhatTTDangKy(bizRegNo, bizName, bizEnName, addr, phoneNo, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".save(" + bizRegNo + ", " + bizName + ", " + commDate + ") : " + e.toString());
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void modify(final String bizRegNo, final String bizName, final String bizEnName, final String commDate, final String addr, final String phoneNo, final String employeeCount, final String fax, final String nationality, final String website, final String capital, final String bizCls, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" update UM_SUPPLIER_ENTER_MAST set BIZ_NM=?, BIZ_EN_NM=?, COMMENCEMENT_DT = to_date(?,'dd/MM/yyyy'), ADDR= ?, PHONE_NO=?, EMPLOYEE_COUNT=to_number(?), FAX=?, NATIONALITY=?, WEBSITE=?, CAPITAL=to_number (?), BIZ_CLS=? ");
            sql.append(" where BIZ_REG_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizName);
            pstmt.setString(2, bizEnName);
            pstmt.setString(3, commDate);
            pstmt.setString(4, addr);
            pstmt.setString(5, phoneNo);
            pstmt.setString(6, employeeCount);
            pstmt.setString(7, fax);
            pstmt.setString(8, nationality);
            pstmt.setString(9, website);
            pstmt.setString(10, capital);
            pstmt.setString(11, bizCls);
            pstmt.setString(12, bizRegNo);
            Log.debug(bizName);
            Log.debug(bizEnName);
            Log.debug(commDate);
            Log.debug(addr);
            Log.debug(phoneNo);
            Log.debug(employeeCount);
            Log.debug(fax);
            Log.debug(nationality);
            Log.debug(website);
            Log.debug(capital);
            Log.debug(bizCls);
            Log.debug(bizRegNo);
            pstmt.executeUpdate();
            
            //Luon cap nhat thong tin rec_sup khi cap nhat thong tin dang ky
            CapNhatTTDangKyRecSupp capNhatTTDangKy = new CapNhatTTDangKyRecSupp();
            capNhatTTDangKy.capnhatTTDangKy(bizRegNo, bizName, bizEnName, addr, phoneNo, conn);
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void modifyWithManagerID(final String bizRegNo, final String bizName, final String bizEnName, final String commDate, final String addr, final String phoneNo, final String employeeCount, final String fax, final String nationality, final String website, final String capital, final String bizCls, final Connection conn, final String managerid) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" update UM_SUPPLIER_ENTER_MAST set BIZ_NM=?, BIZ_EN_NM=?, COMMENCEMENT_DT = to_date(?,'dd/MM/yyyy'), ADDR= ?, PHONE_NO=?, EMPLOYEE_COUNT=to_number(?), FAX=?, NATIONALITY=?, WEBSITE=?, CAPITAL=to_number (?), BIZ_CLS=?, MANAGER_ID = ? ");
            sql.append(" where BIZ_REG_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizName);
            pstmt.setString(2, bizEnName);
            pstmt.setString(3, commDate);
            pstmt.setString(4, addr);
            pstmt.setString(5, phoneNo);
            pstmt.setString(6, employeeCount);
            pstmt.setString(7, fax);
            pstmt.setString(8, nationality);
            pstmt.setString(9, website);
            pstmt.setString(10, capital);
            pstmt.setString(11, bizCls);
            pstmt.setString(12, managerid);
            pstmt.setString(13, bizRegNo);
            Log.debug(bizName);
            Log.debug(bizEnName);
            Log.debug(commDate);
            Log.debug(addr);
            Log.debug(phoneNo);
            Log.debug(employeeCount);
            Log.debug(fax);
            Log.debug(nationality);
            Log.debug(website);
            Log.debug(capital);
            Log.debug(bizCls);
            Log.debug(bizRegNo);
            pstmt.executeUpdate();
            
            //Luon cap nhat thong tin rec_sup khi cap nhat thong tin dang ky
            CapNhatTTDangKyRecSupp capNhatTTDangKy = new CapNhatTTDangKyRecSupp();
            capNhatTTDangKy.capnhatTTDangKy(bizRegNo, bizName, bizEnName, addr, phoneNo, conn);
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
    }
    
    /**
     * ThachPN 20160516 - Update thong tin phan loai doanh nghiep
     * @param bizRegNo
     * @param bizName
     * @param bizEnName
     * @param commDate
     * @param addr
     * @param phoneNo
     * @param employeeCount
     * @param fax
     * @param nationality
     * @param website
     * @param capital
     * @param bizCls
     * @param conn
     * @param managerid
     * @param bizCls1 Doanh nghiep tu nhan, TNHH ...
     * @param provice Tinh, thanh pho
     * @throws Exception
     */
    public void modifyWithManagerID2(final String bizRegNo, final String bizName, final String bizEnName, final String commDate, final String addr, final String phoneNo, final String employeeCount, final String fax, final String nationality, final String website, final String capital, final String bizCls, final Connection conn, final String managerid, String bizCls1, String province) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" update UM_SUPPLIER_ENTER_MAST set BIZ_NM=?, BIZ_EN_NM=?, COMMENCEMENT_DT = to_date(?,'dd/MM/yyyy'), ADDR= ?, PHONE_NO=?, EMPLOYEE_COUNT=to_number(?), FAX=?, NATIONALITY=?, WEBSITE=?, CAPITAL=to_number (?), BIZ_CLS=?, MANAGER_ID = ?, BIZ_CLS1 = ?,  AREA_CD = ?");
            sql.append(" where BIZ_REG_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizName);
            pstmt.setString(2, bizEnName);
            pstmt.setString(3, commDate);
            pstmt.setString(4, addr);
            pstmt.setString(5, phoneNo);
            pstmt.setString(6, employeeCount);
            pstmt.setString(7, fax);
            pstmt.setString(8, nationality);
            pstmt.setString(9, website);
            pstmt.setString(10, capital);
            pstmt.setString(11, bizCls);
            pstmt.setString(12, managerid);
            pstmt.setString(13, bizCls1);//ThachPN 20160516
            pstmt.setString(14, province);//ThachPN 20160606
            pstmt.setString(15, bizRegNo);            
            Log.debug(bizName);
            Log.debug(bizEnName);
            Log.debug(commDate);
            Log.debug(addr);
            Log.debug(phoneNo);
            Log.debug(employeeCount);
            Log.debug(fax);
            Log.debug(nationality);
            Log.debug(website);
            Log.debug(capital);
            Log.debug(bizCls);
            Log.debug(bizCls1);
            Log.debug(province);
            Log.debug(bizRegNo);            
            pstmt.executeUpdate();
            
            //Luon cap nhat thong tin rec_sup khi cap nhat thong tin dang ky
            CapNhatTTDangKyRecSupp capNhatTTDangKy = new CapNhatTTDangKyRecSupp();
            capNhatTTDangKy.capnhatTTDangKy(bizRegNo, bizName, bizEnName, addr, phoneNo, conn);
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public OneRowEntity selectUpdate(final String bizRegNo, final Connection conn) throws Exception {
        final String sql = " SELECT BIZ_EN_NM, EMPLOYEE_COUNT, PHONE_NO, FAX, WEBSITE, MANAGER_ID, TO_CHAR(UPDATE_DT, 'YYYY-MM-DD HH24:MI:SS') UPDATE_DT FROM UM_SUPPLIER_ENTER_MAST_HIST a  WHERE VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = a.BIZ_REG_NO ) AND BIZ_REG_NO= ? ";
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            return new CommDbQuery().getOneRowList(this, sql, new String[] { bizRegNo }, conn);
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            throw e;
        }
    }
    
    public void MasterUpdate(final String saupNo, final String eSangho, final String employeeNo, final String tel, final String fax, final String homepage, final String procID, final String updateDt, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " UPDATE UM_SUPPLIER_ENTER_MAST SET  BIZ_EN_NM = ?,  EMPLOYEE_COUNT = ?,  PHONE_NO = ?,  FAX = ?, WEBSITE = ?,   MANAGER_ID = ?,    UPDATE_DT = TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')  WHERE  BIZ_REG_NO = ? ";
            psmt = con.prepareStatement(query);
            psmt.setString(1, eSangho);
            psmt.setString(2, employeeNo);
            psmt.setString(3, tel);
            psmt.setString(4, fax);
            psmt.setString(5, homepage);
            psmt.setString(6, procID);
            psmt.setString(7, updateDt);
            psmt.setString(8, saupNo);
            psmt.executeUpdate();
        }
        catch (Exception exc) {
            Log.errors(this, exc, "");
            throw exc;
        }
        finally {
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (Exception e) {
                    Log.errors(this, e, "");
                }
            }
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception e) {
                Log.errors(this, e, "");
            }
        }
    }
}
