package com.example.eventhandling;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText; // TextView?
import android.view.MotionEvent;     // needed for gesture
import android.view.GestureDetector;     // needed for gesture
import android.support.v4.view.GestureDetectorCompat;     // needed for gesture
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{
// implement a couple of interfaces to handle gestures (OnGestureListener for several gesture types)

    private EditText gestureText;                       // for use with a text control on this app
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // gesture recogniser
        this.gestureText = (EditText) findViewById(R.id.gesture_edit_text);
        this.gestureDetector =  new GestureDetectorCompat(this, this);
        gestureDetector.setOnDoubleTapListener(this);   // NB DoubleTap separate from other gestures (cf multiple inheritance, above)



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

    // implementation for gesture recognise'ing

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        gestureText.setText(R.string.tap_confirmed_text);
        return true;            // true indicating that the event was handled
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        gestureText.setText(R.string.double_tap_text);
        return true;            // true indicating that the event was handled
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        gestureText.setText(R.string.double_tap_event_text);
        return true;            // true indicating that the event was handled
    }

    @Override
    public boolean onDown(MotionEvent e) {
        gestureText.setText(R.string.down_text);
        return true;            // true indicating that the event was handled
    }

    @Override
    public void onShowPress(MotionEvent e) {
        gestureText.setText(R.string.show_text);

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        gestureText.setText(R.string.up_text);
        return true;            // true indicating that the event was handled
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        gestureText.setText(R.string.scroll_text);
        return true;            // true indicating that the event was handled
    }

    @Override
    public void onLongPress(MotionEvent e) {
        gestureText.setText(R.string.long_press_text);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        gestureText.setText(R.string.fling_text);
        return true;            // true indicating that the event was handled
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

    // end of gesture recognizers


    // as well as gesture recognizer code, override onTouchEvent as well
    // onTouch is the default event of Activity whenever user d'touch the screen

    /**
     * Called when a touch screen event was not handled by any of the views
     * under it.  This is most useful to process touch events that happen
     * outside of your window bounds, where there is no view to receive it.
     *
     * @param event The touch screen event being processed.
     * @return Return true if you have consumed the event, false if you haven't.
     * The default implementation always returns false.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // first, detect if event being processed touch was actually a gesture
        this.gestureDetector.onTouchEvent(event);
        // if not a gesture, go ahead to super-class as normal
        return super.onTouchEvent(event);
    }
}
