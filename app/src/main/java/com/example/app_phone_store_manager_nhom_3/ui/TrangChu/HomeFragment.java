package com.example.app_phone_store_manager_nhom_3.ui.TrangChu;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app_phone_store_manager_nhom_3.MainActivity;
import com.example.app_phone_store_manager_nhom_3.R;
import com.example.app_phone_store_manager_nhom_3.dao.DaoKhachHang;
import com.example.app_phone_store_manager_nhom_3.dao.DaoSanPham;
import com.example.app_phone_store_manager_nhom_3.databinding.FragmentAddSanPhamBinding;
import com.example.app_phone_store_manager_nhom_3.databinding.FragmentHomeBinding;
import com.example.app_phone_store_manager_nhom_3.model.KhachHang;
import com.example.app_phone_store_manager_nhom_3.ui.SanPham.ListSanPhamFragment;
import com.example.app_phone_store_manager_nhom_3.ui.SanPham.SanPhamFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private TextView tv_SLKH, tv_SLSP, tv_SLHDN, tv_SLHDX;
    private LinearLayout ln_KH, ln_SP, ln_HDN, ln_HDX;
    private DaoKhachHang daoKH;
    private DaoSanPham daoSP;
    private List<KhachHang> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ln_KH = view.findViewById(R.id.car_KH);
        ln_SP = view.findViewById(R.id.car_SP);
        ln_HDN = view.findViewById(R.id.car_HDN);
        ln_HDX = view.findViewById(R.id.car_HDX);
        tv_SLKH = view.findViewById(R.id.tv_SLKH);
        tv_SLSP = view.findViewById(R.id.tv_SLSP);
        tv_SLHDN = view.findViewById(R.id.tv_SLHDN);
        tv_SLHDX = view.findViewById(R.id.tv_SLHDX);
        onClick();
        daoKH = new DaoKhachHang(getContext());
        daoKH.open();
        daoSP = new DaoSanPham(getContext());
        daoSP.open();
        tv_SLKH.setText(daoKH.getCountma() + "");
        tv_SLSP.setText(daoSP.getCountma() + "");
        tv_SLHDN.setText(daoSP.getCountma() + "");
        tv_SLHDX.setText(daoSP.getCountma() + "");
    }

    private void onClick() {
        ln_SP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setClick(R.id.nav_sanPham);
            }
        });
        ln_KH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setClick(R.id.nav_khachHang);
            }
        });
        ln_HDX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setClick(R.id.nav_hoaDonXuat);
            }
        });
        ln_HDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setClick(R.id.nav_hoaDonNhap);
            }
        });
    }

}