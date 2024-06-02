package com.example.coffeemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class testAcitivity extends AppCompatActivity {
    String url = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&ssc=tab.nx.all&query=%EB%84%A4%EC%9D%B4%EB%B2%84%EB%82%A0%EC%94%A8&oquery=%EB%84%A4%EC%9D%B4%EB%B2%84%EB%82%A0%EC%94%A8&tqi=iCK7HdpzLiwsskkE6WossssstCw-276301";
    String weather_string = "";
    int weather_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_hall);
        new getWeatherData().execute();
    }

    private class getWeatherData extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Document doc = null;

            try {
                doc = Jsoup.connect(url).get();

                // get weather through web crawling
                Elements content1 = doc.select(".graph_content");
                weather_string = content1.text(); // 초기화한 weather_string에 값 저장
                Log.d("testActivity", "weather_string: "+weather_string);
                String[] split_weather = weather_string.split(" "); // split_weather: split을 위해 존재.
                String now_weather = split_weather[1];
                String now_time = split_weather[3];
                now_time=now_time.split("시")[0];
                Integer time = Integer.parseInt(now_time)-1;
                Log.d("testActivity", "now_weather: "+now_weather+"\nnow_time: "+now_time);

                // now_weather 값에 따라 weather_index 설정
                if (now_weather.equals("비") || now_weather.equals("구름많음") || now_weather.equals("흐림") || now_weather.equals("소나기")) {
                    if(time>=7&&time<20) weather_index=0; //비, 구름많음, 흐림, 소나기인 날씨지표에서 7~19시인 경우 흐린 낮으로 표시
                    else weather_index = 2; //비, 구름많음, 흐림, 소나기인 날씨지표에서 20~6시인 경우 흐린 밤으로 표시
                } else {
                    if(time>=7&&time<20) weather_index=1; //비, 구름많음, 흐림, 소나기 외 날씨지표에서 7~19시인 경우 맑은 낮으로 표시
                    else weather_index = 3;//비, 구름많음, 흐림, 소나기 외 날씨지표에서 20~69시인 경우 맑은 밤으로 표시
                }
                Log.d("weather_index", ""+weather_index);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            changeBG(weather_index);
        }
    }

    private void changeBG(int weather_index) {

        Log.d("weather_index at changeBG", ""+weather_index);
        Log.d("weather", "weather changed");
        Intent intent = new Intent(getApplicationContext(), StoreHall.class);
        intent.putExtra("weather_index", weather_index);
        startActivity(intent);
    }
}