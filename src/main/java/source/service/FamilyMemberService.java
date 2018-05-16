package source.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.domain.Family;
import source.domain.FamilyMember;
import source.domain.User;
import source.repository.IFamilyMemberRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamilyMemberService {

    @Autowired
    IFamilyMemberRepo familyMemberRepo;

    public ArrayList<FamilyMember> findAll(){
        return Lists.newArrayList(familyMemberRepo.findAll());
    }

    public FamilyMember findById(long id){
        return familyMemberRepo.findOne(id);
    }

    public FamilyMember save(FamilyMember familyMember){
        return familyMemberRepo.save(familyMember);
    }

    public void delete(FamilyMember familyMember){
        familyMemberRepo.delete(familyMember);
    }

    public void deleteAll(){
        familyMemberRepo.deleteAll();
    }

    public boolean isExist(long familyMemberId){
        return familyMemberRepo.exists(familyMemberId);
    }
}
