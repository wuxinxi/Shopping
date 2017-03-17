//package com.wxx.shopping.moudle;
//
//import android.app.Fragment;
//import android.app.FragmentTransaction;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.LinearLayout;
//
//import com.ashokvarma.bottomnavigation.BottomNavigationBar;
//import com.ashokvarma.bottomnavigation.BottomNavigationItem;
//import com.wxx.shopping.R;
//import com.wxx.shopping.moudle.cart.CartFragment;
//import com.wxx.shopping.moudle.home.HomeFragment;
//import com.wxx.shopping.moudle.hot.HotFragment;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class MainActivity2 extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
//
//
//    @BindView(R.id.content)
//    LinearLayout content;
//    @BindView(R.id.navigationBar)
//    BottomNavigationBar navigationBar;
//
//    private HomeFragment homeFragment;
//    private HotFragment hotFragment;
//    private CartFragment cartFragment;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//        ButterKnife.bind(this);
//        initView();
//    }
//
//    private void initView() {
//        navigationBar.setAutoHideEnabled(false);
//        navigationBar.setMode(BottomNavigationBar.MODE_FIXED);
//        navigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
//
//        navigationBar.setBarBackgroundColor(R.color.white);
//        navigationBar.setInActiveColor(R.color.bottom_nav_normal);
//        navigationBar.setActiveColor(R.color.colorPrimary);
//
//        BottomNavigationItem homeItem = new BottomNavigationItem(R.mipmap.icon_home, "首页");
//        BottomNavigationItem hotItem = new BottomNavigationItem(R.mipmap.icon_hot, "热卖");
//        BottomNavigationItem typeItem = new BottomNavigationItem(R.mipmap.icon_discover, "分类");
//        BottomNavigationItem cartItem = new BottomNavigationItem(R.mipmap.icon_cart, "购物车");
//        BottomNavigationItem meItem = new BottomNavigationItem(R.mipmap.icon_user, "我的");
//
//        navigationBar.addItem(homeItem).addItem(hotItem).addItem(typeItem).addItem(cartItem).addItem(meItem);
//        navigationBar.setFirstSelectedPosition(0);
//        navigationBar.initialise();
//        navigationBar.setTabSelectedListener(this);
//
//        setDefault();
//    }
//
//    private void setDefault() {
//        if (homeFragment == null) {
//            homeFragment = HomeFragment.newInstance();
//        }
//        addFrag(homeFragment);
//        getFragmentManager().beginTransaction().show(homeFragment).commit();
//    }
//
//    private void addFrag(Fragment frg) {
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        if (frg != null && !frg.isAdded()) {
//            transaction.add(R.id.content, frg);
//        }
//        transaction.commit();
//    }
//
//    @Override
//    public void onTabSelected(int position) {
//        hideAllFrag();
//        switch (position) {
//            case 0:
//                if (homeFragment == null)
//                    homeFragment = HomeFragment.newInstance();
//
//                addFrag(homeFragment);
//                add(homeFragment);
//                break;
//            case 1:
//                if (hotFragment == null) {
//                    hotFragment = HotFragment.newInstance();
//                }
//
//                addFrag(hotFragment);
//                add(hotFragment);
//                break;
//            case 2:
//
//                break;
//            case 3:
//                if (cartFragment == null)
//                    cartFragment = CartFragment.newInstance();
//                addFrag(cartFragment);
//                add(cartFragment);
//                break;
//            case 4:
//                break;
//        }
//    }
//
//    @Override
//    public void onTabUnselected(int position) {
//
//    }
//
//    @Override
//    public void onTabReselected(int position) {
//
//    }
//
//
//    private void add(Fragment frag) {
//        getFragmentManager().beginTransaction().show(frag).commit();
//    }
//
//    private void hideAllFrag() {
//        hideFrag(homeFragment);
//        hideFrag(hotFragment);
//        hideFrag(cartFragment);
//    }
//
//    private void hideFrag(Fragment frg) {
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        //如果Fragment不为空并且已经被添加
//        if (frg != null && frg.isAdded()) {
//            transaction.hide(frg);
//        }
//        transaction.commit();
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//    }
//}
