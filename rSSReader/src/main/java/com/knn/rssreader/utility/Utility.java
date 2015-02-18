package com.knn.rssreader.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by root on 9/1/15.
 */
public class Utility
{

	private static final String TAG = Utility.class.getSimpleName();

	public static boolean isNetworkAvailable(Context con)
	{
		ConnectivityManager connectivity = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null)
		{
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED)
					{
						System.out.println("true");
						return true;
					}

		}
		System.out.println("false");
		return false;
	}

	/**
	 * Method used to validate Email Id
	 * 
	 * @param email
	 * @return isValid : true if email id is valid else return false.
	 */
	public static boolean isEmailValid(String email)
	{
		boolean isValid = false;

		if (email.length() > 0)
		{
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			CharSequence inputStr = email;
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(inputStr);
			if (matcher.matches())
			{
				isValid = true;
			}
		}
		return isValid;
	}

	/**
	 * Description : Message String
	 */
	public static void ShowToast(Context context, String msg)
	{
		if (msg != null && !msg.trim().equalsIgnoreCase(""))
		{
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Description : Custom Message
	 */
	public static boolean isNotEmpty(Context context, EditText editText, String msg)
	{
		if (editText.getText().toString().trim().equalsIgnoreCase(""))
		{
			ShowErrorToast(editText, msg);
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Description : Custom Message
	 */
	public static boolean isNotEmpty(Context context, String editText, String msg)
	{
		if (editText.trim().equalsIgnoreCase(""))
		{
			ShowToast(context, msg + " " + Constants.ToastMessage.FIELD_BLANK);
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Description : Message Res ID
	 */
	public static void ShowToast(Context context, int msgID)
	{
		ShowToast(context, context.getString(msgID));
	}

	public static void SetClickListener(Context context, int[] clickIDs)
	{
		if (context instanceof View.OnClickListener)
		{
			for (int id : clickIDs)
			{
				try
				{
					((Activity) context).findViewById(id).setOnClickListener((View.OnClickListener) context);
				}
				catch (NullPointerException e)
				{
					e.printStackTrace();
					continue;
				}
			}
		}
	}

	public static void runOnMainThread(Activity activity, Runnable runnable)
	{
		if (activity != null)
		{
			activity.runOnUiThread(runnable);
		}
	}

	public static int getScreenWidth(Activity activity)
	{
		Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
		Point p = new Point();
		defaultDisplay.getSize(p);
		return p.x;
	}

	public static int getScreenHeight(Activity activity)
	{
		Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
		Point p = new Point();
		defaultDisplay.getSize(p);
		return p.y;
	}

	public static LinearLayout.LayoutParams getLinearLayoutParams(int screenWidth, int screenHeight)
	{
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(screenWidth, screenHeight);
		return lp;
	}

	public static void setDrawable(Activity activity, View view, Drawable drawable)
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
		{
			view.setBackground(drawable);
		}
		else
		{
			view.setBackgroundDrawable(drawable);
		}

	}

	public static boolean checkSpecifiedIntLength(Activity activity, String tv, String fieldName, int length)
	{

		if (checkTypeInt(activity, tv, fieldName + " " + Constants.ToastMessage.FIELD_BLANK))
		{

			if (tv.trim().length() == length)
			{
				return true;
			}

			ShowToast(activity, Constants.ToastMessage.FIELD_TYPE + " " + fieldName);
		}

		return false;
	}

	public static boolean checkSpecifiedIntLength(Activity activity, EditText tv, String fieldName, int length)
	{

		if (checkTypeInt(activity, tv, fieldName + " " + Constants.ToastMessage.FIELD_BLANK))
		{

			if (tv.getText().toString().trim().length() == length)
			{
				return true;
			}

			ShowErrorToast(tv, Constants.ToastMessage.FIELD_TYPE + " " + fieldName);
		}

		return false;
	}

	public static boolean checkTypeInt(Activity activity, String tv, String fieldName)
	{
		if (isNotEmpty(activity, tv, fieldName + " " + Constants.ToastMessage.FIELD_BLANK))
		{

			try
			{
				Integer.parseInt(tv);
				return true;
			}
			catch (ClassCastException e)
			{
				e.printStackTrace();

			}
			ShowToast(activity, Constants.ToastMessage.FIELD_TYPE + " " + fieldName);

		}

		return false;
	}

	public static boolean checkTypeInt(Activity activity, EditText tv, String fieldName)
	{
		if (isNotEmpty(activity, tv, fieldName + " " + Constants.ToastMessage.FIELD_BLANK))
		{

			try
			{
				Integer.parseInt(tv.getText().toString());
				return true;
			}
			catch (ClassCastException e)
			{
				e.printStackTrace();

			}
			ShowErrorToast(tv, Constants.ToastMessage.FIELD_TYPE + " " + fieldName);

		}

		return false;
	}

	public static void ShowErrorToast(EditText tv, String s)
	{
		if (tv != null && s != null)
		{
			if (!tv.isFocused())
				tv.requestFocus();
			tv.setError(s);
		}

	}

	public static double getDouble(Activity activity, String text, String Field)
	{
		if (isNotEmpty(activity, text, Field))
		{
			try
			{
				return Double.parseDouble(text);
			}
			catch (ClassCastException e)
			{
				e.printStackTrace();
			}
			return 0;
		}
		return 0;
	}

	/**
	 * Method used to validate Email Idpublic static boolean checkSpecifiedIntLength(Activity activity, String tv, String fieldName, int
	 * length) {
	 * <p/>
	 * if (checkTypeInt(activity, tv, fieldName + " " + Constants.ToastMessage.FIELD_BLANK)) {
	 * <p/>
	 * if (tv.trim().length() == length) { return true; }
	 * <p/>
	 * ShowToast(activity, Constants.ToastMessage.FIELD_TYPE + " " + fieldName); }
	 * <p/>
	 * return false; }
	 * 
	 * @param email
	 * @return isValid : true if email id is valid else return false.
	 */
	public static boolean isEmailValid(Activity activity, String email, String field)
	{
		boolean isValid = false;

		if (isNotEmpty(activity, email, field))
		{

			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			CharSequence inputStr = email;
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(inputStr);
			if (matcher.matches())
			{
				return isValid = true;
			}
			ShowToast(activity, Constants.ToastMessage.INVALID_EMAIL);

		}
		return isValid;
	}

	// Pattern is YYMM
	public static String getExpiryDate(String year, String month)
	{
		if (year == null)
		{
			throw new NullPointerException("Year cannot be empty");
		}

		if (month == null)
		{
			throw new NullPointerException("Month cannot be empty");
		}

		return year.substring(year.length() / 2, year.length()) + month;

	}

	public static String readTextFromInputStream(InputStream in) throws IOException
	{
		if (in != null)
		{
			StringBuilder sb = new StringBuilder();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = bufferedReader.readLine()) != null)
			{
				sb.append(line);

			}
			return sb.toString();

		}

		return "";
	}

	public static float deviceDensity(Context context)
	{
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		float deviceDensity = (displayMetrics.density);
		Log.d(TAG, "device density: " + deviceDensity);
		return deviceDensity;
	}

	public static void dismissKeyBoard(Activity context)
	{
		try
		{
			if (context != null)
			{
				InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
				if (imm.isAcceptingText())
				{
					if (context.getWindow().getCurrentFocus() != null && context.getWindow().getCurrentFocus().getWindowToken() != null)
					{
						imm.hideSoftInputFromWindow(context.getWindow().getCurrentFocus().getWindowToken(), 0);
					}
				}
			}
		}
		catch (Exception e)
		{
			Log.d(TAG, "keyboard type exception");
			e.printStackTrace();
		}
	}

	public static void dismissKeyBoard(Activity activity, EditText myEditText)
	{
		if (myEditText != null)
		{
			InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);
		}
	}

	public static void getEditText(Activity activity, View root)
	{
		ArrayList<View> views = getAllViewsFromRoots(root);
		for (View view : views)
		{
			if (view instanceof EditText && view.getVisibility() == View.VISIBLE)
			{
				Utility.dismissKeyBoard(activity, (EditText) view);
				// return (EditText)view;
			}
		}
		// return null;
	}

	private static void getAllViews(ArrayList<View> allviews, View parent)
	{
		allviews.add(parent);
		if (parent instanceof ViewGroup)
		{
			ViewGroup viewGroup = (ViewGroup) parent;
			for (int i = 0; i < viewGroup.getChildCount(); i++)
				getAllViews(allviews, viewGroup.getChildAt(i));
		}
	}

	public static ArrayList<View> getAllViewsFromRoots(View... roots)
	{
		ArrayList<View> result = new ArrayList<View>();
		for (View root : roots)
			getAllViews(result, root);
		return result;
	}

	public static void clearListView(AdapterView... lv)
	{
		int size = lv == null ? 0 : lv.length;
		for (int i = 0; i < lv.length; i++)
		{
			lv[0].setAdapter(null);
		}

	}

	public static void hideView(View... lv)
	{
		int size = lv == null ? 0 : lv.length;
		for (int i = 0; i < lv.length; i++)
		{
			lv[i].setVisibility(View.GONE);
		}
	}

	public static void invisibleView(View... lv)
	{
		int size = lv == null ? 0 : lv.length;
		for (int i = 0; i < lv.length; i++)
		{
			lv[i].setVisibility(View.INVISIBLE);
		}
	}

	public static void visibleView(View... lv)
	{
		int size = lv == null ? 0 : lv.length;
		for (int i = 0; i < lv.length; i++)
		{
			lv[i].setVisibility(View.VISIBLE);
		}
	}

	public static IntentFilter getIntentFilter(String action_category_ui)
	{
		IntentFilter intentFilter = new IntentFilter(action_category_ui);
		return intentFilter;
	}

	public static Intent getIntent(String action_category_ui)
	{
		Intent intent = new Intent(action_category_ui);
		return intent;
	}

	public static Bundle bundle(String[] keys, Object... values)
	{
		Bundle bundle = new Bundle();
		for (int i = 0; i < values.length; i++)
		{
			if (values[i] instanceof Parcelable)
			{
				bundle.putParcelable(keys[i], (Parcelable) values[i]);

			}

			if (values[i] instanceof Serializable)
			{
				bundle.putSerializable(keys[i], (Serializable) values[i]);
			}
			else
			{
				bundle.putString(keys[i], (String) values[i]);
			}

		}
		return bundle;
	}

}
