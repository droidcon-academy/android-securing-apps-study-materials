package com.preemptive.accountmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private TestAccount account;

    private String API_KEY = "oKcL2mTq9Wo06JrlDMcFmK2yXBgLV2gzTNTcFRwRFORbE";
    //public Boolean isEmulated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String user = intent.getStringExtra(LoginActivity.EXTRA_USER);
        setTitle("Welcome " + user);

        if (TestAccounts.accounts.containsKey(user) ) {
            account = TestAccounts.accounts.get(user);
        }
        else {
            account = new TestAccount();
            account.userName = user;
        }

        initForm();

        Button updateButton = (Button)findViewById(R.id.account_update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveForm();
                Toast.makeText(MainActivity.this, "Account Information Saved", Toast.LENGTH_LONG).show();
            }
        });

        Button cancelButton = (Button)findViewById(R.id.account_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initForm();
            }
        });

        Button logoutButton = (Button)findViewById(R.id.account_logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        Button queryButton = (Button)findViewById(R.id.account_query_button);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query(account.accountId);
                Toast.makeText(MainActivity.this, "Account Query " + account.accountId, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveForm() {

        TextView lNameView = (TextView)findViewById(R.id.lname);
        TextView fNameView = (TextView)findViewById(R.id.fname);
        TextView accountIdView= (TextView)findViewById(R.id.account);

        account.accountId = accountIdView.getText().toString();
        account.lastName = lNameView.getText().toString();
        account.firstName = fNameView.getText().toString();

        if (!TestAccounts.accounts.containsKey(account.userName)) {
            TestAccounts.accounts.put(account.userName, account);
        }
    }

    private void initForm() {

        TextView lNameView = (TextView)findViewById(R.id.lname);
        TextView fNameView = (TextView)findViewById(R.id.fname);
        TextView accountIdView= (TextView)findViewById(R.id.account);

        check();

        lNameView.setText(account.lastName);
        fNameView.setText(account.firstName);
        accountIdView.setText(account.accountId);
    }

    private void logout() {
        Class activity = false ? OptionsActivity.class : LoginActivity.class;
        Intent intent =  new Intent( MainActivity.this, activity);
        startActivity(intent);
    }

    private void query(String accountID){
        proceed();
        try {
            String baseUrl = "http://somehost.com/api/endpoint";
            String apiKey = API_KEY;
            String accountId = accountID;

            String urlString = baseUrl + "?apikey=" + URLEncoder.encode(apiKey, "UTF-8")
                    + "&accountID=" + URLEncoder.encode(accountId, "UTF-8");

            URL apiEndpoint = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) apiEndpoint.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());
        }
        catch (Exception ex){

        }
    }

    private void check() {

    }


    private void proceed() {
        if (Options.debugFlag) {
            //Class activity = isEmulated ? OptionsActivity.class : MainActivity.class;
            Class activity = OptionsActivity.class;
            Intent intent = new Intent(MainActivity.this, activity);
            intent.putExtra("Message", "You are running in a debugger.  This is prohibited!  ");
            startActivity(intent);
        }
        else if (Options.rootedFlag) {
            //Class activity = isEmulated ? OptionsActivity.class : MainActivity.class;
            Class activity = OptionsActivity.class;
            Intent intent = new Intent(MainActivity.this, activity);
            intent.putExtra("Message", "You are running on a compromised device.  This is prohibited!");
            startActivity(intent);
        }
    }

}
