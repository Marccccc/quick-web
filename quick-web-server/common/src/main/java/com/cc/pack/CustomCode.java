package com.cc.pack;

public enum  CustomCode {

    SUCCESS(200,"成功"),
    TO_JSON_FAILED(4001,"JSON转换失败"),
    ARGUMENT_NOT_VALID(4002,"参数校验未通过"),
    UNAUTHORIZED(4010,"无登录授权"),
    NO_PERMISSOION(4011,"权限不足");

    private int code;
    private String desc;

    CustomCode(int code, String desc) {
        this.code=code;
        this.desc=desc;
    }

    public int getCode(){
        return this.code;
    }

    public String getDesc(){
        return this.desc;
    }

}
