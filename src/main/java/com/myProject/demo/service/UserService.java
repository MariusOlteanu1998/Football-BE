package com.myProject.demo.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.myProject.demo.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.demo.repository.UserRepository;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private final UserRepository userRepo;

	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public List<UserDTO> getAllUsers() {
		logger.info("Fetching all users");
		return userRepo.findAll();
	}

	public Optional<UserDTO> getAllUsersById(int id) {
		logger.info("Fetching user with ID: {}", id);
		return userRepo.findById(id);
	}

	public UserDTO updateUserById(int id, UserDTO updateUser) {
		logger.info("Updating user with id: {}", id);
		return userRepo.findById(id)
				.map(existingUser -> {
					// Aggiorna solo i campi che sono stati forniti
					if (updateUser.getNome() != null) {
						existingUser.setNome(updateUser.getNome());
					}
					if (updateUser.getCognome() != null) {
						existingUser.setCognome(updateUser.getCognome());
					}
					if (updateUser.getAnno_nascita() != null) {
						existingUser.setAnno_nascita(updateUser.getAnno_nascita());
					}
					if (updateUser.getEmail() != null) {
						existingUser.setEmail(updateUser.getEmail());
					}
					if (updateUser.getPassword() != null) {
						existingUser.setPassword(updateUser.getPassword());
					}
					if (updateUser.getCf() != null) {
						existingUser.setCf(updateUser.getCf());
					}

					// Salva l'oggetto aggiornato
					UserDTO savedUser = userRepo.save(existingUser);

					// Ottieni le modifiche
					String changes = getUserChanges(existingUser, existingUser);
					logger.info("User with ID: {} updated successfully. Changes: {}", id, changes);
					return savedUser;
				})
				.orElseThrow(() -> {
					logger.warn("User with ID: {} not found for update", id);
					return new RuntimeException("User not found with ID: " + id);
				});
	}

	public UserDTO insertUser(UserDTO user) {
		logger.info("Inserting new user with ID: {}", user.getId());
		UserDTO newUser = userRepo.save(user);
		logger.info("User with ID: {} inserted successfully", newUser.getId());
		return newUser;
	}

	public void deleteUserById(int id) {
		logger.info("Deleting user with ID: {}", id);
		userRepo.deleteById(id);
		logger.info("User with ID: {} deleted successfully", id);
	}

	private String getUserChanges(UserDTO oldUser, UserDTO newUser) {
		StringBuilder changes = new StringBuilder();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		if (!oldUser.getNome().equals(newUser.getNome())) {
			changes.append(String.format("Nome changed from '%s' to '%s'. ", oldUser.getNome(), newUser.getNome()));
		}
		if (!oldUser.getCognome().equals(newUser.getCognome())) {
			changes.append(String.format("Cognome changed from '%s' to '%s'. ", oldUser.getCognome(), newUser.getCognome()));
		}
		if (!oldUser.getAnno_nascita().equals(newUser.getAnno_nascita())) {
			changes.append(String.format("Anno nascita changed from '%s' to '%s'. ",
					oldUser.getAnno_nascita().format(formatter), newUser.getAnno_nascita().format(formatter)));
		}
		if (!oldUser.getEmail().equals(newUser.getEmail())) {
			changes.append(String.format("Email changed from '%s' to '%s'. ", oldUser.getEmail(), newUser.getEmail()));
		}
		if (!oldUser.getPassword().equals(newUser.getPassword())) {
			changes.append("Password changed. ");
		}
		if (!oldUser.getCf().equals(newUser.getCf())) {
			changes.append(String.format("CF changed from '%s' to '%s'. ", oldUser.getCf(), newUser.getCf()));
		}

		return changes.toString();
	}
}
