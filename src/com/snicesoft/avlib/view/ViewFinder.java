package com.snicesoft.avlib.view;

import android.app.Activity;
import android.view.View;

/**
 * @author zhu zhe
 * @since 2015年4月15日 上午10:48:05
 * @version V1.0 参考xUtils
 */
public class ViewFinder {
	private View parent;

	public ViewFinder(View view) {
		this.parent = view;
	}

	public ViewFinder(Activity activity) {
		this.parent = activity.getWindow().getDecorView();
	}

	public View findViewById(int vId) {
		return parent.findViewById(vId);
	}
}
