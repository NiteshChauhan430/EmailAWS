package com.example.EmailServices;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class SmsSender {
	
	public static void sendSMS() {
		AmazonSNSClient snsClient = new AmazonSNSClient();
		String message = "My SMS";
		String phoneNumber = "+118888888888";
		/*
		 * Map<String, MessageAttributeValue> smsAttributes = new HashMap<String,
		 * MessageAttributeValue>(); //<set SMS attributes>
		 */
		Map<String, MessageAttributeValue> smsAttributes1 = new HashMap<String, MessageAttributeValue>();
		smsAttributes1.put("AWS.SNS.SMS.SenderID",
				new MessageAttributeValue().withStringValue("mySenderID").withDataType("String"));
		smsAttributes1.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue().withStringValue("0.50")

				.withDataType("Number"));
		smsAttributes1.put("AWS.SNS.SMS.SMSType",
				new MessageAttributeValue().withStringValue("Promotional").withDataType("String"));

		sendSMSMessage(snsClient, message, phoneNumber, smsAttributes1);
	}

	public static void sendSMSMessage(AmazonSNSClient snsClient, String message, String phoneNumber,
			Map<String, MessageAttributeValue> smsAttributes) {
		PublishResult result = snsClient.publish(new PublishRequest().withMessage(message).withPhoneNumber(phoneNumber)
				.withMessageAttributes(smsAttributes));
		System.out.println(result);
	}

}
