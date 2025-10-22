package com.example.lab02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Panel panel;
    Paint bg1 = new Paint();
    Paint bg2 = new Paint();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        panel = new Panel(this);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        registerForContextMenu(panel); // zarejestrowanie menu kontekstowego
        setContentView(panel);
    }


    // Miało być menu kontekstowe więc to się nie przyda bo jest dla menu opcji
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inf = getMenuInflater();
//        inf.inflate(R.menu.main, menu);
//
//        return true;
//    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // W zadaniu 1. wyraźnie powiedziano o mennu kontekstowym więc i tu nie może być menu opcji
        var id = item.getItemId();

        // Musiałem zrobić takiego okropnego ifa ponieważ switch mi nie działał na caseach z R.id.author itp.
        if(id == R.id.author) {
            var ctx = getApplicationContext();
            var txt = "Twórca: Jan Matysik";
            var time = Toast.LENGTH_SHORT;

            var toast = Toast.makeText(ctx, txt, time);
            toast.setGravity(Gravity.BOTTOM | Gravity.BOTTOM, 0, 0);
            toast.show();

            return true;
        } else if(id == R.id.exit) {
            finish();

            return true;
        } else if(id == R.id.color1) {
            bg1.setColor(Color.BLACK);
            bg2.setColor(Color.WHITE);
            panel.postInvalidate();

            return true;
        } else if(id == R.id.color2) {
            bg1.setColor(Color.RED);
            bg2.setColor(Color.YELLOW);
            panel.postInvalidate();

            return true;
        } else {
            return super.onContextItemSelected(item);
//            return super.onOptionsItemSelected(item); // Teraz powinno się wywoływać odpowiednią metodę czyli nie tą dla menu opcji
        }
    }



    class Panel extends View {
        public Panel(Context ctx) {
            super(ctx);
            bg1.setColor(Color.WHITE);
            bg2.setColor(Color.BLACK);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            float w = panel.getWidth();
            float h = panel.getHeight();
            float a = Math.min(w, h);

            canvas.drawColor(Color.GRAY);

            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    if((i + j) % 2 == 0) canvas.drawRect(i * a / 8, j * a / 8, (i + 1) * a / 8, (j + 1) * a / 8, bg1);
                    else canvas.drawRect(i * a / 8, j * a / 8, (i + 1) * a / 8, (j + 1) * a / 8, bg2);
                }
            }
        }
    }
}