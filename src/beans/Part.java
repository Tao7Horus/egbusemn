// 
// Decompiled by Procyon v0.5.30
// 

package beans;

public abstract class Part
{
    private String name;
    
    Part(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean isFile() {
        return false;
    }
    
    public boolean isParam() {
        return false;
    }
}
