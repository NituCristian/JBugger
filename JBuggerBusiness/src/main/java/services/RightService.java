package services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dto.RightDto;
import entities.Right;
import entities.Role;
import mappers.RightMapper;
import repositories.RightRepo;
import repositories.RoleRepo;

@Stateless
public class RightService {

	@EJB
	private RightRepo rightRepo = new RightRepo();

	@EJB
	private RoleRepo roleRepo = new RoleRepo();

	public RightDto findRightById(int rightId) {
		Right right = rightRepo.findRightById(rightId);

		RightDto rightDTO = RightMapper.mapRightToRightDto(right);

		return rightDTO;
	}

	public void addRightToRole(RightDto rightDto, int roleId) throws Exception {
		try {
			Role role = roleRepo.findRoleById(roleId);
			Right right = RightMapper.mapRightDtoToRight(rightDto);
			role.getRights().add(right);
			roleRepo.saveRole(role);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void revokeRightFromRole(RightDto rightDto, int roleId) throws Exception {
		try {
			Role role = roleRepo.findRoleById(roleId);
			Right right = RightMapper.mapRightDtoToRight(rightDto);
			Right rightToDelete = null;
			for (Right r : role.getRights()) {
				if (r.getRightId() == right.getRightId() && r.getName().equals(right.getName())) {
					rightToDelete = r;
				}
			}
			if (rightToDelete != null) {
				right = rightToDelete;
			}
			role.getRights().remove(right);
			right.getRoles().remove(role);
			roleRepo.saveRole(role);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
