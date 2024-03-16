package no.uib.inf101.tetris.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.view.TetrisView;

/**
 * 
 */
public class TetrisController implements KeyListener, ActionListener {

    TetrisModel model;
    ControllableTetrisModel controllableTetrisModel;
    TetrisView tetrisView;
    Timer timer;
    TetrisSong song;

    /**
     * Constructs a new instance of TetrisController.
     * 
     * @param controllableTetrisModel
     * @param tetrisView
     */
    public TetrisController(ControllableTetrisModel controllableTetrisModel, TetrisView tetrisView) {
        this.controllableTetrisModel = controllableTetrisModel;
        this.tetrisView = tetrisView;

        tetrisView.addKeyListener(this);
        tetrisView.setFocusable(true);

        this.timer = new Timer(controllableTetrisModel.getSeconds(), this);
        timer.start();

        this.song = new TetrisSong();
        song.run();

    }

    /**
     * This method locks the tetromino and updates the view.
     */
    public void lockTetrominoAndSpawnNewAndUpdateView() {
        controllableTetrisModel.lockTetrominoAndSpawnNew();
        tetrisView.repaint();
    }

    /**
     * Gets the delay value used by the timer for the tetris game.
     */
    public void getDelay() {
        timer.setDelay(controllableTetrisModel.getSeconds());
        timer.setInitialDelay(controllableTetrisModel.getSeconds());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (controllableTetrisModel.getGameState() == GameState.WELCOME_SCREEN) {

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                controllableTetrisModel.setGameState(GameState.ACTIVE_GAME);
            }
            if (controllableTetrisModel.getGameState() == GameState.GAME_OVER) {
                // Start spillet på nytt hvis spilleren trykker på enter
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    controllableTetrisModel.resetGame();
                }
            }

        }
        if (controllableTetrisModel.getGameState() == GameState.ACTIVE_GAME) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                controllableTetrisModel.moveTetromino(0, -1);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                controllableTetrisModel.moveTetromino(0, 1);

            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                controllableTetrisModel.moveTetromino(1, 0);

            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                controllableTetrisModel.rotateTetromino();

            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                controllableTetrisModel.dropTetromino();
            }

        }

        else if (controllableTetrisModel.getGameState() == GameState.GAME_OVER) {
            controllableTetrisModel.resetGame();
        }
        tetrisView.repaint();

        tetrisView.paintImmediately(tetrisView.getBounds());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (controllableTetrisModel.getGameState() == GameState.ACTIVE_GAME) {
            controllableTetrisModel.clockTick();
            tetrisView.repaint();
        }
        this.getDelay();
    }

}