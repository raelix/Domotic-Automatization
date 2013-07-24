package com.example.tablayout;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import android.R.menu;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.renderscript.Allocation;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
	public Configuration config=new Configuration("jarvise.txt");
	SectionsPagerAdapter mSectionsPagerAdapter;
	public static String host;
	public static String user;
	public static String pass;
	public static String def1;
	public static String def2;
	public static String def3;
	public static String def4;
	public static Activity thisActivity = null;
	ViewPager mViewPager;
	ActionBar actionBar ;
	@SuppressWarnings("static-access")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.thisActivity = getParent();
//		mViewPager.setBackgroundColor(android.graphics.Color.GRAY);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		File file = new File(Environment.getExternalStorageDirectory()+"/jarvise.txt");
		if (!file.exists()) {
		        try {
		            file.createNewFile();
		            BufferedWriter br = new BufferedWriter(new FileWriter(file));
		            br.write("host address / ip address");
		            br.write("\n");
		            br.write("username");
		            br.write("\n");
		            br.write("password");
		            br.write("\n");
		            br.write("default1");
		            br.write("\n");
		            br.write("default2");
		            br.write("\n");
		            br.write("default3");
		            br.write("\n");
		            br.write("default4");
		            br.write("\n");
		            br.flush();
		            br.close();
		        } catch (IOException e) {
		        	System.out.println("errore creazione file");
		            e.printStackTrace();
		        }
		        
		        this.user = config.getUser();
		        this.pass = config.getPass();
		        this.host = config.getHost();
		        this.def1 = config.getDefault0();
		        this.def2 = config.getDefault1();
		        this.def3 = config.getDefault2();
		        this.def4 = config.getDefault3();
		}
		actionBar =  getActionBar();
	actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.carbo));
//		actionBar.setBackgroundDrawable((new ColorDrawable(android.graphics.Color.BLUE)));

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setStackedBackgroundDrawable(getResources().getDrawable(
	            R.drawable.layout));
		mSectionsPagerAdapter = new SectionsPagerAdapter(
		getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);	
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
					

				        @Override
				        public void onPageScrolled(int arg0, float arg1, int arg2) {
				            mViewPager.getParent().requestDisallowInterceptTouchEvent(true);
				        }

				        @Override
				        public void onPageScrollStateChanged(int arg0) {


				                  // go back to the center allowing to scroll indefinitely
//				                  mViewPager.setCurrentItem(1, false);
				              }
				        
					
				});
		
			

		// For each of the sections in the app, add a tab to the action bar.
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.

			actionBar.addTab(actionBar.newTab().setText("Status").setTabListener(this));
			actionBar.addTab(actionBar.newTab().setText("Pannello").setTabListener(this));
			actionBar.addTab(actionBar.newTab().setText("Personali").setTabListener(this));
			actionBar.addTab(actionBar.newTab().setText("Controllo").setTabListener(this));
			actionBar.addTab(actionBar.newTab().setText("Sensori").setTabListener(this));
			actionBar.addTab(actionBar.newTab().setText("Settaggi").setTabListener(this));
			

		
		
	}

	
	
	public static void log(String log){
		Toast.makeText(thisActivity, log, Toast.LENGTH_LONG).show();

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	
	@Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	  // TODO Auto-generated method stub
	  switch(item.getItemId()){
	   case(R.id.action_settings):
//	    Toast.makeText(this.getParent(), "Settaggi", Toast.LENGTH_LONG).show();
	   new ToastMessageTask().execute("Settaggi");
	   actionBar.setSelectedNavigationItem(5);
//	    actionBar.setSelectedNavigationItem(5);
	
	   break;
	   
	  } 
	  
	  return super.onOptionsItemSelected(item);
	 } 
	

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		config.refresh();
		
		mViewPager.setCurrentItem(tab.getPosition());
		}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		config.refresh();
		 InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		    in.hideSoftInputFromWindow(mViewPager.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		config.refresh();
	}
	

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);

		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			// qui si scelgono le nuove schermate già create a priori
			switch (position){
			case 0:
				return new Status();
			case 1: 
				return new AllControlli();
			
			case 2: 
			return new Personalizzato();
	
			case 3:
			return new Controllo();
				
			case 4:
			return new Sensori();
			
			case 5:
			return new Avanzate();
			
				}
			return null;}
/*			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
*/
			
		

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 6;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			case 3:
				return getString(R.string.title_section4).toUpperCase(l);
			case 4:
				return getString(R.string.title_section5).toUpperCase(l);

			case 5:break;
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}
		


		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
		/*  	COUNT PAGE
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			*/
			return rootView;
		}
	}

}
