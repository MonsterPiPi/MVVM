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

import java.util.ArrayList;
import java.util.List;

import example.com.mvvm.base.BaseActivity;
import example.com.mvvm.bean.Food;
import example.com.mvvm.databinding.ActivityFoodBinding;

public class FoodActivity extends BaseActivity {

    private Food food;
    private FloatingActionButton floatBtn;
    private ActivityFoodBinding binding;
    private String materialOther="";
    private List<String> some;
    @Override
    protected void initView() {
        //用 DatabindingUtil.setContentView() 来替换掉 setContentView()
        binding= DataBindingUtil.setContentView(this, R.layout.activity_food);
        //接收传递的对象
        food = (Food)getIntent().getSerializableExtra("food");
        floatBtn=(FloatingActionButton)findViewById(R.id.float_btn);
        some=new ArrayList<>();
        showProgressDialog("正在加载数据");
        LoadData(food.getUrl());
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

                    Elements elementsDetail=elements.select("div.mt30").select("ul").select("li");

                    for (Element element:elementsDetail){
                        String one=element.select("span.category_s1").select("a").html();
                        //String two=element.select("span.category_s2").html();
                        //Log.i("mytag",   "one:" + one+"        two=   "+two);
                        materialOther=materialOther+one+"     ";
                    }
                    Elements elements1=elements.select("div.mt16");
                    for (Element element:elements1){
                        String recipeTip=element.html();
                        some.add(recipeTip);
                        Log.i("mytag",   "one:" +recipeTip);
                    }
                    food.setMaterialOther(materialOther);
                    String []test=some.get(0).split("：");//中文冒号
                    StringBuilder sb = new StringBuilder();
                    String kitchenware = sb.append(test[1]).toString();
                    food.setKitchenware(kitchenware);
                    binding.setFood(food);
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
