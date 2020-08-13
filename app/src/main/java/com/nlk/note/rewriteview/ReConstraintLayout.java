package com.nlk.note.rewriteview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.nlk.note.R;

import java.util.ArrayList;
import java.util.HashMap;

import static com.nlk.note.R.*;

public class ReConstraintLayout extends ConstraintLayout {
    static final boolean ALLOWS_EMBEDDED = false;
    private static final boolean CACHE_MEASURED_DIMENSION = false;
    public static final String VERSION = "ConstraintLayout-1.1.3";
    private static final String TAG = "ConstraintLayout";
    private static final boolean USE_CONSTRAINTS_HELPER = true;
    private static final boolean DEBUG = false;
    SparseArray<View> mChildrenByIds = new SparseArray();
    private ArrayList<ConstraintHelper> mConstraintHelpers = new ArrayList(4);
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList(100);
    ConstraintWidgetContainer mLayoutWidget = new ConstraintWidgetContainer();
    private int mMinWidth = 0;
    private int mMinHeight = 0;
    private int mMaxWidth = 2147483647;
    private int mMaxHeight = 2147483647;
    private boolean mDirtyHierarchy = true;
    private int mOptimizationLevel = 7;
    private ConstraintSet mConstraintSet = null;
    private int mConstraintSetId = -1;
    private HashMap<String, Integer> mDesignIds = new HashMap();
    private int mLastMeasureWidth = -1;
    private int mLastMeasureHeight = -1;
    int mLastMeasureWidthSize = -1;
    int mLastMeasureHeightSize = -1;
    int mLastMeasureWidthMode = 0;
    int mLastMeasureHeightMode = 0;
    public static final int DESIGN_INFO_ID = 0;
    private Metrics mMetrics;

    private Integer alpha=0;

    public ReConstraintLayout(Context context) {
        super(context);
        //this.init((AttributeSet)null);
        Log.d("www","-");
    }

    public ReConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //this.init(attrs);
        Log.d("www","--");
    }

    public ReConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //this.init(attrs);
        Log.d("www","---");
    }

    private void init(AttributeSet attrs) {
        this.mLayoutWidget.setCompanionWidget(this);
        this.mChildrenByIds.put(this.getId(), this);
        this.mConstraintSet = null;
        if (attrs != null) {
            TypedArray a = this.getContext().obtainStyledAttributes(attrs, styleable.ConstraintLayout_Layout);
            int N = a.getIndexCount();

            for(int i = 0; i < N; ++i) {
                int attr = a.getIndex(i);
                if (attr == styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.mMinWidth = a.getDimensionPixelOffset(attr, this.mMinWidth);
                } else if (attr == styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.mMinHeight = a.getDimensionPixelOffset(attr, this.mMinHeight);
                } else if (attr == styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.mMaxWidth = a.getDimensionPixelOffset(attr, this.mMaxWidth);
                } else if (attr == styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.mMaxHeight = a.getDimensionPixelOffset(attr, this.mMaxHeight);
                } else if (attr == styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.mOptimizationLevel = a.getInt(attr, this.mOptimizationLevel);
                } else if (attr == styleable.ConstraintLayout_Layout_constraintSet) {
                    int id = a.getResourceId(attr, 0);

                    try {
                        this.mConstraintSet = new ConstraintSet();
                        this.mConstraintSet.load(this.getContext(), id);
                    } catch (Resources.NotFoundException var8) {
                        this.mConstraintSet = null;
                    }

                    this.mConstraintSetId = id;
                }
            }

            a.recycle();
        }

        this.mLayoutWidget.setOptimizationLevel(this.mOptimizationLevel);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, styleable.ReConstraintLayout);
        alpha = a.getInteger(styleable.ReConstraintLayout_elk,0);

        a.recycle();
        return super.generateLayoutParams(attrs);
    }

    @Override
    public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
        //Log.d("alpha",alpha+"");
        //alpha = 0;
        if (child.getBackground() == null){
            child.setBackgroundResource(R.drawable.empty_bg);
        }
        child.getBackground().setAlpha(alpha);
        Log.d("alpha","--");
        super.addView(child, index, params);
    }
}
