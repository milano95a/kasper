package source.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.domain.User;
import source.repository.IUserRepo;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private IUserRepo IUserRepo;

    public ArrayList<User> findAll(){
        return Lists.newArrayList(IUserRepo.findAll());
    }

    public User findById(long id){
        return IUserRepo.findOne(id);
    }

    public User save(User user){
        return IUserRepo.save(user);
    }

    public void delete(User user){
        IUserRepo.delete(user);
    }

    public void deleteAll(){
        IUserRepo.deleteAll();
    }

    public boolean isExist(long phone){
        return IUserRepo.exists(phone);
    }
}
