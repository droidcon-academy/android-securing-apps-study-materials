package com.preemptive.accountmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class OptionsActivity extends AppCompatActivity {

    private ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>() {

        {
            add(new MenuItem("Run Ransomware", true, null));
            add(new MenuItem("Wipe Storage", true, null));
            add(new MenuItem("Tweet Shame Me", true, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = getIntent();
                    String user = intent.getStringExtra(LoginActivity.EXTRA_USER);

                    String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

                    //twitterClient.sendMessage(user + " at " + time + " shouldn’t have peeked. #NoAndroidDebugForMe");
                }
            }));
            add(new MenuItem("I'm Feeling Lucky", true, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent( OptionsActivity.this, MainActivity.class);
                    intent.putExtra(LoginActivity.EXTRA_USER, "Attacker");
                    startActivity(intent);
                }
            }));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        TextView textViewOptions = (TextView)findViewById(R.id.textview_options);

        Bundle extras = getIntent().getExtras();
        String message = extras.getString("Message");

        textViewOptions.setText(message + "  Please select the consequences of your action:");

        MenuItemAdapter adapter = new MenuItemAdapter(this,
                android.R.layout.simple_list_item_1, menuItemList);

        ListView listView = (ListView) findViewById(R.id.listview_options);
        listView.setAdapter(adapter);

        TextView view = (TextView)OptionsActivity.this.findViewById(R.id.textview_options_status);
        view.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 ((TextView)view).setText("");
             }
        } );

    }
}
