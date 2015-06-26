/**
 * 
 */
package com.ttw.vrj.timetowork;

import java.util.UUID;

/**
 * LOCATION - Holds common code for ImportantLocation and PotentialResidence
 * @author vladimirjeune
 *
 */
public class Location {

	// TODO: add mIsStarred, mCategory, rest of address stuff.
	
	private final UUID mId;
	private String mLabel;
	private String mStreet;
	private String mUnit;
	
	/**
	 * CONSTRUCTOR - 
	 */
	public Location() {
		mId = UUID.randomUUID();
	}
	
	// TODO: Will have abstract() named Category.  Work(Work,Gym..), Home(Rental, Condo..)
	
	/**
	 * GETLABEL - will return the label
	 * @return the Label
	 */
	public String getLabel() {
		return mLabel;
	}



	/**
	 * SETLABEL - sets the label for the Location
	 * @param Label the Label to set
	 */
	public void setLabel(String aLabel) {
		mLabel = aLabel;
	}



	/**
	 * GETSTREET - will return the street information for the Location
	 * @return the Street
	 */
	public String getStreet() {
		return mStreet;
	}



	/**
	 * SETSTREET - will set the street for this Location
	 * @param mStreet the mStreet to set
	 */
	public void setStreet(String aStreet) {
		mStreet = aStreet;
	}



	/**
	 * GETUNIT - will return the Unit for this Location
	 * @return the Unit
	 */
	public String getUnit() {
		return mUnit;
	}



	/**
	 * SETUNIT - will set the Unit for this Location
	 * @param Unit the Unit to set
	 */
	public void setUnit(String aUnit) {
		mUnit = aUnit;
	}



	/**
	 * GETID - will return the Unique ID of this Location
	 * @return the Id
	 */
	public UUID getId() {
		return mId;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
