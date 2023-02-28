import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {

    JPanel mainPnl;

    JPanel gamePnl; // Add a border around this panel
    JButton rockBtn;
    JButton paperBtn;
    JButton scissorsBtn;
    JButton quitBtn;
    ImageIcon rockIcon;
    ImageIcon paperIcon;
    ImageIcon scissorsIcon;

    JPanel statsPnl;
    JLabel playerWinLbl;
    JLabel computerWinLbl;
    JLabel tieLbl;
    JTextField playerWinTF;
    JTextField computerWinTF;
    JTextField tieTF;

    JPanel resultPnl;
    JTextArea resultTA;
    JScrollPane scroller;

    JPanel titlePnl;
    JLabel titleLbl;
    Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 32);

    Random rnd = new Random();
    public int playerMove;
    public int computerMove;
    int gameCnt;
    int playerWinCnt;
    int computerWinCnt;
    int tieCnt;

    public RockPaperScissorsFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTitlePanel();
        mainPnl.add(titlePnl, BorderLayout.NORTH);

        createGamePanel();
        mainPnl.add(gamePnl, BorderLayout.CENTER);

        createStatsPanel();
        mainPnl.add(statsPnl, BorderLayout.EAST);

        createResultPanel();
        mainPnl.add(resultPnl, BorderLayout.SOUTH);

        add(mainPnl);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
        setTitle("Rock Paper Scissors Game");
        setLocation(screenWidth / 8, screenHeight / 8);

    }

    private void createTitlePanel() {
        titlePnl = new JPanel();
        titleLbl = new JLabel("Rock, Paper, Scissors Game");
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titleLbl.setFont(titleFont);
        titlePnl.add(titleLbl);
    }
    private void createGamePanel() {
        gamePnl = new JPanel();
        gamePnl.setLayout(new GridLayout(4, 1));

        rockBtn = new JButton(new ImageIcon("src/rock.jpg"));
        rockBtn.addActionListener((ActionEvent ae) -> {runGame(0);});

        paperBtn = new JButton(new ImageIcon("src/paper.jpg"));
        paperBtn.addActionListener((ActionEvent ae) -> {runGame(1);});

        scissorsBtn = new JButton(new ImageIcon("src/scissors.jpg"));
        scissorsBtn.addActionListener((ActionEvent ae) -> {runGame(2);});

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        gamePnl.add(rockBtn);
        gamePnl.add(paperBtn);
        gamePnl.add(scissorsBtn);
        gamePnl.add(quitBtn);

        gamePnl.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    private void createStatsPanel() {
        statsPnl = new JPanel();
        statsPnl.setLayout(new GridLayout(3,2));
        playerWinLbl = new JLabel("Player Wins:");
        computerWinLbl = new JLabel("Computer Wins:");
        tieLbl = new JLabel("Ties:");

        playerWinTF = new JTextField();
        playerWinTF.setEditable(false);

        computerWinTF = new JTextField();
        computerWinTF.setEditable(false);

        tieTF = new JTextField();
        tieTF.setEditable(false);

        statsPnl.add(playerWinLbl);
        statsPnl.add(playerWinTF);
        statsPnl.add(computerWinLbl);
        statsPnl.add(computerWinTF);
        statsPnl.add(tieLbl);
        statsPnl.add(tieTF);
    }

    private void createResultPanel() {
        resultPnl = new JPanel();
        resultPnl.setLayout(new GridLayout(1,1));

        resultTA = new JTextArea(5, 25);
        resultTA.setEditable(false);

        scroller = new JScrollPane(resultTA);
        resultPnl.add(scroller);
    }

    private void runGame(int playerMove) {

        computerMove = rnd.nextInt(3);

        if (playerMove == 0) { // Rock
            if (computerMove == 0) { // Rock
                tieCnt++;
                tieTF.setText(String.valueOf(tieCnt));
                resultTA.append("Tie! (No One Wins)\n");
            }
            else if (computerMove == 1) { // Paper
                computerWinCnt++;
                computerWinTF.setText(String.valueOf(computerWinCnt));
                resultTA.append("Paper covers Rock (Computer Wins)\n");
            }
            else { // Scissors
                playerWinCnt++;
                playerWinTF.setText(String.valueOf(playerWinCnt));
                resultTA.append("Rock breaks Scissors (Player Wins)\n");
            }
        }
        else if (playerMove == 1) { // Paper
            if (computerMove == 0) { // Rock
                playerWinCnt++;
                playerWinTF.setText(String.valueOf(playerWinCnt));
                resultTA.append("Paper covers Rock (Player Wins)\n");
            }
            else if (computerMove == 1) { // Paper
                tieCnt++;
                tieTF.setText(String.valueOf(tieCnt));
                resultTA.append("Tie! (No One Wins)\n");
            }
            else { // Scissors
                computerWinCnt++;
                computerWinTF.setText(String.valueOf(computerWinCnt));
                resultTA.append("Scissors cut Paper (Computer Wins)\n");
            }
        }
        else if (playerMove == 2) { // Scissors
            if (computerMove == 0) { // Rock
                computerWinCnt++;
                computerWinTF.setText(String.valueOf(computerWinCnt));
                resultTA.append("Rock breaks Scissors (Computer Wins)\n");
            }
            else if (computerMove == 1) { // Paper
                playerWinCnt++;
                playerWinTF.setText(String.valueOf(playerWinCnt));
                resultTA.append("Scissors cut Paper (Player Wins)\n");
            }
            else { // Scissors
                tieCnt++;
                tieTF.setText(String.valueOf(tieCnt));
                resultTA.append("Tie! (No One Wins)\n");
            }
        }
    }
}

