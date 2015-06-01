package com.snicesoft.avlib.rule;

import android.content.Context;

/**
 * @author zhu zhe
 * @since 2015年4月15日 上午9:54:17
 * @version V1.0
 */
public abstract class IData implements IRule {
	private Context context;

	@Override
	public Context getContext() {
		return context;
	}

	public IData(Context context) {
		this.context = context;
	}
}