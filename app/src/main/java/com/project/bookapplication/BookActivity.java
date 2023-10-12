package com.project.bookapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public  static final String BOOK_ID_KEY = "bookId";

    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnToAddToCurrentlyReading, btnToAddToWantToRead, btnToAddToAlreadyRead, btnToAddToFavorite;
    private ImageView bookImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        intiViews();
//        String longDescription = "The story is based on the life of a girl named Tara. She is a simple girl living in her village spending her time playing with other children of same village. While the story goes on, she gets married at the age of seven, the very age at which she does not even understand the meaning of marriage. The story in the novel is of the time period 1850-1950, when child marriage used to be very common in Nepalese society.\n" +
//                "\n" +
//                "Tara's husband has to go for his studies to Banaras, where he dies. Tara, a nine years old girl, is now bound to live her life as a child widow. The story continues and many difficulties come one by one in Tara's life. She comes back from her husband's home to her father's home. She tries to live her life peacefully trying to forget all the pain that life gave her when she was a young child. Later on, her mother dies and she has to take over all the responsibilities of the house.\n" +
//                "\n" +
//                "At her adult age, she leaves her father's house as she gets abused by her stepmother and moves to Devghat, a religious place for Hindus. There she makes a small hut and starts to live a long, boring life. The story mainly tries to reveal the terrible cultural practice called child marriage and child widowhood. This story portrays the pain of a child widow living her whole life without company. The novel best tries to shows bitter reality of Nepalese society where women have to suffer very much before the eradication of many evil practice.";
//
//        //Get the incoming data form the recycler view in here.
//        Book book = new Book(1,
//                "Karnali Blues",
//                "Buddi Sagar",
//                300,
//                "https://upload.wikimedia.org/wikipedia/en/8/86/Karnali_Blues_by_Buddhisagar.jpg",
//                "The novel depicts the father-son relationship in a family from Far-western region of Nepal. The novels begins with birth of Brisha Bahadur, the narrator of the novel. Brisha Bahadur narrates his father struggles. The novel is divided into eleven days. Brisha Bahadur is taking care of his father who is sick in those eleven days and he reminisces his past with his father.",
//                longDescription);

        Intent intent = getIntent();
        if(null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if(bookId != -1){
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if(null!=incomingBook){
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToRead(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                }
            }
        }
    }

    private void handleFavoriteBooks(final Book book) {
        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavoriteBooks();
        boolean existInFavoriteBooks = false;
        for(Book b: favoriteBooks){
            if(b.getId() == book.getId()){
                existInFavoriteBooks = true;
            }
        }

        if(existInFavoriteBooks){
            btnToAddToFavorite.setEnabled(false);
        }
        else{
            btnToAddToFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToFavoriteBooks(book)){
                        Toast.makeText(BookActivity.this, "Book added.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavoriteBooks.class);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(BookActivity.this, "Something went wrong. Try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReadingBooks(Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();
        boolean existInCurrentlyReadingBooks = false;
        for(Book b: currentlyReadingBooks){
            if(b.getId() == book.getId()){
                existInCurrentlyReadingBooks = true;
            }
        }

        if(existInCurrentlyReadingBooks){
            btnToAddToCurrentlyReading.setEnabled(false);
        }
        else{
            btnToAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToCurrentlyReadingBooks(book)){
                        Toast.makeText(BookActivity.this, "Book added.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingBooks.class);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(BookActivity.this, "Something went wrong. Try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToRead(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();
        boolean existInWantToReadBooks = false;
        for(Book b: wantToReadBooks){
            if(b.getId() == book.getId()){
                existInWantToReadBooks = true;
            }
        }

        if(existInWantToReadBooks){
            btnToAddToWantToRead.setEnabled(false);
        }
        else{
            btnToAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToWantToRead(book)){
                        Toast.makeText(BookActivity.this, "Book added.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(BookActivity.this, "Something went wrong. Try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleAlreadyRead(final Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();
        boolean existInAlreadyReadBooks = false;
        for(Book b: alreadyReadBooks){
            if(b.getId() == book.getId()){
                existInAlreadyReadBooks = true;
            }
        }

        if(existInAlreadyReadBooks){
            btnToAddToAlreadyRead.setEnabled(false);
        }
        else{
            btnToAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Book added.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(BookActivity.this, "Something went wrong. Try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(bookImage);
    }

    private void intiViews() {
        txtAuthor = findViewById(R.id.bookAuthor);
        txtBookName = findViewById(R.id.bookName);
        txtPages = findViewById(R.id.bookPages);
        txtDescription = findViewById(R.id.bookDescription);

        btnToAddToAlreadyRead = findViewById(R.id.btnToAddToAlreadyRead);
        btnToAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnToAddToFavorite = findViewById(R.id.btnAddToFavourites);
        btnToAddToWantToRead = findViewById(R.id.btnAddToWantToRead);

        bookImage = findViewById(R.id.bookImageView);

    }
}