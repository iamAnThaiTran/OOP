package Game2;
import Game.AnimatedImage;
import Game.HangmanMain;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    Button Answer1 = new Button("ans1");
    Button Answer2 = new Button("ans2");
    Button Answer3 = new Button("ans3");
    Button Answer4 = new Button("ans4");
    Canvas canvas = new Canvas(1200,800);
    boolean play = false;
    int status = 0;
    boolean daadd = false;
    Menug mng = new Menug(0);


    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();

        AnchorPane.setLeftAnchor(canvas,0.0);
        AnchorPane.setBottomAnchor(canvas,0.0);


        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 1200, 800);

        primaryStage.setTitle("AnchorPane Layout Demo (o7planning.org)");
        primaryStage.setScene(scene);
        primaryStage.show();

         //(B1) Neo vào Top + Left + Right



        // Thêm vào AnchorPane

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.RED);
        Image road = new Image(getClass().getResourceAsStream("road3.png"), 1380, 800, false, false);
        Image car = new Image(getClass().getResourceAsStream("car.png"), 200, 75, false, false);
        AnimatedImage buf = new AnimatedImage();
        Image[] imageArray = new Image[14];
        for (int i = 0; i < 14; i++)
            imageArray[i] = new Image(getClass().getResourceAsStream("bu" + (i + 1) + ".png"));
        buf.frames = imageArray;
        buf.duration = 0.0300;


        new AnimationTimer()
        {

            double shipx = 100;
            double x = 0;
            double  ypos = 190;
            boolean lowestship = true;

            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - 0) / 1000000000.0;

                if (!play) { // neu chua choi
                    if(!daadd) { // neu chua add menu
                        Menug mn = new Menug(0);
                        mng = mn;
                        if( root.getChildren().size() > 1) {
                            root.getChildren().remove(1);
                        }
                        root.getChildren().add(mn);
                        daadd = true;
                    } else { // neu add menu roi
                        play = mng.Getplay();
                        System.out.println(play);
                        if(play) {
                            root.getChildren().remove(1);
                            daadd = false;
                        }
                    }
                } else { // neu vao tro choi

                }
                x -= 1.5;
                if (x <= -1380) {
                    x = 0;
                }

                gc.drawImage( road, x, 0);
                gc.drawImage(road, 1380 + x, 0);
                gc.drawImage(car, 300, 600);
                gc.drawImage( buf.getFrame(1 + t % 14), 1380 + 2 * x, 600 );

            }

        }.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}