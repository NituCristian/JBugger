package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dto.NotificationDto;
import entities.Notification;
import entities.User;
import mappers.NotificationMapper;
import repositories.NotificationRepo;

@Stateless
public class NotificationService {

	@EJB
	private NotificationRepo notificationRepo = new NotificationRepo();

	public List<NotificationDto> findAllByUserId(int userId) {
		List<Notification> notifications = notificationRepo.findNotificationByUserId(userId);
		List<NotificationDto> notificationsDto = new ArrayList<NotificationDto>();

		for (Notification notification : notifications) {
			notificationsDto.add(NotificationMapper.mapNotificationToNotificationDto(notification));
		}

		return notificationsDto;
	}

	public Notification addUpdateUserNotification(User user) {
		Notification notification = new Notification();

		notification.setType("USER_UPDATED");
		notification.setUserId(user.getUserId());

		String welcomeMessage = "UPDATE: " + user.getFirstname() + " " + user.getEmail();
		notification.setText(welcomeMessage);
		notificationRepo.addNotification(notification);

		return notification;
	}

}
