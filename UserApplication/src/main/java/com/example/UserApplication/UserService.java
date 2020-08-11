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

    public String deleteDataFromMySQLService(String email)
    {
        Optional<SQLUser> checkIfUserPresent= sqlUserRepository.findById(email);
        if(checkIfUserPresent.isPresent())
        {
            SQLUser user=checkIfUserPresent.get();
            sqlUserRepository.delete(user);
            return "User with email: " + email +" deleted successfully!";
        }
        else
        {
            return "User with email: " + email +" not found!";
        }
    }

    public String deleteDataFromMongoService(String email)
    {
        Optional<MongoUser> checkIfUserPresent= mongoUserRepository.findById(email);
        if(checkIfUserPresent.isPresent())
        {
            MongoUser user=checkIfUserPresent.get();
            mongoUserRepository.delete(user);
            return "User with email: " + email +" deleted successfully!";
        }
        else
        {
            return "User with email: " + email +" not found!";
        }
    }

    public List<SQLUser> getDataFromMySQLService()
    {
        return sqlUserRepository.findAll();
    }

    public List<SQLUser> getDataFromMongoService()
    {
        return sqlUserRepository.findAll();
    }
}
