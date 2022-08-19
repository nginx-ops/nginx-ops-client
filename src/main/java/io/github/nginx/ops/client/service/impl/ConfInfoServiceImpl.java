package io.github.nginx.ops.client.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RuntimeUtil;
import com.github.odiszapc.nginxparser.NgxBlock;
import com.github.odiszapc.nginxparser.NgxConfig;
import com.github.odiszapc.nginxparser.NgxDumper;
import com.github.odiszapc.nginxparser.NgxParam;
import io.github.nginx.ops.client.domain.dto.GenerateConfDTO;
import io.github.nginx.ops.client.domain.dto.RunConfDTO;
import io.github.nginx.ops.client.domain.dto.TestConfDTO;
import io.github.nginx.ops.client.domain.vo.ConfInfoItemVO;
import io.github.nginx.ops.client.domain.vo.ConfInfoVO;
import io.github.nginx.ops.client.service.ConfInfoService;
import io.github.nginx.ops.client.util.NginxConfUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lihao3
 * @date 2022/8/19
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfInfoServiceImpl implements ConfInfoService {

  @Override
  public ConfInfoVO generate(GenerateConfDTO dto) {
    StopWatch stopWatch = new StopWatch("生成配置文件");
    stopWatch.start("初始化nginx conf实体类");
    NgxConfig ngxConfig = new NgxConfig();
    stopWatch.stop();
    stopWatch.start("装配基本参数");
    dto.getMainConfList()
        .forEach(
            item -> {
              NgxParam ngxParam = new NgxParam();
              ngxParam.addValue(
                  item.getName().trim() + NginxConfUtils.SPACE + item.getValue().trim());
              ngxConfig.addEntry(ngxParam);
            });
    stopWatch.stop();
    if (ObjectUtil.isNotEmpty(dto.getHttpConfList())) {
      stopWatch.start("装配HTTP参数");
      NgxBlock ngxBlockHttp = new NgxBlock();
      ngxBlockHttp.addValue("http");
      dto.getHttpConfList()
          .forEach(
              item -> {
                NgxParam ngxParam = new NgxParam();
                ngxParam.addValue(
                    item.getName().trim() + NginxConfUtils.SPACE + item.getValue().trim());
                ngxBlockHttp.addEntry(ngxParam);
              });
      stopWatch.stop();
    }
    if (ObjectUtil.isNotEmpty(dto.getStreamConfList())) {
      stopWatch.start("装配stream参数");
      NgxBlock ngxBlockHttp = new NgxBlock();
      ngxBlockHttp.addValue("stream");
      dto.getStreamConfList()
          .forEach(
              item -> {
                NgxParam ngxParam = new NgxParam();
                ngxParam.addValue(
                    item.getName().trim() + NginxConfUtils.SPACE + item.getValue().trim());
                ngxBlockHttp.addEntry(ngxParam);
              });
      stopWatch.stop();
    }
    // 判断是否需要分割文件
    stopWatch.start("生成配置文件");
    List<ConfInfoItemVO> confInfoItemList = new ArrayList<>();
    if (Boolean.TRUE.equals(dto.getDecompose())) {
      dto.getConfInfoServerDtoList()
          .forEach(
              item -> {
                NgxConfig serverNginxConf = new NgxConfig();
                NgxBlock ngxEntries = NginxConfUtils.buildBlockServer(item);
                serverNginxConf.addEntry(ngxEntries);
                String serverConf = new NgxDumper(serverNginxConf).dump();
                String tempConfFile =
                    NginxConfUtils.createTempConfFile(serverConf, item.getServerName());
                NgxParam ngxParam = new NgxParam();
                ngxParam.addValue("include" + NginxConfUtils.SPACE + tempConfFile);
                ngxConfig.addEntry(ngxParam);
                confInfoItemList.add(
                    ConfInfoItemVO.builder()
                        .name(item.getServerName() + ".conf")
                        .content(serverConf)
                        .build());
              });
    } else {
      dto.getConfInfoServerDtoList()
          .forEach(
              item -> {
                NgxBlock ngxEntries = NginxConfUtils.buildBlockServer(item);
                ngxConfig.addEntry(ngxEntries);
              });
    }
    String nginxConf = new NgxDumper(ngxConfig).dump();
    NginxConfUtils.createTempConfFile(nginxConf, "nginx");
    stopWatch.stop();
    stopWatch.start("装配返回");
    ConfInfoVO confInfoVO =
        ConfInfoVO.builder().content(nginxConf).confInfoItemVOList(confInfoItemList).build();
    stopWatch.stop();
    return confInfoVO;
  }

  @Override
  public String test(TestConfDTO dto) {
    String result =
        RuntimeUtil.execForStr(
            dto.getNginxSbinPath() + " -t -c " + NginxConfUtils.TMP_NGINX_CONF_PATH);
    if (!result.contains("successful")) {
      throw new BusinessException(result);
    }
      FileUtil.getLineSeparator()
    return result;
  }

  @Override
  public String run(RunConfDTO dto) {
      Fileu
    String result =
        RuntimeUtil.execForStr(
            dto.getNginxSbinPath() + " -t -c " + NginxConfUtils.TMP_NGINX_CONF_PATH);
    if (!result.contains("successful")) {
      throw new BusinessException(result);
    }
    return result;
  }
}
