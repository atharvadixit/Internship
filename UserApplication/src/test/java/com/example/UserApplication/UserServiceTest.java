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
        SQLUser sqlUser=new SQLUser("ABC", "abc@gmail.com", 9881243118L, "XYZ", "Area", "Pune");

        Mockito.when(testSqlUserRepository.save(sqlUser)).thenReturn(sqlUser);

        assertThat(userService.insertInSqlByRepository(sqlUser)).isEqualTo(sqlUser);

       Mockito.verify(testSqlUserRepository,Mockito.times(1)).save(sqlUser);
    }

    @Test
    public void testInsertInMongoByRepository()
    {
        MongoUser mongoUser=new MongoUser("ABC", "abc@gmail.com", 9881243118L, new Address("XYZ", "Area", "Pune"));

        Mockito.when(testMongoUserRepository.save(mongoUser)).thenReturn(mongoUser);

        assertThat(userService.insertInMongoByRepository(mongoUser)).isEqualTo(mongoUser);

        Mockito.verify(testMongoUserRepository,Mockito.times(1)).save(mongoUser);
    }

//    @Test
//    public void testDeleteDataFromMySQLService()
//    {
//        SQLUser sqlUser=new SQLUser("ABC", "abc@gmail.com", 9881243118L, "XYZ", "Area", "Pune");
//
//        Mockito.when(testSqlUserRepository.existsById("abc@gmail.com")).thenReturn(false);
//        Mockito.when(testSqlUserRepository.deleteById(("abc@gmail.com")).thenReturn();
//        assertFalse(testSqlUserRepository.existsById(sqlUser.getEmail()));
//
//        Mockito.verify(testSqlUserRepository,Mockito.times(1)).deleteById(sqlUser.getEmail());
//    }

    @Test
    public void testGetDataFromMySQLService()
    {
        SQLUser sqlUser=new SQLUser("ABC", "abc@gmail.com", 9881243118L, "XYZ", "Area", "Pune");

        SQLUser sqlUser1=new SQLUser("DEF", "def@gmail.com", 3881243118L, "Street", "Area", "Pune");

        List<SQLUser> userList = List.of(sqlUser,sqlUser1);
        Mockito.when(testSqlUserRepository.findByCity("Pune")).thenReturn(userList);

        assertThat(userService.getDataFromMySQLService(sqlUser.getCity())).isEqualTo(userList);

        Mockito.verify(testSqlUserRepository,Mockito.times(1)).findByCity(sqlUser.getCity());
    }

    @Test
    public void testGetDataFromMongoService()
    {
        MongoUser mongoUser=new MongoUser("ABC", "abc@gmail.com", 9881243118L, new Address("XYZ", "Area", "Pune"));

        MongoUser mongoUser1=new MongoUser("DEF", "def@gmail.com", 9881243118L, new Address("Street", "Katraj", "Pune"));


        List<MongoUser> userList = List.of(mongoUser,mongoUser1);

        Mockito.when(testMongoUserRepository.findByCity("Pune")).thenReturn(userList);

        assertThat(userService.getDataFromMongoService(mongoUser.getAddress().getCity())).isEqualTo(userList);

        Mockito.verify(testMongoUserRepository,Mockito.times(1)).findByCity(mongoUser.getAddress().getCity());
    }

}
