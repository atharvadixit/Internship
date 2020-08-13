package com.example.UserApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private  UserService userService;


    @Test
    public void testGetDataFromSql() throws Exception{
        String city = "Pune";
        SQLUser sqlUser = new SQLUser("ABC", "abc@gmail.com", 1234567L, "Street", "Katraj", "Pune");
        given(userService.getDataFromMySQLService(city)).willReturn(List.of(sqlUser));
        this.mockMvc.perform(get("/getDataFromSql/{city}", city))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name", is(sqlUser.getName())))
                .andExpect(jsonPath("$.[0].email", is(sqlUser.getEmail())))
                .andExpect(jsonPath("$.[0].street", is(sqlUser.getStreet())))
                .andExpect(jsonPath("$.[0].area", is(sqlUser.getArea())))
                .andExpect(jsonPath("$.[0].city", is(sqlUser.getCity())));
    }

    @Test
    public void testGetDataFromMongo() throws Exception{
        String city = "Pune";
        MongoUser mongoUser = new MongoUser("ABC", "abc@gmail.com", 1234567L, new Address("Street", "Katraj", "Pune"));
        given(userService.getDataFromMongoService(city)).willReturn(List.of(mongoUser));
        this.mockMvc.perform(get("/getDataFromMongo/{city}", city))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name", is(mongoUser.getName())))
                .andExpect(jsonPath("$[0].email", is(mongoUser.getEmail())))
                .andExpect(jsonPath("$.[0].address.street", is(mongoUser.getAddress().getStreet())))
                .andExpect(jsonPath("$.[0].address.area", is(mongoUser.getAddress().getArea())))
                .andExpect(jsonPath("$.[0].address.city", is(mongoUser.getAddress().getCity())));
    }


//    @Test
//    public void testInsertUserIntoSQL() throws Exception{
//
//        SQLUser user = new SQLUser("ABC", "abc@gmail.com", 1234567L, "Street", "Katraj", "Pune");
//        given(userService.insertInSqlByRepository(user)).willReturn(SQLUser);
//
//        ObjectMapper objectMapper=new ObjectMapper();
//
//        this.mockMvc.perform(post("/sql_insert/{name}/{email}/{contact}/{street}/{area}/{city}",user.name,user.email,user.contact,user.street,user.area, user.city))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.[0].name", is(user.getName())))
//                .andExpect(jsonPath("$.[0].email", is(user.getEmail())))
//                .andExpect(jsonPath("$.[0].street", is(user.getStreet())))
//                .andExpect(jsonPath("$.[0].area", is(user.getStreet())))
//                .andExpect(jsonPath("$.[0].city", is(user.getCity())));
//    }

    @Test
    public void deleteDataFromSql() throws Exception{
        String email="atharva.D@gmail.com";
        SQLUser sqlUser=new SQLUser("Atharva", email, 1234567L, "Street", "Katraj", "Pune");
        given(userService.deleteDataFromMySQLService(email)).willReturn("User with email: " + email +" deleted successfully!");

        this.mockMvc.perform(delete("/deleteDataFromSql/{email}", sqlUser.getEmail()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("User with email: " + email +" deleted successfully!")));
    }

    @Test
    public void deleteDataFromMongo() throws Exception{
        String email="atharva.D@gmail.com";
        MongoUser mongoUser=new MongoUser("Atharva", email, 1234567L, new Address("Street", "Katraj", "Pune"));
        given(userService.deleteDataFromMongoService(email)).willReturn("User with email: " + email +" deleted successfully!");

        this.mockMvc.perform(delete("/deleteDataFromMongo/{email}", mongoUser.getEmail()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("User with email: " + email +" deleted successfully!")));
    }
}
