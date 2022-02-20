package com.sdomozhirov.loftmoneypro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sdomozhirov.loftmoneypro.budget.BudgetFragment;

public class AddItemActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mPrice;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mName = findViewById(R.id.name_item);
        mPrice = findViewById(R.id.price_item);

        mButton = findViewById(R.id.send);
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
                    Intent intent = new Intent();
                    intent.putExtra(BudgetFragment.ARG_ADD_NAME, nameProduction);
                    intent.putExtra(BudgetFragment.ARG_ADD_PRICE, priceProduction);
                    setResult(BudgetFragment.ARG_ADD_DATA, intent);
                    finish();
                }


            }
        });

    }
}