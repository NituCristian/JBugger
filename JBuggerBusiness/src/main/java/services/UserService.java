package services;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONObject;

import dto.RightDto;
import dto.RoleDto;
import dto.UserDto;
import entities.Bug;
import entities.Role;
import entities.User;
import mappers.UserMapper;
import repositories.BugRepo;
import repositories.UserRepo;

@Stateless
public class UserService {

	@EJB
	private UserRepo userRepo = new UserRepo();

	@EJB
	private BugRepo bugRepo = new BugRepo();

	private RoleService roleService = new RoleService();

	public UserDto findUserById(int id) {
		User user = userRepo.findUserById(id);
		UserDto userDTO = UserMapper.mapUserToUserDto(user);
		return userDTO;
	}

	public UserDto findUserByUsername(String username) {
		User user = userRepo.findUserByUsername(username);
		UserDto userDTO = UserMapper.mapUserToUserDto(user);
		return userDTO;
	}

	public User findUserByIdNoDto(int id) {
		User user = userRepo.findUserById(id);
		return user;
	}

	public List<UserDto> findAll() {
		List<User> users = userRepo.findAll();
		List<UserDto> usersDto = new ArrayList<UserDto>();

		for (User user : users) {
			usersDto.add(UserMapper.mapUserToUserDto(user));
		}
		return usersDto;
	}

	public List<RightDto> findAllRightsUser(int userId) {
		User user = userRepo.findUserById(userId);
		List<RightDto> rightsDto = new ArrayList<RightDto>();
		RoleService roleService = new RoleService();
		Set<Role> roles;
		try {
			roles = user.getRoles();
		} catch (Exception e) {
			return rightsDto;
		}
		for (Role role : roles) {
			List<RightDto> rights = roleService.getAllRightsOfARole(role.getRoleId());
			if (rights.size() != 0) {
				rightsDto.addAll(rights);
			}
		}
		return rightsDto;
	}

	public UserDto login(UserDto userDto) throws Exception {
		String passwordEncoded = DigestUtils.sha256Hex(userDto.getPassword());
		userDto.setPassword(passwordEncoded);
		User user = UserMapper.mapUserDtoToUser(userDto);
		try {
			User receivedUser = userRepo.findUserByUsernameAndPassword(user);
			UserDto userDto2 = UserMapper.mapUserToUserDto(receivedUser);
			return userDto2;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public String generateUsername(UserDto userDto) {
		String result;
		result = userDto.getLastname().substring(0, 5).toLowerCase();
		result += Character.toString(userDto.getFirstname().charAt(0)).toLowerCase();

		if (userRepo.findUserByUsername(result) != null) {
			result += Character.toString(userDto.getFirstname().charAt(1));
		}
		return result;
	}

	public UserDto addUser(UserDto userDto) throws Exception {
		String username = this.generateUsername(userDto);
		byte activated = 1;
		try {
			String passwordEncoded = DigestUtils.sha256Hex(userDto.getPassword());
			userDto.setUsername(username);
			userDto.setPassword(passwordEncoded);
			userDto.setActivated(activated);

			for (RoleDto roleDto : userDto.getRoles()) {
				roleDto.setRoleId(roleService.findRoleByName(roleDto.getName()));
			}
			User user = UserMapper.mapUserDtoToUser(userDto);
			UserDto userDto2 = UserMapper.mapUserToUserDto(userRepo.addUser(user));
			return userDto2;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public UserDto updateUser(UserDto userDto) throws Exception {
		try {
			User savedUser = userRepo.findUserByUsername(userDto.getUsername());
			String passwordEncoded = DigestUtils.sha256Hex(userDto.getPassword());
			userDto.setPassword(passwordEncoded);

			for (RoleDto roleDto : userDto.getRoles()) {
				roleDto.setRoleId(roleService.findRoleByName(roleDto.getName()));
			}

			userDto.setUserId(savedUser.getUserId());
			Set<RoleDto> roles = userDto.getRoles();
			Set<RoleDto> emptySet = new HashSet<RoleDto>();

			if (savedUser.getRoles().size() != 0) {
				userDto.setRoles(emptySet);
				userRepo.updateUser(UserMapper.mapUserDtoToUser(userDto));
				userDto.setRoles(roles);
			}
			User user = userRepo.updateUser(UserMapper.mapUserDtoToUser(userDto));
			UserDto userDto2 = UserMapper.mapUserToUserDto(user);

			return userDto2;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public boolean deactivateUser(int userId) throws Exception {
		List<Bug> bugs = bugRepo.bugsAssignedToUser(userId);
		if (bugs.size() == 0) {
			userRepo.deactivateUser(userId);
			return true;
		}
		return false;
	}

	public boolean activateUser(int userId) throws Exception {
		return userRepo.activateUser(userId);
	}

	public String userDtoToString(UserDto userDto) {
		JSONObject parameters = new JSONObject();

		parameters.put("firstname", userDto.getFirstname());
		parameters.put("lastname", userDto.getLastname());
		parameters.put("email", userDto.getEmail());
		parameters.put("mobile", userDto.getMobile());
		parameters.put("username", userDto.getUsername());
		parameters.put("password", userDto.getPassword());
		parameters.put("activated", userDto.getActivated());
		parameters.put("userId", userDto.getUserId());

		StringWriter out = new StringWriter();
		try {
			parameters.writeJSONString(out);
		} catch (IOException e2) {
			System.out.println(e2.getMessage());
			e2.printStackTrace();
		}
		String body = out.toString();
		return body;
	}

}
