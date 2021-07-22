package com.example.app_phone_store_manager_nhom_3.ui.Hang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.app_phone_store_manager_nhom_3.R;

public class AddHangActivity extends AppCompatActivity {
    Toolbar tbHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hang);

        tbHang = findViewById(R.id.tbHang);
        setSupportActionBar(tbHang);
        getSupportActionBar().setTitle("Thêm Hãng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Drawable drawable = getDrawable(R.drawable.ic_clear);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}