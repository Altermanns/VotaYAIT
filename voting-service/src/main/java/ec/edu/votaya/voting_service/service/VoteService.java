package ec.edu.votaya.voting_service.service;

import ec.edu.votaya.voting_service.dto.VoteRequest;
import ec.edu.votaya.voting_service.dto.VoteResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VoteService {
    private final Map<String, VoteResponse> storage = new ConcurrentHashMap<>();

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
        // Optionally attach timestamp as part of message
        resp.setMessage("Voto registrado: " + Instant.now().toString());

        // Store by certificate id
        storage.put(certId, resp);
        return resp;
    }

    public VoteResponse findByCertificateId(String certId) {
        return storage.get(certId);
    }
}
