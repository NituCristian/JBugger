package dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 3910274216858070062L;
	private int userId;
	private byte activated;
	private String email;
	private String firstname;
	private String lastname;
	private String mobile;
	private String password;
	private String username;
	private Set<RoleDto> roles = new HashSet<RoleDto>();
	private Set<NotificationDto> notifications = new HashSet<NotificationDto>();

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

	public Set<NotificationDto> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<NotificationDto> notifications) {
		this.notifications = notifications;
	}

	public UserDto() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public byte getActivated() {
		return activated;
	}

	public void setActivated(byte activated) {
		this.activated = activated;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}
