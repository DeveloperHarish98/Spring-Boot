package org.jsp.useradressbootapp.repository;

import java.util.List;

import org.jsp.useradressbootapp.dto.Adress;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdressRepository extends JpaRepository<Adress, Integer> {

	@Query("select a from Adress a where a.user.email=?1 and a.user.password=?2")
	public List<Adress> findAdressByUserEmailAndPassword(String email, String password);

	@Query("select a from Adress a where a.country=?1")
	public List<Adress> findAdressByCountry(String country);
	
	@Query("select a from Adress a where a.user.id=?1")
	public List<Adress> findAddressByUserId(int id);
}