package source.repository;

import org.springframework.data.repository.CrudRepository;
import source.domain.User;

public interface IUserRepo extends CrudRepository<User,Long>{}
