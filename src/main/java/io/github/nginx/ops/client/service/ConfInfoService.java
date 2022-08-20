package io.github.nginx.ops.client.service;

import io.github.nginx.ops.client.domain.dto.GenerateConfDTO;
import io.github.nginx.ops.client.domain.dto.ReplaceDTO;
import io.github.nginx.ops.client.domain.dto.RunConfDTO;
import io.github.nginx.ops.client.domain.dto.TestConfDTO;
import io.github.nginx.ops.client.domain.vo.ConfInfoVO;

/**
 * @author lihao3
 * @date 2022/8/19
 */
public interface ConfInfoService {

  /**
   * 生成配置文件
   *
   * @param dto
   * @return
   */
  ConfInfoVO generate(GenerateConfDTO dto);

  /**
   * 测试配置文件
   *
   * @param dto
   * @return
   */
  String test(TestConfDTO dto);

  /**
   * 启用脚本
   *
   * @param dto
   * @return
   */
  String run(RunConfDTO dto);

  /** 替换配置文件 */
  void replace(ReplaceDTO dto);
}
