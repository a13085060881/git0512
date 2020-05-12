package com.example.springboot.exception;

/**
 * 运行时异常
 * 继承自 RuntimeException
 */
public class UserNotExistException extends RuntimeException{
    public UserNotExistException(){
        super("用户不存在");
    }
}
