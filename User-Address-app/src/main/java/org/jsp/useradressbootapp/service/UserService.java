package org.jsp.useradressbootapp.service;

import java.util.Optional;

import org.jsp.useradressbootapp.dao.UserDao;
import org.jsp.useradressbootapp.dto.ResponseStructure;
import org.jsp.useradressbootapp.dto.User;
import org.jsp.useradressbootapp.exception.IdNotFoundException;
import org.jsp.useradressbootapp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public ResponseStructure<User> saveUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setMessage("User saved");
		structure.setData(userDao.saveUser(user));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return structure;
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		Optional<User> recUser = userDao.findById(user.getId());
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			User dbUser = new User();
			dbUser.setId(user.getId());
			dbUser.setName(user.getName());
			dbUser.setPhone(user.getPhone());
			dbUser.setGender(user.getGender());
			dbUser.setAge(user.getAge());
			dbUser.setPhone(user.getPhone());
			dbUser.setEmail(user.getEmail());
			dbUser.setPassword(user.getPassword());
			structure.setMessage("User Updated");
			structure.setData(userDao.saveUser(user));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {
		Optional<User> recUser = userDao.findById(id);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("User Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<User>> verifyByPhone(long phone, String password) {
		Optional<User> recUser = userDao.verifyByPhone(phone, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setMessage("Verification Succesfull");
			structure.setData(recUser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid User Phone Number or Password");
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

}