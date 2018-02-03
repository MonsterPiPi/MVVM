package example.com.mvvm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.pedant.SweetAlert.SweetAlertDialog;
import example.com.mvvm.base.PowerfulFragment;

public class AboutFragment extends PowerfulFragment {


    private Button gitHubBtn;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        gitHubBtn=(Button)view.findViewById(R.id.gitHub_btn);
    }

    @Override
    protected void initListener() {
        gitHubBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void waitMethod(int flag) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.gitHub_btn:
                Uri uri=Uri.parse("https://github.com/isliqian");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
        }
    }
}
