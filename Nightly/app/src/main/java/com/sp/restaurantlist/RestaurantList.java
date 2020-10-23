package com.sp.restaurantlist;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RestaurantList extends AppCompatActivity {
    private EditText restaurantName;
    private RadioGroup restaurantTypes;
    private Button buttonSave;
    private EditText restaurantAddress;
    private EditText restaurantTel;

    private List<Restaurant> model =new ArrayList<Restaurant>();
    private RestaurantAdapter adapter = null;
    private ListView list;
    private TabHost host;
    private boolean showMenu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        restaurantName=findViewById(R.id.restaurant_name);
        restaurantTypes=findViewById(R.id.restaurant_types);

        buttonSave =findViewById(R.id.button_save);
        buttonSave.setOnClickListener(onSave);

        restaurantAddress=findViewById(R.id.restaurant_address);
        restaurantTel=findViewById(R.id.restaurant_tel);

        list = findViewById(R.id.restaurants);
        adapter= new RestaurantAdapter();
        list.setAdapter(adapter);

        host = findViewById(R.id.tabHost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("List");
        spec.setContent(R.id.restaurants_tab);
        spec.setIndicator("List");
        host.addTab(spec);

        spec = host.newTabSpec("Details");
        spec.setContent(R.id.details_tab);
        spec.setIndicator("Details");
        host.addTab(spec);
        host.setCurrentTab(0);
        list.setOnItemClickListener(onListClick);

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            @Override
            public void onTabChanged(String tabId){
                invalidateOptionsMenu();
            }
        });
    }

    @Override
    public void invalidateOptionsMenu(){
        if(host.getCurrentTab()==0){
            showMenu=false;
        }
        else if (host.getCurrentTab()==1){
            showMenu=true;
        }
        super.invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.option, menu);
        //return super.onCreateOptionsMenu(menu);
        if (showMenu == true)
            return true;
        else
            return false;
    }

    @Override
    protected void onStart(){
        invalidateOptionsMenu();
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case(R.id.about):
                Toast.makeText(this,"Restaurant List - Version 1.0",Toast.LENGTH_LONG).show();
                break;
        }
        return  super.onOptionsItemSelected(item);
    }

    private View.OnClickListener onSave = new View.OnClickListener()
    {
        @Override
        public  void onClick(View v) {
            String nameStr = restaurantName.getText().toString();
            String addressStr = restaurantAddress.getText().toString();
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

            Restaurant restaurant=new Restaurant();
            restaurant.setName(nameStr);
            restaurant.setAddress(addressStr);
            restaurant.setTelephone(telStr);
            restaurant.setRestaurantType(restType);
            adapter.add(restaurant);
        }
    };

    static class RestaurantHolder {
        private TextView restName =null;
        private TextView addr=null;
        private ImageView icon =null;
        RestaurantHolder(View row){
            restName = row.findViewById(R.id.restName);
            addr = row.findViewById(R.id.restAddr);
            icon = row.findViewById(R.id.icon);
        }
        void populateFrom(Restaurant r){
            restName.setText(r.getName());
            addr.setText(r.getAddress()+","+r.getTelephone());
            if (r.getRestaurantType().equals("Chinese")){
                icon.setImageResource(R.drawable.ball_red);
            }
            else if(r.getRestaurantType().equals("Western")){
                icon.setImageResource(R.drawable.ball_yellow);
            }
            else {
                icon.setImageResource(R.drawable.ball_green);
            }
        }
    }

    class RestaurantAdapter extends ArrayAdapter<Restaurant>{
        RestaurantAdapter(){
            super(RestaurantList.this, R.layout.row, model);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View row =convertView;
            RestaurantHolder holder;
            if (row ==null){
                LayoutInflater inflater =getLayoutInflater();
                row =inflater.inflate(R.layout.row,parent,false);
                holder=new RestaurantHolder(row);
                row.setTag(holder);
            }
            else{
                holder=(RestaurantHolder)row.getTag();
            }
            holder.populateFrom(model.get(position));
            return (row);
        }
    }

    AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener(){
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id){
          Restaurant r = model.get(position);

          restaurantName.setText(r.getName());
          restaurantAddress.setText(r.getAddress());
          restaurantTel.setText(r.getTelephone());

          if(r.getRestaurantType().equals("Chinese")){
              restaurantTypes.check(R.id.chinese);
          }
          else if(r.getRestaurantType().equals("Western")){
              restaurantTypes.check(R.id.western);
          }
          else if(r.getRestaurantType().equals("Indian")){
              restaurantTypes.check(R.id.indian);
          }
          else if(r.getRestaurantType().equals("Indonesian")){
              restaurantTypes.check(R.id.indonesian);
          }
          else if(r.getRestaurantType().equals("Korean")){
              restaurantTypes.check(R.id.korean);
          }
          else if(r.getRestaurantType().equals("Japanese")){
              restaurantTypes.check(R.id.japanese);
          }
          host.setCurrentTab(1);
      }
    };

}