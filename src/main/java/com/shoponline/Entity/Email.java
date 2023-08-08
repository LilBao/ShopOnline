package com.shoponline.Entity;

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
	private String title;
	private String body;
	private List<String> bcc;
	private List<String> cc;
	private List<String> files;
	
}
