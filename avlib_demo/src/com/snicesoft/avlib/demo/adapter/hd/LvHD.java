package com.snicesoft.avlib.demo.adapter.hd;

import android.widget.TextView;

import com.snicesoft.avlib.annotation.DataBind;
import com.snicesoft.avlib.annotation.Id;
import com.snicesoft.avlib.demo.R;
import com.snicesoft.avlib.widget.IAvData;
import com.snicesoft.avlib.widget.IAvHolder;

public interface LvHD {
	public class LVHolder implements IAvHolder {
		@Id(R.id.tvItemValue)
		public TextView tvItemValue;
	}

	public class LVData implements IAvData {
		@DataBind(id = R.id.tvItemValue, prefix = "From LVData ", suffix = "..")
		public String value = "";

		public LVData(int value) {
			this.value = "" + value;
		}
	}
}
