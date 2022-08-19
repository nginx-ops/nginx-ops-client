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
public class TestConfDTO implements Serializable {

  /** nginx执行脚本地址 */
  private String nginxSbinPath;
}
