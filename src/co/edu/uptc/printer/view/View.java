package co.edu.uptc.printer.view;

import co.edu.uptc.printer.logic.ArchiveController;
import co.edu.uptc.printer.logic.PrintSpooler;
import co.edu.uptc.printer.logic.PrinterController;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.concurrent.ForkJoinPool;

public class View {

    final boolean[] exit = {false};
    ArchiveController archive = new ArchiveController();
    PrintSpooler printSpooler = new PrintSpooler();
    PrinterController myPrinterControlelr = new PrinterController();
    WarningMessages myWarningMessages = new WarningMessages();
    String colorElection[] = {"blanco y negro", "color"};
    String size[] = {"carta", "oficio"};
    String priorityElection[] = {"no importa", "Rapido"};
    String amountSheetsElection[] = {"todas", "un rango", "# de paginas especificas"};

    public void menu() {
        boolean flag = false;
        do {
            try {
                String input = JOptionPane.showInputDialog("Bienvenidos" +
                        "\n 1. agregar archivo a la cola" +
                        "\n 2. ver cola" +
                        "\n 3. Imprimir" +
                        "\n 4: Recargar tinta" +
                        "\n 5: Recargar hojas");
                int option = Integer.parseInt(input);
                switch (option) {
                    case 1: {
                        boolean confirm = false;
                        String name = "";
                        while (!confirm) {
                            name = JOptionPane.showInputDialog("Digite el nombre del archivo con su extension (ejemplo.exe)");
                            if (name.contains(".")) {
                                confirm = true;
                            }
                        }
                        int pages = Integer.parseInt(JOptionPane.showInputDialog("Digite el numero de paginas del archivo"));
                        int colorDecision = JOptionPane.showOptionDialog(null, "Tipo de color de la impresion", "Color", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, colorElection, colorElection[0]);
                        boolean color = true;
                        if (colorDecision == 0) {
                            color = false;
                        }
                        int sizeDecision = JOptionPane.showOptionDialog(null, "Tamaño de la hoja", "Hoja", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, size, size[0]);
                        int priorityDecision = JOptionPane.showOptionDialog(null, "Prioridad del archivo", "Cola", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, priorityElection, priorityElection[0]);
                        String amountSheetsDecision = (String) JOptionPane.showInputDialog(null, "Escoja las hojas a imprimir", "cantidad de hojas", JOptionPane.QUESTION_MESSAGE, null, amountSheetsElection, amountSheetsElection[0]);
                        confirm = false;
                        String rangeSheets = "";
                        if (!(amountSheetsDecision.equals("todas"))) {
                            while (!confirm) {
                                rangeSheets = JOptionPane.showInputDialog("Digite el numero de hojas separado por comas si es especifico, si es un rango por un guion");
                                if (rangeSheets.contains("-") || rangeSheets.contains(",")) {
                                    confirm = true;
                                }
                            }
                        } else {
                            rangeSheets = "1-" + pages;
                        }
                        archive.configurationFile(pages, name, color, size[sizeDecision], rangeSheets);
                        printSpooler.addFileToTail(archive.getArchiveController(), priorityElection[priorityDecision]);
                        break;
                    }
                    case 2: {
                        JOptionPane.showMessageDialog(null, printSpooler.showTails());
                        break;
                    }
                    case 3: {
                        printSpooler.printing();
                        break;

                    }
                    case 4: {
                        printSpooler.getPrinterController().reloadInk();
                        break;
                    }
                    case 6: {
                        flag = true;
                    }
                }
            }catch (NumberFormatException e){
                int input = (JOptionPane.showConfirmDialog(null, "Desea salir?"));
                if (input==0){
                    JOptionPane.showMessageDialog(null, "Adios, popó");
                    flag = true;
                }
                else {
                    JOptionPane.showMessageDialog(null, "oka, opción inválida, gil");
                }
            }

        } while (!flag);


    }
}
