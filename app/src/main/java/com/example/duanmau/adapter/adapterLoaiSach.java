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
import com.example.duanmau.model.LoaiSach;

import java.util.ArrayList;

public class adapterLoaiSach extends RecyclerView.Adapter<adapterLoaiSach.ViewHolder>{
    private final ArrayList<LoaiSach> list;
    private final Context context;
    daoLoaiSach daoLoaiSach;
    LoaiSach indexLoaiSach;

    public adapterLoaiSach(ArrayList<LoaiSach> list, Context context) {
        this.list = list;
        this.context = context;
        daoLoaiSach = new daoLoaiSach(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_loaisach, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.lblMaLoai.setText(String.valueOf(list.get(position).getMaLoai()));
        holder.lblTenLoai.setText(list.get(position).getTenLoai());
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexLoaiSach = list.get(position);
                openDialog_del();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView lblMaLoai, lblTenLoai;
        ImageButton btnXoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblMaLoai = itemView.findViewById(R.id.lblMaLoai);
            lblTenLoai = itemView.findViewById(R.id.lblTenLoai);
            btnXoa = itemView.findViewById(R.id.btnXoa_LS);
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
                if (daoLoaiSach.delete(indexLoaiSach.getMaLoai())) {
                    list.clear();
                    list.addAll(daoLoaiSach.selectAll());
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
