package com.snicesoft.avlib.demo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.snicesoft.avlib.annotation.DataBind;
import com.snicesoft.avlib.annotation.DataType;
import com.snicesoft.avlib.annotation.Id;
import com.snicesoft.avlib.annotation.Layout;
import com.snicesoft.avlib.base.AvFragmentActivity;
import com.snicesoft.avlib.demo.MainActivity.MainData;
import com.snicesoft.avlib.demo.MainActivity.MainHolder;
import com.snicesoft.avlib.demo.adapter.LVAdapter;
import com.snicesoft.avlib.demo.adapter.LVAdapter.LVData;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;

@Layout(R.layout.activity_main)
public class MainActivity extends AvFragmentActivity<MainHolder, MainData> {
	public class MainHolder extends IHolder implements OnItemClickListener {
		@Id(R.id.tvText)
		public TextView tvText;
		@Id(R.id.btnTest)
		public Button btnTest;
		@Id(R.id.btnOpen)
		public Button btnOpen;
		@Id(R.id.lv)
		public ListView lv;

		@Override
		public void initViewParams() {
			lv.setOnItemClickListener(this);
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Toast.makeText(getBaseContext(), "position=" + position,
					Toast.LENGTH_LONG).show();
		}
	}

	public class MainData extends IData {
		@DataBind(id = R.id.tvText)
		public String text = "我是来自MainData";
		@DataBind(id = R.id.btnTest)
		public String btn = "Load Fragment";
		@DataBind(id = R.id.btnOpen)
		public String open = "Open TestActivity";
		@DataBind(id = R.id.lv, dataType = DataType.ADAPTER)
		public LVAdapter lvAdapter = new LVAdapter(getBaseContext());

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		List<LVData> list = new ArrayList<LVData>();
		for (int i = 0; i < 20; i++) {
			list.add(new LVAdapter.LVData(i + 1));
		}
		_data.lvAdapter.addAll(list);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnTest:
			break;
		case R.id.btnOpen:
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
