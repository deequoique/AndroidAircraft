package com.example.androidaircraft.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidaircraft.R;
import com.example.androidaircraft.application.PlayerAdapter;
import com.example.androidaircraft.player.Player;
import com.example.androidaircraft.player.PlayerDaoImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RankListActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Player> datas = new ArrayList<>();
    private PlayerAdapter playerAdapter ;
    private Socket socket;
    private PrintWriter out;
    private PlayerDaoImpl playerDao =  new PlayerDaoImpl();
    private BufferedReader s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rank_list);

        initDatas();
        listView = findViewById(R.id.rank_list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(RankListActivity.this,"您单击了"+datas.get(i).name,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class Connect implements Runnable{

        @Override
        public void run() {
            try {
                socket = new Socket();
                Log.i("test","do");
                socket.connect(new InetSocketAddress("192.168.56.1",9999),5000);

                /**
                 * 向服务器发送请求码
                 */

                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8),true);
                out.println("rankList" + "");
                /**
                 * 将从服务器获取的账号密码信息存入列表
                 */
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                try {
                        playerDao.players.addAll ((ArrayList<Player>) in.readObject());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                datas.addAll(playerDao.players);
                datas.add(Player.getInstance());
                out.println("get it"+"");
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());


                s = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
                String i = s.readLine();

                if (i.equals("give")){
                    oos.writeObject(datas);
                }
                oos.close();
                in.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //排序
            //TODO


            playerAdapter= new PlayerAdapter(RankListActivity.this,datas);
            listView.setAdapter(playerAdapter);

            /**
             * 打印账号列表
             */
            for(Player p :datas){
                System.out.println(p.name+"\n");
            }
        }
    }


    private void initDatas(){
        new Thread(new Connect()).start();

    }

}
