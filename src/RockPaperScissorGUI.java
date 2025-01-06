import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// implement the ActionListener interface to the class
// this will force us to define our own ActionPerformed() method
public class RockPaperScissorGUI extends JFrame implements ActionListener {
    // Declare all button as global variable to use add event listener to add it later
    JButton rockButton, scissorButton, paperButton;
    JLabel computerChoice, computerLabel, playerLabel;

    // Backend object
    RockPaperScissorBackend rockPaperScissorBackend;

    public RockPaperScissorGUI()
    {
        // Set up GUI and add title
        super("Rock Paper Scissor");

        // Terminate GUI when hit close (x) button
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set the size of GUI (in pixels)
        setSize(450, 574);

        // Set layout to null to disable layout management, so we can use absolute positioning for the elements
        // (i.e. setting x, y coordinates and width/height values )
        setLayout(null);

        // Load GUI in center of the screen
        setLocationRelativeTo(null);

        // Prevent any resize of GUI
        setResizable(false);

        // Load back end object
//        rockPaperScissorBackend = new RockPaperScissorBackend();

        addGUIComponents();
    }


    private void addGUIComponents()
    {
        // Display computer score
        computerLabel = new JLabel("Computer: 0");
        computerLabel.setBounds(0, 15, 450, 100);
        computerLabel.setFont(new Font("Dialog", Font.BOLD, 26));
        add(computerLabel);
        computerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Display computer choice
        computerChoice = new JLabel("?");
        computerChoice.setBounds(175, 118, 98, 81);
        computerChoice.setFont(new Font("Dialog", Font.PLAIN, 18));
        computerChoice.setHorizontalAlignment(SwingConstants.CENTER);

        // Create a black border around
        computerChoice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(computerChoice);

        // Display player score
        playerLabel = new JLabel("Player: 0");
        playerLabel.setBounds(0, 250, 450, 180);
        playerLabel.setFont(new Font("Dialog", Font.BOLD, 26));
        playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(playerLabel);

        // Display Rock Button
        rockButton = new JButton("Rock");
        rockButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        rockButton.setBounds(30, 380, 100, 100);
        rockButton.setFont(new Font("Dialog", Font.PLAIN, 18));

        // By passing this, you are telling the JButton that the current instance of RockPaperScissorGUI
        // will handle the button's action events.
        rockButton.addActionListener(this);
        add(rockButton);

        // Display Paper Button
        paperButton = new JButton("Paper");
        paperButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        paperButton.setBounds(170, 380, 100, 100);
        paperButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        paperButton.addActionListener(this);
        add(paperButton);

        // Display Scissor Button
        scissorButton = new JButton("Scissors");
        scissorButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        scissorButton.setBounds(310, 380, 100, 100);
        scissorButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        scissorButton.addActionListener(this);
        add(scissorButton);
        rockPaperScissorBackend = new RockPaperScissorBackend();

    }

    // Display a message dialog which will show the winner and a try again button to play again
    public JDialog showDialog(String message)
    {
        // modal: true, user cannot click on any buttons unless we close dialog
        // by passing this, you are specifying that current instance of RockPaperScissorGUI (which extends JFrame)
        // is owner of the dialog
        JDialog resultDialog = new JDialog(this, "Results", true);
        resultDialog.setSize(227, 124);
        resultDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        resultDialog.setResizable(false);

        // Display message in dialog window
        JLabel resultLabel = new JLabel(message);
        resultLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // JDialog has their own layout manager to place the components in specific place
        resultDialog.add(resultLabel, BorderLayout.CENTER);

        // Try again button
        JButton playAgainButton = new JButton("Try again?");
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the computer choice to ?
                computerChoice.setText("?");

                // Close the dialog window
                resultDialog.dispose();
            }
        });



        // Play again button
        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(5, 50, 50, 15);
        resultDialog.add(resetButton, BorderLayout.SOUTH);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computerLabel.setText("Computer: 0");
                rockPaperScissorBackend.setComputerScores(0);

                playerLabel.setText("Player: 0");
                rockPaperScissorBackend.setPlayerScores(0);

                computerChoice.setText("?");
                resultDialog.dispose();
            }
        });
        // Add play again button into the dialog window, set into south
        resultDialog.add(playAgainButton, BorderLayout.SOUTH);
        resultDialog.setLocationRelativeTo(null);
        //resultDialog.setVisible(true);
        return resultDialog;
    }

    // Make all actions into one actionPerformed method
    @Override
    public void actionPerformed(ActionEvent e) {

        // Method e.getActionCommand() returns the action command string associated with the source of the event
        // By default, the action command for a JButton is button's label (the text displayed on the button)
        // When you click the button, the ActionEvent is triggered, and its getActionCommand() method
        // retrieves this default action command.
        // Retrieve player's choice
        String playerChoice = e.getActionCommand();

        // Get result from backend to display in the dialog
        String result = rockPaperScissorBackend.playRockPaperScissor(playerChoice);

        // Get the computer choice from backend to display in frontend
        computerChoice.setText(rockPaperScissorBackend.getComputerChoice());

        // Get the computer scores from backend and display in front end
        computerLabel.setText("Computer: " + rockPaperScissorBackend.getComputerScores());

        playerLabel.setText("Player: " + rockPaperScissorBackend.getPlayerScores());

        showDialog(result).setVisible(true);

    }
}
