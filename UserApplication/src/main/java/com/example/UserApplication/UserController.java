package com.example.UserApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/sql_insert/{name}/{email}/{contact}/{street}/{area}/{city}")
    public SQLUser insertUserIntoSQL(@PathVariable String name, @PathVariable String email, @PathVariable long contact, @PathVariable String street, @PathVariable String area,@PathVariable String city)
    {
        SQLUser user = new SQLUser(name, email, contact, street, area, city);

        return userService.insertInSqlByRepository(user);
    }

    @PostMapping(value = "/mongo_insert/{name}/{email}/{contact}/{street}/{area}/{city}")
    public MongoUser insertUserIntoMongo(@PathVariable String name, @PathVariable String email, @PathVariable long contact, @PathVariable String street, @PathVariable String area,@PathVariable String city)
    {
        Address address=new Address(street, area, city);
        MongoUser user = new MongoUser(name, email, Long.valueOf(contact), address);

        return userService.insertInMongoByRepository(user);
    }


    @DeleteMapping(path = "/deleteDataFromSql/{email}")
    public String deleteDataFromSql(@PathVariable String email)
    {
        return userService.deleteDataFromMySQLService(email);
    }

    @DeleteMapping(path = "/deleteDataFromMongo/{email}")
    public String deleteDataFromMongo(@PathVariable String email)
    {
        return userService.deleteDataFromMongoService(email);
    }

    @GetMapping(path = "/getDataFromSql/{city}")
    public List<SQLUser> getDataFromSql(@PathVariable String city)
    {
        return userService.getDataFromMySQLService(city);
    }

    @GetMapping(path = "/getDataFromMongo/{city}")
    public List<MongoUser> getDataFromMongo(@PathVariable String city)
    {
        return userService.getDataFromMongoService(city);
    }

}
