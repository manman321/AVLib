package com.snicesoft.avlib.test;

import android.graphics.Color;

import com.snicesoft.avlib.R;
import com.snicesoft.avlib.annotation.DataBind;
import com.snicesoft.avlib.test.holder.ListViewHolder;
import com.snicesoft.avlib.widget.AvAdapter;

/**
 * @author zhu zhe
 * @since 2015年4月15日 下午1:28:16
 * @version V1.0
 */
public class ListAdapter extends AvAdapter<ListViewHolder> {
	class Data {
		@DataBind(id = R.id.button1, prefix = "测试——")
		public String text;

		public Data(int position) {
			this.text = "" + (position + 1);
		}
	}

	public ListAdapter(int resource) {
		super(resource);
	}

	@Override
	public int getCount() {
		return 20;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public void bindAfter(ListViewHolder holder, Object data, int position) {
		if (position % 2 == 0)
			holder.button.setBackgroundColor(Color.RED);
	}

	@Override
	public ListViewHolder newHolder() {
		return new ListViewHolder();
	}

	@Override
	public Object getData(int position) {
		return new Data(position);
	}

}
