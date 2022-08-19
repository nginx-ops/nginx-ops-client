package io.github.nginx.ops.client.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lihao3
 * @date 2022/8/19
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoCommDTO implements Serializable {

  /** 配置类型 */
  private String type;
  /** 字段标题 */
  private String name;
  /** 字段内容 */
  private String value;
  /** 顺序 */
  private Integer order;
  /** 备注 */
  private String remark;
}
