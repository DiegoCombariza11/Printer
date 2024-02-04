package co.edu.uptc.printer.model;

public class FileToPrint {
    private Archive archive;
    private boolean color;
    private String size;
    private int priority;

    public FileToPrint(Archive archive, boolean color, String size, int priority) {
        this.archive = archive;
        this.color = color;
        this.size = size;
        this.priority = priority;
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
