package io.github.nginx.ops.client.controller;

import cn.hutool.core.util.ObjectUtil;
import io.github.nginx.ops.client.comm.domain.vo.R;
import io.github.nginx.ops.client.domain.dto.GenerateConfDTO;
import io.github.nginx.ops.client.domain.dto.ReplaceDTO;
import io.github.nginx.ops.client.domain.dto.RunConfDTO;
import io.github.nginx.ops.client.domain.dto.TestConfDTO;
import io.github.nginx.ops.client.domain.vo.ConfInfoVO;
import io.github.nginx.ops.client.domain.vo.FileVo;
import io.github.nginx.ops.client.service.ConfInfoService;
import io.github.nginx.ops.client.util.NginxConfUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lihao3
 * @date 2022/8/17
 */
@Api(tags = "启用配置接口")
@Slf4j
@RestController
@RequestMapping("conf/info")
@RequiredArgsConstructor
public class ConfInfoController {

  private final ConfInfoService service;

  @ApiOperation("生成配置文件")
  @PostMapping("generate")
  public R generate(@RequestBody GenerateConfDTO dto) {
    ConfInfoVO confInfoVO = service.generate(dto);
    return R.success("生成成功!", confInfoVO);
  }

  @ApiOperation("测试配置文件是否正确")
  @PostMapping("test")
  public R test(@RequestBody TestConfDTO dto) {
    return R.success(service.test(dto));
  }

  @ApiOperation("启用配置文件")
  @PostMapping("run")
  public R run(@RequestBody RunConfDTO dto) {
    return R.success(service.run(dto));
  }

  @ApiOperation("替换配置文件")
  @PostMapping("replace")
  public R replace(@RequestBody ReplaceDTO dto) {
    if (ObjectUtil.isEmpty(dto.getNginxConfPath())) {
      dto.setNginxConfPath(NginxConfUtils.PROD_NGINX_CONF_PATH);
    }
    service.replace(dto);
    return R.success("替换成功!");
  }

  @ApiOperation("获取磁盘树")
  @GetMapping("node/list")
  public R<List<FileVo>> nodeList(String pid) {
    File[] fileList = null;
    if (ObjectUtil.isEmpty(pid)) {
      fileList = File.listRoots();
    } else {
      fileList = new File(pid).listFiles();
    }
    List<FileVo> fileVoList = new ArrayList<>();
    if (ObjectUtil.isNotEmpty(fileList)) {
      for (File file : fileList) {
        fileVoList.add(
            FileVo.builder()
                .id(file.getPath())
                .pid(file.getParent())
                .isParent(file.isDirectory())
                .name(file.getName())
                .build());
      }
    }
    return R.success("获取成功!", fileVoList);
  }
}
