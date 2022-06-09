package com.example.androidaircraft.player;


import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Environment;
import android.provider.Settings;

import androidx.activity.result.contract.ActivityResultContracts;

import com.example.androidaircraft.util.CompareScore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author deequoique
 */
public class PlayerDaoImpl implements PlayerDao{


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public final ArrayList<Player> players = new ArrayList<>();

    @Override
    public void doAdd(Player player) {
        players.add(player);
    }

    @Override
    public void scoreArray() {
        CompareScore cs = new CompareScore();
        players.sort(cs);
    }
}
