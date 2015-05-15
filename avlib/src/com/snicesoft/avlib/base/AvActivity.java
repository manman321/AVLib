package com.snicesoft.avlib.base;

import android.app.Activity;
import android.os.Bundle;

import com.snicesoft.avlib.AVLib;
import com.snicesoft.avlib.annotation.Layout;
import com.snicesoft.avlib.pluginmgr.Proxy;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;

/**
 * 普通Activity基类
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
		AvUtils.dataBindAll(holder, data, this);
	}

	public final void dataBindTo(String fieldName) {
		if (data != null && holder != null)
			AVLib.dataBindTo(data, holder, fieldName);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		holder = newHolder();
		data = newData();
		Layout layout = getThisClass().getAnnotation(Layout.class);
		if (layout != null && layout.value() != 0) {
			setContentView(layout.value());
		} else {
			System.err.println("@Layout not find.");
		}
		AvUtils.initHolder(holder, this);
		dataBindAll();
	}

	public Class<?> getThisClass() {
		Class<?> clazz = getClass();
		if (Proxy.PROXY_ACTIVITY.equals(clazz.getName())) {
			clazz = getClass().getSuperclass();
		}
		return clazz;
	}
}