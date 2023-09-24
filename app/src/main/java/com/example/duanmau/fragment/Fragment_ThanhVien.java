package com.example.duanmau.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau.R;
import com.example.duanmau.adapter.adapterThanhVien;
import com.example.duanmau.dao.daoThanhVien;
import com.example.duanmau.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_ThanhVien extends Fragment {
    RecyclerView rcvThanhVien;
    FloatingActionButton flt_btn_Them;
    ArrayList<ThanhVien> list = new ArrayList<>();
    daoThanhVien daoThanhVien;
    adapterThanhVien adapterThanhVien;
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
        daoThanhVien = new daoThanhVien(getContext());
        list = daoThanhVien.selectAll();
        adapterThanhVien = new adapterThanhVien(getContext(), list);
        //
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rcvThanhVien.setLayoutManager(gridLayoutManager);
        rcvThanhVien.setAdapter(adapterThanhVien);
        adapterThanhVien.notifyDataSetChanged();
        //
        return view;
    }
}