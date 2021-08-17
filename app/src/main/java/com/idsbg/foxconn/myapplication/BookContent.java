package com.idsbg.foxconn.myapplication;

        import android.content.Intent;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class BookContent {
    //定义一个内部类作为系统的业务对象
    public static class Book{
        public Integer id;
        public String title;
        public String desc;

        public Book(Integer id, String title, String desc) {
            this.id = id;
            this.title = title;
            this.desc = desc;
        }

        @Override
        public String toString() {
            return title;
        }
        //利用list集合记录系统包括的book对象
        public static List<Book> ITEMS=new ArrayList<Book>();
        //利用Map集合记录系统包括的book对象
        public static Map<Integer,Book> ITEM_MAP=new HashMap<Integer, Book>();
        static {
            //使用静态初始化代码。将Book对象添加到list集合、map集合中
            addItem(new Book(1,"呼啸山庄","英国女作家勃朗特姐妹之一艾米莉·勃朗特的作品"));
            addItem(new Book(2,"傲慢与偏见","英国女小说家简·奥斯汀创作的长篇小说"));
            addItem(new Book(3,"简爱","(英)夏洛蒂·勃朗特(Charlotte Bronte)原著"));
        }
        private static void addItem(Book book){
            ITEMS.add(book);
            ITEM_MAP.put(book.id,book);
        }
    }

}
