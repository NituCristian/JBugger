package mappers;

import java.util.HashSet;
import java.util.Set;

import dto.RightDto;
import dto.RoleDto;
import entities.Right;
import entities.Role;

public class RoleMapper {
	public static Role mapRoleDtoToRole(RoleDto roleDto) {
		Role role = new Role();

		role.setRoleId(roleDto.getRoleId());
		role.setName(roleDto.getName());
		// role.setUsers(roleDto.getUsers());
		// role.setRights(roleDto.getRights());

		Set<Right> rights = new HashSet<Right>();

		for (RightDto rightDto : roleDto.getRights()) {
			Right right = RightMapper.mapRightDtoToRight(rightDto);
			rights.add(right);
		}

		role.setRights(rights);

		return role;
	}

	public static RoleDto mapRoleToRoleDto(Role role) {
		RoleDto roleDto = new RoleDto();

		roleDto.setRoleId(role.getRoleId());
		roleDto.setName(role.getName());
		// roleDto.setUsers(role.getUsers());
		// roleDto.setRights(role.getRights());

		Set<RightDto> rightDtoSet = new HashSet<RightDto>();

		for (Right right : role.getRights()) {
			RightDto rightDto = RightMapper.mapRightToRightDto(right);
			rightDtoSet.add(rightDto);
		}

		roleDto.setRights(rightDtoSet);

		return roleDto;
	}
}
