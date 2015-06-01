package com.snicesoft.avlib.widget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;

import com.snicesoft.avlib.AVLib;
import com.snicesoft.avlib.annotation.Layout;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;

/**
 * @author zhu zhe
 * @since 2015年4月15日 上午9:52:57
 * @version V1.0
 */
public abstract class AvAdapter<H extends IHolder, D extends IData> extends
		android.widget.BaseAdapter {
	private List<D> dataList = new ArrayList<D>();

	public void setDataList(Collection<D> dataList) {
		this.dataList.clear();
		this.dataList.addAll(dataList);
		notifyDataSetChanged();
	}

	public void clearData() {
		this.dataList.clear();
		notifyDataSetChanged();
	}

	public void addAll(Collection<D> collection) {
		this.dataList.addAll(collection);
		notifyDataSetChanged();
	}

	public void add(D d) {
		this.dataList.add(d);
		notifyDataSetChanged();
	}

	public void remove(int location) {
		this.dataList.remove(location);
		notifyDataSetChanged();
	}

	private int resource;

	public AvAdapter() {
		super();
		Layout layout = getClass().getAnnotation(Layout.class);
		if (layout != null && layout.value() != 0) {
			this.resource = layout.value();
		}
	}

	@Override
	public int getCount() {
		return dataList == null ? 0 : dataList.size();
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
			AVLib.initHolder(holder, convertView);
			holder.initViewParams();
			convertView.setTag(holder);
		} else {
			holder = (H) convertView.getTag();
		}
		D data = getData(position);
		if (data != null) {
			AVLib.dataBind(data, holder);
			bindAfter(holder, data, position);
		}
		return convertView;
	}

	public final void refreshDataByName(D data, H holder, String fieldName) {
		AVLib.dataBindTo(data, holder, fieldName);
	}

	/**
	 * 绑定数据之后，可以自定义数据绑定以及其他业务
	 * 
	 * @param holder
	 * @param data
	 * @param position
	 */
	public void bindAfter(H holder, D data, int position) {
	}

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
	public D getData(int position) {
		if (dataList == null || dataList.isEmpty())
			return null;
		if (position >= dataList.size()) {
			return null;
		}
		return dataList.get(position);
	}
}