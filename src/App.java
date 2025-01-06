//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;

public class App
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RockPaperScissorGUI rockPaperScissorGUI = new RockPaperScissorGUI();

                rockPaperScissorGUI.setVisible(true);
            }
        });
    }
}