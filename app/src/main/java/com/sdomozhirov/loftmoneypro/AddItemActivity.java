package com.sdomozhirov.loftmoneypro;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.sdomozhirov.loftmoneypro.databinding.ActivityAddItemBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddItemActivity extends AppCompatActivity {

    public static final String ARG_POSITION = "arg_position";
    private ActivityAddItemBinding binding;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt(ARG_POSITION);
        }

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameProduction = binding.nameItem.getText().toString();
                int priceProduction = 0;
                try {
                    priceProduction = Integer.parseInt(binding.priceItem.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Поле стоимости должно coдержать целое число. без пробелов", Toast.LENGTH_LONG).show();
                }
                if (nameProduction.equals("")) {
                    Toast.makeText(getBaseContext(), "Поле наименования не должно быть пустым", Toast.LENGTH_LONG).show();
                } else {
                    putItem(nameProduction, priceProduction, position);
                    finish();
                }
            }
        });
    }

    private void putItem(String name, int price, int position) {
        String type;
        if (position == 0) {
            type = "income";
        } else {
            type = "expense";
        }
        ((LoftApp) getApplication()).loftAPI.putItems(String.valueOf(price), name, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.getStatus().equals("success")) {
                        finish();
                    } else {
                        Snackbar.make(binding.nameItem, "Произошла ошибка", Snackbar.LENGTH_LONG).show();
                    }
                }, throwable -> {
                    Snackbar.make(binding.nameItem, "Произошла ошибка", Snackbar.LENGTH_LONG).show();
                });
    }
}