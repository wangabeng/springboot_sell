package com.immoc.sell.utils;

import com.immoc.sell.VO.ResultVO;

public class ResultVOUtil {
	// 返回成功 传入数据
	public static ResultVO success(Object object) {
		ResultVO resultVO = new ResultVO();
		resultVO.setData(object);
		resultVO.setCode(0);
		resultVO.setMsg("成功");

		return resultVO;
	}

	// 不传入数据
	public static ResultVO success() {
		return success(null);
	}

	// 返回错误信息
	public static ResultVO error(Integer code, String msg) {
		ResultVO resultVO = new ResultVO();
		resultVO.setCode(code);
		resultVO.setMsg(msg);

		return resultVO;
	}
}
