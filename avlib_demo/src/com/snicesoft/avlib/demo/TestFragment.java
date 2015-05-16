package com.snicesoft.avlib.demo;

import com.snicesoft.avlib.annotation.Layout;
import com.snicesoft.avlib.base.AvFragment;
import com.snicesoft.avlib.demo.hd.TestHD.TestData;
import com.snicesoft.avlib.demo.hd.TestHD.TestHolder;

@Layout(R.layout.fragment_test)
public class TestFragment extends
		AvFragment<TestHolder, TestData, MainActivity> {

	@Override
	public TestHolder newHolder() {
		return new TestHolder();
	}

	@Override
	public TestData newData() {
		return new TestData();
	}

}
