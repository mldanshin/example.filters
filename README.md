# Техническое задание
    
В некой сложной системе, которая работает под большой нагрузкой имеется функция поиска перелётов.
Перед выдачей пользователю, найденные перелёты проходят различную обработку.

Нужно написать небольшой модуль, который будет заниматься фильтрацией набора перелётов 
согласно различным правилам. Правил фильтрации может быть очень много (десятки и сотни).
Также наборы перелётов могут быть очень большими (несколько сотен).
Правила могут выбираться и задаваться динамически в зависимости от контекста.

Продумайте структуру модуля, создайте необходимые абстракции. Покройте свой код тестами. 
Пользовательский интерфейс не рассматривайте. Достаточно вывода информации в консоль.
Для проверки корректности работы модуля создайте публичный класс Main c методом main().
Этот метод должен выдать в консоль результаты обработки тестового набора перелётов.
Исключите из тестового набора перелёты по следующим правилам (по каждому правилу нужен
отдельный вывод списка перелётов):
1. вылет до текущего момента времени
2. имеются сегменты с датой прилёта раньше даты вылета
3. общее время, проведённое на земле превышает два часа (время на земле — это интервал
   между прилётом одного сегмента и вылетом следующего за ним)