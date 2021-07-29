package dto;

import java.io.Serializable;

public class NotificationDto implements Serializable {

	private static final long serialVersionUID = 5202507057833114207L;

	private int notificationId;
	private String text;
	private String type;
	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public NotificationDto() {
	}

}
