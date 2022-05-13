// 
// Decompiled by Procyon v0.5.30
// 

package connect;

import portal.mypage.publicmessage.datamodel.PublicMessageInfo;
import java.util.Vector;
import common.Log;
import javax.naming.InitialContext;
import portal.mypage.publicmessage.session.PublicMessage;
import portal.mypage.publicmessage.session.PublicMessageHome;

public class PublicMessageConn
{
    private PublicMessageHome publicmessagehome;
    private PublicMessage publicmessage;
    
    public PublicMessageConn() {
        this.publicmessagehome = null;
        this.publicmessage = null;
        try {
            this.publicmessagehome = (PublicMessageHome)new InitialContext().lookup("PublicMessage");
            this.publicmessage = this.publicmessagehome.create();
        }
        catch (Exception ex) {
            final Log log = new Log();
            Log.debug(ex.toString());
        }
    }
    
    public Vector getMessageList(final String s, final String s2, final String s3, final String s4, final String s5) {
        Vector messageList = new Vector();
        try {
            messageList = this.publicmessage.getMessageList(s, s2, s3, s4, s5);
        }
        catch (Exception ex) {
            System.out.println("getMessageList_servlet : " + ex);
        }
        return messageList;
    }
    
    public Vector getMessageMain(final String s, final String s2) {
        Vector messageMain = new Vector();
        try {
            messageMain = this.publicmessage.getMessageMain(s, s2);
        }
        catch (Exception ex) {
            System.out.println("getMessageMain_servlet : " + ex);
        }
        return messageMain;
    }
    
    public PublicMessageInfo getMessageDet(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        PublicMessageInfo messageDet = null;
        try {
            messageDet = this.publicmessage.getMessageDet(s, s2, s3, s4, s5, s6, s7);
        }
        catch (Exception ex) {
            System.out.println("getMessageDet_servlet : " + ex);
        }
        return messageDet;
    }
    
    public Vector getMessageCont(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Vector messageCont = new Vector();
        try {
            messageCont = this.publicmessage.getMessageCont(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println("getMessageCont_servlet : " + ex);
        }
        return messageCont;
    }
    
    public int deleteMessage(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        int deleteMessage = 0;
        try {
            deleteMessage = this.publicmessage.deleteMessage(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println("deleteMessage_servlet : " + ex);
        }
        return deleteMessage;
    }
    
    public String getCheckMessage(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        String checkMessage = "";
        try {
            checkMessage = this.publicmessage.getCheckMessage(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println("getCheckMessage_servlet : " + ex);
        }
        return checkMessage;
    }
    
    public int updateMessage(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        int updateMessage = 0;
        try {
            updateMessage = this.publicmessage.updateMessage(s, s2, s3, s4, s5, s6);
        }
        catch (Exception ex) {
            System.out.println("updateMessage_servlet : " + ex);
        }
        return updateMessage;
    }
}
