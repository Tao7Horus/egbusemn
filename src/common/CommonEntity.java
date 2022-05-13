// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.io.Serializable;

public class CommonEntity implements Serializable
{
    public int columnCount;
    public String[] data;
    
    public CommonEntity() {
        this.data = null;
    }
    
    public void dataArraySetting(final int ColCount) {
        this.columnCount = ColCount;
        this.data = new String[ColCount];
    }
}
