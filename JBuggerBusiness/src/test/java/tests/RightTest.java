package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import entities.Right;
import entities.User;
import repositories.RightRepo;
import repositories.UserRepo;
import services.RightService;
import services.UserService;

public class RightTest {

	private RightRepo mockedRightRepo = Mockito.mock(RightRepo.class);

	@InjectMocks
	private RightService rightService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findRightByIdTest() {
		Right mockedRight = new Right();
		int id = 11;
		mockedRight.setRightId(id);
		mockedRight.setName("USER_MANAGEMENT");

		mockedRightRepo.addRight(mockedRight);
		Mockito.when(mockedRightRepo.findRightById(id)).thenReturn(mockedRight);
		Assert.assertEquals(rightService.findRightById(id).getRightId(), mockedRight.getRightId());
		Assert.assertEquals(rightService.findRightById(id).getName(), mockedRight.getName());
	
	}

}
