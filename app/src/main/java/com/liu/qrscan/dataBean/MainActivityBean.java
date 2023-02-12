package com.liu.qrscan.dataBean;

import android.text.TextUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.liu.qrscan.BR;

public class MainActivityBean extends BaseObservable {
    private String qrContent;
    private String qrResult;
    private String preUrl;

    @Bindable
    public String getQrContent() {
        return qrContent;
    }

    public void setQrContent(String qrContent) {
        this.qrContent = qrContent;
        notifyPropertyChanged(BR.qrContent);//使用BaseObservable中的notifyPropertyChanged(BR.id)进行通知
    }
    @Bindable
    public String getQrResult() {
        return qrResult;
    }

    public void setQrResult(String qrResult) {
        this.qrResult = qrResult;
        notifyPropertyChanged(BR.qrResult);//使用BaseObservable中的notifyPropertyChanged(BR.id)进行通知
    }
    @Bindable
    public String getPreUrl() {
        return preUrl;
    }

    public void setPreUrl(String preUrl) {
        this.preUrl = preUrl;
        notifyPropertyChanged(BR.preUrl);//使用BaseObservable中的notifyPropertyChanged(BR.id)进行通知
    }
}
