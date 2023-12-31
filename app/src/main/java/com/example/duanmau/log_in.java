package com.example.duanmau;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.duanmau.dao.daoThuThu;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class log_in extends AppCompatActivity {
//    EditText txtTaiKhoan, txtMatKhau;
    CheckBox chkLuuMatKhau;
    daoThuThu daoThuThu;
    TextInputLayout textInputLayoutTK;
    TextInputLayout textInputLayoutMK;
    TextInputEditText txtTaiKhoan;
    TextInputEditText txtMatKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        daoThuThu = new daoThuThu(this);
        Button btnDangNhap = findViewById(R.id.btnDangNhap);
        chkLuuMatKhau = findViewById(R.id.chkLuuMatKhau);
        textInputLayoutTK = findViewById(R.id.textInputUser);
        textInputLayoutMK = findViewById(R.id.textInputPw);
        txtTaiKhoan = findViewById(R.id.txtTaiKhoan);
        txtMatKhau = findViewById(R.id.txtMatKhau);
        chkMatKhau();

        txtTaiKhoan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txtTaiKhoan.getText().toString().isEmpty()) {
                    textInputLayoutTK.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtMatKhau.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txtMatKhau.getText().toString().isEmpty()) {
                    textInputLayoutMK.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDangNhap();
            }
        });
    }
    private void validateDangNhap() {
        String username = txtTaiKhoan.getText().toString().trim();
        String password = txtMatKhau.getText().toString().trim();
        if (username.isEmpty() || password.isEmpty()) {
            textInputLayoutTK.setError(null);
            textInputLayoutMK.setError(null);
            if (username.isEmpty()) {
                textInputLayoutTK.setError("* Vui lòng nhập tài khoản");
            } else if (password.isEmpty()) {
                textInputLayoutMK.setError("* Vui lòng nhập mật khẩu");
            }
        } else {
            textInputLayoutTK.setError(null);
            textInputLayoutMK.setError(null);
            if (daoThuThu.checklogin( username, password)) {
                Toast.makeText(log_in.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                luuMatKhau( username, password, chkLuuMatKhau.isChecked());
                Intent intent = new Intent(log_in.this, MainActivity.class);
                intent.putExtra("maTT", username);
                startActivity(intent);
            } else {
                textInputLayoutTK.setError("Tài khoản hoặc mật khẩu không chính xác");
            }
        }
    }
    public void luuMatKhau(String user, String pass, boolean status) {
        SharedPreferences sharedPreferences = getSharedPreferences("LuuTaiKhoan.txt", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!status) {
            editor.clear();
        } else {
            editor.putString("User", user);
            editor.putString("Pass", pass);
            editor.putBoolean("Luu", status);
        }
        editor.commit();
    }
    public void chkMatKhau() {
        SharedPreferences sharedPreferences = getSharedPreferences("LuuTaiKhoan.txt", MODE_PRIVATE);
        boolean chk = sharedPreferences.getBoolean("Luu", false);
        if (chk) {
            txtTaiKhoan.setText(sharedPreferences.getString("User", ""));
            txtMatKhau.setText(sharedPreferences.getString("Pass", ""));
        }
    }
}