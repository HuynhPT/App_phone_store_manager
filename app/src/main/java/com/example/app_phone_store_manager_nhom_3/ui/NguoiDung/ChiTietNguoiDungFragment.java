package com.example.app_phone_store_manager_nhom_3.ui.NguoiDung;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.app_phone_store_manager_nhom_3.R;
import com.example.app_phone_store_manager_nhom_3.dao.DaoNhanVien;
import com.example.app_phone_store_manager_nhom_3.model.NhanVien;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ChiTietNguoiDungFragment extends Fragment {
    private AppCompatActivity appCompatActivity;
    private NavController navController;
    private Button btnChangePass;
    private Drawable drawable;
    private String tenTK;
    private DaoNhanVien dao;
    private NhanVien nhanVien;
    private TextDrawable textDrawable;
    private ImageView img_nv;
    private TextView tvHoTen, tvDienThoai, tvDiaChi, tvNamSinh;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chi_tiet_nguoi_dung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appCompatActivity = (AppCompatActivity) getActivity();
        navController = Navigation.findNavController(view);
        drawable = appCompatActivity.getDrawable(R.drawable.ic_menu);
        img_nv = view.findViewById(R.id.imgNguoiDung);
        tvHoTen = view.findViewById(R.id.tvHoTenNguoiDung);
        tvDienThoai = view.findViewById(R.id.tvPhoneNguoiDung);
        tvDiaChi = view.findViewById(R.id.tvDiacChiNguoiDung);
        tvNamSinh = view.findViewById(R.id.tvNamSinhNguoiDung);
        btnChangePass = view.findViewById(R.id.btnChangePass);
        SharedPreferences preferences = appCompatActivity.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        tenTK = preferences.getString("USER", "");
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setHomeAsUpIndicator(drawable);
        appCompatActivity.getSupportActionBar().setTitle("Thông tin tài khoản");

        dao = new DaoNhanVien(getActivity());
        dao.openNV();
        nhanVien = dao.gettaiKhoan(tenTK);

        if (nhanVien.getHinhAnh() == null) {
            textDrawable = TextDrawable.builder().beginConfig().width(100).height(100).endConfig().buildRound(nhanVien.getTaiKhoan().substring(0, 1).toUpperCase(), getRandomColor());
            img_nv.setImageDrawable(textDrawable);
        } else {
            Bitmap bitmap = BitmapFactory.decodeByteArray(nhanVien.getHinhAnh(), 0, nhanVien.getHinhAnh().length);
            img_nv.setImageBitmap(bitmap);
        }
        tvHoTen.setText(nhanVien.getHoTen());
        tvDienThoai.setText(nhanVien.getDienThoai());
        tvDiaChi.setText(nhanVien.getDiaChi());
        tvNamSinh.setText(nhanVien.getNamSinh());
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.chiTietNguoiDung_to_doiMatKhau);
            }
        });
    }

    public int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dao.closeNV();
    }
}