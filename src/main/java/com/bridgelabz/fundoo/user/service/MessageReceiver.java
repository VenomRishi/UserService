package com.bridgelabz.fundoo.user.service;

import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.bridgelabz.fundoo.user.utility.Constant;
import com.bridgelabz.fundoo.user.utility.TokenUtility;

public class MessageReceiver {
	private CountDownLatch countDownLatch = new CountDownLatch(1);
	private final static Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);

	@Autowired
	private JavaMailSender javaMailSender;

	public void receiveMsg(String token) {
		String email = TokenUtility.parseToken(token, Constant.KEY_REGISTER_VERIFY).getSubject();

		registerVerificationSendEmail(email);
		countDownLatch.countDown();
	}

	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}

	/**
	 * Purpose: method is created for sending the email for activating the user
	 * account user only login to account only if user account is activated if the
	 * user wants to activate the account then he/she has to verify the email
	 * verification link
	 * 
	 * @param email from where the user wants to send email this parameter comes
	 *              from the user registration form
	 */

	public void registerVerificationSendEmail(String email) {
		LOG.info(Constant.SERVICE_REGISTER_VERIFICATION_METHOD);

		// code for sending email to recipient
		String token = TokenUtility.buildToken(email, Constant.KEY_REGISTER_VERIFY);
		SimpleMailMessage sampleMailMessage = sendMailForRegistrationVerification(email, token);
		javaMailSender.send(sampleMailMessage);
		LOG.info(Constant.EMAIL_SEND);

	}

	/**
	 * Purpose: This method is created to generate one simple mail message
	 * SimpleMailMessage class has the functionality to setting all the
	 * configuration for sending mail is done by using this class only
	 * 
	 * @param email input from user
	 * @param token input from program
	 * @return returns the object of SimpleMailMessage with assigning the values in
	 *         it
	 */
	private SimpleMailMessage sendMailForRegistrationVerification(String email, String token) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject(Constant.EMAIL_SUBJECT_VERIFY);
		simpleMailMessage.setText(Constant.BASE_URL + Constant.VERIFY_URI + token);
		return simpleMailMessage;
	}
}
