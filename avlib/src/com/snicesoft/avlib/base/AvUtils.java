package com.snicesoft.avlib.base;

import android.app.Activity;
import android.view.View;

import com.snicesoft.avlib.AVLib;
import com.snicesoft.avlib.rule.IHolder;
import com.snicesoft.avlib.view.ViewFinder;

class AvUtils {

	static <H extends IHolder> void initHolder(H holder, Activity av) {
		if (holder != null && av != null) {
			AVLib.initHolder(holder, new ViewFinder(av));
			holder.initViewParams();
		}
	}

	static <H extends IHolder> void initHolder(H holder, View f) {
		if (holder != null && f != null) {
			AVLib.initHolder(holder, new ViewFinder(f));
			holder.initViewParams();
		}
	}

}