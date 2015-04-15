package com.snicesoft.avlib.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.snicesoft.avlib.AvTools;
import com.snicesoft.avlib.widget.IAvData;
import com.snicesoft.avlib.widget.IAvHolder;

public abstract class AvFragmentActivity<H extends IAvHolder, D extends IAvData>
		extends FragmentActivity {
	protected D data;
	protected H holder;

	public abstract void createHolderAndData();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createHolderAndData();
	}

	public void refreshData() {
		AvTools.dataBind(data, holder);
	}
}
