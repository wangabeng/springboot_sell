package com.immoc.sell.SellExceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.immoc.sell.VO.ResultVO;
import com.immoc.sell.exception.SellException;
import com.immoc.sell.utils.ResultVOUtil;
import com.lly835.bestpay.rest.HttpStatus;

@ControllerAdvice
public class SellExceptionHandler {
	
	@ExceptionHandler(value=SellException.class)
	@ResponseBody
	// @ResponseStatus(HttpStatus.FORBIDDEN)
	public ResultVO handlerSellerException(SellException e) {
		return ResultVOUtil.error(e.getCode(), e.getMessage());
	}
}
