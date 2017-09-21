package www.bawei.com.mymvp.view.fragment;

import android.content.Context;

/**
 * Created by wmm on 2017/9/12 0012.
 */

public   class Shoucang {
    private String drugName;
    private int drugFlag;
    private String shoucangId;
    private String Drugmoney;



    public Shoucang() {
    }
    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public int getDrugFlag() {
        return drugFlag;
    }

    public void setDrugFlag(int drugFlag) {
        this.drugFlag = drugFlag;
    }

    public String getShoucangId() {
        return shoucangId;
    }

    public void setShoucangId(String shoucangId) {
        this.shoucangId = shoucangId;
    }

    public String getDrugmoney() {
        return Drugmoney;
    }

    public void setDrugmoney(String drugmoney) {
        Drugmoney = drugmoney;
    }
}
