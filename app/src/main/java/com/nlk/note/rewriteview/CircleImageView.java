package com.nlk.note.rewriteview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.nlk.note.R;

public class CircleImageView extends AppCompatImageView {
    private float[] radius;

    public CircleImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // 读取配置
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        float tempRadius = array.getDimensionPixelOffset(R.styleable.CircleImageView_radius,0);
        radius = new float[]{tempRadius, tempRadius, tempRadius, tempRadius, tempRadius, tempRadius, tempRadius, tempRadius};
        array.recycle();
    }


    /**
     * 画图
     * @param canvas 画布
     */
    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
        /*向路径中添加圆角矩形。radii数组定义圆角矩形的四个圆角的x,y半径。radii长度必须为8*/
        path.addRoundRect(new RectF(0, 0, w, h), radius, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }

}
