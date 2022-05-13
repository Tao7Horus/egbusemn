// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.sql.Connection;
import java.util.Hashtable;

public class MemoryNewInfo
{
    private static final MemoryNewInfo instance;
    private static Hashtable BID_INFO;
    private static Hashtable RESULT_INFO;
    String user;
    CommEntity[] list;
    CommDbQuery comquery;
    public static final String BID_TOTAL_KEY = "total";
    public static final String BID_ITEM_KEY = "item";
    public static final String BID_SISL_KEY = "sisl";
    public static final String BID_YONG_KEY = "yong";
    public static final String BID_FORE_KEY = "fore";
    public static final String RESULT_KEY = "result";
    
    static {
        instance = new MemoryNewInfo();
        MemoryNewInfo.BID_INFO = null;
        MemoryNewInfo.RESULT_INFO = null;
        MemoryNewInfo.BID_INFO = new Hashtable();
        MemoryNewInfo.RESULT_INFO = new Hashtable();
    }
    
    public MemoryNewInfo() {
        this.user = "vbid";
        this.list = null;
        this.comquery = null;
    }
    
    public static MemoryNewInfo getInstance() {
        return MemoryNewInfo.instance;
    }
    
    public void init() throws Exception {
        if (MemoryNewInfo.BID_INFO.isEmpty()) {
            this.setInitData(1);
        }
        if (MemoryNewInfo.RESULT_INFO.isEmpty()) {
            this.setInitData(4);
        }
    }
    
    public static CommEntity[] getBidInfo(final String key) throws Exception {
        if (!MemoryNewInfo.BID_INFO.containsKey(key)) {
            throw new Exception("key 값 확인 필요");
        }
        return MemoryNewInfo.BID_INFO.get(key);
    }
    
    public static CommEntity[] getResultInfo(final String key) throws Exception {
        if (!MemoryNewInfo.RESULT_INFO.containsKey(key)) {
            throw new Exception("key 값 확인 필요");
        }
        return MemoryNewInfo.RESULT_INFO.get(key);
    }
    
    public void setInitData(final int type) throws Exception {
        if (type == 1) {
            this.setBidData(null);
        }
        if (type == 4) {
            this.setResultData(null);
        }
    }
    
    public void setBidData(final Connection conn) throws Exception {
        throw new Error("Unresolved compilation problems: \n\tThe method getList(Object, String, String[], Connection) in the type CommDbQuery is not applicable for the arguments (MemoryNewInfo, String, null, String, Connection)\n\tThe method getList(Object, String, String[], Connection) in the type CommDbQuery is not applicable for the arguments (MemoryNewInfo, String, null, String, Connection)\n\tThe method getList(Object, String, String[], Connection) in the type CommDbQuery is not applicable for the arguments (MemoryNewInfo, String, null, String, Connection)\n\tThe method getList(Object, String, String[], Connection) in the type CommDbQuery is not applicable for the arguments (MemoryNewInfo, String, null, String, Connection)\n\tThe method getList(Object, String, String[], Connection) in the type CommDbQuery is not applicable for the arguments (MemoryNewInfo, String, null, String, Connection)\n");
    }
    
    public void setResultData(final Connection conn) throws Exception {
        throw new Error("Unresolved compilation problems: \n\tThe method getList(Object, String, String[], Connection) in the type CommDbQuery is not applicable for the arguments (MemoryNewInfo, String, null, String, Connection)\n\tThe method getList(Object, String, String[], Connection) in the type CommDbQuery is not applicable for the arguments (MemoryNewInfo, String, null, String, Connection)\n\tThe method getList(Object, String, String[], Connection) in the type CommDbQuery is not applicable for the arguments (MemoryNewInfo, String, null, String, Connection)\n\tThe method getList(Object, String, String[], Connection) in the type CommDbQuery is not applicable for the arguments (MemoryNewInfo, String, null, String, Connection)\n\tThe method getList(Object, String, String[], Connection) in the type CommDbQuery is not applicable for the arguments (MemoryNewInfo, String, null, String, Connection)\n");
    }
    
    public void setDataFromCron(final Connection conn) throws Exception {
        this.setBidData(conn);
        this.setResultData(conn);
    }
    
