package com.snicesoft.avlib.base;

interface IAv<H, D> {
	public H newHolder();

	public D newData();

	public H getHolder();

	public D getData();

	public void dataBindAll();

	public void dataBindTo(String fieldName);
}