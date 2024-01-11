package sample;

public class Book {
    private String ID;
    private String BookName;
    private String BookDesc;
    private Integer BookQuantity;
    private Double BookFee;
    Book(String ID,String BookName,String BookDesc,Integer BookQuantity,Double BookFee){
        this.ID=ID;
        this.BookName = BookName;
        this.BookDesc= BookDesc;
        this.BookQuantity = BookQuantity;
        this.BookFee =BookFee;
    }
    Book(){
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookDesc() {
        return BookDesc;
    }

    public void setBookDesc(String bookDesc) {
        BookDesc = bookDesc;
    }

    public Integer getBookQuantity() {
        return BookQuantity;
    }

    public void setBookQuantity(Integer bookQuantity) {
        BookQuantity = bookQuantity;
    }

    public Double getBookFee() {
        return BookFee;
    }

    public void setBookFee(Double bookFee) {
        BookFee = bookFee;
    }
}
