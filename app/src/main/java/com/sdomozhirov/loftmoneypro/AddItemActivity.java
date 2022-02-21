package com.sdomozhirov.loftmoneypro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.sdomozhirov.loftmoneypro.budget.BudgetFragment;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddItemActivity extends AppCompatActivity {

    public static final String ARG_POSITION = "arg_position";
    private EditText mName;
    private EditText mPrice;
    private Button mButton;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mName = findViewById(R.id.name_item);
        mPrice = findViewById(R.id.price_item);
        mButton = findViewById(R.id.send);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt(ARG_POSITION);
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameProduction = mName.getText().toString();
                int priceProduction = 0;
                try {
                    priceProduction = Integer.parseInt(mPrice.getText().toString());
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
                        Snackbar.make(mName, "Произошла ошибка", Snackbar.LENGTH_LONG).show();
                    }
                }, throwable -> {
                    Snackbar.make(mName, "Произошла ошибка", Snackbar.LENGTH_LONG).show();
                });
    }
}