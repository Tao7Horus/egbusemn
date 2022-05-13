// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import org.w3c.dom.NodeList;
import common.Log;
import oracle.xml.parser.v2.NSResolver;
import oracle.xml.parser.v2.XMLElement;
import java.io.IOException;
import javax.servlet.ServletException;
import oracle.xml.parser.v2.XMLDocument;
import java.io.InputStream;
import org.xml.sax.SAXException;
import oracle.xml.parser.v2.XMLParseException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import oracle.xml.parser.v2.DOMParser;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class testServlet extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/xml;charset=utf-8");
        final DOMParser parser = new DOMParser();
        try {
            final InputStream is = new FileInputStream(String.valueOf(this.getServletContext().getRealPath("/")) + "/xml_data/UM_RAJ_ConuA020i.xml");
            parser.parse(is);
            final XMLDocument dMaster = parser.getDocument();
            String result = "";
            final UM_ADV_ConrA020c um_adv_conra020c = new UM_ADV_ConrA020c();
            um_adv_conra020c.biz_reg_no[0] = "";
            um_adv_conra020c.nationality[0] = "";
            um_adv_conra020c.biz_nm[0] = "";
            um_adv_conra020c.biz_en_nm[0] = "";
            um_adv_conra020c.biz_en_sort_nm[0] = "";
            um_adv_conra020c.commentcement_dt[0] = "";
            um_adv_conra020c.establish_dt[0] = "";
            um_adv_conra020c.biz_cls[0] = "";
            um_adv_conra020c.product_cls[0] = "";
            um_adv_conra020c.mast_industry_cd_std[0] = "";
            um_adv_conra020c.corp_reg_no[0] = "";
            um_adv_conra020c.biz_cls_1[0] = "";
            um_adv_conra020c.biz_cls_2[0] = "";
            um_adv_conra020c.biz_cls_year[0] = "";
            um_adv_conra020c.capital[0] = "";
            um_adv_conra020c.employee_count_[0] = "";
            um_adv_conra020c.lat_settle_dt[0] = "";
            um_adv_conra020c.zip_cd[0] = "";
            um_adv_conra020c.area_cd[0] = "";
            um_adv_conra020c.addr[0] = "";
            um_adv_conra020c.addr_detail[0] = "";
            um_adv_conra020c.phone_no[0] = "";
            um_adv_conra020c.fax[0] = "";
            um_adv_conra020c.homepage[0] = "";
            um_adv_conra020c.special_good_yn[0] = "";
            PutValues(dMaster, um_adv_conra020c.biz_reg_no);
            PutValues(dMaster, um_adv_conra020c.nationality);
            PutValues(dMaster, um_adv_conra020c.biz_nm);
            PutValues(dMaster, um_adv_conra020c.biz_en_nm);
            PutValues(dMaster, um_adv_conra020c.biz_en_sort_nm);
            PutValues(dMaster, um_adv_conra020c.commentcement_dt);
            PutValues(dMaster, um_adv_conra020c.establish_dt);
            PutValues(dMaster, um_adv_conra020c.biz_cls);
            PutValues(dMaster, um_adv_conra020c.product_cls);
            PutValues(dMaster, um_adv_conra020c.mast_industry_cd_std);
            PutValues(dMaster, um_adv_conra020c.corp_reg_no);
            PutValues(dMaster, um_adv_conra020c.biz_cls_1);
            PutValues(dMaster, um_adv_conra020c.biz_cls_2);
            PutValues(dMaster, um_adv_conra020c.biz_cls_year);
            PutValues(dMaster, um_adv_conra020c.capital);
            PutValues(dMaster, um_adv_conra020c.employee_count_);
            PutValues(dMaster, um_adv_conra020c.lat_settle_dt);
            PutValues(dMaster, um_adv_conra020c.zip_cd);
            PutValues(dMaster, um_adv_conra020c.area_cd);
            PutValues(dMaster, um_adv_conra020c.addr);
            PutValues(dMaster, um_adv_conra020c.addr_detail);
            PutValues(dMaster, um_adv_conra020c.phone_no);
            PutValues(dMaster, um_adv_conra020c.fax);
            PutValues(dMaster, um_adv_conra020c.homepage);
            PutValues(dMaster, um_adv_conra020c.special_good_yn);
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final PrintWriter p = new PrintWriter(baos);
            dMaster.print(p);
            result = baos.toString();
            p.close();
            baos.close();
            res.getWriter().print(result);
        }
        catch (XMLParseException e) {
            e.printStackTrace();
        }
        catch (SAXException e2) {
            e2.printStackTrace();
        }
    }
    
    public static void PutValues(final XMLDocument doc, final String[] node) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            final NodeList tmpNodeList = doc.selectNodes(node[1].trim(), (NSResolver)xemt);
            tmpNodeList.item(0).appendChild(doc.createTextNode(node[0]).cloneNode(true));
        }
        catch (NullPointerException ne) {}
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_RAV_ConuA010i.PutValues() Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
}
