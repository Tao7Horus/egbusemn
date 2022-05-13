// 
// Decompiled by Procyon v0.5.30
// 

package exms.usemn;

import org.w3c.dom.Element;
import oracle.xml.parser.v2.NSResolver;
import oracle.xml.parser.v2.XMLElement;
import java.util.StringTokenizer;
import secu.lib.CertInfo;
import secu.lib.Secu;
import oracle.xml.parser.v2.XMLDocument;
import java.sql.SQLException;
import java.io.InputStream;
import java.io.FileInputStream;
import oracle.xml.parser.v2.DOMParser;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class RcvGsacid
{
    private Connection pconn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private StringBuffer LogMsg;
    private String xmlFileName;
    private DOMParser parser;
    private String sql;
    private String tbl_name;
    private String Rvalue;
    private String PersonalAuth;
    private String SoVanBan;
    private String Country_code;
    private String Firm_engname;
    private String Open_date;
    private String Start_date;
    private String Busi_code;
    private String Manu_code;
    private String Busi_type;
    private String Regi_no;
    private String Org_class1;
    private String Org_class2;
    private String Org_year;
    private String Capital;
    private String Emp_no;
    private String Rec_date;
    private String Post_code;
    private String Area_code;
    private String Firm_FAX;
    private String Fac_exc;
    private String Serv_key;
    private String ContractPerformance;
    private String Index_no1;
    private String CEO_ssn;
    private String CEO_name;
    private String CEO_email;
    private String CEO_yn;
    private String CEO_pcs;
    private String CEO_type;
    private String branch_Index_no1;
    private String branch_Reg_no;
    private String branch_Firm_name;
    private String branch_Post_code;
    private String branch_Area_code;
    private String branch_Firm_FAX;
    private String branch_Firm_TEL;
    private String branch_Add1;
    private String branch_Add2;
    private String branch_CEO_ssn;
    private String branch_CEO_name;
    private String Index_no3;
    private String Item_name;
    private String Item_class_sup;
    private String Rec_sale_sup;
    private String Manu_yn_sup;
    private String Rep_yn_sup;
    private String Index_no4;
    private String Item_class_manu;
    private String Appr_no;
    private String Appr_org;
    private String Appr_date;
    private String Rec_sale_manu;
    private String Manu_yn_manu;
    private String Rep_yn_manu;
    private String Pro_Certi_code;
    private String Appr_start_date;
    private String Appr_end_date;
    private String Maun_org;
    private String App_name;
    private String Stand_class;
    private String Index_no5;
    private String Lic_code;
    private String Lic_no;
    private String Lic_date;
    private String Lic_valid;
    private String Able_amount;
    private String Basis_year;
    private String Rep_lic_yn;
    private String Index_no6;
    private String Emp_ssn;
    private String Emp_name;
    private String Emp_dept;
    private String Emp_title;
    private String Emp_TEL;
    private String Emp_email;
    private String Emp_cell;
    private String Emp_FAX;
    private String Bid_Persion;
    private String Reg_no;
    private String Reg_chg_kind;
    private String Reg_date;
    private String Firm_name;
    private String PCS_num;
    private String SMS_num;
    private String Firm_CEO;
    private String Confirm_off;
    private String post_no;
    private String Add1;
    private String Add2;
    private String Firm_TEL;
    private String Homepage;
    private String status;
    private String Accept_res;
    private String XML_loc;
    private String Doc_no;
    private String EMAIL;
    private String relayGo;
    private String Docu_no;
    private String newXML;
    private String _SENDCODE;
    private String _CONTYPE;
    private String unikey;
    private String AdminAgree;
    
    public RcvGsacid() {
        this.pconn = null;
        this.pstmt = null;
        this.rs = null;
        this.LogMsg = new StringBuffer(8192);
        this.parser = new DOMParser();
        this.sql = "";
        this.tbl_name = "UM_EDOC_STATE";
        this.Rvalue = "";
        this.PersonalAuth = "";
        this.SoVanBan = "";
        this.Country_code = "";
        this.Firm_engname = "";
        this.Open_date = "";
        this.Start_date = "";
        this.Busi_code = "";
        this.Manu_code = "";
        this.Busi_type = "";
        this.Regi_no = "";
        this.Org_class1 = "";
        this.Org_class2 = "";
        this.Org_year = "";
        this.Capital = "";
        this.Emp_no = "";
        this.Rec_date = "";
        this.Post_code = "";
        this.Area_code = "";
        this.Firm_FAX = "";
        this.Fac_exc = "";
        this.Serv_key = "";
        this.ContractPerformance = "";
        this.Index_no1 = "";
        this.CEO_ssn = "";
        this.CEO_name = "";
        this.CEO_email = "";
        this.CEO_yn = "";
        this.CEO_pcs = "";
        this.CEO_type = "";
        this.branch_Index_no1 = "";
        this.branch_Reg_no = "";
        this.branch_Firm_name = "";
        this.branch_Post_code = "";
        this.branch_Area_code = "";
        this.branch_Firm_FAX = "";
        this.branch_Firm_TEL = "";
        this.branch_Add1 = "";
        this.branch_Add2 = "";
        this.branch_CEO_ssn = "";
        this.branch_CEO_name = "";
        this.Index_no3 = "";
        this.Item_name = "";
        this.Item_class_sup = "";
        this.Rec_sale_sup = "";
        this.Manu_yn_sup = "";
        this.Rep_yn_sup = "";
        this.Index_no4 = "";
        this.Item_class_manu = "";
        this.Appr_no = "";
        this.Appr_org = "";
        this.Appr_date = "";
        this.Rec_sale_manu = "";
        this.Manu_yn_manu = "";
        this.Rep_yn_manu = "";
        this.Pro_Certi_code = "";
        this.Appr_start_date = "";
        this.Appr_end_date = "";
        this.Maun_org = "";
        this.App_name = "";
        this.Stand_class = "";
        this.Index_no5 = "";
        this.Lic_code = "";
        this.Lic_no = "";
        this.Lic_date = "";
        this.Lic_valid = "";
        this.Able_amount = "";
        this.Basis_year = "";
        this.Rep_lic_yn = "";
        this.Index_no6 = "";
        this.Emp_ssn = "";
        this.Emp_name = "";
        this.Emp_dept = "";
        this.Emp_title = "";
        this.Emp_TEL = "";
        this.Emp_email = "";
        this.Emp_cell = "";
        this.Emp_FAX = "";
        this.Bid_Persion = "";
        this.Reg_no = "";
        this.Reg_chg_kind = "";
        this.Reg_date = "";
        this.Firm_name = "";
        this.PCS_num = "";
        this.SMS_num = "";
        this.Firm_CEO = "";
        this.Confirm_off = "";
        this.post_no = "";
        this.Add1 = "";
        this.Add2 = "";
        this.Firm_TEL = "";
        this.Homepage = "";
        this.status = "";
        this.Accept_res = "";
        this.XML_loc = "";
        this.Doc_no = "";
        this.EMAIL = "";
        this.relayGo = "";
        this.Docu_no = "";
        this.newXML = "";
        this.unikey = "";
        this.AdminAgree = "";
    }
    
    public RcvGsacid(final Connection pconn) {
        this.pconn = null;
        this.pstmt = null;
        this.rs = null;
        this.LogMsg = new StringBuffer(8192);
        this.parser = new DOMParser();
        this.sql = "";
        this.tbl_name = "UM_EDOC_STATE";
        this.Rvalue = "";
        this.PersonalAuth = "";
        this.SoVanBan = "";
        this.Country_code = "";
        this.Firm_engname = "";
        this.Open_date = "";
        this.Start_date = "";
        this.Busi_code = "";
        this.Manu_code = "";
        this.Busi_type = "";
        this.Regi_no = "";
        this.Org_class1 = "";
        this.Org_class2 = "";
        this.Org_year = "";
        this.Capital = "";
        this.Emp_no = "";
        this.Rec_date = "";
        this.Post_code = "";
        this.Area_code = "";
        this.Firm_FAX = "";
        this.Fac_exc = "";
        this.Serv_key = "";
        this.ContractPerformance = "";
        this.Index_no1 = "";
        this.CEO_ssn = "";
        this.CEO_name = "";
        this.CEO_email = "";
        this.CEO_yn = "";
        this.CEO_pcs = "";
        this.CEO_type = "";
        this.branch_Index_no1 = "";
        this.branch_Reg_no = "";
        this.branch_Firm_name = "";
        this.branch_Post_code = "";
        this.branch_Area_code = "";
        this.branch_Firm_FAX = "";
        this.branch_Firm_TEL = "";
        this.branch_Add1 = "";
        this.branch_Add2 = "";
        this.branch_CEO_ssn = "";
        this.branch_CEO_name = "";
        this.Index_no3 = "";
        this.Item_name = "";
        this.Item_class_sup = "";
        this.Rec_sale_sup = "";
        this.Manu_yn_sup = "";
        this.Rep_yn_sup = "";
        this.Index_no4 = "";
        this.Item_class_manu = "";
        this.Appr_no = "";
        this.Appr_org = "";
        this.Appr_date = "";
        this.Rec_sale_manu = "";
        this.Manu_yn_manu = "";
        this.Rep_yn_manu = "";
        this.Pro_Certi_code = "";
        this.Appr_start_date = "";
        this.Appr_end_date = "";
        this.Maun_org = "";
        this.App_name = "";
        this.Stand_class = "";
        this.Index_no5 = "";
        this.Lic_code = "";
        this.Lic_no = "";
        this.Lic_date = "";
        this.Lic_valid = "";
        this.Able_amount = "";
        this.Basis_year = "";
        this.Rep_lic_yn = "";
        this.Index_no6 = "";
        this.Emp_ssn = "";
        this.Emp_name = "";
        this.Emp_dept = "";
        this.Emp_title = "";
        this.Emp_TEL = "";
        this.Emp_email = "";
        this.Emp_cell = "";
        this.Emp_FAX = "";
        this.Bid_Persion = "";
        this.Reg_no = "";
        this.Reg_chg_kind = "";
        this.Reg_date = "";
        this.Firm_name = "";
        this.PCS_num = "";
        this.SMS_num = "";
        this.Firm_CEO = "";
        this.Confirm_off = "";
        this.post_no = "";
        this.Add1 = "";
        this.Add2 = "";
        this.Firm_TEL = "";
        this.Homepage = "";
        this.status = "";
        this.Accept_res = "";
        this.XML_loc = "";
        this.Doc_no = "";
        this.EMAIL = "";
        this.relayGo = "";
        this.Docu_no = "";
        this.newXML = "";
        this.unikey = "";
        this.AdminAgree = "";
        this.pconn = pconn;
        try {
            pconn.setAutoCommit(false);
        }
        catch (Exception ex) {}
    }
    
    public String isNullStr(final String str) {
        return (str == null) ? "" : str;
    }
    
    public String main_process(final String send_no, final String fullname) {
        this.unikey = send_no;
        String sResult = "";
        this.xmlFileName = send_no;
        FileInputStream fisContent = null;
        this.SaveLog("MainProcess Entry==========================================");
        try {
            System.out.println("XML File Doc... FilePath: = " + fullname + "***********");
            fisContent = new FileInputStream(fullname);
            this.parser.parse((InputStream)fisContent);
            final XMLDocument dMaster = this.parser.getDocument();
            this.Reg_chg_kind = GetValues(dMaster, "/gb:Gsacid/New.Modify.Code/Code.Content");
            this.Rvalue = GetValues(dMaster, "/gb:Gsacid/Rvalue.Text/Text.Content");
            System.out.println("Reg_chg_kind==========" + this.Reg_chg_kind);
            System.out.println("Rvalue======" + this.Rvalue);
            if (!this.Rvalue.toLowerCase().equals("admin") && this.Reg_chg_kind.equals("1")) {
                sResult = this.personalAuthCheck(dMaster);
                if (!sResult.equals("true")) {
                    return sResult;
                }
            }
            sResult = this.selectData0(dMaster);
            System.out.println("selectData0(dMaster)=" + sResult);
            if (!sResult.equals("true")) {
                return sResult;
            }
            sResult = this.selectData(dMaster);
            System.out.println("selectData(dMaster)=" + sResult);
            if (!sResult.equals("true")) {
                return sResult;
            }
            sResult = this.parseData1(this.xmlFileName, fullname, dMaster);
            System.out.println("=============parseData1" + sResult);
            if (!sResult.equals("true")) {
                return sResult;
            }
            sResult = this.parseData2(dMaster);
            System.out.println("==========parseData2" + sResult);
            if (!sResult.equals("true")) {
                return sResult;
            }
            sResult = this.parseData6(dMaster);
            System.out.println("=========parseData6: " + sResult);
            if (!sResult.equals("true")) {
                return sResult;
            }
            sResult = this.parseData7(dMaster);
            System.out.println("=========parseData7: " + sResult);
            if (!sResult.equals("true")) {
                return sResult;
            }
            this.SaveLog("지사 항목 저장=================> 결과: " + sResult);
            if (!sResult.equals("true")) {
                return sResult;
            }
            return sResult;
        }
        catch (Exception e) {
            this.SaveLog("조달업체등록(변경) 요청서] DB Update failed =================> Exception : " + e.toString());
            return "00 -2[RcvGsacid - 조달업체등록(변경) 요청서] DB Update failed";
        }
        finally {
            try {
                fisContent.close();
            }
            catch (Exception ex) {}
            try {
                if (this.pstmt != null) {
                    this.pstmt.close();
                }
            }
            catch (SQLException exc) {
                return "oracle error : " + exc.toString();
            }
        }
    }
    
    public String personalAuthCheck(final XMLDocument dMaster) {
        try {
            this.SaveLog("신원확인검사 시작");
            final String UserName = "";
            this.Rvalue = GetValues(dMaster, "/gb:Gsacid/Rvalue.Text/Text.Content");
            this.PersonalAuth = GetValues(dMaster, "/gb:Gsacid/Authorization.Text/Text.Content");
            this.Busi_code = GetValues(dMaster, "/gb:Gsacid/Supplier.Organization.Details/Organization.Identifier/Identifier.Content");
            final Secu secu = new Secu(11);
            final CertInfo cu = new CertInfo(secu, this.PersonalAuth);
            final boolean bRet = cu.ValidateUserID(this.Busi_code, UserName, this.Rvalue);
            if (!bRet) {
                this.SaveLog("신원확인 검사 실패입니다");
                return "12 [RcvGsacid - 신원확인 검사 실패입니다]";
            }
            if (cu.isValid()) {
                return "true";
            }
            this.SaveLog("인증서 유효성 검사 실패입니다");
            return "13 [RcvGsacid - 인증서 유효성 검사 실패입니다]";
        }
        catch (Exception e) {
            this.SaveLog(" 신원확인 확인오류입니다===================> Exception: " + e.toString());
            return "12 [RcvGsacid - 신원 확인오류입니다] Xml Parsing Error ";
        }
    }
    
    public String selectData0(final XMLDocument dMaster) {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        final StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("Select count(*) from UM_SUPPLIER_ENTER_MAST where BIZ_REG_NO=? ");
        try {
            this.Busi_code = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/Organization.Identifier/Identifier.Content");
            this.Reg_chg_kind = GetValues(dMaster, "gb:Gsacid/New.Modify.Code/Code.Content");
            System.out.println("So DKKD = ========= " + this.Busi_code);
            System.out.println("Reg_chg_kind ======== " + this.Reg_chg_kind);
            pstmt = this.pconn.prepareStatement(sqlBuf.toString());
            pstmt.setString(1, this.Busi_code);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                final int iCount = rs.getInt(1);
                if (iCount > 0 && "1".equals(this.Reg_chg_kind)) {
                    return "00 -02 [RcvGsacid - UM_SUPPLIER_ENTER_MAST]  người dùng đã được đăng ký.";
                }
                if (iCount <= 0 && "2".equals(this.Reg_chg_kind)) {
                    return "03 -02 [RcvGsacid - UM_EDOC_STATE] Người dùng chưa đăng ký. Hãy soạn đơn đăng ký.";
                }
            }
            return "true";
        }
        catch (Exception e) {
            this.SaveLog(" Lỗi tình trạng[UM_SUPPLIER_ENTER_MAST] ===================> Exception: " + e.toString());
            return "00 -10[RcvGsacid - Đơn đăng ký ( thay đổi) -UM_SUPPLIER_ENTER_MAST ] Select lỗi.";
        }
        finally {
            try {
                rs.close();
            }
            catch (Exception ex) {}
            try {
                pstmt.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public String selectData(final XMLDocument dMaster) {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        final StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("Select MOD_CLS, PROCESS_ST from UM_EDOC_STATE where BIZ_REG_NO=? and PROCESS_ST<>'3' order by REG_DT desc");
        String tmp_chg_kind = "";
        String tmp_process = "";
        try {
            this.Busi_code = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/Organization.Identifier/Identifier.Content");
            this.Reg_chg_kind = GetValues(dMaster, "gb:Gsacid/New.Modify.Code/Code.Content");
            pstmt = this.pconn.prepareStatement(sqlBuf.toString());
            pstmt.setString(1, this.Busi_code);
            rs = pstmt.executeQuery();
            Label_0210: {
                if (rs.next()) {
                    tmp_chg_kind = rs.getString(1);
                    tmp_process = rs.getString(2);
                    if (!"0".equals(tmp_process)) {
                        if (!"2".equals(tmp_process)) {
                            if ("1".equals(tmp_process) && "1".equals(this.Reg_chg_kind)) {
                                return "01 -02 [RcvGsacid - UM_EDOC_STATE] Đã phê duyệt. Hãy soạn bản đăng ký thay đổi";
                            }
                            break Label_0210;
                        }
                    }
                    return "02 -02 [RcvGsacid - UM_EDOC_STATE] Đang trong quá trình chờ phê duyệt.";
                }
                try {}
                catch (Exception ex) {}
            }
            return "true";
        }
        catch (Exception e) {
            this.SaveLog(" Lỗi trạng thái tiến hành[UM_REC_SUPPLIER_ENTER_MAST & UM_EDOC_STATE] ===================> Exception: " + e.toString());
            return "00 -10[RcvGsacid - Đơn đăng ký(thay đổi) [UM_REC_SUPPLIER_ENTER_MAST & UM_EDOC_STATE] Select lỗi.";
        }
        finally {
            try {
                rs.close();
            }
            catch (Exception ex2) {}
            try {
                pstmt.close();
            }
            catch (Exception ex3) {}
        }
    }
    
    public String parseData1(final String send_no, final String Source, final XMLDocument dMaster) {
        try {
            this.SoVanBan = GetValues(dMaster, "//Header.Details/cc:Document.Number.Text/Text.Content");
            this.Reg_chg_kind = GetValues(dMaster, "gb:Gsacid/New.Modify.Code/Code.Content");
            this.Country_code = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/Nationality.Code/Code.Content");
            this.Firm_engname = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/Organization.English.Name/Text.Content");
            this.Open_date = GetValues(dMaster, "gb:Gsacid/Business.Open.Date/DateTime.Content");
            String dd = "";
            String mm = "";
            String yyyy = "";
            if (this.Open_date.indexOf("/") > 0) {
                dd = this.Open_date.substring(0, 2);
                mm = this.Open_date.substring(3, 5);
                yyyy = this.Open_date.substring(6, 10);
                this.Open_date = String.valueOf(dd) + mm + yyyy;
            }
            this.Start_date = GetValues(dMaster, "gb:Gsacid/Corporation.Start.Date/DateTime.Content");
            if (this.Start_date.indexOf("/") > 0) {
                dd = this.Start_date.substring(0, 2);
                mm = this.Start_date.substring(3, 5);
                yyyy = this.Start_date.substring(6, 10);
                this.Start_date = String.valueOf(dd) + mm + yyyy;
            }
            this.Busi_code = GetValues(dMaster, "gb:Gsacid/Business.Code/Code.Content");
            this.Manu_code = GetValues(dMaster, "gb:Gsacid/Manufacturing.Code/Code.Content");
            this.Busi_type = GetValues(dMaster, "gb:Gsacid/BisinessType.Code/Code.Content");
            this.Regi_no = GetValues(dMaster, "gb:Gsacid/Registration.Number.Text/Text.Content");
            this.Org_class1 = GetValues(dMaster, "gb:Gsacid/Organization.Classfication1.Code/Code.Content");
            this.Org_class2 = GetValues(dMaster, "gb:Gsacid/Organization.Classfication2.Code/Code.Content");
            this.Org_year = GetValues(dMaster, "gb:Gsacid/Organization.IndicatorYear.Date/DateTime.Content");
            this.Capital = GetValues(dMaster, "gb:Gsacid/Capital.Amount/Amount.Content");
            this.Emp_no = GetValues(dMaster, "gb:Gsacid/Employee.Number.Value/Numeric.Content");
            this.Rec_date = GetValues(dMaster, "gb:Gsacid/Recent.Settlement.Date/DateTime.Content");
            this.Post_code = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/Address.Details/cc:PostCode.Identifier/Identifier.Content");
            this.Area_code = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/Address.Details/cc:PostCode.Identifier/Identifier.Content");
            this.Firm_FAX = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/Contact.Details/cc:Fax.Number.Text/Text.Content");
            this.Fac_exc = GetValues(dMaster, "gb:Gsacid/Factory.Exception.Indicator/Indicator.Content");
            this.Serv_key = GetValues(dMaster, "gb:Gsacid/Server.Key.Text/Text.Content");
            this.ContractPerformance = GetValues(dMaster, "gb:Gsacid/cc:Integrity.ContractPerformance.Indicator/Indicator.Content");
            this.Reg_no = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/Organization.Identifier/Identifier.Content");
            this.Reg_date = GetValues(dMaster, "gb:Gsacid/cc:Document.Issue.Date/DateTime.Content");
            this.Firm_name = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/Company.Name/Text.Content");
            this.PCS_num = GetValues(dMaster, "gb:Gsacid/PCS.Number.Text/Text.Content");
            this.SMS_num = GetValues(dMaster, "gb:Gsacid/cc:SMS.Reception.Indicator/Indicator.Content");
            this.AdminAgree = GetValues(dMaster, "gb:Gsacid/cc:Admin.Agree.Indicator/Indicator.Content");
            for (int i = 0; !"".equals(GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem/cc:Line.Number.Value/Numeric.Content", i)); ++i) {
                final String yn = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem/Representation.Ceo.Indicator/Indicator.Content", i);
                if (yn.trim().equals("Y")) {
                    this.Firm_CEO = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem/Organization.CEO.Name/Text.Content", i);
                    this.SaveLog("UM_EDOC_STATE Save the table=================> Firm_CEO: " + this.Firm_CEO);
                    break;
                }
            }
            this.Confirm_off = GetValues(dMaster, "gb:Gsacid/Branch.Code/Code.Content");
            this.post_no = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/Address.Details/cc:PostCode.Identifier/Identifier.Content");
            this.Add1 = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/Address.Details/cc:Address.Line1.Text/Text.Content");
            this.Add2 = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/Address.Details/cc:Address.Line2.Text/Text.Content");
            this.Firm_TEL = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/Contact.Details/cc:Telephone.Number.Text/Text.Content");
            this.Homepage = GetValues(dMaster, "gb:Gsacid/HomePage.Text/Text.Content");
            this.EMAIL = GetValues(dMaster, "gb:Gsacid/Email.Address.Text/Text.Content");
            this.relayGo = GetValues(dMaster, "gb:Gsacid/Header.Details/cc:Document.ReferenceNumber.Text/Text.Content");
            this.Docu_no = GetValues(dMaster, "gb:Gsacid/Header.Details/cc:Document.Number.Text/Text.Content");
            this.status = "0";
            this.Accept_res = "";
            this.XML_loc = Source;
            this.Doc_no = send_no;
            String tmpRe = "true";
            tmpRe = this.updateMaster1();
            this.SaveLog("UM_REC_SUPPLIER_ENTER_MAST Save the table=================> Results: " + tmpRe);
            if (tmpRe.equals("true")) {
                tmpRe = this.updateMaster7();
                this.SaveLog("UM_EDOC_STATE Save the table=================> Results: " + tmpRe);
                if (!tmpRe.equals("true")) {
                    tmpRe = "04 " + tmpRe;
                }
                return tmpRe;
            }
            return "04 " + tmpRe;
        }
        catch (Exception e) {
            this.SaveLog(" XML Parsing Error[UM_REC_SUPPLIER_ENTER_MAST & UM_EDOC_STATE]===================> Exception: " + e.toString());
            return "04 -10[RcvGsacid - Đơn đăng ký ( thay đổi ) -UM_REC_SUPPLIER_ENTER_MAST & UM_EDOC_STATE] Xml Parsing Error";
        }
    }
    
    public String parseData8(final String send_no, final String Source, final XMLDocument dMaster) {
        try {
            String tmp = "true";
            for (int i = 0; !"".equals(GetValues(dMaster, "gb:Gsacid/BranchList/BranchItem/cc:Line.Number.Value/Numeric.Content", i)); ++i) {
                this.branch_Reg_no = GetValues(dMaster, "gb:Gsacid/BranchList/BranchItem/BranchOffice.Details/cc:Organization.Identifier/Identifier.Content", i);
                this.branch_Firm_name = GetValues(dMaster, "gb:Gsacid/BranchList/BranchItem/BranchOffice.Details/cc:Company.Name/Text.Content", i);
                this.branch_Post_code = GetValues(dMaster, "gb:Gsacid/BranchList/BranchItem/BranchOffice.Details/Address.Details/cc:PostCode.Identifier/Identifier.Content", i);
                this.branch_Area_code = GetValues(dMaster, "gb:Gsacid/BranchList/BranchItem/BranchOffice.Details/cc:Area.Limtation.Code/Code.Content", i);
                this.branch_Firm_FAX = GetValues(dMaster, "gb:Gsacid/BranchList/BranchItem/BranchOffice.Details/Contact.Details/cc:Fax.Number.Text/Text.Content", i);
                this.branch_Firm_TEL = GetValues(dMaster, "gb:Gsacid/BranchList/BranchItem/BranchOffice.Details/Contact.Details/cc:Telephone.Number.Text/Text.Content", i);
                this.Regi_no = GetValues(dMaster, "gb:Gsacid/Registration.Number.Text/Text.Content");
                this.branch_Add1 = GetValues(dMaster, "gb:Gsacid/BranchList/BranchItem/BranchOffice.Details/Address.Details/cc:Address.Line1.Text/Text.Content", i);
                this.branch_Add2 = GetValues(dMaster, "gb:Gsacid/BranchList/BranchItem/BranchOffice.Details/Address.Details/cc:Address.Line2.Text/Text.Content", i);
                this.branch_CEO_ssn = GetValues(dMaster, "gb:Gsacid/BranchList/BranchItem/BranchOffice.Details/CEO.Details/cc:Organization.CEO.Identifier/Identifier.Content", i);
                this.branch_CEO_name = GetValues(dMaster, "gb:Gsacid/BranchList/BranchItem/BranchOffice.Details/CEO.Details/cc:Organization.CEO.Name/Text.Content", i);
                tmp = this.updateMaster9();
            }
            if (!tmp.equals("true")) {
                tmp = "11 " + tmp;
            }
            return tmp;
        }
        catch (Exception e) {
            this.SaveLog(" XML Parsing Error[UM_REC_SUPPLIER_ENTER_MAST & UM_REC_REPR]===================> Exception: " + e.toString());
            return "11 -10[RcvGsacid - Đơn đăng ký ( thay đổi ) -UM_REC_SUPPLIER_ENTER_MAST & UM_REC_REPR] Xml Parsing Error";
        }
    }
    
    private String parseData2(final XMLDocument dMaster) {
        try {
            this.Index_no1 = "true";
            this.deleteMaster2();
            for (int i = 0; !"".equals(GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem/cc:Line.Number.Value/Numeric.Content", i)); ++i) {
                this.CEO_ssn = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", i);
                this.CEO_name = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem/Organization.CEO.Name/Text.Content", i);
                this.CEO_email = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem/Email.Address.Text/Text.Content", i);
                this.CEO_yn = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem/Representation.Ceo.Indicator/Indicator.Content", i);
                this.CEO_pcs = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem/cc:PCS.Number.Text/Text.Content", i);
                this.CEO_type = GetValues(dMaster, "gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem/cc:CEO.Type.Code/Code.Content", i);
                this.Index_no1 = this.updateMaster2();
            }
            if (!this.Index_no1.equals("true")) {
                this.Index_no1 = "05 " + this.Index_no1;
            }
            return this.Index_no1;
        }
        catch (Exception e) {
            this.SaveLog(" XML Parsing Error[사용_접수대표자]===================> Exception: " + e.toString());
            return "05 -10[RcvGsacid - 조달업체등록(변경)요청서-사용_접수대표자] Xml Parsing Error";
        }
    }
    
    private String parseData5(final XMLDocument dMaster) {
        try {
            this.Index_no4 = "true";
            for (int i = 0; !"".equals(GetValues(dMaster, "gb:Gsacid/ManList/ManItem/cc:Line.Number.Value/Numeric.Content", i)); ++i) {
                this.Item_class_manu = GetValues(dMaster, "gb:Gsacid/ManList/ManItem/cc:Item.ClassificationNumber.Identifier/Identifier.Content", i);
                this.Appr_no = GetValues(dMaster, "gb:Gsacid/ManList/ManItem/Form.Approval.Identifier/Identifier.Content", i);
                this.Appr_org = GetValues(dMaster, "gb:Gsacid/ManList/ManItem/Form.ApprovalOrg.Name/Text.Content", i);
                this.Appr_date = GetValues(dMaster, "gb:Gsacid/ManList/ManItem/Form.Approval.Date/DateTime.Content", i);
                this.Rec_sale_manu = GetValues(dMaster, "gb:Gsacid/ManList/ManItem/Sale.Recent.Amount/Amount.Content", i);
                this.Rec_sale_manu = this.setNoComma(this.Rec_sale_manu);
                this.Manu_yn_manu = GetValues(dMaster, "gb:Gsacid/ManList/ManItem/Manufacturing.Indicator/Indicator.Content", i);
                this.Rep_yn_manu = GetValues(dMaster, "gb:Gsacid/ManList/ManItem/Representation.Item.Indicator/Indicator.Content", i);
                this.Pro_Certi_code = GetValues(dMaster, "gb:Gsacid/ManList/ManItem/Manufacturing.Details/cc:Production.Certification.Code/Code.Content", i);
                this.Appr_start_date = GetValues(dMaster, "gb:Gsacid/ManList/ManItem/Manufacturing.Details/cc:Validity.Start.Date/DateTime.Content", i);
                this.Appr_end_date = GetValues(dMaster, "gb:Gsacid/ManList/ManItem/Manufacturing.Details/cc:Validity.End.Date/DateTime.Content", i);
                this.Maun_org = GetValues(dMaster, "gb:Gsacid/ManList/ManItem/Manufacturing.Details/cc:Organization.Name/Text.Content", i);
                this.App_name = GetValues(dMaster, "gb:Gsacid/ManList/ManItem/Manufacturing.Details/cc:Certificate.Name/Text.Content", i);
                this.Stand_class = GetValues(dMaster, "gb:Gsacid/ManList/ManItem/Manufacturing.Details/cc:StandardIndustry.Classification.Code/Code.Content", i);
            }
            if (!this.Index_no4.equals("true")) {
                this.Index_no4 = "08 " + this.Index_no4;
            }
            return this.Index_no4;
        }
        catch (Exception e) {
            this.SaveLog(" XML Parsing Error[사용_접수조달품목(제조)]===================> Exception: " + e.toString());
            return "08 -10[RcvGsacid - 조달업체등록(변경)요청서-사용_접수조달품목(제조)] Xml Parsing Error";
        }
    }
    
    private String parseData6(final XMLDocument dMaster) {
        try {
            this.Index_no5 = "true";
            this.deleteMaster5();
            for (int i = 0; !"".equals(GetValues(dMaster, "gb:Gsacid/LicenList/LicenItem/cc:Line.Number.Value/Numeric.Content", i)); ++i) {
                this.Lic_code = GetValues(dMaster, "gb:Gsacid/LicenList/LicenItem/License.Code/Code.Content", i);
                this.Lic_no = GetValues(dMaster, "gb:Gsacid/LicenList/LicenItem/License.Number.Text/Text.Content", i);
                this.Lic_date = GetValues(dMaster, "gb:Gsacid/LicenList/LicenItem/License.ValidityStart.Date/DateTime.Content", i);
                this.Lic_valid = GetValues(dMaster, "gb:Gsacid/LicenList/LicenItem/cc:License.ValidityEnd.Date/DateTime.Content", i);
                String dd = "";
                String mm = "";
                String yyyy = "";
                if (this.Lic_date.indexOf("/") > 0) {
                    dd = this.Lic_date.substring(0, 2);
                    mm = this.Lic_date.substring(3, 5);
                    yyyy = this.Lic_date.substring(6, 10);
                    this.Lic_date = String.valueOf(dd) + mm + yyyy;
                }
                if (this.Lic_valid.indexOf("/") > 0) {
                    dd = this.Lic_valid.substring(0, 2);
                    mm = this.Lic_valid.substring(3, 5);
                    yyyy = this.Lic_valid.substring(6, 10);
                    this.Lic_valid = String.valueOf(dd) + mm + yyyy;
                }
                this.Able_amount = GetValues(dMaster, "gb:Gsacid/LicenList/LicenItem/Construcation.AbliltyPar.Amount/Amount.Content", i);
                this.Able_amount = this.setNoComma(this.Able_amount);
                this.Basis_year = GetValues(dMaster, "gb:Gsacid/LicenList/LicenItem/ParRank.AmountStandardYear.Date/DateTime.Content", i);
                this.Rep_lic_yn = GetValues(dMaster, "gb:Gsacid/LicenList/LicenItem/Representation.License.Indicator/Indicator.Content", i);
                this.Index_no5 = this.updateMaster5();
            }
            if (!this.Index_no5.equals("true")) {
                this.Index_no5 = "09 " + this.Index_no5;
            }
            return this.Index_no5;
        }
        catch (Exception e) {
            this.SaveLog(" XML Parsing Error[사용_접수면허사항]===================> Exception: " + e.toString());
            return "09 -10[RcvGsacid - 조달업체등록(변경)요청서-사용_접수면허사항] Xml Parsing Error";
        }
    }
    
    private String parseData7(final XMLDocument dMaster) {
        try {
            String Index_no6 = "true";
            this.deleteMaster6();
            for (int i = 0; !"".equals(GetValues(dMaster, "gb:Gsacid/RepList/RepItem/cc:Line.Number.Value/Numeric.Content", i)); ++i) {
                this.Emp_ssn = GetValues(dMaster, "gb:Gsacid/RepList/RepItem/Employee.Details/cc:Person.Identifier/Identifier.Content", i);
                this.Emp_name = GetValues(dMaster, "gb:Gsacid/RepList/RepItem/Employee.Details/cc:Employee.Name/Text.Content", i);
                this.Emp_dept = GetValues(dMaster, "gb:Gsacid/RepList/RepItem/Employee.Details/cc:Department.Name/Text.Content", i);
                this.Emp_title = GetValues(dMaster, "gb:Gsacid/RepList/RepItem/Employee.Details/Employee.Title.Name/Text.Content", i);
                this.Emp_TEL = GetValues(dMaster, "gb:Gsacid/RepList/RepItem/Employee.Details/cc:Telephone.Number.Text/Text.Content", i);
                this.Emp_email = GetValues(dMaster, "gb:Gsacid/RepList/RepItem/Employee.Details/cc:Email.Address.Text/Text.Content", i);
                this.Emp_cell = GetValues(dMaster, "gb:Gsacid/RepList/RepItem/Employee.Details/PCS.Number.Text/Text.Content", i);
                this.Emp_FAX = GetValues(dMaster, "gb:Gsacid/RepList/RepItem/Employee.Details/cc:Fax.Number.Text/Text.Content", i);
                this.Bid_Persion = GetValues(dMaster, "gb:Gsacid/RepList/RepItem/Employee.Details/cc:BiddingAgent.Confirmation.Indicator/Indicator.Content", i);
                Index_no6 = this.updateMaster6();
            }
            if (!Index_no6.equals("true")) {
                Index_no6 = "10 " + Index_no6;
            }
            return Index_no6;
        }
        catch (Exception e) {
            this.SaveLog(" XML Parsing Error[사용_접수입찰대리인]===================> Exception: " + e.toString());
            return "10 -10[RcvGsacid - 조달업체등록(변경)요청서-사용_접수입찰대리인] Xml Parsing Error";
        }
    }
    
    public String updateMaster1() {
        String serv_yn = "";
        if (this.Serv_key != null) {
            serv_yn = "Y";
        }
        else {
            serv_yn = "N";
        }
        PreparedStatement pstmt = null;
        final StringBuffer sqlBuf = new StringBuffer();
        final StringBuffer sqlBuf2 = new StringBuffer();
        sqlBuf2.append("delete UM_REC_SUPPLIER_ENTER_MAST where BIZ_REG_NO='" + this.Reg_no + "'");
        sqlBuf.append("insert into UM_REC_SUPPLIER_ENTER_MAST(BIZ_REG_NO, NATIONALITY, BIZ_NM, BIZ_EN_NM, COMMENCEMENT_DT, ESTABLISH_DT,  \r\nBIZ_CLS, PRODUCT_CLS, MAST_INDUSTRY_CD_STD, CORP_REG_NO, BIZ_CLS1, BIZ_CLS2, BIZ_CLS_YEAR, CAPITAL,  \r\nEMPLOYEE_COUNT, LAST_SETTLE_DT, ZIP_CD, AREA_CD, ADDR, DETAIL_ADDR, PHONE_NO, FAX, WEBSITE, SPECIAL_GOODS_YN,  \r\nUPDATE_DT, DOC_NO,DOC_CREAT_DT,REG_DT) values('" + this.Reg_no + "','" + this.Country_code + "','" + this.Firm_name + "' ,'" + this.Firm_engname + "' , " + " to_date('" + this.Open_date + "','DDMMYYYY') ,to_date('" + this.Start_date + "','DDMMYYYY'), '" + this.Busi_code + "','" + this.Manu_code + "','" + this.Busi_type + "'," + " '" + this.Regi_no + "' , '" + this.Org_class1 + "','" + this.Org_class2 + "','" + this.Org_year + "', '" + this.Capital + "' , " + " '" + this.Emp_no + "' ,'0000-00', " + " '" + this.Post_code + "' , '" + this.Area_code + "' , '" + this.Add1 + "' , " + " '" + this.Add2 + "' , '" + this.Firm_TEL + "' , '" + this.Firm_FAX + "', '" + this.Homepage + "' , '" + serv_yn + "', " + " SYSDATE,'" + this.SoVanBan + "',sysdate,sysdate)");
        try {
            pstmt = this.pconn.prepareStatement(sqlBuf2.toString());
            System.out.println("sqlBuf2====: " + sqlBuf2.toString());
            pstmt.executeUpdate();
            if (pstmt != null) {
                pstmt.close();
            }
            pstmt = this.pconn.prepareStatement(sqlBuf.toString());
            System.out.println("sqlBuf====: " + sqlBuf.toString());
            pstmt.executeUpdate();
            System.out.println("Update table UM_REC_SUPPLIER_ENTER_MAST====: True");
            return "true";
        }
        catch (SQLException e) {
            if (e.getErrorCode() == 1) {
                return "-2[RcvGsacid - Đơn xin đăng ký (thay đổi)/UM_REC_SUPPLIER_ENTER_MAST]" + e.getMessage();
            }
            return String.valueOf(e.getErrorCode()) + "[RcvGsacid - Đơn xin đăng ký (thay đổi)/UM_REC_SUPPLIER_ENTER_MAST]" + e.getMessage();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public String updateMaster2() {
        PreparedStatement pstmt = null;
        final StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("insert into UM_REC_REPR(BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, REPR_ISMAIN , REPR_MOBILE, REPR_CLS) \r\nvalues('" + this.Reg_no + "','" + this.CEO_ssn + "','" + this.CEO_name + "','" + this.CEO_email + "','" + this.CEO_yn + "','" + this.CEO_pcs + "','" + this.CEO_type + "')");
        try {
            pstmt = this.pconn.prepareStatement(sqlBuf.toString());
            System.out.println("========" + sqlBuf.toString());
            pstmt.executeUpdate();
            return "true";
        }
        catch (SQLException e) {
            if (e.getErrorCode() == 1) {
                return "-2[RcvGsacid - 조달업체등록(변경)신청서/사용_접수대표자]" + e.getMessage();
            }
            return String.valueOf(e.getErrorCode()) + "[RcvGsacid - 조달업체등록( 변경)신청서/사용_접수대표자]" + e.getMessage();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public String deleteMaster2() {
        PreparedStatement pstmt = null;
        String str = "";
        str = "Delete UM_REC_REPR where BIZ_REG_NO ='" + this.Reg_no + "'";
        try {
            pstmt = this.pconn.prepareStatement(str);
            System.out.println("========" + str);
            pstmt.executeUpdate();
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
            pstmt.executeUpdate();
            return "true";
        }
        catch (SQLException e) {
            if (e.getErrorCode() == 1) {
                return "-2[RcvGsacid - 조달업체등록(변경)신청서/사용_접수대표자]" + e.getMessage();
            }
            return String.valueOf(e.getErrorCode()) + "[RcvGsacid - 조달업체등록( 변경)신청서/사용_접수대표자]" + e.getMessage();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public String updateMaster9() {
        String serv_yn = "";
        if (this.Serv_key != null) {
            serv_yn = "Y";
        }
        else {
            serv_yn = "N";
        }
        PreparedStatement pstmt = null;
        final StringBuffer sqlBuf = new StringBuffer();
        final StringBuffer sqlBuf2 = new StringBuffer();
        final StringBuffer sqlBuf3 = new StringBuffer();
        final StringBuffer sqlBuf4 = new StringBuffer();
        sqlBuf.append("insert into UM_REC_SUPPLIER_ENTER_MAST(BIZ_REG_NO, BIZ_NM, CORP_REG_NO,ZIP_CD, AREA_CD, ADDR, DETAIL_ADDR, PHONE_NO, FAX/*, 본사구분*/  \r\n) values(?,?,?,?,?,?,?,?,?,?) \r\n");
        try {
            pstmt = this.pconn.prepareStatement(sqlBuf.toString());
            pstmt.setString(1, this.branch_Reg_no);
            pstmt.setString(2, this.branch_Firm_name);
            pstmt.setString(3, this.Regi_no);
            pstmt.setString(4, this.branch_Post_code);
            pstmt.setString(5, this.branch_Area_code);
            pstmt.setString(6, this.branch_Add1);
            pstmt.setString(7, this.branch_Add2);
            pstmt.setString(8, this.branch_Firm_TEL);
            pstmt.setString(9, this.branch_Firm_FAX);
            pstmt.executeUpdate();
            sqlBuf2.append("insert into UM_REC_REPR(BIZ_REG_NO, REPR_IDENT_NO, REPR_NM,  MAST_REPR_YN ) \r\nvalues(?,?,?,?) ");
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
            pstmt = this.pconn.prepareStatement(sqlBuf2.toString());
            pstmt.setString(1, this.branch_Reg_no);
            pstmt.setString(2, this.branch_CEO_ssn);
            pstmt.setString(3, this.branch_CEO_name);
            pstmt.setString(4, "Y");
            pstmt.executeUpdate();
            return "true";
        }
        catch (SQLException e) {
            if (e.getErrorCode() == 1) {
                return "-2[RcvGsacid - 지사_조달업체등록(변경)신청서/사용_접수대표자]" + e.getMessage();
            }
            return String.valueOf(e.getErrorCode()) + "[RcvGsacid - 지사_조달업체등록( 변경)신청서/사용_접수대표자]" + e.getMessage();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public String updateMaster5() {
        PreparedStatement pstmt = null;
        final StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("insert into um_rec_enter_std_cls(BIZ_REG_NO, STD_CLS_CD, STD_CLS_NO, STD_CLS_ISSUED_DT, STD_CLS_EXPIRY_DT, CONST_ABIL_EVAL_AMT, \r\n EVAL_STD_YEAR, MAST_STD_CLS_YN) values('" + this.Reg_no + "','" + this.Lic_code + "','" + this.Lic_no + "',to_date('" + this.isNullStr(this.Lic_date) + "','DDMMYYYY'), to_date('" + this.isNullStr(this.Lic_valid) + "','DDMMYYYY'), '" + this.Able_amount + "','" + this.Basis_year + "','" + this.Rep_lic_yn + "')");
        try {
            pstmt = this.pconn.prepareStatement(sqlBuf.toString());
            System.out.println("========updateMaster5" + sqlBuf.toString());
            pstmt.executeUpdate();
            return "true";
        }
        catch (SQLException e) {
            if (e.getErrorCode() == 1) {
                return "-2[RcvGsacid - 조달업체등록(변경)신청서/사용_접수면허사항]" + e.getMessage();
            }
            return String.valueOf(e.getErrorCode()) + "[RcvGsacid - 조달업체등록( 변경)신청서/사용_접수면허사항]" + e.getMessage();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public String deleteMaster5() {
        PreparedStatement pstmt = null;
        final StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("delete um_rec_enter_std_cls where BIZ_REG_NO='" + this.Reg_no + "'");
        try {
            pstmt = this.pconn.prepareStatement(sqlBuf.toString());
            System.out.println("========deleteMaster5" + sqlBuf.toString());
            pstmt.executeUpdate();
            return "true";
        }
        catch (SQLException e) {
            if (e.getErrorCode() == 1) {
                return "-2[RcvGsacid - 조달업체등록(변경)신청서/사용_접수면허사항]" + e.getMessage();
            }
            return String.valueOf(e.getErrorCode()) + "[RcvGsacid - 조달업체등록( 변경)신청서/사용_접수면허사항]" + e.getMessage();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public String updateMaster6() {
        PreparedStatement pstmt = null;
        final StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("insert into UM_REC_BID_AGENT(BIZ_REG_NO, IDENT_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX) \r\n values('" + this.Reg_no + "','" + this.Emp_ssn + "','" + this.Emp_name + "','" + this.Emp_dept + "','" + this.Emp_title + "','" + this.Emp_TEL + "','" + this.Emp_email + "','" + this.Emp_cell + "','" + this.Emp_FAX + "')");
        try {
            pstmt = this.pconn.prepareStatement(sqlBuf.toString());
            System.out.println("===========updateMaster6" + sqlBuf.toString());
            pstmt.executeUpdate();
            return "true";
        }
        catch (SQLException e) {
            if (e.getErrorCode() == 1) {
                return "-2[RcvGsacid - 조달업체등록(변경)신청서/사용_접수입찰대리인]" + e.getMessage();
            }
            return String.valueOf(e.getErrorCode()) + "[RcvGsacid - 조달업체등록( 변경)신청서/사용_접수입찰대리인]" + e.getMessage();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public String deleteMaster6() {
        PreparedStatement pstmt = null;
        final StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("Delete  UM_REC_BID_AGENT where BIZ_REG_NO ='" + this.Reg_no + "'");
        try {
            pstmt = this.pconn.prepareStatement(sqlBuf.toString());
            System.out.println("=================deleteMaster6" + sqlBuf.toString());
            pstmt.executeUpdate();
            return "true";
        }
        catch (SQLException e) {
            if (e.getErrorCode() == 1) {
                return "-2[RcvGsacid - 조달업체등록(변경)신청서/사용_접수입찰대리인]" + e.getMessage();
            }
            return String.valueOf(e.getErrorCode()) + "[RcvGsacid - 조달업체등록( 변경)신청서/사용_접수입찰대리인]" + e.getMessage();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public String updateMaster7() {
        PreparedStatement pstmt = null;
        final StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("INSERT INTO UM_EDOC_STATE(BIZ_REG_NO, MOD_CLS, REG_DT, \r\n COM_NM, REPR_NM,  ZIP_CD, ADDR, DETAIL_ADDR, PHONE_NO, WEBSITE,  PROCESS_ST, \r\n PROCESS_RSON, XML_DOC_LOCATE, TRANS_NO, PERMIT_BRANCH, EMAIL,CONNECTED_COOPERATION,MOBILE,SMS_RECV_YN,DOC_NO,CONSENT_USE_YN) \r\n VALUES('" + this.Reg_no + "'," + " '" + this.Reg_chg_kind + "',sysdate," + " '" + this.Firm_name + "'," + " '" + this.Firm_CEO + "'," + " '" + this.post_no + "'," + " '" + this.Add1 + "'," + " '" + this.Add2 + "'," + " '" + this.Firm_TEL + "'," + " '" + this.Homepage + "'," + " '" + this.status + "'," + " '" + this.Accept_res + "'," + " '" + this.XML_loc + "'," + " '" + this.Doc_no + "'," + " '" + this.Confirm_off + "'," + " '" + this.EMAIL + "'," + " '" + this.relayGo + "'," + " '" + this.PCS_num + "'," + " '" + this.SMS_num + "'," + " '" + this.Docu_no + "'," + " '" + this.AdminAgree + "')");
        try {
            pstmt = this.pconn.prepareStatement(sqlBuf.toString());
            System.out.println("UM_EDOC_STATE========== " + sqlBuf.toString());
            pstmt.executeUpdate();
            return "true";
        }
        catch (SQLException e) {
            if (e.getErrorCode() == 1) {
                return "-2[RcvGsacid - Đơn đăng ký ( thay đổi)/UM_EDOC_STATE]" + e.getMessage();
            }
            return String.valueOf(e.getErrorCode()) + "[RcvGsacid - Đơn đăng ký ( thay đổi)/UM_EDOC_STATE]" + e.getMessage();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public String setNoComma(final String value) {
        try {
            String noComma = "";
            if (value.indexOf(",") == -1) {
                return value;
            }
            final StringTokenizer st = new StringTokenizer(value, ",");
            while (st.hasMoreTokens()) {
                noComma = String.valueOf(noComma) + st.nextToken();
                noComma.trim();
            }
            return noComma;
        }
        catch (Exception e) {
            this.SaveLog("[RcvGsacid] số tiền ',' lỗi remove(,) " + e.toString());
            return "";
        }
    }
    
    private void SaveLog(final String log) {
        System.out.println("[" + this.unikey + "] " + log);
        this.LogMsg.append("[" + this.unikey + "] " + log + "\n");
    }
    
    public String getLog() {
        return this.LogMsg.toString();
    }
    
    public static String GetValues(final XMLDocument doc, final String node) throws Exception {
        return GetValues(doc, node, 0);
    }
    
    public static String GetValues(final XMLDocument doc, final String node, final int index) throws Exception {
        if (node.indexOf("@") == -1) {
            return getNodeValue(doc, node, index).trim();
        }
        return getAttrValue(doc, node.substring(0, node.indexOf("@")), node.substring(node.indexOf("@") + 1, node.length()), index).trim();
    }
    
    public static String getNodeValue(final XMLDocument doc, final String node, final int index) throws Exception {
        try {
            return doc.selectNodes(node, (NSResolver)(XMLElement)doc.getDocumentElement()).item(index).getFirstChild().getNodeValue();
        }
        catch (NullPointerException e) {
            return "";
        }
    }
    
    public static String getAttrValue(final XMLDocument doc, final String node, final String attr, final int index) throws Exception {
        try {
            return ((Element)doc.selectNodes(node, (NSResolver)(XMLElement)doc.getDocumentElement()).item(index)).getAttribute(attr);
        }
        catch (NullPointerException e) {
            return "";
        }
    }
}
