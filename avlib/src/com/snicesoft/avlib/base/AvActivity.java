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
		extends Activity {
	protected D data;
	protected H holder;

	public abstract void createHolderAndData();

	public final void dataBind() {
		if (holder == null && data != null)
			AvTools.dataBind(data, this);
		if (holder != null && data != null)
			AvTools.dataBind(data, holder);
	}

	public void onCreate() {
		dataBind();
	}

	@Override
	protected final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createHolderAndData();
		Layout layout = getClass().getAnnotation(Layout.class);
		if (layout != null && layout.value() != 0) {
			setContentView(layout.value());
		} else {
			System.err.println("@Layout not find.");
		}
		if (holder != null)
			AvTools.initHolder(holder, this);
		else
			AvTools.initHolder(this);
		onCreate();
	}
}
