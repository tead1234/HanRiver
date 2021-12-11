package com.example.hanriver;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.Search);
        EditText StockNum = (EditText) findViewById(R.id.StockNum);
        EditText StockAverage = (EditText) findViewById(R.id.StockAveragePrice);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("Input_Name", StockNum.getText().toString());
                intent.putExtra("AveragePrice", StockAverage.getText().toString());
                startActivity(intent); //
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        String themeColor;
        switch (item.getItemId()){
            case R.id.dark:
                themeColor = ThemeUtil.DARK_MODE;
                ThemeUtil.applyTheme(themeColor);
                ThemeUtil.modSave(getApplicationContext(),themeColor);
                break;
            case R.id.light:
                themeColor = ThemeUtil.LIGHT_MODE;
                ThemeUtil.applyTheme(themeColor);
                ThemeUtil.modSave(getApplicationContext(),themeColor);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}