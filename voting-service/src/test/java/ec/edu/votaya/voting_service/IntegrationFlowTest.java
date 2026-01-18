package ec.edu.votaya.voting_service;

import ec.edu.votaya.voting_service.dto.VoteRequest;
import ec.edu.votaya.voting_service.dto.VoteResponse;
import ec.edu.votaya.voting_service.service.VoteService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationFlowTest {

    @Test
    public void validateAndSubmitVoteFlow() {
        VoteService svc = new VoteService(); // in-memory fallback

        VoteRequest req = new VoteRequest();
        req.setCedula("1234567890");
        req.setFirstName("Test");
        req.setLastName("User");

        VoteResponse resp = svc.submitVote(req);
        assertThat(resp).isNotNull();
        assertThat(resp.getCertificateId()).isNotNull();
        assertThat(resp.getTransactionHash()).isNotNull();
        assertThat(resp.getCedula()).isEqualTo("1234567890");
    }
}
