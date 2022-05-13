// 
// Decompiled by Procyon v0.5.30
// 

package common.util;

import java.io.Serializable;

public class UM_COE_GTQ903 implements Serializable
{
    public int columnCount;
    public String[] data;
    
    public UM_COE_GTQ903() {
        this.data = null;
    }
    
    public void dataArraySetting(final int columnCount) {
        this.columnCount = columnCount;
        this.data = new String[columnCount];
    }
}
