package Presentation.Controller;

import Presentation.View.EditView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.View.EditView.*;

public class ProfileController implements ActionListener {
    private EditView editView;

    public ProfileController(EditView editView){
        this.editView = editView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(EDIT_BTN)) {
            System.out.println("edit buton selected");
            editView.transformToEditable();
        }else if(e.getActionCommand().equals(SAVE_BTN)){
            System.out.println("Save btn selected");
            editView.transfromToNotEditable();
        }else if(e.getActionCommand().equals(DELETE_BTN)){
            System.out.println("Button deleted actioned");
        }else if(e.getActionCommand().equals(CHANGE_BTN)){
            System.out.println("Buto per cnaviar imatge seleccionat");
        }
    }
}
