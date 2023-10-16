package com.itheima;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

//为了测试速度加快，对下面一行注释进行屏蔽
//@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    void uuidTest() {
        for(int i=0;i<100;i++){
            String uuid= UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

    /**
     * 生成jwt算法
     */
    @Test
    public void testGenJwt(){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","fuyu");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "itheima") //签名算法
                .setClaims(claims) //自定义内容（载荷）
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //设置有效期是1个小时
                .compact();
        System.out.println(jwt);

    }

    /**
     * 解析JWT
     */
    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiZnV5dSIsImlkIjoxLCJleHAiOjE2OTcyMTEwODh9.J9yvYFTLFpF1oMIoGRPJiFogmwuwCOuRDKXS-o9l1-A")
                .getBody();
        System.out.println(claims);
    }

}
