package com.example.biblioteca.Dto;

import com.example.biblioteca.Model.*;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static BibliotecaInfo bibliotecaInfoDTO2BibliotecaInfo(BibliotecaInfoDTO obj) throws NullPointerException {
        BibliotecaInfo data = null;
        data = new BibliotecaInfo(obj.getName(), obj.getAddress(), obj.getOpenTime(), obj.getCloseTime(), obj.getOpenDays());
        return data;
    }
    public static StockDTO stock2stockDTO(Stock obj) throws NullPointerException {
        StockDTO data = new StockDTO(obj.getStock());
        return data;
    }
    public static List<Livro> listLivroDTO2listSubject(List<LivroDTO>  list) throws NullPointerException {
        List<Livro> data = new ArrayList();
        for(LivroDTO obj : list){
            Livro i = livroDTO2Livro(obj);
            data.add(i);
        }
        return data;
    }
    public static Livro livroDTO2Livro(LivroDTO obj) throws NullPointerException {
        Livro data = new Livro(obj.getIsbn(), obj.getTitulo(), obj.getAutor(), obj.getStock());
        return data;
    }
    public static BibliotecaInfoDTO bibliotecaInfo2BibliotecaInfoDTO(BibliotecaInfo obj) throws NullPointerException {
        BibliotecaInfoDTO data = new BibliotecaInfoDTO(obj.getName(), obj.getAddress(), obj.getOpenTime(), obj.getCloseTime(), obj.getOpenDays());
        return data;
    }
    public static List<Comentario> listComentarioDTO2listComentario(List<ComentarioDTO>  list) throws NullPointerException {
        List<Comentario> data = new ArrayList();
        for(ComentarioDTO obj : list){
            Comentario i = comentarioDTO2Comentario(obj);
            data.add(i);
        }
        return data;
    }
    public static Comentario comentarioDTO2Comentario(ComentarioDTO obj) throws NullPointerException {
        Comentario data = new Comentario(obj.getRecomendado(), obj.getOpiniao(), obj.getUserId(), obj.getIsbn());
        return data;
    }
    public static ComentarioCriarDTO comentarioCriar2ComentarioCriarDTO(ComentarioCriar obj) throws NullPointerException {
        ComentarioCriarDTO data = new ComentarioCriarDTO(obj.getRecomendado(),obj.getOpiniao());
        return data;
    }
    public static List<CheckOut> listCheckOutDTO2listCheckOut(List<CheckOutDTO>  list) throws NullPointerException {
        List<CheckOut> data = new ArrayList();
        for(CheckOutDTO obj : list){
            CheckOut i = checkOutDTO2CheckOut(obj);
            data.add(i);
        }
        return data;
    }
    public static CheckOut checkOutDTO2CheckOut(CheckOutDTO obj) throws NullPointerException {
        CheckOut data = new CheckOut(obj.getIsbn(), obj.getUserId(), obj.getTempo(), obj.getAtivo());
        return data;
    }
}
