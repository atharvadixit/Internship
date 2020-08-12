package com.example.UserApplication;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private MongoUserRepository testMongoUserRepository;

    @MockBean
    private  SQLUserRepository testSqlUserRepository;

    @Test
    public void testInsertInSqlByRepository()
    {
        SQLUser sqlUser=new SQLUser();
        sqlUser.setName("ABC");
        sqlUser.setEmail("abc@gmail.com");
        sqlUser.setContact(Long.valueOf(988124311));
        sqlUser.setStreet("XYZ");
        sqlUser.setArea("Area");
        sqlUser.setCity("Pune");

        Mockito.when(testSqlUserRepository.save(sqlUser)).thenReturn(sqlUser);

        assertThat(userService.insertInSqlByRepository(sqlUser)).isEqualTo(sqlUser);
    }

    @Test
    public void testInsertInMongoByRepository()
    {
        MongoUser mongoUser=new MongoUser();
        mongoUser.setName("ABC");
        mongoUser.setEmail("abc@gmail.com");
        mongoUser.setContact(Long.valueOf(988124311));
        mongoUser.setAddress(new Address("XYZ", "Area", "Pune"));

        Mockito.when(testMongoUserRepository.save(mongoUser)).thenReturn(mongoUser);

        assertThat(userService.insertInMongoByRepository(mongoUser)).isEqualTo(mongoUser);
    }

    @Test
    public void testDeleteDataFromMySQLService()
    {
        SQLUser sqlUser=new SQLUser();
        sqlUser.setName("ABC");
        sqlUser.setEmail("abc@gmail.com");
        sqlUser.setContact(Long.valueOf(988124311));
        sqlUser.setStreet("XYZ");
        sqlUser.setArea("Area");
        sqlUser.setCity("Pune");

        Mockito.when(testSqlUserRepository.existsById(sqlUser.email)).thenReturn(true);
        Mockito.when(testSqlUserRepository.existsById(sqlUser.getEmail())).thenReturn(false);

        assertFalse(testSqlUserRepository.existsById(sqlUser.getEmail()));
    }

    @Test
    public void testGetDataFromMySQLService()
    {
        SQLUser sqlUser=new SQLUser();
        sqlUser.setName("ABC");
        sqlUser.setEmail("abc@gmail.com");
        sqlUser.setContact(Long.valueOf(988124311));
        sqlUser.setStreet("XYZ");
        sqlUser.setArea("Area");
        sqlUser.setCity("Pune");

        SQLUser sqlUser1=new SQLUser();
        sqlUser1.setName("DEF");
        sqlUser1.setEmail("def@gmail.com");
        sqlUser1.setContact(Long.valueOf(735000038));
        sqlUser1.setStreet("PQR");
        sqlUser1.setArea("Katraj");
        sqlUser1.setCity("Pune");

        List<SQLUser> userList = new ArrayList<>();
        userList.add(sqlUser);
        userList.add(sqlUser1);

        Mockito.when(testSqlUserRepository.findAll()).thenReturn(userList);

        assertThat(userService.getDataFromMySQLService()).isEqualTo(userList);
    }

    @Test
    public void testGetDataFromMongoService()
    {
        MongoUser mongoUser=new MongoUser();
        mongoUser.setName("ABC");
        mongoUser.setEmail("abc@gmail.com");
        mongoUser.setContact(Long.valueOf(988124311));
        mongoUser.setAddress(new Address("XYZ", "Area", "Pune"));

        MongoUser mongoUser1=new MongoUser();
        mongoUser1.setName("DEF");
        mongoUser1.setEmail("def@gmail.com");
        mongoUser1.setContact(Long.valueOf(735000038));
        mongoUser1.setAddress(new Address("PQR", "Katraj", "Pune"));

        List<MongoUser> userList = new ArrayList<>();
        userList.add(mongoUser);
        userList.add(mongoUser1);

        Mockito.when(testMongoUserRepository.findAll()).thenReturn(userList);

        assertThat(userService.getDataFromMongoService()).isEqualTo(userList);
    }

}
