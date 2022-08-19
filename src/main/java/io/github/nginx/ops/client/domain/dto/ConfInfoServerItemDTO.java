package io.github.nginx.ops.client.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author lihao3
 * @date 2022/8/17
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoServerItemDTO {

  /** 顺序 */
  private Integer order;
  /** 监控路径 */
  private String path;
  /** 监控类型 */
  private String type;
  /** 监控地址 */
  private String value;
  /** 是否携带HOST参数 */
  private Boolean isHeader;
  /** 是否开启websocket */
  private Boolean isWebsocket;
  /** 额外参数 */
  private List<ConfInfoCommDTO> confInfoCommDtoList;
}
