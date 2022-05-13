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
import common.Trx;
import common.Log;
import common.ComStr;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class DB2TXT_rex extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        String fileWrite = null;
        final String transNo = ComStr.checkNull(req.getParameter("transNo"), "");
        if (transNo.equals("")) {
            Log.debug(String.valueOf(this.getClass().getName()) + " transNo is null");
            return;
        }
        Trx trx = null;
        Connection conn = null;
        OneRowEntity masterEntity = null;
        final DB2TXT_Control dtc = new DB2TXT_Control();
        String saupjaNumber = null;
        Hashtable upcheHash = null;
        try {
            trx = new Trx(this, "usemn");
            conn = trx.getConnection();
            masterEntity = dtc.getReceiveMaster(transNo, conn);
            if (!masterEntity.DataExist) {
                throw new Exception("전송번호[" + transNo + "]에 해당하는 데이타를 조회할 수 없습니다.");
            }
            saupjaNumber = masterEntity.getDataFromName("사업자등록번호");
            upcheHash = dtc.getCheckItem(saupjaNumber, transNo, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " transNo[" + transNo + "]:" + exm.toString());
            return;
        }
        finally {
            if (conn != null) {
                try {
                    trx.close();
                }
                catch (Exception ex) {}
            }
        }
        if (conn != null) {
            try {
                trx.close();
            }
            catch (Exception ex2) {}
        }
        res.setContentType("text/xml;charset=utf-8");
        final PrintWriter out = res.getWriter();
        try {
            fileWrite = "<?xml version='1.0' encoding='utf-8'?>";
            fileWrite = String.valueOf(fileWrite) + "<main>";
            fileWrite = String.valueOf(fileWrite) + "<row>";
            fileWrite = String.valueOf(fileWrite) + "<상호명><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("상호명")) + "]]></상호명>" + "<우편번호><![CDATA[" + ComStr.divideComma(ComStr.checkNull(masterEntity.getDataFromName("우편번호")), "-", 3) + "]]></우편번호>" + "<주소><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("주소")) + "]]></주소>" + "<담당자명><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("담당자명")) + "]]></담당자명>" + "<담당자전화번호><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("전화번호")) + "]]></담당자전화번호>" + "<담당자팩스번호><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("FAX번호")) + "]]></담당자팩스번호>" + "<문서번호><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("문서번호")) + "]]></문서번호>" + "<제목><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("제목")) + "]]></제목>" + "<승인지청><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("승인지청")) + "]]></승인지청>" + "<신청일자><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("신청일자")) + "]]></신청일자>" + "<사업자등록번호><![CDATA[" + CommUtil.insert_minus_saupno(masterEntity.getDataFromName("사업자등록번호")) + "]]></사업자등록번호>" + "<대표자명><![CDATA[" + ComStr.checkNull(masterEntity.getDataFromName("대표자명")) + "]]></대표자명>";
            fileWrite = String.valueOf(fileWrite) + "<제출서류><![CDATA[";
            int attach = 0;
            final String lawNo = (String)upcheHash.get("lawNo");
            if (lawNo.equals("Y")) {
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + "). 법인등록번호가 없는 비영리법인이나 종교단체의 경우 는 관련 부처의 허가번호 (예 000부 2008- XX호)," + "      국가기관이나 지방자치단체(조합)의 경우에는 고유번호를 증명할수 있는 서류 제출.\r\n";
            }
            final String adminAgree = (String)upcheHash.get("adminAgree");
            if (adminAgree.equals("Y")) {
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + "). 행정정보공동이용에 사전 동의하지 않을 경우에는 필요서류를 직접 또는 우편 제출 해야 합니다.\r\n";
            }
            final String brinfo = (String)upcheHash.get("brinfo");
            if (brinfo.equals("Y")) {
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + "). 지사등록시 국가종합전자조달시스템등록규정 제 13조에 의거 본사가 작성한 지사등록이행각서 를\r\n" + "  본사임직원이 직접 방문 또는 우편 제출하여야 합니다.  대표이사 또는 입찰대리인으로 이미 등록된 자가\r\n" + "  아닌 직원이 등록이행각서를 제출할 경우에는 재직증명서를 별도로 제출하여야 합니다.\r\n";
            }
            final String makeitem = (String)upcheHash.get("makeitem");
            if (makeitem.equals("Y")) {
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + "). 직접생산증명서로 신청한 경우 \r\n" + "      - 중기청의 직접생산증명서로 신청한 경우에는 조달청이 공공구매종합정보망에서 \r\n" + "         확인 가능하므로 제출 생략.\r\n" + "      - 관련법에 따라 다른 공공기관의 직접생산증명서류로 신청하는 경우에는  해당 서류를 접수청에 제출.\r\n";
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + "). 공장등록증명서로 신청한 경우  \r\n" + "      - 조달청이 행정정보공유센터에서 증서 확인이 가능하므로 제출 생략. 다만, 임대 공장의 경우  \r\n" + "        공장등록증상 임대기간이 명기되지 않을 경우 임대차계약서 사본 제출  \r\n";
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + "). 관련법에 따른 생산(제조) 인.허가증을 선택할 경우 \r\n" + "      - 관련 증서 제출  \r\n";
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + "). 건축물관리대장으로 신청한 경우 \r\n" + "      - 직접생산확인(신청)서 제출  \r\n" + "      - 건축물관리대장은 조달청이 행정정보공유센터에서 증서 확인이 가능하므로 제출 생략  \r\n" + "        다만 임대 건축물일 경우 임대차계약서 사본 제출  \r\n\n";
                fileWrite = String.valueOf(fileWrite) + "※ 제출서류가 사본일 경우 인감으로 '사실과 상위없음' 날인 \r\n\n";
            }
            final String approveNumber = (String)upcheHash.get("approveNumber");
            if (approveNumber.equals("Y")) {
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + "). 형식승인 증명서류사본 \r\n";
            }
            final String totalLicense = (String)upcheHash.get("totalLicense");
            final String bidsseLicense = (String)upcheHash.get("bidsseLicense");
            if (Integer.parseInt(totalLicense) > 0 && !totalLicense.equals(bidsseLicense)) {
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + "). 신청 업종 등록수첩과 당해 업종 인·허가, 면허, 등록, 신고증 등 사본(모두 있는 경우는 모두 제출, 인감으로 '사실과 상위없음' 날인) \r\n";
            }
            final String isNewCompany = ComStr.checkNull(masterEntity.getDataFromName("기업구분2"));
            if (isNewCompany.equals("1")) {
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + "). 벤처기업 증명서류사본(인감으로 '사실과 상위없음' 날인) \r\n";
            }
            if (!bidsseLicense.equals("0")) {
                ++attach;
                fileWrite = String.valueOf(fileWrite) + "※ [~용역]으로 끝나는 업종 또는 기타자유업종은 사업자등록증 [종목]란에 용역명 또는 유사명이 등재된 경우에만 처리되며, 별도의 서류제출 없이 처리됩니다. \r\n";
            }
            final String bidvice = (String)upcheHash.get("bidvice");
            if (bidvice.equals("Y")) {
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + "). 입찰대리인 재직증명서(인감으로 날인) \r\n";
            }
            if (attach == 0) {
                fileWrite = String.valueOf(fileWrite) + "※ 본건은 시행문 및 증빙서류 제출없이 승인처리되므로 조달청에 우편발송이나 방문하실 필요가 없습니다. \r\n   나라장터-조달업체이용자등록-등록신청확인및시행문출력에서 승인여부를 확인하시고, 승인 완료가 되면  인증서등록을 하시면 됩니다.\r\n";
            }
            else {
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + "). 인감증명서 원본(제출서류 확인용)\r\n";
            }
            fileWrite = String.valueOf(fileWrite) + "]]></제출서류>";
            fileWrite = String.valueOf(fileWrite) + "</row>";
            fileWrite = String.valueOf(fileWrite) + "</main>";
            out.println(fileWrite);
        }
        catch (Exception exm2) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".transNo[" + transNo + "]:" + exm2.toString());
        }
    }
}
