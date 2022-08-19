package io.github.nginx.ops.client.comm.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author lihao3
 * @since 2022/4/25
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("通用返回实体类")
public class R<T> implements Serializable {

  public static final String SUCCESS = "200";

  /** 编码 */
  @ApiModelProperty("返回编码(200代表成功)")
  private String code;
  /** 内容 */
  @ApiModelProperty("提示内容")
  private String msg;
  /** 反馈时间 */
  @ApiModelProperty("反馈时间")
  private long time;
  /** 返回数据 */
  @ApiModelProperty("返回数据")
  private T data;
  /** 总条数 */
  @ApiModelProperty("总条数")
  private long count;

  public static R success(String msg) {
    return R.builder().code(SUCCESS).msg(msg).time(System.currentTimeMillis()).count(0L).build();
  }

  public static <T> R<T> success(String msg, T data) {
    return (R<T>)
        R.builder()
            .code(SUCCESS)
            .msg(msg)
            .time(System.currentTimeMillis())
            .data(data)
            .count(data instanceof Collection ? ((Collection<?>) data).size() : 1L)
            .build();
  }

  public static R error(String code, String msg) {
    return R.builder().code(code).msg(msg).time(System.currentTimeMillis()).build();
  }
}
