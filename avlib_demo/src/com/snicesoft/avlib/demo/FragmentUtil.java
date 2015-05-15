package com.snicesoft.avlib.demo;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentUtil {
	public static void openFragment(FragmentActivity activity, int id,
			Fragment fragment) {
		try {
			FragmentTransaction transaction = activity
					.getSupportFragmentManager().beginTransaction();
			List<Fragment> list = activity.getSupportFragmentManager()
					.getFragments();
			if (list == null || list.size() == 0) {
				transaction.add(id, fragment).commit();
			} else {
				if (list.contains(fragment)) {
					for (Fragment f : list) {
						if (fragment == f) {
							transaction.show(f);
						} else {
							transaction.hide(f);
						}
					}
					transaction.commit();
				} else {
					for (Fragment f : list) {
						transaction.hide(f);
					}
					transaction.add(id, fragment).commit();
				}
			}
		} catch (Exception e) {
		}
	}

	public static void replaceFragment(int id, Fragment fragment,
			FragmentManager fragmentManager) {
		FragmentTransaction fTransaction = fragmentManager.beginTransaction();
		fTransaction.replace(id, fragment);
		fTransaction.commit();
	}
}
