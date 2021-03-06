package com.example.meal;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;


public class CardWalletView extends RelativeLayout {
    private List<CardView > mCardViews;
    private List<Float> mCardViewOriginalY;
    private boolean mIsPresentingCards;
    private int mCardOffset;

    public CardWalletView(Context context, List<CardView > cardViews) {
        super(context);
        mCardViews = cardViews;
        mCardOffset = 70;
        initView();
    }

    public CardWalletView(Context context) {
        super(context);
    }

    public CardWalletView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardWalletView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CardWalletView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private static int convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }

    private void initView() {
//      inflate(getContext(), R.layout.layout_cards_container, this);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setGravity(Gravity.CENTER);
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsPresentingCards) {
                    exitPresentingCardMode();
                }
            }
        });
        setClickable(false);

        initCardsWallet();
    }

    private void initCardsWallet() {
        if (mCardViews != null) {
            for(int num = mCardViews.size()-1; num>-1; num--) {
                final CardView cardView = mCardViews.get(num);
                cardView.setTag(num);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mIsPresentingCards) {
                            enterPresentingCardMode();
                            moveDowns();
                            final ObjectAnimator translationY = ObjectAnimator.ofFloat(cardView, "y", 50);
                            cardView.setHeight(50);
                            translationY.start();
                        }
                        else{
                            exitPresentingCardMode();
                        }
                    }
                });
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                final int offset = num * convertDpToPixel(mCardOffset, getContext());
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                params.setMargins(0, 0, 0, offset);
                addView(cardView, params);
            }
        }
    }

    public void enterPresentingCardMode() {
        setClickable(true);
        mIsPresentingCards = true;
    }

    public void exitPresentingCardMode() {
        tidyCards();
        setClickable(false);
        mIsPresentingCards = false;
    }

    public void tidyCards() {
        if (mCardViewOriginalY == null) {
            mCardViewOriginalY = new ArrayList<>();
            for (CardView cardView : mCardViews) {
                mCardViewOriginalY.add(cardView.getY());
            }
        }
        for (int i = 0; i < mCardViews.size(); i++) {
            CardView cardView = mCardViews.get(i);
            cardView.setHeight(0);
            final ObjectAnimator translationY = ObjectAnimator.ofFloat(cardView, "y", mCardViewOriginalY.get(i));
            translationY.start();
        }
    }

    public void moveDowns(){
        if (mCardViewOriginalY == null) {
            mCardViewOriginalY = new ArrayList<>();
            for (CardView cardView : mCardViews) {
                mCardViewOriginalY.add(cardView.getY());
            }
        }
        for (int i = 0; i < mCardViews.size(); i++) {
            CardView cardView = mCardViews.get(i);
            final ObjectAnimator translationY = ObjectAnimator.ofFloat(cardView, "y", 1000-20*i);
            translationY.start();
        }
    }

    public boolean isPresentingCards() {
        return mIsPresentingCards;
    }
}