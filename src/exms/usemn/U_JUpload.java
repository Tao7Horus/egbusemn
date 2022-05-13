// 
// Decompiled by Procyon v0.5.30
// 

package exms.usemn;

import org.w3c.dom.Element;
import oracle.xml.parser.v2.NSResolver;
import oracle.xml.parser.v2.XMLElement;
import java.io.FileNotFoundException;
import exms.xml.Xml2Rep;
import java.util.StringTokenizer;
import java.sql.ResultSet;
import org.w3c.dom.NodeList;
import oracle.xml.parser.v2.XMLDocument;
import java.io.Reader;
import java.io.StringReader;
import oracle.xml.parser.v2.DOMParser;
import java.util.ArrayList;
import signgate.xml.util.XmlSignature;
import signgate.xml.util.XmlEncryption;
import signgate.xml.util.XmlSecu;
import secu.lib.CertInfo;
import secu.lib.Secu;
import java.io.InputStream;
import java.io.FileInputStream;
import exms.mime.DAttachInfo;
import exms.mime.MimeParse;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.io.PrintWriter;
import javax.servlet.ServletInputStream;
import java.sql.SQLException;
import exms.upload.JUploadException;
import exms.util.Base64;
import common.Trx;
import java.io.File;
import java.util.Date;
import exms.upload.U_JUploadDocInfo;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import exms.util.InitConfig;
import exms.util.U_JUploadAppLog;
import javax.servlet.http.HttpServlet;

public class U_JUpload extends HttpServlet
{
    public static final String[] erMsg;
    public String[] oidStatus;
    public static final String[] erTyp;
    public static final String[] erCod;
    public static final String[] trailer;
    public static final int INITIAL = 0;
    public static final int MIMECHK = 1;
    public static final int SOAPPARSE = 2;
    public static final int AUTHCHK = 3;
    public static final int TODB_LOG = 4;
    public static final int TODB_FILE = 5;
    public static final int SIGNCHK = 6;
    public static final int XMLPARSE = 7;
    public static final int USERCHK = 8;
    public static final int TODB_USE0 = 9;
    public static final int TODB_USE1 = 10;
    public static final int TODB_USE2 = 11;
    public static final int TODB_USE3 = 12;
    public static final int TODB_USE4 = 13;
    public static final int TODB_USE5 = 14;
    public static final int TODB_USE6 = 15;
    public static final int TODB_USE7 = 16;
    public static final int TODB_USE8 = 17;
    public static final int TODB_USE9 = 18;
    public static final int TODB_USE10 = 19;
    public static final int TOREP = 20;
    public static final int TODB_USE11 = 21;
    public static final int TODB_USE12 = 22;
    public static final int OID_CHECK = 23;
    public static final int GONG_CHECK = 24;
    public static int UniSequence;
    public static U_JUploadAppLog LOG;
    public static InitConfig CONFIG;
    private static final String configDir = "/data1/AppData/apps/usemn/SU/U_JUpload.conf";
    public static String inBox;
    public static String atBox;
    public static String rootBox;
    public static String SVID;
    
    static {
        erMsg = new String[] { " Đây là lỗi hệ thống(lỗi khởi tạo). Vui lòng hỏi trung tâm hỗ trợ", "Lỗi trong quá trình xử lý văn bản đã chuyển..\n Hãy thử lại nếu vẫn bị lỗi vui lòng hỏi Trung tâm hỗ trợ", "Lỗi trong quá trình xử lý văn bản đã chuyển..\n Hãy thử lại nếu vẫn bị lỗi vui lòng hỏi Trung tâm hỗ trợ", "Là doanh nghiệp đã được đăng ký.", "Lỗi khi đang lưu văn bản vào DB. Vui lòng hỏi Trung tâm hỗ trợ", "Lỗi khi đang lưu file đính kèm vào DB. Vui lòng hỏi Trung tâm hỗ trợ", "Lỗi chữ ký số. Hãy thử lại nếu vẫn bị lỗi vui lòng hỏi Trung tâm hỗ trợ", "Đây là lỗi hệ thống(Văn bản điện tử). Vui lòng hỏi Trung tâm hỗ trợ", "Là người dùng chưa đăng ký.", "Là doanh nghiệp đã đăng ký. Có thể sử dụng Màn hình đăng ký chứng nhận số hệ thống n G2B trong mục đăng ký người dùng mới.", "Đã phê duyệt. Hãy soạn đơn đăng ký thay đổi.", "Đang thực hiện phê duyệt. \n Có thể xem tình trạng tiến hành trong mục  In văn bản tiến hành và xác nhận đăng ký .", "Là người dùng chưa đăng ký hoặc đang được xử lý.Hãy soạn đơn đăng ký ", "Thông tin hạng mục chung không đúng.", "Thông tin người đại diện(lãnh đạo) không đúng", "Thông tin nhà máy không đúng.", "Thông tin hàng hóa(cung cấp) không đúng.", "Thông tin hàng hóa(sản xuất) không đúng.", "Thông tin Xây lắp/Tư vấn không đúng.", "Thông tin người đại diện dự thầu không đúng.", "Lỗi hệ thống(lỗi vị trí lưu). Vui lòng hỏi Trung tâm hỗ trợ", "Thất bại xác nhận nhân thân.", "Thất bại kiểm tra tính hợp lệ của chứng nhận số.", "Là chứng nhận số không khớp với chính sách chứng nhận số. Vui lòng hỏi Trung tâm hỗ trợ.", "Không thể đăng ký / chỉnh sửa tư cách tham gia đấu thầu." };
        erTyp = new String[] { "E", "D", "E", "B", "B", "B", "B", "B", "B", "B", "B", "B", "B", "B", "B" };
        erCod = new String[] { "005", "012", "001", "003", "003", "003", "003", "003", "003", "003", "003", "003", "003", "003", "003" };
        trailer = new String[] { "", ".dec" };
        U_JUpload.UniSequence = 0;
        U_JUpload.LOG = null;
        U_JUpload.CONFIG = null;
        U_JUpload.inBox = "";
        U_JUpload.atBox = "";
        U_JUpload.rootBox = "";
        U_JUpload.SVID = "A";
    }
    
