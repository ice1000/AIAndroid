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

# 实现情况：

## 算法(运行方式)部分

现在还只能重复你说的话。。<br/>

- [ ] 起名字(双方)
- [ ] 实现简单的汉字分析
- [ ] 实现汉字语法分析
- [ ] 能完成一些功能性的事务(计算, 出主意, etc.
- [ ] 在和主人的交流中学习
- [ ] 模拟人类情绪o(*^＠^*)o

## UI(视觉效果)部分
- [X] 一个教科书式的冰封APP的标准开始界面
- [X] 对话由RecyclerView组成
- [X] 用户发送的文字在右边，Saber发的文字在左边
- [X] 二人的发言颜色不同
- [ ] 长按弹出窗口并让用户确认删除
- [ ] 能发送颜文字(๑>؂<๑)۶、表情等
- [ ] 能识别颜文字(¯﹃¯).、表情等
- [ ] 简单的动作(以逐帧动画的形式呈现

## 不同类型的数据持久化的规划
+ 聊天记录保存在SQLite中
+ 知识保存在SQLite中
+ 记忆保存在Preference中

# 本次更新 

### 2016年1月6日 1.0.0.5
1. 发送的消息存储在SQLite中
1. 因为SQLite没有boolean类型，所以在[存取的时候](https://github.com/ice1000/AIAndroid/blob/master/app/src/main/java/database/SQLiteManager.java)转换成整数
1. 开启界面增加了[一个逐帧动画](https://github.com/ice1000/AIAndroid/blob/master/app/src/main/res/drawable/saber_shake.xml)

# 历史更新

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