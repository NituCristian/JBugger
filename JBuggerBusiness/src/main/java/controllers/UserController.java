package controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dto.RightDto;
import dto.RoleDto;
import dto.UserDto;
import services.NotificationService;
import services.RoleService;
import services.UserService;

@Path("/users")
public class UserController {

	@EJB
	private UserService userService;

	@EJB
	private NotificationService notificationService;

	@EJB
	private RoleService roleService;

	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserDto getUserById(@PathParam("userId") int id) {
		UserDto user = userService.findUserById(id);
		return user;
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<UserDto> getAllUsers() {
		List<UserDto> users = userService.findAll();
		return users;
	}

	@GET
	@Path("/allRights/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<RightDto> getAllRightsUser(@PathParam("userId") int userId) {
		List<RightDto> rights = userService.findAllRightsUser(userId);
		return rights;
	}

	@POST
	@Path("/login")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response login(UserDto user) {
		try {
			UserDto userDto = userService.login(user);
			String result = userService.userDtoToString(userDto);
			return Response.status(201).entity(result).build();
		} catch (Exception e) {
			return Response.status(500).entity("{\"mes\":\"Unauthorized\"}").build();
		}
	}

	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response addUser(UserDto user) {
		try {
			UserDto userDto = userService.addUser(user);
			String result = userService.userDtoToString(userDto);
			return Response.status(201).entity(result).build();
		} catch (Exception e) {
			return Response.status(500).entity("{\"mes\":\"User cannot be added!\"}").build();
		}
	}

	@POST
	@Path("/update")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateUser(UserDto user) {
		try {
			UserDto userDto = userService.updateUser(user);

//			User user2 = UserMapper.mapUserDtoToUser(userDto);
//			Notification notification = notificationService.addUpdateUserNotification(user2);
//
//			userDto.getNotifications().add(NotificationMapper.mapNotificationToNotificationDto(notification));

			String result = userService.userDtoToString(userDto);
			return Response.status(201).entity(result).build();
		} catch (Exception e) {
			return Response.status(500).entity("{\"mes\":\"User could not be updated\"}").build();
		}
	}

	@POST
	@Path("/deactivate/{userId}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response deactivateUser(@PathParam("userId") int userId) throws Exception {
		try {
			userService.deactivateUser(userId);
			return Response.status(201).entity("{\"mes\":\"User deactivated\"}").build();
		} catch (Exception e) {
			return Response.status(500).entity("{\"mes\":\"User could not be activated\"}").build();
		}
	}

	@POST
	@Path("/activate/{userId}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response activateUser(@PathParam("userId") int userId) throws Exception {
		try {
			userService.activateUser(userId);
			return Response.status(200).entity("{\"mes\":\"User activated\"}").build();
		} catch (Exception e) {
			return Response.status(500).entity("{\"mes\":\"User could not be activated\"}").build();
		}
	}

	@POST
	@Path("/addRole/{userId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addRoleToUser(@PathParam("userId") int userId, RoleDto roleDto) {
		roleService.addRoleToUser(roleDto, userId);
		return Response.status(201).entity("{\"mes\":\"Role added\"}").build();
	}

	@POST
	@Path("/revokeRole/{userId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response revokeRoleFromUser(@PathParam("userId") int userId, RoleDto roleDto) {
		roleService.revokeRoleFromUser(roleDto, userId);
		return Response.status(201).entity("{\"mes\":\"Role revoked\"}").build();
	}

}
