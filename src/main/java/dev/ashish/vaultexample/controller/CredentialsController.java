package dev.ashish.vaultexample.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ashish.vaultexample.model.DatabaseCredential;
import dev.ashish.vaultexample.service.CredentialsService;

@RestController
@RequestMapping("/secure")
public class CredentialsController {

	@Autowired
	private CredentialsService credentialsService;

	@PostMapping("database/{name}/{value}/{orgId}")
	public ResponseEntity<String> saveCreds(@PathVariable("name") String name, @PathVariable("value") String value,
			@PathVariable("orgId") String orgId) {
		DatabaseCredential dc = new DatabaseCredential();
		dc.setUsername(name);
		dc.setPassword(value);
		return new ResponseEntity<String>(credentialsService.secureCredentials(dc, orgId), HttpStatus.OK);
	}

	@GetMapping("getCreds/{orgId}")
	public ResponseEntity<Map<String, Object>> getCreds(@PathVariable("orgId") String orgId) {
		return new ResponseEntity<Map<String, Object>>(credentialsService.accessCredentials(orgId), HttpStatus.OK);
	}
}
