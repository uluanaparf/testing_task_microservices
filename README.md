# Тестовое задание Library API Microservices

Разработка микросервисного проекта цифровой библиотеки

## Основные модули

1. api-gateway единая точка входа для микросервисов
2. book-service для хранения, добавления, удаления, редактирования книг
3. accounting-service для учета свободных книг
4. auth-service для аутентификации и авторизации пользователей

## Запуск программы

1. клонирование репозитория `git clone https://github.com/uluanaparf/testing_task_microservices.git
2. добавление конфигурации базы данных в book-service, accounting-service, auth-service
3. настройка файла `application.properties`, которые находятся в resources
```properties
# ===============================
# DATABASE
# ===============================
spring.application.name=library_api

spring.datasource.url = jdbc:mysql://localhost:3306/db_name
spring.datasource.username=your_username
spring.datasource.password=yourpassword

# ===============================
# JPA / HIBERNATE
# ===============================
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```
4. Запустите проект в порядке eureka-server -> auth-service -> book-service -> accounting-service -> api-gatewey
## Используемые технологии

1. Spring, Boot, MVC
2. ORM: Hibernate, Spring Data, Jpa
3. RDBMS: MySQL
4. DTO
5. Swagger
6. Authentication via Bearer Token
7. Microservices architecture 
8. Spring Cloud

___

### Проверка работы

Для проверки работы микросервисов лучше всего использовать Postman
___

### Получение токена
Для получения доступа необходимо зарегестрироваться с помощью эндпоинта API/User/register.

В тело запроса введите логин и пароль необходимом формате:
```
{
    "username": "User1",
    "password": "Userpassword"
}
```
Затем с помощью эндпоинта API/User/login получите токен введя в тело запроса свои данные:
```
{
    "username": "User1",
    "password": "Userpassword"
}
```
___
## Эндпоинты
### book-service
- GET /API/books - получение списка всех книг в базе данных
- GET /API/books/{id} - получение книги по id 
- GET /API/books/isbn/{isbn} - получение книги по isbn
- POST /API/books - добавление книги (синхронное добавление книги в микросервис accounting-service)
- PUT /API/books/{id} - редактирование книги по id 
- DELETE /API/books/{id} - удаление книги по id 
  <br>

### accounting-service
- GET /API/accountingBook - получение списка книг, которые взяли в пользование
- GET /API/accountingBook/borrowed - получение списка книг, которые еще не вернули
- GET /API/accountingBook/returned - получение списка книг которые вернули
- GET /API/accountingBook/{id} - получение книги по id
- PUT /API/accountingBook/{id}/return-by - редактирование книги для указания время возврата
- POST /API/accountingBook - добавление книги
  <br>

### auth-service
- POST /API/auth/requests - добавление пользователя для регистрации
- POST /API/auth/login - добавление пользователя для логинации и получения токена
  <br>
