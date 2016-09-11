# CoreDemo

如你所见，这是鄙人闲暇之余写的小小Demo，用于积累，用于记录好奇心。虽然大部分都是稀奇古怪似乎派不上用场的东西，但是看着自己的代码动了起来还是十分有趣的。

每写一个Demo我都会尽量详细地记录下实现的步骤，如果你能够在其中找点些许乐趣，那便是鄙人无上的光荣了。

## 自绘动画实践——以Tencent OS录音机波形为例
效果图如下：

![](https://raw.githubusercontent.com/DrkCore/CoreDemo/master/doc/img/wave.gif)

想法来源自腾讯Bugly的一篇博文：
[《Android自绘动画实现与优化实战——以Tencent OS录音机波形动画为实例》](http://mp.weixin.qq.com/s?__biz=MzA3NTYzODYzMg==&mid=2653577211&idx=1&sn=2619c7df79f675e45e87891b7eb17669&scene=4#wechat_redirect)

大概的做法就是继承自SurfaceView，以时间作为偏移量不断地绘制正弦曲线，随着SurfaceView刷新和偏移量的变化就能形成动画效果。

具体的实现方法可以查看我的博客：
[Android：自绘动画实践——以Tencent OS录音机波形为例](http://blog.csdn.net/drkcore/article/details/51822818)

## 排序算法的可视化
大部分开发者在踏入程序这条不归路后都有亲手打一遍各种排序的经历，如果说代码是一种信仰的话那这无疑就是我们的朝圣了。鄙人也差不多到这时候了，但好歹作为一名Android开发者多少也要弄得稍微能看一点比较好吧，于是就有这个Demo。

### 最简单的选择排序
![](https://raw.githubusercontent.com/DrkCore/CoreDemo/master/doc/img/ChoseSort.gif)

### 说好的冒泡排序
![](https://raw.githubusercontent.com/DrkCore/CoreDemo/master/doc/img/BubbleSort.gif)

### 插入排序
![](https://raw.githubusercontent.com/DrkCore/CoreDemo/master/doc/img/InsertSort.gif)

GIF看起来比较慢主要是为了演示效果，实际上各种排序的都是瞬间完成的，最后记录每一次变化交由自定义View来显示而已。

由于暂时没时间，其他排序都还在锅里……

## 关于其中用到的库
如果你看过源码就会注意到程序中用到了一些工具类和视图注解等不够清真的东西，前者来自鄙人最近开源的库[CoreMate](https://github.com/DrkCore/CoreMate)，后者来自于本人fork的xUtils3的魔改[xMate](https://github.com/DrkCore/xMate)。
