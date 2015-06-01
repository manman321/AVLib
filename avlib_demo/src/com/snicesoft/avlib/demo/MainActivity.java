package com.snicesoft.avlib.demo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import com.snicesoft.avlib.annotation.Layout;
import com.snicesoft.avlib.base.AvFragmentActivity;
import com.snicesoft.avlib.demo.adapter.hd.LvHD.LVData;
import com.snicesoft.avlib.demo.hd.MainHD.MainData;
import com.snicesoft.avlib.demo.hd.MainHD.MainHolder;

@Layout(R.layout.activity_main)
public class MainActivity extends AvFragmentActivity<MainHolder, MainData> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		List<LVData> list = new ArrayList<LVData>();
		for (int i = 0; i < 20; i++) {
			list.add(new LVData(this, i + 1));
		}
		data.lvAdapter.addAll(list);
	}

	@Override
	public MainHolder newHolder() {
		return new MainHolder(this);
	}

	@Override
	public MainData newData() {
		return new MainData(this);
	}

}
