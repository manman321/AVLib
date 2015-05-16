package com.snicesoft.avlib.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snicesoft.avlib.AVLib;
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
		View root = inflater.inflate(LayoutUtils.getLayoutId(getClass()), null);
		AvUtils.initHolder(holder, this);
		dataBindAll();
		return root;
	}

	public final void dataBindAll() {
		AVLib.dataBind(data, holder);
	}

	public final void dataBindTo(String fieldName) {
		AVLib.dataBindTo(data, holder, fieldName);
	}
}