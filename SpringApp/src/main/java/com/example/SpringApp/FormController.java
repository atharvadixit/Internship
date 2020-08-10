package com.example.SpringApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
public class FormController {

    @Autowired
    private UserRepository repository;

    @PostMapping(path="/form/{name}/{email}/{contact}/{street}/{area}/{city}/{state}")
    public User formbean(@PathVariable String name, @PathVariable String email, @PathVariable long contact, @PathVariable String street, @PathVariable String area, @PathVariable String city, @PathVariable String state)
    {
        Address address=new Address(street, area, city, state);
        User rep = new User(name, email, contact, address);

        return repository.save(rep);
    }

    @PostMapping(path="request")
    public User hReq(HttpServletRequest req)
    {
        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String cont=req.getParameter("contact");
        String street=req.getParameter("street");
        String area=req.getParameter("area");
        String city=req.getParameter("city");
        String state=req.getParameter("state");
        long c=Long.parseLong(cont);
        System.out.println(name + email+street + area+city+state);
        Address ad=new Address(street,area,city,state);
        return repository.save(new User(name, email, c, ad));
    }

    @PostMapping(path="obj")
    public User objReq(@RequestBody User user)
    {
        return repository.save(user);
    }

    @DeleteMapping(path="delete/{email}")
    public String delReq(@PathVariable String email)
    {
        Optional<User> temp = repository.findById(email);
        if(temp.isPresent())
        {
            User us=temp.get();
            repository.deleteById(email);
            return "User with email: " + email +" deleted successfully!";
        }
        else {
            return "Failed to delete user";
        }
    }

    @GetMapping(path="/dbs")
    public List<User> getbeans()
    {
        return repository.findAll();
    }
}
