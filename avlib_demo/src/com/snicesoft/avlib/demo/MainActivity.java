package com.snicesoft.avlib.demo;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.snicesoft.avlib.annotation.Layout;
import com.snicesoft.avlib.base.AvFragmentActivity;
import com.snicesoft.avlib.demo.adapter.hd.LvHD.LVData;
import com.snicesoft.avlib.demo.hd.MainHD.MainData;
import com.snicesoft.avlib.demo.hd.MainHD.MainHolder;

@Layout(R.layout.activity_main)
public class MainActivity extends AvFragmentActivity<MainHolder, MainData>
		implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		List<LVData> list = new ArrayList<LVData>();
		for (int i = 0; i < 20; i++) {
			list.add(new LVData(i + 1));
		}
		data.lvAdapter.addAll(list);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnTest:
			FragmentUtil.openFragment(this, R.id.content, new TestFragment());
			break;
		case R.id.btnOpen:
			startActivity(new Intent(this, TestActivity.class));
			break;
		default:
			break;
		}
	}

	@Override
	public MainHolder newHolder() {
		return new MainHolder();
	}

	@Override
	public MainData newData() {
		return new MainData();
	}

}
