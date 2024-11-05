package component;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Date;

public class QDate extends JPanel {
    private LocalDate date;
    private JComboBox day;
    private JComboBox month;
    private JTextField year;
    private final Integer[] days = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
    private final String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};

    public QDate() {
        setSize(500,500);
         this.date = LocalDate.of(1990,1,1);
         day = new JComboBox(days);
         month = new JComboBox(months);
         year = new JTextField();
         year.setBounds(50, 50, 150, 20);
         year.setEditable(true);
         add(day);
         add(month);
         add(year);
    }

    public void setDate (int day, int month, int year) throws QDateException {
        checkDate(day,month,year);
        date = LocalDate.of(year, month, day);
    }

    public LocalDate getDate () {
        return date;
    }

    private void checkDate (int day, int month, int year) throws QDateException {
        boolean valid = true;
        boolean leap =  false;
        if (day > 31)
            valid = false;
        else if (month > 12)
            valid = false;
        else if (month % 2 != 0 && month != 7 && day > 30)
            valid = false;
        else if (month == 1 && day > 29)
            valid = false;
        else {
            if (year % 4 == 0) {
                if (year % 100 == 0) {
                    leap = year % 400 == 0;
                } else {
                    leap = true;
                }
            }
            if (!leap && month == 1 && day == 28) {
                valid = false;
            }
        }
        if (!valid)
            throw new QDateException();
    }


}
