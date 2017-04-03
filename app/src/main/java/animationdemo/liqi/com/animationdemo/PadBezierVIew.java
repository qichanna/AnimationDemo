package animationdemo.liqi.com.animationdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by liqi on 2017/4/3.
 */

public class PadBezierVIew extends View {

    private Path mPath;
    private Paint mPaint;
    private float mX;
    private float mY;

    public PadBezierVIew(Context context) {
        super(context);
    }

    public PadBezierVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(8);

        mPath = new Path();
    }

    public PadBezierVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mX = event.getX();
                mY = event.getY();
                mPath.moveTo(mX, mY);
                break;
            case MotionEvent.ACTION_MOVE:
                float x1 = event.getX();
                float y1 = event.getY();
                float cx = (x1 + mX) / 2;
                float cy = (y1 + mY) / 2;
                mPath.quadTo(mX, mY, cx, cy);
//                mPath.lineTo(x1,y1);
                mX = x1;
                mY = y1;
        }
        invalidate();
        return true;
    }
}
