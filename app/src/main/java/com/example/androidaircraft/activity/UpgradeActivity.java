package com.example.androidaircraft.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidaircraft.R;
import com.example.androidaircraft.player.Player;

public class UpgradeActivity extends AppCompatActivity {

    private TextView money;
    private TextView bulletText;
    private TextView bloodText;
    private Button upBullet;
    private Button upHp;
    private Button returnButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upgrade);
        money = findViewById(R.id.money);
        bulletText = findViewById(R.id.text_power);
        bloodText = findViewById(R.id.text_hp);
        upBullet = findViewById(R.id.up_for_bullet);
        upHp = findViewById(R.id.up_for_hp);
        returnButton = findViewById(R.id.btn_return);

        Integer moneyPlus = Player.getInstance().money;

        SpannableString powerText = new SpannableString("your power is " + Player.getInstance().power);
        SpannableString moneyText = new SpannableString(moneyPlus.toString());
        SpannableString hpText = new SpannableString("your hp is " + Player.getInstance().hp);

        money.setText(moneyText);
        bulletText.setText(powerText);
        bloodText.setText(hpText);

        upBullet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Player.getInstance().money>=50){
                    Player.getInstance().power += 10;
                    Player.getInstance().money -= 50;

                    SpannableString powerText = new SpannableString("your power is " + Player.getInstance().power);
                    bulletText.setText(powerText);

                    Integer moneyPlus = Player.getInstance().money;
                    SpannableString moneyText = new SpannableString(moneyPlus.toString());
                    money.setText(moneyText);
                }else{
                    Toast.makeText(UpgradeActivity.this, "you are poor!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        upHp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Player.getInstance().money>=50){
                    Player.getInstance().hp += 50;
                    Player.getInstance().money -= 50;

                    Integer moneyPlus = Player.getInstance().money;
                    SpannableString moneyText = new SpannableString(moneyPlus.toString());
                    money.setText(moneyText);

                    SpannableString hpText = new SpannableString("your hp is " + Player.getInstance().hp);
                    bloodText.setText(hpText);
                }else{
                    Toast.makeText(UpgradeActivity.this, "you are poor!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpgradeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
