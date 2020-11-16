package ar.edu.unq.desapp.grupol022020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import ar.edu.unq.desapp.grupol022020.model.Donation;
import ar.edu.unq.desapp.grupol022020.model.Project;
import ar.edu.unq.desapp.grupol022020.model.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.Calendar;

@SpringBootApplication
public class SendEmailService implements CommandLineRunner {

    @Autowired
    private JavaMailSender javaMailSender;
	@Autowired
	private DonationService donationService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	

    public static void main(String[] args) {
        SpringApplication.run(SendEmailService.class, args);
    }

    @Override
    public void run(String... args) throws MessagingException, IOException {

        System.out.println("Sending Email...");

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
    }

    void sendEmailWithAttachment(String email) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(email);
        helper.setSubject("Ranking donantes");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
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
        
        
        // hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

       // helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(msg);

    }
}
