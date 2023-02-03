package com.example.biblioteca.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.biblioteca.Model.CheckOut;
import com.example.biblioteca.Model.Livro;
import com.example.biblioteca.R;

import java.util.List;

public class ListViewAdapterLivrosRetirados extends BaseAdapter {
    private Context context;
    private final List<CheckOut> items;

    public ListViewAdapterLivrosRetirados(Context context, List<CheckOut> livrosRetirados ) {
        this.context = context;
        this.items = livrosRetirados;
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
    public String getUserId(int position){
        return this.items.get(position).getUserId();
    }
    public String getTempo(int position){
        return this.items.get(position).getTempo();
    }
    public Boolean getAtivo(int position){
        return this.items.get(position).getAtivo();
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
                "UserId:"+getUserId(position)+"\n"+
                "Tempo:"+getTempo(position)+"\n"+
                "Ativo:"+getAtivo(position);

        tv.setText(string);

        return itemView;
    }
}
