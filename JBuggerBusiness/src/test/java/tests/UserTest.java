package tests;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import repositories.UserRepo;
import services.UserService;

public class UserTest {

	private UserRepo mockedUserRepo = Mockito.mock(UserRepo.class);

	@InjectMocks
	private UserService userService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

//	@Test
//	public void findUserByIdTest() throws Exception {
//		User mockedUser = new User();
//		int id = 11;
//		mockedUser.setUserId(id);
//		mockedUser.setFirstname("Mirian");
//		mockedUser.setLastname("Popesc");
//
//		mockedUserRepo.addUser(mockedUser);
//		Mockito.when(mockedUserRepo.findUserById(id)).thenReturn(mockedUser);
//		Assert.assertEquals(userService.findUserById(id).getUserId(), mockedUser.getUserId());
//		Assert.assertEquals(userService.findUserById(id).getFirstname(), mockedUser.getFirstname());
//		Assert.assertEquals(userService.findUserById(id).getLastname(), mockedUser.getLastname());
//	}

//	@Test
//	public void findUserByUsernameTest() throws Exception {
//		User mockedUser = new User();
//		int id = 16;
//		mockedUser.setUserId(id);
//		mockedUser.setFirstname("Andreea");
//		mockedUser.setLastname("Moldovan");
//		mockedUser.setUsername("moldoa");
//		String user = "moldoa";
//
//		mockedUserRepo.addUser(mockedUser);
//
//		Mockito.when(mockedUserRepo.findUserByUsername(user)).thenReturn(mockedUser);
//
//		Assert.assertEquals(userService.findUserByUsername(user).getUsername(), mockedUser.getUsername());
//
//	}
//
//
//	@Test
//	public void addUserTest() throws Exception {
//
//		User mockedUser = new User();
//		int id = 11;
//		mockedUser.setFirstname("Alexandra");
//		mockedUser.setLastname("Marginean");
//		mockedUser.setUsername("modlvoa");
//
//		UserDto userDto = UserMapper.mapUserToUserDto(mockedUser);

//		Mockito.when(mockedUserRepo.addUser(mockedUser)).thenReturn(mockedUser);
//		Assert.assertEquals(userService.addUser(Mockito.any(UserDto.class)).getFirstname(), mockedUser.getFirstname());
//		 Assert.assertEquals(userService.addUser(userDto).getLastname(),
//		 mockedUser.getLastname());
//	}

//	 @Test
//	 public void updateUserTest() throws Exception {
//	 	User mockedUser = new User();
//	 	int id=11;
//	 	mockedUser.setMobile("0790780221");;
//	 	mockedUser.setLastname("Marginean");
//	 	mockedUser.setUsername("margia");
//	 	mockedUser.setFirstname("Alexandra");
//	 	String user="margia";
//	 	UserDto userDto = UserMapper.mapUserToUserDto(mockedUser);
//	 	Mockito.when(mockedUserRepo.updateUser(Mockito.anyInt(), Mockito.any(User.class))).thenReturn(mockedUser);
//	 	Mockito.when(mockedUserRepo.findUserByUsername(user)).thenReturn(mockedUser);
//	 	Assert.assertEquals(userService.updateUser(userDto).getFirstname(), mockedUser.getFirstname());
//	 	Assert.assertEquals(userService.updateUser(userDto).getLastname(), mockedUser.getLastname());
//	 	Assert.assertEquals(userService.updateUser(userDto).getUsername(), mockedUser.getUsername());
//	 }

}

