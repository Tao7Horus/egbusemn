// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.sql.PreparedStatement;

public class QueryUtil
{
    public void setPreparedValues(final Object caller_obj, final PreparedStatement pstmt, final String[] parameter) throws Exception {
        try {
            if (parameter != null && parameter.length > 0) {
                for (int i = 0, n = parameter.length; i < n; ++i) {
                    pstmt.setString(i + 1, parameter[i]);
                }
            }
        }
        catch (Exception ex) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".setPreparedValues():caller->" + caller_obj.getClass().getName() + ":" + ex.toString());
            throw new Exception(ex.getMessage());
        }
    }
}
