package com.sp.restaurantlist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class DetailForm extends AppCompatActivity {
    private EditText restaurantName;
    private RadioGroup restaurantTypes;
    private Button buttonSave;
    private EditText restaurantAddress;
    private EditText restaurantTel;

    private RestaurantHelper helper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_form);

        restaurantName = findViewById(R.id.restaurant_name);
        restaurantTypes = findViewById(R.id.restaurant_types);

        buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(onSave);

        restaurantAddress = findViewById(R.id.restaurant_address);
        restaurantTel = findViewById(R.id.restaurant_tel);

        helper = new RestaurantHelper(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        helper.close();
    }

    private View.OnClickListener onSave = new View.OnClickListener()
    {
        @Override
        public  void onClick(View v) {
            String nameStr = restaurantName.getText().toString();
            String addrStr = restaurantAddress.getText().toString();
            String telStr = restaurantTel.getText().toString();
            String restType = "";

            switch (restaurantTypes.getCheckedRadioButtonId()){
                case R.id.chinese:
                    restType="Chinese";
                    break;
                case R.id.western:
                    restType="Western";
                    break;
                case R.id.indian:
                    restType="Indian";
                    break;
                case R.id.indonesian:
                    restType="Indonesian";
                    break;
                case R.id.korean:
                    restType="Korean";
                    break;
                case R.id.japanese:
                    restType="Japanese";
                    break;
                case R.id.thai:
                    restType="Thai";
                    break;
            }

            helper.insert(nameStr,addrStr,telStr,restType);
            finish();
        }
    };

}