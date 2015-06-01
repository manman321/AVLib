package com.snicesoft.avlib.demo.hd;

import android.content.Context;

import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;

public class TestHD {
	public static class TestHolder extends IHolder {

		public TestHolder(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void initViewParams() {

		}
	}

	public static class TestData extends IData {

		public TestData(Context context) {
			super(context);
		}
	}
}
