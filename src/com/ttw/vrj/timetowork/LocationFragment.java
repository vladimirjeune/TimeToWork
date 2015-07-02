/**
 * 
 */
package com.ttw.vrj.timetowork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author vladimirjeune
 *
 */
public class LocationFragment extends Fragment {

	// Main Object
	private Location mLocation;
	
	// Editable Text Views
	private EditText mNameTextView;
	private EditText mStreetTextView;
	private EditText mUnitTextView;
	private EditText mCityTextView;
	private EditText mStateTextView;
	private EditText mZipcodeTextView;
	
	// Buttons
	private Button mClearButton;
	private Button mEditButton;
	private Button mSaveButton;

	private Button mNextButton;
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mLocation = new Location();
	}

	/**
	 * ONCREATE - where you inflate the layout for the Fragment's View.  Then return
	 * 	it to the hosting Activity.
	 * @see android.support.v4.app.
	 * 		Fragment#onCreateView(android.view.LayoutInflater
	 * 		, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Last parameter tells layout inflater whether to add inflated view to parent.
		// We will do it in the Activity's code
		View view = (View) inflater.inflate(R.layout.fragment_location
				, container, false);
		
		// Obtain UI Element Values
		mNameTextView = (EditText) view
				.findViewById(R.id.location_address_name);
		mStreetTextView = (EditText) view
				.findViewById(R.id.location_address_street);
		mUnitTextView = (EditText) view
				.findViewById(R.id.location_address_unit);
		mCityTextView = (EditText) view
				.findViewById(R.id.location_address_city);
		mStateTextView = (EditText) view
				.findViewById(R.id.location_address_state);
		mZipcodeTextView = (EditText) view
				.findViewById(R.id.location_address_zipcode);
		
		mClearButton = (Button) view
				.findViewById(R.id.location_address_clear_btn);
		mEditButton = (Button) view
				.findViewById(R.id.location_address_edit_btn);
		mSaveButton = (Button) view
				.findViewById(R.id.location_address_save_btn);
		mNextButton = (Button) view
				.findViewById(R.id.location_address_next_btn);
			
		// Create Listeners
		// Fields
		mNameTextView.addTextChangedListener(new LocationViewTextWatcher(mNameTextView));
		mStreetTextView.addTextChangedListener(new LocationViewTextWatcher(mStreetTextView));
		mUnitTextView.addTextChangedListener(new LocationViewTextWatcher(mUnitTextView));
		mCityTextView.addTextChangedListener(new LocationViewTextWatcher(mCityTextView));
		mStateTextView.addTextChangedListener(new LocationViewTextWatcher(mStateTextView));
		mZipcodeTextView.addTextChangedListener(new LocationViewTextWatcher(mZipcodeTextView));

		// Buttons
		mClearButton.setOnClickListener(new LocationViewClickListener());
		mEditButton.setOnClickListener(new LocationViewClickListener());
		mSaveButton.setOnClickListener(new LocationViewClickListener());
		mNextButton.setOnClickListener(new LocationViewClickListener());
		
		return view;
	}
	
	/**
	 * CLEARVIEW - will empty all fields of this view and delete field data from 
	 * 		backing object.
	 */
	private void clear() {
		mLocation.clear();  // Make sure the backing object rms field data
		
		mNameTextView.setText("");  // Clear all fields
		mStreetTextView.setText("");
		mUnitTextView.setText("");
		mCityTextView.setText("");
		mStateTextView.setText("");
		mZipcodeTextView.setText("");
		
	}
	
	/**
	 * SETABILITYBASEDONBUTTON - will enable and disable Views onscreen based on
	 * 		the View that was just interacted with.  Should be used in Listeners.
	 * @param v - View most recently interacted with by user.
	 */
	private void setAbilityOnInteraction(View v) {
		
		if (v.getId() == R.id.location_address_clear_btn) {
			mClearButton.setEnabled(false);  // Must type something to delete it.
			mSaveButton.setEnabled(false);    // Cannot save a blank location.
			mNextButton.setEnabled(false);
		} else if (v.getId() == R.id.location_address_edit_btn) {	
			// TODO: Have edit reset blinker to Name.  Enable fields, Have field enable all buttons
			setFieldsEnabled(true);
			mClearButton.setEnabled(true);
			mEditButton.setEnabled(true);
			mSaveButton.setEnabled(true);
			mNextButton.setEnabled(false);
		} else if (v.getId() == R.id.location_address_save_btn) {
			mSaveButton.setEnabled(false);    // Nothing left to save, but can edit to reenable all
			mClearButton.setEnabled(false);  // If you need delete after save, hit edit 1st
			mNextButton.setEnabled(true);
			
			// Fields
			setFieldsEnabled(false);		
			
		} else {  // Any EditText View should enable buttons
			mClearButton.setEnabled(true);
			mEditButton.setEnabled(true);
			mSaveButton.setEnabled(true);
		}
	
	}

	/**
	 * SETFIELDSENABLED - will set all fields to on or off depending on inputted boolean
	 * @param isEnabled - tells whether all fields should be enabled or disabled.
	 */
	private void setFieldsEnabled(boolean isEnabled) {
		mNameTextView.setEnabled(isEnabled);
		mStreetTextView.setEnabled(isEnabled);
		mUnitTextView.setEnabled(isEnabled);
		mCityTextView.setEnabled(isEnabled);
		mStateTextView.setEnabled(isEnabled);
		mZipcodeTextView.setEnabled(isEnabled);
	}


	/**
	 * CLEARTHENSETENABLED - makes sure that clear is called first.  Otherwise, clear will cause the 
	 * 		wrong buttons to be enabled at the wrong time.  The act of clearing modifies the textfields
	 * 		which enables all buttons.
	 * @param v
	 */
	private void clearThenSetEnabled(View v) {
		clear();
		setAbilityOnInteraction(v);
	}

	/**
	 * LOCATIONVIEWCLICKLISTENER - ClickListener for all buttons in fragment
	 * @author vladimirjeune
	 *
	 */
	private class LocationViewClickListener implements View.OnClickListener {

		 private final String LOG_TAG = LocationViewClickListener.class.getSimpleName() ;
		
		@Override
		public void onClick(View v) {
			Log.d(LOG_TAG, "In onClick()");
			if (v.getId() == R.id.location_address_clear_btn) {

				clearThenSetEnabled(v);
				
				// TODO: Remove Toast
				Toast.makeText(getActivity(), "DELETE:\n" + mLocation.toString()
						, Toast.LENGTH_SHORT).show();
				
			} else if (v.getId() == R.id.location_address_edit_btn) {
				setAbilityOnInteraction(v);
				
				// TODO: Remove Toast
				Toast.makeText(getActivity(), "EDIT:\n" + mLocation.toString()
						, Toast.LENGTH_SHORT).show();
			} else if (v.getId() == R.id.location_address_save_btn) {

				setAbilityOnInteraction(v);
				
				Toast.makeText(getActivity(), "SAVE:\n" + mLocation.toString()
						, Toast.LENGTH_SHORT).show();
			} else if (v.getId() == R.id.location_address_next_btn) {
				// TODO:  Next needs to go to the Next activity
				
				Toast.makeText(getActivity(), "NEXT:\n" + mLocation.toString()
						, Toast.LENGTH_SHORT).show();
				
			}
			
		}
		
	}
	
	
	/**
	 * LOCATIONVIEWTEXTWATCHER - TextWatcher for all fields in fragment
	 * @author vladimirjeune
	 *
	 */
	private class LocationViewTextWatcher implements TextWatcher {

		private View _theView ;
		
		/**
		 * CONSTRUCTOR - 
		 * @param v - View that caused the event
		 */
		LocationViewTextWatcher( View v ) {
			super();
			_theView = v;
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// Unimplemented on purpose
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			
			// If user enters data allow him to save/clear it.
			setFieldsEnabled(true);
			setAbilityOnInteraction(_theView);  // Non Button View will turn buttons on
			
			if (_theView.getId() == R.id.location_address_name) {
				mLocation.setName(s.toString());
			} else if (_theView.getId() == R.id.location_address_street) {
				mLocation.setStreet(s.toString());
			} else if (_theView.getId() == R.id.location_address_unit) {
				mLocation.setUnit(s.toString());
			} else if (_theView.getId() == R.id.location_address_city) {
				mLocation.setCity(s.toString());
			} else if (_theView.getId() == R.id.location_address_state) {
				mLocation.setState(s.toString());
			} else if (_theView.getId() == R.id.location_address_zipcode) {
				mLocation.setZipcode(s.toString());
			} 
			
		}

		@Override
		public void afterTextChanged(Editable s) {
			// Unimplemented on purpose
		}
		
	}

}
