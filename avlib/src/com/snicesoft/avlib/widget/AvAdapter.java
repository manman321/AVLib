package com.snicesoft.avlib.widget;

import android.view.View;
import android.view.ViewGroup;

import com.snicesoft.avlib.AvTools;

/**
 * @author zhu zhe
 * @since 2015年4月15日 上午9:52:57
 * @version V1.0
 */
public abstract class AvAdapter<H extends IAvHolder, D extends IAvData> extends
		android.widget.BaseAdapter {
	private int resource;

	public AvAdapter(int resource) {
		super();
		this.resource = resource;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		H holder = null;
		if (convertView == null) {
			holder = newHolder();
			convertView = View.inflate(parent.getContext(), resource, null);
			AvTools.initHolder(convertView, holder);
			convertView.setTag(holder);
		} else {
			holder = (H) convertView.getTag();
		}
		D data = getData(position);
		if (data != null) {
			AvTools.dataBind(data, holder);
			bindAfter(holder, data, position);
		}
		return convertView;
	}

	/**
	 * 绑定数据之后，可以自定义数据绑定以及其他业务
	 * 
	 * @param holder
	 * @param data
	 * @param position
	 */
	public abstract void bindAfter(H holder, D data, int position);

	/**
	 * 创建Holder
	 * 
	 * @return
	 */
	public abstract H newHolder();

	/**
	 * 获取数据
	 * 
	 * @param position
	 * @return
	 */
	public abstract D getData(int position);
}
