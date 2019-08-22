package team.qiruan.domain;

/**
 * File
 */
public class File {

    Long id;
    String filename;
    String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "File [filename=" + filename + ", id=" + id + ", type=" + type + "]";
    }
    
}