package com.example.duanmau.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Fragment_PhieuMuon extends Fragment {
    RecyclerView rcvPhieuMuon;
    FloatingActionButton flt_btn_Them;
    public Fragment_PhieuMuon() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__phieu_muon, container, false);
        rcvPhieuMuon = view.findViewById(R.id.rcvPhieuMuon);
        flt_btn_Them = view.findViewById(R.id.flt_btn_Them);


        return view;
    }
}