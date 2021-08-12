package com.example.app_phone_store_manager_nhom_3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_phone_store_manager_nhom_3.R;
import com.example.app_phone_store_manager_nhom_3.model.ChiTietHoaDon;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.List;

public class DoanhThuAdapter extends RecyclerView.Adapter<DoanhThuAdapter.DoanhThuViewHoler> {
    List<ChiTietHoaDon> list;

    public DoanhThuAdapter(List<ChiTietHoaDon> list) {
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public DoanhThuViewHoler onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_item_doanhthu, parent, false);
        return new DoanhThuViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DoanhThuViewHoler holder, int position) {
        ChiTietHoaDon chiTietHoaDon = list.get(position);
        if (chiTietHoaDon != null) {
            holder.tvNgay.setText("Ngày: " + chiTietHoaDon.getNgay());
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            String tien_fm = formatter.format(chiTietHoaDon.getDonGia());
            holder.tvTien.setText("Số tiền: " + tien_fm + " vnđ");
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public class DoanhThuViewHoler extends RecyclerView.ViewHolder {
        TextView tvNgay, tvTien;
        public DoanhThuViewHoler(@NonNull @NotNull View itemView) {
            super(itemView);
            tvNgay = itemView.findViewById(R.id.tvNgayDT);
            tvTien = itemView.findViewById(R.id.tvTienDT);

        }
    }
}
