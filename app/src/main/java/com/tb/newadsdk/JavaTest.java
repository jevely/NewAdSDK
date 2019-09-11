package com.tb.newadsdk;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.tb.adsdk.tool.Logger;

public class JavaTest {

    public void test(Context context){

        RelativeLayout relativeLayout = new RelativeLayout(context);

        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Logger.INSTANCE.d("action:"+motionEvent.getAction());
                return false;
            }
        });

    }

}
