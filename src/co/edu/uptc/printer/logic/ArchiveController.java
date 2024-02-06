package co.edu.uptc.printer.logic;

import co.edu.uptc.printer.model.Archive;
import co.edu.uptc.printer.model.FileToPrint;

public class ArchiveController {
    private Archive archive;
    private FileToPrint fileToPrint;

    public ArchiveController() {
        this.archive=new Archive();
        this.fileToPrint=new FileToPrint();
    }


    public void createArchive(int pages, String name){
        String[] aux=name.split("\\.");
        this.archive=new Archive(pages,aux[0],aux[1]);
    }
    public void configurationFile(int pages, String name, boolean color, String size, String numberPages){
        createArchive(pages,name);
        this.fileToPrint=new FileToPrint(this.archive,color,size,numberPages);
    }
    public FileToPrint getArchiveController(){
        return this.fileToPrint;
    }
}