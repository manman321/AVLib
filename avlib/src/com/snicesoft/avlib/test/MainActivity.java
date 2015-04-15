package com.snicesoft.avlib.test;

import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.snicesoft.avlib.R;
import com.snicesoft.avlib.base.AvActivity;
import com.snicesoft.avlib.test.hd.MainHB.MainData;
import com.snicesoft.avlib.test.hd.MainHB.MainHolder;

/**
 * @author zhu zhe
 * @since 2015年4月15日 上午10:10:52
 * @version V1.0
 */
public class MainActivity extends AvActivity<MainHolder, MainData> implements
		OnItemClickListener {
	private Handler mHandler = new Handler();

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(this, "position=" + position, Toast.LENGTH_LONG).show();
	}

	@Override
	public void createHolderAndData() {
		holder = new MainHolder();
		data = new MainData();
	}

	@Override
	public void loadContentView() {
		setContentView(R.layout.activity_main);
	}

	@Override
	public void initHD() {
		holder.setAdapter(new ListAdapter(R.layout.item_list));
		holder.listView1.setOnItemClickListener(this);
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// data发生变化
				data.text = System.currentTimeMillis() + "";
				dataBind();
			}
		}, 3000);
	}
}
