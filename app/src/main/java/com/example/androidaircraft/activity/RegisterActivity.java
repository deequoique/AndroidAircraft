package com.example.androidaircraft.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidaircraft.R;
import com.example.androidaircraft.player.Player;
import com.example.androidaircraft.player.PlayerDaoImpl;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collection;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity{
    private EditText id;
    private EditText passWord;
    private Button btn;
    private String idString, passWordString;
    private PlayerDaoImpl playerDao = new PlayerDaoImpl();
    private Player player;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.register);
        PlayerDaoImpl playerDao = new PlayerDaoImpl();
        id = (EditText) findViewById(R.id.input);
        Log.i("test", String.valueOf(id));
        passWord = (EditText) findViewById(R.id.password);
        btn = (Button) findViewById(R.id.login);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login:
                login();
                Toast.makeText(RegisterActivity.this,"click",Toast.LENGTH_SHORT).show();
        }
    }

    public void login(){
        Log.i("test", String.valueOf(id));
        idString = id.getText().toString();
        passWordString = passWord.getText().toString();
        player = Player.getInstance();
        player.passWord = passWordString;
        player.name = idString;
        new Thread(new Connect()).start();
    }

    public class Connect implements Runnable{

        @Override
        public void run() {
            try {
                Socket socket = new Socket();
                Log.i("test","do");
                socket.connect(new InetSocketAddress("192.168.56.1",9999),5000);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(player);


                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                try {
                    playerDao.players.addAll ((ArrayList<Player>) in.readObject());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                oos.close();
                in.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for(Player p : playerDao.players){
                System.out.println(p.name+"\n");
            }
            judge();
        }
    }

    public void judge(){
        Looper.prepare();
        for(Player players: playerDao.getPlayers()){
            if(players.name.equals(idString)){
                if(players.passWord.equals(passWordString)){
                    player = players;
                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegisterActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                }
                break;
            }
            else {
                System.out.println("no id");
                Toast.makeText(RegisterActivity.this,"您还没有账号",Toast.LENGTH_SHORT).show();
            }
        }
        Looper.loop();
    }
}
