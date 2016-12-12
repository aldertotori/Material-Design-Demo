# 使用Material Design风格的ToolBar和抽屉导航

> 先看个简单的运行效果

![run](http://upload-images.jianshu.io/upload_images/2833342-7cb85e6bd3894961.gif?imageMogr2/auto-orient/strip)

## 主要记录下布局的写法

---

### 1 用到的Google Design依赖和V7包依赖

    compile 'com.android.support:cardview-v7:25.0.0'
    compile 'com.android.support:recyclerview-v7:25.0.0'
    compile 'com.android.support:design:25.0.0'
    compile 'com.android.support:appcompat-v7:25.0.0'

### 2 主布局结构

![主布局结构](http://upload-images.jianshu.io/upload_images/2833342-c6ec57fb3b7378b2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 3 主布局内容
    <android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/drawer_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <FrameLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent">
    
            <!--CoordinatorLayout 布局属性:FrameLayout-->
            <android.support.design.widget.CoordinatorLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent">
    
                <!--AppBar  布局属性:LinearLayout 默认子元素垂直排列-->
                <android.support.design.widget.AppBarLayout
                    android:id="@+id/appbar"
                    android:layout_width="match_parent"
                    android:layout_height="200dp"
                    android:fitsSystemWindows="true">
    
                    <!--提供了一个可以折叠的Toolbar 布局属性:FrameLayout-->
                    <android.support.design.widget.CollapsingToolbarLayout
                        android:layout_width="match_parent"
                        android:layout_height="match_parent"
                        app:layout_scrollFlags="scroll|exitUntilCollapsed">
    
                        <View
                            android:layout_width="match_parent"
                            android:layout_height="match_parent"
                            android:background="@color/colorPrimary"
                            app:layout_collapseMode="parallax"
                            app:layout_collapseParallaxMultiplier="0.5" />
    
                        <android.support.v7.widget.Toolbar
                            android:id="@+id/toolbar"
                            android:layout_width="match_parent"
                            android:layout_height="?android:attr/actionBarSize"
                            app:contentInsetStart="0dp"
                            app:layout_collapseMode="pin"
                            app:title="@string/app_name" />
    
                    </android.support.design.widget.CollapsingToolbarLayout>
    
                </android.support.design.widget.AppBarLayout>
    
                <android.support.v7.widget.RecyclerView
                    android:id="@+id/recycler"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:background="@color/divider"
                    app:layout_behavior="@string/appbar_scrolling_view_behavior" />
    
            </android.support.design.widget.CoordinatorLayout>
    
            <!--Float Button-->
            <android.support.design.widget.FloatingActionButton
                android:id="@+id/float_btn"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="bottom|end"
                android:layout_marginBottom="10dp"
                android:layout_marginEnd="10dp"
                android:src="@drawable/ic_action_important"
                app:elevation="10dp" />
    
        </FrameLayout>
    
        <!--左侧抽屉-->
        <android.support.design.widget.NavigationView
            android:id="@+id/navigation"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            app:headerLayout="@layout/layout_navigation_header_main"
            app:menu="@menu/main_navigation">
    
        </android.support.design.widget.NavigationView>
    </android.support.v4.widget.DrawerLayout>

### 4 抽屉导航头布局内容 (layout_navigation_header_main.xml)
    <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:background="@color/colorAccent">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:drawablePadding="5dp"
            android:drawableStart="@mipmap/ic_launcher"
            android:gravity="center"
            android:text="@string/hello"
            android:textColor="@color/icons"
            android:textSize="20sp" />
    </FrameLayout>

### 5 界面逻辑很简单，给RecyclerView绑定数据，抽屉菜单的打开关闭(MainActivity内容)

    public class MainActivity extends AppCompatActivity {
        private final String[] data = {"Hello,Word!", 
                "Hello,Android!", "Hello,Material design!",
                "Hello,Word!", "Hello,Android!", "Hello,Material design!",
                "Hello,Word!", "Hello,Android!", "Hello,Material design!",
                "Hello,Word!", "Hello,Android!", "Hello,Material design!",
                "Hello,Word!", "Hello,Android!", "Hello,Material design!"};
    
        private FloatingActionButton floatBtn;
        private DrawerLayout drawerLayout;
    
        @Override
        protected void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            //设置ToolBar作为ActionBar
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                //设置ActionBar左上角按钮
                actionBar.setHomeAsUpIndicator(R.drawable.ic_nav);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);
            floatBtn = (FloatingActionButton) findViewById(R.id.float_btn);
            floatBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Float Button", Toast.LENGTH_SHORT).show();
                }
            });
    
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
            MyAdapter myAdapter = new MyAdapter();
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.main_menu, menu);
            return super.onCreateOptionsMenu(menu);
        }
    
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawers();
                    } else {
                        drawerLayout.openDrawer(GravityCompat.START);
                    }
                    break;
                case R.id.menu_1:
                    Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                    break;
            }
            return super.onOptionsItemSelected(item);
        }
    
        private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    
            class ViewHolder extends RecyclerView.ViewHolder {
                private TextView tv;
    
                ViewHolder(View itemView) {
                    super(itemView);
                    tv = (TextView) itemView.findViewById(R.id.tv);
                }
    
            }
    
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
                return new ViewHolder(view);
            }
    
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                holder.tv.setText(data[holder.getAdapterPosition()]);
            }
    
            @Override
            public int getItemCount() {
                return data.length;
            }
        }
    }

### 6 总结
1. Google提供的抽屉菜单(NavigationView)确实方便了许多，可以直接通过设置Menu资源作为抽屉菜单的菜单项，可以直接设置layout作为抽屉菜单的头部
2. 可折叠的ToolBar
(**CoordinatorLayout + AppBarLayout + CollapsingToolbarLayout + Toolbar **)使嵌套滚动的实现变得容易
3. 浮动按钮(**FloatActionButton**)也是方便了实现material design风格