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

@SuppressWarnings("unchecked")
public abstract class AvFragment<H extends IHolder, D extends IData, FA extends FragmentActivity>
		extends Fragment implements IAv<H, D>, View.OnClickListener {
	protected D _data;
	protected H _holder;

	@Override
	public final D getData() {
		return _data;
	}

	@Override
	public final H getHolder() {
		return _holder;
	}

	@Override
	public final void dataBindAll() {
		AVLib.dataBind(_data, _holder);
	}

	@Override
	public final void dataBindTo(String fieldName) {
		AVLib.dataBindTo(_data, _holder, fieldName);
	}

	@Override
	public void dataBindTo(String fieldName, View view) {
		AVLib.dataBindTo(_data, view, fieldName);
	}

	public final FA fa() {
		return (FA) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		_holder = newHolder();
		_data = newData();
		View root = inflater.inflate(LayoutUtils.getLayoutId(getClass()), null);
		AvUtils.initHolder(_holder, root);
		if (_holder != null)
			_holder.initViewParams();
		dataBindAll();
		return root;
	}
}