package ec.edu.votaya.voting_service.dto;

public class VoteResponse {
    private String certificateId;
    private String transactionHash;
    private String cedula;
    private String message;

    public VoteResponse() {
    }

    public VoteResponse(String certificateId, String transactionHash, String cedula) {
        this.certificateId = certificateId;
        this.transactionHash = transactionHash;
        this.cedula = cedula;
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
}
