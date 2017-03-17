package com.wxx.shopping.utils;

/**
 * 作者：Tangren_ on 2017/3/14 18:35.
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */

public class API {

    public static final int PAGESIZE = 10;

    public static final int HOME_TYPE = 0;
    public static final int HOT_TYPE = 1;
    public static final int BANNER_TYPE = 2;

    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/";

    public static final String CAMPAIGN_HOME = BASE_URL + "campaign/recommend";

    public static final String BANNER = BASE_URL + "banner/query?type=1";


    public static final String WARES_HOT = BASE_URL + "wares/hot";
    public static final String WARES_LIST = BASE_URL + "wares/list";
    public static final String WARES_CAMPAIN_LIST = BASE_URL + "wares/campaign/list";
    public static final String WARES_DETAIL = BASE_URL + "wares/detail.html";

    public static final String CATEGORY_LIST = BASE_URL + "category/list";

}
