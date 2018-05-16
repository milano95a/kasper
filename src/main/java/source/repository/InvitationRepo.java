package source.repository;


import org.springframework.data.repository.CrudRepository;
import source.domain.Invitation;

public interface InvitationRepo extends CrudRepository<Invitation, Long>{}
