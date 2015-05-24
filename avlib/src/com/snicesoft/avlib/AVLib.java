package com.snicesoft.avlib;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.Fragment;
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
@SuppressLint("SimpleDateFormat")
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

	private static void bindValue(Object data, Object holder, Field field)
			throws IllegalAccessException {
		Object value = field.get(data);
		if (value == null)
			return;
		DataBind dataBind = field.getAnnotation(DataBind.class);
		if (dataBind != null) {
			int vid = dataBind.id();
			View view = getView(holder, vid);
			if (view != null)
				setValue(view, new ViewValue(value, dataBind));
		}
	}

	/**
	 * 数据绑定
	 * 
	 * @param data
	 * @param holder
	 */
	public static <H extends IHolder, D extends IData> void dataBind(D data,
			H holder) {
		dataBindAll(data, holder);
	}

	private static void dataBind(Object data, Object holder, Class<?> clazz) {
		Field[] dataFields = clazz.getDeclaredFields();
		if (dataFields != null && dataFields.length > 0) {
			for (Field field : dataFields) {
				if (field.getAnnotation(DataBind.class) == null)
					continue;
				try {
					field.setAccessible(true);
					bindValue(data, holder, field);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 数据绑定到view
	 * 
	 * @param data
	 * @param holder
	 */
	private static void dataBindAll(Object data, Object holder) {
		if (data == null || holder == null)
			return;
		try {
			Class<?> clazz = data.getClass();
			dataBind(data, holder, clazz);
			if (isNotObject(clazz)) {
				clazz = clazz.getSuperclass();
				dataBind(data, holder, clazz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static <D extends IData, H extends IHolder> void dataBindTo(D data,
			H holder, String fieldName) {
		if (data == null || holder == null || TextUtils.isEmpty(fieldName))
			return;
		try {
			Class<?> clazz = data.getClass();
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			bindValue(data, holder, field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (isNotObject(data.getClass())) {
			try {
				Class<?> clazz = data.getClass().getSuperclass();
				Field field = clazz.getDeclaredField(fieldName);
				field.setAccessible(true);
				bindValue(data, holder, field);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 通过Id查找Holder的view
	 * 
	 * @param holder
	 * @param vid
	 * @return
	 */
	private static View getView(Object holder, int vid) {
		View v = null;
		Class<?> clazz = holder.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.getAnnotation(Id.class) == null)
				continue;
			try {
				field.setAccessible(true);
				v = (View) field.get(holder);
				Id resource = field.getAnnotation(Id.class);
				if (resource != null && resource.value() == vid) {
					return v;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		v = null;
		if (isNotObject(clazz)) {
			fields = clazz.getSuperclass().getDeclaredFields();
			for (Field field : fields) {
				if (field.getAnnotation(Id.class) == null)
					continue;
				try {
					field.setAccessible(true);
					v = (View) field.get(holder);
					Id resource = field.getAnnotation(Id.class);
					if (resource != null && resource.value() == vid) {
						return v;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return v;
	}

	/**
	 * 初始化Activity中的View
	 * 
	 * @param av
	 *            Activity本身就符合Holder的规范
	 */
	public static void initHolder(Activity av) {
		initHolderAll(av, new ViewFinder(av));
	}

	public static void initHolder(Fragment fragment) {
		initHolderAll(fragment, new ViewFinder(fragment.getView()));
	}

	public static <H extends IHolder> void initHolder(H holder, Activity av) {
		initHolderAll(holder, new ViewFinder(av));
	}

	public static <H extends IHolder> void initHolder(H holder, Fragment fa) {
		initHolderAll(holder, new ViewFinder(fa.getView()));
	}

	public static <H extends IHolder> void initHolder(H holder, View view) {
		initHolderAll(holder, new ViewFinder(view));
	}

	private static void initHolder(Object holder, ViewFinder finder,
			Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		if (fields != null && fields.length > 0)
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
	}

	private static void initHolderAll(Object holder, ViewFinder finder) {
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

	private static boolean isNotObject(Class<?> clazz) {
		return clazz.getSuperclass() != Object.class;
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
			if (value instanceof Adapter && view instanceof AdapterView) {
				((AdapterView<Adapter>) view).setAdapter((Adapter) value);
			}
			if ("android.support.v4.view.PagerAdapter".equals(value.getClass()
					.getName())
					&& "android.support.v4.view.ViewPager".equals(view
							.getClass().getName())) {
				((ViewPager) view).setAdapter((PagerAdapter) value);
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