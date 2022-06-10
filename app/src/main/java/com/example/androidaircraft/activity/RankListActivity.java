package com.example.androidaircraft.activity;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class RankListActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Player> datas = new ArrayList<>();
    private PlayerAdapter playerAdapter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rank_list);

        initDatas();
        listView = findViewById(R.id.rank_list);
        playerAdapter= new PlayerAdapter(this,datas);
        listView.setAdapter(playerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(RankListActivity.this,"您单击了"+datas.get(i).name,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDatas(){
        datas.add(Player.getInstance());
    }

}
