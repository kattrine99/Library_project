package sample;


import java.text.SimpleDateFormat;
import java.util.Date;

public class FeeInfo {
    private String UserID;
    private String UserName;
    private String BookID;
    private String BookName;
    private Date IDate;
    private Date FinalDate;
    private Double Fee;

    FeeInfo( String UserID, String UserName, String BookID, String BookName,  Date IDate, Date FinalDate, Double Fee){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        this.UserID = UserID;
        this.UserName = UserName;
        this.BookID = BookID;
        this.BookName = BookName;
        this.IDate = IDate;
        this.FinalDate= FinalDate;
        this.Fee=Fee;
    }
    FeeInfo(){}


    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String bookID) {
        BookID = bookID;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }
    public Date getIDate(){return IDate;}
    public void setIDate(Date iDate){
        IDate=iDate;
    }
    public Date getFinalDate(){return FinalDate;}
    public void setFinalDate(Date finalDate){
        FinalDate=FinalDate;
    }

    public Double getFee() {
        return Fee;
    }

    public void setFee(Double fee) {
        Fee = fee;
    }
}