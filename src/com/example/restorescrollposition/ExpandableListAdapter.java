package com.example.restorescrollposition;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
	private Activity mActivity;
	private List<ExpandListGroup<String, String>> mExpandListGroups;
	private LayoutInflater mInflater;

	public ExpandableListAdapter(Activity activity,
			List<ExpandListGroup<String, String>> expandListGroups) {
		mActivity = activity;
		mInflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mExpandListGroups = new ArrayList<ExpandListGroup<String, String>>(
				expandListGroups.size() + 1);
		mExpandListGroups.add(null);
		mExpandListGroups.addAll(expandListGroups);
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.child, parent, false);
		}
		final String group = mExpandListGroups.get(groupPosition).getData();
		final String child = mExpandListGroups.get(groupPosition).getItems()
				.get(childPosition);
		((TextView) convertView.findViewById(R.id.text1)).setText(group);
		((TextView) convertView.findViewById(R.id.text2)).setText(child);
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(mActivity, group + " " + child, Toast.LENGTH_SHORT).show();
			}
		});
		return convertView;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
			ViewGroup parent) {
		if (convertView == null) {
			if (groupPosition == 0) {
				convertView = mInflater.inflate(R.layout.zero_height_view, parent, false);
			} else {
				convertView = mInflater.inflate(R.layout.group, parent, false);
			}
		}
		if (groupPosition != 0) {
			((TextView) convertView.findViewById(R.id.text1)).setText(mExpandListGroups
					.get(groupPosition).getData());
		}
		return convertView;
	}

	@Override
	public int getGroupTypeCount() {
		return 2;
	}

	@Override
	public int getGroupType(int groupPosition) {
		if (groupPosition == 0)
			return 0;
		else
			return 1;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		if (groupPosition == 0) {
			return null;
		}
		return mExpandListGroups.get(groupPosition).getItems().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0L | groupPosition << 12 | childPosition;
	}

	@Override
	public long getCombinedChildId(long groupId, long childId) {
		return groupId << 32 | childId << 1 | 1;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		if (groupPosition == 0) {
			return 0;
		}
		return mExpandListGroups.get(groupPosition).getItems().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		if (groupPosition == 0) {
			return null;
		}
		return mExpandListGroups.get(groupPosition).getData();
	}

	@Override
	public int getGroupCount() {
		return mExpandListGroups.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getCombinedGroupId(long groupId) {
		return (groupId & 0x7FFFFFFF) << 32;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}
}