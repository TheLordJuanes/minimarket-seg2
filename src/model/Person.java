package model;

public class Person {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    public final static String TYPE_DOCUMENT_CC = "CC";
    public final static String TYPE_DOCUMENT_TI = "TI";
    public final static String TYPE_DOCUMENT_PP = "PP";
    public final static String TYPE_DOCUMENT_CE = "CE";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private String typeDocument;
    private String id;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public Person(String typeDocument, String id) {
        this.typeDocument = typeDocument;
        this.id = id;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}