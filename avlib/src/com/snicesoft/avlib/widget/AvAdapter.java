package com.snicesoft.avlib.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.snicesoft.avlib.AVLib;
import com.snicesoft.avlib.annotation.Layout;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;
import com.snicesoft.avlib.view.ViewFinder;

/**
 * @author zhu zhe
 * @since 2015年4月15日 上午9:52:57
 * @version V1.0
 */
public abstract class AvAdapter<H extends IHolder, D extends IData> extends
		BaseAdapter<H, D> {

	private int resource;

	public AvAdapter(Context context) {
		super(context);
		Layout layout = getClass().getAnnotation(Layout.class);
		if (layout != null && layout.value() != 0) {
			this.resource = layout.value();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		H holder = null;
		if (convertView == null) {
			holder = newHolder();
			convertView = View.inflate(parent.getContext(), resource, null);
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
