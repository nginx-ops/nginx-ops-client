package io.github.nginx.ops.client.comm.exception;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author lihao3
 * @since 2022/4/25
 */
@Getter
public class BusinessException extends RuntimeException {

  /** 异常编号 */
  private final String code;

  public BusinessException(String code) {
    this.code = code;
  }
}
