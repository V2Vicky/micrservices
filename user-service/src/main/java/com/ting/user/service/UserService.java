package com.ting.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ting.user.dto.UserDTO;
import com.ting.user.entity.User;
import com.ting.user.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;

	public List<UserDTO> getAllUsers() {
		return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public UserDTO getUserById(Long id) {
		User User = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));
		return convertToDTO(User);
	}

	public UserDTO findByEmailOrUsername(String email, String username) {
		User user = userRepository.findByEmailOrUsername(email, username)
				.orElseThrow(() -> new RuntimeException("User not found"));

		return convertToDTO(user);
	}

	public UserDTO createUser(UserDTO UserDTO) {
		User User = convertToEntity(UserDTO);
		return convertToDTO(userRepository.save(User));
	}

	public UserDTO updateUser(Long id, UserDTO UserDTO) {
		User existing = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));
		existing.setName(UserDTO.getName());
		existing.setEmail(UserDTO.getEmail());
		existing.setAddress(UserDTO.getAddress());
		existing.setPhoneNumber(UserDTO.getPhoneNumber());
		return convertToDTO(userRepository.save(existing));
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	private UserDTO convertToDTO(User User) {
		return mapper.map(User, UserDTO.class);
	}

	private User convertToEntity(UserDTO UserDTO) {
		return mapper.map(UserDTO, User.class);
	}
}
