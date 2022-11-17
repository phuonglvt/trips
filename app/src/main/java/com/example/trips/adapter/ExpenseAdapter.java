package com.example.trips.adapter;
        import android.os.Bundle;

        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ListView;
        import android.widget.BaseAdapter;
        import android.widget.AdapterView;
        import android.content.Context;
        import java.util.List;
        import android.widget.TextView;
        import android.view.LayoutInflater;

        import com.example.trips.R;
        import com.example.trips.model.Expense;
        import com.example.trips.sqlite.ExpenseDatabase;
        import com.example.trips.sqlite.DBHelper;

public class ExpenseAdapter extends BaseAdapter {
    private Context context;
    private List<Expense> list;

    public ExpenseAdapter(Context context, List<Expense> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int iPosition) {
        return list.get(iPosition);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_expense_item, null);
        }

        TextView tvType = view.findViewById(com.example.trips.R.id.tvType);
        TextView tvAmount = view.findViewById(com.example.trips.R.id.tvAmount);
        TextView tvTimeExpense = view.findViewById(com.example.trips.R.id.tvTimeExpense);

        Expense expense = list.get(i);
        tvType.setText(expense.getType());
        tvAmount.setText(expense.getAmount());
        tvTimeExpense.setText(expense.getTimeExpense());

        return view;
    }
}
