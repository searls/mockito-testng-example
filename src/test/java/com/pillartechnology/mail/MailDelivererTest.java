package com.pillartechnology.mail;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MailDelivererTest {

	@InjectMocks private MailDeliverer subject = new MailDeliverer();
	
	@Mock private ExternalMailSystem externalMailSystem;
	@Mock private Timestamper timestamper;
	
	@Captor private ArgumentCaptor<Email> emailCaptor;
	
	@BeforeMethod(alwaysRun=true)  
	public void injectDoubles() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void sendsEmailByConstructingEmailObject() {
		String expectedUser = "tim";
		String expectedDomain = "wingfield.com";
		String expectedBody = "Hi Tim!";
		
		subject.deliver(expectedUser+"@"+expectedDomain,expectedBody);
		
		verify(externalMailSystem).send(emailCaptor.capture());
		Email email = emailCaptor.getValue();
		assertThat(email.getUser(),is(expectedUser));
		assertThat(email.getDomain(),is(expectedDomain));
		assertThat(email.getBody(),is(expectedBody));
	}

	@Test
	public void setsTheTimestampOnTheEmail() {
		Date expected = new Date(8932l);
		when(timestamper.stamp()).thenReturn(expected);
		
		subject.deliver("a@b", null);
		
		verify(externalMailSystem).send(emailCaptor.capture());
		Email email = emailCaptor.getValue();
		assertThat(email.getTimestamp(),is(expected));
	}
	
}
