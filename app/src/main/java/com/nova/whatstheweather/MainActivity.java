package com.nova.whatstheweather;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_get_city_id, btn_get_weather_by_id, btn_get_weather_by_name;
    private ListView lv_data;
    private EditText et_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherService weatherService = new WeatherService(MainActivity.this);
        btn_get_city_id = findViewById(R.id.btn_get_city_id);
        btn_get_weather_by_id = findViewById(R.id.btn_get_weather_by_id);
        btn_get_weather_by_name = findViewById(R.id.btn_get_weather_by_name);
        lv_data = findViewById(R.id.lv_data);
        et_input = findViewById(R.id.et_input);

        btn_get_city_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                weatherService.getCityID(et_input.getText().toString(), new WeatherService.VolleyResponseListener() {
                    @Override
                    public void onResponse(String cityId) {
                        Toast.makeText(MainActivity.this, "City ID " + cityId, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn_get_weather_by_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherService.getWeatherById(et_input.getText().toString(), new WeatherService.WeatherVolleyResponseListener() {
                    @Override
                    public void onResponse(List<modal> cityWeather) {
//                        Toast.makeText(MainActivity.this, cityWeather.toString(), Toast.LENGTH_SHORT).show();

                        ArrayAdapter cityWeathersReport = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, cityWeather);
                        lv_data.setAdapter(cityWeathersReport);
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn_get_weather_by_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherService.getWeatherByName(et_input.getText().toString(), new WeatherService.GetWeather() {
                    @Override
                    public void onResponse(List<modal> cityWeathers) {
                        ArrayAdapter cityWeathersReport = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, cityWeathers);
                        lv_data.setAdapter(cityWeathersReport);
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}