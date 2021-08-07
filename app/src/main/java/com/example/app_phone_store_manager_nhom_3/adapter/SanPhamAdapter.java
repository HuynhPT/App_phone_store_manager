package com.example.app_phone_store_manager_nhom_3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_phone_store_manager_nhom_3.R;
import com.example.app_phone_store_manager_nhom_3.model.SanPham;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {
    private List<SanPham> list;

    public SanPhamAdapter(List<SanPham> list) {
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_sanpham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        SanPham sanPham = list.get(position);
        if (sanPham == null) {
            return;
        }
        holder.tvMaSP.setText("Mã sản phẩm: " + sanPham.getMaSP());
        holder.tvTenSP.setText("Sản phẩm: " + sanPham.getTenSP());
        switch (sanPham.getTinhTrang()) {
            case 0:
                holder.tvTinhTrang.setText("Tình trạng: Like new 99%");
                break;
            case 1:
                holder.tvTinhTrang.setText("Tình trạng: Mới 100%");
                break;
            default:
                holder.tvTinhTrang.setText("Tình trạng: Cũ");
                break;
        }
        holder.tvSLNhap.setText("Đang cập nhập");
        holder.tvSLXuat.setText("Đang cập nhập");
        holder.tvTonKho.setText("Đang cập nhập");

        DecimalFormat formatter = new DecimalFormat("###,###,###");

        holder.tvGiaTien.setText(formatter.format(sanPham.getGiaTien()) +"đ");

        if (sanPham.getTrangThai() == 0){
            holder.tvTrangThai.setText("Chưa lưu kho");
        }else {
            holder.tvTrangThai.setText("Đã lưu kho");
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSP, imgHang, imgDelete;
        TextView tvMaSP, tvTenSP, tvTinhTrang, tvSLNhap, tvSLXuat, tvTonKho, tvGiaTien, tvTrangThai, tvHang;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgSP = itemView.findViewById(R.id.imgSPItem);
            imgHang = itemView.findViewById(R.id.imgHangSPItem);
            imgDelete = itemView.findViewById(R.id.imgDeleteSPItem);
            tvMaSP = itemView.findViewById(R.id.tvMaSPItem);
            tvTenSP = itemView.findViewById(R.id.tvTenSPItem);
            tvTinhTrang = itemView.findViewById(R.id.tvTinhTrangSPItem);
            tvSLNhap = itemView.findViewById(R.id.tvSLNhapItem);
            tvSLXuat = itemView.findViewById(R.id.tvSLXuatItem);
            tvTonKho = itemView.findViewById(R.id.tvTonKhoItem);
            tvGiaTien = itemView.findViewById(R.id.tvGiaTienSPItem);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThaiSPItem);
            tvHang = itemView.findViewById(R.id.tvHangSPItem);

        }
    }

}
