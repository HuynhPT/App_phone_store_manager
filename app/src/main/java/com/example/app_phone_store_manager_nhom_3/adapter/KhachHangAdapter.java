package com.example.app_phone_store_manager_nhom_3.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app_phone_store_manager_nhom_3.R;
import com.example.app_phone_store_manager_nhom_3.model.KhachHang;

import java.util.List;

public class KhachHangAdapter extends BaseAdapter {
    private List<KhachHang> list;
    private Context context;
    private TextView tvMa;
    public KhachHangAdapter(List<KhachHang> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null){
            view = View.inflate(context, R.layout.custom_item_khach_hang, null);
        }

        KhachHang khachHang = list.get(position);
        tvMa = view.findViewById(R.id.tvMaKH);
        tvMa.setText(khachHang.getMaKH());
        return view;
    }
}
