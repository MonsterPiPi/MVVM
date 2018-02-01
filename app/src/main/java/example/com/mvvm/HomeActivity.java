package example.com.mvvm;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;

import example.com.mvvm.adapter.FragmentAdapter;

public class HomeActivity extends AppCompatActivity {
    private TextView textView;
    private BottomNavigationView bottomNavigationView;
    private  ViewPager pager;
    private int[] colors={R.color.no1,R.color.no2,R.color.no3,R.color.no4};
    //
    private int[] imageResources={R.drawable.food,R.drawable.map,R.drawable.picture,R.drawable.version};
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
        textView = (TextView) findViewById(R.id.tv);
        pager=(ViewPager)findViewById(R.id.pager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        initViews();
        initListener();


    }

    private void initListener() {
        //pager切换监听

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //导航选择监听
        bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                pager.setCurrentItem(index);
            }
        });
    }

    private void initViews() {
        if (bottomNavigationView != null) {
            bottomNavigationView.isWithText(true);
            // bottomNavigationView.activateTabletMode();
            bottomNavigationView.isColoredBackground(true);
        }



        bottomNavigationView.isColoredBackground(false);
        bottomNavigationView.setItemActiveColorWithoutColoredBackground(R.color.white);
        pager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        ContextCompat.getColor(getBaseContext(), R.color.no1);
        bottomNavigationView.setUpWithViewPager(pager , colors , imageResources);
    }
}
