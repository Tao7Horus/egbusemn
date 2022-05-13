// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import org.w3c.dom.NodeList;
import oracle.xml.parser.v2.NSResolver;
import oracle.xml.parser.v2.XMLElement;
import oracle.xml.parser.v2.XMLDocument;
import java.io.StringReader;
import common.Log;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.ByteArrayOutputStream;
import java.io.Reader;
import oracle.xml.parser.v2.DOMParser;

public class UM_GOV_ConiE010l
{
    String xmlSourceMaster;
    String dxeHost;
    int dxePort;
    String dxeStore;
    String XlnSourceMaster;
    String dxeUser;
    String dxePassword;
    DOMParser parser;
    
    public UM_GOV_ConiE010l() {
        this.xmlSourceMaster = "/devl/usemn/usemnapp/xml/xmlTemplate/xml2/GsacidC.xml";
        this.dxeHost = "XLNDB";
        this.dxePort = 1050;
        this.dxeStore = "usemn";
        this.XlnSourceMaster = String.valueOf(this.dxeStore) + ":/xml2/GsacidC.xml";
        this.dxeUser = "";
        this.dxePassword = "";
        this.parser = new DOMParser();
    }
    
    public String loadDB2XML() {
        String result = "";
        final UM_ADV_ConrA020c um_adv_conra020c = new UM_ADV_ConrA020c();
        try {
            System.setProperty("com.exln.dxe.adminhost", this.dxeHost);
            final StringReader _Message = null;
            this.parser.parse((Reader)_Message);
            final XMLDocument dMaster = this.parser.getDocument();
            _Message.close();
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
            um_adv_conra020c.cert[0] = "";
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
            PutValues(dMaster, um_adv_conra020c.cert);
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final PrintWriter p = new PrintWriter(baos);
            dMaster.print(p);
            result = baos.toString();
            p.close();
            baos.close();
            result = String.valueOf(result.substring(0, result.indexOf("'UTF-8'"))) + "'euc-kr'" + result.substring(result.indexOf("'UTF-8'") + 7, result.length());
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_RAV_ConuA010i.loadDB2XML() Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
            result = "로딩에 이상이 생겼습니다. 잠시후 다시 시도해주시기 바랍니다.";
        }
        return result;
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
