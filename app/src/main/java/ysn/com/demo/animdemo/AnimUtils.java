package ysn.com.demo.animdemo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.view.View;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @Author yangsanning
 * @ClassName AnimUtils
 * @Description 动画工具类
 * @Date 2020/2/18
 * @History 2020/2/18 author: description:
 */
public class AnimUtils {

    public static ValueAnimator ofFloat(final View view, long duration, float... values) {
        return ofFloat(view, duration, null, values);
    }

    public static ValueAnimator ofFloat(final View view, long duration, TypeEvaluator evaluator, float... values) {
        ValueAnimator animator = ObjectAnimator.ofFloat(view, "translationY", values);
        animator.setDuration(duration);
        animator.setEvaluator(evaluator);
        animator.start();
        return animator;
    }

    /**
     * 发射view
     */
    public static void launchView(final View view, long delay, final long duration, final float... values) {
        Observable.timer(delay, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<Long>() {
                @Override
                public void call(Long aLong) {
                    view.setVisibility(View.VISIBLE);
                    ofFloat(view, duration, new KickBackAnimator(150), values);
                }
            });
    }

    /**
     * 回收view
     */
    public static void recoverView(final View view, long delay, final long duration, final float... values) {
        Observable.timer(delay, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<Long>() {
                @Override
                public void call(Long aLong) {
                    view.setVisibility(View.VISIBLE);
                    ofFloat(view, duration, new KickBackAnimator(100), values)
                        .addListener(new AnimListener().setOnAnimEndListener(new AnimListener.OnAnimEndListener() {
                            @Override
                            public void onAnimEnd(Animator animation) {
                                view.setVisibility(View.INVISIBLE);
                            }
                        }));
                }
            });
    }
}