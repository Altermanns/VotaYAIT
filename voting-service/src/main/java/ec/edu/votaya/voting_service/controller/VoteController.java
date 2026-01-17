package ec.edu.votaya.voting_service.controller;

import ec.edu.votaya.voting_service.dto.VoteRequest;
import ec.edu.votaya.voting_service.dto.VoteResponse;
import ec.edu.votaya.voting_service.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin(origins = "*")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<VoteResponse> submitVote(@RequestBody VoteRequest request) {
        VoteResponse resp = voteService.submitVote(request);
        if (resp.getMessage() != null && resp.getCertificateId() == null) {
            return ResponseEntity.badRequest().body(resp);
        }
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{certificateId}")
    public ResponseEntity<VoteResponse> getCertificate(@PathVariable String certificateId) {
        VoteResponse resp = voteService.findByCertificateId(certificateId);
        if (resp == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resp);
    }
}
