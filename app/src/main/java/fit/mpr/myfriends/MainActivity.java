package fit.mpr.myfriends;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import fit.mpr.myfriends.adapters.FriendAdapter;
import fit.mpr.myfriends.models.Friend;

public class MainActivity extends AppCompatActivity {

    public static final int FRIEND_ADDED = 1;
    private List<Friend> friends;
    private FriendAdapter friendAdapter;

    private RecyclerView rvFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recycle view
        rvFriends = findViewById(R.id.rvFriends);

        //demo data
        friends = new ArrayList<>();
        friends.add(new Friend("Thu Thuy", "thuthuyy.242@gmail.com", "0124578979"));
        friends.add(new Friend("Two Twee", "thuthuyy.242@gmail.com", "0124578979"));
        friends.add(new Friend("tunyy", "thuthuyy.242@gmail.com", "0124578979"));
        friends.add(new Friend("Twee", "thuthuyy.242@gmail.com", "0124578979"));
        friends.add(new Friend("Two Three", "thuthuyy.242@gmail.com", "0124578979"));

        //setup RecycleView
        //adapter
        friendAdapter = new FriendAdapter(friends);
        rvFriends.setAdapter(friendAdapter);

        //layout manager
        rvFriends.setLayoutManager(new LinearLayoutManager(this));

        //handle add event
        ImageButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddFriendActivity.class);
                startActivityForResult(intent, FRIEND_ADDED);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == FRIEND_ADDED){
            Friend friend = (Friend) data.getSerializableExtra("FRIEND");

           friends.add(0, friend);

           //notify the adapter recycle view
            friendAdapter.notifyItemInserted(0);
        }
    }
}