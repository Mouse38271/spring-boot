package com.gc_project.service;

import com.gc_project.model.UsersModel;
import com.gc_project.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUser(String login, String password, String email){
        if(login != null && password != null){
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);

            if(usersRepository.existsByEmail(email)){
                System.out.println("Email already taken: " + email);
                return null;
            }

            return usersRepository.save(usersModel);
        }
        return null;
    }

    public UsersModel authenticate(String email, String password){
        return usersRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    public boolean changePassword(String email, String oldPassword, String newPassword) {
        UsersModel user = usersRepository.findByEmailAndPassword(email, oldPassword).orElse(null);
        if (user != null) {
            user.setPassword(newPassword);
            usersRepository.save(user);
            return true;
        }
        return false;
    }
}
