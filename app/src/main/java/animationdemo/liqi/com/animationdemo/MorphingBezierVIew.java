package animationdemo.liqi.com.animationdemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by liqi on 2017/4/3.
 */

public class MorphingBezierVIew extends View implements View.OnClickListener{
    private float mStartPointX;
    private float mStartPointY;

    private float mEndPointX;
    private float mEndPointY;

    private float mFlagPointOneX;
    private float mFlagPointOneY;
    private float mFlagPointTwoX;
    private float mFlagPointTwoY;

    private Path mPath;

    private Paint mPaintBezier;
    private Paint mPaintFlag;
    private Paint mPaintFlagText;

    private boolean isSecondPoint = false;

    private ValueAnimator mValueAnimator;

    public MorphingBezierVIew(Context context) {
        super(context);
    }

    public MorphingBezierVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaintBezier = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBezier.setStrokeWidth(8);
        mPaintBezier.setStyle(Paint.Style.STROKE);

        mPaintFlag = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintFlag.setStrokeWidth(3);
        mPaintFlag.setStyle(Paint.Style.STROKE);

        mPaintFlagText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintFlagText.setStyle(Paint.Style.STROKE);
        mPaintFlagText.setTextSize(20);
    }

    public MorphingBezierVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStartPointX = w / 4;
        mStartPointY = h / 2 - 200;

        mEndPointX = w * 3 / 4;
        mEndPointY = h / 2 - 200;

        mFlagPointOneX = mStartPointX;
        mFlagPointOneY = mStartPointY;
        mFlagPointTwoX = mEndPointX;
        mFlagPointTwoY = mEndPointY;

        mPath = new Path();

        mValueAnimator = ValueAnimator.ofFloat(mStartPointY,h);
        mValueAnimator.setInterpolator(new BounceInterpolator());
        mValueAnimator.setDuration(3000);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mFlagPointOneY = (float) animation.getAnimatedValue();
                mFlagPointTwoY = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(mStartPointX,mStartPointY);
//        mPath.quadTo(mFlagPointOneX,mFlagPointOneY,mEndPointX,mEndPointY);
        mPath.cubicTo(mFlagPointOneX,mFlagPointOneY,mFlagPointTwoX,mFlagPointTwoY,mEndPointX,mEndPointY);

        canvas.drawPoint(mStartPointX,mStartPointY,mPaintFlag);
        canvas.drawText("起点",mStartPointX,mStartPointY,mPaintFlagText);
        canvas.drawPoint(mEndPointX,mEndPointY,mPaintFlag);
        canvas.drawText("终点",mEndPointX,mEndPointY,mPaintFlagText);
        canvas.drawPoint(mFlagPointOneX,mFlagPointOneY,mPaintFlag);
        canvas.drawText("控制点1",mFlagPointOneX,mFlagPointOneY,mPaintFlagText);
        canvas.drawPoint(mFlagPointTwoX,mFlagPointTwoY,mPaintFlag);
        canvas.drawText("控制点2",mFlagPointTwoX,mFlagPointTwoY,mPaintFlagText);
        canvas.drawLine(mStartPointX,mStartPointY,mFlagPointOneX,mFlagPointOneY,mPaintFlag);
        canvas.drawLine(mFlagPointOneX,mFlagPointOneY,mFlagPointTwoX,mFlagPointTwoY,mPaintFlag);
        canvas.drawLine(mFlagPointTwoX,mFlagPointTwoY,mEndPointX,mEndPointY,mPaintFlag);

        canvas.drawPath(mPath,mPaintBezier);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction() & MotionEvent.ACTION_MASK){
//            case  MotionEvent.ACTION_POINTER_DOWN:
//                isSecondPoint = true;
//                break;
//            case MotionEvent.ACTION_POINTER_UP:
//                isSecondPoint = false;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                mFlagPointOneX = event.getX(0);
//                mFlagPointOneY = event.getY(0);
//                if(isSecondPoint){
//                    mFlagPointTwoX = event.getX(1);
//                    mFlagPointTwoY = event.getY(1);
//                }
//                invalidate();
//                break;
//        }
//        return true;
//    }

    @Override
    public void onClick(View v) {
        mValueAnimator.start();
    }
}
