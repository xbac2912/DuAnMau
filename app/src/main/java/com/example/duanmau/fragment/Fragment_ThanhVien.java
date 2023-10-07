package com.example.duanmau.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.icu.util.GregorianCalendar;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.R;
import com.example.duanmau.adapter.adapterSach;
import com.example.duanmau.adapter.adapterThanhVien;
import com.example.duanmau.dao.daoThanhVien;
import com.example.duanmau.model.LoaiSach;
import com.example.duanmau.model.Sach;
import com.example.duanmau.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class Fragment_ThanhVien extends Fragment {
    RecyclerView rcvThanhVien;
    FloatingActionButton flt_btn_Them;
    ArrayList<ThanhVien> list = new ArrayList<>();
    ArrayList<ThanhVien> searchList;
    daoThanhVien daoThanhVien;
    adapterThanhVien adapterThanhVien;
    TextView txtTenTV, txtNamSinh;
    SearchView searchView;
    int ngay, thang, nam;
    android.icu.text.SimpleDateFormat  sdf = new SimpleDateFormat("dd/MM/yyyy");
    public Fragment_ThanhVien() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__thanh_vien, container, false);
        //
        rcvThanhVien = view.findViewById(R.id.rcvThanhVien);
        flt_btn_Them = view.findViewById(R.id.flt_btn_Them);
        searchView = view.findViewById(R.id.searchView);
        daoThanhVien = new daoThanhVien(getContext());
        list = daoThanhVien.selectAll();
        adapterThanhVien = new adapterThanhVien(getContext(), list);
        //
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rcvThanhVien.setLayoutManager(gridLayoutManager);
        rcvThanhVien.setAdapter(adapterThanhVien);
        adapterThanhVien.notifyDataSetChanged();
        //
        flt_btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog_Them();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchList = new ArrayList<>();
                if (query.length() > 0) {
                    for(int i = 0; i < list.size(); i++) {
                        if(list.get(i).getTenTV().toUpperCase().contains(query.toUpperCase())) {
                            ThanhVien tv = new ThanhVien();
                            tv.setMaTV(list.get(i).getMaTV());
                            tv.setTenTV(list.get(i).getTenTV());
                            tv.setNamSinh(list.get(i).getNamSinh());
                            searchList.add(tv);
                        }
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    rcvThanhVien.setLayoutManager(gridLayoutManager);
                    adapterThanhVien = new adapterThanhVien(getContext(), searchList);
                    rcvThanhVien.setAdapter(adapterThanhVien);
                    adapterThanhVien.notifyDataSetChanged();
                } else {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    rcvThanhVien.setLayoutManager(gridLayoutManager);
                    adapterThanhVien = new adapterThanhVien(getContext(), list);
                    rcvThanhVien.setAdapter(adapterThanhVien);
                    adapterThanhVien.notifyDataSetChanged();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList = new ArrayList<>();
                searchList = new ArrayList<>();
                if (newText.length() > 0) {
                    for(int i = 0; i < list.size(); i++) {
                        if(list.get(i).getTenTV().toUpperCase().contains(newText.toUpperCase())) {
                            ThanhVien tv = new ThanhVien();
                            tv.setMaTV(list.get(i).getMaTV());
                            tv.setTenTV(list.get(i).getTenTV());
                            tv.setNamSinh(list.get(i).getNamSinh());
                            searchList.add(tv);
                        }
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    rcvThanhVien.setLayoutManager(gridLayoutManager);
                    adapterThanhVien = new adapterThanhVien(getContext(), searchList);
                    rcvThanhVien.setAdapter(adapterThanhVien);
                    adapterThanhVien.notifyDataSetChanged();
                } else {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    rcvThanhVien.setLayoutManager(gridLayoutManager);
                    adapterThanhVien = new adapterThanhVien(getContext(), list);
                    rcvThanhVien.setAdapter(adapterThanhVien);
                    adapterThanhVien.notifyDataSetChanged();
                }
                return false;
            }
        });

        return view;
    }
    public void OpenDialog_Them() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.layout_them_thanhvien, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        txtTenTV = view.findViewById(R.id.txtTenTV);
        txtNamSinh = view.findViewById(R.id.txtNamSinh);

        txtNamSinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar lich = Calendar.getInstance();
                ngay = lich.get(Calendar.DAY_OF_MONTH);
                thang = lich.get(Calendar.MONTH);
                nam = lich.get(Calendar.YEAR);
                DatePickerDialog d = new DatePickerDialog(getContext(), 0, date, nam, thang, ngay);
                d.show();
            }
        });

        view.findViewById(R.id.btnThem_TV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTV = txtTenTV.getText().toString().trim();
                String namSinh = txtNamSinh.getText().toString().trim();
                if(tenTV.isEmpty() || namSinh.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if(daoThanhVien.insert(new ThanhVien(tenTV, namSinh))) {
                        list.clear();
                        list.addAll(daoThanhVien.selectAll());
                        dialog.dismiss();
                        adapterThanhVien.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        view.findViewById(R.id.btnHuy_TV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            ngay = dayOfMonth;
            thang = month;
            nam = year;
            android.icu.util.GregorianCalendar gregorianCalendar = new GregorianCalendar( nam, thang, ngay);
            txtNamSinh.setText(sdf.format(gregorianCalendar.getTime()));
        }
    };
}