/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arrebol.apc.controller.util;

import static java.util.Calendar.MONTH;
import static java.util.Calendar.DAY_OF_YEAR;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author oscarvargas
 */
public class DateWrapper {

    @Override
    public String toString() {
        return this.date.toString();
    }

    public static DateWrapper getInstance() {
        return new DateWrapper();
    }

    public static DateWrapper today() {
        return new DateWrapper();
    }

    /**
     * Get date time Gregorian Time - 6 (GT-6).
     *
     * @return
     */
    public static Date getTodayMXTime() {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.HOUR_OF_DAY, -6);

        return calendar.getTime();
    }
    
    public static Date updateDateToMXTime(Date curdate) {
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTime(curdate);

        calendar.add(Calendar.HOUR_OF_DAY, -6);

        return calendar.getTime();
    }

    /**
     * Get date time Gregorian Time - 6 (GT-6), set time to midnigth
     * 00:00:00:000.
     *
     * @return
     */
    public static Date midnigthMXTime() {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.HOUR_OF_DAY, -6);

        calendar.set(HOUR_OF_DAY, 0);
        calendar.set(MINUTE, 0);
        calendar.set(SECOND, 0);
        calendar.set(MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date startDate(Date startDate) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(startDate);

        calendar.add(Calendar.HOUR_OF_DAY, -6);

        calendar.set(HOUR_OF_DAY, 0);
        calendar.set(MINUTE, 0);
        calendar.set(SECOND, 0);
        calendar.set(MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date endDate(Date endDate) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(endDate);

        calendar.add(Calendar.HOUR_OF_DAY, -6);

        calendar.set(HOUR_OF_DAY, 23);
        calendar.set(MINUTE, 59);
        calendar.set(SECOND, 59);
        calendar.set(MILLISECOND, 999);

        return calendar.getTime();
    }

    /**
     * Get date time Gregorian Time - 6 (GT-6), set time to midnigth 23:59:59.
     *
     * @return
     */
    public static Date lastMXTime() {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.HOUR, -6);

        calendar.set(HOUR_OF_DAY, 23);
        calendar.set(MINUTE, 59);
        calendar.set(SECOND, 59);

        return calendar.getTime();
    }

    public DateWrapper midnigthTime() {
        Calendar calendar = this.newCalendar(this.date);
        calendar.set(HOUR_OF_DAY, 0);
        calendar.set(MINUTE, 0);
        calendar.set(SECOND, 0);
        calendar.set(MILLISECOND, 0);
        return new DateWrapper(calendar.getTime());
    }

    public DateWrapper add(int aCalendarField, int anMount) {
        Calendar calendar = this.newCalendar(this.date);
        calendar.add(aCalendarField, anMount);
        return new DateWrapper(calendar.getTime());
    }

    private Calendar newCalendar(Date aDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.setTime(aDate);
        return calendar;
    }

    public DateWrapper subtractDaysOfYears(int anMount) {
        return this.addDaysOfYears(-anMount);
    }

    public DateWrapper addDaysOfYears(int anMount) {
        return this.add(DAY_OF_YEAR, anMount);
    }

    public Date asDate() {
        return (Date) this.date.clone();
    }

    public DateWrapper firstDateForMonth() {

        Calendar calendar = newCalendar(this.date);
        calendar.set(DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(DAY_OF_MONTH));
        return new DateWrapper(calendar.getTime());
    }

    public DateWrapper lastDateForMonth() {
        Calendar calendar = newCalendar(this.date);
        calendar.set(DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(DAY_OF_MONTH));
        return new DateWrapper(calendar.getTime());
    }

    public DateWrapper subtractMonths(int months) {
        return this.addMonths(-months);
    }
    
    public DateWrapper subtractHours(int hours) {
        return this.addHours(-hours);
    }
    
     public DateWrapper addMonths(int months) {
        return this.add(MONTH, months);
    }

    public DateWrapper addHours(int hours) {
        return this.add(HOUR_OF_DAY, hours);
    }

    public Date date;

    public DateWrapper(Date aDate) {
        this.date = aDate;
    }

    private DateWrapper() {
        this.date = new Date();
    }
}
