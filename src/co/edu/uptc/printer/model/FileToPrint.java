package co.edu.uptc.printer.model;

public class FileToPrint {
    private Archive archive;
    private boolean color;
    private String size;
    private String numberPages;

    public FileToPrint() {
    }

    public FileToPrint(Archive archive, boolean color, String size, String numberPages) {
        this.archive = archive;
        this.color = color;
        this.size = size;
        this.numberPages = numberPages;
    }

    public String getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(String numberPages) {
        this.numberPages = numberPages;
    }

    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


}
