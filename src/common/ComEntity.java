// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.io.Serializable;

public class ComEntity implements Serializable
{
    public int columnCount;
    public String[] data;
    
    public ComEntity() {
        this.data = null;
    }
    
    public void dataArraySetting(final int columnCount) {
        this.columnCount = columnCount;
        this.data = new String[columnCount];
    }
}
