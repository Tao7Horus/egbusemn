// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.sql.ResultSetMetaData;
import java.util.Hashtable;
import java.io.Serializable;

public class OneRowEntity implements Serializable
{
    public int columnCount;
    public String[] data;
    public Hashtable mappingHash;
    public boolean DataExist;
    
    public OneRowEntity() {
        this.data = null;
        this.mappingHash = null;
        this.DataExist = false;
    }
    
    public void dataArraySetting(final int ColCount) {
        this.columnCount = ColCount;
        this.data = new String[ColCount];
    }
    
    public void hashMappingSetting(final ResultSetMetaData rsmd) throws Exception {
        if (rsmd == null) {
            throw new Exception("ResultSetMetaData is null");
        }
        final int count = rsmd.getColumnCount();
        this.mappingHash = new Hashtable();
        for (int j = 0; j < count; ++j) {
            this.mappingHash.put(rsmd.getColumnName(j + 1).toUpperCase(), new Integer(j));
        }
    }
    
    public String getDataFromName(final String fieldName) throws Exception {
        this.checkSeq();
        final Integer n = (Integer)this.mappingHash.get(fieldName.toUpperCase());
        if (n == null) {
            Log.debug("Không tìm thấy dữ liệu của mục.[" + fieldName + "]");
            throw new Exception("Không tìm thấy dữ liệu của mục.[" + fieldName + "]");
        }
        final String str = this.data[(int)n];
        if (str == null) {
            Log.debug("Không tìm thấy dữ liệu của mục.[" + fieldName + "]");
            throw new Exception("Không tìm thấy dữ liệu của mục.[" + fieldName + "]");
        }
        return str;
    }
    
    private void checkSeq() throws Exception {
        if (this.mappingHash == null) {
            throw new Exception("Không map được dữ liệu");
        }
        if (this.data == null) {
            throw new Exception("Dữ liệu không tồn tại.");
        }
    }
}
