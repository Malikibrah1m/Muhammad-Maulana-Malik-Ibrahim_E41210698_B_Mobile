package com.example.cardveiw;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private List<Mahasiswa> mahasiswaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mahasiswaList = addData();

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new MahasiswaAdapter((ArrayList<Mahasiswa>) mahasiswaList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private List<Mahasiswa> addData() {
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        mahasiswaList.add(new Mahasiswa("Nama   : Muhammad Maulana Malik Ibrahim", "NIM      : E41210698", "No.HP  : 082333992023"));
        mahasiswaList.add(new Mahasiswa("Nama   : Muhammad Izza Alfiansyah", "NIM      : E32211899", "No.HP  : 081231921351"));
        mahasiswaList.add(new Mahasiswa("Nama   : Muhammad Condro Asep S.", "NIM      : E32213193", "No.HP  : 082244970748"));
        mahasiswaList.add(new Mahasiswa("Nama   : Dimas Maulana", "NIM      : E32215261", "No.HP  : 082586594333"));
        return mahasiswaList;
    }
}