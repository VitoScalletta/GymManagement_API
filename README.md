# 🏋️‍♂️ Gym Management REST API

Bu proje, bir spor salonunun tüm operasyonel süreçlerini yönetmek için geliştirilmiş, **Kurumsal Seviye (Enterprise)** bir Spring Boot REST API uygulamasıdır. Proje, modern yazılım geliştirme prensipleri (SOLID), güvenlik standartları ve performans optimizasyonları göz önünde bulundurularak sıfırdan inşa edilmiştir.

## 🚀 Kullanılan Teknolojiler (Tech Stack)

* **Backend:** Java 17, Spring Boot 3, Spring Data JPA
* **Veritabanı & Önbellek:** PostgreSQL, Redis
* **Güvenlik:** Spring Security, JWT (JSON Web Token) Auth
* **Dokümantasyon:** Swagger (OpenAPI 3.0)
* **DevOps & CI/CD:** Docker, Docker Compose, GitHub Actions
* **Test:** JUnit 5, Mockito

## ⚙️ Temel Özellikler ve İş Kuralları

* 🔐 **Rol Tabanlı Yetkilendirme (RBAC):** `ADMIN`, `TRAINER` ve `MEMBER` olmak üzere 3 farklı rol yapısı. Yalnızca yetkili roller ilgili endpoint'lere erişebilir.
* ⚡ **Redis ile Caching (Önbellekleme):** Paket listeleri (Membership Plans) gibi sık okunan veriler Redis üzerinde önbelleklenir. Yeni bir paket eklendiğinde/güncellendiğinde Cache otomatik olarak düşürülür (`@CacheEvict`).
* 🤖 **Zamanlanmış Görevler (Scheduling):** `@Scheduled` kullanılarak her gece yarısı veritabanı taranır ve üyeliğinin bitmesine 3 gün kalan kullanıcılar tespit edilip sisteme loglanır/uyarılır.
* 🚪 **Günlük Check-In Sınırları:** Bir üye, aktif bir paketi yoksa veya o gün zaten giriş yapmışsa turnikeden (Check-In API) geçemez.
* 🏋️ **Antrenman Yönetimi:** Antrenörler (Trainer), üyelere özel antrenman programları ve bu programlara bağlı set/tekrar içeren egzersizler atayabilir.

## 🛠️ Kurulum ve Çalıştırma

Projeyi kendi lokal ortamınızda çalıştırmak için aşağıdaki adımları izleyin:

**1. Repoyu Klonlayın:**
```bash
git clone [https://github.com/KULLANICI_ADIN/GymManagement_API.git](https://github.com/KULLANICI_ADIN/GymManagement_API.git)
cd GymManagement_API
```

**2. Docker ile Altyapıyı Ayağa Kaldırın:**
PostgreSQL ve Redis servislerini başlatmak için:
```bash
docker-compose up -d
```

**3. Projeyi Derleyin ve Çalıştırın:**
```bash
mvn clean install
mvn spring-boot:run
```

## 📖 API Dokümantasyonu (Swagger)

Proje ayağa kalktıktan sonra, tüm API uç noktalarını (endpoint) görmek ve test etmek için tarayıcınızdan aşağıdaki adrese gidebilirsiniz:

👉 **Swagger UI:** `http://localhost:8080/swagger-ui.html`

*(Not: Güvenli uç noktaları test etmek için önce `/api/auth/login` üzerinden giriş yapıp aldığınız Token'ı sağ üstteki "Authorize" butonuna yapıştırmalısınız).*

## 🧪 CI/CD ve Test Süreci

Bu proje, her push işleminde **GitHub Actions** tarafından otomatik olarak derlenir ve **JUnit & Mockito** ile yazılmış birim testleri (Unit Tests) koşulur. Bu sayede ana projedeki (main branch) iş kurallarının bütünlüğü daima korunur.