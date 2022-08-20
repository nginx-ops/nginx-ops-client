package io.github.nginx.ops.client.comm.handler;

import io.github.nginx.ops.client.comm.domain.vo.R;
import io.github.nginx.ops.client.comm.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lihao3
 * @since 2022/4/25
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

  /** 自定义异常抛出 */
  @ExceptionHandler(BusinessException.class)
  public R businessExceptionHandler(BusinessException e) {
    R r = R.error(e.getCode());
    log.warn("业务异常警告, 异常编码为:{}, 异常信息为:{}", r.getCode(), r.getMessage());
    return r;
  }
}
