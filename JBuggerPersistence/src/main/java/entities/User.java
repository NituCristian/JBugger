package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import validators.CompanyEmail;

/**
 * The persistent class for the users database table.
 *
 */
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	private byte activated;

	public User(String firstname, String lastname, String password, String email, String username, String mobile,
			byte activated, Set<Role> roles) {
		super();
		this.activated = activated;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobile = mobile;
		this.password = password;
		this.username = username;
		this.roles = roles;
	}

	@NotNull
	@NotEmpty
	@Email
	@CompanyEmail(value = "@msg.group")
	private String email;

	@NotNull(message = "Firstname cannot be null")
	private String firstname;

	@NotNull(message = "Lastname cannot be null")
	private String lastname;

	@Size(min = 10, max = 12)
	@NotNull
	private String mobile;

	@NotNull(message = "Password cannot be null")
	@NotEmpty
	private String password;

	private String username;

	// bi-directional many-to-many association to Role
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany // (cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "idUser") }, inverseJoinColumns = {
			@JoinColumn(name = "idRole") })
	private Set<Role> roles = new HashSet<Role>();

	public void addRole(Role role) {
		this.roles.add(role);
	}

	// bi-directional many-to-one association to Notification
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "userId")
	private Set<Notification> notifications = new HashSet<Notification>();

	// bi-directional many-to-one association to Bug
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "createdBy")
	private Set<Bug> createdBy;

	// bi-directional many-to-one association to Bug
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "assignedTo")
	private Set<Bug> assignedTo;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public byte getActivated() {
		return this.activated;
	}

	public void setActivated(byte activated) {
		this.activated = activated;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}

	public Notification addNotification(Notification notification) {
		getNotifications().add(notification);

		return notification;
	}

	public Notification removeNotification(Notification notification) {
		getNotifications().remove(notification);

		return notification;
	}

	public Set<Bug> getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Set<Bug> createdBy) {
		this.createdBy = createdBy;
	}

	public Bug addCreatedBy(Bug createdBy) {
		this.getCreatedBy().add(createdBy);

		return createdBy;
	}

	public Bug removeCreatedBy(Bug createdBy) {
		this.getCreatedBy().remove(createdBy);

		return createdBy;
	}

	public Set<Bug> getAssignedTo() {
		return this.assignedTo;
	}

	public void setAssignedTo(Set<Bug> assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Bug addAssignedTo(Bug assignedTo) {
		this.getAssignedTo().add(assignedTo);

		return assignedTo;
	}

	public Bug removeAssignedTo(Bug assignedTo) {
		this.getAssignedTo().remove(assignedTo);

		return assignedTo;
	}

}
