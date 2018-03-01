package com.cc.util;

import com.cc.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JWT的Token工具类
 * @author cyc
 */
@Component
public class JwtTokenUtil {

    /**
     * 该JWT所面向的用户
     */
    static final String CLAIM_KEY_USERNAME = "sub";
    /**
     * 接收该JWT的一方
     */
    static final String CLAIM_KEY_AUDIENCE = "aud";
    /**
     * JWT签发时间
     */
    static final String CLAIM_KEY_CREATED = "iat";

    static final String AUDIENCE_UNKNOWN = "unknown";
    static final String AUDIENCE_WEB = "web";
    static final String AUDIENCE_MOBILE = "mobile";
    static final String AUDIENCE_TABLET = "tablet";

    private Clock clock = DefaultClock.INSTANCE;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 获取ToKen中用户账户
     *
     * @param token JWT的Token字符串
     * @return Date 到期时间
     */
    public String getUserIdFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * 获取ToKen过期时间
     *
     * @param token JWT的Token字符串
     * @return Date 到期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * 从荷载Claim获取信息
     *
     * @param token          jwt字符串
     * @param claimsResolver 获取类型
     * @param <T>            要求返回类型
     * @return 返回类型
     */
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    /**
     * 获取JWT上的荷载
     *
     * @param token JWT字符串
     * @return Claims 荷载信息
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * 检测Token是否过期
     *
     * @param token JWT字符串
     * @return 检测结果
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }

    /**
     * 生成JWT的Token
     * @param userDetails 用户信息
     * @return Token
     */
    public String generateToken(UserDetails userDetails) {
        //设置有效期
        final Date expirationDate = calculateExpirationDate(new Date());
        //创建JWT的Token字符串
        return Jwts.builder().setSubject("" + userDetails.getUsername()).setIssuedAt(new Date()).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    /**
     * 刷新JWT的Token
     * @param token JWT的Token
     * @return 新Token
     */
    public String refreshToken(String token) {
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);
        final Claims claims = getAllClaimsFromToken(token);
        return Jwts.builder().setClaims(claims).setIssuedAt(createdDate).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    /**
     * 验证Token的有效性
     *
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        final String username = getUserIdFromToken(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 计算增加有效期
     * @param createdDate 待增加的日期
     * @return 增加日期结果
     */
    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }
}
