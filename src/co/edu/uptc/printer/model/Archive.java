package co.edu.uptc.printer.model;

public class Archive {
    private int pages;
    private String name;
    private String extension;

    public Archive() {
    }

    public Archive(int pages, String name, String extension) {
        this.pages = pages;
        this.name = name;
        this.extension = extension;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
