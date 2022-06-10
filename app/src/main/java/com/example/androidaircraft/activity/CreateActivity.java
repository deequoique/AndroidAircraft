package com.example.androidaircraft.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidaircraft.R;
import com.example.androidaircraft.player.Player;
import com.example.androidaircraft.player.PlayerDaoImpl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collection;

public class CreateActivity extends AppCompatActivity {
    private EditText id;
    private EditText passWord1;
    private EditText passWord2;
    private String idString, passWordString1 ,passWordString2;
    private PlayerDaoImpl playerDao = new PlayerDaoImpl();
    private Player player = Player.getInstance();
    private Socket socket;
    private boolean flag = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
        id = (EditText)findViewById(R.id.create_id);
        passWord1 = (EditText)findViewById(R.id.create_password);
        passWord2 = (EditText)findViewById(R.id.confirm_password);

    }


    public void create(View view) {

        idString = id.getText().toString();
        passWordString1 = passWord1.getText().toString();
        passWordString2 = passWord2.getText().toString();
        if(!passWordString1.equals(passWordString2)){
            Toast.makeText(CreateActivity.this,"两次密码输入不同，请重新输入",Toast.LENGTH_LONG).show();
        }
        else {
            new Thread(new Connect()).start();
        }
        player.name = idString;
        player.passWord = passWordString1;

        System.out.println(player.name);
    }

    public class Connect implements Runnable{

        @Override
        public void run() {

            socket = new Socket();
            try {
                socket.connect(new InetSocketAddress
                        ("192.168.56.1",9999),5000);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                try {
                    playerDao.players.addAll((Collection<? extends Player>) in.readObject());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                socket.shutdownInput();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                for (Player p : playerDao.players){
                    if(p.name.equals(player.name)){
                        flag = false;
                        break;
                    }
                }
                Looper.prepare();
                if(flag){
                    oos.writeObject(player);
                    Toast.makeText(CreateActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateActivity.this , RegisterActivity.class);
                    startActivity(intent);
                }
                else {
                    oos.writeObject(null);
                    Toast.makeText(CreateActivity.this,"该id已被占用",Toast.LENGTH_SHORT).show();

                }
                Looper.loop();
                in.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
