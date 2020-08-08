package com.example.SpringApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
public class FormController {

    @Autowired
    private UserRepository repository;

    @PostMapping(path="/form/{name}/{email}/{contact}")
    public FormBean formbean(@PathVariable String name, @PathVariable String email, @PathVariable long contact)
    {
        FormBean rep = new FormBean(name, email, contact);

        return repository.save(rep);

    }

    @GetMapping(path="/dbs")
    public List<FormBean> getbeans()
    {
        return repository.findAll();
    }
}
