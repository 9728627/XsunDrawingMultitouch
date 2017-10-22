package eduhollcs184assignments.ucsb.cs.httpwww.xsundrawingmultitouch;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

        final Button button1 = (Button) findViewById(R.id.button1);
        final Button button2 = (Button) findViewById(R.id.button2);
        final Button button3 = (Button) findViewById(R.id.button3);
        final Button button4 = (Button) findViewById(R.id.button4);
        ColorDrawable buttonColor1 = (ColorDrawable) button1.getBackground();
        ColorDrawable buttonColor2 = (ColorDrawable) button2.getBackground();
        ColorDrawable buttonColor3 = (ColorDrawable) button3.getBackground();
        ColorDrawable buttonColor4 = (ColorDrawable) button4.getBackground();
        int button1id = buttonColor1.getColor();
        int button2id = buttonColor2.getColor();
        int button3id = buttonColor3.getColor();
        int button4id = buttonColor4.getColor();

        CanvasView myCanvas = (CanvasView) findViewById(R.id.Mycanvas);
        myCanvas.fingerColors[1]= button1id;
        myCanvas.fingerColors[2] = button2id;
        myCanvas.fingerColors[3] = button3id;
        myCanvas.fingerColors[4] = button4id;

    }

    public void changeColor(View view) {
        final Button button1 = (Button) findViewById(R.id.button1);
        final Button button2 = (Button) findViewById(R.id.button2);
        final Button button3 = (Button) findViewById(R.id.button3);
        final Button button4 = (Button) findViewById(R.id.button4);
        ColorDrawable buttonColor1 = (ColorDrawable) button1.getBackground();
        ColorDrawable buttonColor2 = (ColorDrawable) button2.getBackground();
        ColorDrawable buttonColor3 = (ColorDrawable) button3.getBackground();
        ColorDrawable buttonColor4 = (ColorDrawable) button4.getBackground();
        int button1id = buttonColor1.getColor();
        int button2id = buttonColor2.getColor();
        int button3id = buttonColor3.getColor();
        int button4id = buttonColor4.getColor();
        /*TypedArray colorArray = getResources().obtainTypedArray(R.array.colorArray);
        int[] colarray = new int[colorArray.length()];

        for (int i = 0; i < colorArray.length(); i++) {
            colarray[i] = colorArray.getColor(i, 0);
        }
        colorArray.recycle();*/




        //int randomColor = (int)(Math.random()*colorArray.length());
        //ColorDrawable randColor = (ColorDrawable)(colarray[randomColor]);

        //if (randColor == buttonColor1) {
        //button1.setBackgroundColor(colarray[randomColor]);
        //}

        int[] androidColors = getResources().getIntArray(R.array.colorArray);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];


        if (randomAndroidColor == button1id || randomAndroidColor == button2id || randomAndroidColor == button3id || randomAndroidColor == button4id) {
            changeColor(view);
            //view.setBackgroundColor(randomAndroidColor);
            //int a =1;
        } else {
            view.setBackgroundColor(randomAndroidColor);
        }
        //button1.setBackgroundColor(randomAndroidColor);

        CanvasView myCanvas = (CanvasView) findViewById(R.id.Mycanvas);

        ColorDrawable buttonColor11 = (ColorDrawable) button1.getBackground();
        ColorDrawable buttonColor22 = (ColorDrawable) button2.getBackground();
        ColorDrawable buttonColor33 = (ColorDrawable) button3.getBackground();
        ColorDrawable buttonColor44 = (ColorDrawable) button4.getBackground();

        int button11id = buttonColor11.getColor();
        int button22id = buttonColor22.getColor();
        int button33id = buttonColor33.getColor();
        int button44id = buttonColor44.getColor();

        myCanvas.fingerColors[1]= button11id;
        myCanvas.fingerColors[2] = button22id;
        myCanvas.fingerColors[3] = button33id;
        myCanvas.fingerColors[4] = button44id;
        //myCanvas.fingerColors[5] = Color.BLACK;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TypedArray colorArray = getResources().obtainTypedArray(R.array.colorArray);
        if (item.getItemId() == R.id.action_send) {
            //TextView text = (TextView)findViewById(R.id.textView);
            //text.setText("send selected");
            //mBitmap.recycle();


            CanvasView myCanvas = (CanvasView) findViewById(R.id.Mycanvas);
            myCanvas.clear();
            return true;
            //do something;
        }
        return super.onOptionsItemSelected(item);
    }





}
