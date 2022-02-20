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

public class AddItemActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mPrice;
    private Button mButton;

    private String mNameString;
    private String mPriceString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mName = findViewById(R.id.name_item);
        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged( final Editable editable) {
                mNameString = editable.toString();
                checkEditTextHasText();

            }
        });
        mPrice = findViewById(R.id.price_item);
        mPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(final Editable editable) {
                mPriceString = editable.toString();
                checkEditTextHasText();

            }
        });
        mButton = findViewById(R.id.send);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mNameString) && !TextUtils.isEmpty(mPriceString)) {
                    setResult(RESULT_OK, new Intent().putExtra("name",mNameString).putExtra("price",mPriceString));
                    finish();
                }

            }
        });

    }
    public void checkEditTextHasText() {
        mButton.setEnabled(!TextUtils.isEmpty(mNameString) && !TextUtils.isEmpty(mPriceString));
    }

}