package com.example.alexey.audiostreamer.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.alexey.audiostreamer.R;
import com.example.alexey.audiostreamer.data.Track;
import com.example.alexey.audiostreamer.ui.list.ListFragment;

import java.lang.ref.SoftReference;
import java.util.Stack;

/**
 * Created by alexey
 */

public class NavigationManager {

    public interface NavigationListener {

        void onBackStackChanged();
    }

    private FragmentManager fragmentManager;

    private NavigationListener navigationListener;
    // Stack that contains fullscreen Fragments only, unlike
    // fragmentManager.getFragments() that returns all added fragments.
    private Stack<SoftReference<Fragment>> fullScreenFragmentsStack;

    public void init(FragmentManager fragmentManager, NavigationListener navigationListener) {
        this.fragmentManager = fragmentManager;
        this.navigationListener = navigationListener;
        this.fullScreenFragmentsStack = new Stack<>();

        FragmentManager.enableDebugLogging(true);

        fragmentManager.addOnBackStackChangedListener(() -> {

            if (navigationListener != null)
                navigationListener.onBackStackChanged();
        });
    }

    private void open(Fragment fragment) {
        if (fragment == null) return;

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (!fullScreenFragmentsStack.isEmpty() && fullScreenFragmentsStack.peek() != null)
            transaction.remove(fullScreenFragmentsStack.peek().get());

        transaction.add(R.id.fragments_container, fragment)
                .addToBackStack(fragment.toString())
                .commit();

        fullScreenFragmentsStack.push(new SoftReference<>(fragment));
        Log.d("fullScreenStack", fullScreenFragmentsStack.size() + "");
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

/*    public void openDetailsFragment(Track track) {
        Fragment fragment = DetailFragment.newInstance(track);
        open(fragment);
    }
    */

    public void openListFragment() {
        Fragment fragment = ListFragment.newInstance();
        openAsRoot(fragment);
    }

    public void navigateBack(AppCompatActivity activity) {
        if (fragmentManager.getBackStackEntryCount() == 0) {

            activity.finish();
            return;
        }

        fragmentManager.popBackStackImmediate();
        fullScreenFragmentsStack.pop();
    }

    public boolean isRootFragmentVisible() {
        return fragmentManager.getBackStackEntryCount() <= 1;
    }

}
