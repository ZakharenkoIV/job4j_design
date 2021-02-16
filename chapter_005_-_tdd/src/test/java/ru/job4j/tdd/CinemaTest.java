package ru.job4j.tdd;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CinemaTest {

    @Test
    public void buyNewTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void buyPurchasedTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        cinema.buy(account, 1, 1, date);
        Ticket purchasedTicket = cinema.buy(account, 1, 1, date);
        assertThat(purchasedTicket, is(java.util.Optional.empty()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void buyTicketWithWrongDate() throws IllegalArgumentException {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2010, 10, 10, 23, 00);
        cinema.buy(account, 1, 1, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void buyTicketWithWrongPlace() throws IllegalArgumentException {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        cinema.buy(account, 0, 0, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void buyTicketWithWrongAccount() throws IllegalArgumentException {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        cinema.buy(account, 0, 0, date);
    }

    @Test
    public void findAllSessionsWherePredicateIsTrue() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Test
    public void findAllSessionsWherePredicateIsFalse() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> false);
        assertThat(sessions, is(Arrays.asList()));
    }

    @Test
    public void addNewSession() {
        Cinema cinema = new Cinema3D();
        Session newSession = new Session3D();
        cinema.add(newSession);
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(newSession)));
    }

    @Test
    public void addExistingSession() {
        Cinema cinema = new Cinema3D();
        Session existingSession = new Session3D();
        cinema.add(existingSession);
        cinema.add(existingSession);
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(existingSession)));
    }


}