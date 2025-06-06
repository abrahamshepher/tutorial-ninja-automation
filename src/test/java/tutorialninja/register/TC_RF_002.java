//package tutorialninja.register;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import java.util.Properties;
//
//import javax.mail.*;
//import javax.mail.internet.MimeMultipart;
//import javax.mail.search.FlagTerm;
//import java.io.IOException;
//import java.util.Properties;
//
//import java.io.IOException;
//import java.util.Properties;
//
//public class TC_RF_002 {
//
//	public static void main(String[] args) {
//		WebDriver driver= new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//		driver.get("https://www.amazon.in/");
//		
//		driver.findElement(By.xpath("//span[text()='Hello, sign in']")).click();
//		driver.findElement(By.id("ap_email_login")).sendKeys("sonicgenerations8@gmail.com");
//		driver.findElement(By.xpath("//input[@type='submit']")).click();
//		driver.findElement(By.xpath("//a[@id='auth-fpp-link-bottom']")).click();
//		driver.findElement(By.xpath("//input[@id='continue']")).click();
//		
//		String email="abrahamshepher@gmail.com";
//		String appPasscode="ezci yjow preb ljng";
//		
//        String host = "imap.gmail.com";
//        String username = "your-email@gmail.com";
//        String appPassword = "your-app-password";
//
//        Properties props = new Properties();
//        props.put("mail.imap.ssl.enable", "true");
//        props.put("mail.imap.host", host);
//        props.put("mail.imap.port", "993");
//
//        Session session = Session.getInstance(props);
//
//        try {
//            Store store = session.getStore("imap");
//            store.connect(username, appPassword);
//
//            Folder inbox = store.getFolder("INBOX");
//            inbox.open(Folder.READ_ONLY);
//
//            // Optionally, read only unread messages
//            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
//
//            if (messages.length == 0) {
//                System.out.println("No new messages");
//            } else {
//                for (Message message : messages) {
//                    System.out.println("Subject: " + message.getSubject());
//                    System.out.println("From: " + message.getFrom()[0].toString());
//                    System.out.println("Body: " + getTextFromMessage(message));
//                    System.out.println("-----------------------------------------------------");
//                }
//            }
//
//            inbox.close(false);
//            store.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//		
//		driver.close();
//	}
//	
//	  private static String getTextFromMessage(Message message) throws MessagingException, IOException {
//	        if (message.isMimeType("text/plain")) {
//	            return message.getContent().toString();
//	        } else if (message.isMimeType("multipart/*")) {
//	            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
//	            return getTextFromMimeMultipart(mimeMultipart);
//	        }
//	        return "";
//	    }
//
//	    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
//	        StringBuilder result = new StringBuilder();
//	        int count = mimeMultipart.getCount();
//
//	        for (int i = 0; i < count; i++) {
//	            BodyPart part = mimeMultipart.getBodyPart(i);
//	            if (part.isMimeType("text/plain")) {
//	                result.append(part.getContent());
//	            } else if (part.getContent() instanceof MimeMultipart) {
//	                result.append(getTextFromMimeMultipart((MimeMultipart) part.getContent()));
//	            }
//	        }
//	        return result.toString();
//	    }
//
//}
//
//
