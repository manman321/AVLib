package com.snicesoft.avlib.widget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.view.View;

import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;

public abstract class BaseAdapter<H extends IHolder, D extends IData> extends
		android.widget.BaseAdapter {
	private Context context;

	private List<D> dataList = new ArrayList<D>();

	public BaseAdapter(Context context) {
		this.context = context;
	}

	public final void add(D d) {
		this.dataList.add(d);
		notifyDataSetChanged();
	}

	public final void add(int position, D d) {
		this.dataList.add(position, d);
		notifyDataSetChanged();
	}

	public final void addAll(Collection<D> collection) {
		this.dataList.addAll(collection);
		notifyDataSetChanged();
	}

	public final void addAll(int position, Collection<D> collection) {
		this.dataList.addAll(position, collection);
		notifyDataSetChanged();
	}

	public final void clear() {
		this.dataList.clear();
		notifyDataSetChanged();
	}

	public void bindAfter(int position, View view, D data) {

	}

	public void bindAfter(int position, View view, H holder, D data) {
	}

	public Context getContext() {
		return context;
	}

	@Override
	public int getCount() {
		return dataList == null ? 0 : dataList.size();
	}

	@Override
	public D getItem(int position) {
		if (dataList == null || dataList.isEmpty())
			return null;
		if (position >= dataList.size()) {
			return null;
		}
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	/**
	 * 创建Holder
	 * 
	 * @return
	 */
	public H newHolder() {
		return null;
	}

	public void remove(int location) {
		this.dataList.remove(location);
		notifyDataSetChanged();
	}

	public void setDataList(Collection<D> dataList) {
		this.dataList.clear();
		this.dataList.addAll(dataList);
		notifyDataSetChanged();
	}
}
