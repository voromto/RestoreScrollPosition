package com.example.restorescrollposition;

import java.util.Arrays;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewFragment extends Fragment {
	/** The fragment argument representing the section number for this fragment. */
	private static final String ARG_SECTION_NUMBER = "section_number";
	private ListView mRootView;

	/** Returns a new instance of this fragment for the given section number. */
	public static ListViewFragment newInstance(int sectionNumber) {
		ListViewFragment fragment = new ListViewFragment();
		Bundle args = new Bundle();
		args.putInt(ListViewFragment.ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public ListViewFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = (ListView) inflater.inflate(R.layout.fragment_listview, container,
				false);
		return mRootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ListViewFragment.ARG_SECTION_NUMBER));
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ActionBarActivity activity = (ActionBarActivity) getActivity();
		ListViewFragmentAdapter adapter = new ListViewFragmentAdapter(activity,
				android.R.layout.simple_list_item_1, android.R.layout.simple_list_item_1,
				Arrays.asList(new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
						13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28,
						29, 30, 31,}));
		mRootView.setAdapter(adapter);
	}

	public static class ListViewFragmentAdapter extends ArrayAdapter<Integer> {
		List<Integer> objects;
		Context context;
		int textViewResourceId;

		public ListViewFragmentAdapter(Context context, int resource,
				int textViewResourceId, List<Integer> objects) {
			super(context, resource, textViewResourceId, objects);
			this.context = context;
			this.textViewResourceId = textViewResourceId;
			this.objects = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater layoutInflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				if (position == 0) {
					convertView = layoutInflater.inflate(R.layout.zero_height_view, null);
				} else {
					convertView = layoutInflater.inflate(textViewResourceId, null);
				}
			}
			if (position != 0) {
				((TextView) convertView).setText("" + objects.get(position));
			}
			return convertView;
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		@Override
		public int getItemViewType(int groupPosition) {
			if (groupPosition == 0)
				return 0;
			else
				return 1;
		}
	}
}