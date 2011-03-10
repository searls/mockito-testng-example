package com.pillartechnology.mail;

public interface ExternalMailSystem {

	public void send(String domain, String user, String body);
	
	public void send(Email email);
}
