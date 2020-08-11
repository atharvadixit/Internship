package com.example.UserApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService user_service;

    @PostMapping(value = "/sql_insert/{name}/{email}/{contact}/{street}/{area}/{city}")
    public SQLUser insertUserIntoSQL(@PathVariable String name, @PathVariable String email, @PathVariable long contact, @PathVariable String street, @PathVariable String area,@PathVariable String city)
    {
        SQLUser user = new SQLUser(name, email, Long.valueOf(contact), street, area, city);

        return user_service.insertInSqlByRepository(user);
    }

    @PostMapping(value = "/mongo_insert/{name}/{email}/{contact}/{street}/{area}/{city}")
    public MongoUser insertUserIntoMongo(@PathVariable String name, @PathVariable String email, @PathVariable long contact, @PathVariable String street, @PathVariable String area,@PathVariable String city)
    {
        Address address=new Address(street, area, city);
        MongoUser user = new MongoUser(name, email, Long.valueOf(contact), address);

        return user_service.insertInMongoByRepository(user);
    }


    @DeleteMapping(path = "/deleteDataFromSql/{email}")
    public String deleteDataFromSql(@PathVariable String email)
    {
        return user_service.deleteDataFromMySQLService(email);
    }

    @DeleteMapping(path = "/deleteDataFromMongo/{email}")
    public String deleteDataFromMongo(@PathVariable String email)
    {
        return user_service.deleteDataFromMongoService(email);
    }

    @GetMapping(path = "/getDataFromSql")
    public List<SQLUser> getDataFromSql()
    {
        return user_service.getDataFromMySQLService();
    }

    @GetMapping(path = "/getDataFromMongo")
    public List<SQLUser> getDataFromMongo()
    {
        return user_service.getDataFromMongoService();
    }

}
