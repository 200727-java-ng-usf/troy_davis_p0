package com.revature.bankProject0Tests.services;

import com.revature.bankProject0.exceptions.AuthenticationException;
import com.revature.bankProject0.exceptions.InvalidRequestException;
import com.revature.bankProject0.models.Role;
import com.revature.bankProject0.models.User;
import com.revature.bankProject0.repositories.UserRepository;
import com.revature.bankProject0.services.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserServiceTests {

    private String msg;

    private UserService sut;

    private UserRepository mockUserRepo = Mockito.mock(UserRepository.class);

    Set<User> mockUsers = new HashSet<>();

    @Before
    public void setup(){
        sut = new UserService(mockUserRepo);
        mockUsers.add( new User(1,"troy","davis","tmd1990","password", "test@g.co",Role.ADMIN));
        mockUsers.add( new User(1,"kaila","davis","kay1990","password", "kay@g.co",Role.BASIC_USER));
    }

    @After
    public void teardown(){
        sut = null;

        mockUsers.removeAll(mockUsers);

        msg = null;
    }

    @Test(expected = AuthenticationException.class)
    public void authenticateWithInvalidCredentials() {
        //try to log in with dad credentials
        sut.authenticate("garbage", "user");
    }

    @Test
    public void isUserValidTestwithBadCredentials(){
        //test validation with an empty user
        User newUser = new User();
        Assert.assertFalse(sut.isUserValid(newUser));
    }

    @Test(expected = InvalidRequestException.class)
    public void testReisterWithBadCredentials(){
        User newUser = new User();
        sut.register(newUser);
    }
}
