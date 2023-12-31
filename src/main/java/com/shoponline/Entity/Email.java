package com.shoponline.Entity;

import java.io.File;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
	private String from;
	private String to;
	private String subject;
	private String body;
	private String[] bcc;
	private String[] cc;
	private List<File> files;
	
	public Email(String to, String subject, String body) {
		this.from =".com";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	
}
