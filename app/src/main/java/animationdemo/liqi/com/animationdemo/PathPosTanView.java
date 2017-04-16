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

public class PathPosTanView extends View implements View.OnClickListener{

    private Path mPath;
    private float[] mPos;
    private float[] mTan;
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private ValueAnimator mAnimator;
    private float mCurrentValue;

    public PathPosTanView(Context context) {
        super(context);
    }

    public PathPosTanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mPath.addCircle(0,0,200,Path.Direction.CW);

        mPathMeasure = new PathMeasure();
        mPathMeasure.setPath(mPath,false);

        mPos = new float[2];
        mTan = new float[2];

        setOnClickListener(this);

        mAnimator = ValueAnimator.ofFloat(0,1);
        mAnimator.setDuration(3000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });



    }

    public PathPosTanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPathMeasure.getPosTan(mCurrentValue * mPathMeasure.getLength(),mPos,mTan);
        float degree = (float) (Math.atan2(mTan[1],mTan[0]) * 180 / Math.PI);

        canvas.save();
        canvas.translate(400,400);
        canvas.drawPath(mPath,mPaint);
        canvas.drawCircle(mPos[0],mPos[1],10,mPaint);
        canvas.rotate(degree);
        //旋转后的canvas坐标值还是旋转前的位置，所以画出的图像出现会跟着旋转
        canvas.drawLine(0,-200,300,-200,mPaint);
        canvas.restore();
    }

    @Override
    public void onClick(View v) {
        mAnimator.start();
    }
}