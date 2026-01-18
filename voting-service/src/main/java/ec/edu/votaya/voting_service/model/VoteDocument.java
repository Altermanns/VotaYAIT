package ec.edu.votaya.voting_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "votes")
public class VoteDocument {
    @Id
    private String id;
    private String certificateId;
    private String transactionHash;
    private String cedula;
    private String message;
    private Instant timestamp;

    public VoteDocument() {
    }

    public VoteDocument(String certificateId, String transactionHash, String cedula, String message,
            Instant timestamp) {
        this.certificateId = certificateId;
        this.transactionHash = transactionHash;
        this.cedula = cedula;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
