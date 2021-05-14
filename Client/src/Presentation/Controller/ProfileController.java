package Presentation.Controller;

import Business.Model.ProfileManager;
import Presentation.View.EditView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Presentation.View.EditView.*;

public class ProfileController implements ActionListener {
    private final EditView editView;
    private final ProfileManager profileManager;

    public ProfileController(EditView editView, ProfileManager manager) {
        this.editView = editView;
        this.profileManager = manager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case EDIT_BTN:
                System.out.println("Edit button selected");
                editView.transformToEditable();

                break;
            case SAVE_BTN:
                System.out.println("Save button selected");
                System.out.println(editView.getLanguage());
                System.out.println(editView.getDescription());
                this.profileManager.saveUserChanges(editView.getLanguage(), editView.getDescription());
                editView.transfromToNotEditable();

                break;
            case DELETE_BTN:
                System.out.println("Delete button selected");
                this.profileManager.deleteUser();

                break;
            case CHANGE_BTN:
                System.out.println("Change image button selected");
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    //System.out.println(selectedFile.getAbsolutePath());
                    try {
                        BufferedImage image = ImageIO.read(new File(selectedFile.getAbsolutePath()));
                        editView.setNewImage(image);
                        this.profileManager.saveNewImage(image);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
                break;
        }
    }
}
