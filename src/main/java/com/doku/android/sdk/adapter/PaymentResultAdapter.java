package com.doku.android.sdk.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;


import com.doku.android.sdk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fascal on 26,May,2020
 */
public class PaymentResultAdapter extends ExpandableRecyclerAdapter<PaymentResultAdapter.MenuItem> {
    public static final int TYPE_MENU = 1001;

    public PaymentResultAdapter(Context context) {
        super(context);
        setItems(getItems());
    }

    public static class MenuItem extends ExpandableRecyclerAdapter.ListItem {
        public String titleGroup;
        public Integer logoIdVal;

        public MenuItem(String group,Integer logoId) {
            super(TYPE_HEADER);

            titleGroup = group;
            logoIdVal =logoId;
        }

        public MenuItem(String first, String last,Integer logoId) {
            super(TYPE_MENU);

            titleGroup = first + " " + last;
            logoIdVal =logoId;
        }
    }

    public class HeaderViewHolder extends ExpandableRecyclerAdapter.HeaderViewHolder {
        TextView textviewMenuToptitle;
        ImageView imageviewMenuLogo;
        Integer logoId =null;

        public HeaderViewHolder(View view) {
            super(view, view.findViewById(R.id.imageview_menu_arrow), view.findViewById(R.id.textview_menu_toptitle));
            textviewMenuToptitle = view.findViewById(R.id.textview_menu_toptitle);
//            imageviewMenuLogo = view.findViewById(R.id.imageview_menu_logo);
        }

        public void bind(int position) {
            super.bind(position);
            textviewMenuToptitle.setText(visibleItems.get(position).titleGroup);
            logoId = visibleItems.get(position).logoIdVal;
            imageviewMenuLogo.setBackgroundResource(logoId);
        }
    }

    public class MenuViewHolder extends ExpandableRecyclerAdapter.ViewHolder {
        TextView textviewContentTitle;
        ImageView imageviewContentLogo;
        Integer logoId =null;
        ConstraintLayout constraintlayoutMenuContent;

        public MenuViewHolder(View view) {
            super(view);

            textviewContentTitle = view.findViewById(R.id.textview_content_title);
//            imageviewContentLogo = view.findViewById(R.id.imageview_content_logo);
            constraintlayoutMenuContent = view.findViewById(R.id.constraintlayout_menu_content);
        }

        public void bind(final int position) {
            logoId = visibleItems.get(position).logoIdVal;
            imageviewContentLogo.setBackgroundResource(logoId);
            textviewContentTitle.setText(visibleItems.get(position).titleGroup);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return new HeaderViewHolder(inflate(R.layout.resultpage_header_adapter, parent));
            case TYPE_MENU:
            default:
                return new MenuViewHolder(inflate(R.layout.resultpage_content_adapter, parent));
        }
    }

    @Override
    public void onBindViewHolder(ExpandableRecyclerAdapter.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                ((HeaderViewHolder) holder).bind(position);
                break;
            case TYPE_MENU:
            default:
                ((MenuViewHolder) holder).bind(position);
                break;
        }
    }

    private List<MenuItem> getItems() {
        List<MenuItem> items = new ArrayList<>();
//        items.add(new MenuItem("Transfer via ATM",R.mipmap.icon_ib));
//        items.add(new MenuItem("Mobile Banking",R.mipmap.icon_personal));
        return items;
    }
}