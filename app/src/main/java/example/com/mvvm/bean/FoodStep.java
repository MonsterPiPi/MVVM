package example.com.mvvm.bean;

import android.databinding.BaseObservable;

import java.io.Serializable;

/**
 * Created by DELL on 2018/2/7.
 */

/**
 * 美食步骤实体类
 */
public class FoodStep extends BaseObservable implements Serializable {
    /**
     * 第几步
     */
    private String stepNum;
    /**
     * 步骤描述
     */
    private String stepContext;
    /**
     * 步骤图片
     */
    private String image;

    public FoodStep(String stepNum,String stepContext){
        this.stepNum=stepNum;
        this.stepContext=stepContext;
    }
    public String getStepNum() {
        return stepNum;
    }

    public void setStepNum(String stepNum) {
        this.stepNum = stepNum;
    }

    public String getStepContext() {
        return stepContext;
    }

    public void setStepContext(String stepContext) {
        this.stepContext = stepContext;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
