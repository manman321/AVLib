package com.snicesoft.avlib.demo.adapter;

import com.snicesoft.avlib.annotation.Layout;
import com.snicesoft.avlib.demo.R;
import com.snicesoft.avlib.demo.adapter.hd.LvHD.LVData;
import com.snicesoft.avlib.demo.adapter.hd.LvHD.LVHolder;
import com.snicesoft.avlib.widget.AvAdapter;

@Layout(R.layout.item_lv)
public class LVAdapter extends AvAdapter<LVHolder, LVData> {

	public LVAdapter() {
	}

	@Override
	public LVHolder newHolder() {
		return new LVHolder();
	}
}
