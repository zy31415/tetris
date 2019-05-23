package teris

import javafx.application.Application
import javafx.scene.{Node, Parent, Scene}
import javafx.scene.canvas.{Canvas, GraphicsContext}
import javafx.scene.control.{Button, Label}
import javafx.scene.layout.{HBox, Pane, VBox}
import javafx.scene.paint.Color
import javafx.stage.Stage

class Tetris extends Application {

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("Tetris")
    val root = buildUI()
    val scene = new Scene(root, Tetris.SCENE_WIDTH, Tetris.SCENE_HEIGHT)

    primaryStage.setScene(scene)
    primaryStage.setResizable(false)
    primaryStage.show()
  }

  private def buildUI(): Parent = {

    val hbox = new HBox(buildControlPanel(), buildPlayGround())
    hbox.setSpacing(10)
    hbox.setStyle(
      "-fx-padding: 10;" +
        "-fx-border-style: solid inside;" +
        "-fx-border-width: 2;" +
        "-fx-border-insets: 5;" +
        "-fx-border-radius: 5;" +
        "-fx-border-color: blue;"
    )
    hbox
  }

  def buildControlPanel(): Parent = {
    val label = new Label("Tetris")
    val newGameButton = new Button("New Game")
    val pauseButton = new Button("Pause")

    val controlPanel = new VBox(label, newGameButton, pauseButton)
    controlPanel.setMinWidth(200)
    controlPanel
  }

  def buildPlayGround(): Parent = {

    val canvas = new Canvas(Tetris.BOARD_WIDTH, Tetris.BOARD_HEIGHT)
    val gc = canvas.getGraphicsContext2D

    drawBackgroundStrips(gc)

    gc.setFill(Color.GREEN)
    gc.fillRect(0, 0, Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE)

    val playGround = new Pane(canvas)

    playGround.setMinSize(Tetris.BOARD_WIDTH, Tetris.BOARD_HEIGHT)
    playGround.setPrefSize(Tetris.BOARD_WIDTH, Tetris.BOARD_HEIGHT)
    playGround.setMaxSize(Tetris.BOARD_WIDTH, Tetris.BOARD_HEIGHT)

    playGround.setStyle(
      "-fx-padding: 0;" +
        "-fx-border-style: solid;" +
        "-fx-border-width: 3;" +
        "-fx-border-insets: -3;" +
        "-fx-border-color: red;"
    )

    playGround
  }

  private def drawBackgroundStrips(gc: GraphicsContext): Unit = {
    gc.setFill(Color.LIGHTGRAY)

    (0 until Tetris.BOARD_WIDTH_NUM_BLOCKS by 2).foreach(
      n =>
        gc.fillRect(n * Tetris.BLOCK_SIZE, 0, Tetris.BLOCK_SIZE, Tetris.BOARD_HEIGHT)
    )

  }
}

object Tetris {

  val BLOCK_SIZE = 30
  val BOARD_WIDTH_NUM_BLOCKS = 10
  val BOARD_HEIGHT_NUM_BLOCKS = 22
  val BOARD_WIDTH = BOARD_WIDTH_NUM_BLOCKS * BLOCK_SIZE
  val BOARD_HEIGHT = BOARD_HEIGHT_NUM_BLOCKS * BLOCK_SIZE

  val SCENE_WIDTH = 1.5 * BOARD_WIDTH+ 3 * BLOCK_SIZE
  val SCENE_HEIGHT = BOARD_HEIGHT + 2 * BLOCK_SIZE

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[Tetris], args: _*)
  }
}