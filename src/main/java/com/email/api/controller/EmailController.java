package com.email.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.api.model.Email;
import com.email.api.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@GetMapping("/home")
	public String homeHandler() {
		return "home...";
	}

	@PostMapping("/sendMail")
	public ResponseEntity<?> sendEmail(@RequestBody Email email) {

		boolean sendMail = emailService.sendMail(email.getTo(), email.getSubject(), email.getBody());
		System.out.println(sendMail);
		
		if (sendMail) {
			return ResponseEntity.ok("Mail Sent Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("please check your credentials");
		}
	}
}
