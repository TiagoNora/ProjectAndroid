package com.example.biblioteca.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.biblioteca.Model.Comentario;
import com.example.biblioteca.R;

import java.util.List;

public class ListViewAdapterComentarios extends BaseAdapter {
    private Context context;
    private final List<Comentario> items;

    public ListViewAdapterComentarios(Context context, List<Comentario> comentarios ) {
        this.context = context;
        this.items = comentarios;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }
    public Boolean getRecomendado(int position){
        return this.items.get(position).getRecomendado();
    }
    public String getOpiniao(int position){
        return this.items.get(position).getOpiniao();
    }
    public String getUserId(int position){
        return this.items.get(position).getUserId();
    }
    public String getIsbn(int position){
        return this.items.get(position).getIsbn();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.list_item, null);
        } else {
            itemView = convertView;
        }

        TextView tv = (TextView) itemView.findViewById(R.id.textview_line);
        String string = "Recomendado:"+getRecomendado(position)+"\n"
                +"Opiniao:"+getOpiniao(position)+"\n"
                +"UserId:"+getUserId(position)+"\n"
                +"Isbn:"+getIsbn(position)+"\n";

        tv.setText(string);

        return itemView;
    }
}
