package io.github.nginx.ops.client.domain.dto;

import lombok.AllArgsConstructor;
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
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConfInfoServerDTO {

  /** 监听域名 */
  private String serverName;
  /** 监听IP */
  private String ip;
  /** 监听端口 */
  private Integer port;
  /** 服务类型 */
  private String type;
  /** 证书表ID */
  private String certificateId;
  /** 是否开启证书 */
  private Boolean isSsl;
  /** 是否开启http2 */
  private Boolean isHttp2;
  /** 是否开启http跳转https */
  private Boolean httpToHttps;
  /** http跳转https的ip */
  private String httpIp;
  /** http跳转https的端口 */
  private Integer httpPort;
  /** 是否启用 */
  private Boolean isEnable;
  /** 顺序 */
  private Integer order;
  /** 额外参数 */
  private List<ConfInfoCommDTO> confInfoCommDtoList;
  /** 服务明细列表 */
  private List<ConfInfoServerItemDTO> confInfoServerItemDTOList;
}
