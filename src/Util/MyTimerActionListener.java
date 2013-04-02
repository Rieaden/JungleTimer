package Util;

import javafx.event.ActionEvent;

public class MyTimerActionListener //implements ActionListener 
{
    private int timeMinute;
    private int timeSeconde;

    public MyTimerActionListener(int initMinute, int initSeconde) {
          super();
          this.timeMinute = initMinute;
          this.timeSeconde = initSeconde;
    }

    public void actionPerformed(ActionEvent e) {
          if (this.timeSeconde == 0) {
                this.timeMinute--;
                this.timeSeconde = 59;
          } else
                this.timeSeconde--;
          System.out.println(this.timeMinute + " : " + this.timeSeconde);
    }
}