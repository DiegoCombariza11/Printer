package co.edu.uptc.printer.model;

public class FileToPrint {
    private Archive archive;
    private boolean color;
    private String size;
    private int priority;
    private int numberPages;

    public FileToPrint() {
    }

    public FileToPrint(Archive archive, boolean color, String size, int priority, int numberPages) {
        this.archive = archive;
        this.color = color;
        this.size = size;
        this.priority = priority;
        this.numberPages = numberPages;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(int numberPages) {
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
