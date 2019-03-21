import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p02_hearthstone.Board;
import p02_hearthstone.BoardImpl;
import p02_hearthstone.Card;

public class Test01 {

    private Board board;

    @Before
    public void setUp() {
        this.board = new BoardImpl();
    }

    @Test
    public void draw_should_increase_count() {
        //Arrange
        Card card = new Card("Gnome the grudge", 10, 20, 5);
        Card card2 = new Card("Magic p02_hearthstone.Card", 10, 15, 5);


        //Act
        board.draw(card);
        board.draw(card2);

        //Assert
        Assert.assertEquals(2, board.count());

    }
}
