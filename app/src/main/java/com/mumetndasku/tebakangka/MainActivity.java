package com.mumetndasku.tebakangka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomNumber = 0;
    int valueRange = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Slider slider = findViewById(R.id.idSlider);
        Button generateBtnV = findViewById(R.id.generateBtn);
        final Button submitBtnV = findViewById(R.id.submitBtn);
        final EditText valueAnswerV = findViewById(R.id.answerValue);
        final TextView editRangeTV = findViewById(R.id.idEditRange);
        final TextView probabilitasTV = findViewById(R.id.idProbabilitas);

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                int valueInt = (int) value;
                valueRange = valueInt;
                float chance = (100/value);

                // For decimal formatting
                DecimalFormat df = new DecimalFormat("#.#");
                df.setRoundingMode(RoundingMode.CEILING);

                String probabilitasString = "(Probabilitas : "+String.valueOf(df.format(chance))+"% )";

                probabilitasTV.setText(probabilitasString);
                editRangeTV.setText(String.valueOf(valueInt));
            }
        });

        generateBtnV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateRandomNumber();
                Toast.makeText(getApplicationContext(), "Angka telah berhasil di acak, silahkan tebak!", Toast.LENGTH_SHORT).show();
            }
        });

        submitBtnV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String answer = String.valueOf(valueAnswerV.getText());
                submit(Integer.parseInt(answer));
            }
        });
    }

    public void generateRandomNumber(){
        Random rand = new Random();
        int upperbound = valueRange;
        int int_random = rand.nextInt(upperbound);
        randomNumber = int_random+1;
    }

    public void submit(int value){
        if(value == randomNumber){
            Toast.makeText(getApplicationContext(), "Selamat Jawaban Anda Benar", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Maaf Jawaban Anda Belum Benar", Toast.LENGTH_SHORT).show();
        }
    }

}
