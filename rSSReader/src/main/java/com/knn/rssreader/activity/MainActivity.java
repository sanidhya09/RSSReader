package com.knn.rssreader.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.knn.rssreader.R;
import com.knn.rssreader.core.BaseActivity;
import com.knn.rssreader.core.MyApplication;
import com.knn.rssreader.fragment.NewsDetailFragment;
import com.knn.rssreader.fragment.NewsListFragment;
import com.knn.rssreader.interfaces.ChangeFragmentListener;
import com.knn.rssreader.utility.Constants;

public class MainActivity extends BaseActivity implements ChangeFragmentListener
{
	protected MyApplication myApplication;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myApplication = (MyApplication) getApplication();
		myApplication.setCurrentActivity(this);
		
		Bundle bundle = new Bundle();
		if (savedInstanceState == null) {
			replaceFragment(new NewsListFragment(), bundle);
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		System.out.println("Request code = " + requestCode + " Result Code = "
				+ resultCode);
		if (requestCode == 64206) {
			//For Facebook
		}
	}

	@Override
	public void onChangeFragment(int fragment_id, Bundle bundle) {

		switch (fragment_id) {
		case Constants.ChangeFragments.NEWS_DETAIL_FREAGMENT:
			replaceFragment(new NewsDetailFragment(), bundle);
			break;
		default:
			break;
		}

	}

	@Override
	public void onBackPressed() {
		if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
			//moveTaskToBack(true);
			showDialogForLogOut();
		} else {
			super.onBackPressed();
		}
	}

	private void showDialogForLogOut() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Add the buttons
		builder.setMessage("Are you sure , you want to exit ?");
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				
			finish();
			}
		});
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// User cancelled the dialog
					}
				});
		// Create the AlertDialog
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void replaceFragment(Fragment fragment, Bundle bundle) {
		fragment.setArguments(bundle);
		String backStateName = fragment.getClass().getName();
		String fragmentTag = backStateName;

		FragmentManager manager = getSupportFragmentManager();
		boolean fragmentPopped = manager
				.popBackStackImmediate(backStateName, 0);

		if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) {
			FragmentTransaction ft = manager.beginTransaction();
			ft.replace(R.id.frag_container, fragment, fragmentTag);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.addToBackStack(backStateName);
			ft.commit();
			// ft.commitAllowingStateLoss();
		}
	}

}
