package com.example.administrator.litepaldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.nio.BufferOverflowException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button create_button;
    private Button add_button;
    private Button update_button;
    private Button delete_button;
    private Button query_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create_button = (Button) findViewById(R.id.create_button);
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });

        add_button = (Button) findViewById(R.id.add_data);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
            }
        });

        update_button = (Button) findViewById(R.id.update_data);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Lost Symbol");
                book.setAuthor("Dan Brown");
                book.setPages(510);
                book.setPrice(19.95);
                book.setPress("Unknow");
                book.save();
                book.setPress("Anchor");
                book.setPrice(14.95);
                book.updateAll("name = ? and author = ?","The Lost Symbol","Dan Brown");
            }
     });

        delete_button = (Button) findViewById(R.id.delete_data);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class,"price < ?","15");
            }
        });

        query_button = (Button) findViewById(R.id.query_data);
        query_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = DataSupport.findAll(Book.class);
                for (Book book:books)
                {
                    Log.d("MainActivity","book name is " + book.getName());
                    Log.d("MainActivity","book author is " + book.getAuthor());
                    Log.d("MainActivity","book pages is " + book.getPages());
                    Log.d("MainActivity","book price is " + book.getPrice());
                    Log.d("MainActivity","book press is " + book.getPress());
                }
            }
        });

    }
}
