package ohtu;

public class TennisGame {

    private final int SCORE_NEGATIVE_ONE = -1;
    private final int SCORE_ZERO = 0;
    private final int SCORE_ONE = 1;
    private final int SCORE_TWO = 2;
    private final int SCORE_THREE = 3;
    private final int SCORE_FOUR = 4;

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            m_score1++;
        } else {
            m_score2++;
        }
    }

    public String getScore() {
        String score = "";
        if (m_score1 == m_score2) {
            score = getScoreStringIfEqual();
        } else if (m_score1 >= SCORE_FOUR || m_score2 >= SCORE_FOUR) {
            score = getScoreStringIfEitherHasAdvantage();
        } else {
            score = getScoreStringIfScoresUnequalAndNoAdvantage();
        }
        return score;
    }

    private String getScoreStringIfEqual() {
        if (m_score1 == SCORE_FOUR) {
            return "Deuce";
        } else {
            return scoreToString(m_score1) + "-All";
        }
    }

    private String getScoreStringIfEitherHasAdvantage() {
        String score = "";
        int scoreDifference = m_score1 - m_score2;
        if (scoreDifference == SCORE_ONE) {
            score = "Advantage player1";
        } else if (scoreDifference == SCORE_NEGATIVE_ONE) {
            score = "Advantage player2";
        } else if (scoreDifference >= SCORE_TWO) {
            score = "Win for player1";
        } else {
            score = "Win for player2";
        }
        return score;
    }

    private String getScoreStringIfScoresUnequalAndNoAdvantage() {
        return scoreToString(m_score1) + "-" + scoreToString(m_score2);
    }

    private String scoreToString(int score) {
        String scoreString = "";
        switch (score) {
            case SCORE_ZERO:
                scoreString += "Love";
                break;
            case SCORE_ONE:
                scoreString += "Fifteen";
                break;
            case SCORE_TWO:
                scoreString += "Thirty";
                break;
            case SCORE_THREE:
                scoreString += "Forty";
                break;
        }
        return scoreString;
    }
}
