# Customs (Spring Boot + Thymeleaf + Bootstrap 5)

五步驟：1. 到貨通知、2. 報關文件彙整、3. 貨物報關、4. 入廠驗收、5. 帳務管理  
含：表單驗證、檔案上傳（本機檔案系統）、JPA(H2)、Spring Security 角色。

## 啟動
```bash
mvn spring-boot:run   # 或 mvn -DskipTests package && java -jar target/*.jar
```
- 瀏覽：`http://localhost:8080/flow`
- 帳號：
  - admin/admin123
  - buyer/buyer123
  - broker/broker123
  - wh/wh123
  - acct/acct123
- H2 Console：`/h2-console` （JDBC URL: `jdbc:h2:mem:testdb`）

## 部署到 Render（Buildpack）
- Build Command: `mvn -DskipTests package`
- Start Command: `java -jar target/customs-thymeleaf-0.0.2-SNAPSHOT.jar`
- Health Check Path: `/login` 或 `/flow`
- `server.port=${PORT:8080}` 已在 `application.properties`
