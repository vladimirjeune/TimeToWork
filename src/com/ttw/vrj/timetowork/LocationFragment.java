/**
 * 
 */
package com.ttw.vrj.timetowork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * @author vladimirjeune
 *
 */
public class LocationFragment extends Fragment {

	private static final String LOG_TAG = LocationFragment.class.getSimpleName();
	private final String KEY_INDEX =  LocationFragment.class.getSimpleName() + ": enabledStates";
	private final String CHECKBOX_INDEX = LocationFragment.class.getSimpleName() + ": checkBoxState";
	private List<View> viewList;
	private HashMap<Integer, Boolean> _enabled_m;
	
	// Main Object
	private Location mLocation;
	
	// Editable Views
	private EditText mNameTextView;
	private EditText mStreetTextView;
	private EditText mUnitTextView;
	private EditText mCityTextView;
	private Spinner mStateSpinnerView;
	private EditText mZipcodeTextView;
	
	// Buttons
	private CheckBox mIsImportant;

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
		viewList = new ArrayList<View>();  // Holds views for collection of state later
		_enabled_m = null;
	}

	/**
	 * ONCREATEVIEW - where you inflate the layout for the Fragment's View.  Then return
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
		
		// Obtain UI Element Values and store for rotation
		mNameTextView = (EditText) view
				.findViewById(R.id.location_address_name);
		viewList.add(mNameTextView);
		mStreetTextView = (EditText) view
				.findViewById(R.id.location_address_street);
		viewList.add(mStreetTextView);
		mUnitTextView = (EditText) view
				.findViewById(R.id.location_address_unit);
		viewList.add(mUnitTextView);
		mCityTextView = (EditText) view
				.findViewById(R.id.location_address_city);
		viewList.add(mCityTextView);
		mZipcodeTextView = (EditText) view
				.findViewById(R.id.location_address_zipcode);
		viewList.add(mZipcodeTextView);
		
		// Spinners
		mStateSpinnerView = (Spinner) view.findViewById(R.id.location_address_state);
		viewList.add(mStateSpinnerView);
		
		// Buttons
		mIsImportant = (CheckBox) view
				.findViewById(R.id.location_address_important);
		viewList.add(mIsImportant);
		mClearButton = (Button) view
				.findViewById(R.id.location_address_clear_btn);
		viewList.add(mClearButton);
		mEditButton = (Button) view
				.findViewById(R.id.location_address_edit_btn);
		viewList.add(mEditButton);
		mSaveButton = (Button) view
				.findViewById(R.id.location_address_save_btn);
		viewList.add(mSaveButton);
		mNextButton = (Button) view
				.findViewById(R.id.location_address_next_btn);
		viewList.add(mNextButton);
			

		// Create Listeners
		// Fields
		mNameTextView.addTextChangedListener(new LocationViewTextWatcher(mNameTextView));
		mStreetTextView.addTextChangedListener(new LocationViewTextWatcher(mStreetTextView));
		mUnitTextView.addTextChangedListener(new LocationViewTextWatcher(mUnitTextView));
		mCityTextView.addTextChangedListener(new LocationViewTextWatcher(mCityTextView));
		mZipcodeTextView.addTextChangedListener(new LocationViewTextWatcher(mZipcodeTextView));

		// Spinners
		ArrayAdapter<CharSequence> stateArrayAdapter = ArrayAdapter
				.createFromResource(getActivity(), R.array.states_array
						, android.R.layout.simple_spinner_item);
		
		// Layout to use for List of Choices
		stateArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Apply Adapter to Spinner
		mStateSpinnerView.setAdapter(stateArrayAdapter);
		
		// Setting Spinner Listener
		mStateSpinnerView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mLocation.setState((String) parent.getItemAtPosition(position) );
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// Purposely left blank				
			}
			
		});
		
		
		// Buttons
		mIsImportant.setOnClickListener(new LocationViewClickListener());
		mClearButton.setOnClickListener(new LocationViewClickListener());
		mEditButton.setOnClickListener(new LocationViewClickListener());
		mSaveButton.setOnClickListener(new LocationViewClickListener());
		mNextButton.setOnClickListener(new LocationViewClickListener());
		
		// Set or reset the enabled states
		getEnabledStatesFromBundleIfAvailable(savedInstanceState);
		getObjectStatesFromBundleIfAvailable(savedInstanceState);

		return view;
	}

	
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		_enabled_m = new HashMap<Integer, Boolean>();  // HashMaps are serializable
		
		// Gathering enabled state of all Views
		for (View v: viewList) {
			_enabled_m.put(v.getId(), v.isEnabled());
		}
		
		// Save enabled state for reconstruction
		outState.putSerializable(KEY_INDEX, _enabled_m);
		outState.putBoolean(CHECKBOX_INDEX, mLocation.isImportant());
		
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onStart()
	 */
	@Override
	public void onStart() {
		super.onStart();
		setAbilitiesOnStart();  // UI is ready, so set/reset enabled
	}

	/**
	 * GETENABLEDSTATESFROMBUNDLEIFAVAILABLE - will get the previous Enabled States of the Fragment
	 * 		after rotation, if the Bundle exists.  Value will be used in onStart().
	 * 		If Bundle not available, value will be null.
	 */
	private void getEnabledStatesFromBundleIfAvailable(Bundle savedInstanceState) {
		
		if (savedInstanceState == null) {
			_enabled_m = null;
		} else if (savedInstanceState.containsKey(KEY_INDEX)) {  // In case View not there in this orientation
			_enabled_m = ((HashMap<Integer, Boolean>) savedInstanceState.getSerializable(KEY_INDEX));
		}
	}

	/**
	 * GETOBJECSSTATESFROMBUNDLEIFAVAILABLE - will take the object states that were saved in the
	 * 		Bundle and apply them to the objects in the onCreateView.
	 * @param savedInstanceState
	 */
	private void getObjectStatesFromBundleIfAvailable(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			if (savedInstanceState.containsKey(CHECKBOX_INDEX)) {
				mLocation.setIsImportant(savedInstanceState.getBoolean(CHECKBOX_INDEX));
			}
		}
	}

	
	/**
	 * CLEARVIEW - will empty all fields of this view and delete field data from 
	 * 		backing object.
	 */
	private void clear() {
		mLocation.clear();  // Make sure the backing object rms field data
		
		mIsImportant.setChecked(false);  // Reset to false
		mNameTextView.setText("");  // Clear all fields
		mStreetTextView.setText("");
		mUnitTextView.setText("");
		mCityTextView.setText("");
		mStateSpinnerView.setSelection(0);  // 0 should be empty.  Meaning no selection
		mZipcodeTextView.setText("");
		
	}
	


	/**
	 * SETABILITIESONSTART - to be used in onStart() to set the initial state
	 * 		of the buttons the first time the user goes to the page.  And the previous
	 * 		state of the buttons if the Fragment is being reconstructed after rotation. 
	 */
	private void setAbilitiesOnStart() {
		
		if (_enabled_m == null) {
			mClearButton.setEnabled(false);
			mSaveButton.setEnabled(false);
			mNextButton.setEnabled(false);
		} else {

			for (View v : viewList) {
				// Just in case available views have changed for new orientation
				if (_enabled_m.containsKey(v.getId())) {  
					v.setEnabled(_enabled_m.get(v.getId()));
				}
			}

		}
		
	}
	
	/**
	 * SETABILITYBASEDONBUTTON - will enable and disable Views onscreen based on
	 * 		the View that was just interacted with.  Should be used in Listeners.
	 * @param v - View most recently interacted with by user.
	 */
	private void setAbilityOnInteraction(View v) {
		
		if (v.getId() == R.id.location_address_clear_btn) {
			mIsImportant.setEnabled(false);
			mClearButton.setEnabled(false);  // Must type something to delete it.
			mSaveButton.setEnabled(false);    // Cannot save a blank location.
			mNextButton.setEnabled(false);
		} else if (v.getId() == R.id.location_address_edit_btn) {	
			// TODO: Have edit reset blinker to Name.
			setFieldsEnabled(true);
			mIsImportant.setEnabled(true);
			mClearButton.setEnabled(true);
			mEditButton.setEnabled(true);
			mSaveButton.setEnabled(true);
			mNextButton.setEnabled(false);  // Do not want to move until data set.
		} else if (v.getId() == R.id.location_address_save_btn) {
			mIsImportant.setEnabled(false);
			mSaveButton.setEnabled(false);    // Nothing left to save, but can edit to reenable all
			mClearButton.setEnabled(false);  // If you need delete after save, hit edit 1st
			mNextButton.setEnabled(true);
			
			// Fields
			setFieldsEnabled(false);		
			
		} else {  // Any EditText View should enable these buttons
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
		mIsImportant.setEnabled(isEnabled);
		mNameTextView.setEnabled(isEnabled);
		mStreetTextView.setEnabled(isEnabled);
		mUnitTextView.setEnabled(isEnabled);
		mCityTextView.setEnabled(isEnabled);
		mStateSpinnerView.setEnabled(isEnabled);
		mZipcodeTextView.setEnabled(isEnabled);
	}


	/**
	 * CLEARTHENSETENABLED - makes sure that clear is called first.  Otherwise, clear will cause the 
	 * 		wrong buttons to be enabled at the wrong time.  The act of clearing modifies the textfields
	 * 		which enables most buttons.
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
				
			} else if (v.getId() == R.id.location_address_important) {
				mLocation.setIsImportant( ((CheckBox) v).isChecked() );
				
				Toast.makeText(getActivity(), "STAR:\n" + mLocation.toString()
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
