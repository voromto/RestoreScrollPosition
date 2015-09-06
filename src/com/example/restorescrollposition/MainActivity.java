package com.example.restorescrollposition;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {
	/** Fragment managing the behaviors, interactions and presentation of the navigation
	 * drawer. */
	private NavigationDrawerFragment mNavigationDrawerFragment;
	/** Used to store the last screen title. For use in {@link #restoreActionBar()}. */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.executePendingTransactions();
		String tag0 = "ListViewFragment";
		Fragment fragment0 = fragmentManager.findFragmentByTag(tag0);
		String tag1 = "ExpandableListViewFragment1";
		Fragment fragment1 = fragmentManager.findFragmentByTag(tag1);
		String tag2 = "ExpandableListViewFragment2";
		Fragment fragment2 = fragmentManager.findFragmentByTag(tag2);
		FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction();
		switch (position) {
		case 0:
			if (null != fragment1) {
				fragmentTransaction.detach(fragment1);
			}
			if (null != fragment2) {
				fragmentTransaction.detach(fragment2);
			}
			if (null != fragment0) {
				fragmentTransaction.attach(fragment0);
			} else {
				fragment0 = ListViewFragment.newInstance(1);
				fragmentTransaction.add(R.id.container, fragment0, tag0);
			}
			break;
		case 1:
			if (null != fragment0) {
				fragmentTransaction.detach(fragment0);
			}
			if (null != fragment2) {
				fragmentTransaction.detach(fragment2);
			}
			if (null != fragment1) {
				fragmentTransaction.attach(fragment1);
			} else {
				fragment1 = ExpandableListViewFragment.newInstance(2);
				fragmentTransaction.add(R.id.container, fragment1, tag1);
			}
			break;
		case 2:
			if (null != fragment0) {
				fragmentTransaction.detach(fragment0);
			}
			if (null != fragment1) {
				fragmentTransaction.detach(fragment1);
			}
			if (null != fragment2) {
				fragmentTransaction.attach(fragment2);
			} else {
				fragment2 = ExpandableListViewFragment.newInstance(3);
				fragmentTransaction.add(R.id.container, fragment2, tag2);
			}
			break;
		default:
			break;
		}
		fragmentTransaction.commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
