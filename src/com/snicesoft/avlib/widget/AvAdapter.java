package com.snicesoft.avlib.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.snicesoft.avlib.AvTools;

/**
 * @author zhu zhe
 * @since 2015年4月15日 上午9:52:57
 * @version V1.0
 */
public abstract class AvAdapter<T extends IAvHolder> extends BaseAdapter {
	private int resource;

	public AvAdapter(int resource) {
		super();
		this.resource = resource;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		T holder = null;
		if (convertView == null) {
			holder = newHolder();
			convertView = View.inflate(parent.getContext(), resource, null);
			// 绑定view到holder
			AvTools.initHolder(convertView, holder);
			convertView.setTag(holder);
		} else {
			holder = (T) convertView.getTag();
		}
		Object data = getData(position);
		// 绑定数据
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
	public abstract void bindAfter(T holder, Object data, int position);

	/**
	 * 创建Holder
	 * 
	 * @return
	 */
	public abstract T newHolder();

	/**
	 * 获取数据
	 * 
	 * @param position
	 * @return
	 */
	public abstract Object getData(int position);
}
