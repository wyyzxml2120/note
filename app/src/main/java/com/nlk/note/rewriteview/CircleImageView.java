package com.nlk.note.rewriteview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class CircleImageView extends AppCompatImageView {
    private float[] rids = {dp2px(5), dp2px(5), dp2px(5), dp2px(5), dp2px(5), dp2px(5), dp2px(5), dp2px(5),};


    public CircleImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        setMeasuredDimension(width, width);
//    }

    /**
     * 画图
     *
     * @param canvas
     */
    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
        /*向路径中添加圆角矩形。radii数组定义圆角矩形的四个圆角的x,y半径。radii长度必须为8*/
        path.addRoundRect(new RectF(0, 0, w, h), rids, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }

    public static int dp2px(final float dpValue) {
        final float scale = 5;
        return (int) (dpValue * scale + 0.5f);
    }

}
