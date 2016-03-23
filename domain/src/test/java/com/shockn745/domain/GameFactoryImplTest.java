package com.shockn745.domain;

import com.shockn745.application.driving.dto.GameStatus;
import com.shockn745.application.driving.dto.Player;
import com.shockn745.domain.datamapper.GameDataMapper;
import com.shockn745.testutil.GameStatusTestScenarios;
import com.shockn745.utils.NullObjects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Kempenich Florian
 */
public class GameFactoryImplTest {

    GameFactory gameFactory;
    GameDataMapper gameDataMapper;
    GameStatusTestScenarios testScenarios;

    @Before
    public void setUp() throws Exception {
        gameFactory = new GameFactoryImpl();
        gameDataMapper = new GameDataMapper(gameFactory);
        testScenarios = new GameStatusTestScenarios(gameFactory);
    }

    @Test
    public void createNewGame_returnEmptyGame() throws Exception {
        Game game = gameFactory.makeNewGame();
        GameStatus status = gameDataMapper.transform(game);
        GameStatus expected = NullObjects.makeEmptyGameStatus(GameStatus.NO_ID);
        assertEquals(expected, status);
    }

    @Test
    public void createGameFromStatus_emptyBoard_resultingStatusIsSameAsOriginal() throws Exception {
        int id = 2;
        GameStatus status = NullObjects.makeEmptyGameStatus(id);
        Game game = gameFactory.makeGame(status);

        assertEquals(status, gameDataMapper.transform(game));
    }

    @Test
    public void createGameFromStatus_nonEmptyBoard_resultingStatusIsSameAsOriginal() throws Exception {
        int id = 2;
        GameStatus status = testScenarios.makeGameStatusWithMoveOn00(id);
        Game game = gameFactory.makeGame(status);
        assertEquals(status, gameDataMapper.transform(game));
    }

    @Test
    public void createNewGame_makeStatus_createNewGameWithStatus_gamesAreIdentical()
            throws Exception {
        Game newGame = gameFactory.makeNewGame();
        GameStatus status = gameDataMapper.transform(newGame);

        Game fromStatus = gameFactory.makeGame(status);

        assertEquals(newGame, fromStatus);
    }

    @Test
    public void createNewGame_playMove_makeStatus_createNewGameWithStatus_gamesAreIdentical()
            throws Exception {
        Game moveOn00 = gameFactory.makeNewGame();
        moveOn00.play(new MoveModel(0, 0, Player.player1()));
        GameStatus status = gameDataMapper.transform(moveOn00);

        Game fromStatus = gameFactory.makeGame(status);

        assertEquals(moveOn00, fromStatus);
    }
}