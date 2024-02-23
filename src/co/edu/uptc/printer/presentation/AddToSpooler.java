package co.edu.uptc.printer.presentation;

import co.edu.uptc.printer.logic.ArchiveController;
import co.edu.uptc.printer.logic.PrintSpooler;
import co.edu.uptc.printer.model.FileToPrint;
import co.edu.uptc.printer.view.WarningMessages;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddToSpooler extends JFrame {

    private  MainWindow myMainWindow;
    private PrintSpooler printSpooler;
    private ArchiveController archiveController;
    private WarningMessages myWarningMessages;
    private JPanel mainPanel;
    private  JPanel configPanel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel pagesLabel;
    private JTextField pagesField;
    private JLabel colorLabel;
    private JComboBox<String> colorBox;
    private JLabel sizeLabel;
    private JComboBox<String> sizeBox;
    private JLabel priorityLabel;
    private JComboBox<String> priorityBox;
    private JLabel pagesToPrintLabel;
    private JTextField pagesToPrintField;
    private JLabel errorNameLabel;
    private JLabel errorPagesLabel;
    private JLabel errorPriorityLabel;
    private JButton addButton;
    private JButton finishButton;

    public AddToSpooler(MainWindow mainWindow, PrintSpooler printSpooler) {
        myMainWindow = mainWindow;
        this.printSpooler = printSpooler;
        archiveController = new ArchiveController();
        myWarningMessages = new WarningMessages();
        setTitle("Add to Spooler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
    }
    public void createWindow(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1,1,10,10));

        configPanel = new JPanel();
        configPanel.setLayout(new GridLayout(0,2,20,20));

        nameLabel = new JLabel("Nombre del archivo:");

        nameField = new JTextField(5);
        pagesLabel = new JLabel("Número de páginas:");
        pagesField = new JTextField(5);
        colorLabel = new JLabel("Color:");
        colorBox = new JComboBox<>(new String[]{"Blanco y negro", "Color"});
        sizeLabel = new JLabel("Tamaño:");
        sizeBox = new JComboBox<>(new String[]{"Carta", "Oficio"});
        priorityLabel = new JLabel("Prioridad:");
        priorityBox = new JComboBox<>(new String[]{"Alta", "Baja"});
        pagesToPrintLabel = new JLabel("Hojas a imprimir:");
        pagesToPrintField = new JTextField(5);
        errorNameLabel = new JLabel("");
        errorPagesLabel = new JLabel("");
        errorPriorityLabel = new JLabel("");
        addButton = new JButton("Añadir otro");
        finishButton = new JButton("Finalizar");

        errorNameLabel.setVisible(false);
        errorPagesLabel.setVisible(false);
        errorPriorityLabel.setVisible(false);

        configPanel.add(nameLabel);
        configPanel.add(nameField);
        configPanel.add(pagesLabel);
        configPanel.add(pagesField);
        configPanel.add(colorLabel);
        configPanel.add(colorBox);
        configPanel.add(sizeLabel);
        configPanel.add(sizeBox);
        configPanel.add(priorityLabel);
        configPanel.add(priorityBox);
        configPanel.add(pagesToPrintLabel);
        configPanel.add(pagesToPrintField);
        configPanel.add(addButton);
        configPanel.add(finishButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (validateInput()) {
                        nameField.requestFocus();
                    } else {
                        printSpooler.addFileToTail(createFileToPrint(), processString(priorityBox.getSelectedItem()));
                        nameField.requestFocus();
                        myWarningMessages.inputWarning("Archivo añadido");
                        restartFields();
                    }
                } catch (NumberFormatException f) {
                    System.out.println("Uepa, casilla vacía");
                }
            }
        });

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (validateInput()) {
                        nameField.requestFocus();
                        myMainWindow.setState(true);
                    } else {
                        printSpooler.addFileToTail(createFileToPrint(), processString(priorityBox.getSelectedItem()));
                        nameField.requestFocus();
                        myWarningMessages.inputWarning("Archivo añadido");
                        restartFields();
                        dispose();
                        myMainWindow.setState(true);

                    }
                } catch (NumberFormatException f) {
                    System.out.println("Uepa, casilla vacía");
                }
            }
        });

        mainPanel.add(configPanel);
        add(mainPanel);
        setVisible(true);
    }
    public PrintSpooler getPrintSpooler(){
        return printSpooler;
    }


    public FileToPrint createFileToPrint(){
            //archiveController.createArchive(Integer.parseInt(pagesField.getText()),nameField.getText());
            archiveController.configurationFile(Integer.parseInt(pagesField.getText()),nameField.getText(),validateColor(processString(colorBox.getSelectedItem())),processString(sizeBox.getSelectedItem()),pagesToPrintField.getText());
       return archiveController.getArchiveController();
    }

    public String processString(Object object) {
        String itemToString = "";
        try {
            itemToString = Objects.requireNonNull(object).toString();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return itemToString;
    }

    public boolean validateInput() {
        boolean flag = true;
        String name = nameField.getText();
        String pages = pagesField.getText();
        String pagesToPrint = pagesToPrintField.getText();
            if (name == null || name.trim().isEmpty()) {
                myWarningMessages.inputWarning("Nombre vacío");
            } else {
                if (name.contains(".") && (name.contains("docs") || name.contains("docx") || name.contains("pdf") || name.contains("pptc") || name.contains("png") || name.contains("jpg"))) {
                    flag = true;
                } else {
                    myWarningMessages.inputWarning("Extensión no válida");
                }
            }
            if (pages == null || pages.trim().isEmpty()) {
                myWarningMessages.inputWarning("Número de páginas vacío");
            } else {
                if (Integer.parseInt(pages) > 0&&Integer.parseInt(pages) <=250) {
                    flag = true;
                } else {
                    myWarningMessages.inputWarning("Número de paginas inválida");
                }
            }
            if (pagesToPrint == null || pagesToPrint.trim().isEmpty()) {
                myWarningMessages.inputWarning("rango inválido");
            } else {
                flag = false;
            }
            return flag;
    }




    public boolean validateColor(Object object){
        if (object.equals("Blanco y negro")){
            return false;
        }else {
            return true;
        }
    }

    public void restartFields(){
        nameField.setText("");
        pagesField.setText("");
        colorBox.setSelectedIndex(0);
        sizeBox.setSelectedIndex(0);
        priorityBox.setSelectedIndex(0);
        pagesToPrintField.setText("");
    }

    public void setPrintSpooler(PrintSpooler printSpooler1){
        this.printSpooler = printSpooler1;
    }


}

