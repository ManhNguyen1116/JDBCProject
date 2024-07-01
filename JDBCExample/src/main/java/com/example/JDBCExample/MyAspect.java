package com.example.JDBCExample;

import java.sql.Connection;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {
	
	Connection conn = null;
	
	@Before("execution(* com.example.JDBCExample.AllLogging.message1() )")
	public void startFirst() {
		System.out.println("start before logic");
	}
	
	@After("execution(* com.example.JDBCExample.AllLogging.message2() )")
	public void ExitCode() {
		System.out.println("start after logic");
	}
	
	@Around("execution(* com.example.JDBCExample.AllLogging.series() )")
	public void runningCode(ProceedingJoinPoint joinpoint) {
		for(int i = 1; i <= 10; i++) {
			System.out.println("working with logic");
			try {
				joinpoint.proceed();
			} catch(Throwable e) {
				e.printStackTrace();
			}
		}
	}
}
