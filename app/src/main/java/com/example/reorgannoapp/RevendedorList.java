package com.example.reorgannoapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RevendedorList extends ArrayAdapter<Revendedor> {

    private Activity context;
    private List<Revendedor> revendedorList;

    public RevendedorList (Activity context, List<Revendedor> revendedorList){
        super(context, R.layout.list_revendedores, revendedorList);
        this.context = context;
        this.revendedorList = revendedorList;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_revendedores, null,true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.viewRevendedor);

        Revendedor revendedor = revendedorList.get(position);
        textViewName.setText(revendedor.getRevendedor_nome());



        return listViewItem;
    }
}
