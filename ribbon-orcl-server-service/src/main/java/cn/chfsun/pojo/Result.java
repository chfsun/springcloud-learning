package cn.chfsun.pojo;

import java.io.Serializable;

public class Result<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8490436590907382663L;

	private T data;

	private String message;

	private int code;

	public Result() {
	}

	public Result(T data, String message, int code) {
		this.data = data;
		this.message = message;
		this.code = code;
	}

	public Result(String message, Integer code) {
		this(null, message, code);
	}

	public Result(T data) {
		this(data, "操作成功", 200);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}