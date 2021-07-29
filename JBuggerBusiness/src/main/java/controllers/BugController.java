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

import dto.BugDto;
import entities.Bug;
import mappers.BugMapper;
import services.BugService;
import services.UserService;

@Path("/bugs")
public class BugController {

	@EJB
	private BugService bugService;
	@EJB
	private UserService userService;

	@GET
	@Path("/{bugId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BugDto getBugById(@PathParam("bugId") int id) {
		BugDto bug = bugService.findBugById(id);
		return bug;
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<BugDto> getAllBugs() {
		List<BugDto> bugs = bugService.findAll();
		return bugs;
	}

	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response addBug(BugDto bug) {
		try {
			BugDto bugDto = bugService.addBug(bug);
			String result = bugService.bugDtoToString(bugDto);
			return Response.status(201).entity(result).build();
		} catch (Exception e) {
			return Response.status(500).entity("{\"mes\":\"Bug cannot be added!\"}").build();
		}
	}

	@POST
	@Path("/update")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateBug(BugDto bug) {
		try {
			BugDto bugDto = bugService.updateBug(bug);
			String result = bugService.bugDtoToString(bugDto);
			return Response.status(201).entity(result).build();
		} catch (Exception e) {
			return Response.status(500).entity("{\"mes\":\"Bug cannot be updated!\"}").build();
		}
	}

	@POST
	@Path("/updateStatus")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateStatusBug(BugDto bug) {
		try {
			Bug bug2 = BugMapper.mapBugDtoToBug(bug);
			BugDto bugDto = bugService.updateBugStatus(bug2.getBugId(), bug2.getStatus());
			String result = bugService.bugDtoToString(bugDto);
			return Response.status(201).entity(result).build();
		} catch (Exception e) {
			return Response.status(500).entity("{\"mes\":\"Status could not be updated\"}").build();
		}
	}

	@POST
	@Path("/close")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response closeBug(BugDto bug) {
		try {
			BugDto bugDto = bugService.closeBug(BugMapper.mapBugDtoToBug(bug));
			String result = bugService.bugDtoToString(bugDto);
			return Response.status(201).entity(result).build();
		} catch (Exception e) {
			return Response.status(500).entity("{\"mes\":\"Bug could not be closed\"}").build();
		}
	}
}
