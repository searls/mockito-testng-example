package com.pillartechnology.mail;

import static java.util.Arrays.*;
import java.util.List;

public class AddressSplitter {

	private AddressInputQueue addressInputQueue;
	
	public List<String> split() {
		return asList(addressInputQueue.next().split(","));
	}

}
