package tests;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import repositories.BugRepo;
import services.BugService;

public class BugTest {
	private BugRepo mockedBugRepo = Mockito.mock(BugRepo.class);

	@InjectMocks
	private BugService bugService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

//	@Test
//	public void addBugTest() throws Exception {
//		Bug mockedBug = new Bug();
//		int id = 15;
//		String fixedInVersion = "";
//		String targetDate = "";
//		String severity = "";
//		int createdBy = 0;
//		String status = "";
//		int assignedTo = 0;
//		byte[] attachment = null;
//		mockedBug.setTitle("Unknown behaviour");
//		mockedBug.setDescription("1wwwaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
//				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
//				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
//				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
//				+ "aaaaaaaaaaaaaaaaaaaaaa");
//		mockedBug.setVersion("0");
//		mockedBug.setFixedInVersion(fixedInVersion);
//		mockedBug.setTargetDate(targetDate);
//		mockedBug.setSeverity(severity);
//		mockedBug.setCreatedBy(createdBy);
//		mockedBug.setStatus(status);
//		mockedBug.setAssignedTo(assignedTo);
//		mockedBug.setAttachment(attachment);
//
//		BugDto bugDto = BugMapper.mapBugToBugDto(mockedBug);
//
//		Mockito.when(mockedBugRepo.addBug(mockedBug)).thenReturn(mockedBug);
//		Assert.assertEquals(bugService.addBug(bugDto).getTitle(), mockedBug.getTitle());
//	}
//
//	  @Test
//	  public void updateBugTest() throws Exception {
//		  Bug mockedBug= new Bug(); int id=19; mockedBug.setBugId(id);
//		  mockedBug.setTitle("Unknown behaviour"); mockedBug.setDescription("");
//		  mockedBug.setCreatedBy(0); mockedBug.setAssignedTo(0);
//		  mockedBug.setSeverity("fatal"); mockedBug.setVersion("1");
//		  mockedBug.setFixedInVersion("2"); mockedBug.setAttachment(null);
//		  BugDto bugDto = BugMapper.mapBugToBugDto(mockedBug);
//		  Mockito.when(mockedBugRepo.updateBug(mockedBug)).thenReturn(mockedBug);
//		  Assert.assertEquals(bugService.updateBug(bugDto).getTitle(),mockedBug.getTitle());
//	  }
}
