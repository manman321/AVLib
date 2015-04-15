package com.snicesoft.avlib.test.hd;

import android.graphics.Color;
import android.widget.ListView;
import android.widget.TextView;

import com.snicesoft.avlib.R;
import com.snicesoft.avlib.annotation.DataBind;
import com.snicesoft.avlib.annotation.Id;
import com.snicesoft.avlib.test.ListAdapter;
import com.snicesoft.avlib.widget.IAvData;
import com.snicesoft.avlib.widget.IAvHolder;

public interface MainHB {
	public class MainHolder implements IAvHolder {
		@Id(value = R.id.textView1, backgroundColor = Color.RED)
		public TextView v;
		@Id(R.id.listView1)
		public ListView listView1;
		public ListAdapter adapter;

		public void setAdapter(ListAdapter adapter) {
			this.adapter = adapter;
			listView1.setAdapter(this.adapter);
		}

		public ListAdapter getAdapter() {
			return adapter;
		}
	}

	public class MainData implements IAvData {
		@DataBind(id = R.id.textView1, pattern = "yyyy-MM-dd HH:mm:ss", prefix = "￥：")
		public String text = "" + System.currentTimeMillis();
	}
}
