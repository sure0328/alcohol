package com.sinolease.base.entity;

/**
 * Created by AlbertLy on 2016/11/15.
 */
public class ErrorCode {

    /**
     * 基本错误类型定义
     */
    public static final int FAIL = -1;
    public static final int SUCCESS = 0;

    public static final int SERVICE_ERROR = 101;
    public static final int INTERNAL_ERROR = 102;

    public static final int USER_EXIST_ERROR=201;
    public static final String DES201ERROR="用户已存在";
    public static final int USER_ID_EXIST_ERROR=202;
    public static final String DES202ERROR="工号已存在";
    public static final int TITLE_NAME_EXIST_ERROR=203;
    public static final String DES203ERROR="职位名称已存在";
    public static final int DEPART_NAME_EXIST_ERROR=204;
    public static final String DES204ERROR="部门名称已存在";
    public static final int PSAAWORD_FAIL_ERROR=205;
    public static final String DES205ERROR="密码错误";
    public static final int CHANGE_PSAAWORD_FAIL_ERROR=206;
    public static final String DES206ERROR="新密码与确认密码不一致";

    public static final int FTP_EXIST_ERROE=301;
    public static final int PROVISION_EXIST_ERROR=302;
    public static final int RATE_EXIST_ERROR=303;


}
