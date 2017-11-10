# SmartRefreshLayout

## 1. 在Module下的build.gradle中添加依赖
### Step 1. Add the JitPack repository to your build file
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
### Step 2. Add the dependency
     dependencies {
	        compile 'com.github.Tudie:SmartRefresh:v1.0.0'
	}
	
### Step 3. Java代码设置
    setEnableRefresh 是否开启下拉刷新功能（默认true）
	setEnableLoadmore 是否开启加上拉加载功能（默认true）
	setRefreshHeader(new ClassicsHeader(this));设置 Header 为 ClassicsHeader
    setRefreshFooter(new ClassicsFooter(this));设置 Footer 为 ClassicsFooter
	

### Step 4. xml布局
    <com.scwang.smartrefresh.layout.SmartRefreshLayout
        android:id="@+id/smartLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#ffffff">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="120dp"
            android:padding="10dp"
            android:text="四大皆空法律上的困境发送即可范德萨个" />
	</com.scwang.smartrefresh.layout.SmartRefreshLayout>
	
	<com.scwang.smartrefresh.layout.SmartRefreshLayout
        android:id="@+id/smartLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#ffffff">

     <android.support.v7.widget.RecyclerView
        android:id="@+id/recyclerview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:overScrollMode="never"
        android:background="#fff" />
	</com.scwang.smartrefresh.layout.SmartRefreshLayout>
	
    // xml设置head 和 foot
	<com.scwang.smartrefresh.layout.SmartRefreshLayout
        android:id="@+id/smartLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#ffffff">
        <com.scwang.smartrefresh.layout.header.ClassicsHeader
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
        <TextView
            android:layout_width="match_parent"
            android:layout_height="120dp"
            android:padding="10dp"
            android:text="四大皆空法律上的困境发送即可范德萨个" />
        <com.scwang.smartrefresh.layout.footer.ClassicsFooter
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
    </com.scwang.smartrefresh.layout.SmartRefreshLayout>
    
### Step 5. 代码源于SmartRefreshLayout
    https://github.com/scwang90/SmartRefreshLayout
