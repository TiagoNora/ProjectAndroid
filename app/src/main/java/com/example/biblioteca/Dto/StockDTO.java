package com.example.biblioteca.Dto;

public class StockDTO {
    private String stock;

    public StockDTO(String stock) {
        this.stock = stock;
    }

    public StockDTO() {
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
