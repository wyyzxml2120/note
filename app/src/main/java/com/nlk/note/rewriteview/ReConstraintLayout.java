package com.nlk.note.rewriteview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.nlk.note.R;

public class ReConstraintLayout extends ConstraintLayout {

    private Integer alpha;

    public ReConstraintLayout(Context context) {
        super(context);

    }

    public ReConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public ReConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ReConstraintLayout);
        alpha = a.getInteger(R.styleable.ReConstraintLayout_elk,0);
        a.recycle();
        return super.generateLayoutParams(attrs);
    }

    @Override
    public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
        child.getBackground().setAlpha(alpha);
        super.addView(child, index, params);
    }
}
