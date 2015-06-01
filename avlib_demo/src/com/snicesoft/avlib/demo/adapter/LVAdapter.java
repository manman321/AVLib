package com.snicesoft.avlib.demo.adapter;

import android.content.Context;

import com.snicesoft.avlib.annotation.Layout;
import com.snicesoft.avlib.demo.R;
import com.snicesoft.avlib.demo.adapter.hd.LvHD.LVData;
import com.snicesoft.avlib.demo.adapter.hd.LvHD.LVHolder;
import com.snicesoft.avlib.rule.IRule;
import com.snicesoft.avlib.widget.AvAdapter;

@Layout(R.layout.item_lv)
public class LVAdapter extends AvAdapter<LVHolder, LVData> implements IRule {
	private Context context;

	public LVAdapter(Context context) {
		this.context = context;
	}

	@Override
	public LVHolder newHolder() {
		return new LVHolder(getContext());
	}

	@Override
	public Context getContext() {
		return context;
	}
}
