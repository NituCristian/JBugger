package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dto.RightDto;
import dto.RoleDto;
import entities.Right;
import entities.Role;
import entities.User;
import mappers.RightMapper;
import mappers.RoleMapper;
import repositories.RoleRepo;
import repositories.UserRepo;

@Stateless
public class RoleService {

	@EJB
	private RoleRepo roleRepo = new RoleRepo();

	@EJB
	private UserRepo userRepo = new UserRepo();

	public RoleDto findRoleById(int roleId) {
		Role role = roleRepo.findRoleById(roleId);
		RoleDto roleDTO = RoleMapper.mapRoleToRoleDto(role);
		return roleDTO;
	}

	public int findRoleByName(String name) {
		Role role = roleRepo.findRoleByName(name);
		return role.getRoleId();
	}

	public List<RoleDto> findAll() {
		List<Role> roles = roleRepo.findAll();
		List<RoleDto> rolesDto = new ArrayList<RoleDto>();

		for (Role role : roles) {
			rolesDto.add(RoleMapper.mapRoleToRoleDto(role));
		}

		return rolesDto;
	}

	/*
	 * public void addRightToRole(RightDto rightDto, int roleId) { Role role =
	 * roleRepo.findRoleById(roleId); Right right =
	 * RightMapper.mapRightDtoToRight(rightDto);
	 *
	 * role.getRights().add(right);
	 *
	 * roleRepo.saveRole(role); }
	 */

	public List<RoleDto> findUserRoles(int userId) {
		User user = userRepo.findUserById(userId);

		Set<Role> rolesForUser = user.getRoles();

		List<RoleDto> rolesDto = new ArrayList<RoleDto>();

		for (Role role : rolesForUser) {
			rolesDto.add(RoleMapper.mapRoleToRoleDto(role));
		}
		return rolesDto;
	}

	public void addRoleToUser(RoleDto roleDto, int userId) {
		User user = userRepo.findUserById(userId);
		Role role = RoleMapper.mapRoleDtoToRole(roleDto);
		user.getRoles().add(role);
		userRepo.saveUser(user);
	}

	public void revokeRoleFromUser(RoleDto roleDto, int userId) {
		User user = userRepo.findUserById(userId);
		Role role = RoleMapper.mapRoleDtoToRole(roleDto);

		Role roleToDelete = null;

		for (Role r : user.getRoles()) {
			if (r.getRoleId() == role.getRoleId() && r.getName().equals(role.getName())) {
				roleToDelete = r;
			}
		}

		if (roleToDelete != null) {
			role = roleToDelete;
		}
		user.getRoles().remove(role);
		role.getUsers().remove(user);
		userRepo.saveUser(user);
	}

	public List<RightDto> getAllRightsOfARole(int roleId) {
		List<RightDto> rights = new ArrayList<RightDto>();

		Role role = roleRepo.findRoleById(roleId);

		Set<Right> rightsForRole = role.getRights();

		List<RightDto> rightsDto = new ArrayList<RightDto>();

		for (Right right : rightsForRole) {
			rightsDto.add(RightMapper.mapRightToRightDto(right));
		}
		return rightsDto;
	}
}
