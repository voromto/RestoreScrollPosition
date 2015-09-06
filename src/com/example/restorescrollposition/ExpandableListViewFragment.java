package com.example.restorescrollposition;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

/** A placeholder fragment containing a simple view. */
public class ExpandableListViewFragment extends Fragment {
	/** The fragment argument representing the section number for this fragment. */
	private static final String ARG_SECTION_NUMBER = "section_number";
	private ExpandableListView mExpandableListView;

	/** Returns a new instance of this fragment for the given section number. */
	public static ExpandableListViewFragment newInstance(int sectionNumber) {
		ExpandableListViewFragment fragment = new ExpandableListViewFragment();
		Bundle args = new Bundle();
		args.putInt(ExpandableListViewFragment.ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public ExpandableListViewFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mExpandableListView = (ExpandableListView) inflater.inflate(
				R.layout.fragment_expandablelistview, container, false);
		return mExpandableListView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ExpandableListViewFragment.ARG_SECTION_NUMBER));
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//
		List<ExpandListGroup<String, String>> expandListGroups = new ArrayList<ExpandListGroup<String, String>>();
		ArrayList<String> children;
		for (int i = 0; i < 30; i++) {
			children = new ArrayList<String>();
			for (int j = 0; j < 5; j++) {
				children.add("Child" + (j + 1));
			}
			expandListGroups.add(new ExpandListGroup<String, String>("Group" + (i + 1),
					children));
		}
		//
		ExpandableListAdapter adapter = new ExpandableListAdapter(getActivity(),
				expandListGroups);
		mExpandableListView.setAdapter(adapter);
		int count = adapter.getGroupCount();
		for (int position = 0; position < count; position++) {
			mExpandableListView.expandGroup(position);
		}
	}
}