package com.example.duanmau.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau.R;
import com.example.duanmau.adapter.adapterSach;
import com.example.duanmau.adapter.adapterTop10;
import com.example.duanmau.dao.daoPhieuMuon;
import com.example.duanmau.model.Top10;

import java.util.ArrayList;

public class Fragment_Top10 extends Fragment {
    RecyclerView rcvTop10;
    ArrayList<Top10> list = new ArrayList<>();
    daoPhieuMuon daoPhieuMuon;
    adapterTop10 adapterTop10;

    public Fragment_Top10() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__top10, container, false);
        rcvTop10 = view.findViewById(R.id.rcvTop10);
        daoPhieuMuon = new daoPhieuMuon(getContext());
        list = daoPhieuMuon.getTop();
        adapterTop10 = new adapterTop10(getContext(), list);
        //
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvTop10.setLayoutManager(linearLayoutManager);
        rcvTop10.setAdapter(adapterTop10);
        adapterTop10.notifyDataSetChanged();
        return view;
    }
}