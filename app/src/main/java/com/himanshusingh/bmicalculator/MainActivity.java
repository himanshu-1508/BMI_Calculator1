package com.himanshusingh.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.text.DecimalFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText height,weight;
    private MaterialButton btnSubmit,btnReset;
    private MaterialTextView bmiValue,bmiResult;

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.about_Bmi:
                Intent aboutBmiIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Body_mass_index"));
                startActivity(aboutBmiIntent);break;

            case R.id.bmi_Chart:
                Intent bmiChartIntent = new Intent(MainActivity.this, BmiChartActivity.class);
                startActivity(bmiChartIntent);
                break;

            case R.id.history:
                Toast.makeText(this,"Under Development", Toast.LENGTH_SHORT).show();
                break;

            case R.id.contact:
                Intent contactIntent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(contactIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        bmiValue=findViewById(R.id.bmi_Value);
        bmiResult=findViewById(R.id.bmi_Result);
        btnSubmit=findViewById(R.id.btn_Submit);
        btnReset=findViewById(R.id.btn_Reset);

        btnSubmit.setOnClickListener(this);
        btnReset.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_Submit)
        {
            if(submitBtnError())
            {
                String Height = Objects.requireNonNull(height.getText()).toString();
                String Weight = Objects.requireNonNull(weight.getText()).toString();

                double heightValue = (Double.parseDouble(Height))/100;
                double weightValue = Double.parseDouble(Weight);

                DecimalFormat df =new DecimalFormat("#.##");
                double bmiCalculation = weightValue/(heightValue*heightValue);
                bmiCalculation = Double.valueOf(df.format(bmiCalculation));
                bmiValue.setText("Your BMI value is "+  bmiCalculation);

                if(bmiCalculation<18.5)
                {
                    bmiResult.setText("You are Underweight");
                    bmiValue.setTextColor(Color.parseColor("#01B0F1"));
                    bmiResult.setTextColor(Color.parseColor("#01B0F1"));
                }
                if(bmiCalculation>=18.5 && bmiCalculation<=24.9)
                {
                    bmiResult.setText("You are Healthy");
                    bmiValue.setTextColor(Color.parseColor("#92D14F"));
                    bmiResult.setTextColor(Color.parseColor("#92D14F"));
                }
                if(bmiCalculation>=25 && bmiCalculation<=29.9)
                {
                    bmiResult.setText("You are Overweight");
                    bmiValue.setTextColor(Color.parseColor("#F7A782"));
                    bmiResult.setTextColor(Color.parseColor("#F7A782"));
                }
                if(bmiCalculation>=30 && bmiCalculation<=34.9)
                {
                    bmiResult.setText("You are Obese");
                    bmiValue.setTextColor(Color.parseColor("#FFC000"));
                    bmiResult.setTextColor(Color.parseColor("#FFC000"));
                }
                if(bmiCalculation>=35)
                {
                    bmiResult.setText("You are Extremely Obese");
                    bmiValue.setTextColor(Color.parseColor("#FE0000"));
                    bmiResult.setTextColor(Color.parseColor("#FE0000"));
                }

                btnSubmit.setVisibility(View.INVISIBLE);
                btnReset.setVisibility(View.VISIBLE);
            }


        }
        if (view.getId()==R.id.btn_Reset){
            height.setText("");
            weight.setText("");
            bmiValue.setText("");
            bmiResult.setText("");
            btnReset.setVisibility(View.INVISIBLE);
            btnSubmit.setVisibility(View.VISIBLE);
        }
    }

    private boolean submitBtnError() {
        if(height.getText().toString().length()==0)
        {
            height.requestFocus();
            height.setError("Field cannot be empty");
            return false;
        }
        if(weight.getText().toString().length()==0)
        {
            weight.requestFocus();
            weight.setError("Field cannot be empty");
            return false;
        }
        return true;
    }
}