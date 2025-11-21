package com.projeto.software.rede_mais_social.dto;

public class AfiliacaoResponseDTO {
    private Long pedidoId;
    private Long CandidatoId;
    private String status;
    private String mensagem;

    public AfiliacaoResponseDTO() {}

    public AfiliacaoResponseDTO(Long pedidoId, String status, String mensagem) {
        this.pedidoId = pedidoId;
        this.status = status;
        this.mensagem = mensagem;
    }

    public AfiliacaoResponseDTO(Long pedidoId, Long CandidatoId, String status, String mensagem) {
        this.pedidoId = pedidoId;
        this.CandidatoId = CandidatoId;
        this.status = status;
        this.mensagem = mensagem;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getCandidatoId() {
        return CandidatoId;
    }

    public void setCandidatoId(Long candidatoId) {
        CandidatoId = candidatoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }


}
