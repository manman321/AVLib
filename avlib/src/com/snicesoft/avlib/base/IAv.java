package com.snicesoft.avlib.base;

interface IAv<H, D> {
	public H newHolder();

	public D newData();
}