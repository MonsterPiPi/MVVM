package example.com.mvvm;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import com.getbase.floatingactionbutton.FloatingActionButton;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import example.com.mvvm.adapter.MyBaseAdapter;
import example.com.mvvm.base.BaseActivity;
import example.com.mvvm.bean.Food;
import example.com.mvvm.bean.FoodStep;
import example.com.mvvm.databinding.ActivityFoodBinding;

public class FoodActivity extends BaseActivity {

    private Food food;
    private FloatingActionButton floatBtn;
    private ActivityFoodBinding binding;
    private String materialOther="";
    private List<String> some;
    private List<FoodStep> foodSteps;
    private ListView foodStepLv;
    private MyBaseAdapter<FoodStep> adapter;
    private Button stepBtn;
    private ScrollView scrollView;
    private RelativeLayout relativeLayout;
    private Handler hander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 0:
                    adapter.notifyDataSetChanged(); //发送消息通知ListView更新
                    foodStepLv.setAdapter(adapter); // 重新设置ListView的数据适配器
                    break;
                default:
                    //do something
                    break;
            }
        }
    };
    @Override
    protected void initView() {
        //用 DatabindingUtil.setContentView() 来替换掉 setContentView()
        binding= DataBindingUtil.setContentView(this, R.layout.activity_food);
        //接收传递的对象
        food = (Food)getIntent().getSerializableExtra("food");
        floatBtn=(FloatingActionButton)findViewById(R.id.float_btn);
        foodStepLv = (ListView)findViewById(R.id.step_lv);
        stepBtn=(Button)findViewById(R.id.step_btn);
        scrollView=(ScrollView)findViewById(R.id.scrollView);
        relativeLayout=(RelativeLayout)findViewById(R.id.list_step);
        some=new ArrayList<>();
        showProgressDialog("正在加载数据");
        LoadData(food.getUrl());
    }

    private void LoadData(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (foodSteps == null) {
                    foodSteps = new ArrayList<>();
                }
                Document doc = null;
                try {
                    //从一个URL加载一个Document对象。
                    doc = Jsoup.connect(url).get();
                    //选择节点
                    Elements elements = doc.select("div.wrap").select("div.clear").select("div.space_left").select("div.space_box_home").select("div.recipDetail");

                    String picture=elements.select("div.recipe_De_imgBox").select("a.J_photo").select("img").attr("src");

                    Log.i("mytag",   "pic:" + picture);

                    Elements elementsDetail=elements.select("div.mt30").select("ul").select("li");
                    //爬取详情
                    for (Element element:elementsDetail){
                        String one=element.select("span.category_s1").select("a").html();
                        //String two=element.select("span.category_s2").html();
                        //Log.i("mytag",   "one:" + one+"        two=   "+two);
                        materialOther=materialOther+one+"     ";
                    }
                    //爬取使用的厨具
                    Elements elements1=elements.select("div.mt16");
                    for (Element element:elements1){
                        String recipeTip=element.html();
                        some.add(recipeTip);
                        Log.i("mytag",   "one:" +recipeTip);
                    }
                    //爬取做法步骤片段
                    Elements elements2=elements.select("div.recipeStep").select("ul").select("li");
                    for(Element element:elements2){
                        String stepNum=element.select("div.recipeStep_word").select("div.recipeStep_num").html();
                        String stepContextTest=element.select("div.recipeStep_word").html();
                        String []test=stepContextTest.split("</div>");//中文冒号
                        String stepContext= test[1].toString();
                        Log.i("myNum","stepNum:"+stepNum+"stepContext:"+stepContext);
                        foodSteps.add(new FoodStep(stepNum,stepContext));
                    }
                    food.setMaterialOther(materialOther);
                    String []test=some.get(0).split("：");//中文冒号

                    String kitchenware = test[1].toString();
                    food.setKitchenware(kitchenware);
                    binding.setFood(food);

                    if (adapter== null) {
                        adapter = new MyBaseAdapter<>(getBaseContext(), R.layout.step_item, BR.foodStep, foodSteps);
                    }
                    hander.sendEmptyMessage(0); // 下载完成后发送处理消息
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
        stepBtn.setOnClickListener(this);
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
            case R.id.step_btn:
                scrollView.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}
