package com.example.duanmau.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.dao.daoLoaiSach;
import com.example.duanmau.dao.daoSach;
import com.example.duanmau.model.LoaiSach;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;

public class adapterSach extends RecyclerView.Adapter<adapterSach.ViewHolder>{
    private final Context context;
    private final ArrayList<Sach> list;
    private ArrayList<LoaiSach> listLS;
    daoSach daoSach;
    daoLoaiSach daoLoaiSach;
    Sach indexSach;
    public adapterSach(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
        daoSach = new daoSach(context);
        daoLoaiSach = new daoLoaiSach(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_sach, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.lblMaSach.setText(String.valueOf(list.get(position).getMaSach()));
        holder.lblTenSach.setText(list.get(position).getTenSach());
        holder.lblGiaThue.setText(String.valueOf(list.get(position).getGiaThue()));
        holder.lblLoaiSach.setText(String.valueOf(list.get(position).getTenLoai()));
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexSach = list.get(position);
                openDialog_del();
            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView lblMaSach, lblTenSach, lblGiaThue, lblLoaiSach;
        ImageButton btnXoa, btnUpdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblMaSach = itemView.findViewById(R.id.lblMaSach);
            lblTenSach = itemView.findViewById(R.id.lblTenSach);
            lblGiaThue = itemView.findViewById(R.id.lblGiaThue);
            lblLoaiSach = itemView.findViewById(R.id.lblLoaiSach);
            btnXoa = itemView.findViewById(R.id.btnXoa);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }
    public void openDialog_del() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("Warning");
        builder.setMessage("Bạn có chắc chắn muốn xóa không ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (daoSach.delete(indexSach.getMaSach())) {
                    list.clear();
                    list.addAll(daoSach.selectAll());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "XÓa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
}
