package org.jsp.useradressbootapp.controller;

import java.util.List;

import org.jsp.useradressbootapp.dto.Adress;
import org.jsp.useradressbootapp.dto.ResponseStructure;
import org.jsp.useradressbootapp.dto.User;
import org.jsp.useradressbootapp.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adresss")
public class AdressController {
	@Autowired
	private AdressService adressService;

	@PostMapping("/{user_id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<ResponseStructure<Adress>> saveAdress(@RequestBody Adress Adress,
			@PathVariable(name = "user_id") int user_id) {
		return adressService.saveAdress(Adress, user_id);
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<ResponseStructure<List<Adress>>> findById(@PathVariable(name = "id") int id) {
		return adressService.findByUserId(id);
	}

	@GetMapping("/by-country/{country}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<ResponseStructure<List<Adress>>> findByCountry(
			@PathVariable(name = "country") String category) {
		return adressService.findByCountry(category);
	}


	@GetMapping("/by-user/{user_id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<ResponseStructure<List<Adress>>> findByUser(@PathVariable(name = "user_id") int user_id) {
		return adressService.findByUserId(user_id);
	}

	@PostMapping("/by-user")
//	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<ResponseStructure<User>> findAdressByUserEmailAndPassword(@RequestParam String email,
			@RequestParam String password) {
		return adressService.verifyByEmail(email, password);
	}
}