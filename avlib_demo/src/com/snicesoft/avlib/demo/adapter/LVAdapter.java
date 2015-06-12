package com.snicesoft.avlib.demo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.snicesoft.avlib.annotation.DataBind;
import com.snicesoft.avlib.annotation.Id;
import com.snicesoft.avlib.annotation.Layout;
import com.snicesoft.avlib.demo.R;
import com.snicesoft.avlib.demo.adapter.LVAdapter.LVData;
import com.snicesoft.avlib.demo.adapter.LVAdapter.LVHolder;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;
import com.snicesoft.avlib.widget.AvAdapter;

@Layout(R.layout.item_lv)
public class LVAdapter extends AvAdapter<LVHolder, LVData> {
	public LVAdapter(Context context) {
		super(context);
	}

	public class LVHolder extends IHolder implements View.OnClickListener {
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
			if (v.getId() == R.id.btnDianji) {
				LVData data = (LVData) getTag();
				Toast.makeText(getContext(), "dianji=" + data.value,
						Toast.LENGTH_LONG).show();
			}
		}
	}

	public static class LVData extends IData {

		public LVData(int value) {
			this.value = value + "";
		}

		@DataBind(id = R.id.tvItemValue, prefix = "From LVData ", suffix = "..")
		public String value = "";

	}

	@Override
	public LVHolder newHolder() {
		return new LVHolder();
	}
}
