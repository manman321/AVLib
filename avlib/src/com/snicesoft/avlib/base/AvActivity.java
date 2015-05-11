package com.snicesoft.avlib.base;

import android.app.Activity;
import android.os.Bundle;

import com.snicesoft.avlib.AvTools;
import com.snicesoft.avlib.annotation.Layout;
import com.snicesoft.avlib.widget.IAvData;
import com.snicesoft.avlib.widget.IAvHolder;

/**
 * 普通Activity基类
 * 
 * @author zhe
 *
 * @param <H>
 * @param <D>
 */
public abstract class AvActivity<H extends IAvHolder, D extends IAvData>
		extends Activity implements IAv<H, D> {
	protected D data;
	protected H holder;

	public final void dataBindAll() {
		AvUtils.dataBindAll(holder, data, this);
	}

	public final void dataBindByName(String... fieldNames) {
		if (data != null && holder != null)
			AvTools.dataBindByFieldNames(data, holder, fieldNames);
	}

	public void onCreate() {
		dataBindAll();
	}

	@Override
	protected final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		holder = newHolder();
		data = newData();
		Layout layout = getClass().getAnnotation(Layout.class);
		if (layout != null && layout.value() != 0) {
			setContentView(layout.value());
		} else {
			System.err.println("@Layout not find.");
		}
		AvUtils.initHolder(holder, this);
		onCreate();
	}
}