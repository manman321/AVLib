# AVLib
## AVLib简介
* AVLib最主要组件是Holder和Data。Holder组件用于简化View的主体（比如Activity、Fragment、Adapter等包含零散View的组件）；Data组件和Holder组件是对应起来的，及一个Holder对应一个Data。目前实现了简单数据和简单View之间的数据自动绑定

##Holder和Data规范

### Holder组件
> * 1、需要实现IAvHolder接口：比如独立的Activity对应的Holder
> * 2、不需要实现IAvHolder接口：比如Activity直接当做Holder使用
> * 3、Holder中的View需要通过@Id注解绑定view

### Data组件
> * 1、需要实现IAvData接口：比如独立的Activity对应的Data
> * 2、Data中的数据需要通过@DataBind对应Holder的View

### 示例
* 1、MainHB：MainActivity的独立Holder和Data
```java
public interface MainHB {
	public class MainHolder implements IAvHolder {
		@Id(value = R.id.textView1, backgroundColor = Color.RED)
		public TextView v;
		@Id(R.id.listView1)
		public ListView listView1;
		public ListAdapter adapter;

		public void setAdapter(ListAdapter adapter) {
			this.adapter = adapter;
			listView1.setAdapter(this.adapter);
		}

		public ListAdapter getAdapter() {
			return adapter;
		}
	}

	public class MainData implements IAvData {
		@DataBind(id = R.id.textView1, pattern = "yyyy-MM-dd HH:mm:ss", prefix = "￥：")
		public String text = "" + System.currentTimeMillis();
	}
}
```
* 2、MainActivity
```java
public class MainActivity extends AvActivity<MainHolder, MainData> implements
		OnItemClickListener {
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AvTools.initHolder(holder, this);
		holder.setAdapter(new ListAdapter(R.layout.item_list));
		holder.listView1.setOnItemClickListener(this);
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// data发生变化
				data.text = System.currentTimeMillis() + "";
				refreshData();
			}
		}, 3000);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(this, "position=" + position, Toast.LENGTH_LONG).show();
	}

	@Override
	public void createHolderAndData() {
		holder = new MainHolder();
		data = new MainData();
	}
}
```
# 关于作者
* Email： <snicesoft@qq.com>
* QQ：[14193330](http://wpa.qq.com/msgrd?v=1&uin=14193330)
