// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.util.Vector;
import java.sql.SQLException;

public interface DbWrap
{
    Entity insert(final Entity p0) throws SQLException;
    
    Entity select(final Entity p0) throws SQLException;
    
    Entity update(final Entity p0) throws SQLException;
    
    void delete(final Entity p0) throws SQLException;
    
    Vector selectByCondition(final String p0, final String p1) throws SQLException;
    
    Vector selectByCondition(final String p0) throws SQLException;
}
