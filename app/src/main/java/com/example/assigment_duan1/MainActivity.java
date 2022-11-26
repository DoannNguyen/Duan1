package com.example.assigment_duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assigment_duan1.fragment.DoanhThuFragment;
import com.example.assigment_duan1.fragment.DoiMKFragment;
import com.example.assigment_duan1.fragment.HoaDonFragment;
import com.example.assigment_duan1.fragment.QuanLyNDFragment;
import com.example.assigment_duan1.fragment.SanPhamFragment;
import com.example.assigment_duan1.fragment.ThemNDFragment;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    DrawerLayout mDrawerLayout;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("QUẢN LÝ CỬA HÀNG MINI MARK");

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.menu);

        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        tv = header.findViewById(R.id.tvheader);
        Intent intent = getIntent();
        tv.setText(intent.getStringExtra("email"));

        //nếu dữ liệu type == 1 thì hiện phần thêm người dùng, nếu != 1 thì ẩn
        //int type = Integer.parseInt(intent.getStringExtra("type"));
        int i = intent.getIntExtra("type",1);
        if(i != 0){
            navigationView.getMenu().findItem(R.id.nav_adduser).setVisible(false);
        }
        FragmentManager manager = getSupportFragmentManager();
        HoaDonFragment hoaDonFragment = new HoaDonFragment();
        manager.beginTransaction().replace(R.id.action_aria,hoaDonFragment).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        manager.beginTransaction().replace(R.id.action_aria,hoaDonFragment).commit();
                        break;
                    case R.id.nav_products:
                        SanPhamFragment sanPhamFragment = new SanPhamFragment();
                        manager.beginTransaction().replace(R.id.action_aria,sanPhamFragment).commit();
                        break;
                    case R.id.nav_chart:
                        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                        manager.beginTransaction().replace(R.id.action_aria,doanhThuFragment).commit();
                        break;
                    case R.id.nav_adduser:
                        ThemNDFragment themNDFragment = new ThemNDFragment();
                        manager.beginTransaction().replace(R.id.action_aria,themNDFragment).commit();
                        break;
                    case R.id.nav_manage:
                        QuanLyNDFragment quanLyNDFragment = new QuanLyNDFragment();
                        manager.beginTransaction().replace(R.id.action_aria,quanLyNDFragment).commit();
                        break;
                    case R.id.nav_reset:
                        DoiMKFragment doiMKFragment = new DoiMKFragment();
                        manager.beginTransaction().replace(R.id.action_aria,doiMKFragment).commit();
                        break;
                    case R.id.nav_logout:
                        finish();
                        break;
                }
                mDrawerLayout.close();
                return false;
            }
        });
    }
}