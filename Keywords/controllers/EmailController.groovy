package controllers

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.SmartWaitWebDriver

import datamodels.EmailModel
import internal.GlobalVariable


public class EmailController {
	private static EmailController mInstance = null

	private EmailController(){
	}

	public static EmailController getInstance(){
		if(mInstance == null){
			mInstance = new EmailController()
		}

		return mInstance
	}

	public static void sendReportInEmail(String hostName, String platformName, String suiteName, String fileUrl, int passed, int failed){

		// Recipient's email ID needs to be mentioned.
		String to = GlobalVariable.toList;

		// Sender's email ID needs to be mentioned
		String from = GlobalVariable.from;

		final String username = GlobalVariable.senderEmail;//change accordingly
		final String password = GlobalVariable.senderPassword;//change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			String testSuiteName = suiteName

			if(testSuiteName.contains("Record")){
				testSuiteName = "Record"
			}
			else if(testSuiteName.contains("Replay")){
				testSuiteName = "Replay"
			}
			else if(testSuiteName.contains("Crawl")){
				testSuiteName = "Crawl"
			}
			String emailBodyStr = ""
			// Set Subject: header field
			if(testSuiteName.contains("Sanity") || testSuiteName.contains("Regression")){
				message.setSubject(GlobalVariable.emailSubject+testSuiteName)
				emailBodyStr = GlobalVariable.emailBody
				platformName = ""
			}
			else{
				message.setSubject(GlobalVariable.emailSubject+testSuiteName+" Tests")
				emailBodyStr = "Execution cycle of <b>"+testSuiteName+" Device Stability Tests </b>is completed. Here is result summary"
			}

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// RunConfiguration Params
			WebDriver driver = DriverFactory.getWebDriver()
			Capabilities caps = ((SmartWaitWebDriver) driver).getCapabilities()
			String browserName = caps.getBrowserName().capitalize()
			String OsName = System.getProperty('os.name')

			// message contains HTML markups
			int total = passed+failed
			StringBuilder body = new StringBuilder();
			body.append("<div style=\"padding:20px\">")
					.append("<p>Hi there!</p>")
					.append("<p>${emailBodyStr}</p>")
					.append("<br/>")
					.append("<p><b>Host Name:</b> ${hostName}</p>")
					.append("<p><b>Browser:</b> ${browserName}</p>")
					.append("<p><b>OS:</b> ${OsName}</p>")
					.append("<p><b>SuiteName:</b> ${suiteName}</p>")
					.append("<table border=\"0\" style=\"width: 100%;border-collapse:collapse;text-align:center\">")
					.append("  <tr>")
					.append("    <th style=\"background-color:#D452C1;color:white;padding:5px\">Test Suite Summary</th>")
					.append("    <th style=\"background-color:#D452C1;color:white;padding:5px\">Total</th>")
					.append("    <th style=\"background-color:#D452C1;color:white;padding:5px\">Passed</th>")
					.append("    <th style=\"background-color:#D452C1;color:white;padding:5px\">Failed</th>")
					.append("  </tr>")
					.append("  <tr>")
					.append("    <td>${platformName}</td>")
					.append("    <td  style=\"color:blue\">${total}</td>")
					.append("    <td  style=\"color:blue\">${passed}</td>")
					.append("    <td  style=\"color:blue\">${failed}</td>")
					.append("  </tr>")
					.append("</table>")
					.append("<p>Click the button below to see detailed report of all the test cases for a deeper analysis</p>")
					.append("<br/>")
					.append("<a  class='link' href=${fileUrl} style='padding: 8px 12px; border: 1px solid #000000;border-radius: 2px;font-family: Helvetica, Arial, sans-serif;font-size: 14px; color: #D452C1;text-decoration: none;font-weight:bold;display: inline-block;'>View Report</a>")
					.append("<br/>")
					.append("<p>--</p>")
					.append("<p>Regards,</p>")
					.append("<p>Automation Team</p>")
					.append("</div>");


			//			messageBodyPart.setText(body.toString());
			messageBodyPart.setContent(body.toString(),"text/html")
			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			String filename = ""
			String logFolderPath = RunConfiguration.getLogFolderPath()
			String[] logFileSplitter = logFolderPath.split("/")
			if(logFileSplitter != null && logFileSplitter.length > 0){
				filename = logFileSplitter[logFileSplitter.length-1]
				filename = logFolderPath+"/"+filename+".html"
				//				Utility.zipFile(logFolderPath,filename)
				//				filename = logFolderPath+"/"+filename+".zip"
			}

			//			MimeBodyPart attachementPart = new MimeBodyPart();
			//			attachementPart.attachFile(new File(filename));
			//			attachementPart.setHeader("Content-Type", "text/plain; charset=\"us-ascii\"");
			//			multipart.addBodyPart(attachementPart);

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);
			System.out.println("Sent email message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void sendReportInEmail(String hostName, String suiteName, String fileUrl, HashMap<String, EmailModel> testCasesEmailDataModel){

		// Recipient's email ID needs to be mentioned.
		String to = GlobalVariable.toList;

		// Sender's email ID needs to be mentioned
		String from = GlobalVariable.from;

		final String username = GlobalVariable.senderEmail;//change accordingly
		final String password = GlobalVariable.senderPassword;//change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			String testSuiteName = suiteName

			if(testSuiteName.contains("Record")){
				testSuiteName = "Record"
			}
			else if(testSuiteName.contains("Replay")){
				testSuiteName = "Replay"
			}
			else if(testSuiteName.contains("Crawl")){
				testSuiteName = "Crawl"
			}
			String emailBodyStr = ""
			// Set Subject: header field
			if(testSuiteName.contains("Sanity") || testSuiteName.contains("Regression")){
				message.setSubject(GlobalVariable.emailSubject+" "+testSuiteName)
				emailBodyStr = GlobalVariable.emailBody
			}
			else{
				message.setSubject(GlobalVariable.emailSubject+" "+testSuiteName+" Tests")
				emailBodyStr = "Execution cycle of <b>"+testSuiteName+" Device Stability Tests </b>is completed. Here is result summary"
			}

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// RunConfiguration Params
			WebDriver driver = DriverFactory.getWebDriver()
			Capabilities caps = ((SmartWaitWebDriver) driver).getCapabilities()
			String browserName = caps.getBrowserName().capitalize()
			String OsName = System.getProperty('os.name')

			Set<String> keys = testCasesEmailDataModel.keySet();
			List<String> keysList = new ArrayList<String>(keys)
			StringBuilder rowData = new StringBuilder();
			StringBuilder rowDataLogin = new StringBuilder();
			StringBuilder rowDataLogout = new StringBuilder();
			StringBuilder rowDataFinal = new StringBuilder();
			if(rowData != null){
				for(int j = 0; j < keysList.size(); j++){
					String key1 = keysList.get(j)
					EmailModel dm = testCasesEmailDataModel.get(key1)
					int total = dm.getPassedTestCount()+dm.getFailedTestCount();
					if(key1.equalsIgnoreCase("Login")){
						rowDataLogin.append("  <tr>")
								.append("    <td>${key1}</td>")
								.append("    <td  style=\"color:blue\">${total}</td>")
								.append("    <td  style=\"color:blue\">${dm.getPassedTestCount()}</td>")
								.append("    <td  style=\"color:blue\">${dm.getFailedTestCount()}</td>")
								.append("  </tr>")
					}
					else if(key1.equalsIgnoreCase("Logout")){
						rowDataLogout.append("  <tr>")
								.append("    <td>${key1}</td>")
								.append("    <td  style=\"color:blue\">${total}</td>")
								.append("    <td  style=\"color:blue\">${dm.getPassedTestCount()}</td>")
								.append("    <td  style=\"color:blue\">${dm.getFailedTestCount()}</td>")
								.append("  </tr>")
					}
					else{
						rowData.append("  <tr>")
								.append("    <td>${key1}</td>")
								.append("    <td  style=\"color:blue\">${total}</td>")
								.append("    <td  style=\"color:blue\">${dm.getPassedTestCount()}</td>")
								.append("    <td  style=\"color:blue\">${dm.getFailedTestCount()}</td>")
								.append("  </tr>")
					}
				}
			}

			rowDataFinal.append(rowDataLogin.toString())
			rowDataFinal.append(rowData.toString())
			rowDataFinal.append(rowDataLogout.toString())

			// message contains HTML markups
			StringBuilder body = new StringBuilder();
			body.append("<div style=\"padding:20px\">")
					.append("<p>Hi there!</p>")
					.append("<p>${emailBodyStr}</p>")
					.append("<br/>")
					.append("<p><b>Host Name:</b> ${hostName}</p>")
					.append("<p><b>Browser:</b> ${browserName}</p>")
					.append("<p><b>OS:</b> ${OsName}</p>")
					.append("<p><b>SuiteName:</b> ${suiteName}</p>")
					.append("<table border=\"0\" style=\"width: 100%;border-collapse:collapse;text-align:center\">")
					.append("  <tr>")
					.append("    <th style=\"background-color:#D452C1;color:white;padding:5px\">Test Suite Summary</th>")
					.append("    <th style=\"background-color:#D452C1;color:white;padding:5px\">Total</th>")
					.append("    <th style=\"background-color:#D452C1;color:white;padding:5px\">Passed</th>")
					.append("    <th style=\"background-color:#D452C1;color:white;padding:5px\">Failed</th>")
					.append("  </tr>")
					.append(rowDataFinal.toString())
					.append("</table>")
					.append("<p>Click the button below to see detailed report of all the test cases for a deeper analysis</p>")
					.append("<br/>")
					.append("<a  class='link' href=${fileUrl} style='padding: 8px 12px; border: 1px solid #000000;border-radius: 2px;font-family: Helvetica, Arial, sans-serif;font-size: 14px; color: #D452C1;text-decoration: none;font-weight:bold;display: inline-block;'>View Report</a>")
					.append("<br/>")
					.append("<p>--</p>")
					.append("<p>Regards,</p>")
					.append("<p>Automation Team</p>")
					.append("</div>");


			//			messageBodyPart.setText(body.toString());
			messageBodyPart.setContent(body.toString(),"text/html")
			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			String filename = ""
			String logFolderPath = RunConfiguration.getLogFolderPath()
			String[] logFileSplitter = logFolderPath.split("/")
			if(logFileSplitter != null && logFileSplitter.length > 0){
				filename = logFileSplitter[logFileSplitter.length-1]
				filename = logFolderPath+"/"+filename+".html"
			}

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);
			System.out.println("Sent email message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
