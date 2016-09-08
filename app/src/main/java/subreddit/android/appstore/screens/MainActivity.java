package subreddit.android.appstore.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import subreddit.android.appstore.R;
import subreddit.android.appstore.screens.list.AppListFragment;
import subreddit.android.appstore.screens.navigation.CategoryFilter;
import subreddit.android.appstore.screens.navigation.NavigationFragment;
import subreddit.android.appstore.util.ui.BaseActivity;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.main_drawer) DrawerLayout drawerLayout;
    @BindView(R.id.applist_toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_48px);
        toolbar.setNavigationOnClickListener(this);

        Fragment contentFragment = getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (contentFragment == null) {
            // TODO if we need to create a new list fragment we need to restore it with the correct categories
            contentFragment = AppListFragment.newInstance(new CategoryFilter());
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.contentFrame, contentFragment).commit();

        Fragment navigationFragment = getSupportFragmentManager().findFragmentById(R.id.navigationFrame);
        if (navigationFragment == null) navigationFragment = NavigationFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.navigationFrame, navigationFragment).commit();
    }

    @Override
    public void onClick(View view) {
        drawerLayout.openDrawer(GravityCompat.START);
    }
}