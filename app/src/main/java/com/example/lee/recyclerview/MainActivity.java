package com.example.lee.recyclerview;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.HashMap;


//implements OnItemRecycleViewClickListener
public class MainActivity extends ActionBarActivity {
    private ArrayList<ItemData> Tasklist;
    private ItemData itemData;
    private String content;
    private int source;
    private int pick;
    MyAdapter myAdapter;
    RecyclerView recyclerView;
    Toolbar toolbar;

    //CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tasklist = new ArrayList<ItemData>();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        BindData();
       // RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        toolbar.setTitle("CircleLife");
        setSupportActionBar(toolbar);
        // Navigation Icon 要設定在 setSupoortActionBar 才有作用
        // 否則會出現 back button
        toolbar.setNavigationIcon(R.drawable.ab_android);
        // Menu item click 的監聽事件一樣要設定在 setSupportActionBar 才有作用
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
        Button btn = (Button) findViewById(R.id.button01);




        SwipeDismissRecyclerViewTouchListener touchListener =
                new SwipeDismissRecyclerViewTouchListener(
                        recyclerView,
                        new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    myAdapter.Carrylist.remove(position);
                                }
                                // do not call notifyItemRemoved for every item, it will cause gaps on deleting items
                                myAdapter.notifyDataSetChanged();
                            }
                        });
        recyclerView.setOnTouchListener(touchListener);
        // Setting this scroll listener is required to ensure that during ListView scrolling,
        // we don't look for swipes.
        recyclerView.setOnScrollListener(touchListener.makeScrollListener());
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(recyclerView,
                new OnItemRecycleViewClickListener() {
                    @Override
                    public void onItemClicked(View view, int position) {
                        Toast.makeText(MainActivity.this, "Clicked " + myAdapter.Carrylist.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                    }
                }));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                final View view = inflater.inflate(R.layout.dialog_signin, null);
                //
                final EditText editText = (EditText) (view.findViewById(R.id.username));
                ImageButton img01 = (ImageButton) (view.findViewById(R.id.image01));
                ImageButton img02 = (ImageButton) (view.findViewById(R.id.image02));
                ImageButton img03 = (ImageButton) (view.findViewById(R.id.image03));
                pick = 0;
                img01.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //source = R.drawable.collections_cloud;
                        pick = 1;
                    }
                });
                img02.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //source = R.drawable.content_discard;
                        pick = 2;
                    }
                });
                img03.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //source = R.drawable.help;
                        pick = 3;
                        //editText.setText(String.format("%s",pick));
                        //Toast.makeText(getApplicationContext(),String.format("%d",pick),Toast.LENGTH_SHORT).show();
                    }
                });

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("請輸入任務名稱")
                        .setView(view)
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                //content = editText.getText().toString();

                                content = editText.getText().toString();
                                if (pick == 1) {
                                    //itemData = new ItemData(content, R.drawable.collections_cloud);
                                    //Tasklist.add(itemData);
                                    //BindData();
                                    source = R.drawable.collections_cloud;
                                } else if (pick == 2) {
                                    //itemData = new ItemData(content, R.drawable.content_discard);
                                    //Tasklist.add(itemData);
                                    //BindData();
                                    source = R.drawable.content_discard;

                                } else if (pick == 3) {
                                   // itemData = new ItemData(content, R.drawable.help);
                                    //Tasklist.add(itemData);
                                    //BindData();
                                    source = R.drawable.help;
                                }

                                //Toast.makeText(getApplicationContext(),String.format("%d",pick),Toast.LENGTH_SHORT).show();
                                itemData = new ItemData(content, source);
                                Tasklist.add(itemData);
                                BindData();
                                //Toast.makeText(this,capture.filename,Toast.LENGTH_SHORT).show();


                            }

                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                //itemData = new ();
                //Tasklist
            }
        });

    }
    protected void BindData(){
       recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
       // cardView = (CardView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // MyAdapter mAdapter = new MyAdapter(itemsData);
        //myAdapter = new MyAdapter(Tasklist,this);
        myAdapter = new MyAdapter(Tasklist);
        recyclerView.setAdapter(myAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());



    }
    public void onItemClicked(int position, MyAdapter mAdapter) {
        // TODO Auto-generated method stub

        Toast.makeText(this, myAdapter.Carrylist.get(position).getTitle(), Toast.LENGTH_LONG).show();

    }


    public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemRecycleViewClickListener mListener;

        private static final long DELAY_MILLIS = 100;

        private RecyclerView mRecyclerView;
        private GestureDetector mGestureDetector;
        private boolean mIsPrepressed = false;
        private boolean mIsShowPress = false;
        private View mPressedView = null;

        public RecyclerItemClickListener(RecyclerView recyclerView, OnItemRecycleViewClickListener listener) {
            mListener = listener;
            mRecyclerView = recyclerView;
            mGestureDetector = new GestureDetector(recyclerView.getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDown(MotionEvent e) {
                    mIsPrepressed = true;
                    mPressedView = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                    super.onDown(e);
                    return false;
                }

                @Override
                public void onShowPress(MotionEvent e) {
                    if (mIsPrepressed && mPressedView != null) {
                        mPressedView.setPressed(true);
                        mIsShowPress = true;
                    }
                    super.onShowPress(e);
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    if (mIsPrepressed && mPressedView != null) {
                        mPressedView.setPressed(true);
                        final View pressedView = mPressedView;
                        pressedView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pressedView.setPressed(false);
                            }
                        }, DELAY_MILLIS);
                        mIsPrepressed = false;
                        mPressedView = null;
                    }
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClicked(childView, view.getChildPosition(childView));
            } else if (e.getActionMasked() == MotionEvent.ACTION_UP && mPressedView != null && mIsShowPress) {
                mPressedView.setPressed(false);
                mIsShowPress = false;
                mIsPrepressed = false;
                mPressedView = null;
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        public void onRequestDisallowInterceptTouchEvent(boolean b){
            //return false;
        }
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_edit:
                    msg += "Click edit";
                    break;
                case R.id.action_share:
                    msg += "Click share";
                    break;
                case R.id.action_settings:
                    msg += "Click setting";
                    break;
            }

            if(!msg.equals("")) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 為了讓 Toolbar 的 Menu 有作用，這邊的程式不可以拿掉
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}


