package com.snicesoft.avlib.base;

import android.app.Activity;
import android.os.Bundle;

import com.snicesoft.avlib.AvTools;
import com.snicesoft.avlib.widget.IAvData;
import com.snicesoft.avlib.widget.IAvHolder;

public abstract class AvActivity<H extends IAvHolder, D extends IAvData>
		extends Activity {
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
