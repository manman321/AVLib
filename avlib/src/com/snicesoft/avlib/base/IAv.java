package com.snicesoft.avlib.base;

public interface IAv<H, D> {
	public H newHolder();

	public D newData();
}
