package com.example.hsport.recycleview04;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mMainList;
    private FirebaseFirestore mFirestore;
    private static final String TAG = "FireLog";
    private UsersListAdapter usersListAdapter;
    private List<Users> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usersList = new ArrayList<>();
        usersListAdapter = new UsersListAdapter(usersList);

        mMainList = findViewById(R.id.main_list);
        mMainList.setHasFixedSize(true);
        mMainList.setLayoutManager(new LinearLayoutManager(this));
        mMainList.setAdapter(usersListAdapter);
        mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error :" + e.getMessage());
                }
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        Users users = doc.getDocument().toObject(Users.class);
                        usersList.add(users);

                        usersListAdapter.notifyDataSetChanged();
                    }
//                        String user_name = doc.getDocument().getString("Phone");
//                        Log.d(TAG, "Name : " + user_name);
//                    }
//                }
//                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
//                    String user_name = doc.getString("Model");
//                    Log.d(TAG, "Model : " + user_name);
                }
            }
        });

    }

}
