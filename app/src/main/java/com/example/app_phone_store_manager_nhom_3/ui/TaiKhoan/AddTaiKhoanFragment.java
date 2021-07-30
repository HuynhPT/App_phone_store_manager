package com.example.app_phone_store_manager_nhom_3.ui.TaiKhoan;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_phone_store_manager_nhom_3.R;
import com.example.app_phone_store_manager_nhom_3.dao.DaoNhanVien;
import com.example.app_phone_store_manager_nhom_3.model.NhanVien;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class AddTaiKhoanFragment extends Fragment {
    private Drawable drawable;
    private AppCompatActivity appCompatActivity;
    private NavController navController;
    private DaoNhanVien dao;
    private List<NhanVien> list;
    private String passDefault = "123456";
    EditText edMaNV, edHoTenNV, edDienThoaiNV, edDiaChiNV, edTaiKhoanNV, edNamSinhNV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_tai_khoan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        edMaNV = view.findViewById(R.id.edMaNvTk);
        edHoTenNV = view.findViewById(R.id.ed_HoTen_Tk);
        edDienThoaiNV = view.findViewById(R.id.ed_Sdt_Tk);
        edTaiKhoanNV = view.findViewById(R.id.ed_Tk_Tk);
        edDiaChiNV = view.findViewById(R.id.ed_DiaChi_Tk);
        edNamSinhNV = view.findViewById(R.id.ed_NamSinh_Tk);

        appCompatActivity = (AppCompatActivity) getActivity();

        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setTitle("Thêm Tài Khoản");
        drawable = getActivity().getDrawable(R.drawable.ic_backspace);
        appCompatActivity.getSupportActionBar().setHomeAsUpIndicator(drawable);

        dao = new DaoNhanVien(getActivity());
        dao.openNV();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_save, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navController.navigate(R.id.addTk_to_listTk);
                return true;
            case R.id.menu_reset:
                edMaNV.setText("");
                edHoTenNV.setText("");
                edDienThoaiNV.setText("");
                edTaiKhoanNV.setText("");
                edDiaChiNV.setText("");
                edNamSinhNV.setText("");
                return true;
            case R.id.menu_save:
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(edMaNV.getText().toString());
                nhanVien.setHoTen(edHoTenNV.getText().toString());
                nhanVien.setDienThoai(edDienThoaiNV.getText().toString());
                nhanVien.setTaiKhoan(edTaiKhoanNV.getText().toString());
                nhanVien.setDiaChi(edDiaChiNV.getText().toString());
                nhanVien.setNamSinh(edNamSinhNV.getText().toString());
                nhanVien.setMatKhau(passDefault);
                long kq = dao.addNV(nhanVien);
                if (kq > 0) {
                    navController.navigate(R.id.addTk_to_listTk);
                    Toast.makeText(getContext(), "Thành Công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Thất Bại", Toast.LENGTH_SHORT).show();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dao.closeNV();
    }
}