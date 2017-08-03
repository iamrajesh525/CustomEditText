package com.rajesh.custom.edittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rajesh.custom.edittext.view.CustomEditText;

public class MainActivity extends AppCompatActivity {
    LinearLayout main;
    CustomEditText name, et_max_min, et_max_min_two, et_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = (LinearLayout) findViewById(R.id.main);

        name = new CustomEditText(this);
        name.setHint("name");
        name.setLines(1);
        name.setErrorTextListener(this, name, CustomEditText.HAS_TEXT, main);

        et_max_min = new CustomEditText(this);
        et_max_min.setHint("one");
        et_max_min.setLines(1);
        et_max_min.setErrorMinTextListener(this, et_max_min, 5, main);

        et_max_min_two = new CustomEditText(this);
        et_max_min_two.setHint("two");
        et_max_min_two.setLines(1);
        et_max_min_two.setErrorMinTextListener(this, et_max_min_two, 10, main);

        et_phone = new CustomEditText(this);
        et_phone.setHint("mobile number");
        et_phone.setLines(1);
        setMaxLengthToEditText(10,et_phone);
        et_phone.setInputType(InputType.TYPE_CLASS_PHONE);
        et_phone.setErrorTextListener(this, et_phone, CustomEditText.IS_PHONE, main);

    }
    public void setMaxLengthToEditText(int maxLength, EditText et){
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(maxLength);
        et.setFilters(FilterArray);
    }

    public void onClicked(View v) {
        if (name.isValid() &
                et_max_min.isValid() &
                et_max_min_two.isValid() &
                et_phone.isValid()) {
            Toast.makeText(this, "yes" +et_phone.getText().toString(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "no", Toast.LENGTH_SHORT).show();
        }

    }
}
