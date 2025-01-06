import java.util.Random;

// backend
public class RockPaperScissorBackend {
    private String computerChoice;
    // Store computer scores and player scores to display it in the frontend
    private int computerScores, playerScores;

    // Generate random number to choose from choices
    private Random random;
    // All choices that computer can choose
    private static final String[] computerChoices = {"Rock", "Paper", "Scissors"};

    public int getComputerScores() {
        return computerScores;
    }

    public void setComputerScores(int computerScores) {
        this.computerScores = computerScores;
    }

    public void setPlayerScores(int playerScores) {
        this.playerScores = playerScores;
    }

    public String getComputerChoice() {
        return computerChoice;
    }

    public int getPlayerScores() {
        return playerScores;
    }

    public RockPaperScissorBackend()
    {
        random = new Random();
    }


    // call this method to begin play rock paper scissors
    // playerChoice - is the choice made by player (rock, paper, scissors)
    // this method will return results of the game (player wins or computer wins or draw)
    public String playRockPaperScissor (String playerChoice)
    {
        //int index = random.nextInt(2);
        computerChoice = computerChoices[random.nextInt(computerChoices.length)];

        // Store the results
        String result = "";
        if (computerChoice.equalsIgnoreCase("Rock"))
        {
            if (playerChoice.equalsIgnoreCase("Rock")) {
                result = "Draw";
            } else if (playerChoice.equalsIgnoreCase("Paper")) {
                result = "Player wins";
                playerScores ++;
            } else if (playerChoice.equalsIgnoreCase("Scissors")) {
                result = "Computer wins";
                computerScores ++;
            }
        }

        else if (computerChoice.equalsIgnoreCase("Paper"))
        {
            if (playerChoice.equalsIgnoreCase("Paper")) {
                result = "Draw";
            } else if (playerChoice.equalsIgnoreCase("Scissors")) {
                result = "Player wins";
                playerScores ++;
            } else if (playerChoice.equalsIgnoreCase("Rock")) {
                result = "Computer wins";
                computerScores ++;
            }
        }

        else if (computerChoice.equalsIgnoreCase("Scissors"))
        {
            if (playerChoice.equalsIgnoreCase("Scissors")) {
                result = "Draw";
            } else if (playerChoice.equalsIgnoreCase("Rock")) {
                result = "Player wins";
                playerScores ++;
            } else if (playerChoice.equalsIgnoreCase("Paper")) {
                result = "Computer wins";
                computerScores ++;
            }
        }
        return result;
    }
}
