package Presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Server view.
 */
public class ServerView extends JFrame {
    private static final String LOGO_SRC = "Server/Media/TrophyCup.png";
    private static final int LOGO_WIDTH = 24;
    private static final int LOGO_HIGHT = 24;
    public static final String BUTTON_MONTH = "BUTTON_MONTH";
    public static final String BUTTON_WEEK = "BUTTON_WEEK";
    public static final String BUTTON_DAY = "BUTTON_DAY";

    private Image logoImage;
    private JPanel top5;
    private DrawGraph mainPanelDay;
    private DrawGraph mainPanelWeek;
    private DrawGraph mainPanelMonth;
    private HorizontalPanel horiPanelDay;
    private HorizontalPanel horiPanelWeek;
    private HorizontalPanel horiPanelMonth;
    private JPanel graphicPanel;
    private JButton bdia;
    private JButton bsetmana;
    private JButton bmes;


    /**
     * Instantiates a new Server view.
     */
    public ServerView(){
        add(configureCenter());
        configureWindow();
        setBackground(Color.WHITE);
    }

    /**
     * Function that configures the basics of the view as size, title, location, etc
     */
    private void configureWindow() {
        setTitle("Minder Server");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 400);
        setLocationRelativeTo(null);
    }

    /**
     * Function that defines the view as a JSplitpane divide in two, one for the graphic and the other for the top5.
     * @return
     */
    private JSplitPane configureCenter(){
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, configureGraphic(), configureTop5());
        sp.setResizeWeight(0.75);
        sp.setOpaque(true);
        sp.setBackground(Color.WHITE);
        return sp;
    }

    /**
     * Function returns a JPanel that incorporates the random graphic at the left of the JSplitpane
     * @return
     */
    private JPanel configureGraphic() {
        graphicPanel = new JPanel(new BorderLayout());
        JLabel jtitle = new JLabel("Matching Statistics Graphic", SwingConstants.CENTER);
        jtitle.setBorder(new EmptyBorder(10,0,10,0));

        mainPanelDay = new DrawGraph(getRandomScores(26), 0);
        mainPanelDay.setBackground(Color.WHITE);
        mainPanelDay.setOpaque(true);

        VerticalPanel vertPanel = new VerticalPanel();
        vertPanel.setBackground(Color.WHITE);
        horiPanelDay = new HorizontalPanel(0);
        horiPanelDay.setBackground(Color.WHITE);

        bdia = new JButton("Dia");
        bsetmana = new JButton("Setmana");
        bmes = new JButton("Mes");

        JPanel panelSuperior = new JPanel();
        JPanel panelbuttons = new JPanel();

        panelSuperior.setLayout(new BorderLayout());
        panelbuttons.setLayout(new FlowLayout());

        panelbuttons.add(bdia);
        panelbuttons.add(bsetmana);
        panelbuttons.add(bmes);

        panelSuperior.add(jtitle, BorderLayout.NORTH);
        panelSuperior.add(panelbuttons, BorderLayout.SOUTH);
        panelSuperior.setBackground(Color.WHITE);
        panelbuttons.setBackground(Color.WHITE);

        graphicPanel.add(panelSuperior, BorderLayout.NORTH);
        graphicPanel.add(mainPanelDay, BorderLayout.CENTER);
        graphicPanel.add(vertPanel, BorderLayout.WEST);
        graphicPanel.add(horiPanelDay, BorderLayout.SOUTH);
        graphicPanel.setBackground(Color.WHITE);
        graphicPanel.setOpaque(true);
        return graphicPanel;
    }

    public void registerController(ServerController serverController) {
        bmes.setActionCommand(BUTTON_MONTH);
        bmes.addActionListener(serverController);
        bsetmana.setActionCommand(BUTTON_WEEK);
        bsetmana.addActionListener(serverController);
        bdia.setActionCommand(BUTTON_DAY);
        bdia.addActionListener(serverController);
    }

    public void monthGraphic(int compRemove){
        mainPanelMonth = new DrawGraph(getRandomScores(32), 2);
        horiPanelMonth = new HorizontalPanel(2);
        mainPanelMonth.setBackground(Color.WHITE);
        horiPanelMonth.setBackground(Color.WHITE);
        mainPanelMonth.setOpaque(true);

        switch (compRemove){
            case 0:
                graphicPanel.remove(mainPanelDay);
                graphicPanel.remove(horiPanelDay);
                break;
            case 1:
                graphicPanel.remove(mainPanelWeek);
                graphicPanel.remove(horiPanelWeek);
                break;
        }
        graphicPanel.add(mainPanelMonth, BorderLayout.CENTER);
        graphicPanel.add(horiPanelMonth, BorderLayout.SOUTH);
        repaint();
        revalidate();
    }

    public void weekGraphic(int compRemove){
        mainPanelWeek = new DrawGraph(getRandomScores(8), 1);
        horiPanelWeek = new HorizontalPanel(1);
        mainPanelWeek.setBackground(Color.WHITE);
        horiPanelWeek.setBackground(Color.WHITE);
        mainPanelWeek.setOpaque(true);

        switch (compRemove){
            case 0:
                graphicPanel.remove(mainPanelDay);
                graphicPanel.remove(horiPanelDay);
                break;
            case 2:
                graphicPanel.remove(horiPanelMonth);
                graphicPanel.remove(mainPanelMonth);
                break;
        }
        graphicPanel.add(mainPanelWeek, BorderLayout.CENTER);
        graphicPanel.add(horiPanelWeek, BorderLayout.SOUTH);
        repaint();
        revalidate();
    }

    public void dayGraphic(int compRemove){
        mainPanelDay = new DrawGraph(getRandomScores(26), 0);
        horiPanelDay = new HorizontalPanel(0);
        mainPanelDay.setBackground(Color.WHITE);
        horiPanelDay.setBackground(Color.WHITE);
        mainPanelDay.setOpaque(true);

        switch (compRemove){
            case 1:
                graphicPanel.remove(mainPanelWeek);
                graphicPanel.remove(horiPanelWeek);
                break;
            case 2:
                graphicPanel.remove(horiPanelMonth);
                graphicPanel.remove(mainPanelMonth);
                break;
        }
        graphicPanel.add(mainPanelDay, BorderLayout.CENTER);
        graphicPanel.add(horiPanelDay, BorderLayout.SOUTH);
        repaint();
        revalidate();
    }

    /**
     * Function that returns the Panel that has inside the top5 table, with his title and icon.
     * @return
     */
    private JPanel configureTop5() {
        top5 = new JPanel(new BorderLayout());
        top5.setBackground(Color.WHITE);
        top5.setOpaque(true);

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
            jpLogo.setBorder(new EmptyBorder(25,10,25,10));
            jpLogo.setBackground(Color.WHITE);

            top5.add(jpLogo, BorderLayout.NORTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return top5;
    }

    /**
     * Update table function that renders all the colors, alignments and settings, as well as the
     * information that the table must show.
     *
     * @param data    the data
     * @param headers the headers
     */
    public void updateTable(String[][] data, String[] headers){
        JTable j = new JTable(data, headers){
            @Override
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        j.setBounds(50, 400, 200, 500);
        j.setShowGrid(false);
        j.setBackground(Color.WHITE);
        j.setOpaque(true);
        j.getTableHeader().setBackground(Color.WHITE);
        j.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));

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
        scrollPane.setBorder(new EmptyBorder(0, 15, 0, 15));
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setOpaque(true);
        top5.add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public ArrayList<Integer> getRandomScores(int maxDataPoints){
        ArrayList<Integer> scores = new ArrayList<Integer>();
        Random random = new Random();
        int maxScore = 20;
        for (int i = 0; i < maxDataPoints-1 ; i++) {
            scores.add(random.nextInt(maxScore));
        }
        return scores;
    }

    /**
     * Function to start the view making it visible.
     */
    public void start(){
        setVisible(true);
    }
}
