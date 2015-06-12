package com.snicesoft.avlib.rule;

/**
 * @author zhe
 * @since 2015年4月15日 上午9:54:17
 * @version V1.0
 */
public abstract class IHolder {
	public abstract void initViewParams();

	private Object tag;

	public void setTag(Object tag) {
		this.tag = tag;
	}

	public Object getTag() {
		return tag;
	}
}