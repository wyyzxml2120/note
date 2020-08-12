package com.nlk.note.rewriteview;


import android.content.Context;
import android.widget.FrameLayout;

public class ElkView extends FrameLayout {

    private float alpha;
    public ElkView(Context context) {
        super(context);
    }

    public void setElkAlpha(float alpha){
        this.alpha=alpha;
    }

    public void alpha(){

    }
}