// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.util.Hashtable;
import common.OneRowEntity;
import java.sql.Connection;
import common.util.CommUtil;
import common.Log;
import common.Trx;
import common.CommEntity;
import common.util.HttpUtility;
import common.ComStr;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class DB2DOC_rex extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final long WARNNING_TIME = 10000L;
        final long a = System.currentTimeMillis();
        final String saup_No = ComStr.checkNull(req.getParameter("saup_No"), "");
        String fileWrite = null;
        final String IP = HttpUtility.getIP(req);
        Trx trx = null;
        Connection conn = null;
        OneRowEntity masterEntity = null;
        CommEntity[] ceoEntity = (CommEntity[])null;
        CommEntity[] bidVIceEntity = (CommEntity[])null;
        CommEntity[] jodalItemEntity = (CommEntity[])null;
        CommEntity[] licenseEntity = (CommEntity[])null;
        CommEntity[] factoryEntity = (CommEntity[])null;
        CommEntity[] branchEntity = (CommEntity[])null;
        CommEntity[] changeResumeEntity = (CommEntity[])null;
        CommEntity[] ingamEntity = (CommEntity[])null;
        final DB2DOC_Control dtd = new DB2DOC_Control();
        String saupjaNumber = null;
        Hashtable upcheHash = null;
        String jodalItemYN = null;
        String ingamYN = null;
        while (true) {
            try {
                trx = new Trx(this, "usemn");
                conn = trx.getConnection();
                saupjaNumber = ComStr.replace(saup_No, "-", "");
                masterEntity = dtd.getReceiveMaster(saupjaNumber, conn);
                if (!masterEntity.DataExist) {
                    throw new Exception("사업자에 대한 마스터 정보가 없습니다.");
                }
                if (!ComStr.checkNull(masterEntity.getDataFromName("입찰참가자격여부")).equals("Y")) {
                    res.sendRedirect("/jsp/RA/UM_RAJ_ConuB100s.jsp?flag=false2");
                    return;
                }
                upcheHash = dtd.getCheckItem(saupjaNumber, conn);
                ceoEntity = dtd.getCEO(saupjaNumber, conn);
                bidVIceEntity = dtd.getbidVIce(saupjaNumber, conn);
                jodalItemYN = upcheHash.get("jodalItemYN");
                if (jodalItemYN.equals("Y")) {
                    jodalItemEntity = dtd.getJodalItem(saupjaNumber, conn);
                }
                licenseEntity = dtd.getlicense(saupjaNumber, conn);
                factoryEntity = dtd.getfactoryInfo(saupjaNumber, conn);
                branchEntity = dtd.getbranchInfo(saupjaNumber, conn);
                changeResumeEntity = dtd.getchangeResume(saupjaNumber, conn);
                ingamYN = upcheHash.get("ingamYN");
                if (ingamYN.equals("Y")) {
                    ingamEntity = dtd.getingam(saupjaNumber, conn);
                }
            }
            catch (Exception exm) {
                Log.debug(String.valueOf(this.getClass().getName()) + " saup_No[" + saup_No + "]:" + exm.toString());
                continue;
            }
            finally {
                if (conn != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex) {}
                }
            }
            break;
        }
        if (conn != null) {
            try {
                trx.close();
            }
            catch (Exception ex2) {}
        }
        res.setContentType("text/xml;charset=euc-kr");
        final PrintWriter out = res.getWriter();
        try {
            fileWrite = "<?xml\tversion='1.0' encoding='EUC-KR'?>";
            fileWrite = String.valueOf(fileWrite) + "<main>";
            fileWrite = String.valueOf(fileWrite) + "<row>";
            final String Kiupgubun = ComStr.checkNull(masterEntity.getDataFromName("업무구분"));
            final String gubun_1 = dtd.getGubun1(Kiupgubun);
            final String gubun_2 = dtd.getGubun2(Kiupgubun);
            fileWrite = String.valueOf(fileWrite) + "<물품><![CDATA[" + gubun_1 + "]]></물품>" + "<외자><![CDATA[" + gubun_2 + "]]></외자>" + "<상호명><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("상호명")) + "]]></상호명>" + "<사업자등록번호><![CDATA[" + CommUtil.insert_minus_saupno(masterEntity.getDataFromName("사업자등록번호")) + "]]></사업자등록번호>" + "<개업연월일><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("개업일자")) + "]]></개업연월일>" + "<본사주소><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("주소")) + "]]></본사주소>" + "<본사전화번호><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("전화번호")) + "]]></본사전화번호>" + "<본사팩스번호><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("FAX번호")) + "]]></본사팩스번호>" + "<법인등록번호><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("법인등록번호")) + "]]></법인등록번호>" + "<법인등록일><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("법인등록일")) + "]]></법인등록일>" + "<등록일자><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("등록일자")) + "]]></등록일자>" + "<갱신일자><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("갱신일자")) + "]]></갱신일자>" + "<출력일자><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("출력일자")) + "]]></출력일자>" + "<발행기관명><![CDATA[" + masterEntity.getDataFromName("발행기관명") + "]]></발행기관명>";
            fileWrite = String.valueOf(fileWrite) + "<sub1>";
            for (int i = 0, n = ceoEntity.length; i < n; ++i) {
                fileWrite = String.valueOf(fileWrite) + "<row><대표자명><![CDATA[" + ComStr.checkNull(ceoEntity[i].data[0]) + "]]></대표자명>" + "<대표자주민번호><![CDATA[" + ComStr.checkNull(ceoEntity[i].data[1]) + "]]></대표자주민번호>" + "<대표자메일주소><![CDATA[" + ComStr.checkNull(ceoEntity[i].data[2]) + "]]></대표자메일주소>" + "<대표대표자여부><![CDATA[" + ComStr.checkNull(ceoEntity[i].data[3]) + "]]></대표대표자여부>" + "<대표자휴대폰><![CDATA[" + ComStr.checkNull(ceoEntity[i].data[4]) + "]]></대표자휴대폰>" + "</row>";
            }
            fileWrite = String.valueOf(fileWrite) + "</sub1>";
            fileWrite = String.valueOf(fileWrite) + "<sub2>";
            for (int i = 0, n = bidVIceEntity.length; i < n; ++i) {
                fileWrite = String.valueOf(fileWrite) + "<row><직책명><![CDATA[" + ComStr.checkNull(bidVIceEntity[i].data[0]) + "]]></직책명>" + "<성명><![CDATA[" + ComStr.checkNull(bidVIceEntity[i].data[1]) + "]]></성명>" + "<주민등록번호><![CDATA[" + ComStr.checkNull(bidVIceEntity[i].data[2]) + "]]></주민등록번호>" + "</row>";
            }
            fileWrite = String.valueOf(fileWrite) + "</sub2>";
            final String foreignItem = ComStr.checkNull(masterEntity.getDataFromName("외자구분"));
            fileWrite = String.valueOf(fileWrite) + "<sub3>";
            if (jodalItemYN.equals("Y")) {
                for (int j = 0, n2 = jodalItemEntity.length; j < n2; ++j) {
                    fileWrite = String.valueOf(fileWrite) + "<row><등록일자><![CDATA[" + ComStr.checkNull(jodalItemEntity[j].data[0]) + "]]></등록일자>" + "<물품분류번호><![CDATA[" + ComStr.checkNull(jodalItemEntity[j].data[1]) + "]]></물품분류번호>" + "<분류명><![CDATA[" + ComStr.checkNull(jodalItemEntity[j].data[2]) + "]]></분류명>" + "<제조여부><![CDATA[" + ComStr.checkNull(jodalItemEntity[j].data[3]) + "]]></제조여부>" + "<형식승인번호><![CDATA[" + ComStr.checkNull(jodalItemEntity[j].data[4]) + "]]></형식승인번호>" + "<직접생산증명서류><![CDATA[" + ComStr.checkNull(jodalItemEntity[j].data[5]) + "]]></직접생산증명서류>" + "</row>";
                }
                if (foreignItem.equals("1")) {
                    fileWrite = String.valueOf(fileWrite) + "<row><등록일자><![CDATA[]]></등록일자><물품분류번호><![CDATA[99999999]]></물품분류번호><분류명><![CDATA[외자물품]]></분류명><제조여부><![CDATA[]]></제조여부><형식승인번호><![CDATA[]]></형식승인번호></row>";
                }
            }
            else if (foreignItem.equals("1")) {
                fileWrite = String.valueOf(fileWrite) + "<row><등록일자><![CDATA[]]></등록일자><물품분류번호><![CDATA[99999999]]></물품분류번호><분류명><![CDATA[외자물품]]></분류명><제조여부><![CDATA[]]></제조여부><형식승인번호><![CDATA[]]></형식승인번호></row>";
            }
            fileWrite = String.valueOf(fileWrite) + "</sub3>";
            fileWrite = String.valueOf(fileWrite) + "<sub4>";
            for (int j = 0, n2 = licenseEntity.length; j < n2; ++j) {
                fileWrite = String.valueOf(fileWrite) + "<row><코드명><![CDATA[" + ComStr.checkNull(licenseEntity[j].data[0]) + "]]></코드명>" + "<면허번호><![CDATA[" + ComStr.checkNull(licenseEntity[j].data[1]) + "]]></면허번호>" + "<면허취득일자><![CDATA[" + ComStr.checkNull(licenseEntity[j].data[2]) + "]]></면허취득일자>" + "<면허만료일자><![CDATA[" + ComStr.checkNull(licenseEntity[j].data[3]) + "]]></면허만료일자>" + "<시공능력평가액><![CDATA[" + ComStr.divideComma(licenseEntity[j].data[4], ",", 3) + "]]></시공능력평가액>" + "</row>";
            }
            fileWrite = String.valueOf(fileWrite) + "</sub4>";
            fileWrite = String.valueOf(fileWrite) + "<sub5>";
            for (int j = 0, n2 = factoryEntity.length; j < n2; ++j) {
                fileWrite = String.valueOf(fileWrite) + "<row><공장명><![CDATA[" + ComStr.checkNull(factoryEntity[j].data[0]) + "]]></공장명>" + "<공장주소><![CDATA[" + ComStr.checkNull(factoryEntity[j].data[1]) + "]]></공장주소>" + "<공장전화번호><![CDATA[" + ComStr.checkNull(factoryEntity[j].data[2]) + "]]></공장전화번호>" + "<공장임대여부><![CDATA[" + ComStr.checkNull(factoryEntity[j].data[3]) + "]]></공장임대여부>" + "<공장임대기간><![CDATA[" + ComStr.checkNull(factoryEntity[j].data[4]) + "]]></공장임대기간>" + "</row>";
            }
            fileWrite = String.valueOf(fileWrite) + "</sub5>";
            fileWrite = String.valueOf(fileWrite) + "<sub6>";
            String changeGubun = "";
            for (int k = 0, n3 = changeResumeEntity.length; k < n3; ++k) {
                changeGubun = ComStr.checkNull(changeResumeEntity[k].data[4]);
                if (changeGubun.equals("I")) {
                    fileWrite = String.valueOf(fileWrite) + "<row><갱신일자><![CDATA[" + ComStr.checkNull(changeResumeEntity[k].data[1]) + "]]></갱신일자>" + "<속성명><![CDATA[" + ComStr.checkNull(changeResumeEntity[k].data[2]) + "]]></속성명>" + "<변경후내용><![CDATA[" + "\t추가 : " + ComStr.checkNull(changeResumeEntity[k].data[3]) + "]]></변경후내용>" + "</row>";
                }
                else if (changeGubun.equals("U")) {
                    fileWrite = String.valueOf(fileWrite) + "<row><갱신일자><![CDATA[" + ComStr.checkNull(changeResumeEntity[k].data[1]) + "]]></갱신일자>" + "<속성명><![CDATA[" + ComStr.checkNull(changeResumeEntity[k].data[2]) + "]]></속성명>" + "<변경후내용><![CDATA[" + "\t추가 : " + ComStr.checkNull(changeResumeEntity[k].data[3]) + "]]></변경후내용>" + "</row>";
                }
            }
            fileWrite = String.valueOf(fileWrite) + "</sub6>";
            fileWrite = String.valueOf(fileWrite) + "<sub7>";
            if (ingamYN.equals("Y")) {
                int count = 0;
                for (int m = 0, l = ingamEntity.length; m < l; ++m) {
                    fileWrite = String.valueOf(fileWrite) + "<row><이미지명><![CDATA[" + ComStr.checkNull(ingamEntity[m].data[0]) + "]]></이미지명>" + "</row>";
                    ++count;
                }
                if (count < 12) {
                    for (count = count; count < 12; ++count) {
                        fileWrite = String.valueOf(fileWrite) + "^";
                    }
                }
            }
            fileWrite = String.valueOf(fileWrite) + "</sub7>";
            fileWrite = String.valueOf(fileWrite) + "<sub8>";
            for (int k = 0, n3 = branchEntity.length; k < n3; ++k) {
                fileWrite = String.valueOf(fileWrite) + "<row><삭제여부><![CDATA[" + ComStr.checkNull(branchEntity[k].data[3]) + "]]></삭제여부>" + "<사업자등록번호><![CDATA[" + ComStr.checkNull(branchEntity[k].data[0]) + "]]></사업자등록번호>" + "<상호명><![CDATA[" + ComStr.checkNull(branchEntity[k].data[1]) + "]]></상호명>" + "<지사주소><![CDATA[" + ComStr.checkNull(branchEntity[k].data[2]) + "]]></지사주소>" + "<지사대표자><![CDATA[" + ComStr.checkNull(branchEntity[k].data[4]) + "]]></지사대표자>" + "<주민등록번호><![CDATA[" + ComStr.checkNull(branchEntity[k].data[5]) + "]]></주민등록번호>" + "</row>";
            }
            fileWrite = String.valueOf(fileWrite) + "</sub8>";
            fileWrite = String.valueOf(fileWrite) + "</row></main>";
            out.println(fileWrite);
            final long b = System.currentTimeMillis();
            if (b - a > WARNNING_TIME) {
                Log.debug(String.valueOf(this.getClass().getName()) + ".saup_No[" + saup_No + "]출력 시각 10초 이상!!  [" + (b - a) + "] millis ");
            }
        }
        catch (Exception exm2) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".saup_No[" + saup_No + "]:" + exm2.toString());
        }
    }
}
