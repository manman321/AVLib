package com.snicesoft.avlib;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.snicesoft.avlib.annotation.DataBind;
import com.snicesoft.avlib.annotation.Id;
import com.snicesoft.avlib.rule.IData;
import com.snicesoft.avlib.rule.IHolder;
import com.snicesoft.avlib.view.ViewFinder;

/**
 * @author zhu zhe
 * @since 2015年4月15日 上午9:50:38
 * @version V1.0
 */
@SuppressLint({ "SimpleDateFormat", "UseSparseArrays" })
public class AVLib {
	public interface LoadImg {
		void loadImg(View v, int loadingResId, int failResId, String url);
	}

	public static class ViewValue {
		public DataBind dataBind;
		public Object value;

		public ViewValue(Object value, DataBind dataBind) {
			super();
			this.value = value;
			this.dataBind = dataBind;
		}

		public DataBind getDataBind() {
			return dataBind;
		}

		public Object getValue() {
			return value;
		}
	}

	private static SimpleDateFormat dateFormat = new SimpleDateFormat();

	private static LoadImg loadImg;

	/**
	 * 数据绑定到view
	 * 
	 * @param <D>
	 * 
	 * @param data
	 * @param finder
	 */
	public static <D extends IData> void dataBind(D data, ViewFinder finder) {
		if (data == null || finder == null)
			return;
		try {
			Class<?> clazz = data.getClass();
			dataBind(data, finder, clazz);
			if (isNotObject(clazz)) {
				clazz = clazz.getSuperclass();
				dataBind(data, finder, clazz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static <D extends IData> void dataBindTo(D data, ViewFinder finder,
			String fieldName) {
		if (data == null || finder == null || TextUtils.isEmpty(fieldName))
			return;
		try {
			Class<?> clazz = data.getClass();
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			bindValue(data, finder, field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (isNotObject(data.getClass())) {
			try {
				Class<?> clazz = data.getClass().getSuperclass();
				Field field = clazz.getDeclaredField(fieldName);
				field.setAccessible(true);
				bindValue(data, finder, field);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void dataBind(IData data, ViewFinder finder, Class<?> clazz) {
		Field[] dataFields = clazz.getDeclaredFields();
		if (dataFields != null && dataFields.length > 0) {
			for (Field field : dataFields) {
				if (field.getAnnotation(DataBind.class) == null)
					continue;
				try {
					field.setAccessible(true);
					bindValue(data, finder, field);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void bindValue(IData data, ViewFinder finder, Field field)
			throws IllegalAccessException {
		Object value = field.get(data);
		if (value == null)
			return;
		DataBind dataBind = field.getAnnotation(DataBind.class);
		if (dataBind != null) {
			int vid = dataBind.id();
			View view = finder.findViewById(vid);
			if (view != null)
				setValue(view, new ViewValue(value, dataBind));
		}
	}

	public static <H extends IHolder> void initHolder(H holder,
			ViewFinder finder) {
		if (holder == null || finder == null)
			return;
		try {
			Class<?> clazz = holder.getClass();
			initHolder(holder, finder, clazz);
			if (isNotObject(clazz)) {
				clazz = clazz.getSuperclass();
				initHolder(holder, finder, clazz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void initHolder(IHolder holder, ViewFinder finder,
			Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				try {
					field.setAccessible(true);
					Id resource = field.getAnnotation(Id.class);
					if (resource == null)
						continue;
					int resId = resource.value();
					int background = resource.background();
					int backgroundColor = resource.backgroundColor();
					int src = resource.src();
					View v = finder.findViewById(resId);
					if (v != null) {
						if (backgroundColor != 0)
							v.setBackgroundColor(backgroundColor);
						if (background != 0)
							v.setBackgroundResource(background);
						if (src != 0 && v instanceof ImageView)
							((ImageView) v).setImageResource(src);
						if (field.get(holder) == null)
							field.set(holder, v);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (holder != null)
				holder.initViewParams();
		}
	}

	private static boolean isNotObject(Class<?> clazz) {
		return clazz.getSuperclass() != IData.class;
	}

	public static void setLoadImg(LoadImg loadImg) {
		AVLib.loadImg = loadImg;
	}

	@SuppressWarnings("unchecked")
	private static <T extends View> void setValue(T view, ViewValue viewValue) {
		Object value = viewValue.getValue();
		String p = viewValue.getDataBind().prefix();
		String s = viewValue.getDataBind().suffix();
		int loading = viewValue.getDataBind().loadingResId();
		int fail = viewValue.getDataBind().failResId();
		String pattern = viewValue.getDataBind().pattern();
		switch (viewValue.getDataBind().dataType()) {
		case STRING:
			TextView tv = (TextView) view;
			if (TextUtils.isEmpty(pattern)) {
				tv.setText(p + value + s);
			} else {
				dateFormat.applyPattern(pattern);
				if (value instanceof Long || value instanceof Date) {
					tv.setText(p + dateFormat.format(value) + s);
				} else if (value instanceof String) {
					tv.setText(p + value + s);
				}
			}
			break;
		case IMG:
			if (value instanceof Integer) {
				int resId = Integer.parseInt(value.toString());
				if (view instanceof ImageView) {
					((ImageView) view).setImageResource(resId);
				} else {
					view.setBackgroundResource(resId);
				}
			} else if (value instanceof String) {
				if (loadImg != null)
					loadImg.loadImg(view, loading, fail, p + value.toString()
							+ s);
			}
			break;
		case ADAPTER:
			try {
				if (value instanceof Adapter && view instanceof AdapterView) {
					((AdapterView<Adapter>) view).setAdapter((Adapter) value);
				}
				if (value instanceof PagerAdapter && view instanceof ViewPager) {
					((ViewPager) view).setAdapter((PagerAdapter) value);
				}
			} catch (Exception e) {
			}
			break;
		case NULL:
			break;
		default:
			break;
		}
	}

	private AVLib() {
	}
}