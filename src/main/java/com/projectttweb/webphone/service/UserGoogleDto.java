package com.projectttweb.webphone.service;

public class UserGoogleDto {

	private String id;

	private String email;

	private boolean verified_email;

	private String name;

	private String given_name;

	private String family_name;

	private String picture;

	/**
		 * Creates an empty UserGoogleDto instance with default values for all fields.
		 */
	public UserGoogleDto() {
	}

	/**
		 * Constructs a UserGoogleDto with the specified user profile attributes.
		 *
		 * @param id the user's unique identifier
		 * @param email the user's email address
		 * @param verified_email true if the user's email is verified; false otherwise
		 * @param name the user's full name
		 * @param given_name the user's given (first) name
		 * @param family_name the user's family (last) name
		 * @param picture the URL of the user's profile picture
		 */
	public UserGoogleDto(String id, String email, boolean verified_email, String name, String given_name, String family_name, String picture) {
		this.id = id;
		this.email = email;
		this.verified_email = verified_email;
		this.name = name;
		this.given_name = given_name;
		this.family_name = family_name;
		this.picture = picture;
	}

	/**
	 * Returns the user's unique Google ID.
	 *
	 * @return the Google user ID
	 */
	public String getId() {
		return id;
	}

	/**
		 * Sets the user's unique identifier.
		 *
		 * @param id the unique identifier for the user
		 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Returns the user's email address.
	 *
	 * @return the email associated with the Google user profile
	 */
	public String getEmail() {
		return email;
	}

	/**
		 * Sets the user's email address.
		 *
		 * @param email the email address to assign to the user
		 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns whether the user's email is verified.
	 *
	 * @return true if the email is verified; false otherwise
	 */
	public boolean isVerified_email() {
		return verified_email;
	}

	/**
		 * Sets whether the user's email is verified.
		 *
		 * @param verified_email true if the email is verified; false otherwise
		 */
	public void setVerified_email(boolean verified_email) {
		this.verified_email = verified_email;
	}

	/**
	 * Returns the full name of the Google user.
	 *
	 * @return the user's full name
	 */
	public String getName() {
		return name;
	}

	/**
		 * Sets the user's full name.
		 *
		 * @param name the full name to assign to the user
		 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the user's given (first) name.
	 *
	 * @return the given name of the user
	 */
	public String getGiven_name() {
		return given_name;
	}

	/**
		 * Sets the user's given (first) name.
		 *
		 * @param given_name the user's given name
		 */
	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}

	/**
		 * Returns the user's family name.
		 *
		 * @return the family name of the user
		 */
	public String getFamily_name() {
		return family_name;
	}

	/**
		 * Sets the user's family name.
		 *
		 * @param family_name the user's family (last) name
		 */
	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	/**
		 * Returns the URL of the user's profile picture.
		 *
		 * @return the profile picture URL
		 */
	public String getPicture() {
		return picture;
	}

	/**
		 * Sets the URL of the user's profile picture.
		 *
		 * @param picture the URL of the profile image
		 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * Returns a string representation of the UserGoogleDto, including all user attributes and their values.
	 *
	 * @return a formatted string containing the values of all fields in this object
	 */
	@Override
	public String toString() {
		return "UserGoogleDto{" + "id=" + id + ", email=" + email + ", verified_email=" + verified_email + ", name=" + name + ", given_name=" + given_name + ", family_name=" + family_name + ", picture=" + picture + '}';
	}
	

}
