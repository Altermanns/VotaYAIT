package ec.edu.votaya.voting_service.service;

import ec.edu.votaya.voting_service.dto.VoteRequest;
import ec.edu.votaya.voting_service.dto.VoteResponse;
import ec.edu.votaya.voting_service.model.VoteDocument;
import ec.edu.votaya.voting_service.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final Map<String, VoteResponse> inMemoryStorage;

    // Production constructor (autowired)
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
        this.inMemoryStorage = null;
    }

    // No-arg constructor for tests or fallback
    public VoteService() {
        this.voteRepository = null;
        this.inMemoryStorage = new ConcurrentHashMap<>();
    }

    public VoteResponse submitVote(VoteRequest req) {
        // Basic validation
        if (req.getCedula() == null || req.getCedula().length() != 10) {
            VoteResponse err = new VoteResponse();
            err.setMessage("Número de cédula inválido");
            return err;
        }

        // Generate certificate id and a mocked transaction hash
        String certId = "CERT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String txHash = "0x" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12);

        VoteResponse resp = new VoteResponse(certId, txHash, req.getCedula());
        resp.setMessage("Voto registrado: " + Instant.now().toString());

        // Persist to MongoDB if available, otherwise store in-memory
        VoteDocument doc = new VoteDocument(certId, txHash, req.getCedula(), resp.getMessage(), Instant.now());
        if (voteRepository != null) {
            voteRepository.save(doc);
        } else {
            inMemoryStorage.put(certId, resp);
        }

        return resp;
    }

    public VoteResponse findByCertificateId(String certId) {
        if (voteRepository != null) {
            Optional<VoteDocument> found = voteRepository.findByCertificateId(certId);
            if (found.isEmpty())
                return null;
            VoteDocument d = found.get();
            VoteResponse resp = new VoteResponse(d.getCertificateId(), d.getTransactionHash(), d.getCedula());
            resp.setMessage(d.getMessage());
            return resp;
        } else {
            return inMemoryStorage.get(certId);
        }
    }
}
