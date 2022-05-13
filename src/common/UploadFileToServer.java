package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import beans.RegADBean;
import common.Log;

public class UploadFileToServer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private RegADBean radb = new RegADBean();
	
	private String UPLOAD_DIRECTORY = "/data2/FileUpload";

	public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
		
		Map paramMap = new HashMap();
		String fileName = "";
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
				upload.setHeaderEncoding("UTF-8");
				
				List multiparts = upload.parseRequest(request);
				for (int i = 0; i < multiparts.size(); i++) {
					FileItem item = ((FileItem) multiparts.get(i));
					if (!item.isFormField()) {
						//fileName = new File(item.getName()).getName();
						fileName = System.currentTimeMillis()+"_"+FilenameUtils.getName(item.getName());
						item.write(new File(UPLOAD_DIRECTORY + File.separator + fileName));
					}else{
						String paramName = item.getFieldName();
						String paramVal = item.getString("UTF-8");
						paramMap.put(paramName, paramVal);						
					}
				}
				
				String revc = (String) paramMap.get("revcNo");
				Log.debug(revc);
				String bizRegNo = (String) paramMap.get("bizRegNo");
				Log.debug(bizRegNo);
				
				String strDesc = (String) paramMap.get("desc");
				String filetype = (String) paramMap.get("filetype");
				Log.debug(strDesc);
				radb.addFile(revc, bizRegNo, fileName, strDesc,filetype);
				Log.debug("File uploaded successfully");

			} catch (Exception ex) {
				response.sendRedirect("http://muasamcong.mpi.gov.vn/");
			}
		} else {

			Log.debug("Sorry this Servlet only handles file upload request");

		}
		
		String redirectUrl = "http://muasamcong.mpi.gov.vn:8070/AD/UploadGPKD.jsp?uploaded=1";
		response.sendRedirect( redirectUrl );
	}

	public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
		String fileName = request.getParameter("filename");
	
		File file = null;
		
		file = new File(UPLOAD_DIRECTORY+"/"  + fileName);
		
		if (fileName == null || fileName.equals("")) {
			throw new ServletException("File Name can't be null or empty");
		}
		if (!file.exists()) {
			throw new ServletException("File doesn't exists on server.");
		}
		ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(file);
		String mimeType = ctx.getMimeType(file.getAbsolutePath());
		response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		ServletOutputStream os = response.getOutputStream();
		byte[] bufferData = new byte[1024];
		int read = 0;
		while ((read = fis.read(bufferData)) != -1) {
			os.write(bufferData, 0, read);
		}
		os.flush();
		os.close();
		fis.close();
		System.out.println("File downloaded at client successfully");
		response.sendRedirect( "http://muasamcong.mpi.gov.vn:8070/AD/ViewGPKD.jsp?filename="+fileName);
	}
	
	
	public static void main(String[] args) {
		System.out.println("time:"+System.currentTimeMillis());
	}
}