    public U_JUpload() {
        this.oidStatus = new String[] { "1.2.410.200005.1.1.5", "1.2.410.200004.5.2.1.1", "1.2.410.200004.5.3.1.1", "1.2.410.200004.5.3.1.2", "1.2.410.200004.5.3.1.5", "1.2.410.200004.5.1.1.7", "1.2.410.200004.5.1.1.12", "1.2.410.200004.5.4.1.2", "1.2.410.200012.1.1.3", "1.2.410.200012.1.1.5", "1.2.410.200004.5.2.1.3" };
    }
    
    public void init(final ServletConfig config) throws ServletException {
        System.out.println("---------------Start-----------");
        super.init(config);
        try {
            U_JUpload.CONFIG = new InitConfig("/data1/AppData/apps/usemn/SU/U_JUpload.conf");
            U_JUpload.rootBox = U_JUpload.CONFIG.getValue("DOC_ROOT");
            U_JUpload.inBox = U_JUpload.CONFIG.getValue("IN");
            U_JUpload.atBox = U_JUpload.CONFIG.getValue("ATTACH");
            U_JUpload.SVID = U_JUpload.CONFIG.getValue("SVID");
            U_JUpload.LOG = new U_JUploadAppLog(U_JUpload.CONFIG.getValue("APP_HOME_DIR"), "spool", U_JUpload.CONFIG.getValue("LOG_FILE_NAME").trim());
            System.out.println("rootBox" + U_JUpload.rootBox);
            System.out.println("inBox" + U_JUpload.inBox);
            System.out.println("atBox" + U_JUpload.atBox);
            System.out.println("---------------Ok -----------");
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
            throw new ServletException((Throwable)e);
        }
    }
    
    public void destroy() {
        U_JUpload.LOG.close();
        super.destroy();
    }
    
