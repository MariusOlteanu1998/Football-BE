package com.myProject.demo.service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myProject.demo.dto.UserDTO;
import com.myProject.demo.model.UserModel;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.demo.repository.UserRepository;
import org.springframework.validation.annotation.Validated;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	private final UserRepository userRepo;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public List<UserModel> getAllUsers() {
		logger.info("Fetching all users");
		return userRepo.findAll();
	}

	public Optional<UserModel> getAllUsersById(int id) {
		logger.info("Fetching user with ID: {}", id);
		return userRepo.findById(id);
	}

	public UserModel updateUserById(int id, UserModel updateUserDTO) {
		logger.info("Updating user with id: {}", id);
		return userRepo.findById(id)
				.map(existingUser -> {

					// Convertire DTO in Model
					UserModel updatedUser = objectMapper.convertValue(updateUserDTO, UserModel.class);

					// Aggiorna solo i campi che sono stati forniti
					if (updatedUser.getNome() != null) {
						existingUser.setNome(updatedUser.getNome());
					}
					if (updatedUser.getCognome() != null) {
						existingUser.setCognome(updatedUser.getCognome());
					}
					if (updatedUser.getAnno_nascita() != null) {
						existingUser.setAnno_nascita(updatedUser.getAnno_nascita());
					}
					if (updatedUser.getEmail() != null) {
						existingUser.setEmail(updatedUser.getEmail());
					}
					if (updatedUser.getPassword() != null) {
						existingUser.setPassword(updatedUser.getPassword());
					}
					if (updatedUser.getCf() != null) {
						existingUser.setCf(updatedUser.getCf());
					}

					// Salva l'oggetto aggiornato
					UserModel savedUser = userRepo.save(existingUser);

					// Ottieni le modifiche
					//String changes = getUserChanges(existingUser, existingUser);
					//logger.info("User with ID: {} updated successfully. Changes: {}", id, changes);
					return savedUser;
				})
				.orElseThrow(() -> {
					logger.warn("User with ID: {} not found for update", id);
					return new RuntimeException("User not found with ID: " + id);
				});
	}

	public UserModel insertUser(@Valid UserModel userDTO) {
		logger.info("Insert new user with ID: {}");
		UserModel userModel = objectMapper.convertValue(userDTO, UserModel.class);
		UserModel newUser = userRepo.save(userModel);
		logger.info("User with ID: {} inserted successfully", newUser.getId());
		return newUser;
	}

	public void deleteUserById(int id) {
		logger.info("Deleting user with ID: {}", id);
		userRepo.deleteById(id);
		logger.info("User with ID: {} deleted successfully", id);
	}

//	private String getUserChanges(UserModel oldUser, UserModel newUser) {
//		StringBuilder changes = new StringBuilder();
//		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//
//		if (!oldUser.getNome().equals(newUser.getNome())) {
//			changes.append(String.format("Nome changed from '%s' to '%s'. ", oldUser.getNome(), newUser.getNome()));
//		}
//		if (!oldUser.getCognome().equals(newUser.getCognome())) {
//			changes.append(String.format("Cognome changed from '%s' to '%s'. ", oldUser.getCognome(), newUser.getCognome()));
//		}
//		if (!oldUser.getAnno_nascita().equals(newUser.getAnno_nascita())) {
//			changes.append(String.format("Anno nascita changed from '%s' to '%s'. ",
//					formatter.format(oldUser.getAnno_nascita()), formatter.format(newUser.getAnno_nascita())));
//		}
//		if (!oldUser.getEmail().equals(newUser.getEmail())) {
//			changes.append(String.format("Email changed from '%s' to '%s'. ", oldUser.getEmail(), newUser.getEmail()));
//		}
//		if (!oldUser.getPassword().equals(newUser.getPassword())) {
//			changes.append("Password changed. ");
//		}
//		if (!oldUser.getCf().equals(newUser.getCf())) {
//			changes.append(String.format("CF changed from '%s' to '%s'. ", oldUser.getCf(), newUser.getCf()));
//		}
//
//		return changes.toString();
//	}
}
