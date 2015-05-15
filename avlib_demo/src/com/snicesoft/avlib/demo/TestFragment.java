package com.snicesoft.avlib.demo;

import android.support.v4.app.FragmentActivity;

import com.snicesoft.avlib.annotation.Layout;
import com.snicesoft.avlib.base.AvFragment;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;

@Layout(R.layout.fragment_test)
public class TestFragment extends AvFragment<IHolder, IData, FragmentActivity> {

	@Override
	public IData newData() {
		return null;
	}

	@Override
	public IHolder newHolder() {
		return null;
	}
}
