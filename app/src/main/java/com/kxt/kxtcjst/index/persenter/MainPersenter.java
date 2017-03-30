package com.kxt.kxtcjst.index.persenter;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kxt.kxtcjst.CjstApplicaion;
import com.kxt.kxtcjst.R;
import com.kxt.kxtcjst.common.base.CommunalPresenter;
import com.kxt.kxtcjst.common.coustom.LoadWebView;
import com.kxt.kxtcjst.common.utils.BaseUtils;
import com.kxt.kxtcjst.common.utils.PopupWindowUtils;
import com.kxt.kxtcjst.index.DetailsActivity;
import com.kxt.kxtcjst.index.jsonBean.AdConfigBean;
import com.kxt.kxtcjst.index.jsonBean.UpdateBean;
import com.kxt.kxtcjst.index.view.IMainView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/3/30.
 */

public class MainPersenter extends CommunalPresenter<IMainView>  implements View.OnClickListener  {
    public PopupWindowUtils popupWindowUtils;
    private String adUrl;
    public  MainPersenter(){
        popupWindowUtils=new PopupWindowUtils();
    }



    public void showAdPop(RelativeLayout filterIcon) {
        AdConfigBean adBean = CjstApplicaion.getInstance().getAdConfigBean();
        View adPopView = View.inflate(getContext(), R.layout.pop_ad_view, null);
        RelativeLayout imgRelatve = (RelativeLayout) adPopView.findViewById(R.id.relative_img);
        RelativeLayout webRelatve = (RelativeLayout) adPopView.findViewById(R.id.relative_web);
        ImageView imageClsoe = (ImageView) adPopView.findViewById(R.id.ad_close);
        ImageView imageBg = (ImageView) adPopView.findViewById(R.id.ad_bg);
        WebView webView = (WebView) adPopView.findViewById(R.id.ad_webview);
        ImageView webClose = (ImageView) adPopView.findViewById(R.id.web_close);
        if (adBean.getStatus().equals("1")) {
            if (adBean.getData().getAdvertisement().getType().equals("normal")) {
                adUrl = adBean.getData().getAdvertisement().getUrl();
                if (adUrl.startsWith("http")) {
                    imgRelatve.setVisibility(View.VISIBLE);
                    Glide.with(getContext())
                            .load(adBean.getData().getAdvertisement().getImageUrl())
                            .asBitmap()
                            .into(imageBg);
                    imageClsoe.setOnClickListener(this);
                    imageBg.setOnClickListener(this);
                    popupWindowUtils.dismiss();
                    popupWindowUtils.initPopupWindwo(filterIcon, adPopView,
                            RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT,
                            Color.TRANSPARENT, R.style.popwindow_register_animation, 0,
                            0);
                }

            } else {
                webRelatve.setVisibility(View.VISIBLE);
                if (adBean.getData().getAdvertisement().getUrl() != null && !"".equals(adBean.getData().getAdvertisement().getUrl())
                        && adBean.getData().getAdvertisement().getUrl().startsWith("http")) {
                    webClose.setOnClickListener(this);
                    webView.loadUrl(adBean.getData().getAdvertisement().getUrl());
                    webView.setWebViewClient(new MyWebViewClient());
                    popupWindowUtils.dismiss();
                    popupWindowUtils.initPopupWindwo(filterIcon, adPopView,
                            RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT,
                            Color.TRANSPARENT, R.style.popwindow_register_animation, 0,
                            0);
                }
            }
        }
    }

    public void showUpdatePop(RelativeLayout filterIcon) {
        UpdateBean updateBean = CjstApplicaion.getInstance().getUpdateBean();
        View updatePop = View.inflate(getContext(), R.layout.pop_update_view, null);
        ImageView updateClose = (ImageView) updatePop.findViewById(R.id.update_close);
        TextView textVersion = (TextView) updatePop.findViewById(R.id.update_version);
        TextView textSize = (TextView) updatePop.findViewById(R.id.update_size);
        TextView textContent = (TextView) updatePop.findViewById(R.id.update_content);
        TextView textUpdate = (TextView) updatePop.findViewById(R.id.upate_update);

        String version = updateBean.getData().getVersion();
        boolean isNumOk = true;
        if (version != null && !"".equals(version)) {
            String[] strs = version.split("\\.");
            if (null != strs && strs.length == 3) {
                for (int i = 0; i < strs.length; i++) {
                    Pattern pattern = Pattern.compile("[0-9]*");
                    Matcher isNum = pattern.matcher(strs[i]);
                    if (!isNum.matches()) {
                        isNumOk = false;
                        mView.ShowAdPopView();
                        break;
                    }
                }
                if (isNumOk && !version.equals(BaseUtils.getVersionName(getContext()))) {
                    textVersion.setText("最新版本：" + updateBean.getData().getVersion());
                    textSize.setText("新版本大小：" + updateBean.getData().getSize());
                    String[] contents = updateBean.getData().getContent().split("\\|");
                    String content = "";
                    for (int i = 0; i < contents.length; i++) {
                        content += contents[i] + "\n";
                    }
                    textContent.setText(content);
                    updateClose.setOnClickListener(this);
                    textUpdate.setOnClickListener(this);

                    popupWindowUtils.dismiss();
                    popupWindowUtils.initPopupWindwo(filterIcon, updatePop,
                            RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT,
                            Color.TRANSPARENT, R.style.popwindow_register_animation, 0,
                            0);
                } else {
                    mView.ShowAdPopView();
                }

            } else {
                mView.ShowAdPopView();
            }
        } else {
            mView.ShowAdPopView();
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update_close:
            case R.id.ad_close:
            case R.id.web_close:
                popupWindowUtils.dismiss();
                break;
            case R.id.upate_update:
                popupWindowUtils.dismiss();
                Uri uri = Uri.parse("market://details?id=" + getContext().getPackageName());
//                Uri uri = Uri.parse("market://details?id=com.jyh.kxt");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
                break;
            case R.id.ad_bg:
                popupWindowUtils.dismiss();
                Intent in = new Intent(getContext(), DetailsActivity.class);
                in.putExtra("url", adUrl);
                in.putExtra("from", "main");
                getContext().startActivity(in);
                break;
        }
    }


    public class MyWebViewClient extends WebViewClient {
        private boolean isFirst = true;

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (isFirst) {
                view.loadUrl(url);
                isFirst = false;
            } else {
                popupWindowUtils.dismiss();
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("from", "main");
                getContext().startActivity(intent);
            }

            return true;
        }
    }
}
