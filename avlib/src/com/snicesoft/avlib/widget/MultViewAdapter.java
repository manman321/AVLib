package com.snicesoft.avlib.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.snicesoft.avlib.AVLib;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;
import com.snicesoft.avlib.view.ViewFinder;

public abstract class MultViewAdapter<H extends IHolder, D extends IData>
		extends BaseAdapter<H, D> {
	public MultViewAdapter(Context context) {
		super(context);
	}

	public abstract int getItemViewType(int position);

	public abstract int getViewTypeCount();

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		H holder = null;
		if (convertView == null) {
			holder = newHolder();
			convertView = View.inflate(parent.getContext(),
					getItemViewType(position), null);
			if (holder != null) {
				AVLib.initHolder(holder, new ViewFinder(convertView));
				holder.initViewParams();
				convertView.setTag(holder);
			}
		} else {
			holder = (H) convertView.getTag();
		}
		D data = getItem(position);
		if (data != null) {
			if (holder != null)
				holder.setTag(data);
			AVLib.dataBind(data, new ViewFinder(convertView));
			if (holder == null)
				bindAfter(position, convertView, data);
			else
				bindAfter(position, convertView, holder, data);
		}
		return convertView;
	}
}
