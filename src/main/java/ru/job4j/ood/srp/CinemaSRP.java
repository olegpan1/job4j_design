package ru.job4j.ood.srp;

import ru.job4j.tdd.Account;
import ru.job4j.tdd.Session;
import ru.job4j.tdd.Ticket;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
/**
 * Из урока про TDD
  *Методы find, buy, add должны быть в других соответствующих им интерфейсах.
 * Интерфейс Cinema должен обеспечивать только доступ к возможностям кинотеатра
 * через метод toMenu
 */
public interface CinemaSRP {
    List<Session> find(Predicate<Session> filter);

    Ticket buy(Account account, int row, int column, Calendar date);

    void add(Session session);

    void toMenu();
}