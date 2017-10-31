package com.example.alexey.audiostreamer.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.alexey.audiostreamer.R;
import com.example.alexey.audiostreamer.data.entity.local.Station;
import com.example.alexey.audiostreamer.ui.details.DetailsFragment;
import com.example.alexey.audiostreamer.ui.list.ListFragment;

/**
 * Created by alexey
 */

public class NavigationManager {

    public interface NavigationListener {

        void onBackStackChanged();
    }

    private FragmentManager fragmentManager;

    private NavigationListener navigationListener;

    public void init(FragmentManager fragmentManager, NavigationListener navigationListener) {
        this.fragmentManager = fragmentManager;
        this.navigationListener = navigationListener;

        FragmentManager.enableDebugLogging(true);

        fragmentManager.addOnBackStackChangedListener(() -> {

            if (navigationListener != null)
                navigationListener.onBackStackChanged();
        });
    }

    private void open(Fragment fragment) {
        if (fragment == null) return;

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.fragments_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void openAsRoot(Fragment fragment) {
        popAllFragments();
        open(fragment);
    }

    private void popAllFragments() {
        int backStackCount = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {

            int backStackId = fragmentManager.getBackStackEntryAt(i).getId();

            fragmentManager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void openDetailsFragment(Station station) {
        Fragment fragment = DetailsFragment.newInstance(station);
        open(fragment);
    }

    public void openListFragment() {
        Fragment fragment = ListFragment.newInstance();
        openAsRoot(fragment);
    }

    public void navigateBack(AppCompatActivity activity) {
        if (fragmentManager.getBackStackEntryCount() == 1) {

            activity.finish();
            return;
        }

        fragmentManager.popBackStackImmediate();
    }

    public boolean isRootFragmentVisible() {
        return fragmentManager.getBackStackEntryCount() <= 1;
    }

}
