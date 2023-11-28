package Game2;
import Game.AnimatedImage;
import Game.HangmanMain;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
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
    boolean addcauhoi = false;
    boolean gaptrau = false;
    int status = 0;
    boolean daadd = false;
    Menug mng = new Menug(0);
    Content ctg = new Content();
    Long time = 0L;

    boolean traloidung = false;
    boolean traloisai = false;
    int shitCnt = 0;
    int meatCnt = 0;
    private SimpleStringProperty word = new SimpleStringProperty();



    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();

        AnchorPane.setLeftAnchor(canvas,0.0);
        AnchorPane.setBottomAnchor(canvas,0.0);

        Text shittext = new Text("0");
        Text meattext = new Text("0");
        AnchorPane.setLeftAnchor(shittext,0.0);
        AnchorPane.setBottomAnchor(shittext,700.0);
        AnchorPane.setLeftAnchor(meattext,0.0);
        AnchorPane.setBottomAnchor(meattext,650.0);

        root.getChildren().addAll(canvas, shittext, meattext);
        Scene scene = new Scene(root, 1200, 800);


        primaryStage.setTitle("AnchorPane Layout Demo (o7planning.org)");
        primaryStage.setScene(scene);
        primaryStage.show();

         //(B1) Neo vào Top + Left + Right



        // Thêm vào AnchorPane

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.RED);

        Image road = new Image(getClass().getResourceAsStream("road3.png"), 1380, 800, false, false);
        Image meat = new Image(getClass().getResourceAsStream("meaat.png"), 150, 120, false, false);
        Image shit = new Image(getClass().getResourceAsStream("shit (2).png"), 80, 50, false, false);
        //Image car = new Image(getClass().getResourceAsStream("car.png"), 200, 75, false, false);
        AnimatedImage buf = new AnimatedImage();
        Image[] imageArray = new Image[14];
        for (int i = 0; i < 14; i++)
            imageArray[i] = new Image(getClass().getResourceAsStream("bu" + (i + 1) + ".png"));
        buf.frames = imageArray;
        buf.duration = 0.0300;

        AnimatedImage smoke = new AnimatedImage();
        Image[] imageArray2 = new Image[14];
        for (int i = 0; i < 7; i++){
            imageArray2[i] = new Image(getClass().getResourceAsStream("sm" + (i + 1) + ".png"),200, 270, false, false);

        }
        smoke.frames = imageArray2;
        smoke.duration = 0.100;

        AnimatedImage car = new AnimatedImage();
        Image[] imageArray3 = new Image[2];
        for (int i = 0; i < 2; i++){
            imageArray3[i] = new Image(getClass().getResourceAsStream("car" + (i + 1) + ".png"),400, 150, false, false);

        }
        car.frames = imageArray3;
        car.duration = 0.100;


        new AnimationTimer()
        {

            double trauspeed = -3 ;
            double posMeat = 0;
            double posShit = 0;
            double x = 0;
            double  traupos = 1200;
            double bgspeed = -1.5;
            final long starttime = System.nanoTime();
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - starttime) / 1000000000.0;
               // System.out.println(t);

                x += bgspeed;
                if (x <= -1380) {
                    x = 0;
                }
                //bgspeed += bgspeed
                gc.drawImage( road, x, 0);
                gc.drawImage(road, 1380 + x, 0);
                gc.drawImage( car.getFrame(1 + t % 14), 0, 540 );
                traupos += trauspeed;
                if(traupos <= -65) {
                    traupos = 1300;
                }
                gc.drawImage( buf.getFrame(1 + t % 14), traupos, 600 );


                if (!play) { // neu chua choi
                    if(!daadd) { // neu chua add menu
                        Menug mn = new Menug(0);
                        mng = mn;
                        if( root.getChildren().size() > 3) {
                            root.getChildren().remove(3);
                        }
                        root.getChildren().add(mn);
                        daadd = true;
                    } else { // neu add menu roi
                        play = mng.Getplay();

                        if(play) {
                            traupos = 1380;
                            root.getChildren().remove(3);
                            daadd = false;
                        }
                    }
                } else { // neu vao tro choi
                    if(gaptrau) { // neu gap cau hoi
                        if (status == 0) { // neu chua tra loi
                            bgspeed = 0;
                            status = ctg.getStatus();
                            if(status != 0) {
                                time = System.nanoTime();
                            }

                        } else if(status == 1) { // neu tra loi dung
                            if((currentNanoTime - time) / 1000000000.0 > 1.5) {
                                root.getChildren().remove(3);
                                status = 0;
                                gaptrau = false;
                                bgspeed = -1.5;
                                trauspeed = -3;
                                posMeat = traupos;
                                traupos = 1600;
                                traloidung = true;
                                addcauhoi = false;

                            } else {
                                gc.drawImage( smoke.getFrame(1 + t % 14), traupos, 550 );
                            }


                        }else if(status == 2) { // neu tra loi sai
                            if((currentNanoTime - time) / 1000000000.0 > 1.5) {
                                root.getChildren().remove(3);
                                status = 0;
                                gaptrau = false;
                                bgspeed = -1.5;
                                trauspeed = -3;
                                traloisai = true;
                                posShit = traupos;
                                addcauhoi = false;
                                traupos = 1600;

                            } else {
                                gc.drawImage( smoke.getFrame(1 + t % 14), traupos, 550 );
                            }

                        }
                    } else { // neu chua gap cau hoi
                        if(traupos <= 600) { // cho hien cau hoi
                            if(!addcauhoi) { // neu chua co cau hoi
                                Content ct = new Content();
                                ctg = ct;
                                root.getChildren().add(ct);
                                trauspeed = 0;
                                addcauhoi = true;
                                gaptrau = true;

                            }
                        }
                        if(traloidung) {
                            posMeat += bgspeed;
                            gc.drawImage( meat, posMeat , 600 );
                            if(posMeat < 330) {
                                traloidung = false;
                                meatCnt ++;
                                meattext.setText(String.valueOf(meatCnt));
                            }
                        }
                        if(traloisai) {
                            posShit += bgspeed;
                            gc.drawImage( shit, posShit , 600 );
                            if(posShit < 330) {
                                traloisai = false;
                            }
                        }
                    }
                }


            }

        }.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}