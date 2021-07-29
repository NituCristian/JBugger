package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * The persistent class for the bugs database table.
 *
 */
@Entity
@Table(name="bugs")
@NamedQuery(name="Bug.findAll", query="SELECT b FROM Bug b")
public class Bug implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bugId;

	@Lob
	private byte[] attachment;

	@NotNull(message="Description cannot be null")
	@Size(min = 250, message
	= "Description must have at least 250 characters")
	private String description;

	private String fixedInVersion;

	@NotNull(message="Severity cannot be null")
	private String severity;

	private String status;

	private String targetDate;

	@NotNull(message="Title cannot be null")
	private String title;

	@NotNull(message="Version cannot be null")
	private String version;

	//bi-directional many-to-one association to User
	// @ManyToOne
	@Column(name = "createdBy")
	private int createdBy;

	//bi-directional many-to-one association to User
	// @ManyToOne
	@Column(name = "assignedTo")
	private int assignedTo;

	public Bug() {
	}

	public int getBugId() {
		return this.bugId;
	}

	public void setBugId(int bugId) {
		this.bugId = bugId;
	}

	public byte[] getAttachment() {
		return this.attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFixedInVersion() {
		return this.fixedInVersion;
	}

	public void setFixedInVersion(String fixedInVersion) {
		this.fixedInVersion = fixedInVersion;
	}

	public String getSeverity() {
		return this.severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTargetDate() {
		return this.targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getAssignedTo() {
		return this.assignedTo;
	}

	public void setAssignedTo(int assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Bug(String title, String description, String version, String fixedInVersion, String targetDate,
			String severity, int createdBy, String status, int assignedTo, byte[] attachment) {
		super();
		this.attachment = attachment;
		this.description = description;
		this.fixedInVersion = fixedInVersion;
		this.severity = severity;
		this.status = status;
		this.targetDate = targetDate;
		this.title = title;
		this.version = version;
		this.createdBy = createdBy;
		this.assignedTo = assignedTo;
	}


}