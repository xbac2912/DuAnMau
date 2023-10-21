package com.example.duanmau.fragment;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.R;
import com.example.duanmau.dao.daoPhieuMuon;
import com.example.duanmau.model.ThongKeDoanhThu;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fragment_DoanhThu extends Fragment {

    EditText txtTuNgay, txtDenNgay;
    TextView lblDoanhThu;
    ImageButton btnTuNgay, btnDenNgay;
    Button btnDoanhThu;
    android.icu.text.SimpleDateFormat  sdf = new SimpleDateFormat("yyyy/MM/dd");
    int ngay, thang, nam;
    daoPhieuMuon daoPhieuMuon;
//    BarChart barChart;
//    ArrayList<BarEntry> listBarEntry;
//    ArrayList<String> labelName;
    ArrayList<ThongKeDoanhThu> listThongKe = new ArrayList<>();
    public Fragment_DoanhThu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__doanh_thu, container, false);
        //
        txtTuNgay = view.findViewById(R.id.txtTuNgay);
        txtDenNgay = view.findViewById(R.id.txtDenNgay);
        btnTuNgay = view.findViewById(R.id.btnTuNgay);
        lblDoanhThu = view.findViewById(R.id.lblDoanhThu);
        btnDenNgay = view.findViewById(R.id.btnDenNgay);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        //-------------------//
//        daoPhieuMuon = new daoPhieuMuon(getContext());
//        barChart = view.findViewById(R.id.BarChart);
//        listBarEntry = new ArrayList<>();
//        labelName = new ArrayList<>();
//        //
//        themThang();
//        for (int i = 0; i < listThongKe.size(); i++) {
//            String thang =  listThongKe.get(i).getThang();
//            int doanhThu = listThongKe.get(i).getDoanhThu();
//
//            listBarEntry.add(new BarEntry(i, doanhThu));
//            labelName.add(thang);
//        }
//        BarDataSet barDataSet = new BarDataSet(listBarEntry, "Tháng");
//        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//        Description description = new Description();
//        description.setText("Biểu đồ thống kê doanh thu trong năm " + Calendar.getInstance().get(Calendar.YEAR));
//        description.setTextSize(15);
//        barChart.setDescription(description);
//        BarData barData = new BarData(barDataSet);
//        barChart.setData(barData);
//        //
//        XAxis xAxis = barChart.getXAxis();
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelName));
//        //
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(false);
//        xAxis.setDrawAxisLine(false);
//        xAxis.setLabelCount(labelName.size());
//        xAxis.setLabelRotationAngle(270);
//        barChart.animateX(2000);
//        barChart.invalidate();
        //-------------------------//
        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar lich = Calendar.getInstance();

                ngay = lich.get(Calendar.DAY_OF_MONTH);
                thang = lich.get(Calendar.MONTH);
                nam = lich.get(Calendar.YEAR);
                DatePickerDialog d = new DatePickerDialog(getContext(), 0, dateTuNgay, nam, thang, ngay);
                d.show();
            }
        });
        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar lich = Calendar.getInstance();

                ngay = lich.get(Calendar.DAY_OF_MONTH);
                thang = lich.get(Calendar.MONTH);
                nam = lich.get(Calendar.YEAR);
                DatePickerDialog d = new DatePickerDialog(getContext(), 0, dateDenNgay, nam, thang, ngay);
                d.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = txtTuNgay.getText().toString().trim();
                String denNgay = txtDenNgay.getText().toString().trim();
                daoPhieuMuon daoPhieuMuon = new daoPhieuMuon(getContext());
                if (tuNgay.isEmpty() || denNgay.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng chọn ngày", Toast.LENGTH_SHORT).show();
                } else {
                    lblDoanhThu.setText("Doanh Thu: " + daoPhieuMuon.getDoanhThu(tuNgay, denNgay) + " VNĐ");
                }
            }
        });
        //
        return view;
    }
    DatePickerDialog.OnDateSetListener dateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            ngay = dayOfMonth;
            thang = month;
            nam = year;
            GregorianCalendar gregorianCalendar = new GregorianCalendar( nam, thang, ngay);
            txtTuNgay.setText(sdf.format(gregorianCalendar.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener dateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            ngay = dayOfMonth;
            thang = month;
            nam = year;
            GregorianCalendar gregorianCalendar = new GregorianCalendar( nam, thang, ngay);
            txtDenNgay.setText(sdf.format(gregorianCalendar.getTime()));
        }
    };
//    private String doanhThu(String ngay, String thang) {
//        return Calendar.getInstance().get(Calendar.YEAR)+"/"+thang+"/"+ngay;
//    }
//    private void themThang() {
//        listThongKe.clear();
//        listThongKe.add(new ThongKeDoanhThu("T1", daoPhieuMuon.getDoanhThu(doanhThu("01", "01"),doanhThu("31", "01"))));
//        listThongKe.add(new ThongKeDoanhThu("T2", daoPhieuMuon.getDoanhThu(doanhThu("01", "02"),doanhThu("29", "02"))));
//        listThongKe.add(new ThongKeDoanhThu("T3", daoPhieuMuon.getDoanhThu(doanhThu("01", "03"),doanhThu("31", "03"))));
//        listThongKe.add(new ThongKeDoanhThu("T4", daoPhieuMuon.getDoanhThu(doanhThu("01", "04"),doanhThu("30", "04"))));
//        listThongKe.add(new ThongKeDoanhThu("T5", daoPhieuMuon.getDoanhThu(doanhThu("01", "05"),doanhThu("31", "05"))));
//        listThongKe.add(new ThongKeDoanhThu("T6", daoPhieuMuon.getDoanhThu(doanhThu("01", "06"),doanhThu("30", "06"))));
//        listThongKe.add(new ThongKeDoanhThu("T7", daoPhieuMuon.getDoanhThu(doanhThu("01", "07"),doanhThu("31", "07"))));
//        listThongKe.add(new ThongKeDoanhThu("T8", daoPhieuMuon.getDoanhThu(doanhThu("01", "08"),doanhThu("31", "08"))));
//        listThongKe.add(new ThongKeDoanhThu("T9", daoPhieuMuon.getDoanhThu(doanhThu("01", "09"),doanhThu("30", "09"))));
//        listThongKe.add(new ThongKeDoanhThu("T10", daoPhieuMuon.getDoanhThu(doanhThu("01", "10"),doanhThu("31", "10"))));
//        listThongKe.add(new ThongKeDoanhThu("T11", daoPhieuMuon.getDoanhThu(doanhThu("01", "11"),doanhThu("30", "11"))));
//        listThongKe.add(new ThongKeDoanhThu("T12", daoPhieuMuon.getDoanhThu(doanhThu("01", "12"),doanhThu("31", "12"))));
//    }
}