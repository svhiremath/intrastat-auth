package com.intrastat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intrastat.dto.UserDataDTO;
import com.intrastat.dto.UserResponseDTO;
import com.intrastat.model.User;
import com.intrastat.service.UserService;

/**
 * 
 * @author shivaprasad
 *
 */

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping(method = RequestMethod.POST , value = "/signin")
	public String login(@RequestParam String username,  @RequestParam String password) {
		return userService.signin(username, password);
	}

	@RequestMapping(method = RequestMethod.POST , value = "/signup",  consumes = MediaType.APPLICATION_JSON_VALUE )
	public String signup(@RequestBody UserDataDTO user) {
		LOGGER.debug("user: ", user);
		return userService.signup(modelMapper.map(user, User.class));
	}

	@RequestMapping(method = RequestMethod.DELETE , value = "/{username}",  produces = MediaType.APPLICATION_JSON_VALUE )
	public String delete(@PathVariable String username) {
		LOGGER.debug("username: ", username);
		userService.delete(username);
		return username;
	}

	@RequestMapping(method = RequestMethod.GET , value = "/{username}",  produces = MediaType.APPLICATION_JSON_VALUE )
	public UserResponseDTO search(@PathVariable String username) {
		LOGGER.debug("username: ", username);
		return modelMapper.map(userService.search(username), UserResponseDTO.class);
	}

	@RequestMapping(method = RequestMethod.GET , value = "/getUser",  produces = MediaType.APPLICATION_JSON_VALUE )
	public UserResponseDTO getCurrentUser(HttpServletRequest req) {
		return modelMapper.map(userService.getCurrentUser(req), UserResponseDTO.class);
	}

	@GetMapping("/refresh")
	public String refresh(HttpServletRequest req) {
		return userService.refresh(req.getRemoteUser());
	}

	@RequestMapping(method = RequestMethod.GET , value = "/allusers",  produces = MediaType.APPLICATION_JSON_VALUE )
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}

}

