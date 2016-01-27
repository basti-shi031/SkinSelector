# ZHY无侵入式更换皮肤框架

## 总体分析
* 项目结构

![项目结构](http://7xpvut.com1.z0.glb.clouddn.com/zhy1.png)

* 分析
  * attr包
    * SkinAttr
    * SkinAttrSupport
    * SkinAttrType
    * SkinView 记录View和View样式的类
  * callback包
    * ISkinChangedListener
    * ISkinChangingCallback
  * constant包
    * SkinConfig 存储一些常量
  * utils包
    * L Log工具类
    * PreUtils Sp工具类
  * ResourceManager
  * SkinManager

## 使用  
### 第一步：初始化
```java
SkinManager.getInstance().init(this);
```
获取皮肤有两种途径
1. 本地资源获取，即打包apk时就已经包含的资源
2. 插件下载

我们先看```本地资源获取```：

```init```方法中通过sp获取上次使用的皮肤名称，即
```java
SkinManager.getInstance().changeSkin("skinName");
```
中的```skinName```

### 第二步：注册

在需要使用更换皮肤功能的```Activity```中调用
```java
SkinManager.getInstance().register(this);
```

SkinManager类中保存着一个Activity队列，每次有Activity被注册时，就将Activity加入到队列中，然后调用apply()方法。

```java
public void apply(Activity activity)
{
    List<SkinView> skinViews = SkinAttrSupport.getSkinViews(activity);
    if (skinViews == null) return;
    for (SkinView skinView : skinViews)
    {
        skinView.apply();
    }
}
```
其中
```java
SkinAttrSupport.getSkinViews(activity);
```
方法根据Activity找到Content元素，并递归根据下xml布局文件中设置的tag返回所有需要更换皮肤的View。这里的SkinView是一个保存着一个View和View样式的类。
我们来看将SkinView加入到skinviews的方法
```java
public static void addSkinViews(View view, List<SkinView> skinViews)
 {
     SkinView skinView = getSkinView(view);
     if (skinView != null) skinViews.add(skinView);

     if (view instanceof ViewGroup)
     {
         ViewGroup container = (ViewGroup) view;

         for (int i = 0, n = container.getChildCount(); i < n; i++)
         {
             View child = container.getChildAt(i);
             addSkinViews(child, skinViews);
         }
     }
 }
```
getSkinView(View)方法根据tag获取到需要换肤的View，然后调用changeViewTag(view)

### 第三步：修改皮肤主题
```java
SkinManager.getInstance().changeSkin("day");
```
changeSkin方法内部主要做了两件事情，一件事为修改存储内容如修改上次使用的主题名称，另一件事是调用notifyChangedListeners(),该方法中还是调用了apply()方法，将SkinManager中的mActivities队列中的activity逐个进行主题的切换

### 第四步：反注册
很简单，即直接在mActivities队列中删除该activity。

## 原理
总的分析下来，原理还是比较清晰简单的：应用全局中有一个队列保存着所有需要更换主题的Activity，当其中一个Activity更换主题时，遍历该队列将所有Activity的主题样式都设置完成。当有新的Activity注册时，也使用以保存的主题。
