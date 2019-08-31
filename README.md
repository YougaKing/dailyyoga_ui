# Dailyyoga UI

每日瑜伽 AndroidUI库，国内/海外事业部通用的Ui View，先暂上传至JitPack 

**使用步骤1.** 项目根目录build.gradle

```java
	allprojects {
		repositories {
			maven { url 'https://www.jitpack.io' }
		}
	}
```

**使用步骤2.**添加至dependency

```java
	dependencies {
	        implementation 'com.github.YougaKing:dailyyoga_ui:1.0.0'
	}
```

## 一、AttributeView系列

AttributeView系列支持shape所有属性，和部分selector以及图片着色和图片角度旋转。如有自定义字体TextView直接继承AttributeTextView即可。

shape属性用例以及selector，属性以attr_+shape属性，请自行查阅

```java
    <com.dailyyoga.ui.widget.AttributeTextView
        app:attr_corners_bottomLeftRadius="10dp"       //左下圆角
        app:attr_corners_bottomRightRadius="10dp"      //右下圆角
        app:attr_corners_radius="10dp"                 //圆角
        app:attr_solid_color="@color/selector_red"     //背景色，支持纯色值和selector
        app:attr_stroke_color="@color/selector_red"    //边框色，支持纯色值和selector
        app:attr_stroke_position="top"                 //边框线位置，shae无此属性
        app:attr_stroke_width="2dp"                    //边框宽度
        />
```

图片着色支持drawablePosition和src

```java
//未设置app:attr_drawable_position_tint 既不对图片着色
<com.dailyyoga.ui.widget.AttributeTextView
        android:drawableLeft="@drawable/icon_menu_info_black"  
        android:drawableTop="@drawable/icon_menu_info_black"
        android:drawableRight="@drawable/icon_menu_info_black"
        android:drawableBottom="@drawable/icon_menu_info_black"
        app:attr_drawable_left_tint="#FF00FF"  //和drawableLeft对应
        app:attr_drawable_right_tint="#00FFFF" //和drawableRight对应
        app:attr_drawable_top_tint="#FFFF00"   //和drawableTop对应
        />
//未设置app:attr_src_tint 既不对图片着色        
<com.dailyyoga.ui.widget.AttributeImageView
        android:src="@drawable/icon_menu_info_black"
        app:attr_src_tint="#00FF00"   //和src对应
        />
```



图片角度反转

```java
//以原图为基准创建rotate.xml
<rotate 
    android:drawable="@drawable/icon_menu_info_black" //原图
    android:fromDegrees="180"                         //旋转角度
    android:pivotX="50%"                              //旋转基准点
    android:pivotY="50%"                              //旋转基准点
    />
```

