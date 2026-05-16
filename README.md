# Event Planner API

## 📌 Proje Hakkında

Bu proje, kullanıcıların etkinlik oluşturabildiği ve yönetebildiği bir REST API ve Web-site uygulamasıdır.
Spring Boot kullanılarak geliştirilmiş olup JWT tabanlı authentication ve role-based authorization içermektedir.

---

## 🚀 Özellikler

* 🔐 JWT Authentication (Login/Register)
* 👥 Role-Based Authorization (USER / ADMIN)
* 📅 Event CRUD işlemleri
* 👤 Kullanıcıya özel veri erişimi (User Isolation)
* ⚠️ Global Exception Handling
* ✅ Input Validation (@Valid)
* 📦 Standart API Response yapısı

---

## 🛠️ Kullanılan Teknolojiler

* Java 17+
* Spring Boot
* Spring Security
* JWT (jjwt)
* PostgreSQL
* JPA / Hibernate
* Lombok

---

## 🔑 Authentication

Login sonrası JWT token alınır:

POST /api/auth/login

Sonraki requestlerde header:

Authorization: Bearer TOKEN

---

## 📡 Örnek Endpointler

### 🔹 Event oluştur

POST /api/events

### 🔹 Event listele (kendi eventlerin)

GET /api/events

### 🔹 Event güncelle

PUT /api/events/{id}

### 🔹 Event sil

DELETE /api/events/{id}

### 🔹 Admin tüm eventleri gör

GET /api/admin/events

---

## 📊 API Response Format

```json
{
  "success": true,
  "data": {...},
  "message": "Başarılı"
}
```

---

## ⚠️ Hata Formatı

```json
{
  "success": false,
  "data": null,
  "message": "Hata mesajı"
}
```

---

## 🎯 Proje Amaçları

Bu proje ile:

* Spring Security öğrenildi
* JWT authentication uygulandı
* Role-based authorization geliştirildi
* REST API best practices uygulandı

---

## 👨‍💻 Geliştirici

Alper Arslan
