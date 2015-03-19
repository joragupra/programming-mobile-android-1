package course.labs.todomanager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import course.labs.todomanager.ToDoItem.Status;

public class ToDoListAdapter extends BaseAdapter {

	private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
	private final Context mContext;

	private static final String TAG = "Lab-UserInterface";

	public ToDoListAdapter(Context context) {

		mContext = context;

	}

	// Add a ToDoItem to the adapter
	// Notify observers that the data set has changed

	public void add(ToDoItem item) {

		mItems.add(item);
		notifyDataSetChanged();

	}

	// Clears the list adapter of all items.

	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	// Returns the number of ToDoItems

	@Override
	public int getCount() {

		return mItems.size();

	}

	// Retrieve the number of ToDoItems

	@Override
	public Object getItem(int pos) {

		return mItems.get(pos);

	}

	// Get the ID for the ToDoItem
	// In this case it's just the position

	@Override
	public long getItemId(int pos) {

		return pos;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final ToDoItem toDoItem = mItems.get(position);
		final ViewHolder viewHolder;


		// Inflate the View for this ToDoItem
		// from todo_item.xml.
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.todo_item, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.titleView = (TextView) convertView.findViewById(R.id.titleView);
			viewHolder.statusView = (CheckBox) convertView.findViewById(R.id.statusCheckBox);
			viewHolder.priorityView = (TextView) convertView.findViewById(R.id.priorityView);
			viewHolder.dateAndTime = (TextView) convertView.findViewById(R.id.dateView);
			convertView.setTag(viewHolder);
		} else {
            viewHolder = (ViewHolder) convertView.getTag();
		}


		// Fill in specific ToDoItem data
		// Remember that the data that goes in this View
		// corresponds to the user interface elements defined
		// in the layout file

		// Display Title in TextView
		viewHolder.titleView.setText(toDoItem.getTitle());

		// Set up Status CheckBox
		viewHolder.statusView.setChecked(Status.DONE == toDoItem.getStatus());
        
		// Must also set up an OnCheckedChangeListener,
		// which is called when the user toggles the status checkbox
		viewHolder.statusView
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        Log.i(TAG, "Entered onCheckedChanged()");

                        toDoItem.setStatus(viewHolder.statusView.isChecked() ? Status.DONE : Status.NOTDONE);
                    }
                });

		// Display Priority in a TextView
		viewHolder.priorityView.setText(toDoItem.getPriority().toString());

		// Display Time and Date
		viewHolder.dateAndTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(toDoItem.getDate()));

		// Return the View you just created
		return convertView;

	}

    static class ViewHolder {

        TextView titleView;
        CheckBox statusView;
        TextView priorityView;
        TextView dateAndTime;

    }
}

