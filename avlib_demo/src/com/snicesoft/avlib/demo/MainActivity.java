package com.snicesoft.avlib.demo;

import android.view.View;
import android.view.View.OnClickListener;

import com.snicesoft.avlib.annotation.Layout;
import com.snicesoft.avlib.base.AvActivity;
import com.snicesoft.avlib.demo.adapter.LVAdapter;
import com.snicesoft.avlib.demo.hd.MainHD.MainData;
import com.snicesoft.avlib.demo.hd.MainHD.MainHolder;

@Layout(R.layout.activity_main)
public class MainActivity extends AvActivity<MainHolder, MainData> implements
		OnClickListener {

	@Override
	public void createHolderAndData() {
		holder = new MainHolder();
		data = new MainData();
	}

	@Override
	public void onCreate() {
		holder.lv.setAdapter(new LVAdapter());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnTest:

			break;

		default:
			break;
		}
	}
}
