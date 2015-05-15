package com.snicesoft.avlib.demo;

import android.os.Bundle;
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
	protected void onResume() {
		super.onResume();
		setTitle(R.string.app_name);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// hostContext = HostManager.getHostContext();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnTest:
			FragmentUtil.openFragment(this, R.id.content, new TestFragment());
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
