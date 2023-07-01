# Todo List App

Bu bir Todo List yani Yapılacaklar Listesi uygulamasıdır. Uygulamanın backend'i Spring Boot, frontend'i ReactJs kullanılarak geliştirilmiştir.Veritabanı olarak MySQL kullanılmıştır. 
- Proje kullanıcının ilerleyen süreçte yapmak isteyeceği iş ve görevleri not alması ve yaptıklarını görebilmesi amacıyla yapılmıştır.
- Kullanıcı istediği kadar iş veya görev girebilir, bu girdileri silebilir, bitirmiş olduğu işlerin üstünü çizebilir.
## Başlangıç

Bu adımlar, uygulamayı yerel ortamınızda çalıştırmak için gereken adımları içerir.

## Kullanılan Teknolojiler
### Front End :
- Html
- Css
- Axios
- Bootstrap(react-bootstrap)
- ReactJs

### Back End :
- Java
- Spring Boot
- JPA/Hibernate
- Spring Data JPA
- MySQL

### Diğer Araçlar ve Teknolojiler
- Git
- Maven
- Postman
- IntelliJ IDEA
- Visual Studio Code



### Önkoşullar

Aşağıdaki yazılımların sisteminizde yüklü olduğundan emin olun:
- Java Development Kit (JDK) 20 
- Node.js
- MySQL veritabanı
### Kurulum
1. Bu projeyi bilgisayarınıza klonlayın:
### `git clone <repo-url>`

2. Veritabanı ayarlarını yapılandırın:

- 'application.properties' dosyasında, MySQL veritabanına bağlantı ayarlarınızı belirtin. Örneğin:
```bash 
spring.datasource.url=jdbc:mysql://localhost:3306/todoDB
spring.datasource.username=<username>
spring.datasource.password=<password>
```
3. Spring Boot uygulamasını başlatmak için aşağıdaki komutu çalıştırın:
```bash 
./mvnw spring-boot:run
```
4. React uygulamasını başlatmak için yeni bir terminal penceresi açın ve proje dizinine gidin. Ardından aşağıdaki komutu çalıştırın:
```bash 
cd frontend
npm install
npm start
```
5. Tarayıcınızda http://localhost:3000 adresine gidin ve Todo List uygulamasını görmelisiniz.

