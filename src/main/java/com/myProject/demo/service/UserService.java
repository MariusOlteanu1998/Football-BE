package com.myProject.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myProject.demo.model.UserModel;
import com.myProject.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private final UserRepository userRepo;

	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	
	public List<UserModel> getAllUsers(String sort){
		
		return userRepo.findAll(Sort.by(sort));
	}
	
	
	public Optional<UserModel> getAllUsersById(int id){
		
		return userRepo.findById(id);
	}
	
	
	public Optional<UserModel> updateUsers(int id, UserModel updateUser){
		
		//METODO 1
		/*return userRepo.findById(id).map(user ->{
												 user.setNome(updateUser.getNome());
												 user.setCognome(updateUser.getCognome());
												 user.setAnno_nascita(updateUser.getAnno_nascita());
												 user.setEmail(updateUser.getEmail());
												 user.setPassword(updateUser.getPassword());
												 user.setCf(updateUser.getCf());
												 return userRepo.save(user);
												});*/
		  //METODO 2
		  Optional<UserModel> optionalUser = userRepo.findById(id);
		        
		        if (optionalUser.isPresent()) {
		            UserModel user = optionalUser.get();
		            user.setNome(updateUser.getNome());
		            user.setCognome(updateUser.getCognome());
		            user.setAnno_nascita(updateUser.getAnno_nascita());
		            user.setEmail(updateUser.getEmail());
		            user.setPassword(updateUser.getPassword());
		            user.setCf(updateUser.getCf());
		            //user.setScheda(updateUser.getScheda());
		            return Optional.of(userRepo.save(user));
		        } else {
		            return Optional.empty();
		        }
	}
	
	
	public UserModel insertUser(UserModel user) {
		
		return userRepo.save(user);
	}
	
	
	public void deleteUser(int id) {
		
		userRepo.deleteById(id);
	}
	

}
