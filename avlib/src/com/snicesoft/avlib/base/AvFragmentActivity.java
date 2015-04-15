package com.snicesoft.avlib.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.snicesoft.avlib.AvTools;
import com.snicesoft.avlib.widget.IAvData;
import com.snicesoft.avlib.widget.IAvHolder;

/**
 * FragmentActivity基类
 * 
 * @author zhe
 *
 * @param <H>
 * @param <D>
 */
public abstract class AvFragmentActivity<H extends IAvHolder, D extends IAvData>
		extends FragmentActivity {
	protected D data;
	protected H holder;

	public abstract void createHolderAndData();

	public void dataBind() {
		if (holder == null && data != null)
			AvTools.dataBind(data, this);
		if (holder != null && data != null)
			AvTools.dataBind(data, holder);
	}

	public abstract void loadContentView();

	public abstract void onAv();

	@Override
	protected final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createHolderAndData();
		loadContentView();
		if (holder != null)
			AvTools.initHolder(holder, this);
		else
			AvTools.initHolder(this);
		onAv();
	}
}
