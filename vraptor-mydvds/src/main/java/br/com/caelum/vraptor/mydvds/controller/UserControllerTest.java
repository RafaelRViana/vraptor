package br.com.caelum.vraptor.mydvds.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.mydvds.dao.UserDao;
import br.com.caelum.vraptor.mydvds.interceptor.UserInfo;
import br.com.caelum.vraptor.mydvds.model.User;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;

/**
 * Test class for UserController
 *
 * @author Lucas Cavalcanti
 *
 */
public class UserControllerTest {


	private Mockery mockery;
	private UserDao dao;
	private HttpSession session;
	private UserInfo userInfo;
	private UserController controller;
	private MockResult result;

	@Before
	public void setUp() throws Exception {
		mockery = new Mockery();

		dao = mockery.mock(UserDao.class);
		session = mockery.mock(HttpSession.class);

		//ignoring first invocation of session.getAttribute
		mockery.checking(new Expectations() {
			{
				one(session).getAttribute(UserInfo.CURRENT_USER);
				will(returnValue(null));
			}
		});

		userInfo = new UserInfo(session);

		result = new MockResult();
		Validator validator = new MockValidator();

		controller = new UserController(dao, userInfo, result, validator);
	}

	@Test
	public void listingAllUsersWillNotExposeTheirLoginAndPassword() throws Exception {
		User user = new User();
		user.setId(1l);
		user.setName("John");
		user.setLogin("john");
		user.setPassword("youwontknow");

		userListingWillContain(user);

		controller.list();

		// this is the way we can inspect outjected objects on result:
		// we can assign without casting, because of generic return
		List<User> users = result.included("users");
		assertThat(users.size(), is(1));
		assertThat(users.get(0).getLogin(), is(nullValue()));
		assertThat(users.get(0).getPassword(), is(nullValue()));
		assertThat(users.get(0).getId(), is(1l));
		assertThat(users.get(0).getName(), is("John"));
	}

	private void userListingWillContain(final User user) {
		mockery.checking(new Expectations() {
			{
				one(dao).listAll();
				will(returnValue(Collections.singletonList(user)));
			}
		});
	}
}
