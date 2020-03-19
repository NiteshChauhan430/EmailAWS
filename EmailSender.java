package com.example.EmailServices;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

public class EmailSender {
	static final String FROM = "xyz@gmail.com";

	static final String TO = "zbc@gmail.com";

	static final String BODY = "It's Gift for you my dear";
	static final String SUBJECT = "Amazon SES test (AWS SDK for Java)";

	public static void sendMail() {

		Destination destination = new Destination().withToAddresses(new String[] { TO });

		Content subject = new Content().withData(SUBJECT);
		Content textBody = new Content().withData(BODY);
		Body body = new Body().withText(textBody);
		Message message = new Message().withSubject(subject).withBody(body);
		SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination)
				.withMessage(message);

		try {
			System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

			ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
			try {
				credentialsProvider.getCredentials();
			} catch (Exception e) {
				throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
						+ "Please make sure that your credentials file is at the correct "
						+ "location (), and is in valid format.", e);
			}

			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
					.withCredentials(credentialsProvider)

					.withRegion(Regions.AP_SOUTH_1).build();

			client.sendEmail(request);
			System.out.println("Email sent!");

		} catch (Exception ex) {
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
		}

	}

}
