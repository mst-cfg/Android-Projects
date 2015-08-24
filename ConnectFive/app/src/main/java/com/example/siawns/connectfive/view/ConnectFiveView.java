package com.example.siawns.connectfive.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.siawns.connectfive.R;
import com.example.siawns.connectfive.model.ConnectFiveModel;

public class ConnectFiveView extends View {

    private Paint paintBackGround;
    private Paint paintLine;
    private Paint paintX;
    private Paint paintO;
    private List<PointF> points = new ArrayList<PointF>();


    public ConnectFiveView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintBackGround = new Paint();
        paintBackGround.setStyle(Paint.Style.FILL);
        paintBackGround.setColor(Color.TRANSPARENT);

        paintLine = new Paint();
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);
        paintLine.setColor(Color.BLACK);

        paintX = new Paint();
        paintX.setStyle(Paint.Style.STROKE);
        paintX.setStrokeWidth(5);
        paintX.setColor(Color.RED);

        paintO = new Paint();
        paintO.setStyle(Paint.Style.STROKE);
        paintO.setStrokeWidth(5);
        paintO.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(
                0, 0,
                getWidth(), getHeight(),
                paintBackGround);

        drawGameArea(canvas);

        drawPlayers(canvas);

    }

    private void drawGameArea(Canvas canvas) {

        canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);

        for (int i = 1; i < 15; i++) {
            canvas.drawLine(0, i * getHeight() / 15, getWidth(), i * getHeight() / 15, paintLine);
        }

        for (int i = 1; i < 15; i++) {
            canvas.drawLine(i * getWidth() / 15, 0, i * getWidth() / 15, getHeight(), paintLine);
        }

    }

    private void drawPlayers(Canvas canvas) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (ConnectFiveModel.getInstance().getFieldContent(i,j) == ConnectFiveModel.CIRCLE) {

                    float centerX = i * getWidth() / 15 + getWidth() / 30;
                    float centerY = j * getHeight() / 15 + getHeight() / 30;
                    int radius = getHeight() / 30 - 2;

                    canvas.drawCircle(centerX, centerY, radius, paintO);

                } else if (ConnectFiveModel.getInstance().getFieldContent(i,j) == ConnectFiveModel.CROSS) {
                    canvas.drawLine(i * getWidth() / 15, j * getHeight() / 15,
                            (i + 1) * getWidth() / 15,
                            (j + 1) * getHeight() / 15, paintX);

                    canvas.drawLine((i + 1) * getWidth() / 15, j * getHeight() / 15,
                            i * getWidth() / 15, (j + 1) * getHeight() / 15, paintX);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int tX = ((int) event.getX()) / (getWidth() / 15);
            int tY = ((int) event.getY()) / (getHeight() / 15);

            if (tX < 15 && tY < 15 && ConnectFiveModel.getInstance().getFieldContent(tX,tY)
                    == ConnectFiveModel.EMPTY) {
                ConnectFiveModel.getInstance().setFieldContent(
                        tX, tY, ConnectFiveModel.getInstance().getNextPlayer());
                ConnectFiveModel.getInstance().changeNextPlayer();


                int winner = ConnectFiveModel.getInstance().getWinner();
                if (winner == ConnectFiveModel.CIRCLE) {
                    Toast.makeText(getContext(), getContext().getString(R.string.winner_O),
                            Toast.LENGTH_LONG).show();
                } else if (winner == ConnectFiveModel.CROSS) {
                    Toast.makeText(getContext(), getContext().getString(R.string.winner_X),
                            Toast.LENGTH_LONG).show();
                } else if (winner == -1) {
                    Toast.makeText(getContext(), getContext().getString(R.string.tie_game), Toast.LENGTH_LONG)
                            .show();
                }
                invalidate();
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);
    }
}