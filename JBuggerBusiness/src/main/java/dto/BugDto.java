package dto;

import java.io.Serializable;

public class BugDto implements Serializable {
	private static final long serialVersionUID = 4188828292157997727L;
	private int bugId;
	private String attachment;
	private String description;
	private String fixedInVersion;
	private String severity;
	private String status;
	private String targetDate;
	private String title;
	private String version;
	private int createdBy;
	private int assignedTo;

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(int assignedTo) {
		this.assignedTo = assignedTo;
	}

	public BugDto() {
	}

	public int getBugId() {
		return this.bugId;
	}

	public void setBugId(int bugId) {
		this.bugId = bugId;
	}

	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String attachment) {
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

}
