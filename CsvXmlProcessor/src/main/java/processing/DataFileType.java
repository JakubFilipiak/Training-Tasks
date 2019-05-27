package processing;

/**
 * Created by Jakub Filipiak on 17.05.2019.
 */
public enum DataFileType {

    CSV(".csv"),
    XML(".xml"),
    TXT(".txt"),
    UNKNOWN(".unknown");

    private String type;

    DataFileType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static DataFileType fromString(String type) {
        for (DataFileType dataFileType : DataFileType.values()) {
            if (dataFileType.toString().equals(type)) {
                return dataFileType;
            }
        }
        return UNKNOWN;
    }
}
