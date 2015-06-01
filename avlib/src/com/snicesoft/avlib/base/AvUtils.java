package com.snicesoft.avlib.base;

import android.app.Activity;
import android.view.View;

import com.snicesoft.avlib.AVLib;
import com.snicesoft.avlib.rule.IHolder;

class AvUtils {

	public static <H extends IHolder> void initHolder(H holder, Activity av) {
		if (holder != null)
			AVLib.initHolder(holder, av);
		else
			AVLib.initHolder(av);
	}

	public static <H extends IHolder> void initHolder(H holder, View f) {
		if (holder != null)
			AVLib.initHolder(holder, f);
	}

}