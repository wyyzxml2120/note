package com.nlk.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Method;

public class TestActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        //initView();
    }

    private void initView() {
        ConstraintLayout fu_layout = (ConstraintLayout)findViewById(R.id.fu_layout);
        TextView zi_layout = (TextView)findViewById(R.id.zi_layout);
        TextView v_foot = findViewById(R.id.v_foot);

        addLayoutListener(fu_layout, zi_layout,v_foot);

    }

    /*
     * main: 父布局,最外层的LinearLayout
     * scroll:子布局:LinearLayout
     * foot : 最外层的view
     **/

    private void addLayoutListener(final View main, final View scroll, final View foot) {

        main.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            Rect rect = new Rect();
            main.getWindowVisibleDisplayFrame(rect);
            int nav_height = getNavigationBarHeight(TestActivity2.this);
            int mainInvisibleHeight = main.getRootView().getHeight() - nav_height - (rect.bottom - rect.top);
            if (mainInvisibleHeight > 260) {
                if (foot.getVisibility() == View.GONE) {
                    int[] location = new int[2];
                    scroll.getLocationInWindow(location);
                    int srollHeight = (location[1] + scroll.getHeight()) - rect.bottom;
                    ViewGroup.LayoutParams Params = foot.getLayoutParams();
                    Params.height = srollHeight;
                    foot.setLayoutParams(Params);
                    foot.setVisibility(View.VISIBLE);

                }
            } else {
                foot.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 获取虚拟键的高度
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        if (hasNavBar(context)) {
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    /**
     * 判断是否有虚拟键
     * @param context
     * @return
     */
    public static boolean hasNavBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
        }
        return hasNavigationBar;
    }


}