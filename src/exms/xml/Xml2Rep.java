// 
// Decompiled by Procyon v0.5.30
// 

package exms.xml;

import com.exln.dxe.filesystem.XlnOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.StringTokenizer;
import com.exln.dxe.filesystem.XMLDocument;
import com.exln.dxe.filesystem.Directory;
import com.exln.dxe.XMLStore;
import com.exln.dxe.Partition;
import com.exln.dxe.Session;
import datec.xis.session.ClientSession;

public class Xml2Rep
{
    static boolean isLocal;
    private String targetStore;
    private String path;
    private String unikey;
    
    static {
        Xml2Rep.isLocal = false;
    }
    
    public Xml2Rep(final String unikey, final String path) {
        this.targetStore = "";
        this.path = "";
        this.unikey = "";
        this.unikey = unikey;
        this.targetStore = path.substring(0, path.indexOf("/"));
        this.path = path.substring(path.indexOf("/") + 1, path.length());
    }
    
    public void save2Rep(final String source) throws Exception {
        System.setProperty("com.exln.dxe.adminhost", "XLNDB");
        final Session session = ClientSession.getInstance("XLNDB", 1050).getSession("ppsxml", "update");
        if (session == null) {
            throw new Exception("Repository 연결 실패");
        }
        final Partition partition = getPartition(session);
        final XMLStore xmlStore = getXMLStore(session, partition, this.targetStore);
        if (xmlStore == null) {
            throw new Exception("Store 생성 실패.");
        }
        final Directory xmlDir = getXMLDirectory(session, this.targetStore, this.path);
        if (xmlDir == null) {
            throw new Exception("Directory 생성 실패.");
        }
        final XMLDocument xmlDoc = createXMLDocument(session, xmlDir, this.unikey, source);
        if (xmlDoc == null) {
            throw new Exception("XMLFile 생성 실패.");
        }
    }
    
    public static Partition getPartition(final Session session) throws Exception {
        Partition partition = null;
        partition = session.getPartition("xlndefault");
        if (partition == null) {
            throw new Exception("파티션 XISLabPartition이 없습니다.");
        }
        return partition;
    }
    
    public static XMLStore getXMLStore(final Session session, final Partition partition, final String targetStore) throws Exception {
        XMLStore xmlStore = null;
        xmlStore = session.getXMLStore(targetStore);
        if (xmlStore == null) {
            xmlStore = session.createXMLStore(targetStore, partition);
        }
        return xmlStore;
    }
    
    public static Directory getXMLDirectory(final Session session, final String targetStore, final String path) {
        XMLStore xmlStore;
        Directory xmlDir;
        StringTokenizer st;
        String tk;
        for (xmlStore = session.getXMLStore(targetStore), xmlDir = null, st = new StringTokenizer(path, "/"), tk = st.nextToken(), xmlDir = (Directory)xmlStore.getFile(tk); xmlDir == null || st.hasMoreTokens(); xmlDir = (Directory)xmlStore.getFile(tk)) {
            if (xmlDir == null) {
                xmlStore.createDirectory(tk);
            }
            tk = String.valueOf(tk) + (st.hasMoreTokens() ? ("/" + st.nextToken()) : "");
        }
        return xmlDir;
    }
    
    public static XMLDocument createXMLDocument(final Session session, final Directory xmlDir, final String srcname, final String srcpath) throws Exception {
        XMLDocument xmlDoc = null;
        xmlDoc = xmlDir.createXMLDocument(String.valueOf(srcname) + ".0");
        final XlnOutputStream outStream = xmlDoc.getOutputStream();
        outStream.setContentType("text/xml");
        outStream.setOverwrite(1);
        final File f = new File(srcpath);
        final FileInputStream fln = new FileInputStream(f);
        final byte[] bytes = new byte[fln.available()];
        fln.read(bytes);
        outStream.write(bytes);
        try {
            fln.close();
        }
        catch (Exception ex) {}
        try {
            outStream.close();
        }
        catch (Exception ex2) {}
        return xmlDoc;
    }
}
