package eduhollcs184assignments.ucsb.cs.httpwww.xsundrawingmultitouch;

/**
 * Created by sunxiao on 10/19/17.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import static java.util.Arrays.asList;
import java.util.ArrayList;


public class CanvasView extends SurfaceView implements SurfaceHolder.Callback {
    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Add the class itself as the SurfaceView's SurfaceHolder's callback
        // This will allow the surfaceCreated, Changed, and Destroyed methods to be called
        getHolder().addCallback(this);
    }

    // The bitmap to store our painting
    Bitmap painting;
    // The canvas to draw to our painting
    Canvas paintingCanvas;

    // Keep track if the surface exists or not
    boolean surfaceExists = false;
    //store X cord of finger
    ArrayList<Float> oldX = new ArrayList<>(asList(0.0f,0.0f,0.0f,0.0f,0.0f));
    //store Y cord of finger
    ArrayList<Float> oldY = new ArrayList<>(asList(0.0f,0.0f,0.0f,0.0f,0.0f));
    ArrayList<Integer> fingerList = new ArrayList<Integer>(asList(-1,-1,-1,-1,-1));
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        painting = Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888);
        //painting = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        surfaceExists = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
       //call a template
        Bitmap tempt = painting.copy(Bitmap.Config.ARGB_8888,false);
        //create the max lengh to covver the whole page
        int maxLength = Math.max(painting.getWidth(),painting.getHeight());
        int maxLengthNow = Math.max(width, height);
        int Max = Math.max(maxLength,maxLengthNow);
        painting = Bitmap.createBitmap(Max,Max, Bitmap.Config.ARGB_8888);
        // Let painting canvas draw to the bitmap
        paintingCanvas = new Canvas(painting);
        // Fill the canvas with white
        paintingCanvas.drawColor(Color.WHITE);

        // Draw a circle on the bitmap
        Paint paint = new Paint();
        paintingCanvas.drawBitmap(tempt,0.0f,0.0f,null);
        //paint.setColor(Color.BLACK);
        //paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //paintingCanvas.drawCircle(100, 100, 50, paint);

        refreshView();
    }

    public void refreshView() {
        if(!surfaceExists) return;

        // Draw the painting bitmap to the SurfaceView
        Canvas surfaceCanvas = getHolder().lockCanvas();
        // surfaceCanvas is only valid between lockCanvas and unlockCanvasAndPost
        surfaceCanvas.drawBitmap(painting, 0, 0, null);
        getHolder().unlockCanvasAndPost(surfaceCanvas);
        // unlockCanvasAndPost will show the SurfaceView. invalidate() is not necessary here.
        // invalidate();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        surfaceExists = false;
    }
    //float oldX;
    //float oldY;

    int[] fingerColors = new int[5];
    //private int mActivePointerId;

    /*public CanvasView(Context context) {
        super(context);
    }
    public CanvasView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }*/


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int fingerCount = event.getPointerCount();
        int fingerindex = 0;
        int id;

        Paint paint = new Paint();
        //paint.setColor(fingerColors[1]);
        paint.setStyle(Paint.Style.FILL);

        paint.setStrokeWidth(10);

        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:{
                int index = event.getActionIndex();
                id = event.getPointerId(index);
                if(index>3){
                    if(fingerList.size()<index+2) {
                        fingerList.add(-1);
                        oldX.add(0.0f);
                        oldY.add(0.0f);
                    }
                }


                for (int k = 1; k < fingerList.size()+1; k++) {
                    if (fingerList.get(k) == -1) {
                        fingerList.set(k, id);
                        fingerindex = k;
                        if(fingerindex>4){
                            paint.setColor(Color.BLACK);
                        }
                        else{
                            paint.setColor(fingerColors[fingerindex]);
                        }
                        break;

                    }
                }

                paintingCanvas.drawCircle(event.getX(index), event.getY(index), 5, paint);
                oldX.set(fingerindex, event.getX(index));
                oldY.set(fingerindex, event.getY(index));
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                for (int i = 0; i < fingerCount; i++) {
                    id = event.getPointerId(i);
                    for (int j = 1; j < fingerList.size()+1; j++) {
                        if (id == fingerList.get(j)) {
                            fingerindex = j;
                            if(fingerindex>4){
                                paint.setColor(Color.BLACK);
                            }
                            else {
                                paint.setColor(fingerColors[fingerindex]);
                            }
                            break;
                        }
                    }
                    paintingCanvas.drawLine(oldX.get(fingerindex), oldY.get(fingerindex), event.getX(i), event.getY(i), paint);
                    paintingCanvas.drawCircle(event.getX(i), event.getY(i), 5, paint);
                    oldX.set(fingerindex, event.getX(i));
                    oldY.set(fingerindex, event.getY(i));
                }
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP: {
                int up = event.getActionIndex();
                int upId = event.getPointerId(up);
                for (int m = 0; m < fingerList.size(); m++) {
                    if (upId == fingerList.get(m)) {
                        fingerList.set(m, -1);
                        oldX.set(m, 0.0f);
                        oldY.set(m, 0.0f);

                    }
                }
                break;
            }
        }
        refreshView();

        return true;
    }
    public void clear(){
        paintingCanvas.drawColor(Color.WHITE);
        refreshView();
    }

}
