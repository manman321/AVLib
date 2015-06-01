package com.snicesoft.avlib.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

import com.snicesoft.avlib.AVLib;
import com.snicesoft.avlib.annotation.DataBind;
import com.snicesoft.avlib.annotation.Id;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;

public class TestActivity extends Activity implements IHolder, IData {

	@Id(R.id.textView1)
	TextView textView1;
	@Id(R.id.radioButton1)
	RadioButton radioButton1;

	@DataBind(id = R.id.textView1)
	String txt = "我是Data的txt";
	@DataBind(id = R.id.radioButton1)
	String radio = "我是Data的radio";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		AVLib.initHolder(this);
		AVLib.dataBind(this, this);
	}

	@Override
	public void initViewParams() {
		
	}
}
