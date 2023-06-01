package com.myshop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	/**
	 * 메서드 정상 결과 반환이후에 동작해야 한다.
	 * point cut의 구성은 아래와 같다.
	 * 리턴 타입: * 이므로 모든 리턴 타입에 대해 적용된다.
	 * 클래스명: com.myshop.dao 패키지로 시작하고 .. 이므로 하위의 모든 경로, 클래스에 적용된다.
	 * 메서드명: memberInsert 이므로 memberInsert 라는 이름을 가진 메서드에 적용된다.
	 * 파라미터: ..이므로 파라미터의 개수, 타입에 상관없이 적용된다.
	 * @param jp JoinPoint를 통해 joinpoint의 다양한 정보에 접근할 수 있다.
	 */
	
	// memberInsert 메소드 이후 로그 출력
	@AfterReturning(value = "execution(* com.myshop.dto..*.memberInsert(..))")
	public void memberInsert(JoinPoint jp) {
		logger.debug("회원 가입 성공!! 전달 파라미터:{}",Arrays.toString(jp.getArgs()));
	}
	// memberList 메소드 이후 로그 출력
	@AfterReturning(value = "execution(* com.myshop.dto..*.memberList())")
	public void memberList() {
		logger.debug("회원 정보 목록 로딩 성공!!");
	}
}
