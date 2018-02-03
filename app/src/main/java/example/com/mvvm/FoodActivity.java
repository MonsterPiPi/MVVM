package example.com.mvvm;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import com.getbase.floatingactionbutton.FloatingActionButton;
import android.util.Log;
import android.view.View;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import example.com.mvvm.base.BaseActivity;
import example.com.mvvm.bean.Food;
import example.com.mvvm.databinding.ActivityFoodBinding;

public class FoodActivity extends BaseActivity {

    private String url;

    private FloatingActionButton floatBtn;
    @Override
    protected void initView() {
        //用 DatabindingUtil.setContentView() 来替换掉 setContentView()
        ActivityFoodBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_food);
        //接收传递的数据
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("url");
        }
        floatBtn=(FloatingActionButton)findViewById(R.id.float_btn);
        binding.setFood(new Food("testFirst", "testLast","22",url));
        showProgressDialog("正在加载数据");
        LoadData(url);
    }

    private void LoadData(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc = null;
                try {
                    //从一个URL加载一个Document对象。
                    doc = Jsoup.connect(url).get();
                    //选择节点
                    Elements elements = doc.select("div.wrap").select("div.clear").select("div.space_left").select("div.space_box_home").select("div.recipDetail");

                    String picture=elements.select("div.recipe_De_imgBox").select("a.J_photo").select("img").attr("src");

                    Log.i("mytag",   "pic:" + picture);

                    Elements elementsDetail=elements.select("div.recipeCategory_sub_R").select("ul").select("li");

                    for (Element element:elementsDetail){
                        String one=element.select("span.category_s1").select("a").html();
                        String two=element.select("span.category_s2").html();
                        Log.i("mytag",   "one:" + one+"        two=   "+two);
                    }
                    Thread.sleep(2000);
                    dismissProgressDialog();
                }catch(Exception e) {
                    Log.i("mytag", e.toString());
                }

            }
        }).start();

    }


    @Override
    protected void initData() {

    }

    @Override
    public void initListener() {
        floatBtn.setOnClickListener(this);
    }

    @Override
    protected void waitMethod(int flag) {

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

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.float_btn:
                finish();
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
                break;
            default:
                break;
        }
    }
}
