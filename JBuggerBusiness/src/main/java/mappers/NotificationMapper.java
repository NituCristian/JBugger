package mappers;

import dto.NotificationDto;
import entities.Notification;

public class NotificationMapper {
	public static Notification mapNotificationDtoToNotification(NotificationDto notificationDto) {
		Notification notification = new Notification();

		notification.setNotificationId(notificationDto.getNotificationId());
		notification.setText(notificationDto.getText());
		notification.setType(notificationDto.getType());
		notification.setUserId(notificationDto.getUserId());

		return notification;
	}

	public static NotificationDto mapNotificationToNotificationDto(Notification notification) {
		NotificationDto notificationDto = new NotificationDto();

		notificationDto.setNotificationId(notification.getNotificationId());
		notificationDto.setText(notification.getText());
		notificationDto.setType(notification.getType());
		notificationDto.setUserId(notification.getUserId());

		return notificationDto;
	}

}
