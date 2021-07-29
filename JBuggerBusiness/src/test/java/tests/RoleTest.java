package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import entities.Right;
import entities.Role;
import repositories.RightRepo;
import repositories.RoleRepo;
import services.RightService;
import services.RoleService;

public class RoleTest {
	private RoleRepo mockedRoleRepo = Mockito.mock(RoleRepo.class);

	@InjectMocks
	private RoleService roleService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findRoleByIdTest() {
		Role mockedRole = new Role();
		int id = 11;
		mockedRole.setRoleId(id);
		mockedRole.setName("DEVELOPER");
		Mockito.when(mockedRoleRepo.findRoleById(id)).thenReturn(mockedRole);
		Assert.assertEquals(roleService.findRoleById(id).getRoleId(), mockedRole.getRoleId());
		Assert.assertEquals(roleService.findRoleById(id).getName(), mockedRole.getName());
	
	}
	
}
