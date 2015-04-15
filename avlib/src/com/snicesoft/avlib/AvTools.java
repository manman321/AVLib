package com.snicesoft.avlib;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
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
		void loadImg(String url);
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

	/**
	 * 数据绑定
	 * 
	 * @param data
	 * @param holder
	 */
	public static <H extends IAvHolder, D extends IAvData> void dataBind(D data, H holder) {
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
	 */
	public static void initHolder(Activity av) {
		initAnyHolder(av, new ViewFinder(av));
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

	private static <D extends IAvData> void dataAnyBind(D data, Object holder) {
		Field[] holderFields = holder.getClass().getDeclaredFields();
		if (holderFields != null && holderFields.length > 0)
			for (Field field : holderFields) {
				try {
					field.setAccessible(true);
					Id resource = field.getAnnotation(Id.class);
					if (resource != null) {
						Object view = field.get(holder);
						if (view != null)
							setValue((View) view,
									getViewValue(data, resource.value()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}

	private static <D extends IAvData> ViewValue getViewValue(D data, int vId) {
		Object value = null;
		DataBind dataBind = null;
		if (data == null || vId <= 0)
			return null;
		Field[] fields = data.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				try {
					field.setAccessible(true);
					value = field.get(data);
					dataBind = field.getAnnotation(DataBind.class);
					if (dataBind != null) {
						int resId = dataBind.id();
						if (vId == resId) {
							break;
						} else {
							value = null;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (value == null || dataBind == null)
			return null;
		return avTools.new ViewValue(value, dataBind);
	}

	private static void setValue(View view, ViewValue viewValue) {
		if (view == null || viewValue == null)
			return;
		Object value = viewValue.getValue();
		String p = viewValue.getDataBind().prefix();
		String s = viewValue.getDataBind().suffix();
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
					loadImg.loadImg(p + value.toString() + s);
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