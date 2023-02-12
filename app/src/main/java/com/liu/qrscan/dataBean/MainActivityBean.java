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
        notifyPropertyChanged(BR.qrContent);
    }
    @Bindable
    public String getQrResult() {
        return qrResult;
    }

    public void setQrResult(String qrResult) {
        this.qrResult = qrResult;
        notifyPropertyChanged(BR.qrResult);
    }
    @Bindable
    public String getPreUrl() {
        return preUrl;
    }

    public void setPreUrl(String preUrl) {
        this.preUrl = preUrl;
        notifyPropertyChanged(BR.preUrl);
    }
}
