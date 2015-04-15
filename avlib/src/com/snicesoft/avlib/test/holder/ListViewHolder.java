package com.snicesoft.avlib.test.holder;

import android.widget.TextView;

import com.snicesoft.avlib.R;
import com.snicesoft.avlib.annotation.Id;
import com.snicesoft.avlib.widget.IAvHolder;

/**
 * @author zhu zhe
 * @since 2015年4月15日 下午2:14:45
 * @version V1.0
 */
public class ListViewHolder implements IAvHolder {
	@Id(value = R.id.button1)
	public TextView button;
}
