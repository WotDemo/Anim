package ysn.com.demo.animdemo;

import android.animation.Animator;

/**
 * @Author yangsanning
 * @ClassName AnimListener
 * @Description 一句话概括作用
 * @Date 2020/2/18
 * @History 2020/2/18 author: description:
 */
public class AnimListener implements Animator.AnimatorListener {

    private OnAnimEndListener onAnimEndListener;

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (onAnimEndListener != null) {
            onAnimEndListener.onAnimEnd(animation);
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    public AnimListener setOnAnimEndListener(OnAnimEndListener onAnimEndListener) {
        this.onAnimEndListener = onAnimEndListener;
        return this;
    }

    public interface OnAnimEndListener {

        void onAnimEnd(Animator animation);
    }
}
