package com.itheima;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

//Ϊ�˲����ٶȼӿ죬������һ��ע�ͽ�������
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
     * ����jwt�㷨
     */
    @Test
    public void testGenJwt(){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","fuyu");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "itheima") //ǩ���㷨
                .setClaims(claims) //�Զ������ݣ��غɣ�
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //������Ч����1��Сʱ
                .compact();
        System.out.println(jwt);

    }

    /**
     * ����JWT
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
