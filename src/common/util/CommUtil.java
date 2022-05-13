package common.util; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringEscapeUtils;

import common.Log;
import common.Trx;

public class CommUtil
{
	private static final String CERT_PREFIX = "DT-";
	
	public static String getCERT_PREFIX() {
		return CERT_PREFIX;
	}
	/**
	 *	넘겨받은 String Array 에 Null 이 하나라도 존재하면 Exception을 던진다.
	 */
	public static String[] retNullParameterCheck(String[] s) throws Exception{
		
		if(s ==null) return null;
	
		for(int i=0,n=s.length;i<n;i++){
			if(s[i] == null || s[i].equals("")){
			
				for(int j=0,m=s.length;j<m;j++){
					Log.debug(j+":"+s[j]);
				}	
				//throw new Exception("인자값에 대한 점검이 필요합니다.");	
				throw new Exception("Cần kiểm tra giá trị.");	
			}
		}
		
		return s;
	}
	
	
	
	/**
     * @param   String strOriginal		원본 String
     * @param   String fromStr			from 문자열
     * @param   String toStr    		to 문자열
     * @return  String           		치환이 끝난 문자열
     *
     */
    public static String newReplace(String strOriginal, String fromStr, String toStr){
        
        if (strOriginal == null || strOriginal.length()==0 || fromStr == null || fromStr.length()==0 || toStr == null){
            return strOriginal;
		}
		
        StringBuffer dest = new StringBuffer("");
        
        int  len = fromStr.length();
        int  srclen = strOriginal.length();
        
        int  pos = 0;
        int  oldpos = 0;

        while ((pos = strOriginal.indexOf(fromStr, oldpos)) >= 0) {
            dest.append(strOriginal.substring(oldpos, pos));
            dest.append(toStr);
            oldpos = pos + len;
        }

        if (oldpos < srclen){
            dest.append(strOriginal.substring(oldpos, srclen));
		}

        return dest.toString();
    }


	/**
	*	null 인지 "" 인지를 체크하여 Null 을 리턴
	*/
	public String retNull(String s){
		try{
			if(s == null || s.equals("")) s=null;
		}catch(Exception e){
			Log.debug("CommUtil.retNull()" + e.toString() );
		}

		return s;
	}

	public String StandardPhonNumber(String pNum){		

	  String firstNum="";			//	전화번호 첫번째 자리
	  String middleNum="";		// 전화번호 두번째 자리
	  String lastNum="";			// 전화번호 세번째 자리
	  String fullNum = "";				// 전체 전화번호

		try { 
			StringTokenizer st = new StringTokenizer(pNum, "-");
			if (st.countTokens() == 3) {
				firstNum = pNum.substring(0, pNum.indexOf("-"));
				middleNum = pNum.substring(pNum.indexOf("-")+1, pNum.lastIndexOf("-"));
				lastNum = pNum.substring(pNum.lastIndexOf("-")+1);
				fullNum = firstNum + middleNum + lastNum ;

		return fullNum;			 
			} else if (st.countTokens() == 2) { 
				firstNum  = pNum.substring(0, pNum.indexOf("-"));
				middleNum = pNum.substring(pNum.indexOf("-")+1);
				lastNum   = "";
				fullNum = firstNum + middleNum + lastNum ;
		return fullNum;			 
			} else if (st.countTokens() == 1 || st.countTokens() == 0 ) { 
				firstNum  = pNum;
				middleNum = "";
				lastNum   = "";
				fullNum = firstNum + middleNum + lastNum ;
		return fullNum;			 
			} else { 
				firstNum = pNum.substring(0, pNum.indexOf("-"));
				middleNum = pNum.substring(pNum.indexOf("-")+1, pNum.lastIndexOf("-"));
				lastNum = pNum.substring(pNum.lastIndexOf("-")+1);
				fullNum = firstNum + middleNum + lastNum ;
		return fullNum;			 
			}
		} catch (Exception e) {
			Log.debug("CommUtil.StandardPhonNumber()" + e.toString() );
		}
		return fullNum;			 
	}
	/*
	* null 인지 "" 인지를 체크하여 Null 을 리턴 - 배열인 경우
	*/
	public String[] retNulls(String s[]){
		try{
			if(s != null){
				for(int i=0;i<s.length;i++){
					if(s[i] == null || s[i].equals(""))
						s[i]=null;
				}
			}
		}catch(Exception e){
			Log.debug("CommUtil.retNulls()" + e.toString() );
		}
		return s;
	}

	/**
	*	null 인지 "" 인지를 체크하여 "" 을 리턴
	*/
	public static String retSpace(String s){
		try{
			if(s == null || s.equals("") || s.equals(null) || s == "")
			s="";
		}catch(Exception e){
			Log.debug("CommUtil.retSpace()" + e.toString() );
		}
		return s;
	}

	/**
	*	str의 from 을 to로 바꾸어준다.
	*/
	public String replace(String  source, String from , String to) {

		if(from.equals("")) return source;

		StringBuffer sb = new StringBuffer(source);
		replace(sb, from, to);
		return sb.toString();
	}

