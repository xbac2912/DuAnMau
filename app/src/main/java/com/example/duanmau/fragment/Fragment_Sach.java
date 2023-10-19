package com.example.duanmau.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.R;
import com.example.duanmau.adapter.adapterSach;
import com.example.duanmau.dao.daoLoaiSach;
import com.example.duanmau.dao.daoSach;
import com.example.duanmau.model.LoaiSach;
import com.example.duanmau.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Fragment_Sach extends Fragment {
    RecyclerView rcvSach;
    FloatingActionButton flt_btn_Them;
    ArrayList<Sach> list = new ArrayList<>();
    ArrayList<Sach> searchList;
    daoSach daoSach;
    daoLoaiSach daoLoaiSach;
    adapterSach adapterSach;
    Spinner spnSapXep;
    EditText txtTenSach, txtGiaThue, txtNamXB;
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
        spnSapXep = view.findViewById(R.id.spnSapXep);
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
        ArrayList<String> sapXepArr = new ArrayList<>();
        sapXepArr.add("-- Mặc định --");
        sapXepArr.add("Giá tăng dần");
        sapXepArr.add("Giá giảm dần");
        sapXepArr.add("Năm xuất bản");
        ArrayAdapter<String> adapterSX = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, sapXepArr);
        spnSapXep.setAdapter(adapterSX);
        spnSapXep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    list = daoSach.selectAll();
                    adapterSach = new adapterSach(getContext(), list);
                    rcvSach.setAdapter(adapterSach);
                } else if (position == 1) {
                    sapXepTang();
                } else if (position == 2) {
                    sapXepGiam();
                } else if (position == 3) {
                    sapXepNam();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //
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
    public void sapXepTang() {
        Collections.sort(list, new Comparator<Sach>() {
            @Override
            public int compare(Sach o1, Sach o2) {
                if (o1.getGiaThue() > o2.getGiaThue()) {
                    return 1;
                } else {
                    if (o1.getGiaThue() == o2.getGiaThue()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            }
        });
        adapterSach = new adapterSach(getContext(), list);
        rcvSach.setAdapter(adapterSach);
    }
    public void sapXepGiam() {
        Collections.sort(list, new Comparator<Sach>() {
            @Override
            public int compare(Sach o1, Sach o2) {
                if (o1.getGiaThue() > o2.getGiaThue()) {
                    return -1;
                } else {
                    if (o1.getGiaThue() == o2.getGiaThue()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            }
        });
        adapterSach = new adapterSach(getContext(), list);
        rcvSach.setAdapter(adapterSach);
    }
    public void sapXepNam() {
        Collections.sort(list, new Comparator<Sach>() {
            @Override
            public int compare(Sach o1, Sach o2) {
                if (o1.getNamXB() > o2.getNamXB()) {
                    return 1;
                } else {
                    if (o1.getNamXB() == o2.getNamXB()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            }
        });
        adapterSach = new adapterSach(getContext(), list);
        rcvSach.setAdapter(adapterSach);
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
        txtNamXB = view.findViewById(R.id.txtNamXuatBan);
        ArrayList<LoaiSach> listLS = new ArrayList<>();
        listLS = daoLoaiSach.selectAll();
        ArrayList<String> loaiSachArr = new ArrayList<>();
        for (LoaiSach x: listLS) {
            loaiSachArr.add(x.getTenLoai());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, loaiSachArr);
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
                String tenSach = txtTenSach.getText().toString().trim();
                String giaThue = txtGiaThue.getText().toString().trim();
                String namXB = txtNamXB.getText().toString().trim();
                int nam = Calendar.getInstance().get(Calendar.YEAR);

                if(tenSach.isEmpty() || giaThue.isEmpty() || loaiSachArr.isEmpty() || namXB.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if(giaThue.matches("\\d+") == false) {
                        Toast.makeText(getContext(), "Giá tiền sai định dạng", Toast.LENGTH_SHORT).show();
                    } else if(Integer.valueOf(giaThue) < 0) {
                        Toast.makeText(getContext(), "Giá tiền phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                    }else if (namXB.matches("\\d+") == false) {
                        Toast.makeText(getContext(), "Năm xuất bản phải là số", Toast.LENGTH_SHORT).show();
                    } else if (namXB.length() != 4) {
                        Toast.makeText(getContext(), "Năm xuất bản sai định dạng ", Toast.LENGTH_SHORT).show();
                    } else if (Integer.valueOf(namXB) > nam) {
                        Toast.makeText(getContext(), "Năm nay mới là năm "+nam, Toast.LENGTH_SHORT).show();
                    } else if(daoSach.insert(new Sach(tenSach, Integer.parseInt(giaThue), Integer.valueOf(namXB), index))) {
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
        view.findViewById(R.id.btnHuy_S).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}