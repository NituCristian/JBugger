package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * The persistent class for the rights database table.
 *
 */
@Entity
@Table(name="rights")
@NamedQuery(name="Right.findAll", query="SELECT r FROM Right r")
public class Right implements Serializable {
	private static final long serialVersionUID = 1L;

	public Right(String name) {
		super();
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rightId;

	private String name;

	//bi-directional many-to-many association to Role
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(mappedBy = "rights")
	private Set<Role> roles = new HashSet<Role>();

	public void addRole(Role role) {
		this.roles.add(role);
		role.getRights().add(this);
	}

	public void removeRole(Role role) {
		this.roles.remove(role);
		role.getRights().remove(this);
	}

	public Right() {
	}

	public int getRightId() {
		return this.rightId;
	}

	public void setRightId(int rightId) {
		this.rightId = rightId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
