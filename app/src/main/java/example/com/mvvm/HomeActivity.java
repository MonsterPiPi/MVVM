package example.com.mvvm;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //让布局向上移来显示软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorWhite));
            //设置状态栏文字颜色及图标为深色
            getWindow().getDecorView().setSystemUiVisibility(  View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("Food", ContextCompat.getColor(this, R.color.no1), R.drawable.food);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("Map", ContextCompat.getColor(this, R.color.no2), R.drawable.map);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("Photo", ContextCompat.getColor(this, R.color.no3), R.drawable.picture);
        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
                ("About", ContextCompat.getColor(this, R.color.no4), R.drawable.version);
        bottomNavigationView.addTab(bottomNavigationItem);
        bottomNavigationView.addTab(bottomNavigationItem1);
        bottomNavigationView.addTab(bottomNavigationItem2);
        bottomNavigationView.addTab(bottomNavigationItem3);

        bottomNavigationView.isColoredBackground(false);
        bottomNavigationView.setItemActiveColorWithoutColoredBackground(R.color.white);
    }
}
