package com.example.biblioteca.Dto;

public class ComentarioCriarDTO {
    private Boolean recomendado;
    private String opiniao;

    public ComentarioCriarDTO() {
    }

    public ComentarioCriarDTO(Boolean recomendado, String opiniao) {
        this.recomendado = recomendado;
        this.opiniao = opiniao;
    }

    public Boolean getRecomendado() {
        return recomendado;
    }

    public void setRecomendado(Boolean recomendado) {
        this.recomendado = recomendado;
    }

    public String getOpiniao() {
        return opiniao;
    }

    public void setOpiniao(String opiniao) {
        this.opiniao = opiniao;
    }
}
