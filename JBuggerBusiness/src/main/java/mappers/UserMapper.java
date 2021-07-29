package mappers;

import java.util.HashSet;
import java.util.Set;

import dto.NotificationDto;
import dto.RoleDto;
import dto.UserDto;
import entities.Notification;
import entities.Role;
import entities.User;

public class UserMapper {

	public static User mapUserDtoToUser(UserDto userDto) {
		User user = new User();

		user.setUserId(userDto.getUserId());
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setMobile(userDto.getMobile());
		user.setFirstname(userDto.getFirstname());
		user.setLastname(userDto.getLastname());
		user.setEmail(userDto.getEmail());
		user.setActivated(userDto.getActivated());

		Set<Role> roles = new HashSet<Role>();

		for (RoleDto roleDto : userDto.getRoles()) {
			Role role = RoleMapper.mapRoleDtoToRole(roleDto);
			roles.add(role);
		}

		user.setRoles(roles);

		/*
		 * Set<Notification> notifications = new HashSet<Notification>();
		 * 
		 * for (NotificationDto notificationDto : userDto.getNotifications()) {
		 * Notification notification =
		 * NotificationMapper.mapNotificationDtoToNotification(notificationDto);
		 * notifications.add(notification); }
		 * 
		 * user.setNotifications(notifications);
		 */

		return user;
	}

	public static UserDto mapUserToUserDto(User user) {
		UserDto userDto = new UserDto();

		userDto.setUserId(user.getUserId());
		userDto.setUsername(user.getUsername());
		userDto.setPassword(user.getPassword());
		userDto.setMobile(user.getMobile());
		userDto.setFirstname(user.getFirstname());
		userDto.setLastname(user.getLastname());
		userDto.setEmail(user.getEmail());
		userDto.setActivated(user.getActivated());

		Set<RoleDto> roleDtoSet = new HashSet<RoleDto>();

		for (Role role : user.getRoles()) {
			RoleDto roleDto = RoleMapper.mapRoleToRoleDto(role);
			roleDtoSet.add(roleDto);
		}

		userDto.setRoles(roleDtoSet);

		Set<NotificationDto> notificationDtoSet = new HashSet<NotificationDto>();

		for (Notification notification : user.getNotifications()) {
			NotificationDto notificationDto = NotificationMapper.mapNotificationToNotificationDto(notification);
			notificationDtoSet.add(notificationDto);
		}

		userDto.setNotifications(notificationDtoSet);
		return userDto;
	}
}
