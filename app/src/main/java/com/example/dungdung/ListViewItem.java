package com.example.dungdung;

/*
public class ListViewItem {

    private String titleStr ;
    private String descStr ;
    private String partStr ;


    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setPart(String part) {
        partStr = part ;
    }


    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public String getPart() {
        return this.partStr ;
    }
}
*/

public class ListViewItem{

    private String bookName;
    private String bookPart;
    private String bookDo;

    public void setName(String name){
        bookName=name;
    }
    public void setPart(String part){
        bookPart=part;
    }
    public void setDo(String bdo){ //do 예약어라 안됨^^
        bookDo=bdo;
    }

    public String getName(){
        return this.bookName;
    }
    public String getPart(){
        return this.bookPart;
    }
    public String getDo(){
        return this.bookDo;
    }
    public ListViewItem( String bookName, String bookPart, String bookDo) {

        this.bookName = bookName;
        this.bookPart = bookPart;
        this.bookDo = bookDo;
    }

}
