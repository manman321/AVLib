package com.snicesoft.avlib.test.hd;

import android.widget.TextView;

import com.snicesoft.avlib.R;
import com.snicesoft.avlib.annotation.DataBind;
import com.snicesoft.avlib.annotation.Id;
import com.snicesoft.avlib.widget.IAvData;
import com.snicesoft.avlib.widget.IAvHolder;

public interface ListAdapterHB {
	public class Data implements IAvData {
		@DataBind(id = R.id.button1, prefix = "测试——")
		public String text;

		public Data(int position) {
			this.text = "" + (position + 1);
		}
	}

	public class ListViewHolder implements IAvHolder {
		@Id(value = R.id.button1)
		public TextView button;
	}
}
