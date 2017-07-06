package com.zyf.mvvm.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyf.mvvm.databinding.ScaleItemBinding;
import com.zyf.mvvm.viewModels.ScaleItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyf on 2017/7/6.
 */

public class ScaleAdapter extends RecyclerView.Adapter<ScaleAdapter.ScaleHolder> {
    private List<ScaleItemViewModel> mData = new ArrayList<>();

    public ScaleAdapter(List<ScaleItemViewModel> data) {
        this.mData = data;
    }

    @Override
    public ScaleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ScaleHolder.create(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ScaleHolder holder, int position) {
        holder.bindTo(mData.get(position));
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    static class ScaleHolder extends RecyclerView.ViewHolder {
        private ScaleItemBinding mBinding;

        static ScaleHolder create(LayoutInflater inflater, ViewGroup parent) {
            ScaleItemBinding binding = ScaleItemBinding.inflate(inflater, parent, false);
            return new ScaleHolder(binding);
        }

        private ScaleHolder(ScaleItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bindTo(ScaleItemViewModel scaleItemViewModel) {
            mBinding.setScaleItemViewModel(scaleItemViewModel);
            mBinding.executePendingBindings();
        }

    }

    /**
     * listview方式的分割线
     */
    static public class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private static final int[] ATTRS = new int[]{
                android.R.attr.listDivider
        };

        public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

        public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

        private Drawable mDivider;

        private int mOrientation;

        public DividerItemDecoration(Context context, int orientation) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
            setOrientation(orientation);
        }

        public void setOrientation(int orientation) {
            if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
                throw new IllegalArgumentException("invalid orientation");
            }
            mOrientation = orientation;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent) {

            if (mOrientation == VERTICAL_LIST) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }

        }


        public void drawVertical(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(parent.getContext());
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        public void drawHorizontal(Canvas c, RecyclerView parent) {
            final int top = parent.getPaddingTop();
            final int bottom = parent.getHeight() - parent.getPaddingBottom();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
            if (mOrientation == VERTICAL_LIST) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }
        }
    }

    /**
     * GridView的分割线
     */
    static public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

        private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
        private Drawable mDivider;

        public DividerGridItemDecoration(Context context) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

            drawHorizontal(c, parent);
            drawVertical(c, parent);

        }

        private int getSpanCount(RecyclerView parent) {
            // 列数
            int spanCount = -1;
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {

                spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                spanCount = ((StaggeredGridLayoutManager) layoutManager)
                        .getSpanCount();
            }
            return spanCount;
        }

        public void drawHorizontal(Canvas c, RecyclerView parent) {
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getLeft() - params.leftMargin;
                final int right = child.getRight() + params.rightMargin
                        + mDivider.getIntrinsicWidth();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        public void drawVertical(Canvas c, RecyclerView parent) {
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);

                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getTop() - params.topMargin;
                final int bottom = child.getBottom() + params.bottomMargin;
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicWidth();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                    int childCount) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int orientation = ((StaggeredGridLayoutManager) layoutManager)
                        .getOrientation();
                if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                    if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                    {
                        return true;
                    }
                } else {
                    childCount = childCount - childCount % spanCount;
                    if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                        return true;
                }
            }
            return false;
        }

        private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                                  int childCount) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                    return true;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int orientation = ((StaggeredGridLayoutManager) layoutManager)
                        .getOrientation();
                // StaggeredGridLayoutManager 且纵向滚动
                if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                    childCount = childCount - childCount % spanCount;
                    // 如果是最后一行，则不需要绘制底部
                    if (pos >= childCount)
                        return true;
                } else
                // StaggeredGridLayoutManager 且横向滚动
                {
                    // 如果是最后一行，则不需要绘制底部
                    if ((pos + 1) % spanCount == 0) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public void getItemOffsets(Rect outRect, int itemPosition,
                                   RecyclerView parent) {
            int spanCount = getSpanCount(parent);
            int childCount = parent.getAdapter().getItemCount();
            if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
            {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            } else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
            {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(),
                        mDivider.getIntrinsicHeight());
            }
        }
    }

    static public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int mSpanCount;
        private float mItemSize;

        public GridSpacingItemDecoration(int spanCount, int itemSize) {
            this.mSpanCount = spanCount;
            mItemSize = itemSize;
        }

        @Override
        public void getItemOffsets(final Rect outRect, final View view, RecyclerView parent,
                                   RecyclerView.State state) {
            final int position = parent.getChildLayoutPosition(view);
            final int column = position % mSpanCount;
            final int parentWidth = parent.getWidth();
            int spacing = (int) (parentWidth - (mItemSize * mSpanCount)) / (mSpanCount + 1);
            outRect.left = spacing - column * spacing / mSpanCount;
            outRect.right = (column + 1) * spacing / mSpanCount;

            if (position < mSpanCount) {
                outRect.top = spacing;
            }
            outRect.bottom = spacing;
        }
    }
    static public class MyItemDecoration extends RecyclerView.ItemDecoration {

        /**
         * 复写onDraw方法
         *
         * @param c
         * @param parent
         * @param state
         */
        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            //先初始化一个Paint来简单指定一下Canvas的颜色，就黑的吧！
            Paint paint = new Paint();
            paint.setColor(parent.getContext().getResources().getColor(android.R.color.black));

            //获得RecyclerView中总条目数量
            int childCount = parent.getChildCount();

            //遍历一下
            for (int i = 0; i < childCount; i++) {
                if (i == 0) {
                    //如果是第一个条目，那么我们就不画边框了
                    continue;
                }
                //获得子View，也就是一个条目的View，准备给他画上边框
                View childView = parent.getChildAt(i);

                //先获得子View的长宽，以及在屏幕上的位置，方便我们得到边框的具体坐标
                float x = childView.getX();
                float y = childView.getY();
                int width = childView.getWidth();
                int height = childView.getHeight();

                //根据这些点画条目的四周的线
                c.drawLine(x, y, x + width, y, paint);
                c.drawLine(x, y, x, y + height, paint);
                c.drawLine(x + width, y, x + width, y + height, paint);
                c.drawLine(x, y + height, x + width, y + height, paint);

                //当然了，这里大家肯定是要根据自己不同的设计稿进行画线，或者画一些其他的东西，都可以在这里搞，非常方便
            }
            super.onDraw(c, parent, state);
        }
    }
}
