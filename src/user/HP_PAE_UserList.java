// 
// Decompiled by Procyon v0.5.30
// 

package user;

import java.util.Vector;

public class HP_PAE_UserList
{
    private Vector flist;
    private int totalcount;
    private int index;
    private int max;
    
    private HP_PAE_UserList() {
    }
    
    public HP_PAE_UserList(final int max) {
        this.flist = new Vector();
        this.totalcount = 0;
        this.index = -1;
        this.max = max;
    }
    
    public boolean add(final HP_PAE_UserInfo hp_PAE_UserInfo) {
        if (this.totalcount >= this.max) {
            return false;
        }
        this.flist.add(hp_PAE_UserInfo);
        ++this.totalcount;
        return true;
    }
    
    public boolean next() {
        if (this.index < this.flist.size() - 1 && this.index < this.max - 1) {
            ++this.index;
            return true;
        }
        return false;
    }
    
    public HP_PAE_UserInfo getUserEB() {
        return this.flist.get(this.index);
    }
    
    public int getTotalCount() {
        return this.totalcount;
    }
}
