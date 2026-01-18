package ec.edu.votaya.voting_service.repository;

import ec.edu.votaya.voting_service.model.VoteDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends MongoRepository<VoteDocument, String> {

    Optional<VoteDocument> findByCertificateId(String certificateId);

}
