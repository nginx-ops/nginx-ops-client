package io.github.nginx.ops.client.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author lihao3
 * @date 2022/8/18
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoVO implements Serializable {

  /** 配置文件 */
  private String content;
  /** 按照域名拆分文件列表 */
  private List<ConfInfoItemVO> confInfoItemVOList;
}
