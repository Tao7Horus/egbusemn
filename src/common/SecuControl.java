// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.security.MessageDigest;
import secu.lib.EnvelopedMessage;
import secu.lib.Secu;

public class SecuControl
{
    public String[] encrypt(final String s) throws Exception {
        final String[] array = { null, null };
        String encryptInit = null;
        String encryptUpdate = null;
        EnvelopedMessage envelopedMessage = null;
        final Secu secu = new Secu(4);
        try {
            envelopedMessage = new EnvelopedMessage(secu);
            encryptInit = envelopedMessage.encryptInit(secu.getPemKmCertInfo(2));
            if (encryptInit == null) {
                throw new Exception("# encryption failure");
            }
            encryptUpdate = envelopedMessage.encryptUpdate(s);
            if (encryptUpdate == null) {
                throw new Exception("# Data Encryption Failure");
            }
        }
        finally {
            try {
                if (envelopedMessage != null) {
                    envelopedMessage.encryptFinal();
                }
            }
            catch (Exception ex) {}
        }
        array[0] = encryptInit;
        array[1] = encryptUpdate;
        return array;
    }
    
    public String decrypt(final String s, final String s2) throws Exception {
        String decryptUpdate = "";
        EnvelopedMessage envelopedMessage = null;
        final Secu secu = new Secu(4);
        try {
            envelopedMessage = new EnvelopedMessage(secu);
            envelopedMessage.decryptInit(2, 1, s);
            decryptUpdate = envelopedMessage.decryptUpdate(s2);
            if (decryptUpdate == null) {
                throw new Exception("# Data decryption failed");
            }
        }
        finally {
            if (envelopedMessage != null) {
                try {
                    envelopedMessage.decryptFinal();
                }
                catch (Exception ex) {}
            }
        }
        return decryptUpdate;
    }
    
    public static String encodeString(final String s) throws Exception {
        if (s == null || s.length() == 0) {
            return s;
        }
        final MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(s.getBytes());
        return Base64Util.encode(instance.digest());
    }
}
