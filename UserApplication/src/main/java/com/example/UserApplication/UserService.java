package com.example.UserApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private SQLUserRepository sqlUserRepository;

    @Autowired
    private  MongoUserRepository mongoUserRepository;

    public SQLUser insertInSqlByRepository(SQLUser user)
    {
        return sqlUserRepository.save(user);
    }

    public MongoUser insertInMongoByRepository(MongoUser user)
    {
        return mongoUserRepository.save(user);
    }

    public String deleteDataFromMySQLService (String email)
    {
        try {
            sqlUserRepository.deleteById(email);
        }
        catch (Exception e)
        {
            return "User with email: " + email +" not found!";
        }
        return "User with email: " + email +" deleted successfully!";
    }

    public String deleteDataFromMongoService(String email)
    {
        try {
            mongoUserRepository.deleteById(email);
            return "User with email: " + email +" deleted successfully!";
        }
        catch (Exception e) {
//            return "User with email: " + email + " not found!";
            System.out.print(e);
            return ":(";
        }
    }

    public List<SQLUser> getDataFromMySQLService(String city)
    {
        return sqlUserRepository.findByCity(city);
    }

    public List<MongoUser> getDataFromMongoService(String city)
    {
        return mongoUserRepository.findByCity(city);
    }
}
