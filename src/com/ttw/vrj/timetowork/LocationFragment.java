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
	private Button mDeleteButton;
	private Button mEditButton;
	private Button mSaveButton;
	
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
		
		mDeleteButton = (Button) view
				.findViewById(R.id.location_address_delete_btn);
		mEditButton = (Button) view
				.findViewById(R.id.location_address_edit_btn);
		mSaveButton = (Button) view
				.findViewById(R.id.location_address_save_btn);
		
		
		// Create Listeners
		mNameTextView.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// Purposely left blank
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mLocation.setName(s.toString());  // Set name for Location obj
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// Purposely left blank
			}
		});

		mStreetTextView.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// Purposely left blank
			}

			// TODO:  See getId compare to R.id.#### so can just use if/else.
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mLocation.setStreet(s.toString());  // Set Street name on Location obj
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// Purposely left blank
			}
		});

		mUnitTextView.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// Purposely left blank
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mLocation.setUnit(s.toString());  // Set Unit if applicable on obj
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// Purposely left blank
			}
		});
		
		mCityTextView.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// Purposely left blank
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mLocation.setCity(s.toString());  // Set Unit if applicable on obj
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// Purposely left blank
			}
		});
		
		mStateTextView.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// Purposely left blank
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mLocation.setState(s.toString());  // Set Unit if applicable on obj
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// Purposely left blank
			}
		});
		
		mZipcodeTextView.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// Purposely left blank
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mLocation.setZipcode(s.toString());  // Set Unit if applicable on obj
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// Purposely left blank
			}
		});
		
		// Buttons
		mDeleteButton.setOnClickListener(new LocationViewClickListener());
		mEditButton.setOnClickListener(new LocationViewClickListener());
		mSaveButton.setOnClickListener(new LocationViewClickListener());
		
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
		
		if (v.getId() == R.id.location_address_delete_btn) {
			mDeleteButton.setEnabled(false);  // Must type something to delete it.
			mSaveButton.setEnabled(false);    // Cannot save a blank location.
		} else if (v.getId() == R.id.location_address_edit_btn) {	
			// TODO: Have edit reset blinker to Name.  Enable fields, Have field enable all buttons
			setFieldsEnabled(true);
			mDeleteButton.setEnabled(true);
			mEditButton.setEnabled(true);
			mSaveButton.setEnabled(true);
		} else if (v.getId() == R.id.location_address_save_btn) {
			mSaveButton.setEnabled(false);    // Nothing left to save, but can edit to reenable all
			mDeleteButton.setEnabled(false);  // If you need delete after save, hit edit 1st
			
			// Fields
			setFieldsEnabled(false);
			
		} else {  // Any EditText 
			mDeleteButton.setEnabled(true);
			mEditButton.setEnabled(true);
			mSaveButton.setEnabled(true);
		}
		
		
	}

	/**
	 * SETFIELDSENABLED - will set all fields to on or off depending on inputted boolean
	 * @param aBool - tells whether all fields should be enabled or disabled.
	 */
	private void setFieldsEnabled(boolean aBool) {
		mNameTextView.setEnabled(aBool);
		mStreetTextView.setEnabled(aBool);
		mUnitTextView.setEnabled(aBool);
		mCityTextView.setEnabled(aBool);
		mStateTextView.setEnabled(aBool);
		mZipcodeTextView.setEnabled(aBool);
	}


	private class LocationViewClickListener implements View.OnClickListener {

		 private final String LOG_TAG = LocationViewClickListener.class.getSimpleName() ;
		
		@Override
		public void onClick(View v) {
			Log.d(LOG_TAG, "In onClick()");
			if (v.getId() == R.id.location_address_delete_btn) {

				setAbilityOnInteraction(v);
				clear();
				
				// TODO: Remove Toast
				Toast.makeText(getActivity(), "DELETE:\n" + mLocation.toString()
						, Toast.LENGTH_SHORT).show();
				
			} else if (v.getId() == R.id.location_address_edit_btn) {
				setAbilityOnInteraction(v);
				
				Toast.makeText(getActivity(), "EDIT:\n" + mLocation.toString()
						, Toast.LENGTH_SHORT).show();
			} else if (v.getId() == R.id.location_address_save_btn) {

				Log.d(LOG_TAG, "There is a problem in the Save button.");
				
				// Save should be disabled until there is a change in the data
				// Save should disable all view fields
				setAbilityOnInteraction(v);
				
				Toast.makeText(getActivity(), "SAVE:\n" + mLocation.toString()
						, Toast.LENGTH_SHORT).show();
			}
			
		}
		
	}

}
