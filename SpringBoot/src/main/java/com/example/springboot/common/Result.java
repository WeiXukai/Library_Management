/**
 * ClassName: Result
 * Package: com.example.springboot.common
 * Description: 通用结果封装类，用于统一接口返回格式。
 *
 * @Author WXK
 * @Create 2025/4/9 16:00
 * @Version 1.0
 */

package com.example.springboot.common;

/**
 * 通用接口响应结果封装类（支持泛型）。
 * 封装了响应码、提示信息与返回数据。
 *
 * @param <T> 响应数据的类型
 */
public class Result<T> {

    /**
     * 响应状态码（如："0" 表示成功，"1" 表示失败）
     */
    private String code;

    /**
     * 响应提示信息（如："成功"、"系统异常" 等）
     */
    private String msg;

    /**
     * 响应数据主体（可以是对象、列表、字符串等）
     */
    private T data;

    // ===== Getter / Setter 方法 =====

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // ===== 构造方法 =====

    /**
     * 无参构造方法，通常用于手动设置各个字段
     */
    public Result() {
    }

    /**
     * 含数据参数的构造方法，默认不设置 code 和 msg
     * @param data 返回的数据
     */
    public Result(T data) {
        this.data = data;
    }

    // ===== 工具方法（静态） =====

    /**
     * 返回一个无数据的成功结果
     * @return 封装好的 Result 对象
     */
    public static Result success() {
        Result result = new Result<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    /**
     * 返回一个携带数据的成功结果
     * @param data 响应数据
     * @return 封装好的 Result 对象
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    /**
     * 返回一个错误结果，可指定错误码与错误信息
     * @param code 自定义错误码
     * @param msg  错误提示信息
     * @return 封装好的 Result 对象
     */
    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
