package com.example.biblioteca.Service;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.example.biblioteca.Dto.*;
import com.example.biblioteca.Handler.JsonHandler;
import com.example.biblioteca.Handler.NetworkHandler;
import com.example.biblioteca.Helper.Utils;
import com.example.biblioteca.Model.BibliotecaInfo;
import com.example.biblioteca.Model.CheckOut;
import com.example.biblioteca.Model.Comentario;
import com.example.biblioteca.Model.Livro;

import java.util.List;

public class RequestsService {
    public static String lastUrl;
    public static BibliotecaInfo getBibliotecaInfo(Context c) {
        try{
            String url = Utils.getWSAddress(c)+"library/"+Utils.BIBLIOTECAID;
            String json = NetworkHandler.getDataInStringFromUrl(url);
            lastUrl = url;
            BibliotecaInfoDTO bibliotecaInfoDTO = JsonHandler.deSerializeJson2BibliotecaInfoDTO(json);
            BibliotecaInfo data = Mapper.bibliotecaInfoDTO2BibliotecaInfo(bibliotecaInfoDTO);
            return  data ;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static BibliotecaInfo getBibliotecaInfoDet(Activity activity) {
        try{
            String url = Utils.getWSAddress(activity)+"library/"+Utils.BIBLIOTECAID;
            String json = NetworkHandler.getDataInStringFromUrl(url);
            lastUrl = url;
            BibliotecaInfoDTO bibliotecaInfoDTO = JsonHandler.deSerializeJson2BibliotecaInfoDTO(json);
            BibliotecaInfo data = Mapper.bibliotecaInfoDTO2BibliotecaInfo(bibliotecaInfoDTO);
            return  data ;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void loadBook(String isbn, Activity activity) {
        try{
            String url = Utils.getWSAddress(activity)+"book"+ isbn;
            lastUrl = url;
            String result = NetworkHandler.getDataInStringFromUrl(url);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addBook(StockDTO stockDTO, String isbn, Activity activity) {
        try{
            String url = Utils.getWSAddress(activity)+"library/"+Utils.BIBLIOTECAID+"/book/"+isbn;
            lastUrl = url;
            String json = JsonHandler.serializeStockDTO2Json(stockDTO);
            String result = NetworkHandler.addDataInStringFromUrl(url, json);
        }catch(Exception e){
            e.printStackTrace();
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, "Erro"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public static void updateBook(StockDTO stockDTO, String isbn, Activity activity) {
        try{
            String url = Utils.getWSAddress(activity)+"library/"+Utils.BIBLIOTECAID+"/book/"+isbn;
            lastUrl = url;
            String json = JsonHandler.serializeStockDTO2Json(stockDTO);
            String result = NetworkHandler.updateDataInStringFromUrl(url, json);
        }catch(Exception e){
            e.printStackTrace();
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, "Erro"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public static List<Livro> getLivros(Activity c) {
        try{
            String url = Utils.getWSAddress(c)+"library/"+Utils.BIBLIOTECAID+"/book";
            lastUrl = url;
            String json = NetworkHandler.getDataInStringFromUrl(url);
            List<LivroDTO> livrosDTOS = JsonHandler.deSerializeJson2ListLivroDTO(json);
            List<Livro> livros = Mapper.listLivroDTO2listSubject(livrosDTOS);
            return livros;
        }
        catch(Exception e){
            e.printStackTrace();
            c.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(c, "Erro"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        return null;
    }
    public static void updateBibliotecaInfo(BibliotecaInfoDTO bibliotecaInfoDTO, Activity activity) {
        try{
            String url = Utils.getWSAddress(activity)+"library/"+Utils.BIBLIOTECAID;
            lastUrl = url;
            String json = JsonHandler.serializeBibliotecaInfoDTO2Json(bibliotecaInfoDTO);
            String result = NetworkHandler.updateDataInStringFromUrl(url, json);
        }catch(Exception e){
            e.printStackTrace();
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, "Erro"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public static void addCheckOut(String isbn,String userId, Activity activity) {
        try{
            String url = Utils.getWSAddress(activity)+"library/"+Utils.BIBLIOTECAID+"/book/"+isbn+"/checkout?userId="+userId;
            lastUrl = url;
            String result = NetworkHandler.returnDataInStringFromUrl(url);
        }catch(Exception e){
            e.printStackTrace();
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, "Erro"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public static void addCheckIn(String isbn,String userId, Activity activity) {
        try{
            String url = Utils.getWSAddress(activity)+"library/"+Utils.BIBLIOTECAID+"/book/"+isbn+"/checkin?userId="+userId;
            lastUrl = url;
            String result = NetworkHandler.returnDataInStringFromUrl(url);
        }catch(Exception e){
            e.printStackTrace();
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, "Erro"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public static String getIdCheckOutBook(String isbn,String userId,Activity activity) {
        try{
            String url = Utils.getWSAddress(activity)+"user/check-out?userId="+userId;
            String json = NetworkHandler.getDataInStringFromUrl(url);
            lastUrl = url;
            String id = JsonHandler.findIdCheckOut(json,isbn,userId);
            return id ;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void extendCheckOut(String id, Activity activity) {
        try{
            String url = Utils.getWSAddress(activity)+"checkout/"+id+"extend" ;
            lastUrl = url;
            String result = NetworkHandler.returnDataInStringFromUrl(url);
        }catch(Exception e){
            e.printStackTrace();
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, "Erro"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public static int getRecommendedCount(String isbn,Activity activity) {
        try{
            String url = Utils.getWSAddress(activity)+"book/"+isbn+"/review/recommended-count";
            int json = NetworkHandler.getIntFromUrl(url);
            lastUrl = url;
            return json ;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static List<Comentario> getComentarios(String isbn,Activity c) {
        try{
            String url = Utils.getWSAddress(c)+"book/"+isbn+"/review";
            lastUrl = url;
            String json = NetworkHandler.getDataInStringFromUrl(url);
            List<ComentarioDTO> comentarioDTOS = JsonHandler.deSerializeJson2ListComentarioDTO(json);
            List<Comentario> comentarios = Mapper.listComentarioDTO2listComentario(comentarioDTOS);
            return comentarios;

        }
        catch(Exception e){
            e.printStackTrace();
            c.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(c, "Error"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        return null;
    }
    public static void addComentario(ComentarioCriarDTO comentarioCriarDTO, String isbn, String user, Activity activity) {
        try{
            String url = Utils.getWSAddress(activity)+"book/"+isbn+"/review?userId="+user;
            lastUrl = url;
            String json = JsonHandler.serializeComentarioDTO2Json(comentarioCriarDTO);
            String result = NetworkHandler.addDataInStringFromUrl(url, json);
        }catch(Exception e){
            e.printStackTrace();
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, "Erro"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public static String getIdComentario(String isbn,String userId,Activity activity) {
        try{
            String url = Utils.getWSAddress(activity)+"book/"+isbn+"/review";
            String json = NetworkHandler.getDataInStringFromUrl(url);
            lastUrl = url;
            String id = JsonHandler.findIdComentario(json,isbn,userId);
            return id ;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void updateComentario(String id,String userId,String isbn,ComentarioCriarDTO comentarioCriarDTO, Activity activity) {
        try{
            String url = Utils.getWSAddress(activity)+"book/"+isbn+"/review/"+id+"?userId="+userId;
            lastUrl = url;
            String json = JsonHandler.serializeComentarioDTO2Json(comentarioCriarDTO);
            String result = NetworkHandler.updateDataInStringFromUrl(url, json);
        }catch(Exception e){
            e.printStackTrace();
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, "Erro"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public static Comentario getComentario(String isbn,String userId,Activity c) {
        try{
            String url = Utils.getWSAddress(c)+"book/"+isbn+"/review/self?userId="+userId;
            lastUrl = url;
            String json = NetworkHandler.getDataInStringFromUrl(url);
            ComentarioDTO comentarioDTO = JsonHandler.deSerializeJson2ComentarioDTO(json);
            Comentario comentario = Mapper.comentarioDTO2Comentario(comentarioDTO);
            return comentario;

        }
        catch(Exception e){
            e.printStackTrace();
            c.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(c, "Erro"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        return null;
    }
    public static List<CheckOut> getLivrosRetirados(String userId, Activity c) {
        try{
            String url = Utils.getWSAddress(c)+"user/checked-out?userId="+userId;
            lastUrl = url;
            String json = NetworkHandler.getDataInStringFromUrl(url);
            List<CheckOutDTO> checkOutDTOS = JsonHandler.deSerializeJson2ListCheckOutDTO(json);
            List<CheckOut> checkOuts = Mapper.listCheckOutDTO2listCheckOut(checkOutDTOS);
            return checkOuts;

        }
        catch(Exception e){
            e.printStackTrace();
            c.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(c, "Error"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        return null;
    }
}
