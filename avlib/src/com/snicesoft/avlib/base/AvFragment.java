package com.snicesoft.avlib.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snicesoft.avlib.AvTools;
import com.snicesoft.avlib.annotation.Layout;
import com.snicesoft.avlib.widget.IAvData;
import com.snicesoft.avlib.widget.IAvHolder;

public abstract class AvFragment<H extends IAvHolder, D extends IAvData, FA extends FragmentActivity>
		extends Fragment implements IAv<H, D> {
	private View root;
	protected D data;
	protected H holder;

	public final View getRootView() {
		return root;
	}

	@SuppressWarnings("unchecked")
	public final FA fa() {
		return (FA) getActivity();
	}

	@Override
	public final View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		holder = newHolder();
		data = newData();
		Layout layout = getClass().getAnnotation(Layout.class);
		if (layout != null && layout.value() != 0) {
			root = inflater.inflate(layout.value(), null);
		} else {
			System.err.println("@Layout not find.");
		}
		AvUtils.initHolder(this, holder, root);
		onCreate();
		return root;
	}

	public final void dataBindAll() {
		AvUtils.dataBindAll(holder, data, this);
	}

	public final void dataBindByName(String... fieldNames) {
		AvTools.dataBindByFieldNames(data, holder, fieldNames);
	}

	public void onCreate() {
		dataBindAll();
	}
}
