package com.snicesoft.avlib.base;

import android.view.View;

interface IAv<H, D> {
	public H newHolder();

	public D newData();

	public H getHolder();

	public D getData();

	public void dataBindAll();

	public void dataBindTo(String fieldName);

	public void dataBindTo(String fieldName, View view);
}