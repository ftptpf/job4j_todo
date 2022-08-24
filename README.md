# job4j_todo
[![Build Status](https://app.travis-ci.com/ftptpf/job4j_todo.svg?branch=master)](https://app.travis-ci.com/ftptpf/job4j_todo)

## Проект "TODO лист"
Реализован функционал работы со списком задач.

Используемые технологии:
Spring boot, Thymeleaf, Bootstrap, Hibernate, PostgreSql, Liquibase
Сборка: Maven

### Скриншоты web интерфейса.

Для начала работы необходимо зарегистрироваться и авторизоваться.
![Alt-текст](https://github.com/ftptpf/job4j_todo/blob/master/src/main/resources/images/1.JPG "Регистрация")

![Alt-текст](https://github.com/ftptpf/job4j_todo/blob/master/src/main/resources/images/2.JPG "Авторизация")

На главной странице мы видим список задач - статус их выполнения, автора, дату создания и категории к которым они относятся.
Нажимая на "Все", "Выполненные" и "Новые" мы соответственно можем отфильтровать вывод задач по данным признакам.

![Alt-текст](https://github.com/ftptpf/job4j_todo/blob/master/src/main/resources/images/3.JPG "Главная")

Если на главной странице мы нажмем кнопку "Добавить задачу" 
- мы попадем на страницу где сможем выполнить эту операцию.

![Alt-текст](https://github.com/ftptpf/job4j_todo/blob/master/src/main/resources/images/4.JPG "Добавить задачу")

На главной странице кликаем на текст задачи - попадаем на страницу где у нас будет возможность:
- установить задаче статус "Выполнено"
- удалить задачу
- отредактировать задачу

![Alt-текст](https://github.com/ftptpf/job4j_todo/blob/master/src/main/resources/images/5.JPG "Детали задачи")

![Alt-текст](https://github.com/ftptpf/job4j_todo/blob/master/src/main/resources/images/6.JPG "Редактирование задачи")

![Alt-текст](https://github.com/ftptpf/job4j_todo/blob/master/src/main/resources/images/7.JPG "Главная после редактирования")

### Запуск проекта.

Для запуска проекта вам понадобится установить:
JAVA 17+
PostgreSQL 13+
Intellij IDEA
Настройки базы данных смотрите в файле db.properties проекта.
Импортируйте данный проект в Intellij IDEA загрузив его с github.
Запуск проекта выполните запустив метод main файла Main.java проекта.

![Alt-текст](https://github.com/ftptpf/job4j_todo/blob/master/src/main/resources/images/8.JPG "Файл Main.java")

![Alt-текст](https://github.com/ftptpf/job4j_todo/blob/master/src/main/resources/images/9.JPG "Запуск проекта")

После запуска для начала работы с "TODO листом" перейдите по ссылке http://localhost:8080/index