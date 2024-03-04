package org.jsp.useradressbootapp.service;

import java.util.List;
import java.util.Optional;
import org.jsp.useradressbootapp.dao.AdressDao;
import org.jsp.useradressbootapp.dao.UserDao;
import org.jsp.useradressbootapp.dto.User;
import org.jsp.useradressbootapp.dto.Adress;
import org.jsp.useradressbootapp.dto.ResponseStructure;
import org.jsp.useradressbootapp.exception.AdressNotFoundException;
import org.jsp.useradressbootapp.exception.IdNotFoundException;
import org.jsp.useradressbootapp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdressService {
	@Autowired
	private AdressDao adressDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Adress>> saveAdress(Adress adress, int user_id) {
		Optional<User> recUser = userDao.findById(user_id);
		ResponseStructure<Adress> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			User user = recUser.get();
			user.getAdresses().add(adress);
			adress.setUser(user);
			structure.setData(adressDao.saveAdress(adress));
			structure.setMessage("Adress added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Adress>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();

	}

	public ResponseEntity<ResponseStructure<List<Adress>>> findByUserId(int id) {
		List<Adress> adresses = adressDao.findAdressByUserId(id);
		ResponseStructure<List<Adress>> structure = new ResponseStructure<>();
		if (adresses.size() > 0) {
			structure.setData(adresses);
			structure.setMessage("List of adresses" + id);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Adress>>>(structure, HttpStatus.OK);
		}
		throw new AdressNotFoundException("No Adresses found for User id:" + id);

	}

	public ResponseEntity<ResponseStructure<User>> verifyByEmail(String email, String password) {
		Optional<User> recUser = userDao.verifyByEmail(email, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setMessage("Verification Succesfull");
			structure.setData(recUser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid User Email or Password");
	}

	public ResponseEntity<ResponseStructure<List<Adress>>> findByCountry(String country) {
		List<Adress> recAdress = adressDao.findAdressByCountry(country);
		ResponseStructure<List<Adress>> structure = new ResponseStructure<>();
		if (recAdress.size() > 0) {
			structure.setMessage("Adress Found");
			structure.setData(recAdress);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Adress>>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid User Email or Password");
	}

}