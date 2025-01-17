package com.jspiders.springmvc.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jspiders.springmvc.dao.UserDAO;
import com.jspiders.springmvc.dto.CarDTO;
import com.jspiders.springmvc.dto.UserDTO;

@Component
public class UserService {
	@Autowired
	private UserDAO userDAO;

	public void addUser(String email, String password) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(email);
		userDTO.setPassword(password);
		userDTO.setCars(new ArrayList<CarDTO>());
		userDAO.addUser(userDTO);
	}

	public UserDTO checkUser(String email, String password) {
		List<UserDTO> users = userDAO.findAllUsers();
		UserDTO signedInUser = null;
		for (UserDTO user : users) {
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
				signedInUser = user;
				break;
			}
		}
		return signedInUser;
	}

	public void updateUser(UserDTO signedInUser, CarDTO addedCar) {
		userDAO.updateUser(signedInUser.getId(), addedCar.getId());
	}
}
