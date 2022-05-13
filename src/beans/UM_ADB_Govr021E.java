// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.io.IOException;
import javax.servlet.ServletException;
import common.Log;
import dao.EP_COV_GTQ802;
import dao.EP_COB_GTQ805;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADB_Govr021E extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        final String instituCD = EP_COB_GTQ805.retSpace(req.getParameter("instituCD"));
        final String upcheNm = EP_COB_GTQ805.retSpace(req.getParameter("upcheNm"));
        final String cert_cls = EP_COB_GTQ805.retSpace(req.getParameter("cert_cls"));
        final String enName = EP_COB_GTQ805.retSpace(req.getParameter("tempName"));
        final String type_update = EP_COB_GTQ805.retSpace(req.getParameter("type_update"));
        String urlBack = "";
        String mss = "";
        urlBack = "/RA/UM_ADJ_GovrP021E.jsp?instituCD=" + instituCD + "&upcheNm=" + enName + "&cert_cls=" + cert_cls + "&enName=" + upcheNm + "&type_update=" + type_update;
        final UM_URB_GovuA030c1 gov = new UM_URB_GovuA030c1();
        try {
            if ("S".equals(cert_cls)) {
                if ("update".equals(type_update)) {
                    gov.update_tempName_Um_supplier_enter_mast(enName, upcheNm, instituCD);
                    gov.update_tempName_Um_rec_supplier_enter_mast(enName, upcheNm, instituCD);
                    gov.update_tempName_UM_REC_PUB_INSTITU_CERT_SUPPLIER(enName, instituCD);
                    gov.update_tempName_UM_EDOC_STATE(enName, instituCD);
                    mss = "Việc cập nhật tên mới nhà thầu hoàn tất. ";
                }
                else if ("reset".equals(type_update)) {
                    gov.update_tempName_Um_supplier_enter_mast(enName, upcheNm, instituCD);
                    gov.update_tempName_Um_rec_supplier_enter_mast(enName, upcheNm, instituCD);
                    gov.update_tempName_UM_REC_PUB_INSTITU_CERT_SUPPLIER(enName, instituCD);
                    gov.update_tempName_UM_EDOC_STATE(enName, instituCD);
                    mss = "Việc cập nhật lại tên nhà thầu hoàn tất. ";
                }
                EP_COV_GTQ802.printMessage("Thông báo", "1", mss, urlBack, res);
                return;
            }
            if ("B".equals(cert_cls)) {
                if ("update".equals(type_update)) {
                    gov.update_tempName_um_pub_institu_mast(enName, upcheNm, instituCD);
                    gov.update_tempName_um_rec_pub_institu_mast(enName, upcheNm, instituCD);
                    gov.update_tempName_UM_REC_PUB_INSTITU_CERT_INSTITU(enName, instituCD);
                    mss = "Việc cập nhật tên mới bên mời thầu hoàn tất. ";
                }
                else {
                    gov.update_tempName_um_pub_institu_mast(enName, upcheNm, instituCD);
                    gov.update_tempName_um_rec_pub_institu_mast(enName, upcheNm, instituCD);
                    gov.update_tempName_UM_REC_PUB_INSTITU_CERT_INSTITU(enName, instituCD);
                    mss = "Việc cập nhật lại tên bên mời thầu hoàn tất. ";
                }
                EP_COV_GTQ802.printMessage("Thông báo", "1", mss, urlBack, res);
            }
        }
        catch (Exception e) {
            Log.errors(this, e, "Exception block EP_MDV_GGA874.doPost()");
            final String mssg = "Đã có lỗi xảy ra trong quá trình cập nhật dữ liệu.<br>Bạn hãy kiểm tra lại các trường thông tin nhập vào và thử lại.";
            EP_COV_GTQ802.printMessage("Thông báo", "1", mssg, urlBack, res);
        }
    }
}
