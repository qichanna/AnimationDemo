package animationdemo.liqi.com.animationdemo;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by liqi on 2017/4/16.
 */

public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF mFlagPoint;

    public BezierEvaluator(PointF mFlagPoint) {
        this.mFlagPoint = mFlagPoint;
    }

    @Override
    public PointF evaluate(float v, PointF startValue, PointF endValue) {
        return BezierUtil.CalculateBezierPointForQuadratic(v,startValue,mFlagPoint,endValue);
    }
}
