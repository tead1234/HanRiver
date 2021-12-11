package com.example.hanriver;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;


public class SubActivity extends AppCompatActivity {
    String htmlPageUrl;
    TextView tx2;
    TextView tx;
    ImageButton bt ;
    ImageButton bt2;
    ImageButton bt3;
    String stockName;
    String RealtimePrice;
    double RealtimePrice2;
    double ExPrice;
    double percent;
    double percent2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Intent intent = getIntent();
        String StockAver = intent.getStringExtra("AveragePrice");
        String StockName = intent.getStringExtra("Input_Name");
        bt = (ImageButton) findViewById(R.id.imagebt);
        bt2=(ImageButton) findViewById(R.id.imagebt2);
        bt3=(ImageButton) findViewById(R.id.imagebt3);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, SubActivity2.class);
                intent.putExtra("bt","https://tead1234.github.io/myweb/");
                startActivity(intent);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, SubActivity2.class);
                intent.putExtra("bt","http://msweb123.dothome.co.kr/");
                startActivity(intent);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, SubActivity2.class);
                intent.putExtra("bt","");
                startActivity(intent);
            }
        });
        htmlPageUrl = String.format("https://finance.naver.com/item/main.nhn?code=%s",StockName );
        tx2 = (TextView) findViewById(R.id.평균단가);
        tx = (TextView) findViewById(R.id.Stock_name);
        ExPrice = Double.parseDouble(StockAver);
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();
        if(percent<-0.10){
            bt.setVisibility(View.VISIBLE); //bt 소주
            bt2.setVisibility(View.GONE); // bt2 버핏
            bt3.setVisibility(View.GONE); // bt3 clzls
        }else if(-0.1<=percent && percent <0 ){
            bt3.setVisibility(View.VISIBLE);
            bt2.setVisibility(View.GONE);
            bt.setVisibility(View.GONE);
        }else{
            bt.setVisibility(View.GONE);
            bt3.setVisibility(View.GONE);
            bt2.setVisibility(View.GONE);
        }



    }

    class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {
        Document doc;
        String stock;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                doc = Jsoup.connect(htmlPageUrl).get();
                Elements elem = doc.select(".date");
                String[] str = elem.text().split(" ");

                Elements todaylist =doc.select(".new_totalinfo dl>dd");

                stock = todaylist.get(3).text().split(" ")[1];
                stockName = todaylist.get(1).text().split(" ")[1];

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @SuppressLint("DefaultLocale")
        @Override
        protected void onPostExecute(Void result) {

            RealtimePrice = stock.toString().replace(",", "");
            RealtimePrice2 = Double.parseDouble(RealtimePrice);
            percent = (RealtimePrice2-ExPrice)/ExPrice;
            percent2 = percent*100;
            tx2.setText(String.format("%.2f", percent2)+"%");
            tx.setText(stockName);

        }
    }
}