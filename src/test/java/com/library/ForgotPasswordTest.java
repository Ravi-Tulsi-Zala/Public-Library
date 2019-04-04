package com.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.mail.MessagingException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.businessModels.Book;
import com.library.email.EmailDetails;
import com.library.email.SendEmail;
import com.library.mockDB.ForgotPasswordMocked;
import com.library.mockDB.WelcomePageMocked;
@RunWith(SpringRunner.class)
public class ForgotPasswordTest {
	public static ForgotPasswordMocked forgotPasswordMocked;

	@BeforeClass
	public static void initializer() {
		forgotPasswordMocked = new ForgotPasswordMocked();
	}

	@Test
	public void testRecoveredPassword() {
		ForgotPasswordMocked fPassword = new ForgotPasswordMocked();
		EmailDetails eDetails = fPassword.initiateForgotUserMock();
		try {
			SendEmail.sendmail(eDetails);
			assertTrue(true);
		} catch (MessagingException | IOException e) {
			assertTrue(false);
		}
	}

}
