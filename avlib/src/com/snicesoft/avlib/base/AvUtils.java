package com.snicesoft.avlib.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

import com.snicesoft.avlib.AvTools;
import com.snicesoft.avlib.widget.IAvData;
import com.snicesoft.avlib.widget.IAvHolder;

public class AvUtils {
	protected static <D extends IAvData, H extends IAvHolder> void dataBindAll(
			H holder, D data, Activity activity) {
		if (holder == null && data != null)
			AvTools.dataBind(data, activity);
		if (holder != null && data != null)
			AvTools.dataBind(data, holder);
	}

	protected static <D extends IAvData, H extends IAvHolder> void dataBindAll(
			H holder, D data, Fragment fragment) {
		if (holder == null && data != null)
			AvTools.dataBind(data, fragment);
		if (holder != null && data != null)
			AvTools.dataBind(data, holder);
	}

	protected static <H extends IAvHolder> void initHolder(H holder,
			Activity activity) {
		if (holder != null)
			AvTools.initHolder(holder, activity);
		else
			AvTools.initHolder(activity);
	}

	protected static <H extends IAvHolder> void initHolder(Fragment f,
			H holder, View v) {
		if (holder != null)
			AvTools.initHolder(holder, v);
		else
			AvTools.initHolder(f, v);
	}

}
