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
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * The persistent class for the roles database table.
 *
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;

	private String name;

	//bi-directional many-to-many association to User
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(mappedBy="roles")
	private Set<User> users = new HashSet<User>();

	//bi-directional many-to-many association to Right
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany // (cascade = CascadeType.ALL)
	@JoinTable(
			name="roles_rights"
			, joinColumns={
					@JoinColumn(name="idRole")
			}
			, inverseJoinColumns={
					@JoinColumn(name="idRight")
			}
			)
	private Set<Right> rights = new HashSet<Right>();

	public void addUser(User user) {
		this.users.add(user);
	}

	public Role() {
	}

	public void addRight(Right right) {
		this.rights.add(right);
		right.getRoles().add(this);
	}

	public void removeRight(Right right) {
		this.rights.remove(right);
		right.getRoles().remove(this);
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Right> getRights() {
		return this.rights;
	}

	public Role(String name) {
		super();
		this.name = name;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}

}
