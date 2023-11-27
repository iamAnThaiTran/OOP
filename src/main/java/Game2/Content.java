package Game2;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Content extends AnchorPane {
    Button Answer1 = new Button("ans1");
    Button Answer2 = new Button("ans2");
    Button Answer3 = new Button("ans3");
    Button Answer4 = new Button("ans4");
    Button Question = new Button("question");
    SearchData sd = new SearchData();
    boolean play = false;
    public Content() {
        int idQuestion = (int)(Math.random()*20);
        Answer1.setText(sd.getAns(idQuestion, 1));
        Answer2.setText(sd.getAns(idQuestion, 2));
        Answer3.setText(sd.getAns(idQuestion, 3));
        Answer4.setText(sd.getAns(idQuestion, 4));

        AnchorPane.setTopAnchor(Answer1, 40.0);
        AnchorPane.setLeftAnchor(Answer1, 50.0);
        AnchorPane.setRightAnchor(Answer1, 70.0);

        // (B2) Neo vào Top + Left + Right
        AnchorPane.setTopAnchor(Answer2, 90.0);
        AnchorPane.setLeftAnchor(Answer2, 10.0);
        AnchorPane.setRightAnchor(Answer2, 320.0);


        // (B3) Neo vào Top + Left + Right
        AnchorPane.setTopAnchor(Answer3, 100.0);
        AnchorPane.setLeftAnchor(Answer3, 250.0);
        AnchorPane.setRightAnchor(Answer3, 20.0);

        // (B4) Neo vào 4 cạnh của AnchorPane
        AnchorPane.setTopAnchor(Answer4, 150.0);
        AnchorPane.setLeftAnchor(Answer4, 40.0);
        AnchorPane.setRightAnchor(Answer4, 50.0);
        AnchorPane.setBottomAnchor(Answer4, 45.0);
        this.getChildren().addAll(Answer1,Answer2, Answer3, Answer4);
    }
}
