// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class Search extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2         /* res */
        //     1: ldc             "text/html;charset=UTF-8"
        //     3: invokeinterface javax/servlet/http/HttpServletResponse.setContentType:(Ljava/lang/String;)V
        //     8: aload_2         /* res */
        //     9: invokeinterface javax/servlet/http/HttpServletResponse.getWriter:()Ljava/io/PrintWriter;
        //    14: astore_3        /* out */
        //    15: aload_0         /* this */
        //    16: aload_1         /* req */
        //    17: ldc             "mulCode"
        //    19: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //    24: invokespecial   servlets/Search.retNull:(Ljava/lang/String;)Ljava/lang/String;
        //    27: astore          mulCode
        //    29: aload_0         /* this */
        //    30: aload_1         /* req */
        //    31: ldc             "mulName"
        //    33: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //    38: invokespecial   servlets/Search.retNull:(Ljava/lang/String;)Ljava/lang/String;
        //    41: astore          mulName
        //    43: aload_0         /* this */
        //    44: aload_1         /* req */
        //    45: ldc             "gubun"
        //    47: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //    52: invokespecial   servlets/Search.retNull:(Ljava/lang/String;)Ljava/lang/String;
        //    55: astore          gubun
        //    57: aload_0         /* this */
        //    58: aload_1         /* req */
        //    59: ldc             "ABgubun"
        //    61: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //    66: invokespecial   servlets/Search.retNull:(Ljava/lang/String;)Ljava/lang/String;
        //    69: astore          ABgubun
        //    71: aload           gubun
        //    73: ifnonnull       80
        //    76: ldc             "1"
        //    78: astore          gubun
        //    80: aload           ABgubun
        //    82: ifnonnull       89
        //    85: ldc             "1"
        //    87: astore          ABgubun
        //    89: aload           mulCode
        //    91: ifnonnull       98
        //    94: ldc             ""
        //    96: astore          mulCode
        //    98: aload           mulName
        //   100: ifnonnull       107
        //   103: ldc             ""
        //   105: astore          mulName
        //   107: aload           gubun
        //   109: ifnonnull       116
        //   112: ldc             ""
        //   114: astore          gubun
        //   116: aload           ABgubun
        //   118: ifnonnull       125
        //   121: ldc             ""
        //   123: astore          ABgubun
        //   125: iconst_0       
        //   126: istore          flag
        //   128: aload           mulCode
        //   130: invokevirtual   java/lang/String.length:()I
        //   133: iconst_1       
        //   134: if_icmpgt       146
        //   137: aload           mulName
        //   139: invokevirtual   java/lang/String.length:()I
        //   142: iconst_1       
        //   143: if_icmple       149
        //   146: iconst_1       
        //   147: istore          flag
        //   149: aload_0         /* this */
        //   150: aload_1         /* req */
        //   151: ldc             "a"
        //   153: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   158: invokespecial   servlets/Search.retNull:(Ljava/lang/String;)Ljava/lang/String;
        //   161: astore          a
        //   163: aload_0         /* this */
        //   164: aload_1         /* req */
        //   165: ldc             "b"
        //   167: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   172: invokespecial   servlets/Search.retNull:(Ljava/lang/String;)Ljava/lang/String;
        //   175: astore          b
        //   177: aload           b
        //   179: ifnonnull       186
        //   182: ldc             ""
        //   184: astore          b
        //   186: aload_0         /* this */
        //   187: aload_1         /* req */
        //   188: ldc             "formName"
        //   190: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   195: invokespecial   servlets/Search.retNull:(Ljava/lang/String;)Ljava/lang/String;
        //   198: astore          formName
        //   200: aload           a
        //   202: ifnull          210
        //   205: aload           formName
        //   207: ifnonnull       217
        //   210: aload_3         /* out */
        //   211: ldc             "Ph\u01b0\u01a1ng ph\u00e1p s\u1eed d\u1ee5ng ch\u01b0\u01a1ng tr\u00ecnh kh\u00f4ng \u0111\u00fang. H\u00e3y \u0111\u1ecdc ph\u01b0\u01a1ng ph\u00e1p v\u00e0 \u00e1p d\u1ee5ng ch\u00ednh x\u00e1c."
        //   213: invokevirtual   java/io/PrintWriter.println:(Ljava/lang/String;)V
        //   216: return         
        //   217: aload_0         /* this */
        //   218: aload_1         /* req */
        //   219: ldc             "page_no"
        //   221: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   226: invokespecial   servlets/Search.retNull:(Ljava/lang/String;)Ljava/lang/String;
        //   229: astore          page_no
        //   231: aload           page_no
        //   233: ifnonnull       240
        //   236: ldc             "1"
        //   238: astore          page_no
        //   240: ldc             "10"
        //   242: astore          page_size
        //   244: new             Ljava/lang/StringBuffer;
        //   247: dup            
        //   248: ldc             "/servlet/servlets/Search?gubun="
        //   250: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   253: aload           gubun
        //   255: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   258: ldc             "&mulCode="
        //   260: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   263: aload           mulCode
        //   265: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   268: ldc             "&mulName="
        //   270: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   273: aload           mulName
        //   275: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   278: ldc             "&a="
        //   280: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   283: aload           a
        //   285: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   288: ldc             "&b="
        //   290: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   293: aload           b
        //   295: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   298: ldc             "&formName="
        //   300: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   303: aload           formName
        //   305: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   308: ldc             "&ABgubun="
        //   310: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   313: aload           ABgubun
        //   315: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   318: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   321: astore          url
        //   323: iconst_0       
        //   324: istore          totalCount
        //   326: aconst_null    
        //   327: astore          conn
        //   329: aconst_null    
        //   330: astore          pstm
        //   332: aconst_null    
        //   333: astore          rest
        //   335: aconst_null    
        //   336: astore          resource
        //   338: iconst_0       
        //   339: istore          pstm_inx
        //   341: ldc             ""
        //   343: astore          sql
        //   345: ldc             ""
        //   347: astore          sqlCount
        //   349: ldc             ""
        //   351: astore          sqlWhere
        //   353: ldc             ""
        //   355: astore          sqlOrder
        //   357: aload           gubun
        //   359: ldc             "1"
        //   361: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   364: ifne            377
        //   367: aload           gubun
        //   369: ldc             "2"
        //   371: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   374: ifeq            392
        //   377: ldc             " SELECT COUNT(*) FROM SYN_VIEW_\ubb3c\ud488\ubd84\ub958\ub9e4\ud551\t"
        //   379: astore          sqlCount
        //   381: ldc             " SELECT RNUM, \ubb3c\ud488\ubd84\ub958, \uc815\ubd80\ubb3c\ud488\ubd84\ub958\ubc88\ud638, \ubd84\ub958\uba85, \ud488\uba85\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  FROM (SELECT \ubb3c\ud488\ubd84\ub958, \uc815\ubd80\ubb3c\ud488\ubd84\ub958\ubc88\ud638, \ubd84\ub958\uba85, \ud488\uba85, ROWNUM RNUM\t\t\t\t\t\t\t\t\t\t\t FROM (SELECT \ubb3c\ud488\ubd84\ub958, \uc815\ubd80\ubb3c\ud488\ubd84\ub958\ubc88\ud638, \ubd84\ub958\uba85, \ud488\uba85 FROM SYN_VIEW_\ubb3c\ud488\ubd84\ub958\ub9e4\ud551\t\t"
        //   383: astore          sql
        //   385: ldc             " ORDER BY \ubb3c\ud488\ubd84\ub958 "
        //   387: astore          sqlOrder
        //   389: goto            400
        //   392: ldc             " SELECT COUNT(*) FROM syn_\ubb3c\ud488\ubd84\ub958\ubcc0\uacbd\uc774\ub825 "
        //   394: astore          sqlCount
        //   396: ldc             " SELECT RNUM, \ubcc0\uacbd\uc804\ubd84\ub958\ucf54\ub4dc, \ubcc0\uacbd\ud6c4\ubd84\ub958\ucf54\ub4dc, \ubcc0\uacbd\uc804\ubd84\ub958\uba85, \ubcc0\uacbd\ud6c4\ubd84\ub958\uba85\t\t\t\t\t\t\t\t\t\t\t\t\t  FROM (SELECT \ubcc0\uacbd\uc804\ubd84\ub958\ucf54\ub4dc, \ubcc0\uacbd\ud6c4\ubd84\ub958\ucf54\ub4dc, \ubcc0\uacbd\uc804\ubd84\ub958\uba85, \ubcc0\uacbd\ud6c4\ubd84\ub958\uba85, ROWNUM RNUM\t\t\t\t\t\t\t       FROM (SELECT \ubcc0\uacbd\uc804\ubd84\ub958\ucf54\ub4dc, \ubcc0\uacbd\ud6c4\ubd84\ub958\ucf54\ub4dc, \ubcc0\uacbd\uc804\ubd84\ub958\uba85, \ubcc0\uacbd\ud6c4\ubd84\ub958\uba85 FROM syn_\ubb3c\ud488\ubd84\ub958\ubcc0\uacbd\uc774\ub825   "
        //   398: astore          sql
        //   400: aload           gubun
        //   402: ldc             "1"
        //   404: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   407: ifeq            473
        //   410: aload           mulCode
        //   412: ldc             ""
        //   414: if_acmpeq       431
        //   417: aload           mulName
        //   419: ldc             ""
        //   421: if_acmpeq       431
        //   424: ldc             " WHERE \ubb3c\ud488\ubd84\ub958 LIKE ?||'%' AND \ubd84\ub958\uba85 LIKE '%'||?||'%' AND \uc0ac\uc6a9\uc5ec\ubd80 NOT IN ('N')    AND SUBSTR(\ubb3c\ud488\ubd84\ub958, 1, 2)   NOT IN ('70','71','72','73','76','77','78','80','81','82','83','84','85','86','90','91','92','93','94','95')    AND NVL(TO_CHAR(STARTDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')    AND NVL(TO_CHAR(ENDDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) >= TO_CHAR(SYSDATE, 'YYYY-MM-DD') "
        //   426: astore          sqlWhere
        //   428: goto            697
        //   431: aload           mulCode
        //   433: ldc             ""
        //   435: if_acmpeq       452
        //   438: aload           mulName
        //   440: ldc             ""
        //   442: if_acmpne       452
        //   445: ldc             " WHERE \ubb3c\ud488\ubd84\ub958 LIKE ?||'%' AND \uc0ac\uc6a9\uc5ec\ubd80 NOT IN ('N')    AND SUBSTR(\ubb3c\ud488\ubd84\ub958, 1, 2)   NOT IN ('70','71','72','73','76','77','78','80','81','82','83','84','85','86','90','91','92','93','94','95')    AND NVL(TO_CHAR(STARTDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')    AND NVL(TO_CHAR(ENDDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) >= TO_CHAR(SYSDATE, 'YYYY-MM-DD') "
        //   447: astore          sqlWhere
        //   449: goto            697
        //   452: aload           mulCode
        //   454: ldc             ""
        //   456: if_acmpne       697
        //   459: aload           mulName
        //   461: ldc             ""
        //   463: if_acmpeq       697
        //   466: ldc             " WHERE \ubd84\ub958\uba85 LIKE '%'||?||'%' AND \uc0ac\uc6a9\uc5ec\ubd80 NOT IN ('N')    AND SUBSTR(\ubb3c\ud488\ubd84\ub958, 1, 2)   NOT IN ('70','71','72','73','76','77','78','80','81','82','83','84','85','86','90','91','92','93','94','95')    AND NVL(TO_CHAR(STARTDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')    AND NVL(TO_CHAR(ENDDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) >= TO_CHAR(SYSDATE, 'YYYY-MM-DD') "
        //   468: astore          sqlWhere
        //   470: goto            697
        //   473: aload           gubun
        //   475: ldc             "2"
        //   477: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   480: ifeq            546
        //   483: aload           mulCode
        //   485: ldc             ""
        //   487: if_acmpeq       504
        //   490: aload           mulName
        //   492: ldc             ""
        //   494: if_acmpeq       504
        //   497: ldc             " WHERE \uc815\ubd80\ubb3c\ud488\ubd84\ub958\ubc88\ud638 LIKE ?||'%' AND \ud488\uba85 LIKE '%'||?||'%' AND \uc0ac\uc6a9\uc5ec\ubd80 NOT IN ('N')    AND SUBSTR(\ubb3c\ud488\ubd84\ub958, 1, 2)   NOT IN ('70','71','72','73','76','77','78','80','81','82','83','84','85','86','90','91','92','93','94','95')    AND NVL(TO_CHAR(STARTDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')    AND NVL(TO_CHAR(ENDDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) >= TO_CHAR(SYSDATE, 'YYYY-MM-DD') "
        //   499: astore          sqlWhere
        //   501: goto            697
        //   504: aload           mulCode
        //   506: ldc             ""
        //   508: if_acmpeq       525
        //   511: aload           mulName
        //   513: ldc             ""
        //   515: if_acmpne       525
        //   518: ldc             " WHERE \uc815\ubd80\ubb3c\ud488\ubd84\ub958\ubc88\ud638 LIKE ?||'%' AND \uc0ac\uc6a9\uc5ec\ubd80 NOT IN ('N')    AND SUBSTR(\ubb3c\ud488\ubd84\ub958, 1, 2)   NOT IN ('70','71','72','73','76','77','78','80','81','82','83','84','85','86','90','91','92','93','94','95')    AND NVL(TO_CHAR(STARTDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')    AND NVL(TO_CHAR(ENDDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) >= TO_CHAR(SYSDATE, 'YYYY-MM-DD') "
        //   520: astore          sqlWhere
        //   522: goto            697
        //   525: aload           mulCode
        //   527: ldc             ""
        //   529: if_acmpne       697
        //   532: aload           mulName
        //   534: ldc             ""
        //   536: if_acmpeq       697
        //   539: ldc             " WHERE \ud488\uba85 LIKE '%'||?||'%' AND \uc0ac\uc6a9\uc5ec\ubd80 NOT IN ('N')    AND SUBSTR(\ubb3c\ud488\ubd84\ub958, 1, 2)   NOT IN ('70','71','72','73','76','77','78','80','81','82','83','84','85','86','90','91','92','93','94','95')    AND NVL(TO_CHAR(STARTDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')    AND NVL(TO_CHAR(ENDDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) >= TO_CHAR(SYSDATE, 'YYYY-MM-DD') "
        //   541: astore          sqlWhere
        //   543: goto            697
        //   546: aload           gubun
        //   548: ldc             "3"
        //   550: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   553: ifeq            697
        //   556: aload           ABgubun
        //   558: ldc             "1"
        //   560: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   563: ifeq            633
        //   566: aload           mulCode
        //   568: ldc             ""
        //   570: if_acmpeq       587
        //   573: aload           mulName
        //   575: ldc             ""
        //   577: if_acmpeq       587
        //   580: ldc             " WHERE \ubcc0\uacbd\uc804\ubd84\ub958\ucf54\ub4dc LIKE ?||'%' AND \ubcc0\uacbd\uc804\ubd84\ub958\uba85 LIKE '%'||?||'%' AND \uc0ac\uc6a9\uc5ec\ubd80 = 'Y'    AND SUBSTR(\ubcc0\uacbd\uc804\ubd84\ub958\ucf54\ub4dc, 1, 2)   NOT IN ('70','71','72','73','76','77','78','80','81','82','83','84','85','86','90','91','92','93','94','95')    AND NVL(TO_CHAR(STARTDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')    AND NVL(TO_CHAR(ENDDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) >= TO_CHAR(SYSDATE, 'YYYY-MM-DD') "
        //   582: astore          sqlWhere
        //   584: goto            626
        //   587: aload           mulCode
        //   589: ldc             ""
        //   591: if_acmpeq       608
        //   594: aload           mulName
        //   596: ldc             ""
        //   598: if_acmpne       608
        //   601: ldc             " WHERE \ubcc0\uacbd\uc804\ubd84\ub958\ucf54\ub4dc LIKE ?||'%' AND \uc0ac\uc6a9\uc5ec\ubd80 = 'Y'    AND SUBSTR(\ubcc0\uacbd\uc804\ubd84\ub958\ucf54\ub4dc, 1, 2)   NOT IN ('70','71','72','73','76','77','78','80','81','82','83','84','85','86','90','91','92','93','94','95')    AND NVL(TO_CHAR(STARTDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')    AND NVL(TO_CHAR(ENDDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) >= TO_CHAR(SYSDATE, 'YYYY-MM-DD') "
        //   603: astore          sqlWhere
        //   605: goto            626
        //   608: aload           mulCode
        //   610: ldc             ""
        //   612: if_acmpne       626
        //   615: aload           mulName
        //   617: ldc             ""
        //   619: if_acmpeq       626
        //   622: ldc             " WHERE \ubcc0\uacbd\uc804\ubd84\ub958\uba85 LIKE '%'||?||'%' AND \uc0ac\uc6a9\uc5ec\ubd80 = 'Y'    AND SUBSTR(\ubcc0\uacbd\uc804\ubd84\ub958\ucf54\ub4dc, 1, 2)   NOT IN ('70','71','72','73','76','77','78','80','81','82','83','84','85','86','90','91','92','93','94','95')    AND NVL(TO_CHAR(STARTDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')    AND NVL(TO_CHAR(ENDDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) >= TO_CHAR(SYSDATE, 'YYYY-MM-DD') "
        //   624: astore          sqlWhere
        //   626: ldc             " ORDER BY \ubcc0\uacbd\uc804\ubd84\ub958\uba85 "
        //   628: astore          sqlOrder
        //   630: goto            697
        //   633: aload           mulCode
        //   635: ldc             ""
        //   637: if_acmpeq       654
        //   640: aload           mulName
        //   642: ldc             ""
        //   644: if_acmpeq       654
        //   647: ldc             " WHERE \ubcc0\uacbd\ud6c4\ubd84\ub958\ucf54\ub4dc LIKE ?||'%' AND \ubcc0\uacbd\ud6c4\ubd84\ub958\uba85 LIKE '%'||?||'%'  AND \uc0ac\uc6a9\uc5ec\ubd80 = 'Y'    AND SUBSTR(\ubcc0\uacbd\ud6c4\ubd84\ub958\ucf54\ub4dc, 1, 2)   NOT IN ('70','71','72','73','76','77','78','80','81','82','83','84','85','86','90','91','92','93','94','95')    AND NVL(TO_CHAR(STARTDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')    AND NVL(TO_CHAR(ENDDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) >= TO_CHAR(SYSDATE, 'YYYY-MM-DD') "
        //   649: astore          sqlWhere
        //   651: goto            693
        //   654: aload           mulCode
        //   656: ldc             ""
        //   658: if_acmpeq       675
        //   661: aload           mulName
        //   663: ldc             ""
        //   665: if_acmpne       675
        //   668: ldc             " WHERE \ubcc0\uacbd\ud6c4\ubd84\ub958\ucf54\ub4dc LIKE ?||'%' AND \uc0ac\uc6a9\uc5ec\ubd80 = 'Y'   AND SUBSTR(\ubcc0\uacbd\ud6c4\ubd84\ub958\ucf54\ub4dc, 1, 2)   NOT IN ('70','71','72','73','76','77','78','80','81','82','83','84','85','86','90','91','92','93','94','95')    AND NVL(TO_CHAR(STARTDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')    AND NVL(TO_CHAR(ENDDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) >= TO_CHAR(SYSDATE, 'YYYY-MM-DD') "
        //   670: astore          sqlWhere
        //   672: goto            693
        //   675: aload           mulCode
        //   677: ldc             ""
        //   679: if_acmpne       693
        //   682: aload           mulName
        //   684: ldc             ""
        //   686: if_acmpeq       693
        //   689: ldc             " WHERE \ubcc0\uacbd\ud6c4\ubd84\ub958\uba85 LIKE '%'||?||'%'  AND \uc0ac\uc6a9\uc5ec\ubd80 = 'Y'   AND SUBSTR(\ubcc0\uacbd\ud6c4\ubd84\ub958\ucf54\ub4dc, 1, 2)   NOT IN ('70','71','72','73','76','77','78','80','81','82','83','84','85','86','90','91','92','93','94','95')    AND NVL(TO_CHAR(STARTDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')    AND NVL(TO_CHAR(ENDDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD')) >= TO_CHAR(SYSDATE, 'YYYY-MM-DD') "
        //   691: astore          sqlWhere
        //   693: ldc             " ORDER BY \ubcc0\uacbd\ud6c4\ubd84\ub958\uba85 "
        //   695: astore          sqlOrder
        //   697: new             Ljava/lang/StringBuffer;
        //   700: dup            
        //   701: aload           sqlCount
        //   703: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   706: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   709: aload           sqlWhere
        //   711: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   714: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   717: astore          sqlCount
        //   719: new             Ljava/lang/StringBuffer;
        //   722: dup            
        //   723: aload           sql
        //   725: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   728: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   731: aload           sqlWhere
        //   733: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   736: aload           sqlOrder
        //   738: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   741: ldc             ")) WHERE RNUM > "
        //   743: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   746: aload           page_size
        //   748: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   751: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   754: ldc             "* ("
        //   756: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   759: aload           page_no
        //   761: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   764: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   767: ldc             "-1) and RNUM < ("
        //   769: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   772: aload           page_size
        //   774: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   777: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   780: ldc             " * "
        //   782: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   785: aload           page_no
        //   787: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   790: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   793: ldc             " + 1)"
        //   795: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   798: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   801: astore          sql
        //   803: aload_0         /* this */
        //   804: aload_3         /* out */
        //   805: aload           mulCode
        //   807: aload           mulName
        //   809: aload           a
        //   811: aload           b
        //   813: aload           formName
        //   815: aload           gubun
        //   817: aload           ABgubun
        //   819: invokespecial   servlets/Search.initHtml:(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   822: iload           flag
        //   824: ifeq            1086
        //   827: new             Lcommon/Trx;
        //   830: dup            
        //   831: aload_0         /* this */
        //   832: ldc             "usemn"
        //   834: invokespecial   common/Trx.<init>:(Ljava/lang/Object;Ljava/lang/String;)V
        //   837: astore          resource
        //   839: aload           resource
        //   841: invokevirtual   common/Trx.getConnection:()Ljava/sql/Connection;
        //   844: astore          conn
        //   846: aload           conn
        //   848: aload           sqlCount
        //   850: invokeinterface java/sql/Connection.prepareStatement:(Ljava/lang/String;)Ljava/sql/PreparedStatement;
        //   855: astore          pstm
        //   857: aload           mulCode
        //   859: ldc             ""
        //   861: if_acmpeq       878
        //   864: iinc            pstm_inx, 1
        //   867: aload           pstm
        //   869: iload           pstm_inx
        //   871: aload           mulCode
        //   873: invokeinterface java/sql/PreparedStatement.setString:(ILjava/lang/String;)V
        //   878: aload           mulName
        //   880: ldc             ""
        //   882: if_acmpeq       899
        //   885: iinc            pstm_inx, 1
        //   888: aload           pstm
        //   890: iload           pstm_inx
        //   892: aload           mulName
        //   894: invokeinterface java/sql/PreparedStatement.setString:(ILjava/lang/String;)V
        //   899: aload           pstm
        //   901: invokeinterface java/sql/PreparedStatement.executeQuery:()Ljava/sql/ResultSet;
        //   906: astore          rest
        //   908: aload           pstm
        //   910: invokeinterface java/sql/PreparedStatement.clearParameters:()V
        //   915: aload           rest
        //   917: invokeinterface java/sql/ResultSet.next:()Z
        //   922: ifeq            935
        //   925: aload           rest
        //   927: iconst_1       
        //   928: invokeinterface java/sql/ResultSet.getInt:(I)I
        //   933: istore          totalCount
        //   935: iload           totalCount
        //   937: ifle            1079
        //   940: aload           pstm
        //   942: ifnull          957
        //   945: aload           pstm
        //   947: invokeinterface java/sql/PreparedStatement.close:()V
        //   952: goto            957
        //   955: astore          25
        //   957: aload           conn
        //   959: aload           sql
        //   961: invokeinterface java/sql/Connection.prepareStatement:(Ljava/lang/String;)Ljava/sql/PreparedStatement;
        //   966: astore          pstm
        //   968: iconst_0       
        //   969: istore          pstm_inx
        //   971: aload           mulCode
        //   973: ldc             ""
        //   975: if_acmpeq       992
        //   978: iinc            pstm_inx, 1
        //   981: aload           pstm
        //   983: iload           pstm_inx
        //   985: aload           mulCode
        //   987: invokeinterface java/sql/PreparedStatement.setString:(ILjava/lang/String;)V
        //   992: aload           mulName
        //   994: ldc             ""
        //   996: if_acmpeq       1013
        //   999: iinc            pstm_inx, 1
        //  1002: aload           pstm
        //  1004: iload           pstm_inx
        //  1006: aload           mulName
        //  1008: invokeinterface java/sql/PreparedStatement.setString:(ILjava/lang/String;)V
        //  1013: aload           rest
        //  1015: ifnull          1030
        //  1018: aload           rest
        //  1020: invokeinterface java/sql/ResultSet.close:()V
        //  1025: goto            1030
        //  1028: astore          25
        //  1030: aload           pstm
        //  1032: invokeinterface java/sql/PreparedStatement.executeQuery:()Ljava/sql/ResultSet;
        //  1037: astore          rest
        //  1039: aload           pstm
        //  1041: invokeinterface java/sql/PreparedStatement.clearParameters:()V
        //  1046: aload_0         /* this */
        //  1047: aload           rest
        //  1049: aload_3         /* out */
        //  1050: iload           totalCount
        //  1052: aload           gubun
        //  1054: invokespecial   servlets/Search.dataHtml:(Ljava/sql/ResultSet;Ljava/io/PrintWriter;ILjava/lang/String;)V
        //  1057: aload_3         /* out */
        //  1058: aload_0         /* this */
        //  1059: aload           url
        //  1061: iload           totalCount
        //  1063: bipush          10
        //  1065: aload           page_no
        //  1067: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //  1070: invokespecial   servlets/Search.getNextPageIndexes:(Ljava/lang/String;III)Ljava/lang/String;
        //  1073: invokevirtual   java/io/PrintWriter.println:(Ljava/lang/String;)V
        //  1076: goto            1086
        //  1079: aload_0         /* this */
        //  1080: aload_3         /* out */
        //  1081: aload           gubun
        //  1083: invokespecial   servlets/Search.noDataHtml:(Ljava/io/PrintWriter;Ljava/lang/String;)V
        //  1086: aload_0         /* this */
        //  1087: aload_3         /* out */
        //  1088: invokespecial   servlets/Search.endHtml:(Ljava/io/PrintWriter;)V
        //  1091: goto            1339
        //  1094: astore          sqle
        //  1096: new             Ljava/lang/StringBuffer;
        //  1099: dup            
        //  1100: ldc             "\ubb3c\ud488\ubd84\ub958\ubc88\ud638\ucc3e\uae30[Search.java] \uc5d0\ub7ec : "
        //  1102: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //  1105: aload           sqle
        //  1107: invokevirtual   java/sql/SQLException.toString:()Ljava/lang/String;
        //  1110: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1113: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //  1116: invokestatic    common/Log.debug:(Ljava/lang/String;)V
        //  1119: new             Ljava/lang/StringBuffer;
        //  1122: dup            
        //  1123: ldc             "\ubb3c\ud488\ucf54\ub4dc["
        //  1125: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //  1128: aload           mulCode
        //  1130: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1133: ldc             "], \ubb3c\ud488\uba85["
        //  1135: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1138: aload           mulName
        //  1140: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1143: ldc             "]"
        //  1145: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1148: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //  1151: invokestatic    common/Log.debug:(Ljava/lang/String;)V
        //  1154: aload_3         /* out */
        //  1155: aload           sqle
        //  1157: invokevirtual   java/sql/SQLException.toString:()Ljava/lang/String;
        //  1160: invokevirtual   java/io/PrintWriter.println:(Ljava/lang/String;)V
        //  1163: aload           rest
        //  1165: ifnull          1180
        //  1168: aload           rest
        //  1170: invokeinterface java/sql/ResultSet.close:()V
        //  1175: goto            1180
        //  1178: astore          27
        //  1180: aload           pstm
        //  1182: ifnull          1197
        //  1185: aload           pstm
        //  1187: invokeinterface java/sql/PreparedStatement.close:()V
        //  1192: goto            1197
        //  1195: astore          27
        //  1197: aload           conn
        //  1199: ifnull          1212
        //  1202: aload           resource
        //  1204: invokevirtual   common/Trx.close:()V
        //  1207: goto            1212
        //  1210: astore          27
        //  1212: return         
        //  1213: astore          ex
        //  1215: new             Ljava/lang/StringBuffer;
        //  1218: dup            
        //  1219: ldc             "\ubb3c\ud488\ubd84\ub958\ubc88\ud638\ucc3e\uae30[Search.java] \uc5d0\ub7ec : "
        //  1221: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //  1224: aload           ex
        //  1226: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //  1229: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1232: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //  1235: invokestatic    common/Log.debug:(Ljava/lang/String;)V
        //  1238: new             Ljava/lang/StringBuffer;
        //  1241: dup            
        //  1242: ldc             "\ubb3c\ud488\ucf54\ub4dc["
        //  1244: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //  1247: aload           mulCode
        //  1249: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1252: ldc             "], \ubb3c\ud488\uba85["
        //  1254: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1257: aload           mulName
        //  1259: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1262: ldc             "]"
        //  1264: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1267: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //  1270: invokestatic    common/Log.debug:(Ljava/lang/String;)V
        //  1273: aload_3         /* out */
        //  1274: aload           ex
        //  1276: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //  1279: invokevirtual   java/io/PrintWriter.println:(Ljava/lang/String;)V
        //  1282: goto            1163
        //  1285: astore          26
        //  1287: aload           rest
        //  1289: ifnull          1304
        //  1292: aload           rest
        //  1294: invokeinterface java/sql/ResultSet.close:()V
        //  1299: goto            1304
        //  1302: astore          27
        //  1304: aload           pstm
        //  1306: ifnull          1321
        //  1309: aload           pstm
        //  1311: invokeinterface java/sql/PreparedStatement.close:()V
        //  1316: goto            1321
        //  1319: astore          27
        //  1321: aload           conn
        //  1323: ifnull          1336
        //  1326: aload           resource
        //  1328: invokevirtual   common/Trx.close:()V
        //  1331: goto            1336
        //  1334: astore          27
        //  1336: aload           26
        //  1338: athrow         
        //  1339: aload           rest
        //  1341: ifnull          1356
        //  1344: aload           rest
        //  1346: invokeinterface java/sql/ResultSet.close:()V
        //  1351: goto            1356
        //  1354: astore          27
        //  1356: aload           pstm
        //  1358: ifnull          1373
        //  1361: aload           pstm
        //  1363: invokeinterface java/sql/PreparedStatement.close:()V
        //  1368: goto            1373
        //  1371: astore          27
        //  1373: aload           conn
        //  1375: ifnull          1388
        //  1378: aload           resource
        //  1380: invokevirtual   common/Trx.close:()V
        //  1383: goto            1388
        //  1386: astore          27
        //  1388: return         
        //    Exceptions:
        //  throws javax.servlet.ServletException
        //  throws java.io.IOException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name        Signature
        //  -----  ------  ----  ----------  ----------------------------------------
        //  0      1389    0     this        Lservlets/Search;
        //  0      1389    1     req         Ljavax/servlet/http/HttpServletRequest;
        //  0      1389    2     res         Ljavax/servlet/http/HttpServletResponse;
        //  15     1374    3     out         Ljava/io/PrintWriter;
        //  29     1360    4     mulCode     Ljava/lang/String;
        //  43     1346    5     mulName     Ljava/lang/String;
        //  57     1332    6     gubun       Ljava/lang/String;
        //  71     1318    7     ABgubun     Ljava/lang/String;
        //  128    1261    8     flag        Z
        //  163    1226    9     a           Ljava/lang/String;
        //  177    1212    10    b           Ljava/lang/String;
        //  200    1189    11    formName    Ljava/lang/String;
        //  231    1158    12    page_no     Ljava/lang/String;
        //  244    1145    13    page_size   Ljava/lang/String;
        //  323    1066    14    url         Ljava/lang/String;
        //  326    1063    15    totalCount  I
        //  329    1060    16    conn        Ljava/sql/Connection;
        //  332    1057    17    pstm        Ljava/sql/PreparedStatement;
        //  335    1054    18    rest        Ljava/sql/ResultSet;
        //  338    1051    19    resource    Lcommon/Trx;
        //  341    1048    20    pstm_inx    I
        //  345    1044    21    sql         Ljava/lang/String;
        //  349    1040    22    sqlCount    Ljava/lang/String;
        //  353    1036    23    sqlWhere    Ljava/lang/String;
        //  357    1032    24    sqlOrder    Ljava/lang/String;
        //  1096   67      25    sqle        Ljava/sql/SQLException;
        //  1215   70      25    ex          Ljava/lang/Exception;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                   
        //  -----  -----  -----  -----  -----------------------
        //  945    952    955    957    Ljava/lang/Exception;
        //  1018   1025   1028   1030   Ljava/lang/Exception;
        //  803    1091   1094   1163   Ljava/sql/SQLException;
        //  1163   1175   1178   1180   Ljava/lang/Exception;
        //  1180   1192   1195   1197   Ljava/lang/Exception;
        //  1197   1207   1210   1212   Ljava/lang/Exception;
        //  803    1091   1213   1285   Ljava/lang/Exception;
        //  803    1163   1285   1339   Any
        //  1213   1285   1285   1339   Any
        //  1287   1299   1302   1304   Ljava/lang/Exception;
        //  1304   1316   1319   1321   Ljava/lang/Exception;
        //  1321   1331   1334   1336   Ljava/lang/Exception;
        //  1339   1351   1354   1356   Ljava/lang/Exception;
        //  1356   1368   1371   1373   Ljava/lang/Exception;
        //  1373   1383   1386   1388   Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 582, Size: 582
        //     at java.util.ArrayList.rangeCheck(Unknown Source)
        //     at java.util.ArrayList.get(Unknown Source)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3417)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:123)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void initHtml(final PrintWriter out, final String mulCode, final String mulName, final String a, final String b, final String formName, final String gubun, final String ABgubun) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title> 물품분류번호찾기</title>");
        out.println("<link rel=STYLESHEET type=text/css\thref='http://www.g2b.go.kr:8070/css/UM.css'>");
        out.println("<script language='javascript' src='http://www.g2b.go.kr:8070/js/EP.js'></script>");
        out.println("<script language='javascript'>");
        out.println("\tfunction search() ");
        out.println("\t{ ");
        out.println("\t\tdocument.ebid.method='post';");
        out.println("\t\tdocument.ebid.action\t='/servlet/servlets/Search';\t");
        out.println("\t\tdocument.ebid.submit();");
        out.println("\t\treturn;");
        out.println("\t}");
        out.println("function toOpener(arg1, arg2, arg3, arg4) {");
        out.println("\topener.document." + formName + "." + a + ".value=arg1;");
        if (!b.equals("")) {
            out.println("\topener.document." + formName + "." + b + ".value=arg2;");
        }
        out.println("\tself.close();");
        out.println("\treturn;");
        out.println("}");
        String temp = "";
        String temp2 = "";
        String temp3 = "";
        String ABtemp1 = "";
        String ABtemp2 = "";
        if (gubun.equals("1") || gubun.equals("")) {
            temp = "checked";
            temp2 = "";
            temp3 = "";
        }
        else if (gubun.equals("2")) {
            temp = "";
            temp2 = "checked";
            temp3 = "";
        }
        else {
            temp = "";
            temp2 = "";
            temp3 = "checked";
        }
        if (ABgubun.equals("1") || ABgubun.equals("")) {
            ABtemp1 = "checked";
            ABtemp2 = "";
        }
        else {
            ABtemp1 = "";
            ABtemp2 = "checked";
        }
        out.println("function changeBase(obj) { ");
        out.println("\tvar TextTemp = \"\"; ");
        out.println("\tif(obj.value == \"1\") { ");
        out.println("\t\tTextTemp = \"<table width=100% border=0 cellspacing=1 cellpadding=2><tr>\" ");
        out.println("\t\t\t\t+ \"<td class='tdar'>물품분류명</td>\" ");
        out.println("\t\t\t\t+ \"<td class='tdb' colspan=2>\" ");
        out.println("\t\t\t\t+ \"     <input type=text name=mulName value='" + mulName.trim() + "'\tsize=30\tmaxlength=30 onkeypress='javascript:if(event.keyCode == 13) search();' class=read></td>\" ");
        out.println("\t\t\t\t+ \"</tr>\" ");
        out.println("\t\t\t\t+ \"<tr>\" ");
        out.println("\t\t\t\t+ \"<td\tclass='tdar'>물품분류번호</td>\" ");
        out.println("\t\t\t\t+ \"<td\tclass='tdb'><input type=text name=mulCode size=8 value='" + mulCode.trim() + "' maxlength=8 onkeypress='javascript:if(event.keyCode == 13) search();' class=read onBlur='javascript:js_NumCheck(this);'></td>\" ");
        out.println("\t\t\t\t+ \"<td\tclass='tdb'><a href='javascript:search();'><img src=/img/bu_refer.gif\tborder=0 align=absmiddle alt='검색'></a></td>\" ");
        out.println("\t\t\t\t+ \"</tr><tr height=2><td colspan=4 class=line></td></tr></table>\" ");
        out.println("\t} ");
        out.println("\telse if(obj.value == \"2\") { ");
        out.println("\t\tTextTemp = \"<table width=100% border=0 cellspacing=1 cellpadding=2><tr>\" ");
        out.println("\t\t\t\t+ \"<td class='tdar'>품명</td>\" ");
        out.println("\t\t\t\t+ \"<td class='tdb' colspan=2>\" ");
        out.println("\t\t\t\t+ \"     <input type=text name=mulName value='" + mulName.trim() + "'\tsize=30\tmaxlength=30 onkeypress='javascript:if(event.keyCode == 13) search();' class=read></td>\" ");
        out.println("\t\t\t\t+ \"</tr>\" ");
        out.println("\t\t\t\t+ \"<tr>\" ");
        out.println("\t\t\t\t+ \"<td\tclass='tdar'>정부물품분류번호</td>\" ");
        out.println("\t\t\t\t+ \"<td\tclass='tdb'><input type=text name=mulCode size=8 value='" + mulCode.trim() + "' maxlength=8 onkeypress='javascript:if(event.keyCode == 13) search();' class=read onBlur='javascript:js_NumCheck(this);'></td>\" ");
        out.println("\t\t\t\t+ \"<td\tclass='tdb'><a href='javascript:search();'><img src=/img/bu_refer.gif\tborder=0 align=absmiddle alt='검색'></a></td>\" ");
        out.println("\t\t\t\t+ \"</tr><tr height=2><td colspan=4 class=line></td></tr></table>\" ");
        out.println("\t} ");
        out.println("\telse { ");
        out.println("\t\tTextTemp = \"<table width=100% border=0 cellspacing=1 cellpadding=2><tr>\" ");
        out.println("\t\t\t\t + \"<td class='tdar'>물품분류명</td>\" ");
        out.println("\t\t\t\t + \"<td class='tdb' colspan=2>\" ");
        out.println("\t\t\t\t + \"     <input type=text name=mulName value='" + mulName.trim() + "'\tsize=30\tmaxlength=30 onkeypress='javascript:if(event.keyCode == 13) search();' class=read></td>\" ");
        out.println("\t\t\t\t + \"</tr>\" ");
        out.println("\t\t\t\t + \"<tr>\" ");
        out.println("\t\t\t\t + \"<td class='tdar'>물품분류번호</td>\" ");
        out.println("\t\t\t\t + \"<td class='tdb'><input type=text name=mulCode size=8 value='" + mulCode.trim() + "' maxlength=8 onkeypress='javascript:if(event.keyCode == 13) search();' class=read onBlur='javascript:js_NumCheck(this);'>\" ");
        out.println("\t\t\t\t + \"<input type=radio name=ABgubun value='1' " + ABtemp1 + ">변경전 물품분류&nbsp;\" ");
        out.println("\t\t\t\t + \"<input type=radio name=ABgubun value='2' " + ABtemp2 + ">변경후 물품분류\" ");
        out.println("\t\t\t\t + \"</td>\" ");
        out.println("\t\t\t\t + \"<td class='tdb'><a href='javascript:search();'><img src=/img/bu_refer.gif\tborder=0 align=absmiddle alt='검색'></a></td>\" ");
        out.println("\t\t\t\t + \"</tr><tr height=2><td colspan=4 class=line></td></tr></table>\" ");
        out.println("\t} ");
        out.println("\tScriptDiv.innerHTML = TextTemp; ");
        out.println(" } ");
        out.println("</script>");
        out.println("</head>");
        out.println("<body background=/img/pop_bg.gif topmargin=0 leftmargin=0 rightmargin=0 style=background-position:left; background-repeat:repeat-y; bgcolor=white class=BODY>");
        out.println("");
        out.println("<table border=0 cellspacing=0 cellpadding=0 height=100% width=100%>");
        out.println("<tr valign=bottom height=57>");
        out.println("<td colspan=3>");
        out.println("");
        out.println("<table width=100% cellpadding=0 cellspacing=0 border=0 bgcolor=white>");
        out.println("<tr height=51 valign=bottom>");
        out.println("<td rowspan=2 width=30></td>");
        out.println("<td width=5><img src='/img/pop_tit_dot.gif'></td>");
        out.println("<td width=* class='HEADLINE' background='' style='background-position:right; background-repeat:no-repeat;'>&nbsp;물품분류번호찾기</td>");
        out.println("</tr><tr height=6>");
        out.println("<td></td>");
        out.println("<td background='/img/pop_tit_b.jpg' style='background-position:right; background-repeat:no-repeat;'></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</td></tr>");
        out.println("<tr height=1><td colspan=3 background='/img/pop_tit_bg.gif'></td></tr>");
        out.println("<tr height=5><td colspan=3></td>");
        out.println("</tr><tr height=20>");
        out.println("<td colspan=3></td>");
        out.println("</tr><tr valign=top>");
        out.println("<td width=30></td><td width=*>");
        out.println("<form name='ebid'>");
        out.println("<table width=100% cellpadding=2 cellspacing=1>");
        out.println("<tr>");
        out.println("<td height=4 class=LINE colspan=2></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td class=tdb align=left>");
        out.println("▷ 변경사항이 있는 물품분류의 변경사항(변경전/후 내역)을 검색하려면 [변경된 물품분류]를 선택하고 검색하십시요.");
        out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td height=2 class=LINE></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("<table width=100% border=0 cellspacing=1 cellpadding=2>");
        out.println("<tr height=4><td colspan=4 class=line></td></tr>");
        out.println("<tr>");
        out.println("<td class='tdar'>구분</td>");
        out.println("<td class='tdb'><input type=radio\tname=gubun value='1' onclick='javascript:changeBase(this)' " + temp + ">물품분류</td>");
        out.println("<td class='tdb'><input type=radio\tname=gubun value='2' onclick='javascript:changeBase(this)' " + temp2 + ">정부물품분류</td>");
        out.println("<td class='tdb'><input type=radio\tname=gubun value='3' onclick='javascript:changeBase(this)' " + temp3 + ">변경된 물품분류</td>");
        out.println("</tr>");
        out.println("<tr height=1><td colspan=4 class=line></td></tr>");
        out.println("</table>");
        out.println("<DIV ID='ScriptDiv'>");
        if (gubun.equals("1") || gubun.equals("")) {
            out.println("<table width=100% border=0 cellspacing=1 cellpadding=2><tr>");
            out.println("<td class='tdar'>물품분류명</td>");
            out.println("<td class='tdb' colspan=2>");
            out.println("     <input type=text name=mulName value='" + mulName.trim() + "'\tsize=30\tmaxlength=30 onkeypress='javascript:if(event.keyCode == 13) search();' class=read></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td\tclass='tdar'>물품분류번호</td>");
            out.println("<td\tclass='tdb'><input type=text name=mulCode size=8 value='" + mulCode.trim() + "' maxlength=8 onkeypress='javascript:if(event.keyCode == 13) search();' class=read onBlur='javascript:js_NumCheck(this);'></td>");
            out.println("<td\tclass='tdb'><a href='javascript:search();'><img src=/img/bu_refer.gif\tborder=0 align=absmiddle alt='검색'></a></td>");
            out.println("</tr><tr height=2><td colspan=4 class=line></td></tr></table>");
        }
        else if (gubun.equals("2")) {
            out.println("<table width=100% border=0 cellspacing=1 cellpadding=2><tr>");
            out.println("<td class='tdar'>품명</td>");
            out.println("<td class='tdb' colspan=2>");
            out.println("     <input type=text name=mulName value='" + mulName.trim() + "'\tsize=30\tmaxlength=30 onkeypress='javascript:if(event.keyCode == 13) search();' class=read></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td\tclass='tdar'>정부물품분류번호</td>");
            out.println("<td\tclass='tdb'><input type=text name=mulCode size=8 value='" + mulCode.trim() + "' maxlength=8 onkeypress='javascript:if(event.keyCode == 13) search();' class=read onBlur='javascript:js_NumCheck(this);'></td>");
            out.println("<td\tclass='tdb'><a href='javascript:search();'><img src=/img/bu_refer.gif\tborder=0 align=absmiddle alt='검색'></a></td>");
            out.println("</tr><tr height=2><td colspan=4 class=line></td></tr></table>");
        }
        else {
            out.println("<table width=100% border=0 cellspacing=1 cellpadding=2><tr>");
            out.println("<td class='tdar'>물품분류명</td>");
            out.println("<td class='tdb' colspan=2>");
            out.println("     <input type=text name=mulName value='" + mulName.trim() + "'\tsize=30\tmaxlength=30 onkeypress='javascript:if(event.keyCode == 13) search();' class=read></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td class='tdar'>물품분류번호</td>");
            out.println("<td class='tdb'><input type=text name=mulCode size=8 value='" + mulCode.trim() + "' maxlength=8 onkeypress='javascript:if(event.keyCode == 13) search();' class=read onBlur='javascript:js_NumCheck(this);'>");
            out.println("\t<input type=radio name=ABgubun value='1' " + ABtemp1 + ">변경전 물품분류&nbsp;");
            out.println("\t<input type=radio name=ABgubun value='2' " + ABtemp2 + ">변경후 물품분류");
            out.println("</td>");
            out.println("<td class='tdb'><a href='javascript:search();'><img src=/img/bu_refer.gif\tborder=0 align=absmiddle alt='검색'></a></td>");
            out.println("</tr><tr height=2><td colspan=4 class=line></td></tr></table>");
        }
        out.println("&nbsp</DIV>");
        out.println("<input\ttype=hidden\tname=a value='" + a + "'>");
        out.println("<input\ttype=hidden\tname=b value='" + b + "'>");
        out.println("<input\ttype=hidden\tname=formName value='" + formName + "'>");
        out.println("</form>");
    }
    
    private void endHtml(final PrintWriter out) {
        out.println("</td><td width=28></td></tr>");
        out.println("<tr height=10><td colspan=3></td></tr>");
        out.println("<tr height=*><td colspan=3></td></tr>");
        out.println("<tr><td colspan=3 height=45 width=*>");
        out.println("");
        out.println("<table width=100% cellpadding=0 cellspacing=0 border=0 background='/img/pop_bottom_back.gif' height=45>");
        out.println("<tr>");
        out.println("<td background='/img/pop_bottom.gif' style='background-position:right; background-repeat:no-repeat;'></td>");
        out.println("</tr></table>");
        out.println("");
        out.println("</td></tr></table>");
        out.println("</body>");
        out.println("</html>");
    }
    
    private void noDataHtml(final PrintWriter out, final String gubun) {
        if (gubun.equals("1") || gubun.equals("2")) {
            out.println("<table width=100% border=0 cellspacing=1 cellpadding=2>");
            out.println("<tr>");
            out.println("\t<td\talign=right\tcolspan=5 class='page'>검색건수 : 0건</td>");
            out.println("</tr>");
            out.println("<tr height=4><td colspan=5 class=line></td></tr>");
            out.println("<tr>");
            out.println("<td class=tdac>순번</td>");
            out.println("<td class=tdac>물품분류번호</td>");
            out.println("<td class=tdac>정부물품<BR>분류번호</td>");
            out.println("<td class=tdac>물품분류명</td>");
            out.println("<td class=tdac>품명</td>");
            out.println("</tr>");
            out.println("<tr height=1><td colspan=5 class=line></td></tr>");
            out.println("<tr>");
            out.println("<td class=tdbc\tcolspan=5><font\tcolor=red>검색된 Data가 없습니다.</font></td>");
            out.println("</tr>");
            out.println("<tr height=1><td colspan=5 class=line></td></tr></table>");
        }
        else if (gubun.equals("3")) {
            out.println("<table width=100% border=0 cellspacing=1 cellpadding=2>");
            out.println("<tr>");
            out.println("\t<td\talign=right\tcolspan=5 class='page'>검색건수 : 0건</td>");
            out.println("</tr>");
            out.println("<tr height=4><td colspan=5 class=line></td></tr>");
            out.println("<tr>");
            out.println("<td class=tdac>순번</td>");
            out.println("<td class=tdac>변경전 물품분류번호</td>");
            out.println("<td class=tdac>변경후 물품분류번호</td>");
            out.println("<td class=tdac>변경전 물품분류명</td>");
            out.println("<td class=tdac>변경후 물품분류명</td>");
            out.println("</tr>");
            out.println("<tr height=1><td colspan=5 class=line></td></tr>");
            out.println("<tr>");
            out.println("<td class=tdbc\tcolspan=5><font\tcolor=red>해당 물품은 변경된 사항이 없습니다.</font></td>");
            out.println("</tr>");
            out.println("<tr height=1><td colspan=5 class=line></td></tr></table>");
        }
    }
    
    private void dataHtml(final ResultSet rest, final PrintWriter out, final int totalCount, final String gubun) throws SQLException {
        if (gubun.equals("1") || gubun.equals("2")) {
            out.println("<table width=100% border=0 cellspacing=1 cellpadding=2>");
            out.println("<tr>");
            out.println("\t<td\talign=right\tcolspan=5 class='page'>검색건수 : " + Integer.toString(totalCount) + "건</td>");
            out.println("</tr>");
            out.println("<tr height=4><td colspan=5 class=line></td></tr>");
            out.println("<tr>");
            out.println("<td class=tdac>순번</td>");
            out.println("<td class=tdac>물품분류번호</td>");
            out.println("<td class=tdac>정부물품<BR>분류번호</td>");
            out.println("<td class=tdac>물품분류명</td>");
            out.println("<td class=tdac>품명</td>");
            out.println("</tr>");
            out.println("<tr height=1><td colspan=5 class=line></td></tr>");
        }
        else if (gubun.equals("3")) {
            out.println("<table width=100% border=0 cellspacing=1 cellpadding=2>");
            out.println("<tr>");
            out.println("\t<td\talign=right\tcolspan=5 class='page'>검색건수 : " + Integer.toString(totalCount) + "건</td>");
            out.println("</tr>");
            out.println("<tr height=4><td colspan=5 class=line></td></tr>");
            out.println("<tr>");
            out.println("<td class=tdac>순번</td>");
            out.println("<td class=tdac>변경전 물품분류번호</td>");
            out.println("<td class=tdac>변경후 물품분류번호</td>");
            out.println("<td class=tdac>변경전 물품분류명</td>");
            out.println("<td class=tdac>변경후 물품분류명</td>");
            out.println("</tr>");
            out.println("<tr height=1><td colspan=5 class=line></td></tr>");
        }
        Label_0868: {
            if (!gubun.equals("1")) {
                if (!gubun.equals("2")) {
                    if (gubun.equals("3")) {
                        while (rest.next()) {
                            out.println("<tr>");
                            out.println("<td class=tdbc>" + rest.getString(1) + "</td>");
                            out.println("<td class=tdbc>" + rest.getString(2) + "</td>");
                            out.println("<td class=tdbc><a href=\"javascript:toOpener('" + rest.getString(3) + "','" + rest.getString(5) + "','" + rest.getString(2) + "','" + rest.getString(5) + "')\">" + rest.getString(3) + "</td>");
                            out.println("<td class=tdb>" + rest.getString(4) + "</td>");
                            out.println("<td class=tdb>" + rest.getString(5) + "</td>");
                            out.println("</tr>");
                        }
                    }
                    break Label_0868;
                }
            }
            while (rest.next()) {
                out.println("<tr>");
                out.println("<td class=tdbc>" + rest.getString(1) + "</td>");
                out.println("<td class=tdbc><a href=\"javascript:toOpener('" + rest.getString(2) + "','" + rest.getString(4) + "','" + rest.getString(3) + "','" + rest.getString(4) + "')\">" + rest.getString(2) + "</td>");
                out.println("<td class=tdbc><a href=\"javascript:toOpener('" + rest.getString(2) + "','" + rest.getString(4) + "','" + rest.getString(3) + "','" + rest.getString(4) + "')\">" + rest.getString(3) + "</td>");
                out.println("<td class=tdb>" + rest.getString(4) + "</td>");
                out.println("<td class=tdb>" + rest.getString(5) + "</td>");
                out.println("</tr>");
            }
        }
        out.println("<tr height=2><td colspan=5 class=line></td></tr>");
        out.println("</table>");
    }
    
    private String retNull(String str) {
        if (str == null || str.equals("")) {
            str = null;
        }
        return str;
    }
    
    private String getNextPageIndexes(final String url, final int totalRow, final int page_size, final int page_no) {
        String sReturn = "";
        if (totalRow <= page_size) {
            return sReturn;
        }
        final int startPage = ((page_no % 10 == 0) ? (page_no - 1) : page_no) / 10 * 10 + 1;
        final int totalPage = totalRow / page_size + ((totalRow % page_size != 0) ? 1 : 0);
        final int endPage = (totalPage - startPage >= 10) ? (startPage + 9) : totalPage;
        sReturn = String.valueOf(sReturn) + "<table width=100% border=0 cellpadding=2 cellspacing=2><tr><td align=center background=/img/dotlines.gif height=1></td></tr><tr><td align=center class=redc>";
        if (page_no > 10) {
            sReturn = String.valueOf(sReturn) + "<a href='" + url + "&page_no=" + (startPage - 1) + "'><IMG ALIGN=absmiddle SRC=/img/bu_backarw.gif BORDER=0></a>";
        }
        else if (page_no > 1) {
            sReturn = String.valueOf(sReturn) + "<IMG ALIGN=absmiddle SRC=/img/bu_backarwn.gif BORDER=0>";
        }
        else {
            sReturn = String.valueOf(sReturn) + "<IMG ALIGN=absmiddle SRC=/img/bu_backarwn.gif BORDER=0>";
        }
        for (int i = startPage; i <= endPage; ++i) {
            if (page_no == i) {
                sReturn = String.valueOf(sReturn) + "[" + i + "]";
            }
            else {
                sReturn = String.valueOf(sReturn) + "<a href='" + url + "&page_no=" + i + "'>[" + i + "]</a>";
            }
        }
        if (endPage != totalPage) {
            sReturn = String.valueOf(sReturn) + "<a href='" + url + "&page_no=" + (endPage + 1) + "'><IMG ALIGN=absmiddle SRC=/img/bu_nextarw.gif BORDER=0></a><a href='" + url + "&page_no=" + (endPage + 1) + "'></a>";
        }
        else if (page_no < endPage) {
            sReturn = String.valueOf(sReturn) + "<IMG ALIGN=absmiddle SRC=/img/bu_nextarwn.gif BORDER=0>";
        }
        else {
            sReturn = String.valueOf(sReturn) + "<IMG ALIGN=absmiddle SRC=/img/bu_nextarwn.gif BORDER=0>";
        }
        sReturn = String.valueOf(sReturn) + "</td></tr><tr><td align=center background=/img/dotlines.gif height=1></td></tr></table>";
        return sReturn;
    }
}
