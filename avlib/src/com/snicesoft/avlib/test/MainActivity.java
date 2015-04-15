package com.snicesoft.avlib.test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.snicesoft.avlib.AvTools;
import com.snicesoft.avlib.R;
import com.snicesoft.avlib.annotation.DataBind;
import com.snicesoft.avlib.annotation.Id;
import com.snicesoft.avlib.widget.IAvData;

/**
 * @author zhu zhe
 * @since 2015年4月15日 上午10:10:52
 * @version V1.0
 */
public class MainActivity extends Activity implements OnItemClickListener {
	@Id(value = R.id.textView1, backgroundColor = Color.RED)
	TextView v;
	@Id(R.id.listView1)
	ListView listView1;

	ListAdapter adapter;

	class Data implements IAvData {
		@DataBind(id = R.id.textView1, pattern = "yyyy-MM-dd HH:mm:ss", prefix = "￥：")
		String text = "" + System.currentTimeMillis();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AvTools.initHolder(this);
		AvTools.dataBind(new Data(), this);
		adapter = new ListAdapter(R.layout.item_list);
		listView1.setAdapter(adapter);
		listView1.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(this, "position=" + position, Toast.LENGTH_LONG).show();
	}
}
