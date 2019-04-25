package com.example.eventhandling;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText; // TextView?
import android.view.View.OnLongClickListener;       // import this especially for OnlongClick to work?


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        // Event handling: (1) add event listener (2) callback method.
        // cf boilerplate event code above
        Button new_button = findViewById(R.id.new_button);

        new_button.setOnClickListener(new Button.OnClickListener()
              {
                  public void onClick(View v)
                  {
                      // callback listener
                      EditText pnjText = findViewById(R.id.pnjText);
                      pnjText.setText(R.string.clicked);
                      Snackbar.make(v, R.string.clicked, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                      // NB snackbar to display info to user
                  }
              }
        );
        // NB above pattern for event listening and callback

        new_button.setOnLongClickListener(new Button.OnLongClickListener()

            {
                // :-z lost loads of time after typing public boolean OnLongClick (View v)(sic) NB *O*nLongClick
                // messages included, e.g. "cannot find symbol class OnLongClick", and "anonymous class derived from OnLongClickListener must ..."
                public boolean onLongClick(View v)      // :-z lost loads of time after typing public boolean OnLongClick (View v)(sic) NB *O*nLongClick
                {
                    // this is the callback
                    EditText pnjText = findViewById(R.id.pnjText);
                    pnjText.setText(R.string.long_clicked);
                    Snackbar.make(v, R.string.long_clicked, Snackbar.LENGTH_INDEFINITE).setAction("Will this appear anywhere?", null).show();
                    return true;
                }
            });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
