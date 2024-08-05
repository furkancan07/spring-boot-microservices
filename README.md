
# Spring Boot Mikroservislerde Security

Bu projeyi mikroservisleri öğrenmek amacıyla geliştirdim. Güvenlik işlemlerini merkezi hale getirmek için tüm güvenlik yönetimini sadece gateway-server üzerinden gerçekleştirdim ve bu güvenliği Spring WebFlux kullanarak sağladım. Diğer servisler, dış dünyaya kapalı olarak tasarlandı ve yalnızca gateway-server aracılığıyla erişilebilir hale getirildi. Ayrıca, bazı servisler arasında iletişim sağlamak için Apache Kafka'yı kullandım. Bu yaklaşım, güvenlik duvarı işlevini gören bir API geçidi kullanarak mikroservislerin her birinin ayrı ayrı güvenlik yapılandırmalarını yapma ihtiyacını ortadan kaldırdı ve sistemin genel güvenliğini artırdı. Kafka'nın kullanımı ise servisler arasındaki veri akışını daha verimli ve güvenilir bir şekilde yönetmeme olanak tanıdı. Projeyi gelecekte daha da geliştirmeyi ve ek özellikler eklemeyi planlıyorum

## Teknolojiler

Bu projede kullanılan ana teknolojiler ve araçlar şunlardır:

- **Docker**: Kafkayı kurmak için kullandım

- **Apache Kafka**: Servisler arasında asenkron veri iletimi ve mesajlaşma için kullanılan dağıtık bir yayın-abone sistemidir.
- **Spring Boot**: Java tabanlı mikroservislerin geliştirilmesi için kullanılan framework.
- **Zookeeper**: Dağıtık sistemlerin koordinasyonu ve konfigürasyon yönetimi için kullanılan servis.
- **Spring Security**: Güvenlik yapılandırması ve yönetimi için kullanılan kapsamlı bir güvenlik framework’ü.
- **Gateway Microservice**: API geçidi olarak görev yapan ve tüm servislerin güvenliğini sağlayan mikroservis.
- **Spring WebFlux**: Reaktif programlama modeline dayanan ve asenkron veri akışlarını işlemek için kullanılan bir framework.
- **Eureka Discovery Service**: Servis keşfi ve kayıt yönetimi için kullanılan bir Netflix OSS projesidir.
- **Feign Client**: HTTP istemcisi olarak kullanılan ve mikroservisler arası iletişimi kolaylaştıran bir kütüphane.
- **Kafka UI**: Kafka cluster'larını izlemek ve yönetmek için kullanılan kullanıcı arayüzü.


## Servisler ve Kullanılan Teknolojiler

![App Screenshot](https://furkancan.dev/assets/mikroservis.png)





## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://furkancan.dev/#/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/furkan-can-45182b236/)


