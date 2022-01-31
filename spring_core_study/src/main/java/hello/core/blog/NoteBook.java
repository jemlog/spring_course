package hello.core.blog;


public class NoteBook {

    private static NoteBook noteBook = new NoteBook();

    public static NoteBook getInstance()
    {
        return noteBook;
    }

    private NoteBook(){};


}
