// 
// Decompiled by Procyon v0.5.30
// 

package entity;

import java.io.Serializable;

public class UM_FOE_CommInfo2 implements Serializable
{
    private static final long serialVersionUID = 1L;
    public int columnCount;
    public String[] data;
    
    public UM_FOE_CommInfo2() {
        this.data = null;
    }
    
    public void dataArraySetting(final int columnCount) {
        this.columnCount = columnCount;
        this.data = new String[columnCount];
    }
}
