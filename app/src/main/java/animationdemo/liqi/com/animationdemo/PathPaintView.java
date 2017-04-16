package animationdemo.liqi.com.animationdemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by liqi on 2017/4/16.
 */

public class PathPaintView extends View implements View.OnClickListener{

    private Path mPath;
    private Paint mPaint;
    private float mLength;
    private float mAnimValue;
    private PathEffect mEffect;
    private PathMeasure mPathMeasure;
    private ValueAnimator animator;

    public PathPaintView(Context context) {
        super(context);
    }

    public PathPaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mPath = new Path();

        mPath.moveTo(100,100);
        mPath.lineTo(100,500);
        mPath.lineTo(400,300);
        mPath.close();

        mPathMeasure = new PathMeasure();
        mPathMeasure.setPath(mPath,true);

        mLength = mPathMeasure.getLength();

        animator = ValueAnimator.ofFloat(1,0);
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimValue = (float) animation.getAnimatedValue();
                //偏移量越来越小，实线越来越多
                mEffect = new DashPathEffect(new float[]{mLength,mLength},mLength * mAnimValue);
                mPaint.setPathEffect(mEffect);
                invalidate();
            }
        });

        setOnClickListener(this);

    }

    public PathPaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);
    }

    @Override
    public void onClick(View v) {
        animator.start();
    }
}
