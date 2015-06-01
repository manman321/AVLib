package com.snicesoft.avlib.demo.hd;

import android.content.Context;
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
import com.snicesoft.avlib.demo.R;
import com.snicesoft.avlib.demo.adapter.LVAdapter;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;

public interface MainHD {
	public class MainHolder extends IHolder implements OnItemClickListener {
		public MainHolder(Context context) {
			super(context);
		}

		@Id(R.id.tvText)
		public TextView tvText;
		@Id(R.id.btnTest)
		public Button btnTest;
		@Id(R.id.btnOpen)
		public Button btnOpen;
		@Id(R.id.lv)
		public ListView lv;

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
		public void initViewParams() {
			lv.setOnItemClickListener(this);
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Toast.makeText(getContext(), "position="+position, Toast.LENGTH_LONG).show();
		}
	}

	public class MainData extends IData {
		public MainData(Context context) {
			super(context);
		}

		@DataBind(id = R.id.tvText)
		public String text = "我是来自MainData";
		@DataBind(id = R.id.btnTest)
		public String btn = "Load Fragment";
		@DataBind(id = R.id.btnOpen)
		public String open = "Open TestActivity";
		@DataBind(id = R.id.lv, dataType = DataType.ADAPTER)
		public LVAdapter lvAdapter = new LVAdapter(getContext());

	}
}
