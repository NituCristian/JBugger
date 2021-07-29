package mappers;

import java.util.Base64;

import dto.BugDto;
import entities.Bug;

public class BugMapper {
	public static Bug mapBugDtoToBug(BugDto bugDto) {
		Bug bug = new Bug();

		byte[] decodedString;

		if (bugDto.getAttachment() != null) {
			decodedString = Base64.getDecoder().decode(bugDto.getAttachment().split("base64,")[1]);
			bug.setAttachment(decodedString);
		}
		else {
			bug.setAttachment(null);
		}

		bug.setBugId(bugDto.getBugId());

		bug.setDescription(bugDto.getDescription());
		bug.setFixedInVersion(bugDto.getFixedInVersion());
		bug.setSeverity(bugDto.getSeverity());
		bug.setStatus(bugDto.getStatus());
		bug.setTargetDate(bugDto.getTargetDate());
		bug.setTitle(bugDto.getTitle());
		bug.setVersion(bugDto.getVersion());
		bug.setCreatedBy(bugDto.getCreatedBy());
		bug.setAssignedTo(bugDto.getAssignedTo());

		return bug;
	}

	public static BugDto mapBugToBugDto(Bug bug) {
		BugDto bugDto = new BugDto();

		byte[] encodedString = null;
		if (bug.getAttachment() != null) {
			encodedString = Base64.getEncoder().encode(new String(bug.getAttachment()).getBytes());
			bugDto.setAttachment(new String(encodedString));
		}
		else {

			bugDto.setAttachment(null);
		}

		bugDto.setBugId(bug.getBugId());
		bugDto.setDescription(bug.getDescription());
		bugDto.setFixedInVersion(bug.getFixedInVersion());
		bugDto.setSeverity(bug.getSeverity());
		bugDto.setStatus(bug.getStatus());
		bugDto.setTargetDate(bug.getTargetDate());
		bugDto.setTitle(bug.getTitle());
		bugDto.setVersion(bug.getVersion());
		bugDto.setCreatedBy(bug.getCreatedBy());
		bugDto.setAssignedTo(bug.getAssignedTo());

		return bugDto;
	}

}