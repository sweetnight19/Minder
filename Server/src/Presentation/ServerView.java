package Presentation;

import Business.Entity.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ServerView extends JFrame {
    private static final String LOGO_SRC = "Server/Media/TrophyCup.png";
    private static final int LOGO_WIDTH = 24;
    private static final int LOGO_HIGHT = 24;

    private Image logoImage;
    private JPanel top5;

    public ServerView(){
        add(configureCenter());
        configureWindow();
    }

    private void configureWindow() {
        setTitle("Minder Server");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
    }

    private JSplitPane configureCenter(){
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, configureGraphic(), configureTop5());
        sp.setResizeWeight(0.75);
        return sp;
    }

    private JPanel configureGraphic() {
        JPanel hola = new JPanel();
        JLabel hola2 = new JLabel("hola");
        hola.add(hola2);
        return hola;
    }

    private JPanel configureTop5() {
        top5 = new JPanel(new BorderLayout());
        try {
            logoImage = ImageIO.read(new File(LOGO_SRC));
            // Image
            logoImage = logoImage.getScaledInstance(LOGO_WIDTH, LOGO_HIGHT, Image.SCALE_DEFAULT);
            JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
            // Title
            JLabel logoTitle = new JLabel("TOP 5 USERS");
            Font logoFont = logoTitle.getFont().deriveFont(Font.BOLD, 20);
            logoTitle.setFont(logoFont);
            // Panel
            JPanel jpLogo = new JPanel();
            jpLogo.add(logoTitle);
            jpLogo.add(logoLabel);

            top5.add(jpLogo, BorderLayout.NORTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return top5;
    }

    public void updateTable(String[][] data, String[] headers){
        JTable j = new JTable(data, headers){
            @Override
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        j.setBounds(50, 400, 200, 500);
        j.setShowGrid(false);
        j.getTableHeader().setBackground(Color.white);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                switch (row){
                    case 0:
                        setForeground(Color.decode("#efb810"));
                        break;
                    case 1:
                        setForeground(Color.decode("#BEBEBE"));
                        break;
                    case 2:
                        setForeground(Color.decode("#cd7f32"));
                        break;
                    default:
                        setForeground(Color.BLACK);
                }
                return this;
            }
        };
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer.setForeground(Color.red);
        for (int i = 0; i < j.getColumnCount(); i++) {
            j.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        j.setRowHeight(40);

        JScrollPane scrollPane= new  JScrollPane(j);
        top5.add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void start(){
        setVisible(true);
    }
}
