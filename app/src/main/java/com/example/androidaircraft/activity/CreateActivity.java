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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Scanner;

public class CreateActivity extends AppCompatActivity {
    private EditText id;
    private EditText passWord1;
    private EditText passWord2;
    private String idString, passWordString1 ,passWordString2;
    private PlayerDaoImpl playerDao = new PlayerDaoImpl();
    private Player player = Player.getInstance();
    private Socket socket;
    private boolean flag = true;
    private PrintWriter out;

    private BufferedReader s;

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

        //判断两次输入是否相同
        if(!passWordString1.equals(passWordString2)){
            Toast.makeText(CreateActivity.this,"两次密码输入不同，请重新输入",Toast.LENGTH_LONG).show();
        }
        else {
            new Thread(new Connect()).start();  //通过新线程连接socket
        }

        player.name = idString;
        player.passWord = passWordString1;
        player.money = 0;
        player.hp = 150;
        player.power = 10;

        System.out.println(player.name);
    }

    public class Connect implements Runnable{

        @Override
        public void run() {

            try {

                socket = new Socket();
                socket.connect(new InetSocketAddress
                        (MainActivity.IP,9999),5000);

                /**
                 * 向服务器发送请求码
                 */

                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8),true);
                out.println("create" + "");

            } catch (IOException e) {
                e.printStackTrace();
            }


            try {

                /*
                  将从服务器获取的账号密码信息存入列表
                 */
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                try {
                    playerDao.players.addAll((Collection<? extends Player>) in.readObject());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }



                //判断id是否已被注册
                for (Player p : playerDao.players){
                    if(p.name.equals(player.name)){
                        flag = false;
                        break;
                    }
                }


                Looper.prepare();
                ObjectOutputStream oos = null;
                if(flag){ 
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    //未注册则将该用户传入服务器
                    s = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
                    String i = s.readLine();
                    System.out.println(i);
                    while (i != null){
                        System.out.println("there");
                        oos.writeObject(player);

                        Toast.makeText(CreateActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CreateActivity.this , RegisterActivity.class);
                        startActivity(intent);
                    }
                }
                else {

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
