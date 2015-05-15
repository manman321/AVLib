package com.snicesoft.avlib.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.snicesoft.avlib.AVLib;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;

class AvUtils {
	public static <D extends IData, H extends IHolder> void dataBindAll(
			H holder, D data, Activity activity) {
		if (holder == null && data != null)
			if (activity instanceof IHolder)
				AVLib.dataBind(data, (IHolder) activity);
		if (holder != null && data != null)
			AVLib.dataBind(data, holder);
	}

	public static <D extends IData, H extends IHolder> void dataBindAll(
			H holder, D data, Fragment fragment) {
		if (holder == null && data != null)
			if (fragment instanceof IHolder)
				AVLib.dataBind(data, (IHolder) fragment);
		if (holder != null && data != null)
			AVLib.dataBind(data, holder);
	}

	public static <H extends IHolder> void initHolder(H holder,
			Activity activity) {
		if (holder != null)
			AVLib.initHolder(holder, activity);
		else
			AVLib.initHolder(activity);
	}

	public static <H extends IHolder> void initHolder(Fragment f, H holder) {
		if (holder != null)
			AVLib.initHolder(holder, f);
		else
			AVLib.initHolder(f);
	}

}