package edu.uw.multitouchlab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    private DrawingSurfaceView view;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (DrawingSurfaceView)findViewById(R.id.drawingView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        float x = event.getX();
//        float y = event.getY() - getSupportActionBar().getHeight(); //closer to center...
        int pointerId = event.getPointerId(event.getActionIndex());
        float x = event.getX(pointerId);
        float y = event.getY(pointerId) - getSupportActionBar().getHeight();

        int action = event.getActionMasked();
        switch(action) {

            case (MotionEvent.ACTION_DOWN) : //put finger down
                Log.v(TAG, "finger down");
                view.addTouch(pointerId, x, y);
                return true;

            case(MotionEvent.ACTION_POINTER_DOWN):
                Log.v(TAG, "another finger added, pointer id: " + pointerId);
                view.addTouch(pointerId, x, y);
                return true;

            case (MotionEvent.ACTION_MOVE) : //move finger
                Log.v(TAG, "finger move");
                // TODO move balls under fingers
                return true;

            case (MotionEvent.ACTION_UP) : //lift finger up
                Log.v(TAG, "all fingers up");
                view.removeTouch(pointerId);
                return true;

            case(MotionEvent.ACTION_POINTER_UP):
                Log.v(TAG, "finger " + pointerId + " removed");
                view.removeTouch(pointerId);
                return true;

            case (MotionEvent.ACTION_CANCEL) : //aborted gesture

            case (MotionEvent.ACTION_OUTSIDE) : //outside bounds

            default :
                return super.onTouchEvent(event);

        }
    }
}