package com.example.biblioteca.Handler;

import com.example.biblioteca.Dto.*;
import com.example.biblioteca.Model.CheckOut;
import com.example.biblioteca.Model.Comentario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonHandler {
    public static BibliotecaInfoDTO deSerializeJson2BibliotecaInfoDTO(String resp) throws JSONException {
        BibliotecaInfoDTO data = new BibliotecaInfoDTO();
        JSONObject mResponseObject = new JSONObject(resp);
        data.setName(mResponseObject.getString("name"));
        data.setAddress(mResponseObject.getString("address"));
        data.setOpenTime(mResponseObject.getString("openTime"));
        data.setCloseTime(mResponseObject.getString("closeTime"));
        data.setOpenDays(mResponseObject.getString("openDays"));
        return data;
    }
    public static String serializeStockDTO2Json(StockDTO obj) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("stock", obj.getStock());
        return jsonObject.toString();

    }
    public static List<LivroDTO> deSerializeJson2ListLivroDTO(String resp) throws JSONException {
        JSONArray jsonResponse = new JSONArray(resp);
        List<LivroDTO> list = new ArrayList<>();
        for(int i = 0; i<jsonResponse.length();i++){
            String autor = "";
            JSONObject jsonChildNode = jsonResponse.getJSONObject(i);
            String stock = jsonChildNode.optString("stock");
            String isbn = jsonChildNode.optString("isbn");
            JSONObject _jsonChildNode = jsonChildNode.optJSONObject("book");
            String titulo = _jsonChildNode.optString("title");
            JSONArray __jsonChildNode = _jsonChildNode.optJSONArray("authors");
            for (int j = 0; j < __jsonChildNode.length() ; j++){
                JSONObject json = __jsonChildNode.getJSONObject(j);
                if (j==0) {
                    autor = json.optString("name");
                }
            }

            list.add(new LivroDTO(isbn, titulo, autor, stock));
        }
        return list;
    }
    public static String serializeBibliotecaInfoDTO2Json(BibliotecaInfoDTO obj) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", obj.getName());
        jsonObject.put("address", obj.getAddress());
        jsonObject.put("openTime", obj.getOpenTime());
        jsonObject.put("closeTime", obj.getCloseTime());
        jsonObject.put("openDays", obj.getOpenDays());

        return jsonObject.toString();

    }
    public static String findIdCheckOut(String resp,String isbn,String userId) throws JSONException {
        JSONArray jsonResponse = new JSONArray(resp);
        String id="";
        for(int i = 0; i<jsonResponse.length();i++){
            JSONObject jsonChildNode = jsonResponse.getJSONObject(i);
            String userIdProcura = jsonChildNode.optString("userId");
            String isbnProcura = jsonChildNode.optString("isbn");
            if (isbn == isbnProcura && userId == userIdProcura){
                id = jsonChildNode.optString("id");

            }
        }
        return id;
    }
    public static List<ComentarioDTO> deSerializeJson2ListComentarioDTO(String resp) throws JSONException {
        JSONArray jsonResponse = new JSONArray(resp);
        List<ComentarioDTO> list = new ArrayList<>();
        for(int i = 0; i<jsonResponse.length();i++){
            JSONObject jsonChildNode = jsonResponse.getJSONObject(i);
            Boolean recomendado = jsonChildNode.getBoolean("recommended");
            String opiniao = jsonChildNode.optString("review");
            String userId = jsonChildNode.optString("reviewer");
            String isbn=jsonChildNode.optString("isbn");
            list.add(new ComentarioDTO(recomendado, opiniao, userId, isbn));
        }
        return list;
    }
    public static String serializeComentarioDTO2Json(ComentarioCriarDTO obj) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("recommended", obj.getRecomendado());
        jsonObject.put("review", obj.getOpiniao());
        return jsonObject.toString();

    }
    public static String findIdComentario(String resp,String isbn,String userId) throws JSONException {
        JSONArray jsonResponse = new JSONArray(resp);
        String id="";
        for(int i = 0; i<jsonResponse.length();i++){
            JSONObject jsonChildNode = jsonResponse.getJSONObject(i);
            String userIdProcura = jsonChildNode.optString("reviewer");
            String isbnProcura = jsonChildNode.optString("isbn");
            if (isbn == isbnProcura && userId == userIdProcura){
                id = jsonChildNode.optString("id");

            }
        }
        return id;
    }
    public static ComentarioDTO deSerializeJson2ComentarioDTO(String resp) throws JSONException {
        ComentarioDTO data = new ComentarioDTO();
        JSONObject mResponseObject = new JSONObject(resp);
        data.setRecomendado(mResponseObject.getBoolean("recommended"));
        data.setOpiniao(mResponseObject.getString("review"));
        data.setUserId(mResponseObject.getString("reviewer"));
        data.setIsbn(mResponseObject.getString("isbn"));
        return data;
    }
    public static List<CheckOutDTO> deSerializeJson2ListCheckOutDTO(String resp) throws JSONException {
        JSONArray jsonResponse = new JSONArray(resp);
        List<CheckOutDTO> list = new ArrayList<>();
        for(int i = 0; i<jsonResponse.length();i++){
            JSONObject jsonChildNode = jsonResponse.getJSONObject(i);
            String isbn = jsonChildNode.optString("bookId");
            String userId = jsonChildNode.optString("userId");
            String tempo = jsonChildNode.optString("dueDate");
            Boolean ativo = jsonChildNode.getBoolean("active");
            list.add(new CheckOutDTO(isbn, userId, tempo, ativo));
        }
        return list;
    }
}

