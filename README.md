# Moi Cheki Online Parser

Небольшой REST-сервис для получения чеков и фискальных данных из личного
кабинета [«Мои чеки онлайн»](https://lkdr.nalog.ru/).

Проект написан на **Java 21** и **Spring Boot**. Поддерживает получение списка чеков, загрузку подробных фискальных
данных и их экспорт в JSON.

> За основу взята идея проекта [moi-cheki-online-exporter](https://github.com/asazanoff/moi-cheki-online-exporter).
> Бэкенд переписан для более простого запуска в Docker.

## Быстрый старт

### Docker Compose

1. Создайте рядом с `compose.yaml` файл `.env`:

   ```env
   refreshToken=ваш_refresh_token
   sourceDeviceId=ваш_source_device_id
   ```

2. Раскомментируйте блок `env_file` в `compose.yaml` и запустите сервис:

   ```bash
   docker compose up --build -d
   ```

Сервис будет доступен по адресу `http://localhost:8080`.

Также переменные можно указать напрямую в блоке `environment` файла `compose.yaml`.

### Локальный запуск

Понадобится **Java 21**. Укажите `refreshToken` и `sourceDeviceId` в `.env`, затем выполните:

```bash
./mvnw spring-boot:run
```

Для Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

## Где взять токены

Значения `refreshToken` и `sourceDeviceId` можно найти в локальном хранилище браузера после авторизации
на [lkdr.nalog.ru](https://lkdr.nalog.ru/).

## API

Все запросы отправляются методом `POST` с префиксом `/api`.

| Ручка                | Описание                                                          |
|----------------------|-------------------------------------------------------------------|
| `/getReceipts`       | Возвращает список чеков по заданным параметрам поиска             |
| `/getFiscalData`     | Возвращает фискальные данные для списка ключей чеков              |
| `/getFiscalDataJson` | Формирует и скачивает фискальные данные в файле `fiscalData.json` |
| `/setRefreshToken`   | Обновляет `refreshToken` во время работы приложения               |
| `/setSourceDeviceId` | Обновляет `sourceDeviceId` во время работы приложения             |

Интерактивная документация и схемы запросов доступны в **Swagger UI**:

```text
http://localhost:8080/swagger-ui.html
```

## Стек

- Java 21
- Spring Boot
- Spring Web
- Springdoc OpenAPI
- Docker Compose
