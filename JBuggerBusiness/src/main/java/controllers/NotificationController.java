package controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dto.NotificationDto;
import services.NotificationService;

@Path("/notifications")
public class NotificationController {

	@EJB
	private NotificationService notificationService;

	@GET
	@Path("/user/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<NotificationDto> getNotificationsByUserId(@PathParam("userId") int userId) {
		List<NotificationDto> notifications = notificationService.findAllByUserId(userId);

		return notifications;
	}

}
