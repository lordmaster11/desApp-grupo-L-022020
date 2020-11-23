package ar.edu.unq.desapp.grupol022020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupol022020.model.Donation;
import ar.edu.unq.desapp.grupol022020.model.Project;
import ar.edu.unq.desapp.grupol022020.model.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@SpringBootApplication
@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;
	@Autowired
	private DonationService donationService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	
	public void sendEmailsClose(List<String> emails, String nameProject) {
		for(String email: emails) {
		 try {
			 sendEmailClose(email, nameProject);
        }catch (MessagingException e) {
        	e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done");
        }
	}
		
	public void sendEmailClose(String email, String nameProject) throws MessagingException, IOException {

	    MimeMessage msg = javaMailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(msg, true);
	
	    helper.setTo(email);
	    helper.setSubject("Proyecto cerrado");
	    helper.setText("El proyecto " + nameProject+" ha sido finalizado.Gracias por Donar", true);
	
	    javaMailSender.send(msg);
    }

	@Scheduled(cron = "0 10 18 ? * * ")
	public void sendmail() {
       try {
    	   for(User user:userService.findAll()){
   	        try {
   	        	sendEmailWithAttachment(user.getMail());
   	        }catch (MessagingException e) {
   	        	e.printStackTrace();
   	        }catch (IOException e) {
   	            e.printStackTrace();
   	        }
   	        System.out.println("Done");
           }

       } catch (Exception ex) {
           System.out.println("error running thread " + ex.getMessage());
       }
    }
	   
   public void sendEmailWithAttachment(String email) throws MessagingException, IOException {

       MimeMessage msg = javaMailSender.createMimeMessage();
       MimeMessageHelper helper = new MimeMessageHelper(msg, true);

       helper.setTo(email);
       helper.setSubject("Ranking donantes");

       String res = "";
       for(Donation donation: donationService.getTop10()) {
       	res = res.concat("<li> <mark>" +donation.getUser().getName() + ": " 
       		+ (donation.getAmount()) + "</mark> </li>");
       }
       String res2 = "";
       for(Project project: projectService.getTopLastDonations()) {
    	   Calendar donationDate = project.getLastDonation();
    	   res2 = res2.concat("<li> <mark>" +project.getFantasyName()+ ": " 
        		+ (donationDate.getTime()) + "</mark> </li>");
      }
      String someHtmlMessage = 
      		"<div class=\"leaderboard\">\n" + 
      		"  <h1>\n" + 
      		"    <svg class=\"ico-cup\">\n" + 
      		"      <use xlink:href=\"#cup\"></use>\n" + 
      		"    </svg>\n" + 
      		"    Mayores donaciones\n" + 
      		"  </h1>\n" + 
      		"  <ol>\n" + res + 
      		"  </ol>\n" + 
      		"</div>\n" + 
      		"  <h1>\n" + 
      		"    <svg class=\"ico-cup\">\n" + 
      		"      <use xlink:href=\"#cup\"></use>\n" + 
      		"    </svg>\n" + 
      		"    Top ultimas localidades sin recibir donaciones\n" + 
      		"  </h1>\n" + 
      		"  <ol>\n" + res2 + 
      		"  </ol>\n" + 
      		"\n" ;
      

       helper.setText(someHtmlMessage, true);
       
       someHtmlMessage.concat(       		
       		"<div class=\"leaderboard\">\n" + 
          		"  <h1>\n" + 
          		"    <svg class=\"ico-cup\">\n" + 
          		"      <use xlink:href=\"#cup\"></use>\n" + 
          		"    </svg>\n" + 
          		"    Mayores donaciones\n" + 
          		"  </h1>\n" + 
          		"  <ol>\n" + res2 + 
          		"  </ol>\n" + 
          		"</div>\n" + 
          		"\n");

       javaMailSender.send(msg);

   }

}
