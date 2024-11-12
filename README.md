  ### Дипломная работа «Облачное хранилище»

#### Описание проекта

Задача — разработать REST-сервис. Сервис должен предоставить REST-интерфейс для загрузки файлов и вывода 
списка уже загруженных файлов пользователя.

Все запросы к сервису должны быть авторизованы. Заранее подготовленное веб-приложение (FRONT) должно 
подключаться к разработанному сервису без доработок, а также использовать функционал FRONT для 
авторизации, загрузки и вывода списка файлов пользователя.

#### Описание

Приложение разработано с использованием Spring Boot.
Использован сборщик пакетов maven.
Для запуска используется docker, docker-compose.
Код размещён на Github.
Код покрыт unit-тестами с использованием mockito.

## Запуск приложения

### Описание и запуск FRONT
1. Установите nodejs (версия не ниже 19.7.0) на компьютер, следуя [инструкции](https://nodejs.org/ru/download/current/).
2. Скачайте [FRONT](./netology-diplom-frontend) (JavaScript).
3. Перейдите в папку FRONT приложения и все команды для запуска выполняйте из неё.
4. Следуя описанию README.md FRONT проекта, запустите nodejs-приложение (`npm install`, `npm run serve`).
5. Далее нужно задать url для вызова своего backend-сервиса.
    1. В файле `.env` FRONT (находится в корне проекта) приложения нужно изменить url до backend, например: `VUE_APP_BASE_URL=http://localhost:8080`.
        1. Нужно указать корневой url вашего backend, к нему frontend будет добавлять все пути согласно спецификации
        2. Для `VUE_APP_BASE_URL=http://localhost:8080` при выполнении логина frontend вызовет `http://localhost:8080/login`
    2. Запустите FRONT снова: `npm run serve`.
    3. Изменённый `url` сохранится для следующих запусков.
6. По умолчанию FRONT запускается на порту 8080 и доступен по url в браузере `http://localhost:8080`.
    1. Если порт 8080 занят, FRONT займёт следующий доступный (`8081`). После выполнения `npm run serve` в терминале вы увидите, на каком порту он запустился.


### Запуск BACKEND через Docker:

Для запуска приложения с использованием Docker и Docker Compose, следуйте инструкциям ниже:

1. Убедитесь, что Docker установлен на вашем компьютере. 

2. Склонируйте репозиторий с кодом на свой компьютер. 

3. Откройте терминал и перейдите в корневую директорию проекта. 

4. Выполните команду docker-compose up --build для сборки и запуска приложения. 

5. После успешного запуска приложения оно будет доступно по адресу http://localhost:5050. 

### Данные пользователя для теста приложения
**Логин:**    test  
**Пароль:**   test