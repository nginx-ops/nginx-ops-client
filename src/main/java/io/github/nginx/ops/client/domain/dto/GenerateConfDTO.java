package io.github.nginx.ops.client.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author lihao3
 * @date 2022/8/19
 */
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GenerateConfDTO implements Serializable {

  /** 是否按照域名分割文件 */
  private Boolean decompose;
  /** 基础参数 */
  private List<ConfInfoCommDTO> mainConfList;
  /** http参数 */
  private List<ConfInfoCommDTO> httpConfList;
  /** stream参数 */
  private List<ConfInfoCommDTO> streamConfList;
  /** server参数 */
  private List<ConfInfoServerDTO> confInfoServerDtoList;
}
