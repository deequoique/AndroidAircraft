package com.example.androidaircraft.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity{
    private EditText id;
    private EditText passWord;
    private Button btn;
    private String idString, passWordString;
    private PlayerDaoImpl playerDao = new PlayerDaoImpl();
    private Player player;
    private Socket socket;
    private boolean flag = false;
    private PrintWriter out;
    public static ArrayList<Player> save;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        player = Player.getInstance();

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
        }
    }

    public void login(){
        Log.i("test", String.valueOf(id));
        idString = id.getText().toString();
        passWordString = passWord.getText().toString();
        player.passWord = passWordString;
        player.name = idString;
        new Thread(new Connect()).start();
    }

    public class Connect implements Runnable{

        @Override
        public void run() {
            try {
                socket = new Socket();
                Log.i("test","do");
                socket.connect(new InetSocketAddress(MainActivity.IP,9999),5000);

                /**
                 * 向服务器发送请求码
                 */

                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8),true);
                out.println("register" + "");

                /**
                 * 将从服务器获取的账号密码信息存入列表
                 */
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                try {
                    playerDao.players.addAll ((ArrayList<Player>) in.readObject());
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(null);
                    oos.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                in.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            /**
             * 打印账号列表
             */
            for(Player p : playerDao.players){
                System.out.println(p.name+"\n");
            }
            judge();
        }
    }

    /**
     * 页面逻辑判断
     */
    public void judge(){
        Looper.prepare();
        for(Player players: playerDao.getPlayers()){
            if (players.name.equals(idString)){
                if(players.passWord.equals(passWordString)){
                    Player.getInstance().money = players.money;
                    Player.getInstance().power = players.power;
                    Player.getInstance().hp = players.hp;
                    save = playerDao.getPlayers();
                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                }//密码正确，进入主页面
                else {
                    Toast.makeText(RegisterActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                }//密码错误
                flag = true;
                break;
            }
        }
        if(!flag) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("no id");
            Toast.makeText(RegisterActivity.this,"您还没有账号,正在为您跳转到注册界面",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this , CreateActivity.class);
            startActivity(intent);
        }//没有账户，跳转到注册界面
        Looper.loop();
    }
}
