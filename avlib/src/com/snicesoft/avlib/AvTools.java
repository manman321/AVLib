package com.snicesoft.avlib;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.snicesoft.avlib.annotation.DataBind;
import com.snicesoft.avlib.annotation.Id;
import com.snicesoft.avlib.view.ViewFinder;
import com.snicesoft.avlib.widget.IAvData;
import com.snicesoft.avlib.widget.IAvHolder;

/**
 * @author zhu zhe
 * @since 2015年4月15日 上午9:50:38
 * @version V1.0
 */
@SuppressLint("SimpleDateFormat")
public class AvTools {
	private static AvTools avTools;

	private AvTools() {
	}

	static {
		avTools = new AvTools();
	}
	private static LoadImg loadImg;

	public static void setLoadImg(LoadImg loadImg) {
		AvTools.loadImg = loadImg;
	}

	public interface LoadImg {
		void loadImg(View v, int loadingResId, int failResId, String url);
	}

	private static SimpleDateFormat dateFormat = new SimpleDateFormat();

	@SuppressWarnings("unchecked")
	public static <T extends View> T getView(View v, int vId, Class<T> cls) {
		return (T) v.findViewById(vId);
	}

	@SuppressWarnings("unchecked")
	public static <T extends View> T getView(Activity av, int vId, Class<T> cls) {
		return (T) av.findViewById(vId);
	}

	public static <D extends IAvData> void dataBind(D data, Activity activity) {
		dataAnyBind(data, activity);
	}

	public static <D extends IAvData> void dataBind(D data, Fragment fragment) {
		dataAnyBind(data, fragment);
	}

	/**
	 * 数据绑定
	 * 
	 * @param data
	 * @param holder
	 */
	public static <H extends IAvHolder, D extends IAvData> void dataBind(
			D data, H holder) {
		dataAnyBind(data, holder);
	}

	public static <H extends IAvHolder, D extends IAvData> void initHolderAndData(
			H holder, D data, Activity activity) {
		AvTools.initHolder(holder, activity);
		AvTools.dataBind(data, holder);
	}

	/**
	 * 初始化Activity中的View
	 * 
	 * @param av
	 *            Activity本身就符合Holder的规范
	 */
	public static void initHolder(Activity av) {
		initAnyHolder(av, new ViewFinder(av));
	}

	public static void initHolder(Fragment fragment, View view) {
		initAnyHolder(fragment, new ViewFinder(view));
	}

	public static <H extends IAvHolder> void initHolder(H holder, View view) {
		initAnyHolder(holder, new ViewFinder(view));
	}

	public static <H extends IAvHolder> void initHolder(H holder, Activity av) {
		initAnyHolder(holder, new ViewFinder(av));
	}

	private static void initAnyHolder(Object holder, ViewFinder finder) {
		Field[] holderFields = holder.getClass().getDeclaredFields();
		if (holderFields != null && holderFields.length > 0)
			for (Field field : holderFields) {
				try {
					field.setAccessible(true);
					Id resource = field.getAnnotation(Id.class);
					if (resource != null) {
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
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}

	public static <D extends IAvData, H extends IAvHolder> void dataBindByName(
			D data, H holder, String name) {
		try {
			Field field = data.getClass().getDeclaredField(name);
			field.setAccessible(true);
			Object value = field.get(data);
			DataBind dataBind = field.getAnnotation(DataBind.class);
			if (dataBind != null && value != null) {
				int vid = dataBind.id();
				View view = getView(holder, vid);
				if (view != null)
					setValue(view, avTools.new ViewValue(value, dataBind));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 数据绑定到view
	 * 
	 * @param data
	 * @param holder
	 */
	private static void dataAnyBind(Object data, Object holder) {
		Field[] dataFields = data.getClass().getDeclaredFields();
		if (dataFields != null && dataFields.length > 0) {
			for (Field field : dataFields) {
				try {
					field.setAccessible(true);
					Object value = field.get(data);
					DataBind dataBind = field.getAnnotation(DataBind.class);
					if (dataBind != null && value != null) {
						int vid = dataBind.id();
						View view = getView(holder, vid);
						if (view != null)
							setValue(view, avTools.new ViewValue(value,
									dataBind));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
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
		Field[] holderFields = holder.getClass().getDeclaredFields();
		if (holderFields != null && holderFields.length > 0)
			for (Field field : holderFields) {
				try {
					field.setAccessible(true);
					v = (View) field.get(holder);
					Id resource = field.getAnnotation(Id.class);
					if (resource != null && resource.value() == vid) {
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		return v;
	}

	@SuppressWarnings("unchecked")
	private static <T extends View> void setValue(T view, ViewValue viewValue) {
		if (view == null || viewValue == null || viewValue.getValue() == null
				|| viewValue.getDataBind() == null)
			return;
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
				tv.setText(p + value.toString() + s);
			} else {
				dateFormat.applyPattern(pattern);
				Date date = new Date(Long.parseLong(value.toString()));
				tv.setText(p + dateFormat.format(date) + s);
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
			break;
		case NULL:
			break;
		default:
			break;
		}
	}

	private class ViewValue {
		private Object value;
		private DataBind dataBind;

		public DataBind getDataBind() {
			return dataBind;
		}

		public Object getValue() {
			return value;
		}

		public ViewValue(Object value, DataBind dataBind) {
			super();
			this.value = value;
			this.dataBind = dataBind;
		}
	}
}