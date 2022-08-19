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
public class RunConfDTO implements Serializable {

  /** nginx配置文件地址 */
  private String nginxConfPath;
  /** nginx执行脚本命令 */
  private String nginxRunCmd;
}
