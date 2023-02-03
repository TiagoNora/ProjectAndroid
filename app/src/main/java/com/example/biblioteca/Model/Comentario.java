package com.example.biblioteca.Model;

public class Comentario {
    private Boolean recomendado;
    private String opiniao;
    private String userId;
    private String isbn;

    public Comentario(Boolean recomendado, String opiniao, String userId, String isbn) {
        this.recomendado = recomendado;
        this.opiniao = opiniao;
        this.userId = userId;
        this.isbn = isbn;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
