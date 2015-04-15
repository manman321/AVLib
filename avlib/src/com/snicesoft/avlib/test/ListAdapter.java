package com.snicesoft.avlib.test;

import android.graphics.Color;

import com.snicesoft.avlib.test.hd.ListAdapterHB.Data;
import com.snicesoft.avlib.test.hd.ListAdapterHB.ListViewHolder;
import com.snicesoft.avlib.widget.AvAdapter;

/**
 * @author zhu zhe
 * @since 2015年4月15日 下午1:28:16
 * @version V1.0
 */
public class ListAdapter extends AvAdapter<ListViewHolder, Data> {

	public ListAdapter(int resource) {
		super(resource);
	}

	@Override
	public int getCount() {
		return 20;
	}

	@Override
	public void bindAfter(ListViewHolder holder, Data data, int position) {
		if (position % 2 == 0)
			holder.button.setBackgroundColor(Color.RED);
	}

	@Override
	public ListViewHolder newHolder() {
		return new ListViewHolder();
	}

	@Override
	public Data getData(int position) {
		return new Data(position);
	}

}