	private int replace(StringBuffer sb, String from, String to) {
		if(sb == null)		return 0;
		if(from == null)	return 0;
		if(to == null)		return 0;
		int count =0;
		while (true)
		{
			String s = new String(sb);
			int ns = s.indexOf(from);
			int ne = ns +  from.length();

			s = null;
			if (ns == -1) break;
			count ++;
			sb.replace(ns, ne, to);
		}
		return count;
	}
	
	
	
	
	
	

	/**
	*	사업자등록번호에 - 를 넣어 리턴한다.
	*/
	public static String insert_minus_saupno(String saup_no0){

		String saup_no  = "";
		String saup_no1 = "";
		String saup_no2 = "";
		String saup_no3 = "";

		if(saup_no0.length() == 10)
		{
			saup_no1 = saup_no0.substring(0, 3);
			saup_no2 = saup_no0.substring(3, 5);
			saup_no3 = saup_no0.substring(5, 10);

			saup_no = saup_no1 + "-" + saup_no2 + "-" + saup_no3;

			return saup_no;

		} else if (saup_no0.equals("")) {

			saup_no = "";

			return saup_no;

		} else {

			saup_no = saup_no0;

			return saup_no;
		}
	}
	 //  String MakeComma
    public static String MakeComma(String gve_money) {
        String result = "";
        String psTmp1="", psTmp2="";

        try {
            gve_money=eraseComma(gve_money);

            if(gve_money==null || gve_money.equals("")) {
              return result="";
            }

            NumberFormat nf = NumberFormat.getInstance();

            int point=gve_money.indexOf(".");
            if(point>0) {
              psTmp1 = gve_money.substring(0,point);
              psTmp2 = gve_money.substring(point);
              psTmp1 = nf.format(Double.parseDouble(psTmp1));
              result = psTmp1.trim() + psTmp2.trim();
            } else {
              result = nf.format(Double.parseDouble(gve_money)).trim();
            }
        }catch(Exception e) {
        }
        return result;
    }

	 public static void close(Connection conn) {
        try {
            conn.close();
        }catch(Exception exc){}
    }

    public static void close(Statement stmt) {
        try {
            stmt.close();
        }catch(Exception exc){}
    }

