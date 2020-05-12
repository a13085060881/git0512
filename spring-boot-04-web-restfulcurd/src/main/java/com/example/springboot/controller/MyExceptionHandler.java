package com.example.springboot.controller;

import com.example.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ControllerAdvice：表示这是一个增强的 Controller，是由SpringMVC提供的的功能
 * 使用这个 Controller ，可以实现三个方面的功能：
 *      1、全局异常处理
 *      2、全局数据绑定
 *      3、全局数据预处理
 */
@ControllerAdvice
public class MyExceptionHandler {

    /**
     * @ExceptionHandler：统一处理某一类异常，从而能够减少代码重复率和复杂度
     * 该注解作用对象为方法，并且在运行时有效，value()可以指定异常类
     * @param e
     * @return
     */
    /*@ExceptionHandler(UserNotExistException.class)
    public String handlerException(Exception e){

    }*/
}
