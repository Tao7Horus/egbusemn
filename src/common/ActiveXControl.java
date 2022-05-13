// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.sql.Connection;
import GT.EP_COB_GTQ908;
import GT.EP_COB_GTQ805;
import GT.EP_COE_GTQ903;

public class ActiveXControl
{
    public EP_COE_GTQ903[] getActivexInfo(final String s) throws Exception {
        final String retNull = EP_COB_GTQ805.retNull(s);
        if (retNull == null) {
            throw new Exception("activeX 버젼 parameter 오류");
        }
        return new EP_COB_GTQ908().getList((Object)this, " select VERSION,종류,사용여부,입력일시, CODEBASE, CLASSID, 파일명  from SYN_사용_ACTIVEX이력 where 사용여부='Y' and 종류=?", new String[] { retNull }, (Connection)null);
    }
}
