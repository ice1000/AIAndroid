package util;

/**
 * 方便外部调用
 * Copyright 2016(c) Comet Corporation.
 * Created by asus1 on 2016/1/24.
 */
public class T extends BaseActivity.T {
	// 字库，正则表达式
	public static final String SHOULD_BE_SPLIT = "[ \n,]|，|。";
	public static final String SHOULD_BE_DELETE = "[ \n]|，|。";

	// 在SQLite中的
	public static final String DATABASE_NAME = "ice1000.db";
	public static final String TALK_LOG_TABLE = "TALK_LOG_TABLE";
	public static final String KNOWLEDGE = "KNOWLEDGE";
}
