package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.json.simple.JSONObject;

import dto.BugDto;
import entities.Bug;
import mappers.BugMapper;
import repositories.BugRepo;

@Stateless
public class BugService {

	@EJB
	private BugRepo bugRepo = new BugRepo();

	public BugDto findBugById(int bugId) {
		Bug bug = bugRepo.findBugById(bugId);
		BugDto bugDTO = BugMapper.mapBugToBugDto(bug);
		return bugDTO;
	}

	public List<BugDto> findAll() {
		List<Bug> bugs = bugRepo.findAll();
		List<BugDto> bugsDto = new ArrayList<BugDto>();
		for (Bug bug : bugs) {
			bugsDto.add(BugMapper.mapBugToBugDto(bug));
		}
		return bugsDto;
	}

	private byte[] addAttachment(String path) {
		File file = new File(path);
		byte[] imageData = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(imageData);
			fileInputStream.close();
			return imageData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BugDto addBug(BugDto bugDto) throws Exception {
		try {
			Bug bug = BugMapper.mapBugDtoToBug(bugDto);
			BugDto bugDto2 = BugMapper.mapBugToBugDto(bugRepo.addBug(bug));
			return bugDto2;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public BugDto updateBug(BugDto bugDto) throws Exception {
		try {
			Bug bug = BugMapper.mapBugDtoToBug(bugDto);
			BugDto bugDto2 = BugMapper.mapBugToBugDto(bugRepo.updateBug(bug));
			return bugDto2;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public BugDto updateBugStatus(int bugId, String newStatus) throws Exception {
		try {
			Bug savedBug = bugRepo.findBugById(bugId);
			String oldStatus = savedBug.getStatus();

			if (oldStatus.equalsIgnoreCase("new")) {
				if (newStatus.equalsIgnoreCase("in progress")) {
					newStatus = "in_progress";
					bugRepo.updateStatus(bugId, newStatus);

				} else if (newStatus.equalsIgnoreCase("rejected")) {
					bugRepo.updateStatus(bugId, newStatus);
				} else {
					return null;
				}
			} else if (oldStatus.equalsIgnoreCase("rejected")) {
				if (newStatus.equalsIgnoreCase("closed")) {
					bugRepo.updateStatus(bugId, newStatus);
				} else {
					return null;
				}
			} else if (oldStatus.equalsIgnoreCase("fixed")) {
				if (newStatus.equalsIgnoreCase("open") || newStatus.equalsIgnoreCase("closed")) {
					bugRepo.updateStatus(bugId, newStatus);
				} else {
					return null;
				}
			} else if (oldStatus.equalsIgnoreCase("in_progress")) {
				if (newStatus.equalsIgnoreCase("fixed") || newStatus.equalsIgnoreCase("rejected")) {
					bugRepo.updateStatus(bugId, newStatus);

				} else if (newStatus.equalsIgnoreCase("info needed")) {
					newStatus = "info_needed";
					bugRepo.updateStatus(bugId, newStatus);

				} else {
					return null;
				}
			} else if (oldStatus.equalsIgnoreCase("info_needed")) {
				if (newStatus.equalsIgnoreCase("in progress")) {
					newStatus = "in_progress";
					bugRepo.updateStatus(bugId, newStatus);

				} else {
					return null;
				}
			} else {
				return null;
			}
			BugDto bugDto = BugMapper.mapBugToBugDto(bugRepo.findBugById(bugId));
			return bugDto;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public BugDto closeBug(Bug bug) throws Exception {
		try {
			if (bug.getStatus().equalsIgnoreCase("fixed")) {
				bug.setStatus("closed");
				bugRepo.updateBug(bug);
			}
			BugDto bugDto = BugMapper.mapBugToBugDto(bug);
			return bugDto;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public String bugDtoToString(BugDto bugDto) {
		JSONObject parameters = new JSONObject();

		parameters.put("bugId", bugDto.getBugId());
		parameters.put("title", bugDto.getTitle());
		parameters.put("description", bugDto.getDescription());
		parameters.put("version", bugDto.getVersion());
		parameters.put("fixedInVersion", bugDto.getFixedInVersion());
		parameters.put("targetDate", bugDto.getTargetDate());
		parameters.put("severity", bugDto.getSeverity());
		parameters.put("createdBy", bugDto.getCreatedBy());
		parameters.put("status", bugDto.getStatus());
		parameters.put("assignedTo", bugDto.getAssignedTo());
		parameters.put("attachment", bugDto.getAttachment());

		StringWriter out = new StringWriter();
		try {
			parameters.writeJSONString(out);
		} catch (IOException e2) {
			System.out.println(e2.getMessage());
			e2.printStackTrace();
		}

		String body = out.toString();
		return body;
	}

}
