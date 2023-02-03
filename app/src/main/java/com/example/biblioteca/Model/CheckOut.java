package com.example.biblioteca.Model;

public class CheckOut {
    private String isbn;
    private String userId;
    private String tempo;
    private Boolean ativo;

    public CheckOut(String isbn, String userId, String tempo, Boolean ativo) {
        this.isbn = isbn;
        this.userId = userId;
        this.tempo = tempo;
        this.ativo = ativo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