    public static void close(PreparedStatement stmt) {
        try {
            stmt.close();
        }catch(Exception exc){}
    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        }catch(Exception exc){}
    }

	public static void close(Trx tr) {
	        try {
	            tr.close();
	        }catch(Exception exc){}
	    }
    public static String eraseBusiMark(String  gve_busi) {
        String result = "";
        try {
            for(int i=0;i<gve_busi.length();i++) {
              char c = gve_busi.charAt(i);
              if ( c != '-' ) {
                result += c;
              }
            }
        }catch(Exception e) {
        }

        return result;
    }
    public static String editBusiMask(String gve_busi) {
        String result = "";

        try {
            gve_busi=eraseBusiMark(gve_busi);
            for(int i=0;i<gve_busi.length();i++) {
              result += gve_busi.charAt(i);
              if ( (i==2) || (i==4)) {
                result += "-";
              }
            }
        }catch(Exception e) {
        }

        return result;
    }
	/* 널체크 후 공백 또는 str 값을 리턴 한다.
     * param : str
     * return: "" or str
     */
    public static String isNull(String str) {
        return str==null?"":str;
    }

	/* 널체크 후 str 또는 rtn 값을 리턴 한다.
     * param : str, rtn
     * return: str or rtn
     */
    public static String isNull(String str, String rtn) {
        return str==null?rtn:str;
    }
	/* String 값을 Integer 값으로 리턴 한다.
     * param : str
     * return: int or 0
     */
    public static int isInteger(String str) {
        int piTmp=0;
        try {
            piTmp=Integer.parseInt(str);
        }catch(Exception exc){
            piTmp=0;
        }
        return piTmp;
    }
  public static String getNextPageIndexes( String url, String str_totalRow
                                          ,String str_pageSize, String str_pageNo) {
        String result = "";

        int totalRow  = CommUtil.isInteger(str_totalRow);
        int page_size = CommUtil.isInteger(str_pageSize);
        int page_no   = CommUtil.isInteger(str_pageNo);

		if( totalRow <= page_size )
		  return result;

		int	startPage	= ( ( ( (page_no % 10) == 0) ? page_no-1 : page_no) / 10) * 10 + 1;
		int	totalPage	= (totalRow / page_size) + ( ((totalRow % page_size) == 0) ? 0 : 1);
		int	endPage		= ( (totalPage - startPage) >= 10 ) ? (startPage + 9) : totalPage;


      	result += "<table width=100% border=0 cellpadding=2 cellspacing=2 class=tr>"
		+"<tr><td align=center background=/img/dotlines.gif height=1></td></tr><tr>"
		+"<td align=center class=redc>";
		if( page_no > 10 ) {
		  result += "<a href='" + url + "&page_no=" + (startPage-1) + "'><img align=absmiddle src=/img/bu_backarw.gif border=0></a>";
		} else {
		  if (page_no > 1){
			  result += "<img align=absmiddle src=/img/bu_backarwn.gif border=0>";
		  } else {
			  result += "<img align=absmiddle src=/img/bu_backarwn.gif border=0>";
		  }
		}
		for( int i= startPage; i <= endPage; i++ ) {
		  	if (page_no == i){
				result += 	"["+i+"]";
		  	} else {
				result += 	"<a href='" + url + "&page_no="+i+"'>["+i+"]</a>";
			}
		}
		if( endPage != totalPage) {
		  result += "<a href='" + url + "&page_no=" + (endPage+1) + "'><img align=absmiddle src=/img/bu_nextarw.gif border=0></a><a href='" + url + "&page_no=" + (endPage+1) + "'></a>";
		} else {
		  if (page_no < endPage) {
			  result += "<img align=absmiddle src=/img/bu_nextarwn.gif border=0>";
		  } else {
			  result += "<img align=absmiddle src=/img/bu_nextarwn.gif border=0>";
		  }
		}
		result += "</td></tr><tr><td align=center background=/img/dotlines.gif height=1></td></tr></table>";

        return result;
    }
    public static String eraseComma(String  gve_money) {
      String result = "";
      
      try {
          for(int i=0;i<gve_money.length();i++) {
            char c = gve_money.charAt(i);
            if ( c != ',' ) {
              result += c;
            }
          }
      }catch(Exception e) {
      }
      return result;
    }
    
    public static String retNullObject(Object obj){
    	return obj==null?"":obj.toString();
    }
    
    public static String escapeHtml(String str){
		if(str==null ){
			str="";
		}
		return StringEscapeUtils.escapeHtml(str);
	}
	
	private static char[] SPECIAL_CHARACTERS = { ' ', '!', '"', '#', '$', '%',
			'*', '+', ',', ':', '<', '=', '>', '?', '@', '[', '\\', ']', '^',
			'`', '|', '~', 'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò',
			'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê',
			'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ă', 'ă', 'Đ', 'đ',
			'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ',
			'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ', 'Ắ', 'ắ', 'Ằ', 'ằ',
			'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế',
			'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị',
			'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ', 'ổ', 'Ỗ', 'ỗ', 'Ộ',
			'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ',
			'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự', };

	private static char[] REPLACEMENTS = { ' ', '\0', '\0', '\0', '\0', '\0',
			'\0', '_', '\0', '_', '\0', '\0', '\0', '\0', '\0', '\0', '_',
			'\0', '\0', '\0', '\0', '\0', 'A', 'A', 'A', 'A', 'E', 'E', 'E',
			'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a', 'a', 'a',
			'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y', 'A',
			'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a',
			'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
			'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E', 'e',
			'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'I',
			'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
			'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
			'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
			'U', 'u', };

	public static String toUrlFriendly(String s) {
		int maxLength = Math.min(s.length(), 236);
		char[] buffer = new char[maxLength];
		int n = 0;
		for (int i = 0; i < maxLength; i++) {
			char ch = s.charAt(i);
			buffer[n] = removeAccent(ch);
			// skip not printable characters
			if (buffer[n] > 31) {
				n++;
			}
		}
		// skip trailing slashes
		while (n > 0 && buffer[n - 1] == '/') {
			n--;
		}
		return String.valueOf(buffer, 0, n);
	}

	private static char removeAccent(char ch) {
		int index = Arrays.binarySearch(SPECIAL_CHARACTERS, ch);
		if (index >= 0) {
			ch = REPLACEMENTS[index];
		}
		return ch;
	}
	
	public static String removeAccent(String s) {
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < sb.length(); i++) {
			sb.setCharAt(i, removeAccent(sb.charAt(i)));
		}
		return sb.toString();
	}
	
	public static String stringReplace(String str)
    {

        String str_imsi = "";

        //\ / : ? " < > | * $ _ + , ' ` = ; #
        //! % ( ) - @ ^  { } ~
        		
        /*String[] filter_word = { "", "\\.", "\\?", "\\/", "\\~", "\\!", "\\@",
                "\\#", "\\$", "\\%", "\\^", "\\&", "\\*", "\\(", "\\)", "\\_",
                "\\+", "\\=", "\\|", "\\\\", "\\}", "\\]", "\\{", "\\[",
                "\\\"", "\\'", "\\:", "\\;", "\\<", "\\,", "\\>", "\\.", "\\?",
                "\\/" };*/
        
        String[] filter_word = { "\\?", "\\/", "\\#", "\\$", 
                                "\\*", "\\_", "\\+", "\\=", 
                                "\\|", "\\\\", "\\}", "\\{", "\\\"", 
                                "\\'", "\\:", "\\;", "\\<", 
                                "\\,", "\\>", "\\?", "\\/",
                                "\\`","\\[", "\\]"};
        
        for (int i = 0; i < filter_word.length; i++)
        {
            str_imsi = str.replaceAll(filter_word[i], "");
            str = str_imsi;
        }
        return str;
    }
}
