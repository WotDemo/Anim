package ysn.com.demo.animdemo.page;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ysn.com.demo.animdemo.R;
import ysn.com.demo.animdemo.utils.AnimUtils;

/**
 * @Author yangsanning
 * @ClassName LaunchAnimActivity
 * @Description 一句话概括作用
 * @Date 2020/3/27
 * @History 2020/3/27 author: description:
 */
public class LaunchAnimActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewGroup parent;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("发射动画演示");
        setContentView(R.layout.activity_launch_anim);

        parent = findViewById(R.id.activity_launch_anim_parent);

        startButton = findViewById(R.id.activity_launch_anim_start);
        startButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int childCount = parent.getChildCount();
        if (view.getTag() == null) {
            view.setTag(Boolean.TRUE);
            startButton.setText("收回");
            for (int i = 0; i < childCount; i++) {
                AnimUtils.launchView(parent.getChildAt(i), i * 50,
                        300, 150, 600, 0);
            }
        } else {
            view.setTag(null);
            startButton.setText("发射");
            for (int i = 0; i < childCount; i++) {
                AnimUtils.recoverView(parent.getChildAt(i), (childCount - i - 1) * 30,
                        200, 100, 0, 600);
            }
        }
    }
}
