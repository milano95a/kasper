package source.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.domain.Family;
import source.domain.Invitation;
import source.repository.InvitationRepo;

import java.util.ArrayList;

@Service
public class InvitationService {

    @Autowired
    InvitationRepo invitationRepo;

    public ArrayList<Invitation> findAll(){
        return Lists.newArrayList(invitationRepo.findAll());
    }

    public Invitation findById(long id){
        return invitationRepo.findOne(id);
    }

    public Invitation save(Invitation invitation){
        return invitationRepo.save(invitation);
    }

    public void delete(Invitation invitation){
        invitationRepo.delete(invitation);
    }

    public void deleteAll(){
        invitationRepo.deleteAll();
    }

    public boolean isExist(long id){
        return invitationRepo.exists(id);
    }
}
