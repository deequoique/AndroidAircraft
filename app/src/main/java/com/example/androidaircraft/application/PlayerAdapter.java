package com.example.androidaircraft.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidaircraft.R;
import com.example.androidaircraft.player.Player;

import java.util.ArrayList;

public class PlayerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Player> players;

    public PlayerAdapter(Context context , ArrayList<Player> players){
        this.context = context;
        this.players = players;
    }
    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int i) {
        return players.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Player player = (Player) getItem(i);
        View v;
        ViewHold viewHold;
        if (view == null){
            v = LayoutInflater.from(context).inflate(R.layout.rank_list_item,null);

            viewHold = new ViewHold();

            viewHold.playerName = v.findViewById(R.id.player_id);
            viewHold.playerNo = v.findViewById(R.id.number);
            viewHold.playerScore = v.findViewById(R.id.score);
            viewHold.playerTime = v.findViewById(R.id.time);

            v.setTag(viewHold);
        }else {
            v = view;
            viewHold = (ViewHold) v.getTag();
        }

        viewHold.playerTime.setText(player.time);

        viewHold.playerScore.setText(Integer.toString(player.score));
        viewHold.playerNo.setText(Integer.toString(i));
        viewHold.playerName.setText(player.name);

        return v;
    }


    class ViewHold{
        TextView playerNo;
        TextView playerName;
        TextView playerScore;
        TextView playerTime;
    }
}
