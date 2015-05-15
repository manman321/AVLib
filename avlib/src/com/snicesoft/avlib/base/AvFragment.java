package com.snicesoft.avlib.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snicesoft.avlib.AVLib;
import com.snicesoft.avlib.annotation.Layout;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;

public abstract class AvFragment<H extends IHolder, D extends IData, FA extends FragmentActivity>
		extends Fragment implements IAv<H, D> {
	protected D data;
	protected H holder;

	@SuppressWarnings("unchecked")
	public final FA fa() {
		return (FA) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		holder = newHolder();
		data = newData();
		View root = null;
		Layout layout = getClass().getAnnotation(Layout.class);
		if (layout != null && layout.value() != 0) {
			root = inflater.inflate(layout.value(), null);
		} else {
			System.err.println("@Layout not find.");
		}
		AvUtils.initHolder(this, holder);
		dataBindAll();
		return root;
	}

	public final void dataBindAll() {
		AvUtils.dataBindAll(holder, data, this);
	}

	public final void dataBindTo(String fieldName) {
		AVLib.dataBindTo(data, holder, fieldName);
	}
}