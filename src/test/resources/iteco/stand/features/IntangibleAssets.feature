# language: ru
Функционал: Нематериальные активы

  Структура сценария: создания нового актива типа «Нематериальные активы» для ФО типа КО
    Дано Выполнен вход в систему
    Когда Пользователь открыл <form>
    Тогда Проверить открылась форма <form>
    Тогда проверить наличие актива с номером <accountNum>
    Когда Нажал кнопку <button>
    Тогда Проверить открылась форма <form2>
    Когда Выпадающий список «Наименование ФО» должен быть заполнен наименование текущей организации и недоспупен для редактирования
    И Выбрал тип актива <asset>
    И На этой странице нажал кнопку Далее
    Тогда На Карточке актива должны отображаться закладки
    Когда Ввел в поле номер счета <accountNum>
    Тогда Валюта должна быть <currency>
    И Ввел начальную стоимость баланса <balance>
    И кликнул на Текущая балансовая стоимость
    Тогда Текущая балансовая стоимость заполнилась автоматически
    И Заполнил все обязательные поля
    И перешел на вкладку Приём
    Когда Нажал на кнопку Сохранить
    Тогда проверить создание актива <accountNum>

    Примеры:
      | form           | form2                         | button  | asset             | accountNum           | currency         | balance |
      | Список активов | Создание актива (первый этап) | Создать | Денежные средства | 20208810416645671998 | Российский рубль | 1000    |