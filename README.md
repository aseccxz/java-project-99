# Менеджер задач
### Hexlet tests and linter status:
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=aseccxz_java-project-99&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=aseccxz_java-project-99)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=aseccxz_java-project-99&metric=coverage)](https://sonarcloud.io/summary/new_code?id=aseccxz_java-project-99)
[![Actions Status](https://github.com/aseccxz/java-project-99/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/aseccxz/java-project-99/actions)

"Менеджер задач" предоставляет инструменты для полного цикла работы с задачами: их создания, отслеживания и модификации. Ключевые возможности — назначение ответственных лиц, управление статусами задач и гибкая категоризация с помощью множественных меток (лейблов). Решение функционирует в двух режимах: для локального развертывания (development) и для промышленной эксплуатации (production).

### Деплой

Проект доступен по адресу: [сайт](https://task-manager-4lmk.onrender.com/)

### Авторизация

- **Логин:** `hexlet@example.com`
- **Пароль:** `qwerty`
## Используемые технологии

### 🚀 Фреймворк и безопасность
- **Spring Boot** — основной фреймворк приложения
- **Spring Security** — аутентификация и авторизация

### 🛠️ Разработка и маппинг
- **Mapstruct** — автоматический маппинг сущностей и DTO
- **DTO** — шаблон проектирования для передачи данных

### 📄 Документация и мониторинг
- **Springdoc OpenAPI** + **Swagger** — документация API

### 🧪 Тестирование и качество кода
- **JUnit 5** — модульное тестирование
- **MockWebServer** — тестирование HTTP-клиентов
- **Datafaker** — генерация тестовых данных
- **JaCoCo** — отчёт о покрытии кода тестами
- **Checkstyle** — линтер для соблюдения стиля кода

### 🗄️ Базы данных
- **H2** — встроенная БД для локальной разработки
- **PostgreSQL** — БД для production-среды

### 🐳 Деплой и хостинг
- **Docker** — контейнеризация приложения
- **Render** — бесплатный cloud-хостинг (production-среда)
