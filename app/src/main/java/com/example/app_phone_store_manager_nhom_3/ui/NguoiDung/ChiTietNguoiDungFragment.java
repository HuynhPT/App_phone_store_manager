package com.example.app_phone_store_manager_nhom_3.ui.NguoiDung;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.app_phone_store_manager_nhom_3.R;

import org.jetbrains.annotations.NotNull;

public class ChiTietNguoiDungFragment extends Fragment {
    private AppCompatActivity appCompatActivity;
    private NavController navController;
    private Button btnChangePass;
    private Drawable drawable;
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
        btnChangePass = view.findViewById(R.id.btnChangePass);

        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setHomeAsUpIndicator(drawable);
        appCompatActivity.getSupportActionBar().setTitle("Thông tin tài khoản");

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.chiTietNguoiDung_to_doiMatKhau);
            }
        });
    }

}