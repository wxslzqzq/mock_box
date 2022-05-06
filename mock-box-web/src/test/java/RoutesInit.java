import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.zhtty.mock.box.Application;
import com.zhtty.mock.box.model.RouteDO;
import com.zhtty.mock.box.model.out.MetaResponse;
import com.zhtty.mock.box.model.out.RoutesResponse;
import com.zhtty.mock.box.repository.RouteRepository;
import com.zhtty.mock.box.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(value = {"classpath:application.properties"})
public class RoutesInit {
    @Autowired
    private RouteRepository routeRepository;

    @Test
    public void initRoutes() throws IOException {
        String path = "/init/routes.json";
        InputStream inputStream = getClass().getResourceAsStream(path);
        log.info("————————开始读取路由配置初始化的文件————————");
        byte[] bytes = StreamUtils.copyToByteArray(inputStream);
        List<RoutesResponse> routesResponseList = JsonUtils.readValue(new TypeReference<List<RoutesResponse>>() {
        }, bytes);
        if (CollectionUtil.isNotEmpty(routesResponseList))
            routesResponseList.forEach(routesResponse -> {
                RouteDO build = RouteDO.builder()
                        .hidden(routesResponse.getHidden())
                        .path(routesResponse.getPath())
                        .component(routesResponse.getComponent())
                        .redirect(routesResponse.getRedirect())
                        .name(routesResponse.getName())
                        .masterId(routesResponse.getParentId())//parentId前端增加容错排序需要
                        .meta(JsonUtils.writeValueAsString(routesResponse.getMeta()))
                        .hideChildrenInMenu(routesResponse.getHideChildrenInMenu())
                        .build();
                build.setId(routesResponse.getId());
                routeRepository.insert(build);
            });


    }
}
