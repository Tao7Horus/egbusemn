package Mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import common.Log;

public class MailServer {
	public static final String NT_REGISTRATION_EMAIL_TEAMPLATE_STEP1 = "nt_registration_email_step1.template";
	public static final String NT_REGISTRATION_EXT_EMAIL_TEAMPLATE_STEP1 = "nt_registration_ext_email_step1.template";
	public static final String NT_REGISTRATION_EMAIL_TEAMPLATE_STEP1_APPROVAL = "nt_registration_email_step1_approval.template";
	public static final String NT_REGISTRATION_EMAIL_TEAMPLATE_STEP2 = "nt_registration_email_step2.template";
	public static final String NTNN_REGISTRATION_EMAIL_TEAMPLATE_STEP2 = "inter_email_step3.template";
	public static final String MT_REGISTRATION_EMAIL_TEAMPLATE_STEP1 = "mt_registration_email_step1.template";
	public static final String MT_REGISTRATION_EXT_EMAIL_TEAMPLATE_STEP1 =  "mt_registration_ext_email_step1.template";
	public static final String MT_REGISTRATION_EMAIL_TEAMPLATE_STEP1_APPROVAL = "mt_registration_email_step1_approval.template";
	public static final String MT_REGISTRATION_EMAIL_TEAMPLATE_STEP2 = "mt_registration_email_step2.template";
	public static final String NT_REGISTRATION_EMAIL_TEAMPLATE_STEP1_PENDDING = "nt_registration_email_step1_pendding.template";

	private Properties prop = null;

