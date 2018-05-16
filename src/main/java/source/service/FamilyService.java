package source.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.domain.Family;
import source.domain.Group;
import source.repository.IFamilyRepo;

import java.util.ArrayList;

@Service
public class FamilyService {
    @Autowired
    private IFamilyRepo iFamilyRepo;

    public ArrayList<Family> findAll(){
        return Lists.newArrayList(iFamilyRepo.findAll());
    }

    public Family findById(long id){
        return iFamilyRepo.findOne(id);
    }

    public ArrayList<Family> findByUserId(long userId){
        ArrayList<Family> all = findAll();
        ArrayList<Family> families = new ArrayList<>();

        for(Family family: all){
            if(family.getFamilyHead()== userId){
                families.add(family);
            }
        }

        return families;
    }

    public Family save(Family family){
        return iFamilyRepo.save(family);
    }

    public void delete(Family family){
        iFamilyRepo.delete(family);
    }

    public void deleteAll(){
        iFamilyRepo.deleteAll();
    }

    public boolean isExist(long id){
        return iFamilyRepo.exists(id);
    }
}
