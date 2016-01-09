# 当前暂无已知bug
                                           FBI warning


`federal law provides severe civil and criminal penalties for the unauthorized reproduction,
distribution ,or exhibition of copyrighted motion pictures (title 17,united states code,sections 501 and 508),
the federal bureau of investigation investigates allegations of criminal copyright infringement 
(title 17,united states code,section 506)`


# AIAndroid is:
受[我的友人](https://github.com/Wimacs)的影响 决定开始写的:<br/>
运行在[Android操作系统](http://baike.baidu.com/link?url=Fq6NnG8s-GSI9lQ3EsBQyI0Km0zICP-RA4kNTK4083ZGfQ9CBZ2Ews7V-zlmPulmX6qG7mbMYXpdpayxfPfKf7GAzQxtxLJzVYnVHG47Pw3)上的[人工智能](http://baike.baidu.com/link?url=vd1SM2trGRrWeWv5uerz1PTu1G1PTLQTBgNXTd9bDGoWizhDFFdZlR8KnHpTxZE3eLcJvV0YXs-OwDdvNWHvi6LzMWf5oRnuaj2E1BL7QOq)<br/>

目前[我的友人](https://github.com/Wimacs)正在构建数学模型，我正在做界面。<br/>
图标在我的任性下决定采用Saber~吾王美美哒<br/>
![icon](https://github.com/ice1000/AIAndroid/blob/master/app/src/main/res/mipmap-xhdpi/ic_launcher.png)

这是一个Material Design的项目，就是不知道这个和二次元的微妙结合会有什么样的效果呢<br/>

![233](https://github.com/ice1000/dialogs/blob/master/通用raw/screencut_0x00.png)

# 实现情况：

## 算法(运行方式)部分

这部分还没开始做，是友人在研究。不过他是C++和txt，而我把他的项目翻译成Java和SQLite会很困难。。。<br/>
现在还只能重复你说的话。。<br/>

- [ ] 起名字(双方)
- [ ] 实现简单的汉字分析
- [ ] 实现汉字语法分析
- [ ] 能完成一些功能性的事务(计算, 出主意, etc.
- [ ] 在和主人的交流中学习
- [ ] 模拟人类情绪o(*^＠^*)o
- [ ] 能发送颜文字(๑>؂<๑)۶、表情等
- [ ] 能识别颜文字(¯﹃¯).、表情等
- [ ] 简单的动作(以逐帧动画的形式呈现

## UI(视觉效果)部分
- [X] 一个教科书式的冰封APP的标准开始界面
- [X] 对话由RecyclerView组成
- [X] 用户发送的文字在右边，Saber发的文字在左边
- [X] 二人的发言颜色不同
- [X] 长按弹出窗口并让用户确认删除
- [X] 触摸消息背景颜色改变
- [X] 沉浸式状态栏
- [ ] 自定义消息删除动画

## 不同类型的数据持久化的规划
+ 聊天记录保存在SQLite中
+ 知识保存在SQLite中
+ 个人信息保存在Preference中

# 本次更新 

### 2016年1月10日 1.0.2.1
1. 修复了昨晚上发现的bug
1. 更改弹出窗口的布局
1. 增加清空记录时背景色的检查
1. 上次push忘记改版本号了。。。
1. 现在主脑正式封装完毕，MainActivity只负责更新UI、和主脑通信，主脑负责数据的处理。和SQLite的通信也交由主脑全权负责。这特么才叫MVC啊！

# 历史更新

### 2016年1月10日凌晨 1.0.2.0 重大更新
1. 改进程序结构，回复的算法被单独提了出来。
1. 让所有调用到主脑的Activity都必须实现BrainUsingActivity。
1. 主脑的控制反转，呼唤Activity更新数据。
1. 修复最新发送的消息无法删除的bug。
1. 更改发送键的大小。
1. 不过还是发现了新bug，不太好描述就不说了。

### 2016年1月10日凌晨 1.0.1.4
1. 修复了bug。现在可以拿出来玩了。
1. 将主界面的menu做成了单独的。
1. 背景颜色变化还有点问题。

### 2016年1月9日 1.0.1.3
1. 增加了刷新。
1. 增加了清空聊天记录。
1. 旧的消息记录已经可以删除了。最新发送的消息记录仍然不能删除！
1. 更改了很多颜色配置。

### 2016年1月8日 1.0.1.2
1. 突然发现重大bug！消息记录删除不掉！吓尿！！
1. 增加了[toolbar](https://github.com/ice1000/AIAndroid/blob/master/app/src/main/res/layout-v11/toolbar.xml)
1. 把fab删了改成toolbar的三个点(朋友的建议)

### 2016年1月8日 1.0.1.1
1. 写了[关于开发者](https://github.com/ice1000/AIAndroid/blob/master/app/src/main/res/layout/activity_about_me.xml)
1. 将本项目源码的github链接放上去了
1. 终于可以删除聊天记录了，修复一大堆bug

### 2016年1月7日 1.0.1.0 重大更新
1. 设置消息触摸监听事件，触摸后背景会变色
1. 各种与UI有关的值的修改
1. 增加了阅读github的Activity，并且内置fab

### 2016年1月6日 1.0.0.6
1. 修改圆角大小为5dp
1. 自动去掉消息末尾换行符和空格

### 2016年1月6日 1.0.0.5
1. 发送的消息存储在SQLite中
1. 因为SQLite没有boolean类型，所以在[存取的时候](https://github.com/ice1000/AIAndroid/blob/master/app/src/main/java/database/SQLiteManager.java)转换成整数
1. 开启界面增加了[一个逐帧动画](https://github.com/ice1000/AIAndroid/blob/master/app/src/main/res/drawable/saber_shake.xml)

### 2016年1月6日 1.0.0.4
1. 实例化了适配器，现在可以互相发送消息了
1. 发送的消息左右对齐
1. 主色调确定为蓝色

### 2016年1月6日 1.0.0.3
1. 初步完成SQLite存储数据的系统。
1. 修改图标格式(体现圆角)。

### 2016年1月5日 1.0.0.2
1. 图标(不知为何采用了吾王)。
1. 简单的界面

### 2016年1月5日 1.0.0.1
1. 开始界面卖个萌。(>▽<)
