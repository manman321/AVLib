package com.snicesoft.avlib.demo.hd;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.snicesoft.avlib.annotation.DataBind;
import com.snicesoft.avlib.annotation.Id;
import com.snicesoft.avlib.demo.R;
import com.snicesoft.avlib.widget.IAvData;
import com.snicesoft.avlib.widget.IAvHolder;

public interface MainHD {
	public class MainHolder implements IAvHolder {
		@Id(R.id.tvText)
		public TextView tvText;
		@Id(R.id.btnTest)
		public Button btnTest;
		@Id(R.id.lv)
		public ListView lv;
	}

	public class MainData implements IAvData {
		@DataBind(id = R.id.tvText)
		public String text = "我是来自MainData";
		@DataBind(id = R.id.btnTest)
		public String btn = "From MainData";

	}
}
