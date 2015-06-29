/**
 * 
 */
package com.ttw.vrj.timetowork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * @author vladimirjeune
 *
 */
public class LocationFragment extends Fragment {

	private Location mLocation;
	private EditText mNameTextView;
	private EditText mStreetTextView;
	private EditText mUnitTextView;
	private EditText mCityTextView;
	private EditText mStateTextView;
	private EditText mZipcodeTextView;
	
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
		// We will do it in code
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
		
		return view;
	}
	
	

}
