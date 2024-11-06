package component;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class QDate extends JPanel {
    private LocalDate date;
    private final JComboBox<Integer> day;
    private final JComboBox<String> month;
    private final JTextField year;
    private final JLabel response;

    public QDate() {
        this.date = LocalDate.of(1990, 1, 1);
        JLabel dayLabel = new JLabel("Day:");
        day = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31});
        JLabel monthLabel = new JLabel("Month:");
        month = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        JLabel yearLabel = new JLabel("Year:");
        year = new JTextField(5);
        year.setEditable(true);
        JButton check = new JButton("Check");
        check.addActionListener(e -> onCheckDate());
        check.setLayout(new FlowLayout(FlowLayout.CENTER));
        response = new JLabel();
        add(dayLabel);
        add(day);
        add(monthLabel);
        add(month);
        add(yearLabel);
        add(year);
        add(check);
        add(response);
    }

    public void setDate(int day, int month, int year) throws QDateException {
        checkDate(day, month, year);
        date = LocalDate.of(year, month, day);
    }

    public LocalDate getDate() {
        return date;
    }

    private void checkDate(int day, int month, int year) throws QDateException {
        boolean valid = true;
        boolean leap = false;
        if (day > 31)
            valid = false;
        else if (month > 12)
            valid = false;
        else if (month % 2 == 0 && month != 8 && day > 30)
            valid = false;
        else if (month == 2 && day > 29)
            valid = false;
        else {
            if (year % 4 == 0) {
                if (year % 100 == 0) {
                    leap = year % 400 == 0;
                } else {
                    leap = true;
                }
            }
            if (!leap && month == 2 && day == 29) {
                valid = false;
            }
        }
        if (!valid)
            throw new QDateException();
    }

    public void onCheckDate() {
        response.setText("");
        try {
            setDate(day.getSelectedIndex()+1, month.getSelectedIndex()+1,Integer.parseInt(year.getText()));
            response.setText(String.format("Fecha: %d/%s/%d es válida",date.getDayOfMonth(),date.getMonth(),date.getYear()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Introduzca solamente numeros en el campo del año");
        } catch (QDateException ex) {
            JOptionPane.showMessageDialog(this,"La fecha introducida no es correcta");
        }
    }
}
class QDateException extends Exception {
}
