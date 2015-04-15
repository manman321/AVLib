package com.snicesoft.avlib.demo.adapter;

import com.snicesoft.avlib.demo.adapter.hd.LvHD.LVData;
import com.snicesoft.avlib.demo.adapter.hd.LvHD.LVHolder;
import com.snicesoft.avlib.widget.AvAdapter;

public class LVAdapter extends AvAdapter<LVHolder, LVData> {

	public LVAdapter(int resource) {
		super(resource);
		for (int i = 0; i < 20; i++) {
			add(new LVData(i + 1));
		}
	}

	@Override
	public void bindAfter(LVHolder holder, LVData data, int position) {

	}

	@Override
	public LVHolder newHolder() {
		return new LVHolder();
	}
}
