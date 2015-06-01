package com.snicesoft.avlib.demo.adapter.hd;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.snicesoft.avlib.annotation.DataBind;
import com.snicesoft.avlib.annotation.Id;
import com.snicesoft.avlib.demo.R;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;

public interface LvHD {
	public class LVHolder extends IHolder {
		public LVHolder(Context context) {
			super(context);
		}

		@Id(R.id.tvItemValue)
		private TextView tvItemValue;
		@Id(R.id.btnDianji)
		private Button btnDianji;

		@Override
		public void initViewParams() {
			btnDianji.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			super.onClick(v);
			if (v.getId() == R.id.btnDianji) {
				LVData data = (LVData) getTag();
				Toast.makeText(getContext(), "dianji=" + data.value,
						Toast.LENGTH_LONG).show();
			}
		}
	}

	public class LVData extends IData {
		public LVData(Context context) {
			super(context);
		}

		public LVData(Context context, int value) {
			this(context);
			this.value = value + "";
		}

		@DataBind(id = R.id.tvItemValue, prefix = "From LVData ", suffix = "..")
		public String value = "";

	}
}
