package com.doku.android.sdk.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.doku.android.sdk.R;
import com.doku.android.sdk.model.MandiriHowToPayResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dedyeirawan on 26,May,2020
 */
public class HowToInstructionAdapter extends ExpandableRecyclerAdapter<HowToInstructionAdapter.MenuItem> {
    public static final int TYPE_MENU = 1002;
    public MandiriHowToPayResponse dataInstruction;

    public HowToInstructionAdapter(Context context, MandiriHowToPayResponse dataInstruction) {
        super(context);
        this.dataInstruction = dataInstruction;
        setItems(getItems());
    }

    public static class MenuItem extends ExpandableRecyclerAdapter.ListItem {
        public String titleGroup;

        public MenuItem(String group) {
            super(TYPE_HEADER);

            titleGroup = group;
        }

        public MenuItem(String first, String last) {
            super(TYPE_MENU);

            titleGroup = first + " " + last;
        }
    }

    public class HeaderViewHolder extends ExpandableRecyclerAdapter.HeaderViewHolder {
        TextView textviewMenuToptitle;

        public HeaderViewHolder(View view) {
            super(view, view.findViewById(R.id.imageview_menu_arrow), view.findViewById(R.id.textview_menu_toptitle));
            textviewMenuToptitle = view.findViewById(R.id.textview_menu_toptitle);
        }

        public void bind(int position) {
            super.bind(position);
            textviewMenuToptitle.setText(visibleItems.get(position).titleGroup);
        }
    }

    public class MenuViewHolder extends ExpandableRecyclerAdapter.ViewHolder {
        TextView textviewContentTitle;
        ConstraintLayout constraintlayoutMenuContent;

        public MenuViewHolder(View view) {
            super(view);

            textviewContentTitle = view.findViewById(R.id.textview_content_title);
            constraintlayoutMenuContent = view.findViewById(R.id.constraintlayout_menu_content);
        }

        public void bind(final int position) {
            textviewContentTitle.setText(visibleItems.get(position).titleGroup);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return new HeaderViewHolder(inflate(R.layout.paymentpage_header_adapter, parent));
            case TYPE_MENU:
            default:
                return new MenuViewHolder(inflate(R.layout.paymentpage_content_adapter, parent));
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
        for(int i = 0; i < dataInstruction.getPaymentInstruction().size(); i++) {
            items.add(new MenuItem(dataInstruction.getPaymentInstruction().get(i).getChannel()));
            for (int j = 0; j < dataInstruction.getPaymentInstruction().get(i).getStep().size(); j++) {
                items.add(new MenuItem((j+1)+". ", dataInstruction.getPaymentInstruction().get(i).getStep().get(j)));
            }
        }
        return items;
    }
}