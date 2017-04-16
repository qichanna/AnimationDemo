package animationdemo.liqi.com.animationdemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by liqi on 2017/4/16.
 */

public class LoadingView extends View {

    private Path mDst;
    private Path mPath;
    private Paint mPaint;
    private float mLength;
    private float mAnimValue;

    private PathMeasure mPathMeacure;

    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mPath = new Path();
        mDst = new Path();

        mPath.addCircle(400,400,100,Path.Direction.CW);
        mPathMeacure = new PathMeasure();
        mPathMeacure.setPath(mPath,true);

        mLength = mPathMeacure.getLength();

        ValueAnimator animator = ValueAnimator.ofFloat(0,1);
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mDst.reset();
        mDst.lineTo(0,0);

        float start = 0;
        float stop = mLength * mAnimValue;

        //windown系统loading效果
//        float stop = mLength * mAnimValue;
//        float start = (float) (stop - ((0.5 - Math.abs(mAnimValue - 0.5)) * mLength));

        mPathMeacure.getSegment(start,stop,mDst,true);
        canvas.drawPath(mDst,mPaint);
    }
}
