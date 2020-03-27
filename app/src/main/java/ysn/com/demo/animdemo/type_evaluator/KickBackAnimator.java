package ysn.com.demo.animdemo.type_evaluator;

import android.animation.TypeEvaluator;

/**
 * @Author yangsanning
 * @ClassName KickBackAnimator
 * @Description 回弹动画
 * @Date 2020/2/18
 * @History 2020/2/18 author: description:
 */
public class KickBackAnimator implements TypeEvaluator<Float> {

    private final float s = 1.70158f;
    private float duration;

    public KickBackAnimator(float duration) {
        this.duration = duration;
    }

    @Override
    public Float evaluate(float fraction, Float startValue, Float endValue) {
        float t = duration * fraction;
        float b = startValue;
        float c = endValue - startValue;
        float d = duration;
        return calculate(t, b, c, d);
    }

    private Float calculate(float t, float b, float c, float d) {
        return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
    }
}
