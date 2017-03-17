package com.wxx.shopping.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * 作者：Tangren_ on 2017/3/16 14:45.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Picasso.with(context).load((String) path).fit().into(imageView);
    }
}
