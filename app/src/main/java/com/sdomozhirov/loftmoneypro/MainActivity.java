package com.sdomozhirov.loftmoneypro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sdomozhirov.loftmoneypro.cells.Item;
import com.sdomozhirov.loftmoneypro.cells.ItemsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView itemsView;
    private Button btnClick;

    private ItemsAdapter itemsAdapter = new ItemsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClick = findViewById(R.id.btn_click);
        itemsView = findViewById(R.id.recycler_view);

        configureRecyclerView();
        generateMoney();

                btnClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(view.getContext(), "На меня нажали", Toast.LENGTH_LONG).show();
                        Intent newActivity = new Intent(getApplicationContext(), AddItemActivity.class);
                        startActivity(newActivity);
                    }
                });


    }
    private void generateMoney(){
        List<Item> Items = new ArrayList<>();
        Items.add(new Item("МОЛОКО","15000"));
        Items.add(new Item("Salary","150000"));

        itemsAdapter.setData(Items);
    }

    private void configureRecyclerView() {
        itemsView.setAdapter(itemsAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);

        itemsView.setLayoutManager(layoutManager);
        itemsView.setAdapter(itemsAdapter);
    }
}