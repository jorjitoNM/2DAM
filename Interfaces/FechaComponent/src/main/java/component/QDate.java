package component;

import javax.swing.*;
import java.time.LocalDate;

public class QDate extends JPanel {
    private LocalDate date = LocalDate.of(1990,1,1);

    public QDate() {}

    public void setDate (int day, int month, int year) throws QDateException {
        checkDate(day,month,year);
        date = LocalDate.of(year, month, day);
    }

    private void checkDate(int day, int month, int year) throws QDateException {
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
