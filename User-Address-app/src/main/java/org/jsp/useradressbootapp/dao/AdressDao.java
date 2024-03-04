package org.jsp.useradressbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.useradressbootapp.dto.Adress;
import org.jsp.useradressbootapp.repository.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdressDao {
	@Autowired
	private AdressRepository adressRepository;

	public Adress saveAdress(Adress Adress) {
		return adressRepository.save(Adress);
	}

	public Optional<Adress> findById(int id) {
		return adressRepository.findById(id);
	}

	public List<Adress> findAdressByUserId(int id) {
		return adressRepository.findAddressByUserId(id);
	}

	public List<Adress> findAdressByUserEmailAndPassword(String email, String password) {
		return adressRepository.findAdressByUserEmailAndPassword(email, password);
	}

	public List<Adress> findAdressByCountry(String country) {
		return adressRepository.findAdressByCountry(country);
	}
}
