package tests;

public class GitResetTutorial{

	
	/*
	 * 1. Reset all files from staging area
	 * --> git reset
	 * 
	 * 2. Reset single file from stanging area
	 * --> git restore --staged file.java
	 * --> git reset file.java
	 * 
	 * 3. Reset a commit from local repository
	 * --> git reset HEAD^  --> sterge ultimul commit si muta fisierele in working directory
	 * 
	 * --> git reset --soft commitID (sterge commit si muta fisierele din commit in stanging area)
	 * --> git reset --mixed commitID (sterge commit simuta fisierele din commit in working dir)
	 * este acelasi lucru cu git reset 
	 * --> git reset --hard commitID (sterge commit si sterge fisierele de pe disk)
	 * 
	 * 
	 * --> git reset HEAD~2  --> sterge ultimele commituri (bazat pe numarul dat ~2) 
	 * si muta fisierele in working directory
	 * 
	 * 3.Reset a merge
	 * 
	 * --> git reset --merge
	 * --> git reset --hard
	 * 
	 * 4. Reset a commit from reomte repo
	 * 
	 * --> git reset HEAD^  (face reset pe local la ultimul commit)
	 * --> git push origin +HEAD (face push resetului de mai sus)
	 * 
	 * Reset doar pe remote, dar pastram local commit
	 * --> git push origin +HEAD^:master
	 * 
	 * 
	 */
	
	
	
	
	
}