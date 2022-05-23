package com.example.androidaircraft.player;


import com.example.androidaircraft.util.CompareScore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

    private final ArrayList<Player> players = new ArrayList<>();

    @Override
    public void doAdd(Player player) {
        players.add(player);
    }

    @Override
    public void scoreArray() {
        CompareScore cs = new CompareScore();
        players.sort(cs);

    }

    public void storage() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/score"));
        oos.writeObject(players);
        oos.flush();
        oos.close();
    }

    public void read() throws IOException, ClassNotFoundException {

        File file = new File("src/score");
            if(file.exists()){
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/score"));
            ArrayList<Player> ply =(ArrayList<Player>) ois.readObject();
            players.addAll(ply);
            ois.close();}
            else {
                storage();
            }
    }
}
