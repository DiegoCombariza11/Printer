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
                        "\n 1. Agregar archivo a la cola" +
                        "\n 2. ver cola" +
                        "\n 3. Imprimir" +
                        "\n 4: Recargar tinta" +
                        "\n 5: Recargar hojas" +
                        "\n 6: Mostrar niveles de tinta" +
                        "\n 7: Mostrar número de hojas" +
                        "\n 8: Salir");
                int option = Integer.parseInt(input);
                switch (option) {
                    case 1: {
                        boolean confirm = false;
                        String name = "";
                        while (!confirm) {
                            name = JOptionPane.showInputDialog("Digite el nombre del archivo con su extension (extensiones validas: .docs, .docx, .pdf, .pptx, .png, .jpg)");
                            if (name.contains(".") && (name.contains("docs") || name.contains("docx") || name.contains("pdf") || name.contains("pptc") || name.contains("png") || name.contains("jpg"))) {
                                confirm = true;
                            } else {
                                myWarningMessages.inputWarning("Extensión no válida");
                            }
                        }
                        confirm = false;
                        int pages = 0;
                        while (!confirm) {
                            pages = Integer.parseInt(JOptionPane.showInputDialog("Digite el numero de paginas del archivo"));
                            if (pages > 0&&pages <=250) {
                                confirm = true;
                            } else {
                                myWarningMessages.inputWarning("Número de paginas inválida");
                            }
                        }
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
                        if (amountSheetsDecision.equals("todas")) {
                            rangeSheets = "0-" + pages;
                        }else if (amountSheetsDecision.equals("un rango")) {
                            while (!confirm) {
                                rangeSheets = JOptionPane.showInputDialog("Digite el numero de hojas separado por un guion");
                                if (rangeSheets.matches("[0-9-]+")&& !(printSpooler.numberPages(rangeSheets,pages)==0)) {
                                    confirm = true;
                                } else {
                                    myWarningMessages.inputWarning("Rango no válido");
                                }
                            }
                        }else if (amountSheetsDecision.equals("# de paginas especificas")) {
                            while (!confirm) {
                                rangeSheets = JOptionPane.showInputDialog("Digite el numero de hojas separado por comas");
                                if (rangeSheets.matches("[0-9,]+")&&!(printSpooler.numberPages(rangeSheets,pages)==0) ) {
                                    confirm = true;
                                } else {
                                    myWarningMessages.inputWarning("hojas no validas");
                                }
                            }
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
                    case 5: {
                        //recargar hojas
                        printSpooler.getPrinterController().addSheets();
                        break;
                    }
                    case 6: {
                        myWarningMessages.showInkLevel(String.valueOf(printSpooler.getPrinterController().showInkPercentage()));
                        break;
                    }
                    case 7:{
                        myWarningMessages.showSheetsAmount(String.valueOf(printSpooler.getPrinterController().showSheetsAmount()));
                        break;
                    }
                    case 8: {
                        flag = true;
                    }
                }
            } catch (NumberFormatException | NullPointerException e) {
                int input = (JOptionPane.showConfirmDialog(null, "Desea salir?"));
                if (input == 0) {
                    JOptionPane.showMessageDialog(null, "Adios");
                    flag = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Continue con lo suyo");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        } while (!flag);


    }
}
