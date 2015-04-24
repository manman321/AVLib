package com.snicesoft.avlib.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snicesoft.avlib.AvTools;
import com.snicesoft.avlib.widget.IAvData;
import com.snicesoft.avlib.widget.IAvHolder;

public abstract class AvFragment<H extends IAvHolder, D extends IAvData>
		extends Fragment {
	private View root;
	protected D data;
	protected H holder;

	public final View getRootView() {
		return root;
	}

	public abstract View onCreateView();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		createHolderAndData();
		root = onCreateView();
		if (holder != null)
			AvTools.initHolder(holder, root);
		else
			AvTools.initHolder(this);
		onAv();
		return root;
	}

	public abstract void createHolderAndData();

	public void dataBind() {
		if (holder == null && data != null)
			AvTools.dataBind(data, this);
		if (holder != null && data != null)
			AvTools.dataBind(data, holder);
	}

	public abstract void onAv();
}
