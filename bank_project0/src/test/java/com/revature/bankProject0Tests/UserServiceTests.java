package com.revature.bankProject0Tests;

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
        mockUsers.add( new User(1,"troy","davis","tmd1990","password", Role.ADMIN));
    }

    @After
    public void teardown(){
        sut = null;

        mockUsers.removeAll(mockUsers);

        msg = null;
    }

    @Test
    public void authenticateWithValidCredentials(){

        //arrange
        msg = "when given a valid user ";
        User expectedUser = new User(1,"troy","davis","admin","admin", Role.ADMIN);
        Mockito.when(mockUserRepo.findUsersByCredentials(Mockito.anyString(),Mockito.anyString()))
                .thenReturn(Optional.of(expectedUser));
        //act
        User actualResult = sut.authenticate("admin","admin");

        //assert
        Assert.assertEquals(expectedUser,actualResult);

    }

}
