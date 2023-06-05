# QA_Diplom_3

Учебный проект по автотестированию UI для сайта по заказу бургеров Stellar Burgers.

## Описание

В проекте тестируется функциональность в Google Chrome и Яндекс.Браузере.

Версия Java 11
Проект использует следующие библиотеки:
- JUnit 4
- RestAssured
- Allure
- Selenium

## Документация

[Ссылка](https://stellarburgers.nomoreparties.site) на сайт.

### Запуск автотестов

Для запуска автотестов необходимо:

1. Скачать код

 ```sh
   git clone https://github.com/chesterior/QA_Diplom_3.git
   ```
   
2. Запустить команду в проекте, чтобы запустить тесты и сгенерировать отчёт

```sh
mvn clean test
```

3. Для отображения отчета в Allure запустите команду

```sh
mvn allure:serve
```
