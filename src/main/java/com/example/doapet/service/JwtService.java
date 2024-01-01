package com.example.doapet.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

  private final static String SECRET_KEY = "b5e9df502f278abdc7bbe9c6ef45e21bedce4d115a6a1c0b1f381f5f3b3a08771326908f90c2e1468d0c4ee5b1e0a5b1dfdd0fc651cb1a8c670e3e75bb5389db0393bc5a583f374c810c5069cf237764561f82378e627516d074ff380ede239421e144c8d7839fd151e642362937fe40136f8e3d1a2af762ba9aa7405cdc68b62c3f09e08d707fb732bf547c8a6db6ec2a82f681911367c64c276c48d3c816a3c550e5bd535af75a27d130c351dda45d1ef8f538d219c1211b945fe224fbe02e32b4e7e26a2a94688801f510cbbfd104295fc1410859e62de6234cf9a2ea4bc0f7fcca0ba8463aa0954251f8587ee11644783b4129f9485e4e9fd60c8c140be06aa438e9420107632acfbafbeedeec4189b8594d8bfc28ab2ce0626197b5809b027bc89675e9f4e222e96ab9d501811180c70e14b4a0560a881abf275bbd3780bf8c414c133f2974bf3c963f1856343b10419ffb2ca55603c9ebf6bf095a6f47c79d088a4e8b3ebb5e255f68ae99731d99937c83f3f67878df3fbb8207b45854959ff3b1933d8ba07990ed23170c1a7c07029c808df35632f69508c2567f133d6166e2851587e925eb81ae399e6c3cf9eee7d2b76c057e10fbe898be05414afbf577d8ed5213d5016b6fb23685713a43b42f234d06712495fa3c1e618462274880eff0718fa8ebd634ff5a791fa9d8d588dee8b9feccb80e69c5dd0fc795cacdcb66d749535907870bbcc018bf58528520c1f896759fdeaef50bc3514aaad250af973022dcff897c78970151d487b9d11e1bb9395c2336475f77ab21878f27910cf4e3cea48a59276d023209994ecf6fed8a0d7df2111a524f017c347d11ae15bb397c7e9406c4757390bc479af3046e11fb42e18550f7ebd574649797a20b6c6712da8fd217d4b1565bca95895c0b49a2b07f7f0ca5da2e7367e6893927ac6fe4d0d6103b57fb45a88c4754fadf1ed7e0de14e6b2e0f080fb16c7c6fad1bd6d91225591adcb9e9a74626b856f29f7744ee6da2ce476618b47710c28658c3de5cce41469c31054d82204106a7b75fa3f3b18a317bbca245e7bb01399ad37b8e5fa8b3036c209893dd263f231d36a58913240470fe6e1f45f3168b4d70022a6c041cafbf673ce771669861cff59aa07cdfa6254b44d55a6ae75b43849ff4b1d5ecdb91ffa47c28cdf57a12c120c909d5a67ec09ba601a6767384961ad8a34b621ee32bcb7eacb12afe010f3f37d7ff625c5557510f3fe832ea2c1f71b4e5c2e9480bbae0763c546ffdb917b84eccfd400e6f8db36a7b509abdb4b76000287683d8af90361c31a63ff7a13c6372010e2e523fc3005f9461d50e881644765f780bc7bd48996c6b269f8de3513c3e8ce72e75765af5e67f567d662571948811432a2e39b1bd7a2f70a2dd948863ee13a2e8514f8b367787996de902e96d2287fef3dd8e0d59907f6a280cbc5f22c258b37a9600edbae05609fa0c242730e99f35937ed14550e086602633480d4ded5e01690197b834fa9c5f8d6f028287236cba06952f4fbb3ab0d0c8fcc26409f5d89851e90c00a1ccc48f5e67559de15e2a7da7dd306a68399bfc08a4e3ae76a4c3632bb48e71394f0267664ec5080cc55c10811c0fc0b62daa24476b1d19f43df20c6511435f5b503c9d976077f9defeb78b54dcc304de7e4a702650665fc2a53dd7e3fd24b3cc25c8cdada2a6dd4914d82f563c526328638c519b5951333d51e3a7ca648acd940311ed69dc35883d8c242457c07cc679775041358d5e5b40cd456c78cb54a60d2955aeb6425e9b634854dd374abade9598fbfbcd14fd6361239107544bae33d141a923867a044e86cf93719c66a7c7c25c7b6aa848b687f5eb5e4f98f88de763efd0da422cb513f44d39f47c0352489f5e8ef12dd91ad28fdd48cccc63cdfad0a6bacfae70e519ee45db78b493b0535fe2b471bd517cb739cfbfb286dda86e907f1976a861f0df52d642ac42e874590147deb6f037858807ebb5ec6daefe593f78a223223b02c0b581f2ac32903488332efb8ac5c1b93cf1e5110ecc69fae3b77c0f766a31db7b55341e96b3d06ddfd002fdd70ca8f32f3ff7a967a03efc765c090092e9188dcd3f52758a47d68131bf811f6519b62d84e378a0c55563a5bb37fb0a1cdc67464130f3e1fe1d19a5f14f248709439efc9b62d071d2dfb86474406f6fdf6b2c6601e29de16fa43f012561af109a8dea4b8144361ab498892539e340810deb45f5e559a31e7d47f03be2767c81f5fe0a0d822e34bfa4cf0c4ae0118949df13a7a80e1398365dfd1d0904a9f99851cfc552dc71c5d096626ea50b4fbe603670d77ef327eac5f461d7f7f28e7a7a4eead9ba2a9b04dfc03319f10244e74d9f952b410a0236bd3fa9102a043a11567e0e77cda9c5dd342259ebc9f7a9fd5b34c33680c892833e7bdfa184a4e407f3f03fad3bb9f01f888187b583a1ba58257dc043ce4227f96a8a140862f1df53c61e410b584b6729956a0182da6921328cb07abdc9bcaff6d5d4a014d705a694fc781b99c073c0f3045ecff1f61eb96f187c59a75d5955bbc52edd64e2597a1044a7cb644c8fd1bd4cd324976aef251069cf08e9a6bb814f8060302de2ac78ce8747c7be5d580c3147cc8d1636a9dc0c69f21f3434b7543775bfb956e4e03d7b1a975cacc268aca8a31715c1084a515759ba0d5300f052cf2451c93a82920b895d61ce3302daff356e7a67a198b079ecad323d6baa08a39d7ab0e823895aef710e7783752d1033dbb567ed1ed1a7a469cba619cfabb0f9d1d07a60361dfbe59e967ea88d6c870aa5d44a14e298d983a19737f1ec52a126c388fb7ff1020c89ff39915c33a2be9b5fd2bd7f9589820e320f6d557ac72da9e4f9bd14d";
  
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {

    return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 8))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
  }

  private Claims extractAllClaims(String token) {
    return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
