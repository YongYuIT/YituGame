package com.thinking.yitugame;

import com.thinking.basicService.LogService;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Game_Main extends Activity
{

    LinearLayout   lil_0_0;
    LinearLayout   lil_0_1;
    LinearLayout   lil_1_0;
    LinearLayout   lil_1_1;
    LinearLayout   lil_2_0;
    LinearLayout   lil_2_1;
    RelativeLayout rel_root;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        LogService.writeLog("running!");
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game_main_line);

        ImageView img_0_0 = (ImageView) findViewById(R.id.img_0_0);
        ImageView img_1_0 = (ImageView) findViewById(R.id.img_1_0);
        ImageView img_1_1 = (ImageView) findViewById(R.id.img_1_1);
        ImageView img_2_0 = (ImageView) findViewById(R.id.img_2_0);
        ImageView img_2_1 = (ImageView) findViewById(R.id.img_2_1);

        lil_0_0 = (LinearLayout) findViewById(R.id.lil_0_0);
        lil_0_1 = (LinearLayout) findViewById(R.id.lil_0_1);
        lil_1_0 = (LinearLayout) findViewById(R.id.lil_1_0);
        lil_1_1 = (LinearLayout) findViewById(R.id.lil_1_1);
        lil_2_0 = (LinearLayout) findViewById(R.id.lil_2_0);
        lil_2_1 = (LinearLayout) findViewById(R.id.lil_2_1);
        rel_root = (RelativeLayout) findViewById(R.id.rel_root);

        img_0_0.setOnTouchListener(new ImgOnTouchListener());
        img_1_0.setOnTouchListener(new ImgOnTouchListener());
        img_1_1.setOnTouchListener(new ImgOnTouchListener());
        img_2_0.setOnTouchListener(new ImgOnTouchListener());
        img_2_1.setOnTouchListener(new ImgOnTouchListener());

        lil_0_0.setOnDragListener(new MyDragListener());
        lil_0_1.setOnDragListener(new MyDragListener());
        lil_1_0.setOnDragListener(new MyDragListener());
        lil_1_1.setOnDragListener(new MyDragListener());
        lil_2_0.setOnDragListener(new MyDragListener());
        lil_2_1.setOnDragListener(new MyDragListener());
        rel_root.setOnDragListener(new MyDragListener());
    }

    private int[] getXYById(int id)
    {
        if (id == lil_0_0.getId())
        {
            return new int[] { 0, 0 };
        }
        if (id == lil_0_1.getId())
        {
            return new int[] { 0, 1 };
        }
        if (id == lil_1_0.getId())
        {
            return new int[] { 1, 0 };
        }
        if (id == lil_1_1.getId())
        {
            return new int[] { 1, 1 };
        }
        if (id == lil_2_0.getId())
        {
            return new int[] { 2, 0 };
        }
        if (id == lil_2_1.getId())
        {
            return new int[] { 2, 1 };
        } else
        {
            return new int[] { -1, -1 };
        }
    }
    
    private class ImgOnTouchListener implements OnTouchListener
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            LogService.writeLog("onTouch running!");
            ClipData data = ClipData.newPlainText("", "");
            DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder, v, 0);
            return true;
        }
    }

    class MyDragListener implements OnDragListener
    {
        @Override
        public boolean onDrag(View v, DragEvent event)
        {
            int action = event.getAction();
            switch (event.getAction())
            {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    LinearLayout container;
                    try
                    {
                        container = (LinearLayout) v;
                    } catch (Exception e)
                    {
                        view.setVisibility(View.VISIBLE);
                        break;
                    }

                    LogService.writeLog("owner:" + getXYById(owner.getId())[0]
                            + " " + getXYById(owner.getId())[1] + " container:"
                            + getXYById(container.getId())[0] + " "
                            + getXYById(container.getId())[1]);

                    LogService.writeLog("getChildCount"
                            + container.getChildCount());

                    if ((getXYById(owner.getId())[0] == getXYById(container
                            .getId())[0] || getXYById(owner.getId())[1] == getXYById(container
                            .getId())[1])
                            && container.getChildCount() == 0)
                    {
                        owner.removeView(view);
                        container.addView(view);
                    }
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }
    }
}
