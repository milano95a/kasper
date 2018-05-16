package source.repository;

import org.springframework.data.repository.CrudRepository;
import source.domain.FamilyMember;

public interface IFamilyMemberRepo extends CrudRepository<FamilyMember,Long>{}
