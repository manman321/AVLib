package com.snicesoft.avlib.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snicesoft.avlib.AvTools;
import com.snicesoft.avlib.annotation.Layout;
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

	@Override
	public final View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		createHolderAndData();
		Layout layout = getClass().getAnnotation(Layout.class);
		if (layout != null && layout.value() != 0) {
			root = inflater.inflate(layout.value(), null);
		} else {
			System.err.println("@Layout not find.");
		}
		if (holder != null)
			AvTools.initHolder(holder, root);
		else
			AvTools.initHolder(this, root);
		onCreate();
		return root;
	}

	public abstract void createHolderAndData();

	public final void dataBind() {
		if (holder == null && data != null)
			AvTools.dataBind(data, this);
		if (holder != null && data != null)
			AvTools.dataBind(data, holder);
	}

	public void onCreate() {
		dataBind();
	}
}
