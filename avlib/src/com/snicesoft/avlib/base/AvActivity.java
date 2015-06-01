package com.snicesoft.avlib.base;

import android.app.Activity;
import android.os.Bundle;

import com.snicesoft.avlib.AVLib;
import com.snicesoft.avlib.pluginmgr.Proxy;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;

/**
 * 
 * @author zhe
 *
 * @param <H>
 * @param <D>
 */
public abstract class AvActivity<H extends IHolder, D extends IData> extends
		Activity implements IAv<H, D> {
	protected D data;
	protected H holder;

	public final void dataBindAll() {
		AVLib.dataBind(data, holder);
	}

	public final void dataBindTo(String fieldName) {
		AVLib.dataBindTo(data, holder, fieldName);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		holder = newHolder();
		data = newData();
		setContentView(LayoutUtils.getLayoutId(getThisClass()));
		AvUtils.initHolder(holder, this);
		holder.initViewParams();
		dataBindAll();
	}

	public Class<?> getThisClass() {
		Class<?> clazz = getClass();
		if (Proxy.PROXY_ACTIVITY.equals(clazz.getName())) {
			clazz = getClass().getSuperclass();
		}
		return clazz;
	}

	public final D getData() {
		return data;
	}

	public final H getHolder() {
		return holder;
	}
}