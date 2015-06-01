package com.snicesoft.avlib.rule;

import android.content.Context;
import android.view.View;

/**
 * @author zhu zhe
 * @since 2015年4月15日 上午9:54:17
 * @version V1.0
 * @param <T>
 */
public abstract class IHolder implements IRule, View.OnClickListener {
	public abstract void initViewParams();

	private Context context;

	@Override
	public Context getContext() {
		return context;
	}

	private Object tag;

	public void setTag(Object tag) {
		this.tag = tag;
	}

	public Object getTag() {
		return tag;
	}

	public IHolder(Context context) {
		this.context = context;
	}

	@Override
	public void onClick(View v) {

	}
}