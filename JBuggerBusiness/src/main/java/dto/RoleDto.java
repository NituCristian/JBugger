package dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RoleDto implements Serializable {
	private static final long serialVersionUID = -2507476771713469940L;

	private int roleId;
	private String name;
	private Set<RightDto> rights = new HashSet<RightDto>();

	public RoleDto() {
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<RightDto> getRights() {
		return rights;
	}

	public void setRights(Set<RightDto> rights) {
		this.rights = rights;
	}

}
