package com.pillartechnology.mail;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddressSplitterTest {

	@InjectMocks private AddressSplitter subject = new AddressSplitter();
	
	@Mock private AddressInputQueue addressInputQueue;
	
	@BeforeMethod(alwaysRun=true)  
	public void injectDoubles() {
		MockitoAnnotations.initMocks(this); //This could be pulled up into a shared base class
	}
	
	@Test
	public void splitsAddressesByComma() {
		when(addressInputQueue.next()).thenReturn("jim@weirich.com,kent@beck.com");
		
		List<String> result = subject.split();
		
		assertThat(result,hasItems("jim@weirich.com","kent@beck.com"));
	}
	
}
