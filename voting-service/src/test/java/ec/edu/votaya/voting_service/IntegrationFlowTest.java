package ec.edu.votaya.voting_service;

import ec.edu.votaya.voting_service.dto.VoteRequest;
import ec.edu.votaya.voting_service.dto.VoteResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationFlowTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validateAndSubmitVoteFlow() throws Exception {
        // Validate cedula
        mockMvc.perform(get("/api/auth/validate-cedula?cedula=1234567890")).andExpect(status().isOk());

        // Submit vote JSON
        String json = "{\"cedula\":\"1234567890\",\"firstName\":\"Test\",\"lastName\":\"User\",\"votes\":{\"q1\":\"q1-si\",\"q2\":\"q2-no\",\"q3\":\"q3-si\"}}";

        String resp = mockMvc.perform(post("/api/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(resp).contains("certificateId");
        assertThat(resp).contains("transactionHash");
    }
}
