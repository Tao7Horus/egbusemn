// 
// Decompiled by Procyon v0.5.30
// 

package exms.upload;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import common.Trx;
import oracle.xml.parser.v2.XMLDocument;
import oracle.xml.parser.v2.DOMParser;
import java.util.Vector;

public class U_JUploadDocInfo
{
    public String contentType;
    public String _SOAP;
    public String _XML;
    public Vector mimes;
    public DOMParser _SOAPParser;
    public XMLDocument _SOAPDoc;
    public DOMParser _XMLParser;
    public XMLDocument _XMLDoc;
    public Trx trx;
    public Connection conn;
    public Date now;
    public TExmsLog texmslog;
    public int isSigned;
    public SimpleDateFormat df;
    public SimpleDateFormat df2;
    public SimpleDateFormat df3;
    
    public U_JUploadDocInfo() {
        this.contentType = "";
        this._SOAP = "";
        this._XML = "";
        this.mimes = null;
        this._SOAPParser = null;
        this._SOAPDoc = null;
        this._XMLParser = null;
        this._XMLDoc = null;
        this.trx = null;
        this.conn = null;
        this.now = null;
        this.texmslog = null;
        this.isSigned = 0;
        this.df = null;
        this.df2 = null;
        this.df3 = null;
        this._SOAPParser = new DOMParser();
        this._SOAPDoc = new XMLDocument();
        this._XMLParser = new DOMParser();
        this._XMLDoc = new XMLDocument();
        this.texmslog = new TExmsLog();
        this.df = new SimpleDateFormat("yyyy'/'MM'/'dd'/'");
        this.df2 = new SimpleDateFormat("'U'yyyyMMddHHmmss");
        this.df3 = new SimpleDateFormat("'to_date('''yyyy-MM-dd HH:mm:ss''',''YYYY-MM-DD HH24:MI:SS'')'");
    }
}