	public MailServer() {

		InputStream is = null;
		try {
			this.prop = new Properties();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			// is = this.getClass().getResourceAsStream("/mail.properties");
			is = classLoader.getResourceAsStream("/mail.properties");
			prop.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getPropertyValue(String key) {
		return this.prop.getProperty(key);
	}

	// Gửi Email tới bên mời thầu khi đăng ký thành công bước 1
	public void sendRegistrationEmailToMoiThauStep1(String toAddress, String regName, String regCompany,String company_code,String approvalRegCode,String reg_no) {
		Log.debug("sendRegistrationEmailToBMTStep1() >> START:"+toAddress);

		String registrationEmailStep1Path = getPropertyValue("file.emailtemplate.dir") + File.separator + MT_REGISTRATION_EMAIL_TEAMPLATE_STEP1;
		Map registrationEmailStep1 = getEmailTemplate(registrationEmailStep1Path);
		String content = registrationEmailStep1.get("content").toString();
		String subject = registrationEmailStep1.get("subject").toString();

		content = content.replaceAll("REG_NAME", regName);
		content = content.replaceAll("REG_COMPANY", regCompany);
		content = content.replaceAll("COMPANY_CODE", company_code);
		content = content.replaceAll("APPROVAL_REG_CODE", approvalRegCode);
		content = content.replaceAll("REG_NO", reg_no);
		
		createSendEmailFile(toAddress, subject, content);

		Log.debug("sendRegistrationEmailToBMTStep1 done() >> END"+toAddress);
	}

	// Gửi Email tới bên mời thầu khi đăng ký thành công bước 1 them CTS
	public void sendRegistrationExtEmailToMoiThauStep1(String toAddress, String regName, String regCompany,String company_code,String approvalRegCode,String reg_no) {
		Log.debug("sendRegistrationExtEmailToMoiThauStep1() >> START:"+toAddress);

		String registrationEmailStep1Path = getPropertyValue("file.emailtemplate.dir") + File.separator + MT_REGISTRATION_EXT_EMAIL_TEAMPLATE_STEP1;
		Map registrationEmailStep1 = getEmailTemplate(registrationEmailStep1Path);
		String content = registrationEmailStep1.get("content").toString();
		String subject = registrationEmailStep1.get("subject").toString();

		content = content.replaceAll("REG_NAME", regName);
		content = content.replaceAll("REG_COMPANY", regCompany);
		content = content.replaceAll("COMPANY_CODE", company_code);
		content = content.replaceAll("APPROVAL_REG_CODE", approvalRegCode);
		content = content.replaceAll("REG_NO", reg_no);
		
		createSendEmailFile(toAddress, subject, content);

		Log.debug("sendRegistrationExtEmailToMoiThauStep1 done() >> END"+toAddress);
	}

	// Gửi email khi phê duyệt đăng ký bên MT
	public void sendRegistrationEmailToMTStep1Approval(String toAddress, String regName, String regCompany, String regNo, String approvalRegCode, String companyCode) {
		Log.debug("sendRegistrationEmailToNhaThauStep1() >> START");

		String registrationEmailStep1Path = getPropertyValue("file.emailtemplate.dir") + File.separator + MT_REGISTRATION_EMAIL_TEAMPLATE_STEP1_APPROVAL;
		Map registrationEmailStep1 = getEmailTemplate(registrationEmailStep1Path);
		String content = registrationEmailStep1.get("content").toString();
		String subject = registrationEmailStep1.get("subject").toString();
		
		content = content.replaceAll("COMPANY_CODE", companyCode);
		content = content.replaceAll("REG_NAME", regName);
		content = content.replaceAll("REG_COMPANY", regCompany);
		content = content.replaceAll("REG_NO", regNo);
		content = content.replaceAll("APPROVAL_REG_CODE", approvalRegCode);

		createSendEmailFile(toAddress, subject, content);

		Log.debug("sendRegistrationEmailToMTStep1() >> END");
	}

	// Gửi email bước 2 bên mời thầu
	public void sendRegistrationEmailToMTStep2(String toAddress, String regName, String regCompany, String dependCode, String referCode) {
		Log.debug("sendRegistrationEmailToMTStep2() >> START");

		String registrationEmailStep1Path = getPropertyValue("file.emailtemplate.dir") + File.separator + MT_REGISTRATION_EMAIL_TEAMPLATE_STEP2;
		Map registrationEmailStep1 = getEmailTemplate(registrationEmailStep1Path);
		String content = registrationEmailStep1.get("content").toString();
		String subject = registrationEmailStep1.get("subject").toString();

		content = content.replaceAll("REG_NAME", regName);
		content = content.replaceAll("REG_COMPANY", regCompany);
		content = content.replaceAll("REG_DEPEND_CODE", dependCode);
		content = content.replaceAll("REG_DEPEND_REF", referCode);

		createSendEmailFile(toAddress, subject, content);

		Log.debug("sendRegistrationEmailToMTStep2() >> END");
	}

	public void sendRegistrationEmailToNhaThauStep1(String toAddress, String regName, String regCompany, String regNo, String approvalRegCode) {
		Log.debug("sendRegistrationEmailToNhaThauStep1() >> START");

		String registrationEmailStep1Path = getPropertyValue("file.emailtemplate.dir") + File.separator + NT_REGISTRATION_EMAIL_TEAMPLATE_STEP1;
		Map registrationEmailStep1 = getEmailTemplate(registrationEmailStep1Path);
		String content = registrationEmailStep1.get("content").toString();
		String subject = registrationEmailStep1.get("subject").toString();

		content = content.replaceAll("REG_NAME", regName);
		content = content.replaceAll("REG_COMPANY", regCompany);
		content = content.replaceAll("REG_NO", regNo);
		content = content.replaceAll("APPROVAL_REG_CODE", approvalRegCode);

		createSendEmailFile(toAddress, subject, content);

		Log.debug("sendRegistrationEmailToNhaThauStep1() >> END");
	}

	public void sendRegistrationExtEmailToNhaThauStep1(String toAddress, String regName, String regCompany, String regNo, String approvalRegCode) {
		Log.debug("sendRegistrationExtEmailToNhaThauStep1() >> START");

		String registrationEmailStep1Path = getPropertyValue("file.emailtemplate.dir") + File.separator + NT_REGISTRATION_EXT_EMAIL_TEAMPLATE_STEP1;
		Map registrationEmailStep1 = getEmailTemplate(registrationEmailStep1Path);
		String content = registrationEmailStep1.get("content").toString();
		String subject = registrationEmailStep1.get("subject").toString();

		content = content.replaceAll("REG_NAME", regName);
		content = content.replaceAll("REG_COMPANY", regCompany);
		content = content.replaceAll("REG_NO", regNo);
		content = content.replaceAll("APPROVAL_REG_CODE", approvalRegCode);

		createSendEmailFile(toAddress, subject, content);

		Log.debug("sendRegistrationExtEmailToNhaThauStep1() >> END");
	}

	public void sendRegistrationEmailToNhaThauStep1Approval(String toAddress, String regName, String regCompany, String regNo, String approvalRegCode) {
		Log.debug("sendRegistrationEmailToNhaThauStep1() >> START");

		String registrationEmailStep1Path = getPropertyValue("file.emailtemplate.dir") + File.separator + NT_REGISTRATION_EMAIL_TEAMPLATE_STEP1_APPROVAL;
		Map registrationEmailStep1 = getEmailTemplate(registrationEmailStep1Path);
		String content = registrationEmailStep1.get("content").toString();
		String subject = registrationEmailStep1.get("subject").toString();

		content = content.replaceAll("REG_NAME", regName);
		content = content.replaceAll("REG_COMPANY", regCompany);
		content = content.replaceAll("REG_NO", regNo);
		content = content.replaceAll("APPROVAL_REG_CODE", approvalRegCode);

		createSendEmailFile(toAddress, subject, content);

		Log.debug("sendRegistrationEmailToNhaThauStep1() >> END");
	}

	public void sendRegistrationEmailToNhaThauStep2(String toAddress, String regName, String regCompany, String dependCode, String referCode) {
		Log.debug("sendRegistrationEmailToNhaThauStep2() >> START");

		String registrationEmailStep1Path = getPropertyValue("file.emailtemplate.dir") + File.separator + NT_REGISTRATION_EMAIL_TEAMPLATE_STEP2;
		Map registrationEmailStep1 = getEmailTemplate(registrationEmailStep1Path);
		String content = registrationEmailStep1.get("content").toString();
		String subject = registrationEmailStep1.get("subject").toString();

		content = content.replaceAll("REG_NAME", regName);
		content = content.replaceAll("REG_COMPANY", regCompany);
		content = content.replaceAll("REG_DEPEND_CODE", dependCode);
		content = content.replaceAll("REG_DEPEND_REF", referCode);

		createSendEmailFile(toAddress, subject, content);

		Log.debug("sendRegistrationEmailToNhaThauStep2() >> END");
	}

	/**
	 * This method is used to create EmailFile to send email.
	 * 
	 * @param to
	 * @param subject
	 * @param content
	 */
	public void createSendEmailFile(String to, String subject, String content) {
		String filePath = getPropertyValue("file.email.dir") + File.separator + getPropertyValue("file.email.basename") + "_" + System.currentTimeMillis() + "."
				+ getPropertyValue("file.email.fileextension");
		Log.debug("createSendEmailFile() >> " + filePath);
		System.out.println("createSendEmailFile() >> " + filePath);
		Log.debug("createSendEmailFile() >> " + filePath);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(filePath));
			StringBuffer strBuffer = new StringBuffer();
			strBuffer.append("sendto=").append(to).append("\n");
			strBuffer.append("subject=").append(subject).append("\n");
			strBuffer.append("content=").append(content).append("\n");
			byte[] contentInBytes = strBuffer.toString().getBytes("UTF-8");
			fos.write(contentInBytes);
		} catch (Throwable e) {
			Log.debug("createSendEmailFile() has exceptions >> " + e.getMessage());
			System.out.println("createSendEmailFile() has exceptions >> " + e.getMessage());
		} finally {
			try {
				fos.flush();
			} catch (Throwable e) {
			}

			try {
				fos.close();
			} catch (Throwable e) {
			}
		}
	}

