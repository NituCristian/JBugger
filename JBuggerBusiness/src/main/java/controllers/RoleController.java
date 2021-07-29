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
import services.RightService;
import services.RoleService;
import services.UserService;

@Path("/roles")
public class RoleController {

	@EJB
	private RoleService roleService;

	@EJB
	private UserService userService;

	@EJB
	private RightService rightService;

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<RoleDto> getAllRoles() {
		List<RoleDto> roles = roleService.findAll();
		return roles;
	}

	@GET
	@Path("/all/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<RoleDto> getAllRoles(@PathParam("userId") int userId) {
		List<RoleDto> roles = roleService.findUserRoles(userId);
		return roles;
	}

	@GET
	@Path("/allRights/{roleId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<RightDto> getAllRightsOfARole(@PathParam("roleId") int roleId) {
		List<RightDto> rights = roleService.getAllRightsOfARole(roleId);
		return rights;
	}

	@POST
	@Path("/addRight/{roleId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addRightToRole(@PathParam("roleId") int roleId, RightDto rightDto) {
		try {
			rightService.addRightToRole(rightDto, roleId);
			return Response.status(201).entity("{\"mes\":\"Right added\"}").build();
		} catch (Exception e) {
			return Response.status(501).entity("{\"mes\":\"Right could not be added\"}").build();
		}
	}

	@POST
	@Path("/revokeRight/{roleId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response revokeRightFromRole(@PathParam("roleId") int roleId, RightDto rightDto) {
		try {
			rightService.revokeRightFromRole(rightDto, roleId);
			return Response.status(201).entity("{\"mes\":\"Right revoked\"}").build();
		} catch (Exception e) {
			return Response.status(501).entity("{\"mes\":\"Right could not be revoked\"}").build();
		}
	}

}
