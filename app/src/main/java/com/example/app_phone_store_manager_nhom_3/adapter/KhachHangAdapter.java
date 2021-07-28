package com.example.app_phone_store_manager_nhom_3.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_phone_store_manager_nhom_3.R;
import com.example.app_phone_store_manager_nhom_3.model.KhachHang;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder>{
    private List<KhachHang> list;

    public KhachHangAdapter(List<KhachHang> list) {
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_khach_hang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        KhachHang khachHang = list.get(position);
        holder.tvMaKH.setText("Mã KH: #"+ khachHang.getMaKH());
        holder.tvHoTenKH.setText("Họ Tên: "+ khachHang.getHoTen());
        holder.tvPhoneKH.setText("Số Điện Thoại: "+ khachHang.getDienThoai());
        holder.tvDiaChiKH.setText("Địa Chỉ: "+ khachHang.getDiaChi());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMaKH, tvHoTenKH, tvPhoneKH, tvDiaChiKH;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvMaKH = itemView.findViewById(R.id.tvMaKH);
            tvHoTenKH = itemView.findViewById(R.id.tvHoTenKH);
            tvPhoneKH = itemView.findViewById(R.id.tvDienThoaiKH);
            tvDiaChiKH = itemView.findViewById(R.id.tvDiaChiKH);
        }
    }
}
