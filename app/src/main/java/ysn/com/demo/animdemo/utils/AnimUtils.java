package ysn.com.demo.animdemo.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.view.View;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import ysn.com.demo.animdemo.type_evaluator.KickBackAnimator;

/**
 * @Author yangsanning
 * @ClassName AnimUtils
 * @Description 动画工具类
 * @Date 2020/2/18
 * @History 2020/2/18 author: description:
 */
public class AnimUtils {

    /**
     * 发射view
     */
    public static void launchView(final View view, long delay, final long duration,
                                  final long kickBackDuration, final float... values) {
        Observable.timer(delay, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        view.setVisibility(View.VISIBLE);
                        ofFloat(view, duration, new KickBackAnimator(kickBackDuration), values);
                    }
                });
    }

    /**
     * 回收view
     */
    public static void recoverView(final View view, long delay, final long duration,
                                   final long kickBackDuration, final float... values) {
        Observable.timer(delay, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        view.setVisibility(View.VISIBLE);
                        ofFloat(view, duration, new KickBackAnimator(kickBackDuration), values)
                                .addListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        view.setVisibility(View.INVISIBLE);
                                    }
                                });
                    }
                });
    }

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
}