	/**
	 * This method is used to read email template.
	 * 
	 * @param templatePath
	 * @return
	 */
	public Map getEmailTemplate(String templatePath) {
		Map emailTemplate = new HashMap();
		try {
			File fileDir = new File(templatePath);
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
			String str;
			StringBuffer content = new StringBuffer();
			while ((str = in.readLine()) != null) {
				content.append(str);
			}

			String[] tmpArr = content.toString().split(":;:");

			emailTemplate.put(tmpArr[0], tmpArr[1]);
			emailTemplate.put(tmpArr[2], tmpArr[3]);
			in.close();
		} catch (Exception e) {
			Log.info("getEmailTemplate()" + e.getMessage());
		}
		return emailTemplate;
	}

	public void sendMail(String toAddress, String subject, String body) {
		MailServer mpc = new MailServer();
		final String username = mpc.getPropertyValue("mail.account");
		final String password = mpc.getPropertyValue("mail.pass");
		Properties props = new Properties();
		props.put("mail.smtp.auth", mpc.getPropertyValue("mail.smtp.auth"));
		// props.put("mail.smtp.starttls.enable",
		// mpc.getPropertyValue("mail.smtp.starttls.enable"));
		props.put("mail.smtp.host", mpc.getPropertyValue("mail.smtp.host"));
		props.put("mail.smtp.port", mpc.getPropertyValue("mail.smtp.port"));
		// //props.put("mail.smtp.ssl.trust",
		// mpc.getPropertyValue("mail.smtp.ssl.trust"));
		// final String username = "muasamcong@mpi.gov.vn";
		// final String password = "qlmdt2015";
		// Properties props = new Properties();
		// props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.host", "mail.mpi.gov.vn");
		// props.put("mail.smtp.port", "25");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void sendRegistrationEmailToNhaThauStep1Pedding(String toAddress, String regName, String regCompany, String regNo, String pendingRS) {
		Log.debug("sendRegistrationEmailToNhaThauStep1Pedding() >> START");

		String registrationEmailStep1Path = getPropertyValue("file.emailtemplate.dir") + File.separator + NT_REGISTRATION_EMAIL_TEAMPLATE_STEP1_PENDDING;
		Map registrationEmailStep1 = getEmailTemplate(registrationEmailStep1Path);
		String content = registrationEmailStep1.get("content").toString();
		String subject = registrationEmailStep1.get("subject").toString();

		Log.debug("Input: " + toAddress + " | " + regName+ " | " + regCompany+ " | " +regNo+ " | " + pendingRS);
		content = content.replaceAll("REG_NAME", regName);
		content = content.replaceAll("REG_COMPANY", regCompany);
		content = content.replaceAll("REG_NO", regNo);
		content = content.replaceAll("ERR_REG_RS", pendingRS);

		createSendEmailFile(toAddress, subject, content);

		Log.debug("sendRegistrationEmailToNhaThauStep1Pedding() >> END");
	}
	
	public void sendRegistrationEmailToNhaThauNNStep2(String toAddress, String regName, String regCompany, String dependCode, String referCode) {
		Log.debug("sendRegistrationEmailToNhaThauStep2() >> START");

		String registrationEmailStep1Path = getPropertyValue("file.emailtemplate.dir") + File.separator + NTNN_REGISTRATION_EMAIL_TEAMPLATE_STEP2;
		Map registrationEmailStep1 = getEmailTemplate(registrationEmailStep1Path);
		String content = registrationEmailStep1.get("content").toString();
		String subject = registrationEmailStep1.get("subject").toString();

		content = content.replaceAll("REG_NAME", regName);
		content = content.replaceAll("REG_COMPANY", regCompany);
		createSendEmailFile(toAddress, subject, content);

		Log.debug("sendRegistrationEmailToNhaThauStep2() >> END");
	}
	
	

}
