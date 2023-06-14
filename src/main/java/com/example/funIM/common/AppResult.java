package com.example.funIM.common;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 通用返回对象
 */
@Data
@AllArgsConstructor
//@ApiModel(value = "返回值", description = "通用返回")
public class AppResult<T> {
//	@ApiModelProperty(value = "状态")
	private Boolean success;
//	@ApiModelProperty(value = "状态码 1正常 0失败")
	private Integer code;
//	@ApiModelProperty(value = "提示信息")
	private String message;
//	@ApiModelProperty(value = "响应数据")
	private T data;

	// 操作成功
	private static final Integer SUCCESS = 200;
	// 操作失败
	private static final Integer FAIL = 500;

	private static final String MESSAGE = "操作成功";

	private static final String ERROR = "未知错误";

	public AppResult(boolean success,Integer code, String message) {
		this(success,code, message,null);

	}

	public AppResult(boolean success, Integer code, String message, T data) {
		this.success = success;
		this.code = code;
		this.message = message;
		this.data = data;

	}

	// 自定义code返回
	public static <T> AppResult<T> data(Integer code, T data, String message) {
		return new AppResult<>(true,code, message, data);

	}

	// 成功无返回
	public static <T> AppResult<T> success() {
		return new AppResult<>(true,SUCCESS, MESSAGE);

	}

	// 自定义成功数据
	public static <T> AppResult<T> success(String message) {
		return new AppResult<>(true, SUCCESS, message, null);
	}

	// 自定义成功数据和提示信息
	public static <T> AppResult<T> success(T data, String message) {
		return new AppResult<>(true,SUCCESS, message, data);

	}

	// 自定义程序成功提示
	public static <T> AppResult<T> message(String message) {
		return new AppResult<>(true,SUCCESS, message);

	}

	// 自定义程序失败提示
	public static <T> AppResult<T> error() {
		return new AppResult<>(false,FAIL, MESSAGE);

	}
	public static <T> AppResult<T> error(String message) {
		return new AppResult<>(false,FAIL, message);

	}

	public static <T> AppResult<T> error(Integer code,String message) {
		return new AppResult<>(false,code, message);

	}

	// 设置结果，形参为结果枚举
//	public static <T> AppResult<T> setResult(ResultCodeEnum result) {
//		return new AppResult(result.getSuccess(),result.getCode(),result.getMessage());
//
//	}

}