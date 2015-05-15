package com.snicesoft.avlib.demo.adapter.hd;

import android.widget.TextView;

import com.snicesoft.avlib.annotation.DataBind;
import com.snicesoft.avlib.annotation.Id;
import com.snicesoft.avlib.demo.R;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;

public interface LvHD {
	public class LVHolder implements IHolder {
		@Id(R.id.tvItemValue)
		public TextView tvItemValue;
	}

	public class LVData implements IData {
		@DataBind(id = R.id.tvItemValue, prefix = "From LVData ", suffix = "..")
		public String value = "";

		public LVData(int value) {
			this.value = "" + value;
		}
	}
}
