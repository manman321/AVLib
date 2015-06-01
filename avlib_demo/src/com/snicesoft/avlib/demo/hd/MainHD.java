package com.snicesoft.avlib.demo.hd;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.snicesoft.avlib.annotation.DataBind;
import com.snicesoft.avlib.annotation.DataType;
import com.snicesoft.avlib.annotation.Id;
import com.snicesoft.avlib.demo.R;
import com.snicesoft.avlib.demo.adapter.LVAdapter;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;

public interface MainHD {
	public class MainHolder implements IHolder {
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
			// TODO Auto-generated method stub
			
		}
	}

	public class MainData implements IData {
		@DataBind(id = R.id.tvText)
		public String text = "我是来自MainData";
		@DataBind(id = R.id.btnTest)
		public String btn = "Load Fragment";
		@DataBind(id = R.id.btnOpen)
		public String open = "Open TestActivity";
		@DataBind(id = R.id.lv, dataType = DataType.ADAPTER)
		public LVAdapter lvAdapter = new LVAdapter();

	}
}