    private String getBidInfoSql(final String key) throws Exception {
        String sql = " select decode(입찰유형, '1','물품', '2','외자','3','시설','5','용역','6','리스','7','리스',  '9','용역','10','용역','11','비축',입찰유형) 유형,  decode(공고정보,'01','일반','02','정정','03','취소','04','재입찰','05','연기','06','긴급','07','갱신','08','긴급갱신',공고정보) 분류, 공고명,  gt_query( decode(입찰유형,  '1','L17', '2','F45', '3','S01', '5',decode(length(계약방법),2,'S90','L17'), '6','L17', '7','L17', '9','L17', '10',decode(length(계약방법),2,'S90','L17'), '11','J50','L17'), 계약방법, '2') 계약방법명, to_char(입찰마감일시,'mm/dd hh24:mi') 입찰마감, 입찰공고번호, 입찰공고차수, 통합공고번호, 입찰유형, 등록유형 from ( select /*+ INDEX_DESC(a IDX_공고_통합입찰공고3) */ a.입찰유형,  a.공고정보, a.공고명, a.계약방법,  a.입찰마감일시, a.입찰공고번호, a.입찰공고차수, a.통합공고번호, a.등록유형 from 공고_통합입찰공고 a where a.공고일시 between sysdate-5 and sysdate and a.입찰공고번호||'-'||a.입찰공고차수=(select 입찰공고번호||'-'||max(입찰공고차수)   \t\t\t\t\t\t\t\t\t\t from 공고_통합입찰공고 b \t\t\t\t\t\t\t\t\t\t where a.통합공고번호=b.통합공고번호  \t\t\t\t\t\t\t\t\t\t group by 입찰공고번호) and a.등록유형='2' and a.등록여부='Y'";
        if (key.equals("item")) {
            sql = String.valueOf(sql) + "\r\n and a.입찰유형='1'";
        }
        if (key.equals("sisl")) {
            sql = String.valueOf(sql) + "\r\n and a.입찰유형='3'";
        }
        if (key.equals("yong")) {
            sql = String.valueOf(sql) + "\r\n and a.입찰유형 in ('5','9','10')";
        }
        if (key.equals("fore")) {
            sql = String.valueOf(sql) + "\r\n and a.입찰유형='2'";
        }
        sql = String.valueOf(sql) + " and rownum <=15 )";
        return sql;
    }
    
    private String getResultInfoSql(final String key) throws Exception {
        String sql = " select /*+ INDEX_DESC(a IDX_입찰상황1)*/ a.입찰공고번호, a.입찰공고차수, a.입찰분류, a.재입찰번호, b.공고명,  c.공공기관명_전체 실수요기관,  gt_query( decode(b.업무구분,  '1','L17', '2','F45', '3','S01', '5',decode(length(b.계약방법),2,'S90','L17'), '6','L17', '7','L17', '9','L17', '10',decode(length(b.계약방법),2,'S90','L17'), '4','J50','L17'), b.계약방법, '2') 계약방법명,  to_char(a.입력일시,'mm/dd hh24:mi'), decode(b.업무구분,'1','물품','2','외자','3','시설','4','비축','5','용역','6','리스','7','리스','9','용역',''), b.업무구분 from 입찰공고현황 b, syn_사용_공공기관마스터 c, 입찰상황 a where a.진행구분='4' and a.입력일시 between sysdate-5 and sysdate and a.입찰공고번호=b.입찰공고번호 and a.입찰공고차수=b.입찰공고차수 and b.등록여부='Y' and b.수요기관코드=c.공공기관코드 and nvl(b.시담공개여부,'Y') ='Y'";
        if (key.equals("item")) {
            sql = String.valueOf(sql) + "\r\n and b.업무구분='1'";
        }
        if (key.equals("sisl")) {
            sql = String.valueOf(sql) + "\r\n and b.업무구분='3'";
        }
        if (key.equals("yong")) {
            sql = String.valueOf(sql) + "\r\n and b.업무구분 in ('5','9')";
        }
        if (key.equals("fore")) {
            sql = String.valueOf(sql) + "\r\n and b.업무구분='2'";
        }
        sql = String.valueOf(sql) + " and rownum <=15";
        return sql;
    }
}
