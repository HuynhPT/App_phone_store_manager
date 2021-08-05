package com.example.app_phone_store_manager_nhom_3.ui.SanPham;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.app_phone_store_manager_nhom_3.R;
import com.example.app_phone_store_manager_nhom_3.adapter.ChonHangAdapter;
import com.example.app_phone_store_manager_nhom_3.dao.DaoHang;
import com.example.app_phone_store_manager_nhom_3.model.Hang;
import com.example.app_phone_store_manager_nhom_3.utilities.ItemHangClick;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChonHangFragment extends Fragment {
    private AppCompatActivity activity;
    private DaoHang daoHang;
    private List<Hang> list;
    private ChonHangAdapter adapter;
    private Drawable drawable;
    private NavController navController;
    private RecyclerView rvHang;
    private SearchView svHang;
    private String maHang = "";
    private int positon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chon_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        activity = (AppCompatActivity) getActivity();
        drawable = activity.getDrawable(R.drawable.ic_backspace);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setHomeAsUpIndicator(drawable);
        activity.getSupportActionBar().setTitle("Chọn hãng");
        rvHang = view.findViewById(R.id.rvChonHang);
        svHang = view.findViewById(R.id.svChonHang);

        daoHang = new DaoHang(activity);
        daoHang.open();

        list = new ArrayList<>();
        list = daoHang.getAll();
        adapter = new ChonHangAdapter(list);

        svHang.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return true;
            }
        });
        rvHang.setAdapter(adapter);
        rvHang.setLayoutManager(new GridLayoutManager(activity, 3));

        if (getArguments() != null) {
            for (Hang x : list) {
                if (x.getMaHang().equals(getArguments().getString("maHang"))) {
                    positon = list.indexOf(x);
                }
            }
            adapter.setCheckedPositon(positon);
        }
        if (getArguments() != null){
            maHang = getArguments().getString("maHang");
        }
        adapter.setItemHangClick(new ItemHangClick() {
            @Override
            public void ItemClick(Hang hang) {
                maHang = hang.getMaHang();
                if (getArguments() == null){
                    Bundle bundle = new Bundle();
                    bundle.putString("maHangSelected", maHang);
                    navController.navigate(R.id.chonHang_to_addSP, bundle);
                }
            }
        });

    }

    private void filter(String newText) {
        List<Hang> filterList = new ArrayList<>();
        for (Hang item : list) {
            if (item.getTenHang().toLowerCase().contains(newText.toLowerCase())) {
                filterList.add(item);
            }
        }
        adapter.filter(filterList);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_done, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getArguments() != null){
                    Bundle bundle = new Bundle();
                    bundle.putString("maHangSelected", maHang);
                    navController.navigate(R.id.chonHang_to_addSP, bundle);
                }
                return true;
            case R.id.menu_done:
                if (getArguments() != null){
                    Bundle bundle = new Bundle();
                    bundle.putString("maHangSelected", maHang);
                    navController.navigate(R.id.chonHang_to_addSP, bundle);
                }
                if (maHang == ""){
                    Toast.makeText(activity, "Bạn chưa chọn Hãng nào.", Toast.LENGTH_SHORT).show();
                }
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        daoHang.close();
    }
}