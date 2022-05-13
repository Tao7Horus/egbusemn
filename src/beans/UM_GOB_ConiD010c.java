// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.io.StringReader;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import oracle.xml.parser.v2.NSResolver;
import oracle.xml.parser.v2.XMLElement;
import oracle.xml.parser.v2.XMLDocument;
import java.io.Reader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.ByteArrayOutputStream;
import common.ComStr;
import entity.UM_GOE_ConiC090b;
import oracle.xml.parser.v2.DOMParser;
import java.util.Hashtable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.Vector;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import entity.UM_GOE_ConiC100b;
import entity.UM_GOE_ConiC070b;
import entity.UM_GOE_ConiC060b;
import entity.UM_GOE_ConiC050b;
import entity.UM_GOE_ConiC040b;
import entity.UM_GOE_ConiC030b;
import entity.UM_GOE_ConiC020b;
import entity.UM_GOE_ConiC010b;
import entity.UM_GOE_ConiC080b;

public class UM_GOB_ConiD010c
{
    private String saupNo;
    private String receiverId;
    String xmlSourceMaster;
    String dxeHost;
    int dxePort;
    String dxeStore;
    String XlnSourceMaster;
    String dxeUser;
    String dxePassword;
    private UM_GOE_ConiC080b[] ettI;
    private UM_GOE_ConiC010b[] ett01;
    private UM_GOE_ConiC020b[] ett02;
    private UM_GOE_ConiC030b[] ett03;
    private UM_GOE_ConiC040b[] ett04;
    private UM_GOE_ConiC050b[] ett05;
    private UM_GOE_ConiC060b[] ett06;
    private UM_GOE_ConiC070b[] ett07;
    private UM_GOE_ConiC100b[] ett10;
    private String[] receiver;
    private String[] manageNum;
    private String[] seq2;
    private String[] ceoJuminNo;
    private String[] ceoName;
    private String[] ceoMail;
    private String[] ceoYN;
    private String[] ilrunNo;
    private String[] factoryName;
    private String[] factoryZipCode;
    private String[] factoryAddr;
    private String[] factoryRestAddr;
    private String[] factoryTel;
    private String[] factoryFax;
    private String[] seq3;
    private String[] juminNo;
    private String[] name;
    private String[] jobPart;
    private String[] dutyName;
    private String[] tel3;
    private String[] mail;
    private String[] handphone;
    private String[] fax3;
    private String[] seq4;
    private String[] goodsNo;
    private String[] formNo;
    private String[] formOrg;
    private String[] formDate;
    private String[] threeSale;
    private String[] makeYN;
    private String[] dpGoodsYN;
    private String[] seq1;
    private String[] licenseNo;
    private String[] licenseCode;
    private String[] licenseBeginDate;
    private String[] licenseEndDate;
    private String[] sigongAccount;
    private String[] peonggaYear;
    private String[] dpLicenseYN;
    private String[] seq5;
    private String[] happenDate;
    private String[] startDate;
    private String[] endDate;
    private String[] stateGubun;
    private String[] note;
    private String[] punishmentCount;
    private String[] stopGubun;
    private String[] stopDay;
    private String[] stopSayu;
    private String[] punishmentBasicCode;
    private String[] punishmentBasic;
    private String[] dipunishmentBasic;
    private String[] punishmentStart;
    private String[] punishmentEnd;
    private String[] punishmentPeriod;
    private String[] cancelDay;
    private String[] punishmentSayu;
    private String[] punishmentOrgCode;
    private String[] punishmentOrgCodeGubun;
    
    public UM_GOB_ConiD010c(final String saupNo) {
        this.saupNo = null;
        this.receiverId = null;
        this.xmlSourceMaster = "/devl/usemn/usemnapp/xml/xmlTemplate/Suicod/suicod.xml";
        this.dxeHost = "XLNDB";
        this.dxePort = 1050;
        this.dxeStore = "exms";
        this.XlnSourceMaster = this.dxeStore + ":/xml/suicod.xml";
        this.dxeUser = "";
        this.dxePassword = "";
        this.ettI = null;
        this.ett01 = null;
        this.ett02 = null;
        this.ett03 = null;
        this.ett04 = null;
        this.ett05 = null;
        this.ett06 = null;
        this.ett07 = null;
        this.ett10 = null;
        this.receiver = new String[] { "", "/gb:Suicod/Header.Details/cc:Message.Receiver.Identifier/Identifier.Content" };
        this.manageNum = new String[] { "", "/gb:Suicod/Header.Details/cc:Document.ManagementNumber.Text/Text.Content" };
        this.seq2 = new String[] { "Line.Number.Value", "Numeric.Content" };
        this.ceoJuminNo = new String[] { "cc:Organization.CEO.Identifier", "Identifier.Content" };
        this.ceoName = new String[] { "Organization.CEO.Name", "Text.Content" };
        this.ceoMail = new String[] { "Email.Address.Text", "Text.Content" };
        this.ceoYN = new String[] { "Representation.Ceo.Indicator", "Indicator.Content" };
        this.ilrunNo = new String[] { "Line.Number.Value", "Numeric.Content" };
        this.factoryName = new String[] { "Factory.Name", "Text.Content" };
        this.factoryZipCode = new String[] { "cc:PostCode.Identifier", "Identifier.Content" };
        this.factoryAddr = new String[] { "cc:Address.Line1.Text", "Text.Content" };
        this.factoryRestAddr = new String[] { "cc:Address.Line2.Text", "Text.Content" };
        this.factoryTel = new String[] { "cc:Telephone.Number.Text", "Text.Content" };
        this.factoryFax = new String[] { "cc:Fax.Number.Text", "Text.Content" };
        this.seq3 = new String[] { "Line.Number.Value", "Numeric.Content" };
        this.juminNo = new String[] { "Person.Identifier", "Identifier.Content" };
        this.name = new String[] { "cc:Employee.Name", "Text.Content" };
        this.jobPart = new String[] { "cc:Department.Name", "Text.Content" };
        this.dutyName = new String[] { "cc:Employee.Title.Name", "Text.Content" };
        this.tel3 = new String[] { "cc:Telephone.Number.Text", "Text.Content" };
        this.mail = new String[] { "Email.Address.Text", "Text.Content" };
        this.handphone = new String[] { "PCS.Number.Text", "Text.Content" };
        this.fax3 = new String[] { "cc:Fax.Number.Text", "Text.Content" };
        this.seq4 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.goodsNo = new String[] { "cc:Item.ClassificationNumber.Identifier", "Identifier.Content" };
        this.formNo = new String[] { "Form.Approval.Identifier", "Identifier.Content" };
        this.formOrg = new String[] { "Form.ApprovalOrg.Name", "Text.Content" };
        this.formDate = new String[] { "Form.Approval.Date", "DateTime.Content" };
        this.threeSale = new String[] { "Sale.Recent.Amount", "Amount.Content" };
        this.makeYN = new String[] { "Manufacturing.Indicator", "Indicator.Content" };
        this.dpGoodsYN = new String[] { "Representation.Item.Indicator", "Indicator.Content" };
        this.seq1 = new String[] { "Line.Number.Value", "Numeric.Content" };
        this.licenseNo = new String[] { "License.Number.Text", "Text.Content" };
        this.licenseCode = new String[] { "License.Code", "Code.Content" };
        this.licenseBeginDate = new String[] { "License.ValidityStart.Date", "DateTime.Content" };
        this.licenseEndDate = new String[] { "License.ValidityEnd.Date", "DateTime.Content" };
        this.sigongAccount = new String[] { "Construcation.AbliltyPar.Amount", "Amount.Content" };
        this.peonggaYear = new String[] { "ParRank.AmountStandardYear.Date", "DateTime.Content" };
        this.dpLicenseYN = new String[] { "Representation.License.Indicator", "Indicator.Content" };
        this.seq5 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.happenDate = new String[] { "Origin.Date", "DateTime.Content" };
        this.startDate = new String[] { "Start.Date", "DateTime.Content" };
        this.endDate = new String[] { "End.Date", "DateTime.Content" };
        this.stateGubun = new String[] { "General.Note.Text", "Text.Content" };
        this.note = new String[] { "Situation.Code", "Code.Content" };
        this.punishmentCount = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.stopGubun = new String[] { "Suspend.Indicator", "Indicator.Content" };
        this.stopDay = new String[] { "Suspend.Date", "DateTime.Content" };
        this.stopSayu = new String[] { "SuspendReason.Text", "Text.Content" };
        this.punishmentBasicCode = new String[] { "Sanction.Basis.Text", "Text.Content" };
        this.punishmentBasic = new String[] { "Sanction.Basis.Code", "Code.Content" };
        this.dipunishmentBasic = new String[] { "OtherSanction.Basis.Text", "Text.Content" };
        this.punishmentStart = new String[] { "Sanction.Date", "DateTime.Content" };
        this.punishmentEnd = new String[] { "Expiration.Date", "DateTime.Content" };
        this.punishmentPeriod = new String[] { "Sanction.Periode.Text", "Text.Content" };
        this.cancelDay = new String[] { "Contract.Cancel.Date", "DateTime.Content" };
        this.punishmentSayu = new String[] { "Sanction.Reason.Code", "Code.Content" };
        this.punishmentOrgCode = new String[] { "Sanction.Organization.Code", "Code.Content" };
        this.punishmentOrgCodeGubun = new String[] { "Sanction.GovernmentOffice.Code", "Code.Content" };
        this.saupNo = saupNo;
    }
    
    public void setSaupNo(final String saupNo) {
        this.saupNo = saupNo;
    }
    
    public void setReceiverIDs(final String receiverId) {
        this.receiverId = receiverId;
    }
    
    public String getReceiverIDs() {
        return this.receiverId;
    }
    
