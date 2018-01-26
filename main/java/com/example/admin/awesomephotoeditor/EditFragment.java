package com.example.admin.awesomephotoeditor;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Admin on 5/25/2017.
 */

public class EditFragment extends Fragment implements View.OnTouchListener{
    private ImageView letterView;                       // The letter that the user drags.
    private ImageView emptyLetterView;              // The letter outline that the user is supposed to drag letterView to.
    private RelativeLayout mainLayout;
    Toolbar toolbar;
    Button next,done;
    private FragmentActivity fragmentActivity;
    int [] src = new int[]{R.drawable.f1,R.drawable.f2,R.drawable.f3,R.drawable.f4,R.drawable.f5,R.drawable.f6};


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.emojis, container, false);
        letterView=(ImageView)v.findViewById(R.id.img2);
        toolbar=(Toolbar) v.findViewById(R.id.tb2);
        next=(Button)v.findViewById(R.id.next2);
        done=(Button)v.findViewById(R.id.tick2);

        //setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.leftarrow);

        LinearLayout layout = (LinearLayout)v.findViewById(R.id.thumbnails2);

        for (int i = 0; i < 5; i++) {
            ImageView imageView =new ImageView(fragmentActivity);
            imageView.setId(i);
            imageView.setPadding(10, 10, 10, 10);
            imageView.setImageBitmap(BitmapFactory.decodeResource(
                    getResources(), src[i]));
            imageView.setOnTouchListener(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            layout.addView(imageView);

        }

        mainLayout = (RelativeLayout)v. findViewById(R.id.mainLayout);
        mainLayout.setOnTouchListener(this);
        emptyLetterView = (ImageView)v. findViewById(R.id.img2);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onAttach(Activity activity) {
        fragmentActivity =(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private boolean dragging = false;
    private Rect hitRect = new Rect();

    @Override
    /**
     * NOTE:  Had significant problems when I tried to react to ACTION_MOVE on letterView.   Kept getting alternating (X,Y)
     * locations of the motion events, which caused the letter to flicker and move back and forth.  The only solution I could
     * find was to determine when the user had touched down on the letter, then process moves in the ACTION_MOVE
     * associated with the mainLayout.
     */
    public boolean onTouch(View v, MotionEvent event) {
        boolean eventConsumed = true;
        int x = (int)event.getX();
        int y = (int)event.getY();

        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            if (v == letterView) {
                letterView.setImageResource(R.drawable.f1);
                dragging = true;
                eventConsumed = false;
            }
        } else if (action == MotionEvent.ACTION_UP) {

            if (dragging) {
                emptyLetterView.getHitRect(hitRect);
                if (hitRect.contains(x, y)) {
                    letterView.setImageResource(R.drawable.f2);
                    setSameAbsoluteLocation(letterView, emptyLetterView);
                }
            }
            dragging = false;
            eventConsumed = false;

        } else if (action == MotionEvent.ACTION_MOVE) {
            if (v != letterView) {
                if (dragging) {
                    setAbsoluteLocationCentered(letterView, x, y);
                }
            }
        }

        return eventConsumed;

    }


    private void setSameAbsoluteLocation(View v1, View v2) {
        AbsoluteLayout.LayoutParams alp2 = (AbsoluteLayout.LayoutParams) v2.getLayoutParams();
        setAbsoluteLocation(v1, alp2.x, alp2.y);
    }


    private void setAbsoluteLocationCentered(View v, int x, int y) {
        setAbsoluteLocation(v, x - v.getWidth() / 2, y - v.getHeight() / 2);
    }


    private void setAbsoluteLocation(View v, int x, int y) {
        AbsoluteLayout.LayoutParams alp = (AbsoluteLayout.LayoutParams) v.getLayoutParams();
        alp.x = x;
        alp.y = y;
        v.setLayoutParams(alp);
    }

}
