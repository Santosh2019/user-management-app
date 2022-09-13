package org.management.dao;

import org.management.entity.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, Integer> {

		
	public UserMaster findByEmailAndPassword(String email, String pwd);
	
	public UserMaster findByEmail(String email);
	
}

