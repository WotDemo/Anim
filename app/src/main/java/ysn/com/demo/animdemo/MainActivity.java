package ysn.com.demo.animdemo;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private   ViewGroup parent;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.main_activity_parent);

        button = findViewById(R.id.main_activity_btn);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int childCount = parent.getChildCount();
        if (view.getTag() == null) {
            view.setTag(Boolean.TRUE);
            button.setText("收起");
            for (int i = 0; i < childCount; i++) {
                AnimUtils.launchView(parent.getChildAt(i),i * 50,300,600,0);
            }
        } else {
            view.setTag(null);
            button.setText("展开");
            for (int i = 0; i < childCount; i++) {
                AnimUtils.recoverView(parent.getChildAt(i), (childCount - i - 1) * 30, 200, 0, 600);
            }
        }
    }
}
