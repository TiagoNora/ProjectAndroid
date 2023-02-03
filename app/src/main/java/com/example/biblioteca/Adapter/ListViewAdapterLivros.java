package com.example.biblioteca.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.biblioteca.Model.Livro;
import com.example.biblioteca.R;

import java.util.List;

public class ListViewAdapterLivros extends BaseAdapter {
    private Context context;
    private final List<Livro> items;

    public ListViewAdapterLivros(Context context, List<Livro> livros ) {
        this.context = context;
        this.items = livros;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }
    public String getIsbn(int position){
        return this.items.get(position).getIsbn();
    }
    public String getTitulo(int position){
        return this.items.get(position).getTitulo();
    }
    public String getAutor(int position){
        return this.items.get(position).getAutor();
    }
    public String getStock(int position){
        return this.items.get(position).getStock();
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
        String string = "Isbn:"+getIsbn(position)+"\n"+
                "Autor:"+getAutor(position)+"\n"+
                "Titulo:"+getTitulo(position)+"\n"+
                "StockTotal:"+getStock(position);

        tv.setText(string);

        return itemView;
    }
}
