package example.com.mvvm;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import example.com.mvvm.bean.Food;
import example.com.mvvm.databinding.ActivityFoodBinding;

public class FoodActivity extends AppCompatActivity {

    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //用 DatabindingUtil.setContentView() 来替换掉 setContentView()
        ActivityFoodBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_food);
        //接收传递的数据
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("url");
        }
        binding.setFood(new Food("testFirst", "testLast","22",url));
    }
    /**
     * 监听Back键按下事件,方法1:
     * 注意:
     * super.onBackPressed()会自动调用finish()方法,关闭
     * 当前Activity.
     * 若要屏蔽Back键盘,注释该行代码即可
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }
}
