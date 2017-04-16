package animationdemo.liqi.com.animationdemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by liqi on 2017/4/3.
 */

public class PathBezierVIew extends View implements View.OnClickListener{

    private int mStartPointX;
    private int mStartPointY;

    private int mEndPointX;
    private int mEndPointY;

    private int mFlagPointX;
    private int mFlagPointY;

    private int mMovePointX;
    private int mMovePointY;

    private Path mPath;
    private Paint mPaintPath;
    private Paint mPaintCircle;

    public PathBezierVIew(Context context) {
        super(context);
    }

    public PathBezierVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mPaintPath = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintPath.setStyle(Paint.Style.STROKE);
        mPaintPath.setStrokeWidth(8);
        mPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);

        mStartPointX = 100;
        mStartPointY = 100;

        mMovePointX = mStartPointX;
        mMovePointY = mStartPointY;

        mEndPointX = 600;
        mEndPointY = 600;

        mFlagPointX = 500;
        mFlagPointY = 0;

        setOnClickListener(this);
    }

    public PathBezierVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mStartPointX,mStartPointY,20,mPaintCircle);
        canvas.drawCircle(mEndPointX,mEndPointY,20,mPaintCircle);

        canvas.drawCircle(mMovePointX,mMovePointY,20,mPaintCircle);

        mPath.reset();
        mPath.moveTo(mStartPointX,mStartPointY);
        mPath.quadTo(mFlagPointX,mFlagPointY,mEndPointX,mEndPointY);
        canvas.drawPath(mPath,mPaintPath);
    }

    @Override
    public void onClick(View v) {
        BezierEvaluator evaluator = new BezierEvaluator(new PointF(mFlagPointX,mFlagPointY));

        ValueAnimator animator = ValueAnimator.ofObject(evaluator,new PointF(mStartPointX,mStartPointY),new PointF(mEndPointX,mEndPointY));
        animator.setDuration(600);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointf = (PointF) animation.getAnimatedValue();
                mMovePointX = (int) pointf.x;
                mMovePointY = (int) pointf.y;
                invalidate();
            }
        });
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}
