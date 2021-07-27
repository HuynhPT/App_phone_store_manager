package com.example.app_phone_store_manager_nhom_3.ui.KhachHang;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.app_phone_store_manager_nhom_3.R;
import com.example.app_phone_store_manager_nhom_3.adapter.KhachHangAdapter;
import com.example.app_phone_store_manager_nhom_3.dao.DaoKhachHang;
import com.example.app_phone_store_manager_nhom_3.databinding.FragmentListKhachHangBinding;
import com.example.app_phone_store_manager_nhom_3.model.KhachHang;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ListKhachHangFragment extends Fragment {
    private NavController navController;
    private FragmentListKhachHangBinding khachHangBinding;// lớp liên kết trực tiếp đến XML
    private AppCompatActivity appCompatActivity;
    private Drawable drawable;
    private SearchView searchView;
    private List<KhachHang> list;
    private KhachHangAdapter adapter;
    private DaoKhachHang dao;
    private RecyclerView rvKhachHang;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        appCompatActivity = (AppCompatActivity) getActivity();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvKhachHang.setLayoutManager(layoutManager);

        khachHangBinding.tlbKhachHang.inflateMenu(R.menu.menu_header);

        appCompatActivity.getSupportActionBar().setTitle("Khách Hàng");
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawable = appCompatActivity.getDrawable(R.drawable.ic_menu);
        appCompatActivity.getSupportActionBar().setHomeAsUpIndicator(drawable);

        dao = new DaoKhachHang(getActivity());
        dao.open();

        MenuItem menuItem = khachHangBinding.tlbKhachHang.getMenu().findItem(R.id.menu_search);


        searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Tên Khách Hàng, Mã Khách Hàng");
        searchView.setMaxWidth(Integer.MAX_VALUE);

        EditText edSeach = (EditText) searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        edSeach.setTextColor(Color.BLACK);
        edSeach.setHintTextColor(Color.LTGRAY);

        ImageView iconSeach = (ImageView) searchView.findViewById(androidx.appcompat.R.id.search_button);
        ImageView iconClose = (ImageView) searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        iconSeach.setColorFilter(Color.BLACK);
        iconClose.setColorFilter(Color.BLACK);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        khachHangBinding.tlbKhachHang.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_loc:
                        navController.navigate(R.id.action_listKH_to_chitietKH);
                        return true;
                    default:
                        return false;
                }
            }
        });
        list = new ArrayList<>();
        list = dao.getAll();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        khachHangBinding = FragmentListKhachHangBinding.inflate(inflater, container, false);
        View view = khachHangBinding.getRoot();
        return view;
    }
    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                navController.navigate(R.id.action_listKH_to_addKH);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dao.close();
    }
}