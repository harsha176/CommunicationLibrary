package edu.ncsu.csc574.emailserver.workflowmanager;

/**
 * All commands supported by email service
 * @author Harsha
 *
 */
public enum Command {
	LOGIN,
	LOGOUT,
	SEND,
	RECV,
	RETRIEVE,
	DELETE,
	FORGOT,
	CREATE
}