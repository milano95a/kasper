package source.repository;


import org.springframework.data.repository.CrudRepository;
import source.domain.Family;

public interface IFamilyRepo extends CrudRepository<Family,Long> {}
