package com.zhtty.mock.box.shiro;

import com.zhtty.mock.box.exception.BizException;
import com.zhtty.mock.box.exception.ExceptionMessageEnum;
import com.zhtty.mock.box.model.UserDO;
import com.zhtty.mock.box.service.ShiroAuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class JWTRealm extends AuthorizingRealm {


    @Autowired
    private ShiroAuthService shiroAuthService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("doGetAuthorizationInfo+" + principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String userNo = JWTUtil.getUserNo(principals.toString());
        UserDO userDO = shiroAuthService.getPrincipal(userNo);
        List<String> userPermissions = shiroAuthService.getPermissions(userDO.getId());
//        // 基于Permission的权限信息
//        info.addStringPermissions(userPermissions);

        return info;
    }

    /**
     * 使用JWT替代原生Token
     *
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();

        String userNo = JWTUtil.getUserNo(token);

        UserDO userDO = shiroAuthService.getPrincipal(userNo);

        // 用户不存在
        if (userDO == null) {
            throw new BizException(ExceptionMessageEnum.USER_NO_OR_PASSWORD_IS_ERROR);
        }
        // 密码验证成功则返回实例
        if (JWTUtil.verify(token, userNo, userDO.getPassword())) {
            return new SimpleAuthenticationInfo(token, token, "realm");
        }
        throw new BizException(ExceptionMessageEnum.USER_NO_OR_PASSWORD_IS_ERROR);
    }

}