    public void doGet(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        this.service(req, res);
    }
    
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        this.service(req, res);
    }
    
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        ServletInputStream in = null;
        PrintWriter out = null;
        String _SEQ = "0000" + getUniSequence();
        String _Auth = null;
        String _AuthMethod = null;
        String _AuthInfoE = null;
        String _AuthInfoD = null;
        final int i = 0;
        File inBoxDir = null;
        final U_JUploadDocInfo docInfo = new U_JUploadDocInfo();
        try {
            res.setContentType("text/xml; charset=UTF-8");
            in = req.getInputStream();
            out = res.getWriter();
            docInfo.now = new Date();
            _SEQ = _SEQ.substring(_SEQ.length() - 4, _SEQ.length());
            docInfo.texmslog.unikey = String.valueOf(docInfo.df2.format(docInfo.now)) + _SEQ + U_JUpload.SVID;
            System.out.println("---------------docInfo.texmslog.unikey -----------" + docInfo.texmslog.unikey);
            inBoxDir = new File(String.valueOf(U_JUpload.rootBox) + docInfo.df.format(docInfo.now) + U_JUpload.inBox);
            System.out.println("---------------inBoxDir -----------" + inBoxDir);
            if (!inBoxDir.exists()) {
                if (inBoxDir.mkdirs()) {
                    System.out.println("---------------tao thu muc -----------");
                }
                else {
                    System.out.println("---------------ko tao thu muc -----------");
                }
            }
            new File(String.valueOf(U_JUpload.rootBox) + docInfo.df.format(docInfo.now) + U_JUpload.inBox + docInfo.texmslog.unikey).createNewFile();
            System.out.println("---------------tao  file -----------" + docInfo.texmslog.unikey);
            docInfo.trx = new Trx(this, "usemn");
            _Auth = req.getHeader("Authorization");
            System.out.println("Authorization============== " + _Auth + "<br/>");
            if (req.getHeader("Authorization") != null) {
                _AuthMethod = _Auth.substring(0, _Auth.indexOf(" "));
                System.out.println("_AuthMethod============== " + _Auth + "<br/>");
                _AuthInfoE = _Auth.substring(_Auth.indexOf(" ") + 1, _Auth.length());
                System.out.println("_AuthInfoE============== " + _AuthInfoE + "<br/>");
                _AuthInfoD = ("basic".equals(_AuthMethod.toLowerCase()) ? new String(Base64.decode(_AuthInfoE.toCharArray())) : "");
                System.out.println("AuthInfoD============== " + _AuthInfoD + "<br/>");
                if (_AuthInfoD.indexOf(":") != -1) {
                    docInfo.texmslog.from_id = _AuthInfoD.substring(0, _AuthInfoD.indexOf(":"));
                }
            }
            docInfo.contentType = ((req.getHeader("Content-Type") == null) ? "" : req.getHeader("Content-Type"));
            docInfo.texmslog.import_date = docInfo.now;
            System.out.println("docInfo.contentType ============= " + docInfo.contentType + "<br/>");
            if ((docInfo.conn = docInfo.trx.getConnection()) == null) {
                throw new JUploadException("exms Pool Error", 0);
            }
            docInfo.conn.setAutoCommit(false);
            this.getDocumentContent(in, docInfo);
            System.out.println("------------getDocumentContent(in, docInfo) <br/>");
            if (in != null) {
                in.close();
            }
            this.checkMime(docInfo.texmslog.unikey, docInfo);
            System.out.println("------------checkMime(docInfo) <br/>");
            this.checkAuth(docInfo);
            System.out.println("------------checkAuth(docInfo) <br/>");
            this.toLOGDB(docInfo);
            System.out.println("------------toLOGDB(docInfo) <br/>");
            this.checkUser(docInfo);
            System.out.println("------------checkUser(docInfo) <br/>");
            this.checkSign(docInfo);
            System.out.println("------------checkSign(docInfo) <br/>");
            this.toDB(docInfo);
            System.out.println("------------toDB(docInfo) <br/>");
            this.toCopy(docInfo);
            System.out.println("------------toCopy(docInfo) <br/>");
            docInfo.conn.commit();
            docInfo.conn.setAutoCommit(true);
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            out.println("<response>");
            out.println("  <nextpass>NONE</nextpass>");
            out.println("  <result>0</result>");
            out.println("</response>");
            System.out.println(String.valueOf(docInfo.texmslog.unikey) + "Da nhan duoc");
        }
        catch (ArrayIndexOutOfBoundsException ex3) {
            try {
                docInfo.conn.rollback();
            }
            catch (Exception ex6) {}
        }
        catch (SQLException ex4) {
            try {
                docInfo.conn.rollback();
            }
            catch (Exception ex7) {}
        }
        catch (ClassNotFoundException ex5) {
            out.println("Loi connection");
            try {
                docInfo.conn.rollback();
            }
            catch (Exception ex8) {}
        }
        catch (JUploadException ex) {
            try {
                out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                out.println("<response>");
                out.println("  <nextpass>NONE</nextpass>");
                out.println("  <result>" + new String(U_JUpload.erMsg[ex.getSeq()].getBytes(), "UTF-8") + "</result>");
                out.println("</response>");
                System.out.println(String.valueOf(ex.getSeq()) + ": " + ex.getMessage());
                switch (ex.getSeq()) {
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22: {
                        docInfo.conn.rollback();
                        PreparedStatement pstmt = null;
                        String qry = "";
                        try {
                            System.out.println("============== Exception =================");
                            qry = "update syn_bid_edoc_log set ERR_CD='E', ERROR_DT=sysdate where unikey='" + docInfo.texmslog.unikey + "'";
                            pstmt = docInfo.conn.prepareStatement(qry);
                            pstmt.executeUpdate();
                            try {
                                pstmt.close();
                            }
                            catch (Exception ex9) {}
                            qry = "insert into T_EXMS_ERR (KEY, OCCUR_DATE , ERR_TYPE , ERR_CODE,  ERR_BCODE,  APPEND_MESG) values ('" + docInfo.texmslog.unikey + "'," + "sysdate," + "'" + U_JUpload.erTyp[ex.getSeq() - 6] + "'," + "'" + U_JUpload.erCod[ex.getSeq() - 6] + "'," + "'00000'," + "?)";
                            pstmt = docInfo.conn.prepareStatement(qry);
                            pstmt.setString(1, ex.getMessage());
                        }
                        catch (Exception exec) {
                            System.out.println(String.valueOf(qry) + ":" + ex.getMessage());
                            System.out.println(exec.toString());
                        }
                        finally {
                            try {
                                pstmt.close();
                            }
                            catch (Exception ex10) {}
                            try {
                                docInfo.conn.commit();
                            }
                            catch (Exception ex11) {}
                        }
                        try {
                            pstmt.close();
                        }
                        catch (Exception ex12) {}
                        try {
                            docInfo.conn.commit();
                        }
                        catch (Exception ex13) {}
                        break;
                    }
                }
            }
            catch (Exception ex14) {}
            this.log(docInfo.texmslog.unikey, "Lỗi nhận");
        }
        catch (Exception ex2) {
            System.out.println("Lỗi không xác định" + ex2.toString());
            this.log(docInfo.texmslog.unikey, "Lỗi nhận(" + ex2.toString() + ")");
        }
        finally {
            try {
                U_JUpload.LOG.save(docInfo.texmslog.unikey);
            }
            catch (Exception ex15) {}
            try {
                docInfo.trx.close();
            }
            catch (Exception ex16) {}
            try {
                out.close();
            }
            catch (Exception ex17) {}
            try {
                in.close();
            }
            catch (Exception ex18) {}
        }
        try {
            U_JUpload.LOG.save(docInfo.texmslog.unikey);
        }
        catch (Exception ex19) {}
        try {
            docInfo.trx.close();
        }
        catch (Exception ex20) {}
        try {
            out.close();
        }
        catch (Exception ex21) {}
        try {
            in.close();
        }
        catch (Exception ex22) {}
    }
    
    private synchronized void getDocumentContent(final ServletInputStream in, final U_JUploadDocInfo docInfo) throws IOException {
        this.log(docInfo.texmslog.unikey, "=========>> bắt đầu lưu file <<==========");
        FileOutputStream out = null;
        int ret = 0;
        try {
            out = new FileOutputStream(String.valueOf(U_JUpload.rootBox) + docInfo.df.format(docInfo.now) + U_JUpload.inBox + docInfo.texmslog.unikey);
            out.write(("Content-Type: " + docInfo.contentType + "\r\n\r\n").getBytes());
            final byte[] buf = new byte[8192];
            String tmpString = null;
            while ((ret = in.read(buf)) != -1) {
                tmpString = new String(buf, 0, ret);
                Thread.sleep(3L);
                out.write(buf, 0, ret);
            }
            out.flush();
        }
        catch (Exception e) {
            throw new IOException(e.getMessage());
        }
        finally {
            try {
                out.close();
            }
            catch (Exception ex) {}
        }
        try {
            out.close();
        }
        catch (Exception ex2) {}
        this.log(docInfo.texmslog.unikey, "=========>> hoàn thành lưu file <<==========");
    }
    
    private void checkMime(final String unikey, final U_JUploadDocInfo docInfo) throws JUploadException {
        System.out.println(String.valueOf(docInfo.texmslog.unikey) + "=========[[ Bat dau tap tin ]]==========" + "<br/>");
        System.out.println("mime file folder " + U_JUpload.rootBox + docInfo.df.format(docInfo.now) + U_JUpload.inBox + unikey);
        System.out.println("unikey  : " + unikey);
        System.out.println(String.valueOf(U_JUpload.rootBox) + docInfo.df.format(docInfo.now) + U_JUpload.atBox);
        System.out.println("##########################################");
        final MimeParse MP = new MimeParse(String.valueOf(U_JUpload.rootBox) + docInfo.df.format(docInfo.now) + U_JUpload.inBox + unikey, unikey, String.valueOf(U_JUpload.rootBox) + docInfo.df.format(docInfo.now) + U_JUpload.atBox);
        docInfo.texmslog.analyz_date = new Date();
        docInfo.texmslog.transm_date = docInfo.texmslog.analyz_date;
        docInfo.texmslog.inform_date = docInfo.texmslog.analyz_date;
        docInfo.texmslog.export_date = docInfo.texmslog.analyz_date;
        try {
            docInfo.mimes = MP.parseFile();
        }
        catch (Exception e) {
            final JUploadException aException = new JUploadException(e.getMessage(), 1);
            if (e.getClass() != aException.getClass()) {
                throw aException;
            }
            aException.setSeq(((JUploadException)e).getSeq());
            throw aException;
        }
        finally {
            System.out.println(String.valueOf(docInfo.texmslog.unikey) + "[" + 1 + "]" + MP.getLog() + "<br/>");
        }
        System.out.println(String.valueOf(docInfo.texmslog.unikey) + "[" + 1 + "]" + MP.getLog() + "<br/>");
        System.out.println(String.valueOf(docInfo.texmslog.unikey) + "=========>> Tach tap tin hoan tat <<==========" + "<br/>");
    }
    
    private void checkAuth(final U_JUploadDocInfo docInfo) throws JUploadException {
        this.log(docInfo.texmslog.unikey, "=========>> SOAP Parse Start<<==========<br/>");
        FileInputStream _SOAPMessage = null;
        try {
            docInfo._SOAP = new String(((DAttachInfo)docInfo.mimes.get(0)).Contents.getBytes("8859_1"), "KSC5601");
            _SOAPMessage = new FileInputStream(((DAttachInfo)docInfo.mimes.get(0)).SavedPath);
            docInfo._SOAPParser.parse((InputStream)_SOAPMessage);
            docInfo._SOAPDoc = docInfo._SOAPParser.getDocument();
            this.getLogData(docInfo);
        }
        catch (Exception e) {
            throw new JUploadException("Soap Parse Error", 2);
        }
        finally {
            try {
                _SOAPMessage.close();
            }
            catch (Exception ex) {}
        }
        try {
            _SOAPMessage.close();
        }
        catch (Exception ex2) {}
        System.out.println(String.valueOf(docInfo.texmslog.unikey) + "=========>> SOAP Parse Complete <<==========");
        try {
            docInfo.texmslog.from_id = this.GetValue(docInfo._SOAPDoc, "/SOAP:Envelope/SOAP:Header/eb:MessageHeader/eb:From/eb:PartyId");
        }
        catch (Exception e) {
            throw new JUploadException("Authorization And the caller ID is different", 3);
        }
        this.log(docInfo.texmslog.unikey, "=========>> Sender ID : " + docInfo.texmslog.from_id);
    }
    
    private void checkSign(final U_JUploadDocInfo docInfo) throws JUploadException {
        int status = 0;
        if (docInfo._SOAP.indexOf("<ds:Signature") == -1) {
            System.out.println("=========>> No Electronic Signature <<==========<br/>");
            docInfo.isSigned = 0;
        }
        else {
            System.out.println("=========>> In an e-signature <<==========<br/>");
            docInfo.isSigned = 1;
            try {
                this.log(docInfo.texmslog.unikey, "=========>> Certificate validation and verification policy started <<==========");
                System.out.println("=========>> start getX509Cert <<==========<br/>");
                final String x509Cert = this.getX509Cert(docInfo, docInfo._SOAP);
                System.out.println("=========>> getX509Cert <<==========<br/>");
                final String xCert = "-----BEGIN CERTIFICATE-----\n" + x509Cert + "\n" + "-----END CERTIFICATE-----";
                final Secu secu = new Secu(11);
                final CertInfo ci = new CertInfo(secu, xCert);
                if (!ci.isValid()) {
                    throw new Exception("Validation certificate validation and policy failure");
                }
                this.log(docInfo.texmslog.unikey, "=========>> Complete the certificate validation and verification policy <<==========");
                this.log(docInfo.texmslog.unikey, "=========>> Start OID verification certificate <<==========");
                final boolean oid_check = false;
                final String oid = ci.getPolicyOid();
                System.out.println("=========>> oid=" + oid + "<<==========<br/>");
                System.out.println("=========>> Complete verification certificate OID <<==========");
                final String dnInfo = ci.getCertDN();
                System.out.println("=========>> Start the certificate DN <<==========");
                System.out.println("=========>> Certificate DN : " + dnInfo);
                final boolean dnCert = this.getDN(dnInfo);
                if (dnCert) {
                    status = 2;
                    throw new Exception("Demand for institutions participating in the bidding registration can not be used for a change.");
                }
                System.out.println("=========>> Complete the certificate DN<<==========");
                final XmlSecu xmlSecu = new XmlSecu(1, "/app/secu/config/secu.cfg");
                xmlSecu.setFileEncoding("UTF-8");
                xmlSecu.setSignEncoding("UTF-8");
                xmlSecu.setEncEncoding("UTF-8");
                xmlSecu.setEncTypeEncoding("UTF-8");
                final XmlEncryption XmlEnc = new XmlEncryption(xmlSecu);
                final XmlSignature sign = new XmlSignature(xmlSecu);
                final int len = docInfo.mimes.size();
                final String[] AttackInDoc = new String[len];
                final ArrayList list = new ArrayList();
                System.out.println("--- length: " + len);
                System.out.println("--- list : " + docInfo.mimes.size());
                for (int i = 0; i < len; ++i) {
                    System.out.println("--- counting... " + i);
                    AttackInDoc[i] = ((DAttachInfo)docInfo.mimes.get(i)).SavedPath;
                    if (i > 0) {
                        list.add(AttackInDoc[i]);
                    }
                }
                System.out.println("--- loop is done");
                if (!sign.vrfG2BSoapSignFile(AttackInDoc[0], list)) {
                    System.out.println("--- code dies here");
                    throw new Exception("e-signature failure");
                }
                System.out.println("--- 1:  " + AttackInDoc[1]);
                System.out.println("--- 2:  " + AttackInDoc[1] + U_JUpload.trailer[docInfo.isSigned]);
                System.out.println("----3:XmlSecu.USR======1");
                System.out.println("----4:XmlSecu.KM======1");
                if (!XmlEnc.decG2BFile(AttackInDoc[1], String.valueOf(AttackInDoc[1]) + U_JUpload.trailer[docInfo.isSigned], 1, 1)) {
                    throw new Exception("decryption failed: Exception occurred");
                }
                System.out.println("=========>> Complete decryption <<==========");
            }
            catch (Exception ex) {
                if (status == 1) {
                    throw new JUploadException(ex.getMessage(), 23);
                }
                if (status == 2) {
                    throw new JUploadException(ex.getMessage(), 24);
                }
                throw new JUploadException(ex.getMessage(), 6);
            }
        }
        System.out.println("=========>> XML Parse Start <<==========");
        FileInputStream _XMLMessage = null;
        try {
            _XMLMessage = new FileInputStream(String.valueOf(((DAttachInfo)docInfo.mimes.get(1)).SavedPath) + U_JUpload.trailer[docInfo.isSigned]);
            docInfo._XMLParser.parse((InputStream)_XMLMessage);
            docInfo._XMLDoc = docInfo._XMLParser.getDocument();
        }
        catch (Exception e) {
            throw new JUploadException("Xml Parse Error", 7);
        }
        finally {
            try {
                _XMLMessage.close();
            }
            catch (Exception ex2) {}
        }
        try {
            _XMLMessage.close();
        }
        catch (Exception ex3) {}
        System.out.println("=========>> XML Parse Complete <<==========");
    }
    
    private String getX509Cert(final U_JUploadDocInfo docInfo, final String soapData) {
        final DOMParser parser = new DOMParser();
        String rtValue = "";
        try {
            final StringReader _Message = new StringReader(soapData);
            parser.parse((Reader)_Message);
            final XMLDocument dMaster = parser.getDocument();
            _Message.close();
            final NodeList nlAttach = dMaster.getElementsByTagName("X509Certificate");
            rtValue = nlAttach.item(0).getLastChild().getNodeValue();
        }
        catch (Exception e) {
            this.log(docInfo.texmslog.unikey, "Error parsing the Soap seomyeonggap.");
        }
        return rtValue;
    }
    
    private void checkUser(final U_JUploadDocInfo docInfo) throws JUploadException {
        this.log(docInfo.texmslog.unikey, "=========>> ID checks <<==========");
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final String qry = "select USER_GRP , CON_TYPE from UM_USER where PERMIT_YN='2' ";
        String condition = "";
        try {
            if (docInfo.texmslog.from_id.equals("cGuest0000000")) {
                docInfo.texmslog.from_grp = "SYSGRP";
                docInfo.texmslog.from_contype = "HH";
            }
            else {
                condition = " and USER_ID='" + docInfo.texmslog.from_id + "'";
                pstmt = docInfo.conn.prepareStatement(String.valueOf(qry) + condition);
                System.out.println("=====1" + qry + condition);
                rs = pstmt.executeQuery();
                System.out.println("=================== Loi roi day nay===============");
                if (!rs.next()) {
                    throw new Exception("Nguoi gui khong phai la nguoi dung dang ky 1");
                }
                docInfo.texmslog.from_grp = rs.getString("USER_GRP");
                docInfo.texmslog.from_contype = rs.getString("CON_TYPE");
                try {
                    rs.close();
                }
                catch (Exception ex2) {}
                try {
                    pstmt.close();
                }
                catch (Exception ex3) {}
            }
            if (docInfo.texmslog.to_id.equals("c000000000000")) {
                docInfo.texmslog.to_grp = "SYSGRP";
                docInfo.texmslog.to_contype = "CC";
            }
            else {
                condition = " and USER_ID='" + docInfo.texmslog.to_id + "'";
                pstmt = docInfo.conn.prepareStatement(String.valueOf(qry) + condition);
                System.out.println("=====2" + qry + condition);
                rs = pstmt.executeQuery();
                if (!rs.next()) {
                    throw new Exception("Nguoi gui khong phai la nguoi dung dang ky 2");
                }
                docInfo.texmslog.to_grp = rs.getString("USER_GRP");
                docInfo.texmslog.to_contype = rs.getString("CON_TYPE");
            }
        }
        catch (Exception ex) {
            throw new JUploadException(ex.getMessage(), 8);
        }
        finally {
            try {
                rs.close();
            }
            catch (Exception ex4) {}
            try {
                pstmt.close();
            }
            catch (Exception ex5) {}
        }
        try {
            rs.close();
        }
        catch (Exception ex6) {}
        try {
            pstmt.close();
        }
        catch (Exception ex7) {}
        this.log(docInfo.texmslog.unikey, "=========>> Kết thúc kiểm tra tham số gửi và nhận <<==========");
    }
    
    public boolean getDN(final String dn) {
        boolean retval = false;
        boolean g2b = false;
        String strDN = "";
        final StringTokenizer st = new StringTokenizer(dn, ",");
        while (st.hasMoreTokens()) {
            final String token = st.nextToken();
            if (token.startsWith("cn=")) {
                final String strCN = this.isNullStrTrim(token.substring(3, token.length())).toUpperCase();
                if (strCN.indexOf("테스트") >= 0) {
                    g2b = false;
                    break;
                }
            }
            if (token.startsWith("ou=")) {
                strDN = this.isNullStrTrim(token.substring(3, token.length())).toUpperCase();
                if (strDN.equals("G2B") || strDN.equals("조달청")) {
                    g2b = true;
                    break;
                }
                if (strDN.equals("G2B운영") || strDN.equals("G2B교육")) {
                    g2b = false;
                    break;
                }
                continue;
            }
        }
        retval = g2b;
        return retval;
    }
    
    public String isNullStrTrim(final String str) {
        return (str == null) ? "" : str.trim();
    }
    
    private void toLOGDB(final U_JUploadDocInfo docInfo) throws JUploadException {
        this.log(docInfo.texmslog.unikey, "=========>> BID_EDOC_LOG <<==========");
        PreparedStatement pstmt = null;
        final ResultSet rs = null;
        String qry = "";
        int rowcnt = 0;
        try {
            docInfo.texmslog.inform_date = new Date();
            qry = "insert into SYN_BID_EDOC_LOG  ( UNIKEY, MSG_TYPE, DOC_TYPE, STATUS, ERR_CD, IMPORT_DT, ANALYZ_DT, TRANSM_DT, INFORM_DT, EXPORT_DT, ERROR_DT, FROM_ID, FROM_TYPE, FROM_GRP, FROM_CONTYPE, TO_ID, TO_TYPE, TO_GRP, TO_CONTYPE, CPA_ID, CONVERSATION_ID, SERVICE, ACTION, MESSAGE_ID, REF_MESSAGE_ID, TIMESTAMP, SEQ_NUM, DESCRIPTION, ACK_REQ_TYPE, ACK_SIGN_YN, HIGHESTSERVERITY, ORG_FILE_NM, FILE_SIZE, ATTACH_COUNT, RESND_COUNT, CONSTRUCT_NM)  values ( '" + docInfo.texmslog.unikey + "', " + "'" + docInfo.texmslog.msg_type + "', " + "'" + docInfo.texmslog.doc_type + "', " + "'E', " + "'" + docInfo.texmslog.err_code + "', " + ((docInfo.texmslog.import_date == null) ? "NULL" : docInfo.df3.format(docInfo.texmslog.import_date)) + "," + ((docInfo.texmslog.analyz_date == null) ? "NULL" : docInfo.df3.format(docInfo.texmslog.analyz_date)) + "," + ((docInfo.texmslog.transm_date == null) ? "NULL" : docInfo.df3.format(docInfo.texmslog.transm_date)) + "," + ((docInfo.texmslog.inform_date == null) ? "NULL" : docInfo.df3.format(docInfo.texmslog.inform_date)) + "," + ((docInfo.texmslog.export_date == null) ? "NULL" : docInfo.df3.format(docInfo.texmslog.export_date)) + "," + ((docInfo.texmslog.error_date == null) ? "NULL" : docInfo.df3.format(docInfo.texmslog.error_date)) + "," + "'" + docInfo.texmslog.from_id + "', " + "'" + docInfo.texmslog.from_type + "', " + "'" + docInfo.texmslog.from_grp + "', " + "'" + docInfo.texmslog.from_contype + "', " + "'" + docInfo.texmslog.to_id + "', " + "'" + docInfo.texmslog.to_type + "', " + "'" + docInfo.texmslog.to_grp + "', " + "'" + docInfo.texmslog.to_contype + "', " + "?, ?," + "'" + docInfo.texmslog.service + "', " + "'" + docInfo.texmslog.action + "', " + "?, ?," + "'" + docInfo.texmslog.timestamp + "', " + docInfo.texmslog.seq_num + ", " + "?, " + "'" + docInfo.texmslog.ack_req_type + "', " + "'" + docInfo.texmslog.ack_sign_yn + "', " + "'" + docInfo.texmslog.highestserverity + "', " + "?, " + docInfo.texmslog.file_size + ", " + docInfo.texmslog.attach_count + ", " + docInfo.texmslog.resnd_count + ", " + "?) ";
            pstmt = docInfo.conn.prepareStatement(qry);
            pstmt.setString(1, docInfo.texmslog.cpa_id);
            pstmt.setString(2, docInfo.texmslog.conversation_id);
            pstmt.setString(3, docInfo.texmslog.message_id);
            pstmt.setString(4, docInfo.texmslog.ref_message_id);
            pstmt.setString(5, docInfo.texmslog.description);
            pstmt.setString(6, docInfo.texmslog.org_file_name);
            pstmt.setString(7, docInfo.texmslog.constructname);
            rowcnt = pstmt.executeUpdate();
            if (rowcnt != 1) {
                throw new Exception("Was issued in error T_EXMS_LOG Saving");
            }
            this.log(docInfo.texmslog.unikey, "=========>>Completed later T_EXMS_LOG DB <<==========");
        }
        catch (Exception ex) {
            throw new JUploadException(ex.getMessage(), 4);
        }
        finally {
            try {
                docInfo.conn.commit();
            }
            catch (Exception ex2) {}
            try {
                pstmt.close();
            }
            catch (Exception ex3) {}
        }
        try {
            docInfo.conn.commit();
        }
        catch (Exception ex4) {}
        try {
            pstmt.close();
        }
        catch (Exception ex5) {}
        this.log(docInfo.texmslog.unikey, "=========>>  Electronic document storage document file DB Start <<==========");
        try {
            docInfo.conn.setAutoCommit(false);
            qry = "insert into SYN_BID_EDOC_FILE(REG_DT, TRANS_NO, FILE_NM, SAVE_PATH, FILE_SIZE, FILE_ORDER_NO) values (" + ((docInfo.texmslog.import_date == null) ? "NULL" : docInfo.df3.format(docInfo.texmslog.import_date)) + "," + "'" + docInfo.texmslog.unikey + "', " + "?, " + "?, " + "?, " + "?) ";
            for (int i = 1; i < docInfo.mimes.size(); ++i) {
                pstmt = docInfo.conn.prepareStatement(qry);
                pstmt.setString(1, new File(this.GetValue(docInfo._SOAPDoc, "/SOAP:Envelope/SOAP:Body/eb:Manifest/eb:Reference/eb:Description", i - 1)).getName());
                pstmt.setString(2, ((DAttachInfo)docInfo.mimes.elementAt(i)).SavedPath);
                pstmt.setLong(3, ((DAttachInfo)docInfo.mimes.elementAt(i)).fileSize);
                pstmt.setString(4, ((DAttachInfo)docInfo.mimes.elementAt(i)).SEQ);
                rowcnt = pstmt.executeUpdate();
                if (rowcnt != 1) {
                    throw new Exception("Electronic document to a file Saving a document has been issued an error");
                }
            }
        }
        catch (Exception ex) {
            throw new JUploadException(ex.getMessage(), 5);
        }
        finally {
            try {
                pstmt.close();
            }
            catch (Exception ex6) {}
        }
        try {
            pstmt.close();
        }
        catch (Exception ex7) {}
        this.log(docInfo.texmslog.unikey, "=========>> Electronic document storage document file DB complete <<==========");
    }
    
    private void toDB(final U_JUploadDocInfo docInfo) throws JUploadException {
        System.out.println("=========>> Start the user information DB storage <<==========");
        final RcvGsacid gsacid = new RcvGsacid(docInfo.conn);
        final String ireturn = gsacid.main_process(docInfo.texmslog.unikey, String.valueOf(((DAttachInfo)docInfo.mimes.elementAt(1)).SavedPath) + U_JUpload.trailer[docInfo.isSigned]);
        this.log(docInfo.texmslog.unikey, gsacid.getLog());
        if (!"true".equals(ireturn)) {
            throw new JUploadException("[Đơn đăng ký /thay đổi nhà thầu]Xml2DB Exception" + ireturn.substring(3), 9 + Integer.parseInt(ireturn.substring(0, 2)));
        }
        this.log(docInfo.texmslog.unikey, "=========>> Hoàn thành lưu thông tin người dùng vào DB <<==========");
    }
    
    private void toRepository(final U_JUploadDocInfo docInfo) throws JUploadException {
        this.log(docInfo.texmslog.unikey, "=========>> Repository Bắt đầu lưu văn bản <<==========");
        final String sResult = "";
        try {
            final Xml2Rep xml2rep = new Xml2Rep(docInfo.texmslog.unikey, docInfo.df.format(docInfo.now));
            xml2rep.save2Rep(String.valueOf(((DAttachInfo)docInfo.mimes.elementAt(1)).SavedPath) + U_JUpload.trailer[docInfo.isSigned]);
        }
        catch (Exception e) {
            throw new JUploadException("Repository 저장 실패: " + e.getMessage(), 20);
        }
        this.log(docInfo.texmslog.unikey, "=========>> Repository 문서저장 완료 <<==========");
    }
    
    private void toCopy(final U_JUploadDocInfo docInfo) throws JUploadException {
        this.log(docInfo.texmslog.unikey, "=========>> Work 문서저장 시작 <<==========");
        final String sResult = "";
        final File inBoxDir = null;
        File atBoxDir = null;
        int ret = 0;
        final int bytes_read = 0;
        final byte[] buffer = new byte[8192];
        final String input_file = String.valueOf(U_JUpload.rootBox) + docInfo.df.format(docInfo.now) + U_JUpload.inBox + docInfo.texmslog.unikey;
        final String output_file = String.valueOf(U_JUpload.rootBox) + docInfo.df.format(docInfo.now) + File.separator + "Work" + File.separator + U_JUpload.inBox + docInfo.texmslog.unikey;
        final FileOutputStream out = null;
        final FileInputStream in = null;
        try {
            FileInputStream is = null;
            FileOutputStream fout = null;
            if (new File(String.valueOf(U_JUpload.rootBox) + docInfo.df.format(docInfo.now) + U_JUpload.atBox + docInfo.texmslog.unikey + ".0").exists()) {
                atBoxDir = new File(String.valueOf(U_JUpload.rootBox) + docInfo.df.format(docInfo.now) + U_JUpload.atBox + "Work" + File.separator);
                if (!atBoxDir.exists()) {
                    atBoxDir.mkdirs();
                }
                is = new FileInputStream(String.valueOf(U_JUpload.rootBox) + docInfo.df.format(docInfo.now) + U_JUpload.atBox + docInfo.texmslog.unikey + ".0");
                fout = new FileOutputStream(String.valueOf(U_JUpload.rootBox) + docInfo.df.format(docInfo.now) + U_JUpload.atBox + "Work" + File.separator + docInfo.texmslog.unikey + ".0");
                ret = 0;
                while ((ret = is.read(buffer)) != -1) {
                    fout.write(buffer, 0, ret);
                }
                fout.flush();
                ret = 0;
                if (new File(String.valueOf(U_JUpload.rootBox) + docInfo.df.format(docInfo.now) + U_JUpload.atBox + docInfo.texmslog.unikey + ".0.dec").exists()) {
                    is = new FileInputStream(String.valueOf(U_JUpload.rootBox) + docInfo.df.format(docInfo.now) + U_JUpload.atBox + docInfo.texmslog.unikey + ".0.dec");
                    fout = new FileOutputStream(String.valueOf(U_JUpload.rootBox) + docInfo.df.format(docInfo.now) + U_JUpload.atBox + "Work" + File.separator + docInfo.texmslog.unikey + ".0");
                    System.out.println("Copy file giai ma ================" + U_JUpload.rootBox + docInfo.df.format(docInfo.now) + U_JUpload.atBox + "Work" + File.separator + docInfo.texmslog.unikey + ".0");
                    ret = 0;
                    while ((ret = is.read(buffer)) != -1) {
                        fout.write(buffer, 0, ret);
                    }
                    fout.flush();
                    ret = 0;
                }
            }
            try {
                fout.close();
            }
            catch (Exception ex) {}
            try {
                is.close();
            }
            catch (Exception ex2) {}
        }
        catch (FileNotFoundException e) {
            throw new JUploadException("Work lưu văn bản FileNotFound lỗi: " + e.getMessage(), 20);
        }
        catch (IOException e2) {
            throw new JUploadException("Work lưu văn bản IO lỗi: " + e2.getMessage(), 20);
        }
        catch (Exception e3) {
            throw new JUploadException("Work lưu văn bản lỗi: " + e3.getMessage(), 20);
        }
        finally {
            try {
                out.close();
            }
            catch (Exception ex3) {}
        }
        try {
            out.close();
        }
        catch (Exception ex4) {}
        this.log(docInfo.texmslog.unikey, "=========>> Work hoàn thành lưu văn bản <<==========");
    }
    
    private boolean fileCopy(final String strInFile, final String strOutFile) {
        final int readSize = 8192;
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(strInFile);
            out = new FileOutputStream(strOutFile);
            int n = 0;
            final byte[] buffer = new byte[readSize];
            while ((n = in.read(buffer, 0, readSize)) != -1) {
                out.write(buffer, 0, n);
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (Exception ex) {}
            }
            if (out != null) {
                try {
                    out.close();
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    private void getLogData(final U_JUploadDocInfo docInfo) throws Exception {
        this.log(docInfo.texmslog.unikey, "=========>> I started reading the document information <<==========");
        PreparedStatement pstmt = null;
        final ResultSet rs = null;
        final String qry = "select 공사명 from 유통_기관접수정보 where 문서코드=?";
        docInfo.texmslog.from_type = this.GetValue(docInfo._SOAPDoc, "/SOAP:Envelope/SOAP:Header/eb:MessageHeader/eb:From/eb:PartyId@eb:type");
        docInfo.texmslog.from_grp = "SYSGRP";
        docInfo.texmslog.from_contype = "HH";
        docInfo.texmslog.to_id = this.GetValue(docInfo._SOAPDoc, "/SOAP:Envelope/SOAP:Header/eb:MessageHeader/eb:To/eb:PartyId");
        docInfo.texmslog.to_type = this.GetValue(docInfo._SOAPDoc, "/SOAP:Envelope/SOAP:Header/eb:MessageHeader/eb:To/eb:PartyId@eb:type");
        docInfo.texmslog.from_grp = "SYSGRP";
        docInfo.texmslog.from_contype = "CC";
        docInfo.texmslog.cpa_id = this.GetValue(docInfo._SOAPDoc, "/SOAP:Envelope/SOAP:Header/eb:MessageHeader/eb:CPAId");
        docInfo.texmslog.conversation_id = this.GetValue(docInfo._SOAPDoc, "/SOAP:Envelope/SOAP:Header/eb:MessageHeader/eb:ConversationId");
        docInfo.texmslog.service = this.GetValue(docInfo._SOAPDoc, "/SOAP:Envelope/SOAP:Header/eb:MessageHeader/eb:Service");
        docInfo.texmslog.action = this.GetValue(docInfo._SOAPDoc, "/SOAP:Envelope/SOAP:Header/eb:MessageHeader/eb:Action");
        docInfo.texmslog.action = docInfo.texmslog.action.substring(docInfo.texmslog.action.indexOf(":") + 1, docInfo.texmslog.action.length());
        docInfo.texmslog.message_id = this.GetValue(docInfo._SOAPDoc, "/SOAP:Envelope/SOAP:Header/eb:MessageHeader/eb:MessageData/eb:MessageId");
        docInfo.texmslog.ref_message_id = this.GetValue(docInfo._SOAPDoc, "/SOAP:Envelope/SOAP:Header/eb:MessageHeader/eb:MessageData/eb:RefToMessageId");
        docInfo.texmslog.timestamp = this.GetValue(docInfo._SOAPDoc, "/SOAP:Envelope/SOAP:Header/eb:MessageHeader/eb:MessageData/eb:Timestamp");
        docInfo.texmslog.description = this.GetValue(docInfo._SOAPDoc, "/SOAP:Envelope/SOAP:Header/eb:MessageHeader/eb:Description");
        docInfo.texmslog.ack_req_type = this.GetValue(docInfo._SOAPDoc, "/SOAP:Envelope/SOAP:Header/eb:MessageHeader/eb:AckRequested@SOAP:actor").toLowerCase();
        docInfo.texmslog.ack_req_type = ((docInfo.texmslog.ack_req_type.indexOf("topartymsh") != -1) ? "T" : "N");
        docInfo.texmslog.ack_sign_yn = (this.GetValue(docInfo._SOAPDoc, "/SOAP:Envelope/SOAP:Header/eb:MessageHeader/eb:AckRequested@eb:signed").toLowerCase().equals("true") ? "Y" : "N");
        docInfo.texmslog.org_file_name = new File(this.GetValue(docInfo._SOAPDoc, "/SOAP:Envelope/SOAP:Body/eb:Manifest/eb:Reference/eb:Description")).getName();
        docInfo.texmslog.file_size = new File(String.valueOf(U_JUpload.rootBox) + docInfo.df.format(docInfo.now) + U_JUpload.inBox + docInfo.texmslog.unikey).length();
        docInfo.texmslog.attach_count = docInfo.mimes.size() - 2;
        docInfo.texmslog.resnd_count = 0;
        try {
            this.log(docInfo.texmslog.unikey, "=========>> Path started reading <<==========");
            pstmt = docInfo.conn.prepareStatement(qry);
            pstmt.setString(1, docInfo.texmslog.action);
            docInfo.texmslog.constructname = "";
            this.log(docInfo.texmslog.unikey, "=========>> Path Read complete <<==========");
        }
        catch (Exception ex) {}
        finally {
            try {
                rs.close();
                pstmt.close();
            }
            catch (Exception ex2) {}
        }
        try {
            rs.close();
            pstmt.close();
        }
        catch (Exception ex3) {}
        this.log(docInfo.texmslog.unikey, "=========>> Read complete article information <<==========");
    }
    
    private String GetValue(final XMLDocument doc, final String node) throws Exception {
        return this.GetValue(doc, node, 0);
    }
    
    private String GetValue(final XMLDocument doc, final String node, final int index) throws Exception {
        if (node.indexOf("@") == -1) {
            return this.GetNodeValue(doc, node, index);
        }
        return this.GetAttrValue(doc, node.substring(0, node.indexOf("@")), node.substring(node.indexOf("@") + 1, node.length()), index);
    }
    
    private String GetNodeValue(final XMLDocument doc, final String node, final int index) throws Exception {
        try {
            return doc.selectNodes(node, (NSResolver)(XMLElement)doc.getDocumentElement()).item(index).getFirstChild().getNodeValue();
        }
        catch (NullPointerException e) {
            return "";
        }
    }
    
    private String GetAttrValue(final XMLDocument doc, final String node, final String attr, final int index) throws Exception {
        try {
            return ((Element)doc.selectNodes(node, (NSResolver)(XMLElement)doc.getDocumentElement()).item(index)).getAttribute(attr);
        }
        catch (NullPointerException e) {
            return "";
        }
    }
    
    private void log(final String s1, final String s2) {
        U_JUpload.LOG.log(s1, "[" + s1 + "] " + s2);
    }
    
    public static synchronized int getUniSequence() {
        if (++U_JUpload.UniSequence > 9999) {
            U_JUpload.UniSequence = 0;
        }
        return U_JUpload.UniSequence;
    }
}
