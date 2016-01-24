package util;

/**
 * 方便外部调用
 * Copyright 2016(c) Comet Corporation.
 * Created by asus1 on 2016/1/24.
 */
public class T extends BaseActivity.T {
	// 字库，正则表达式
	public static final String SHOULD_BE_SPLIT = "[\n,;]|；|，|。";
	public static final String SHOULD_BE_DELETE = "[\n;]|；|，|。";

	// 在SQLite中的
	public static final String DATABASE_NAME = "ice1000.db";
	public static final String TALK_LOG_TABLE = "TALK_LOG_TABLE";
	public static final String KNOWLEDGE = "KNOWLEDGE";

	// 暂时没用的
	public static final String MEET_FIRST_TIME = "第一次见，先生您好~";
	public static final String DELETE_FAILED = "删除失败，使用暴力方法删除";
	public static final String SORRY_CANNOT_USE =  "抱歉本功能暂未开放";
	public static final String HELLO_HONEY = "嗨~";
	public static final String NO_MESSAGE_FOUND =  "没有找到消息";


}