    public UM_GOE_ConiC080b[] getDateFromDB() throws SQLException, Exception {
        final StringBuffer sb = new StringBuffer();
        sb.append("\n SELECT U1.BIZ_REG_NO, U1.NATIONALITY, U1.BIZ_NM, U1.BIZ_EN_NM ");
        sb.append("\n\t    , U1.COMMENCEMENT_DT, U1.ESTABLISH_DT, U1.BIZ_CLS, U1.PRODUCT_CLS, U1.MAST_INDUSTRY_CD_STD ");
        sb.append("\n\t    , U1.CORP_REG_NO, U1.BIZ_CLS1, U1.BIZ_CLS2, U1.BIZ_CLS_YEAR, U1.CAPITAL ");
        sb.append("\n\t    , U1.EMPLOYEE_COUNT, U1.LAST_SETTLE_DT, U1.ZIP_CD, U1.AREA_CD, U1.ADDR ");
        sb.append("\n\t    , U1.DETAIL_ADDR, U1.PHONE_NO, U1.FAX, U1.WEBSITE, U1.SPECIAL_GOODS_YN ");
        sb.append("\n\t    , U1.MAST_GOOD_CLS_NO, U1.REG_DT, U1.UPDATE_DT, U1.REPR_BIZ_APPROVE_YN, U2.LICENSE_CD ");
        sb.append("\n\t    , U2.LICENSE_NO, U2.LICENSE_ISSUED_DT, U2.LICENSE_EXPIRY_DT, U2.CONST_ABIL_EVAL_AMT, U2.EVAL_STD_YEAR ");
        sb.append("\n\t    , U2.MAST_LICENSE_YN, U3.REPR_IDENT_NO, U3.REPR_NM, U3.REPR_EMAIL, U3.MAST_REPR_YN ");
        sb.append("\n\t    , U4.SERIAL_NO, U4.FACTORY_NM, U4.FACTORY_ADDR, U4.FACTORY_ADDR2, U4.FACTORY_PHONE_NO ");
        sb.append("\n\t    , U4.FACTORY_FAX, U4.FACTORY_ZIP_CD, U5.IDENT_NO, U5.NM, U5.DEPART ");
        sb.append("\n\t    , U5.POSITION, U5.PHONE_NO, U5.EMAIL, U5.MOBILE, U5.FAX ");
        sb.append("\n      , U6.GOOD_CLS_NO, U6.PERMIT_NO, U6.PERMIT_INSTITU, U6.PERMIT_DT, U6.INCOME_3YEARS ");
        sb.append("\n      , U6.DIRECT_PRODUCTION_YN, U6.MAST_GOODS_YN ");
        sb.append("\n      , U7.RAISED_DT, U7.START_DT, U7.END_DT, U7.STATE_CLS, U7.REMARK ");
        sb.append("\n\t    , U8.PUNISH_COUNT, U8.SUSPEND_CLS, U8.SUSPEND_DT, U8.SUSPEND_RSON, U8.PUNISH_BASIS1 ");
        sb.append("\n\t    , U8.PUNISH_BASIS2, U8.PUNISH_BY_LAW, U8.PUNISH_DT, U8.EXPIRE_DT, U8.PUNISH_PERIOD ");
        sb.append("\n\t    , U8.CANCEL_DT, U8.PUNISH_RSON, U8.PUNISH_INSTITU_CD_CLS, U8.PUNISH_INSTITU_CD ");
        sb.append("\n   FROM UM_LICENSE_FACTS U2 ");
        sb.append("\n      , UM_REPR U3 ");
        sb.append("\n      , UM_FACTORY_INFO U4 ");
        sb.append("\n      , UM_BID_AGENT U5 ");
        sb.append("\n      , UM_SUPPLIER_ENTER_ITEMS U6 ");
        sb.append("\n      , ( SELECT /*+ INDEX_DESC(U8 UM_ENTER_STATE) */ ");
        sb.append("\n                 S7.RAISED_DT, S7.START_DT, S7.END_DT, S7.STATE_CLS, S7.REMARK, S7.BIZ_REG_NO ");
        sb.append("\n            FROM UM_ENTER_STATE S7 ");
        sb.append("\n           WHERE S7.BIZ_REG_NO= ? ");
        sb.append("\n             AND ROWNUM=1 ) U7 ");
        sb.append("\n      , ( SELECT /*+ INDEX_DESC(U8 PK_UM_VIOLATE_COM) */ ");
        sb.append("\n                 S8.PUNISH_COUNT, S8.SUSPEND_CLS, S8.SUSPEND_DT, S8.SUSPEND_RSON, S8.PUNISH_BASIS1 ");
        sb.append("\n\t             , S8.PUNISH_BASIS2, S8.PUNISH_BY_LAW, S8.PUNISH_DT, S8.EXPIRE_DT, S8.PUNISH_PERIOD ");
        sb.append("\n\t             , S8.CANCEL_DT, S8.PUNISH_RSON, S8.PUNISH_INSTITU_CD_CLS, S8.PUNISH_INSTITU_CD ");
        sb.append("\n\t\t         , S8.BIZ_REG_NO ");
        sb.append("\n            FROM UM_VIOLATE_COM S8 ");
        sb.append("\n           WHERE S8.BIZ_REG_NO= ? ");
        sb.append("\n             AND ROWNUM=1 ) U8 ");
        sb.append("\n      , UM_SUPPLIER_ENTER_MAST U1 ");
        sb.append("\n  WHERE U1.BIZ_REG_NO = U2.BIZ_REG_NO(+) ");
        sb.append("\n    AND U1.BIZ_REG_NO = U3.BIZ_REG_NO(+) ");
        sb.append("\n    AND U1.BIZ_REG_NO = U4.BIZ_REG_NO(+) ");
        sb.append("\n    AND U1.BIZ_REG_NO = U5.BIZ_REG_NO(+) ");
        sb.append("\n    AND U1.BIZ_REG_NO = U6.BIZ_REG_NO(+) ");
        sb.append("\n    AND U1.BIZ_REG_NO = U7.BIZ_REG_NO(+) ");
        sb.append("\n    AND U1.BIZ_REG_NO = U8.BIZ_REG_NO(+) ");
        sb.append("\n    AND U1.BIZ_REG_NO = ? ");
        final Trx trx = new Trx(this, "usemn");
        final Connection connection = trx.getConnection();
        if (connection == null) {
            Log.errors("Get Connection failed");
            throw new SQLException("Connection is null");
        }
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        Object[] array = null;
        final Vector vector = new Vector<UM_GOE_ConiC080b>();
        try {
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, this.saupNo);
            prepareStatement.setString(2, this.saupNo);
            prepareStatement.setString(3, this.saupNo);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery != null) {
                while (executeQuery.next()) {
                    final UM_GOE_ConiC080b um_GOE_ConiC080b = new UM_GOE_ConiC080b();
                    um_GOE_ConiC080b.ett1.setSaupNo(executeQuery.getString(1));
                    um_GOE_ConiC080b.ett1.setNationality(executeQuery.getString(2));
                    um_GOE_ConiC080b.ett1.setSangho(executeQuery.getString(3));
                    um_GOE_ConiC080b.ett1.setESangho(executeQuery.getString(4));
                    um_GOE_ConiC080b.ett1.setOpenDate(executeQuery.getString(5));
                    um_GOE_ConiC080b.ett1.setBubinOpenDate(executeQuery.getString(6));
                    um_GOE_ConiC080b.ett1.setJobGubun(executeQuery.getString(7));
                    um_GOE_ConiC080b.ett1.setMakeGubun(executeQuery.getString(8));
                    um_GOE_ConiC080b.ett1.setDpUpjongCode(executeQuery.getString(9));
                    um_GOE_ConiC080b.ett1.setBubinNo(executeQuery.getString(10));
                    um_GOE_ConiC080b.ett1.setComGubun1(executeQuery.getString(11));
                    um_GOE_ConiC080b.ett1.setComGubun2(executeQuery.getString(12));
                    um_GOE_ConiC080b.ett1.setComGubunYear(executeQuery.getString(13));
                    um_GOE_ConiC080b.ett1.setJabon(executeQuery.getLong(14));
                    um_GOE_ConiC080b.ett1.setEmployeeNo(executeQuery.getInt(15));
                    um_GOE_ConiC080b.ett1.setAccountDate(executeQuery.getString(16));
                    um_GOE_ConiC080b.ett1.setZipCode(executeQuery.getString(17));
                    um_GOE_ConiC080b.ett1.setLocalCode(executeQuery.getString(18));
                    um_GOE_ConiC080b.ett1.setAddr(executeQuery.getString(19));
                    um_GOE_ConiC080b.ett1.setRestAddr(executeQuery.getString(20));
                    um_GOE_ConiC080b.ett1.setTel(executeQuery.getString(21));
                    um_GOE_ConiC080b.ett1.setFax(executeQuery.getString(22));
                    um_GOE_ConiC080b.ett1.setHomepage(executeQuery.getString(23));
                    um_GOE_ConiC080b.ett1.setExceptYN(executeQuery.getString(24));
                    um_GOE_ConiC080b.ett1.setRegistOkDate(executeQuery.getString(25));
                    um_GOE_ConiC080b.ett1.setRegistDate(executeQuery.getString(26));
                    um_GOE_ConiC080b.ett1.setRenewDate(executeQuery.getString(27));
                    um_GOE_ConiC080b.ett1.setDpOkYN(executeQuery.getString(28));
                    um_GOE_ConiC080b.ett2.setSaupNo(executeQuery.getString(1));
                    um_GOE_ConiC080b.ett2.setLicenseCode(executeQuery.getString(29));
                    um_GOE_ConiC080b.ett2.setLicenseNo(executeQuery.getString(30));
                    um_GOE_ConiC080b.ett2.setLicenseBeginDate(executeQuery.getString(31));
                    um_GOE_ConiC080b.ett2.setLicenseEndDate(executeQuery.getString(32));
                    um_GOE_ConiC080b.ett2.setSigongAccount(executeQuery.getLong(33));
                    um_GOE_ConiC080b.ett2.setPeonggaYear(executeQuery.getString(34));
                    um_GOE_ConiC080b.ett2.setDpLicenseYN(executeQuery.getString(35));
                    um_GOE_ConiC080b.ett3.setSaupNo(executeQuery.getString(1));
                    um_GOE_ConiC080b.ett3.setCeoJuminNo(executeQuery.getString(36));
                    um_GOE_ConiC080b.ett3.setCeoName(executeQuery.getString(37));
                    um_GOE_ConiC080b.ett3.setCeoMail(executeQuery.getString(38));
                    um_GOE_ConiC080b.ett3.setCeoYN(executeQuery.getString(39));
                    um_GOE_ConiC080b.ett4.setSaupNo(executeQuery.getString(1));
                    um_GOE_ConiC080b.ett4.setIlrunNo(executeQuery.getInt(40));
                    um_GOE_ConiC080b.ett4.setFactoryName(executeQuery.getString(41));
                    um_GOE_ConiC080b.ett4.setFactoryAddr(executeQuery.getString(42));
                    um_GOE_ConiC080b.ett4.setFactoryRestAddr(executeQuery.getString(43));
                    um_GOE_ConiC080b.ett4.setFactoryTel(executeQuery.getString(44));
                    um_GOE_ConiC080b.ett4.setFactoryFax(executeQuery.getString(45));
                    um_GOE_ConiC080b.ett4.setFactoryZipCode(executeQuery.getString(46));
                    um_GOE_ConiC080b.ett5.setSaupNo(executeQuery.getString(1));
                    um_GOE_ConiC080b.ett5.setJuminNo(executeQuery.getString(47));
                    um_GOE_ConiC080b.ett5.setName(executeQuery.getString(48));
                    um_GOE_ConiC080b.ett5.setJobPart(executeQuery.getString(49));
                    um_GOE_ConiC080b.ett5.setDutyName(executeQuery.getString(50));
                    um_GOE_ConiC080b.ett5.setTel(executeQuery.getString(51));
                    um_GOE_ConiC080b.ett5.setMail(executeQuery.getString(52));
                    um_GOE_ConiC080b.ett5.setHandphone(executeQuery.getString(53));
                    um_GOE_ConiC080b.ett5.setFax(executeQuery.getString(54));
                    um_GOE_ConiC080b.ett6.setSaupNo(executeQuery.getString(1));
                    um_GOE_ConiC080b.ett6.setGoodsNo(executeQuery.getString(55));
                    um_GOE_ConiC080b.ett6.setFormNo(executeQuery.getString(56));
                    um_GOE_ConiC080b.ett6.setFormOrg(executeQuery.getString(57));
                    um_GOE_ConiC080b.ett6.setFormDate(executeQuery.getString(58));
                    um_GOE_ConiC080b.ett6.setThreeSale(executeQuery.getLong(59));
                    um_GOE_ConiC080b.ett6.setMakeYN(executeQuery.getString(60));
                    um_GOE_ConiC080b.ett6.setDpGoodsYN(executeQuery.getString(61));
                    um_GOE_ConiC080b.ett7.setSaupNo(executeQuery.getString(1));
                    um_GOE_ConiC080b.ett7.setHappenDate(executeQuery.getString(62));
                    um_GOE_ConiC080b.ett7.setStartDate(executeQuery.getString(63));
                    um_GOE_ConiC080b.ett7.setEndDate(executeQuery.getString(64));
                    um_GOE_ConiC080b.ett7.setStateGubun(executeQuery.getString(65));
                    um_GOE_ConiC080b.ett7.setNote(executeQuery.getString(66));
                    um_GOE_ConiC080b.ett10.setSaupNo(executeQuery.getString(1));
                    um_GOE_ConiC080b.ett10.setPunishmentCount(executeQuery.getString(67));
                    um_GOE_ConiC080b.ett10.setStopGubun(executeQuery.getString(68));
                    um_GOE_ConiC080b.ett10.setStopDay(executeQuery.getString(69));
                    um_GOE_ConiC080b.ett10.setStopSayu(executeQuery.getString(70));
                    um_GOE_ConiC080b.ett10.setPunishmentBasicCode(executeQuery.getString(71));
                    um_GOE_ConiC080b.ett10.setPunishmentBasic(executeQuery.getString(72));
                    um_GOE_ConiC080b.ett10.setDipunishmentBasic(executeQuery.getString(73));
                    um_GOE_ConiC080b.ett10.setPunishmentStart(executeQuery.getString(74));
                    um_GOE_ConiC080b.ett10.setPunishmentEnd(executeQuery.getString(75));
                    um_GOE_ConiC080b.ett10.setPunishmentPeriod(executeQuery.getString(76));
                    um_GOE_ConiC080b.ett10.setCancelDay(executeQuery.getString(77));
                    um_GOE_ConiC080b.ett10.setPunishmentSayu(executeQuery.getString(78));
                    um_GOE_ConiC080b.ett10.setPunishmentOrgCode(executeQuery.getString(79));
                    um_GOE_ConiC080b.ett10.setPunishmentOrgCodeGubun(executeQuery.getString(80));
                    vector.add(um_GOE_ConiC080b);
                }
                executeQuery.close();
                if (!vector.isEmpty()) {
                    array = new UM_GOE_ConiC080b[vector.size()];
                    vector.copyInto(array);
                }
            }
            return (UM_GOE_ConiC080b[])array;
        }
        catch (SQLException ex) {
            Log.errors("" + this.getClass().getName() + ", getDateFromDB() Lỗi \n" + ", " + sb.toString() + " \n" + ", BIZ_REG_NO=" + this.saupNo + ", " + ex);
            throw ex;
        }
        catch (Exception ex2) {
            Log.errors("" + this.getClass().getName() + ", getDateFromDB() Lỗi \n" + ", BIZ_REG_NO=" + this.saupNo + ", " + ex2);
            throw ex2;
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (connection != null) {
                    trx.close();
                }
            }
            catch (Exception ex5) {}
        }
    }
    
    public String getReceiverID() throws SQLException, Exception {
        final StringBuffer sb = new StringBuffer();
        sb.append("\n SELECT U1.USER_ID ");
        sb.append("\n   FROM UM_USER U1 ");
        sb.append("\n      , UM_SUPPLIER_ENTER_MAST U2 ");
        sb.append("\n  WHERE U1.MAST_CD = U2.BIZ_REG_NO ");
        sb.append("\n    AND U1.USER_CLS = 'c' ");
        sb.append("\n    AND U2.BIZ_REG_NO = ? ");
        sb.append("\n  ORDER BY U1.MAST_RECV_YN DESC ");
        final Trx trx = new Trx(this, "usemn");
        final Connection connection = trx.getConnection();
        if (connection == null) {
            Log.errors("Get Connection failed");
            throw new SQLException("Connection is null");
        }
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String string = null;
        try {
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, this.saupNo);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery != null && executeQuery.next()) {
                string = executeQuery.getString(1);
                executeQuery.close();
            }
            return string;
        }
        catch (Exception ex) {
            Log.errors("" + this.getClass().getName() + ", getReceiverID() Lỗi \n" + ", BIZ_REG_NO=" + this.saupNo + ", " + ex);
            throw ex;
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
                if (connection != null) {
                    trx.close();
                }
            }
            catch (Exception ex4) {}
        }
    }
    
    public void setEntity(final UM_GOE_ConiC080b[] ettI) {
        this.ettI = ettI;
    }
    
    public void setEXelon(final String xmlSourceMaster, final String dxeHost, final int dxePort, final String dxeStore, final String xlnSourceMaster, final String dxeUser, final String dxePassword) {
        this.xmlSourceMaster = xmlSourceMaster;
        this.dxeHost = dxeHost;
        this.dxePort = dxePort;
        this.dxeStore = dxeStore;
        this.XlnSourceMaster = xlnSourceMaster;
        this.dxeUser = dxeUser;
        this.dxePassword = dxePassword;
    }
    
    private Hashtable[] makeDATA2XMLArray(final String s) throws Exception {
        try {
            if (this.ettI != null) {
                final Hashtable<String, String[]> hashtable = new Hashtable<String, String[]>();
                final Hashtable<String, String[]> hashtable2 = new Hashtable<String, String[]>();
                final Hashtable<String, String[]> hashtable3 = new Hashtable<String, String[]>();
                final Hashtable<String, String[]> hashtable4 = new Hashtable<String, String[]>();
                final Hashtable<String, String[]> hashtable5 = new Hashtable<String, String[]>();
                final Hashtable<String, String[]> hashtable6 = new Hashtable<String, String[]>();
                final Hashtable<String, String[]> hashtable7 = new Hashtable<String, String[]>();
                final Vector vector = new Vector<String>();
                for (int i = 0; i < this.ettI.length; ++i) {
                    if (s.equals(this.ettI[i].ett2.getSaupNo())) {
                        final String licenseCode = this.ettI[i].ett2.getLicenseCode();
                        if (licenseCode != null) {
                            if (!vector.contains(licenseCode)) {
                                vector.addElement(licenseCode);
                            }
                        }
                    }
                }
                if (!vector.isEmpty()) {
                    final String[] array = new String[vector.size()];
                    vector.copyInto(array);
                    hashtable.put(s, array);
                }
                final Vector<String> vector2 = new Vector<String>();
                for (int j = 0; j < this.ettI.length; ++j) {
                    if (s.equals(this.ettI[j].ett3.getSaupNo())) {
                        final String ceoJuminNo = this.ettI[j].ett3.getCeoJuminNo();
                        if (ceoJuminNo != null) {
                            if (!vector2.contains(ceoJuminNo)) {
                                vector2.addElement(ceoJuminNo);
                            }
                        }
                    }
                }
                if (!vector2.isEmpty()) {
                    final String[] array2 = new String[vector2.size()];
                    vector2.copyInto(array2);
                    hashtable2.put(s, array2);
                }
                final Vector<String> vector3 = new Vector<String>();
                for (int k = 0; k < this.ettI.length; ++k) {
                    if (s.equals(this.ettI[k].ett4.getSaupNo())) {
                        final String string = "" + this.ettI[k].ett4.getIlrunNo();
                        if (string != null) {
                            if (!vector3.contains(string)) {
                                vector3.addElement(string);
                            }
                        }
                    }
                }
                if (!vector3.isEmpty()) {
                    final String[] array3 = new String[vector3.size()];
                    vector3.copyInto(array3);
                    hashtable3.put(s, array3);
                }
                final Vector<String> vector4 = new Vector<String>();
                for (int l = 0; l < this.ettI.length; ++l) {
                    if (s.equals(this.ettI[l].ett5.getSaupNo())) {
                        final String juminNo = this.ettI[l].ett5.getJuminNo();
                        if (juminNo != null) {
                            if (!vector4.contains(juminNo)) {
                                vector4.addElement(juminNo);
                            }
                        }
                    }
                }
                if (!vector4.isEmpty()) {
                    final String[] array4 = new String[vector4.size()];
                    vector4.copyInto(array4);
                    hashtable4.put(s, array4);
                }
                final Vector<String> vector5 = new Vector<String>();
                for (int n = 0; n < this.ettI.length; ++n) {
                    if (s.equals(this.ettI[n].ett6.getSaupNo())) {
                        final String goodsNo = this.ettI[n].ett6.getGoodsNo();
                        if (goodsNo != null) {
                            if (!vector5.contains(goodsNo)) {
                                vector5.addElement(goodsNo);
                            }
                        }
                    }
                }
                if (!vector5.isEmpty()) {
                    final String[] array5 = new String[vector5.size()];
                    vector5.copyInto(array5);
                    hashtable5.put(s, array5);
                }
                final Vector<String> vector6 = new Vector<String>();
                for (int n2 = 0; n2 < this.ettI.length; ++n2) {
                    if (s.equals(this.ettI[n2].ett7.getSaupNo())) {
                        final String happenDate = this.ettI[n2].ett7.getHappenDate();
                        if (happenDate != null) {
                            if (!vector6.contains(happenDate)) {
                                vector6.addElement(happenDate);
                            }
                        }
                    }
                }
                if (!vector6.isEmpty()) {
                    final String[] array6 = new String[vector6.size()];
                    vector6.copyInto(array6);
                    hashtable6.put(s, array6);
                }
                final Vector<String> vector7 = new Vector<String>();
                for (int n3 = 0; n3 < this.ettI.length; ++n3) {
                    if (s.equals(this.ettI[n3].ett10.getSaupNo())) {
                        final String punishmentCount = this.ettI[n3].ett10.getPunishmentCount();
                        if (punishmentCount != null) {
                            if (!vector7.contains(punishmentCount)) {
                                vector7.addElement(punishmentCount);
                            }
                        }
                    }
                }
                if (!vector7.isEmpty()) {
                    final String[] array7 = new String[vector7.size()];
                    vector7.copyInto(array7);
                    hashtable7.put(s, array7);
                }
                return new Hashtable[] { hashtable, hashtable2, hashtable3, hashtable4, hashtable5, hashtable6, hashtable7 };
            }
            return null;
        }
        catch (Exception ex) {
            Log.errors("" + this.getClass().getName() + ", makeDATA2XMLArray() Lỗi \n" + ", " + ex);
            throw ex;
        }
    }
    
    private void makeDATA2XMLArray2(final String s) throws Exception {
        Hashtable[] data2XMLArray;
        try {
            data2XMLArray = this.makeDATA2XMLArray(s);
            if (data2XMLArray == null) {
                return;
            }
        }
        catch (Exception ex) {
            throw ex;
        }
        try {
            final Vector vector = new Vector<UM_GOE_ConiC010b>();
            final Vector vector2 = new Vector<UM_GOE_ConiC020b>();
            final Vector vector3 = new Vector<UM_GOE_ConiC030b>();
            final Vector vector4 = new Vector<UM_GOE_ConiC040b>();
            final Vector vector5 = new Vector<UM_GOE_ConiC050b>();
            final Vector vector6 = new Vector<UM_GOE_ConiC060b>();
            final Vector vector7 = new Vector<UM_GOE_ConiC070b>();
            final Vector vector8 = new Vector<UM_GOE_ConiC100b>();
            if (s.equals(this.ettI[0].ett1.getSaupNo())) {
                vector.addElement(this.ettI[0].ett1);
            }
            final String[] array = (String[])data2XMLArray[0].get(s);
            if (array != null) {
                for (int i = 0; i < array.length; ++i) {
                    final String s2 = array[i];
                    for (int j = 0; j < this.ettI.length; ++j) {
                        final String licenseCode = this.ettI[j].ett2.getLicenseCode();
                        if (s.equals(this.ettI[j].ett2.getSaupNo()) && s2.equals(licenseCode)) {
                            vector2.addElement(this.ettI[j].ett2);
                            break;
                        }
                    }
                }
            }
            final String[] array2 = (String[])data2XMLArray[1].get(s);
            if (array2 != null) {
                for (int k = 0; k < array2.length; ++k) {
                    final String s3 = array2[k];
                    for (int l = 0; l < this.ettI.length; ++l) {
                        final String ceoJuminNo = this.ettI[l].ett3.getCeoJuminNo();
                        if (s.equals(this.ettI[l].ett3.getSaupNo()) && s3.equals(ceoJuminNo)) {
                            vector3.addElement(this.ettI[l].ett3);
                            break;
                        }
                    }
                }
            }
            final String[] array3 = (String[])data2XMLArray[2].get(s);
            if (array3 != null) {
                for (int n = 0; n < array3.length; ++n) {
                    final String s4 = array3[n];
                    for (int n2 = 0; n2 < this.ettI.length; ++n2) {
                        final String string = "" + this.ettI[n2].ett4.getIlrunNo();
                        if (s.equals(this.ettI[n2].ett4.getSaupNo()) && s4.equals(string)) {
                            vector4.addElement(this.ettI[n2].ett4);
                            break;
                        }
                    }
                }
            }
            final String[] array4 = (String[])data2XMLArray[3].get(s);
            if (array4 != null) {
                for (int n3 = 0; n3 < array4.length; ++n3) {
                    final String s5 = array4[n3];
                    for (int n4 = 0; n4 < this.ettI.length; ++n4) {
                        final String juminNo = this.ettI[n4].ett5.getJuminNo();
                        if (s.equals(this.ettI[n4].ett5.getSaupNo()) && s5.equals(juminNo)) {
                            vector5.addElement(this.ettI[n4].ett5);
                            break;
                        }
                    }
                }
            }
            final String[] array5 = (String[])data2XMLArray[4].get(s);
            if (array5 != null) {
                for (int n5 = 0; n5 < array5.length; ++n5) {
                    final String s6 = array5[n5];
                    for (int n6 = 0; n6 < this.ettI.length; ++n6) {
                        final String goodsNo = this.ettI[n6].ett6.getGoodsNo();
                        if (s.equals(this.ettI[n6].ett6.getSaupNo()) && s6.equals(goodsNo)) {
                            vector6.addElement(this.ettI[n6].ett6);
                            break;
                        }
                    }
                }
            }
            final String[] array6 = (String[])data2XMLArray[5].get(s);
            if (array6 != null) {
                for (int n7 = 0; n7 < array6.length; ++n7) {
                    final String s7 = array6[n7];
                    for (int n8 = 0; n8 < this.ettI.length; ++n8) {
                        final String happenDate = this.ettI[n8].ett7.getHappenDate();
                        if (s.equals(this.ettI[n8].ett7.getSaupNo()) && s7.equals(happenDate)) {
                            vector7.addElement(this.ettI[n8].ett7);
                            break;
                        }
                    }
                }
            }
            final String[] array7 = (String[])data2XMLArray[6].get(s);
            if (array7 != null) {
                for (int n9 = 0; n9 < array7.length; ++n9) {
                    final String s8 = array7[n9];
                    for (int n10 = 0; n10 < this.ettI.length; ++n10) {
                        final String punishmentCount = this.ettI[n10].ett10.getPunishmentCount();
                        if (s.equals(this.ettI[n10].ett10.getSaupNo()) && s8.equals(punishmentCount)) {
                            vector8.addElement(this.ettI[n10].ett10);
                            break;
                        }
                    }
                }
            }
            if (vector.size() != 0) {
                vector.copyInto(this.ett01 = new UM_GOE_ConiC010b[1]);
            }
            if (vector2.size() != 0) {
                vector2.copyInto(this.ett02 = new UM_GOE_ConiC020b[vector2.size()]);
            }
            if (vector3.size() != 0) {
                vector3.copyInto(this.ett03 = new UM_GOE_ConiC030b[vector3.size()]);
            }
            if (vector4.size() != 0) {
                vector4.copyInto(this.ett04 = new UM_GOE_ConiC040b[vector4.size()]);
            }
            if (vector5.size() != 0) {
                vector5.copyInto(this.ett05 = new UM_GOE_ConiC050b[vector5.size()]);
            }
            if (vector6.size() != 0) {
                vector6.copyInto(this.ett06 = new UM_GOE_ConiC060b[vector6.size()]);
            }
            if (vector7.size() != 0) {
                vector7.copyInto(this.ett07 = new UM_GOE_ConiC070b[vector7.size()]);
            }
            if (vector8.size() != 0) {
                vector8.copyInto(this.ett10 = new UM_GOE_ConiC100b[vector8.size()]);
            }
        }
        catch (Exception ex2) {
            Log.errors("" + this.getClass().getName() + ", makeDATA2XMLArray2() Lỗi \n" + ", " + ex2);
            throw ex2;
        }
    }
    
    public String startDataXML() throws Exception {
        final Vector vector = new Vector();
        try {
            this.makeDATA2XMLArray2(this.saupNo);
            if (this.ett01 != null && 0 < this.ett01.length) {
                final Vector vector2 = new Vector();
                final Vector vector3 = new Vector();
                final Vector vector4 = new Vector();
                final Vector vector5 = new Vector();
                final Vector vector6 = new Vector();
                final Vector vector7 = new Vector();
                final Vector vector8 = new Vector();
                String parsingXML;
                try {
                    parsingXML = this.parsingXML(this.ett01[0], this.ett02, this.ett03, this.ett04, this.ett05, this.ett06, this.ett07, this.ett10);
                }
                catch (Exception ex) {
                    throw ex;
                }
                this.ett02 = null;
                this.ett03 = null;
                this.ett04 = null;
                this.ett05 = null;
                this.ett06 = null;
                this.ett07 = null;
                this.ett10 = null;
                return parsingXML;
            }
            return null;
        }
        catch (Exception ex2) {
            Log.errors("" + this.getClass().getName() + ", startDataXML() Lỗi \n" + ", " + ex2);
            throw ex2;
        }
    }
    
    private String parsingXML(final UM_GOE_ConiC010b um_GOE_ConiC010b, final UM_GOE_ConiC020b[] array, final UM_GOE_ConiC030b[] array2, final UM_GOE_ConiC040b[] array3, final UM_GOE_ConiC050b[] array4, final UM_GOE_ConiC060b[] array5, final UM_GOE_ConiC070b[] array6, final UM_GOE_ConiC100b[] array7) throws Exception {
        final DOMParser domParser = new DOMParser();
        final UM_GOE_ConiC090b um_GOE_ConiC090b = new UM_GOE_ConiC090b();
        if (um_GOE_ConiC010b == null) {
            return null;
        }
        try {
            System.setProperty("com.exln.dxe.adminhost", this.dxeHost);
            final Reader reader = null;
            domParser.parse(reader);
            final XMLDocument document = domParser.getDocument();
            ((StringReader)reader).close();
            this.receiver[0] = ComStr.checkNull(this.getReceiverIDs(), "");
            this.PutValues(document, this.receiver);
            um_GOE_ConiC090b.saupNo[0] = um_GOE_ConiC010b.getSaupNo();
            um_GOE_ConiC090b.nationality[0] = ComStr.checkNull(um_GOE_ConiC010b.getNationality(), "");
            um_GOE_ConiC090b.sangho[0] = ComStr.checkNull(um_GOE_ConiC010b.getSangho(), "");
            um_GOE_ConiC090b.eSangho[0] = ComStr.checkNull(um_GOE_ConiC010b.getESangho(), "");
            um_GOE_ConiC090b.openDate[0] = ComStr.checkNull(um_GOE_ConiC010b.getOpenDate(), "");
            um_GOE_ConiC090b.bubinOpenDate[0] = ComStr.checkNull(um_GOE_ConiC010b.getBubinOpenDate(), "");
            um_GOE_ConiC090b.jobGubun[0] = ComStr.checkNull(um_GOE_ConiC010b.getJobGubun(), "");
            um_GOE_ConiC090b.makeGubun[0] = ComStr.checkNull(um_GOE_ConiC010b.getMakeGubun(), "");
            um_GOE_ConiC090b.dpUpjongCode[0] = ComStr.checkNull(um_GOE_ConiC010b.getDpUpjongCode(), "");
            um_GOE_ConiC090b.bubinNo[0] = ComStr.checkNull(um_GOE_ConiC010b.getBubinNo(), "");
            um_GOE_ConiC090b.comGubun1[0] = ComStr.checkNull(um_GOE_ConiC010b.getComGubun1(), "");
            um_GOE_ConiC090b.comGubun2[0] = ComStr.checkNull(um_GOE_ConiC010b.getComGubun2(), "");
            um_GOE_ConiC090b.comGubunYear[0] = ComStr.checkNull(um_GOE_ConiC010b.getComGubunYear(), "");
            um_GOE_ConiC090b.jabon[0] = ComStr.checkNull("" + um_GOE_ConiC010b.getJabon(), "");
            um_GOE_ConiC090b.employeeNo[0] = ComStr.checkNull("" + um_GOE_ConiC010b.getEmployeeNo(), "");
            um_GOE_ConiC090b.accountDate[0] = ComStr.checkNull(um_GOE_ConiC010b.getAccountDate(), "");
            um_GOE_ConiC090b.zipCode[0] = ComStr.checkNull(um_GOE_ConiC010b.getZipCode(), "");
            um_GOE_ConiC090b.localCode[0] = ComStr.checkNull(um_GOE_ConiC010b.getLocalCode(), "");
            um_GOE_ConiC090b.addr[0] = ComStr.checkNull(um_GOE_ConiC010b.getAddr(), "");
            um_GOE_ConiC090b.restAddr[0] = ComStr.checkNull(um_GOE_ConiC010b.getRestAddr(), "");
            um_GOE_ConiC090b.tel[0] = ComStr.checkNull(um_GOE_ConiC010b.getTel(), "");
            um_GOE_ConiC090b.fax[0] = ComStr.checkNull(um_GOE_ConiC010b.getFax(), "");
            um_GOE_ConiC090b.homepage[0] = ComStr.checkNull(um_GOE_ConiC010b.getHomepage(), "");
            um_GOE_ConiC090b.exceptYN[0] = ComStr.checkNull(um_GOE_ConiC010b.getExceptYN(), "");
            um_GOE_ConiC090b.registOkDate[0] = ComStr.checkNull(um_GOE_ConiC010b.getRegistOkDate(), "");
            um_GOE_ConiC090b.registDate[0] = ComStr.checkNull(um_GOE_ConiC010b.getRegistDate(), "");
            um_GOE_ConiC090b.renewDate[0] = ComStr.checkNull(um_GOE_ConiC010b.getRenewDate(), "");
            um_GOE_ConiC090b.dpOkYN[0] = ComStr.checkNull(um_GOE_ConiC010b.getDpOkYN(), "");
            this.PutValues(document, um_GOE_ConiC090b.saupNo);
            this.PutValues(document, um_GOE_ConiC090b.nationality);
            this.PutValues(document, um_GOE_ConiC090b.sangho);
            this.PutValues(document, um_GOE_ConiC090b.eSangho);
            this.PutValues(document, um_GOE_ConiC090b.openDate);
            this.PutValues(document, um_GOE_ConiC090b.bubinOpenDate);
            this.PutValues(document, um_GOE_ConiC090b.jobGubun);
            this.PutValues(document, um_GOE_ConiC090b.makeGubun);
            this.PutValues(document, um_GOE_ConiC090b.dpUpjongCode);
            this.PutValues(document, um_GOE_ConiC090b.bubinNo);
            this.PutValues(document, um_GOE_ConiC090b.comGubun1);
            this.PutValues(document, um_GOE_ConiC090b.comGubun2);
            this.PutValues(document, um_GOE_ConiC090b.comGubunYear);
            this.PutValues(document, um_GOE_ConiC090b.jabon);
            this.PutValues(document, um_GOE_ConiC090b.employeeNo);
            this.PutValues(document, um_GOE_ConiC090b.accountDate);
            this.PutValues(document, um_GOE_ConiC090b.zipCode);
            this.PutValues(document, um_GOE_ConiC090b.localCode);
            this.PutValues(document, um_GOE_ConiC090b.addr);
            this.PutValues(document, um_GOE_ConiC090b.restAddr);
            this.PutValues(document, um_GOE_ConiC090b.tel);
            this.PutValues(document, um_GOE_ConiC090b.fax);
            this.PutValues(document, um_GOE_ConiC090b.homepage);
            this.PutValues(document, um_GOE_ConiC090b.exceptYN);
            this.PutValues(document, um_GOE_ConiC090b.registOkDate);
            this.PutValues(document, um_GOE_ConiC090b.registDate);
            this.PutValues(document, um_GOE_ConiC090b.renewDate);
            this.PutValues(document, um_GOE_ConiC090b.dpOkYN);
            if (array2 != null) {
                for (int n = 0; n < array2.length && array2[n].getCeoJuminNo() != null; ++n) {
                    if (array2[n].getCeoJuminNo().trim().length() == 0) {
                        break;
                    }
                    if (array2.length == 1) {
                        um_GOE_ConiC090b.seq2[0] = ComStr.checkNull("" + (n + 1), "");
                        um_GOE_ConiC090b.ceoJuminNo[0] = ComStr.checkNull(array2[n].getCeoJuminNo(), "");
                        um_GOE_ConiC090b.ceoName[0] = ComStr.checkNull(array2[n].getCeoName(), "");
                        um_GOE_ConiC090b.ceoMail[0] = ComStr.checkNull(array2[n].getCeoMail(), "");
                        um_GOE_ConiC090b.ceoYN[0] = ComStr.checkNull(array2[n].getCeoYN(), "");
                        this.PutValues(document, um_GOE_ConiC090b.seq2);
                        this.PutValues(document, um_GOE_ConiC090b.ceoJuminNo);
                        this.PutValues(document, um_GOE_ConiC090b.ceoName);
                        this.PutValues(document, um_GOE_ConiC090b.ceoMail);
                        this.PutValues(document, um_GOE_ConiC090b.ceoYN);
                        break;
                    }
                    um_GOE_ConiC090b.seq2[0] = ComStr.checkNull("" + (n + 1), "");
                    um_GOE_ConiC090b.ceoJuminNo[0] = ComStr.checkNull(array2[n].getCeoJuminNo(), "");
                    um_GOE_ConiC090b.ceoName[0] = ComStr.checkNull(array2[n].getCeoName(), "");
                    um_GOE_ConiC090b.ceoMail[0] = ComStr.checkNull(array2[n].getCeoMail(), "");
                    um_GOE_ConiC090b.ceoYN[0] = ComStr.checkNull(array2[n].getCeoYN(), "");
                    this.dpPutValues(document, um_GOE_ConiC090b.seq2, this.seq2, n, 1);
                    this.dpPutValues(document, um_GOE_ConiC090b.ceoJuminNo, this.ceoJuminNo, n, 0);
                    this.dpPutValues(document, um_GOE_ConiC090b.ceoName, this.ceoName, n, 0);
                    this.dpPutValues(document, um_GOE_ConiC090b.ceoMail, this.ceoMail, n, 0);
                    this.dpPutValues(document, um_GOE_ConiC090b.ceoYN, this.ceoYN, n, 0);
                }
            }
            if (array3 != null) {
                for (int i = 0; i < array3.length; ++i) {
                    if (array3[i].getIlrunNo() == 0) {
                        break;
                    }
                    if (array3.length == 1) {
                        um_GOE_ConiC090b.ilrunNo[0] = ComStr.checkNull("" + array3[i].getIlrunNo(), "");
                        um_GOE_ConiC090b.factoryName[0] = ComStr.checkNull(array3[i].getFactoryName(), "");
                        um_GOE_ConiC090b.factoryZipCode[0] = ComStr.checkNull(array3[i].getFactoryZipCode(), "");
                        um_GOE_ConiC090b.factoryAddr[0] = ComStr.checkNull(array3[i].getFactoryAddr(), "");
                        um_GOE_ConiC090b.factoryRestAddr[0] = ComStr.checkNull(array3[i].getFactoryRestAddr(), "");
                        um_GOE_ConiC090b.factoryTel[0] = ComStr.checkNull(array3[i].getFactoryTel(), "");
                        um_GOE_ConiC090b.factoryFax[0] = ComStr.checkNull(array3[i].getFactoryFax(), "");
                        this.PutValues(document, um_GOE_ConiC090b.ilrunNo);
                        this.PutValues(document, um_GOE_ConiC090b.factoryName);
                        this.PutValues(document, um_GOE_ConiC090b.factoryZipCode);
                        this.PutValues(document, um_GOE_ConiC090b.factoryAddr);
                        this.PutValues(document, um_GOE_ConiC090b.factoryRestAddr);
                        this.PutValues(document, um_GOE_ConiC090b.factoryTel);
                        this.PutValues(document, um_GOE_ConiC090b.factoryFax);
                        break;
                    }
                    um_GOE_ConiC090b.ilrunNo[0] = ComStr.checkNull("" + array3[i].getIlrunNo(), "");
                    um_GOE_ConiC090b.factoryName[0] = ComStr.checkNull(array3[i].getFactoryName(), "");
                    um_GOE_ConiC090b.factoryZipCode[0] = ComStr.checkNull(array3[i].getFactoryZipCode(), "");
                    um_GOE_ConiC090b.factoryAddr[0] = ComStr.checkNull(array3[i].getFactoryAddr(), "");
                    um_GOE_ConiC090b.factoryRestAddr[0] = ComStr.checkNull(array3[i].getFactoryRestAddr(), "");
                    um_GOE_ConiC090b.factoryTel[0] = ComStr.checkNull(array3[i].getFactoryTel(), "");
                    um_GOE_ConiC090b.factoryFax[0] = ComStr.checkNull(array3[i].getFactoryFax(), "");
                    this.facPutValues(document, um_GOE_ConiC090b.ilrunNo, this.ilrunNo, i, 1);
                    this.facPutValues(document, um_GOE_ConiC090b.factoryName, this.factoryName, i, 0);
                    this.facPutValues(document, um_GOE_ConiC090b.factoryZipCode, this.factoryZipCode, i, 0);
                    this.facPutValues(document, um_GOE_ConiC090b.factoryAddr, this.factoryAddr, i, 0);
                    this.facPutValues(document, um_GOE_ConiC090b.factoryRestAddr, this.factoryRestAddr, i, 0);
                    this.facPutValues(document, um_GOE_ConiC090b.factoryTel, this.factoryTel, i, 0);
                    this.facPutValues(document, um_GOE_ConiC090b.factoryFax, this.factoryFax, i, 0);
                }
            }
            if (array4 != null) {
                for (int n2 = 0; n2 < array4.length && array4[n2].getJuminNo() != null; ++n2) {
                    if (array4[n2].getJuminNo().trim().length() == 0) {
                        break;
                    }
                    if (array4.length == 1) {
                        um_GOE_ConiC090b.seq3[0] = ComStr.checkNull("" + (n2 + 1), "");
                        um_GOE_ConiC090b.juminNo[0] = ComStr.checkNull(array4[n2].getJuminNo(), "");
                        um_GOE_ConiC090b.name[0] = ComStr.checkNull(array4[n2].getName(), "");
                        um_GOE_ConiC090b.jobPart[0] = ComStr.checkNull(array4[n2].getJobPart(), "");
                        um_GOE_ConiC090b.dutyName[0] = ComStr.checkNull(array4[n2].getDutyName(), "");
                        um_GOE_ConiC090b.tel3[0] = ComStr.checkNull(array4[n2].getTel(), "");
                        um_GOE_ConiC090b.mail[0] = ComStr.checkNull(array4[n2].getMail(), "");
                        um_GOE_ConiC090b.handphone[0] = ComStr.checkNull(array4[n2].getHandphone(), "");
                        um_GOE_ConiC090b.fax3[0] = ComStr.checkNull(array4[n2].getFax(), "");
                        this.PutValues(document, um_GOE_ConiC090b.seq3);
                        this.PutValues(document, um_GOE_ConiC090b.jobPart);
                        this.PutValues(document, um_GOE_ConiC090b.name);
                        this.PutValues(document, um_GOE_ConiC090b.dutyName);
                        this.PutValues(document, um_GOE_ConiC090b.tel3);
                        this.PutValues(document, um_GOE_ConiC090b.fax3);
                        this.PutValues(document, um_GOE_ConiC090b.mail);
                        this.PutValues(document, um_GOE_ConiC090b.juminNo);
                        this.PutValues(document, um_GOE_ConiC090b.handphone);
                        break;
                    }
                    um_GOE_ConiC090b.seq3[0] = ComStr.checkNull("" + (n2 + 1), "");
                    um_GOE_ConiC090b.juminNo[0] = ComStr.checkNull(array4[n2].getJuminNo(), "");
                    um_GOE_ConiC090b.name[0] = ComStr.checkNull(array4[n2].getName(), "");
                    um_GOE_ConiC090b.jobPart[0] = ComStr.checkNull(array4[n2].getJobPart(), "");
                    um_GOE_ConiC090b.dutyName[0] = ComStr.checkNull(array4[n2].getDutyName(), "");
                    um_GOE_ConiC090b.tel3[0] = ComStr.checkNull(array4[n2].getTel(), "");
                    um_GOE_ConiC090b.mail[0] = ComStr.checkNull(array4[n2].getMail(), "");
                    um_GOE_ConiC090b.handphone[0] = ComStr.checkNull(array4[n2].getHandphone(), "");
                    um_GOE_ConiC090b.fax3[0] = ComStr.checkNull(array4[n2].getFax(), "");
                    this.idPutValues(document, um_GOE_ConiC090b.seq3, this.seq3, n2, 1);
                    this.idPutValues(document, um_GOE_ConiC090b.jobPart, this.jobPart, n2, 0);
                    this.idPutValues(document, um_GOE_ConiC090b.name, this.name, n2, 0);
                    this.idPutValues(document, um_GOE_ConiC090b.dutyName, this.dutyName, n2, 0);
                    this.idPutValues(document, um_GOE_ConiC090b.tel3, this.tel3, n2, 0);
                    this.idPutValues(document, um_GOE_ConiC090b.fax3, this.fax3, n2, 0);
                    this.idPutValues(document, um_GOE_ConiC090b.mail, this.mail, n2, 0);
                    this.idPutValues(document, um_GOE_ConiC090b.juminNo, this.juminNo, n2, 0);
                    this.idPutValues(document, um_GOE_ConiC090b.handphone, this.handphone, n2, 0);
                }
            }
            if (array5 != null) {
                for (int n3 = 0; n3 < array5.length && array5[n3].getGoodsNo() != null; ++n3) {
                    if (array5[n3].getGoodsNo().trim().length() == 0) {
                        break;
                    }
                    if (array5.length == 1) {
                        um_GOE_ConiC090b.seq4[0] = ComStr.checkNull("" + (n3 + 1), "");
                        um_GOE_ConiC090b.goodsNo[0] = ComStr.checkNull(array5[n3].getGoodsNo(), "");
                        um_GOE_ConiC090b.formNo[0] = ComStr.checkNull(array5[n3].getFormNo(), "");
                        um_GOE_ConiC090b.formOrg[0] = ComStr.checkNull(array5[n3].getFormOrg(), "");
                        um_GOE_ConiC090b.formDate[0] = ComStr.checkNull(array5[n3].getFormDate(), "");
                        um_GOE_ConiC090b.threeSale[0] = ComStr.checkNull("" + array5[n3].getThreeSale(), "");
                        um_GOE_ConiC090b.makeYN[0] = ComStr.checkNull(array5[n3].getMakeYN(), "");
                        um_GOE_ConiC090b.dpGoodsYN[0] = ComStr.checkNull(array5[n3].getDpGoodsYN(), "");
                        this.PutValues(document, um_GOE_ConiC090b.seq4);
                        this.PutValues(document, um_GOE_ConiC090b.goodsNo);
                        this.PutValues(document, um_GOE_ConiC090b.formNo);
                        this.PutValues(document, um_GOE_ConiC090b.formOrg);
                        this.PutValues(document, um_GOE_ConiC090b.formDate);
                        this.PutValues(document, um_GOE_ConiC090b.threeSale);
                        this.PutValues(document, um_GOE_ConiC090b.makeYN);
                        this.PutValues(document, um_GOE_ConiC090b.dpGoodsYN);
                        break;
                    }
                    um_GOE_ConiC090b.seq4[0] = ComStr.checkNull("" + (n3 + 1), "");
                    um_GOE_ConiC090b.goodsNo[0] = ComStr.checkNull(array5[n3].getGoodsNo(), "");
                    um_GOE_ConiC090b.formNo[0] = ComStr.checkNull(array5[n3].getFormNo(), "");
                    um_GOE_ConiC090b.formOrg[0] = ComStr.checkNull(array5[n3].getFormOrg(), "");
                    um_GOE_ConiC090b.formDate[0] = ComStr.checkNull(array5[n3].getFormDate(), "");
                    um_GOE_ConiC090b.threeSale[0] = ComStr.checkNull("" + array5[n3].getThreeSale(), "");
                    um_GOE_ConiC090b.makeYN[0] = ComStr.checkNull(array5[n3].getMakeYN(), "");
                    um_GOE_ConiC090b.dpGoodsYN[0] = ComStr.checkNull(array5[n3].getDpGoodsYN(), "");
                    this.maPutValues(document, um_GOE_ConiC090b.seq4, this.seq4, n3, 1);
                    this.maPutValues(document, um_GOE_ConiC090b.goodsNo, this.goodsNo, n3, 0);
                    this.maPutValues(document, um_GOE_ConiC090b.formNo, this.formNo, n3, 0);
                    this.maPutValues(document, um_GOE_ConiC090b.formOrg, this.formOrg, n3, 0);
                    this.maPutValues(document, um_GOE_ConiC090b.formDate, this.formDate, n3, 0);
                    this.maPutValues(document, um_GOE_ConiC090b.threeSale, this.threeSale, n3, 0);
                    this.maPutValues(document, um_GOE_ConiC090b.makeYN, this.makeYN, n3, 0);
                    this.maPutValues(document, um_GOE_ConiC090b.dpGoodsYN, this.dpGoodsYN, n3, 0);
                }
            }
            if (array != null) {
                for (int n4 = 0; n4 < array.length && array[n4].getLicenseNo() != null; ++n4) {
                    if (array[n4].getLicenseNo().trim().length() == 0) {
                        break;
                    }
                    if (array.length == 1) {
                        um_GOE_ConiC090b.seq1[0] = ComStr.checkNull("" + (n4 + 1), "");
                        um_GOE_ConiC090b.licenseNo[0] = ComStr.checkNull(array[n4].getLicenseNo(), "");
                        um_GOE_ConiC090b.licenseCode[0] = ComStr.checkNull(array[n4].getLicenseCode(), "");
                        um_GOE_ConiC090b.licenseBeginDate[0] = ComStr.checkNull(array[n4].getLicenseBeginDate(), "");
                        um_GOE_ConiC090b.licenseEndDate[0] = ComStr.checkNull(array[n4].getLicenseEndDate(), "");
                        um_GOE_ConiC090b.sigongAccount[0] = ComStr.checkNull("" + array[n4].getSigongAccount(), "");
                        um_GOE_ConiC090b.peonggaYear[0] = ComStr.checkNull(array[n4].getPeonggaYear(), "");
                        um_GOE_ConiC090b.dpLicenseYN[0] = ComStr.checkNull(array[n4].getDpLicenseYN(), "");
                        this.PutValues(document, um_GOE_ConiC090b.seq1);
                        this.PutValues(document, um_GOE_ConiC090b.licenseNo);
                        this.PutValues(document, um_GOE_ConiC090b.licenseCode);
                        this.PutValues(document, um_GOE_ConiC090b.licenseBeginDate);
                        this.PutValues(document, um_GOE_ConiC090b.licenseEndDate);
                        this.PutValues(document, um_GOE_ConiC090b.sigongAccount);
                        this.PutValues(document, um_GOE_ConiC090b.peonggaYear);
                        this.PutValues(document, um_GOE_ConiC090b.dpLicenseYN);
                        break;
                    }
                    um_GOE_ConiC090b.seq1[0] = ComStr.checkNull("" + (n4 + 1), "");
                    um_GOE_ConiC090b.licenseNo[0] = ComStr.checkNull(array[n4].getLicenseNo(), "");
                    um_GOE_ConiC090b.licenseCode[0] = ComStr.checkNull(array[n4].getLicenseCode(), "");
                    um_GOE_ConiC090b.licenseBeginDate[0] = ComStr.checkNull(array[n4].getLicenseBeginDate(), "");
                    um_GOE_ConiC090b.licenseEndDate[0] = ComStr.checkNull(array[n4].getLicenseEndDate(), "");
                    um_GOE_ConiC090b.sigongAccount[0] = ComStr.checkNull("" + array[n4].getSigongAccount(), "");
                    um_GOE_ConiC090b.peonggaYear[0] = ComStr.checkNull(array[n4].getPeonggaYear(), "");
                    um_GOE_ConiC090b.dpLicenseYN[0] = ComStr.checkNull(array[n4].getDpLicenseYN(), "");
                    this.licensePutValues(document, um_GOE_ConiC090b.seq1, this.seq1, n4, 1);
                    this.licensePutValues(document, um_GOE_ConiC090b.licenseNo, this.licenseNo, n4, 0);
                    this.licensePutValues(document, um_GOE_ConiC090b.licenseCode, this.licenseCode, n4, 0);
                    this.licensePutValues(document, um_GOE_ConiC090b.licenseBeginDate, this.licenseBeginDate, n4, 0);
                    this.licensePutValues(document, um_GOE_ConiC090b.licenseEndDate, this.licenseEndDate, n4, 0);
                    this.licensePutValues(document, um_GOE_ConiC090b.sigongAccount, this.sigongAccount, n4, 0);
                    this.licensePutValues(document, um_GOE_ConiC090b.peonggaYear, this.peonggaYear, n4, 0);
                    this.licensePutValues(document, um_GOE_ConiC090b.dpLicenseYN, this.dpLicenseYN, n4, 0);
                }
            }
            if (array6 != null) {
                for (int n5 = 0; n5 < array6.length && array6[n5].getHappenDate() != null; ++n5) {
                    if (array6[n5].getHappenDate().trim().length() == 0) {
                        break;
                    }
                    if (array6.length == 1) {
                        um_GOE_ConiC090b.seq5[0] = ComStr.checkNull("" + (n5 + 1), "");
                        um_GOE_ConiC090b.happenDate[0] = ComStr.checkNull(array6[n5].getHappenDate(), "");
                        um_GOE_ConiC090b.startDate[0] = ComStr.checkNull(array6[n5].getStartDate(), "");
                        um_GOE_ConiC090b.endDate[0] = ComStr.checkNull(array6[n5].getEndDate(), "");
                        um_GOE_ConiC090b.note[0] = ComStr.checkNull(array6[n5].getNote(), "");
                        um_GOE_ConiC090b.stateGubun[0] = ComStr.checkNull(array6[n5].getStateGubun(), "");
                        this.PutValues(document, um_GOE_ConiC090b.seq5);
                        this.PutValues(document, um_GOE_ConiC090b.happenDate);
                        this.PutValues(document, um_GOE_ConiC090b.startDate);
                        this.PutValues(document, um_GOE_ConiC090b.endDate);
                        this.PutValues(document, um_GOE_ConiC090b.note);
                        this.PutValues(document, um_GOE_ConiC090b.stateGubun);
                        break;
                    }
                    um_GOE_ConiC090b.seq5[0] = ComStr.checkNull("" + (n5 + 1), "");
                    um_GOE_ConiC090b.happenDate[0] = ComStr.checkNull(array6[n5].getHappenDate(), "");
                    um_GOE_ConiC090b.startDate[0] = ComStr.checkNull(array6[n5].getStartDate(), "");
                    um_GOE_ConiC090b.endDate[0] = ComStr.checkNull(array6[n5].getEndDate(), "");
                    um_GOE_ConiC090b.note[0] = ComStr.checkNull(array6[n5].getNote(), "");
                    um_GOE_ConiC090b.stateGubun[0] = ComStr.checkNull(array6[n5].getStateGubun(), "");
                    this.condPutValues(document, um_GOE_ConiC090b.seq5, this.seq5, n5, 1);
                    this.condPutValues(document, um_GOE_ConiC090b.happenDate, this.happenDate, n5, 0);
                    this.condPutValues(document, um_GOE_ConiC090b.startDate, this.startDate, n5, 0);
                    this.condPutValues(document, um_GOE_ConiC090b.endDate, this.endDate, n5, 0);
                    this.condPutValues(document, um_GOE_ConiC090b.note, this.note, n5, 0);
                    this.condPutValues(document, um_GOE_ConiC090b.stateGubun, this.stateGubun, n5, 0);
                }
            }
            if (array7 != null) {
                final int n6 = 0;
                if (n6 < array7.length && array7[n6].getPunishmentCount() != null) {
                    if (array7[n6].getPunishmentCount().trim().length() != 0) {
                        um_GOE_ConiC090b.punishmentCount[0] = ComStr.checkNull(array7[n6].getPunishmentCount(), "");
                        um_GOE_ConiC090b.stopGubun[0] = ComStr.checkNull(array7[n6].getStopGubun(), "");
                        um_GOE_ConiC090b.stopDay[0] = ComStr.checkNull(array7[n6].getStopDay(), "");
                        um_GOE_ConiC090b.stopSayu[0] = ComStr.checkNull(array7[n6].getStopSayu(), "");
                        um_GOE_ConiC090b.punishmentBasicCode[0] = ComStr.checkNull(array7[n6].getPunishmentBasicCode(), "");
                        um_GOE_ConiC090b.punishmentBasic[0] = ComStr.checkNull(array7[n6].getPunishmentBasic(), "");
                        um_GOE_ConiC090b.dipunishmentBasic[0] = ComStr.checkNull(array7[n6].getDipunishmentBasic(), "");
                        um_GOE_ConiC090b.punishmentStart[0] = ComStr.checkNull(array7[n6].getPunishmentStart(), "");
                        um_GOE_ConiC090b.punishmentEnd[0] = ComStr.checkNull(array7[n6].getPunishmentEnd(), "");
                        um_GOE_ConiC090b.punishmentPeriod[0] = ComStr.checkNull(array7[n6].getPunishmentPeriod(), "");
                        um_GOE_ConiC090b.cancelDay[0] = ComStr.checkNull(array7[n6].getCancelDay(), "");
                        um_GOE_ConiC090b.punishmentSayu[0] = ComStr.checkNull(array7[n6].getPunishmentSayu(), "");
                        um_GOE_ConiC090b.punishmentOrgCode[0] = ComStr.checkNull(array7[n6].getPunishmentOrgCode(), "");
                        um_GOE_ConiC090b.punishmentOrgCodeGubun[0] = ComStr.checkNull(array7[n6].getPunishmentOrgCodeGubun(), "");
                        this.PutValues(document, um_GOE_ConiC090b.punishmentCount);
                        this.PutValues(document, um_GOE_ConiC090b.stopGubun);
                        this.PutValues(document, um_GOE_ConiC090b.stopDay);
                        this.PutValues(document, um_GOE_ConiC090b.stopSayu);
                        this.PutValues(document, um_GOE_ConiC090b.punishmentBasicCode);
                        this.PutValues(document, um_GOE_ConiC090b.punishmentBasic);
                        this.PutValues(document, um_GOE_ConiC090b.dipunishmentBasic);
                        this.PutValues(document, um_GOE_ConiC090b.punishmentStart);
                        this.PutValues(document, um_GOE_ConiC090b.punishmentEnd);
                        this.PutValues(document, um_GOE_ConiC090b.punishmentPeriod);
                        this.PutValues(document, um_GOE_ConiC090b.cancelDay);
                        this.PutValues(document, um_GOE_ConiC090b.punishmentSayu);
                        this.PutValues(document, um_GOE_ConiC090b.punishmentOrgCode);
                        this.PutValues(document, um_GOE_ConiC090b.punishmentOrgCodeGubun);
                    }
                }
            }
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final PrintWriter printWriter = new PrintWriter(byteArrayOutputStream);
            document.print(printWriter);
            final String string = byteArrayOutputStream.toString();
            printWriter.close();
            byteArrayOutputStream.close();
            return string.substring(0, string.indexOf("'UTF-8'")) + "'euc-kr'" + string.substring(string.indexOf("'UTF-8'") + 7, string.length());
        }
        catch (Exception ex) {
            Log.errors("" + this.getClass().getName() + ", parsingXML() Lỗi \n" + ", " + ex);
            throw ex;
        }
    }
    
    private void headPutValues(final XMLDocument xmlDocument, final String[] array, final int n) throws Exception {
        try {
            final XMLElement xmlElement = (XMLElement)xmlDocument.getDocumentElement();
            if (n == 0) {
                xmlDocument.selectNodes(array[1], (NSResolver)xmlElement).item(0).appendChild(xmlDocument.createTextNode(array[0]).cloneNode(true));
            }
            else {
                final Element element = xmlDocument.createElement("Identifier.Content");
                final Node item = xmlDocument.selectNodes(array[1], (NSResolver)xmlElement).item(0);
                item.getParentNode().insertBefore(element, item);
                xmlDocument.selectNodes(array[1], (NSResolver)xmlElement).item(0).appendChild(xmlDocument.createTextNode(array[0]));
            }
        }
        catch (Exception ex) {
            Log.errors("" + this.getClass().getName() + ", 헤더정보의 수신자ID xml 생성 중 에러 \n" + ", " + ex);
            throw ex;
        }
    }
    
    private void PutValues(final XMLDocument xmlDocument, final String[] array) throws Exception {
        try {
            xmlDocument.selectNodes(array[1].trim(), (NSResolver)(XMLElement)xmlDocument.getDocumentElement()).item(0).appendChild(xmlDocument.createTextNode(array[0]).cloneNode(true));
        }
        catch (Exception ex) {
            Log.errors("" + this.getClass().getName() + ", PutValues() Lỗi \n" + ", " + ex);
            throw ex;
        }
    }
    
    private void dpPutValues(final XMLDocument xmlDocument, final String[] array, final String[] array2, final int n, final int n2) throws Exception {
        try {
            final XMLElement xmlElement = (XMLElement)xmlDocument.getDocumentElement();
            int n3;
            if (n == 0) {
                n3 = 0;
            }
            else {
                n3 = n;
            }
            if (n2 == 1) {
                final Element element = xmlDocument.createElement("CeoItem");
                final Node item = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/Supplier.Organization.Details/CeoList/CeoItem", (NSResolver)xmlElement).item(n);
                item.getParentNode().insertBefore(element, item);
            }
            final Node appendChild = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/Supplier.Organization.Details/CeoList/CeoItem", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement(array2[0]));
            final Element element2 = xmlDocument.createElement(array2[1]);
            element2.appendChild(xmlDocument.createTextNode(array[0]));
            appendChild.appendChild(element2);
        }
        catch (Exception ex) {
            Log.errors("" + this.getClass().getName() + ", 사용_대표자 xml 생성 중 에러 \n" + ", " + ex);
            throw ex;
        }
    }
    
    private void facPutValues(final XMLDocument xmlDocument, final String[] array, final String[] array2, final int n, final int n2) throws Exception {
        try {
            final XMLElement xmlElement = (XMLElement)xmlDocument.getDocumentElement();
            int n3;
            if (n == 0) {
                n3 = 0;
            }
            else {
                n3 = n;
            }
            if (n2 == 1) {
                final Element element = xmlDocument.createElement("FacItem");
                final Node item = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/FacList/FacItem", (NSResolver)xmlElement).item(n);
                item.getParentNode().insertBefore(element, item);
            }
            final Element element2 = xmlDocument.createElement(array2[0]);
            final NodeList selectNodes = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/FacList/FacItem", (NSResolver)xmlElement);
            Node node;
            if (array2[0].equals("cc:PostCode.Identifier")) {
                node = selectNodes.item(n3).appendChild(xmlDocument.createElement("Address.Details")).appendChild(xmlDocument.createElement(array2[0]));
            }
            else if (array2[0].equals("cc:Address.Line1.Text")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/FacList/FacItem/Address.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("cc:Address.Line1.Text"));
            }
            else if (array2[0].equals("cc:Address.Line2.Text")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/FacList/FacItem/Address.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("cc:Address.Line2.Text"));
            }
            else if (array2[0].equals("cc:Telephone.Number.Text")) {
                node = selectNodes.item(n3).appendChild(xmlDocument.createElement("Contact.Details")).appendChild(xmlDocument.createElement(array2[0]));
            }
            else if (array2[0].equals("cc:Fax.Number.Text")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/FacList/FacItem/Contact.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("cc:Fax.Number.Text"));
            }
            else {
                node = selectNodes.item(n3).appendChild(element2);
            }
            final Element element3 = xmlDocument.createElement(array2[1]);
            element3.appendChild(xmlDocument.createTextNode(array[0]));
            node.appendChild(element3);
        }
        catch (Exception ex) {
            Log.errors("" + this.getClass().getName() + ", 사용_공장정보 xml 생성 중 에러 \n" + ", " + ex);
            throw ex;
        }
    }
    
    private void idPutValues(final XMLDocument xmlDocument, final String[] array, final String[] array2, final int n, final int n2) throws Exception {
        try {
            final XMLElement xmlElement = (XMLElement)xmlDocument.getDocumentElement();
            int n3;
            if (n == 0) {
                n3 = 0;
            }
            else {
                n3 = n;
            }
            if (n2 == 1) {
                final Element element = xmlDocument.createElement("RepItem");
                final Node item = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RepList/RepItem", (NSResolver)xmlElement).item(n);
                item.getParentNode().insertBefore(element, item);
            }
            final Element element2 = xmlDocument.createElement(array2[0]);
            final NodeList selectNodes = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RepList/RepItem", (NSResolver)xmlElement);
            Node node;
            if (array2[0].equals("cc:Department.Name")) {
                node = selectNodes.item(n3).appendChild(xmlDocument.createElement("Employee.Details")).appendChild(xmlDocument.createElement(array2[0]));
            }
            else if (array2[0].equals("cc:Employee.Name")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RepList/RepItem/Employee.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("cc:Employee.Name"));
            }
            else if (array2[0].equals("Employee.Title.Name")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RepList/RepItem/Employee.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("Employee.Title.Name"));
            }
            else if (array2[0].equals("cc:Telephone.Number.Text")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RepList/RepItem/Employee.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("cc:Telephone.Number.Text"));
            }
            else if (array2[0].equals("cc:Fax.Number.Text")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RepList/RepItem/Employee.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("cc:Fax.Number.Text"));
            }
            else if (array2[0].equals("Email.Address.Text")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RepList/RepItem/Employee.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("Email.Address.Text"));
            }
            else if (array2[0].equals("Person.Identifier")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RepList/RepItem/Employee.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("Person.Identifier"));
            }
            else if (array2[0].equals("PCS.Number.Text")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RepList/RepItem/Employee.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("PCS.Number.Text"));
            }
            else {
                node = selectNodes.item(n3).appendChild(element2);
            }
            final Element element3 = xmlDocument.createElement(array2[1]);
            element3.appendChild(xmlDocument.createTextNode(array[0]));
            node.appendChild(element3);
        }
        catch (Exception ex) {
            Log.errors("" + this.getClass().getName() + ", 사용_입찰대리인 xml 생성 중 에러 \n" + ", " + ex);
            throw ex;
        }
    }
    
    private void maPutValues(final XMLDocument xmlDocument, final String[] array, final String[] array2, final int n, final int n2) throws Exception {
        try {
            final XMLElement xmlElement = (XMLElement)xmlDocument.getDocumentElement();
            int n3;
            if (n == 0) {
                n3 = 0;
            }
            else {
                n3 = n;
            }
            if (n2 == 1) {
                final Element element = xmlDocument.createElement("ManItem");
                final Node item = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/ManList/ManItem", (NSResolver)xmlElement).item(n);
                item.getParentNode().insertBefore(element, item);
            }
            final Node appendChild = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/ManList/ManItem", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement(array2[0]));
            final Element element2 = xmlDocument.createElement(array2[1]);
            element2.appendChild(xmlDocument.createTextNode(array[0]));
            appendChild.appendChild(element2);
        }
        catch (Exception ex) {
            Log.errors("" + this.getClass().getName() + ", 사용_조달품목 xml 생성 중 에러 \n" + ", " + ex);
            throw ex;
        }
    }
    
    private void licensePutValues(final XMLDocument xmlDocument, final String[] array, final String[] array2, final int n, final int n2) throws Exception {
        try {
            final XMLElement xmlElement = (XMLElement)xmlDocument.getDocumentElement();
            int n3;
            if (n == 0) {
                n3 = 0;
            }
            else {
                n3 = n;
            }
            if (n2 == 1) {
                final Element element = xmlDocument.createElement("LicenItem");
                final Node item = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/LicenList/LicenItem", (NSResolver)xmlElement).item(n);
                item.getParentNode().insertBefore(element, item);
            }
            final Node appendChild = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/LicenList/LicenItem", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement(array2[0]));
            final Element element2 = xmlDocument.createElement(array2[1]);
            element2.appendChild(xmlDocument.createTextNode(array[0]));
            appendChild.appendChild(element2);
        }
        catch (Exception ex) {
            Log.errors("" + this.getClass().getName() + ", UM_LICENSE_FACTS xml 생성 중 에러 \n" + ", " + ex);
            throw ex;
        }
    }
    
    private void condPutValues(final XMLDocument xmlDocument, final String[] array, final String[] array2, final int n, final int n2) throws Exception {
        try {
            final XMLElement xmlElement = (XMLElement)xmlDocument.getDocumentElement();
            int n3;
            if (n == 0) {
                n3 = 0;
            }
            else {
                n3 = n;
            }
            if (n2 == 1) {
                final Element element = xmlDocument.createElement("CondItem");
                final Node item = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/CondList/CondItem", (NSResolver)xmlElement).item(n);
                item.getParentNode().insertBefore(element, item);
            }
            final Node appendChild = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/CondList/CondItem", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement(array2[0]));
            final Element element2 = xmlDocument.createElement(array2[1]);
            element2.appendChild(xmlDocument.createTextNode(array[0]));
            appendChild.appendChild(element2);
        }
        catch (Exception ex) {
            Log.errors("" + this.getClass().getName() + ", UM_ENTER_STATE xml 생성 중 에러 \n" + ", " + ex);
            throw ex;
        }
    }
    
    private void restPutValues(final XMLDocument xmlDocument, final String[] array, final String[] array2, final int n, final int n2) throws Exception {
        try {
            final XMLElement xmlElement = (XMLElement)xmlDocument.getDocumentElement();
            int n3;
            if (n == 0) {
                n3 = 0;
            }
            else {
                n3 = n;
            }
            if (n2 == 1) {
                final Element element = xmlDocument.createElement("RestItem");
                final Node item = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RestList/RestItem", (NSResolver)xmlElement).item(n);
                item.getParentNode().insertBefore(element, item);
            }
            final Element element2 = xmlDocument.createElement(array2[0]);
            final NodeList selectNodes = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RestList/RestItem", (NSResolver)xmlElement);
            Node node;
            if (array2[0].equals("Suspend.Indicator")) {
                node = selectNodes.item(n3).appendChild(xmlDocument.createElement("Sanction.Details")).appendChild(xmlDocument.createElement(array2[0]));
            }
            else if (array2[0].equals("Suspend.Date")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RestList/RestItem/Sanction.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("Suspend.Date"));
            }
            else if (array2[0].equals("SuspendReason.Text")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RestList/RestItem/Sanction.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("SuspendReason.Text"));
            }
            else if (array2[0].equals("Sanction.Basis.Text")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RestList/RestItem/Sanction.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("Sanction.Basis.Text"));
            }
            else if (array2[0].equals("Sanction.Basis.Code")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RestList/RestItem/Sanction.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("Sanction.Basis.Code"));
            }
            else if (array2[0].equals("OtherSanction.Basis.Text")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RestList/RestItem/Sanction.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("OtherSanction.Basis.Text"));
            }
            else if (array2[0].equals("Sanction.Date")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RestList/RestItem/Sanction.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("Sanction.Date"));
            }
            else if (array2[0].equals("Expiration.Date")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RestList/RestItem/Sanction.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("Expiration.Date"));
            }
            else if (array2[0].equals("Sanction.Periode.Text")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RestList/RestItem/Sanction.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("Sanction.Periode.Text"));
            }
            else if (array2[0].equals("Contract.Cancel.Date")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RestList/RestItem/Sanction.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("Contract.Cancel.Date"));
            }
            else if (array2[0].equals("Sanction.Reason.Code")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RestList/RestItem/Sanction.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("Sanction.Reason.Code"));
            }
            else if (array2[0].equals("Sanction.Organization.Code")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RestList/RestItem/Sanction.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("PCS.Number.Text"));
            }
            else if (array2[0].equals("Sanction.GovernmentOffice.Code")) {
                node = xmlDocument.selectNodes("/gb:Suicod/DocList/DocItem/RestList/RestItem/Sanction.Details", (NSResolver)xmlElement).item(n3).appendChild(xmlDocument.createElement("PCS.Number.Text"));
            }
            else {
                node = selectNodes.item(n3).appendChild(element2);
            }
            final Element element3 = xmlDocument.createElement(array2[1]);
            element3.appendChild(xmlDocument.createTextNode(array[0]));
            node.appendChild(element3);
        }
        catch (Exception ex) {
            Log.errors("" + this.getClass().getName() + ", UM_VIOLATE_COM xml 생성 중 에러 \n" + ", " + ex);
            throw ex;
        }
    }
}
