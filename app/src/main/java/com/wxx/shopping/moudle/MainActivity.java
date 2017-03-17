package com.wxx.shopping.moudle;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.wxx.shopping.R;
import com.wxx.shopping.base.BaseFragment;
import com.wxx.shopping.moudle.cart.CartFragment;
import com.wxx.shopping.moudle.home.HomeFragment;
import com.wxx.shopping.moudle.hot.HotFragment;
import com.wxx.shopping.moudle.mine.MineFragment;
import com.wxx.shopping.moudle.type.TypeFragment;
import com.wxx.shopping.widget.BottomBar;
import com.wxx.shopping.widget.BottomBarTab;
import com.yanzhenjie.nohttp.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation.helper.FragmentLifecycleCallbacks;

public class MainActivity extends SupportActivity implements BaseFragment.OnBackToFirstListener {


    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    private HomeFragment homeFragment;
    private HotFragment hotFragment;
    private CartFragment cartFragment;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIVE = 4;

    private SupportFragment[] fragments = new SupportFragment[5];

    private int PREVIOUS;//上一个postion
    private int PREVIOUS_TEMP;//临时存储

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {

            fragments[FIRST] = HomeFragment.newInstance();
            fragments[SECOND] = HotFragment.newInstance();
            fragments[THIRD] = TypeFragment.newInstance();
            fragments[FOURTH] = CartFragment.newInstance();
            fragments[FIVE] = MineFragment.newInstance();

            loadMultipleRootFragment(R.id.content, FIRST,
                    fragments[FIRST],
                    fragments[SECOND],
                    fragments[THIRD],
                    fragments[FOURTH]);
        } else {
            fragments[FIRST] = findFragment(HomeFragment.class);
            fragments[SECOND] = findFragment(HotFragment.class);
            fragments[THIRD] = findFragment(TypeFragment.class);
            fragments[FOURTH] = findFragment(CartFragment.class);
            fragments[FIVE] = findFragment(MineFragment.class);
        }

        initView();

        registerFragmentLifecycleCallbacks(new FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentSupportInvisible(SupportFragment fragment) {
                super.onFragmentSupportInvisible(fragment);
                Logger.d("MainActivity--->onFragmentSupportViable--->" + fragment.getClass().getSimpleName());
            }
        });
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return super.onCreateFragmentAnimator();
    }

    private void initView() {
        mBottomBar
                .addItem(new BottomBarTab(this, R.mipmap.icon_home, "首页"))
                .addItem(new BottomBarTab(this, R.mipmap.icon_hot, "热买"))
                .addItem(new BottomBarTab(this, R.mipmap.icon_discover, "分类"))
                .addItem(new BottomBarTab(this, R.mipmap.icon_cart, "购物车"))
                .addItem(new BottomBarTab(this, R.mipmap.icon_user, "我的"));
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(fragments[position], fragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                SupportFragment currentFragment = fragments[position];
                int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();

                // 如果不在该类别Fragment的主页,则回到主页;
                if (count > 1) {
                    if (currentFragment instanceof HomeFragment) {
                        currentFragment.popToChild(HomeFragment.class, false);
                    } else if (currentFragment instanceof HotFragment) {
                        currentFragment.popToChild(HomeFragment.class, false);
                    } else if (currentFragment instanceof TypeFragment) {
                        currentFragment.popToChild(HomeFragment.class, false);
                    } else if (currentFragment instanceof CartFragment) {
                        currentFragment.popToChild(HomeFragment.class, false);
                    }
                    return;
                }

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackToFirstFragment() {
        mBottomBar.setCurrentItem(0);
    }
}
