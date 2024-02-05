package co.edu.uptc.printer.view;

import co.edu.uptc.printer.logic.ArchiveController;
import co.edu.uptc.printer.logic.PrintSpooler;

import javax.swing.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) {
        final boolean[] exit = {false};
        ArchiveController archive=new ArchiveController();
        PrintSpooler printSpooler=new PrintSpooler();
        String colorElection[]={"blanco y negro","color"};
        String size[]={"carta","oficio"};
        String priorityElection[]={"no importa","Rapido"};
        String amountSheetsElection[]={"todas","un rango","# de paginas especificas"};
       /* Thread printing = new Thread(() -> {
            while (!exit[0]) {
                if (!printSpooler.printing().equals("")) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(null, printSpooler.printing());
                    });
                }
                try {
                    // Esperar antes de verificar nuevamente
                    Thread.sleep(1000); // Puedes ajustar el tiempo de espera según tus necesidades
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });a
        printing.start();*/
        do {
            JOptionPane.showMessageDialog(null, printSpooler.printing());
            try {
                String aux=JOptionPane.showInputDialog("Bienvenidos" +
                        "\n 1. agregar archivo a la cola" +
                        "\n 2. ver cola");
                if(aux==null){
                    exit[0]=true;
                    break;
                }
                int decision=Integer.parseInt(aux);
                switch (decision){
                    case 1:{
                        boolean confirm=false;
                        String name="";
                        while (!confirm) {
                            name=JOptionPane.showInputDialog("Digite el nombre del archivo con su extension (ejemplo.exe)");
                            if(name.contains(".")){
                                confirm=true;
                            }
                        }
                        int pages=Integer.parseInt(JOptionPane.showInputDialog("Digite el numero de paginas del archivo"));
                        int colorDecision=JOptionPane.showOptionDialog(null,"Tipo de color de la impresion","Color",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,colorElection,colorElection[0]);
                        boolean color=true;
                        if (colorDecision==0){
                            color=false;
                        }
                        int sizeDecision=JOptionPane.showOptionDialog(null,"Tamaño de la hoja","Hoja",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,size,size[0]);
                        int priorityDecision=JOptionPane.showOptionDialog(null,"Prioridad del archivo","Cola",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,priorityElection,priorityElection[0]);
                        String amountSheetsDecision=(String) JOptionPane.showInputDialog(null,"Escoja las hojas a imprimir","Cantidad de hojas",JOptionPane.QUESTION_MESSAGE, null,amountSheetsElection,amountSheetsElection[0]);
                        confirm=false;
                        String rangeSheets="";
                        if (!(amountSheetsDecision.equals("todas"))){
                            while (!confirm) {
                                rangeSheets = JOptionPane.showInputDialog("Digite el numero de hojas separado por comas si es especifico, si es un rango por un guion");
                                if (rangeSheets.contains("-") || rangeSheets.contains(",")) {
                                    confirm = true;
                                }
                            }
                        }else {
                            rangeSheets="0-"+pages;
                        }
                        archive.configurationFile(pages,name,color,size[sizeDecision],rangeSheets);
                        printSpooler.addFileToTail(archive.getArchiveController(),priorityElection[priorityDecision]);
                    }break;
                    case 2:{
                        JOptionPane.showMessageDialog(null,printSpooler.showTails());
                    }break;
                }
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"solo numeros");
            }
        }while (!exit[0]);
       /* try {
            printing.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

}