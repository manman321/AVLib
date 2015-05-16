package com.snicesoft.avlib.demo;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.snicesoft.avlib.annotation.Layout;
import com.snicesoft.avlib.base.AvFragmentActivity;
import com.snicesoft.avlib.demo.hd.MainHD.MainData;
import com.snicesoft.avlib.demo.hd.MainHD.MainHolder;

@Layout(R.layout.activity_main)
public class MainActivity extends AvFragmentActivity<MainHolder, MainData>
		implements OnClickListener {

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
