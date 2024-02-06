package co.edu.uptc.printer.view;

import co.edu.uptc.printer.logic.ArchiveController;
import co.edu.uptc.printer.logic.PrintSpooler;

import javax.swing.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args) {
        View myView = new View();
        myView.menu();
    }

}