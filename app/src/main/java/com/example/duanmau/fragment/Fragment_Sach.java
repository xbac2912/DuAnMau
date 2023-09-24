package com.example.duanmau.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import androidx.appcompat.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmau.R;
import com.example.duanmau.adapter.adapterSach;
import com.example.duanmau.dao.daoLoaiSach;
import com.example.duanmau.dao.daoSach;
import com.example.duanmau.model.LoaiSach;
import com.example.duanmau.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_Sach extends Fragment {
    RecyclerView rcvSach;
    FloatingActionButton flt_btn_Them;
    ArrayList<Sach> list = new ArrayList<>();
    ArrayList<Sach> searchList;
    daoSach daoSach;
    daoLoaiSach daoLoaiSach;
    adapterSach adapterSach;
    EditText txtTenSach, txtGiaThue;
    Spinner spnLoaiSach;
    int index;
    SearchView searchView;
    public Fragment_Sach() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__sach, container, false);
        rcvSach = view.findViewById(R.id.rcvSach);
        flt_btn_Them = view.findViewById(R.id.flt_btn_Them);
        searchView = view.findViewById(R.id.searchView);
        daoSach = new daoSach(getContext());
        daoLoaiSach = new daoLoaiSach(getContext());
        list = daoSach.selectAll();
        adapterSach = new adapterSach(getContext(), list);
        //
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvSach.setLayoutManager(linearLayoutManager);
        rcvSach.setAdapter(adapterSach);
        adapterSach.notifyDataSetChanged();
        //
        flt_btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog_Them();
            }
        });
        //
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchList = new ArrayList<>();
                if (query.length() > 0) {
                    for(int i = 0; i < list.size(); i++) {
                        if(list.get(i).getTenSach().toUpperCase().contains(query.toUpperCase())) {
                            Sach s = new Sach();
                            s.setMaSach(list.get(i).getMaSach());
                            s.setTenSach(list.get(i).getTenSach());
                            s.setGiaThue(list.get(i).getGiaThue());
                            s.setTenLoai(list.get(i).getTenLoai());
                            searchList.add(s);
                        }
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    rcvSach.setLayoutManager(linearLayoutManager);
                    adapterSach = new adapterSach(getContext(), searchList);
                    rcvSach.setAdapter(adapterSach);
                    adapterSach.notifyDataSetChanged();
                } else {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    rcvSach.setLayoutManager(linearLayoutManager);
                    adapterSach = new adapterSach(getContext(), list);
                    rcvSach.setAdapter(adapterSach);
                    adapterSach.notifyDataSetChanged();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList = new ArrayList<>();
                if (newText.length() > 0) {
                    for(int i = 0; i < list.size(); i++) {
                        if(list.get(i).getTenSach().toUpperCase().contains(newText.toUpperCase())) {
                            Sach s = new Sach();
                            s.setMaSach(list.get(i).getMaSach());
                            s.setTenSach(list.get(i).getTenSach());
                            s.setGiaThue(list.get(i).getGiaThue());
                            s.setTenLoai(list.get(i).getTenLoai());
                            searchList.add(s);
                        }
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    rcvSach.setLayoutManager(linearLayoutManager);
                    adapterSach = new adapterSach(getContext(), searchList);
                    rcvSach.setAdapter(adapterSach);
                    adapterSach.notifyDataSetChanged();
                } else {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    rcvSach.setLayoutManager(linearLayoutManager);
                    adapterSach = new adapterSach(getContext(), list);
                    rcvSach.setAdapter(adapterSach);
                    adapterSach.notifyDataSetChanged();
                }
                return false;
            }
        });

        return view;
    }
    public void OpenDialog_Them() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.layout_them_sach, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        txtTenSach = view.findViewById(R.id.txtTenSach);
        txtGiaThue = view.findViewById(R.id.txtGiaThue);
        spnLoaiSach = view.findViewById(R.id.spnLoaiSach);
        ArrayList<LoaiSach> listLS = new ArrayList<>();
        listLS = daoLoaiSach.selectAll();
        ArrayList<String> loaiSachArr = new ArrayList<>();
        for (LoaiSach x: listLS) {
            loaiSachArr.add(x.getTenLoai());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, loaiSachArr);
        spnLoaiSach.setAdapter(adapter);
        spnLoaiSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index = daoLoaiSach.getMaLoai(loaiSachArr.get(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        view.findViewById(R.id.btnThem_S).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenSach = txtTenSach.getText().toString();
                String giaThue = txtGiaThue.getText().toString();

                if(tenSach.isEmpty() || giaThue.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if(giaThue.matches("\\d+") == false) {
                        Toast.makeText(getContext(), "Giá tiền sai định dạng", Toast.LENGTH_SHORT).show();
                    } else if(Integer.valueOf(giaThue) < 0) {
                        Toast.makeText(getContext(), "Giá tiền phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                    }
                    else if(daoSach.insert(new Sach(tenSach, Integer.parseInt(giaThue), index))) {
                        list.clear();
                        list.addAll(daoSach.selectAll());
                        dialog.dismiss();
                        adapterSach.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}