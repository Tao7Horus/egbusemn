// 
// Decompiled by Procyon v0.5.30
// 

package common;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class CheckValidation
{
    private static final List validIPList;
    
    static {
        (validIPList = new ArrayList()).add("211.42.85.190");
        CheckValidation.validIPList.add("211.42.85.191");
        CheckValidation.validIPList.add("211.42.85.192");
        CheckValidation.validIPList.add("211.42.85.193");
        CheckValidation.validIPList.add("211.42.85.194");
        CheckValidation.validIPList.add("211.42.85.195");
        CheckValidation.validIPList.add("211.42.85.196");
        CheckValidation.validIPList.add("211.42.85.197");
        CheckValidation.validIPList.add("211.42.85.198");
        CheckValidation.validIPList.add("211.42.85.199");
    }
    
    public static boolean isValidIP(final HttpServletRequest req) throws Exception {
        final String gatewayIP = ExplorerControl.getIP(req);
        return CheckValidation.validIPList.contains(gatewayIP);
    }
}
