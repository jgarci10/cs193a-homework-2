package com.example.jorge.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.media.MediaPlayer;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ArrayList<String> taskList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private int completedScore = 0;

    @Override
    //cretaes the addpate and contects it to the ListView
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView thingsToDoList = (ListView) findViewById(R.id.task_list);
        thingsToDoList.setOnItemClickListener(this);

        //set the list to store tasks
        adapter = new ArrayAdapter<>(
                this,
                R.layout.todolistlayout,
                R.id.item_text,
                taskList
        );
        thingsToDoList.setAdapter(adapter);
    }

    //add the world that is in the textview to the arraylist so that it appears within the ListView
    public void toAdd(View view) {
        EditText the_task = (EditText) findViewById(R.id.task);
        String task = the_task.getText().toString();
        taskList.add(task); //add the task to the arraylist containing the words
        adapter.notifyDataSetChanged();
    }

    @Override
    //have to remove the task from the arraylist and then update the listview
    public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
        taskList.remove(index);
        adapter.notifyDataSetChanged();
        completedScore++;
        if (completedScore > 4){
            Toast.makeText(this,"You have done enough. Go play Pokemon!!",Toast.LENGTH_LONG).show();
            MediaPlayer mp = MediaPlayer.create(this,R.raw.pokemon_song);
            mp.start();

        }
    }
}
