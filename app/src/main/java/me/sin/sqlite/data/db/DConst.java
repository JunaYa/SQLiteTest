package me.sin.sqlite.data.db;

/**
 * Created by sin on 2016/7/15.
 */
public class DConst {
    public static final int CURRENT_VERSION = 2;
    public static final String DATABASE_NAME = "Message.db";

    /**
     * 创建 NOTE 表
     */
    public static final String CREATE_TABLE_NOTE = "create table Notes("
            + "id integer primary key autoincrement"
            + ", title varchar(20)"
            + ", content varchar(400)"
            + ", date varchar(20)"
            + ");";

    /**
     *表里插入字段
     */
    public static final String ALTER_TABLE_NOTE = "alter table notes add tag varchar(20)";

    /**
     * 创建 User 表
     */
    public static final String CREATE_TABLE_USER = "create table User("
            +"id integer primary key autoincrement"
            +", name varchar(20)"
            +", birth varchar(20)"
            +", gender varchar(2)"
            +", mobile varchar(11)"
            +", qq varchar(20)"
            +", GitHub varChar(40)"
            + ");";
}
