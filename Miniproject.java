package miniproject;

import java.util.ArrayList;
import java.util.Scanner;

class Note{
	private String Title;
	private String Description;
    private String Password;
	 
    Note(String Title,String Description,String Password){
		 this.Title=Title;
		  this.Description=Description;
		  this.Password=Password;
		
	  }
	  public String getTitle() {
	        return Title;
	    }

	    public boolean unlockNoteWithPassword(String inputPassword) {
	        if (this.Password == null) {
	            // If password is null, no need to check, just unlock
	            return true;
	        }
	        return this.Password.equals(inputPassword);
	    }

	    public String getDescription() {
	        return Description;
	    }

//	   public void setDescription(String description) {
//	        this.Description = description;
//	    }
//
//	    public void setPassword(String password) {
//	        this.Password = password;
//	    }
	     
	    public boolean isPassword() {
	    	return this.Password != null;
	    }

	    
	    public String printnote() {
	        return "Title: " + Title + "\nDescription: " + Description;
	    }
	}

class User{
	
	private String username;
	 private String password;
	 private ArrayList<Note>notes;
	 
	 User(String username,String password){
		 this.username=username;
		 this.password=password;
		 this.notes=new ArrayList<Note>();//default array empty array
		
	}

	public String getusername(){
	return username;
}
public boolean checkpassword(String pwd) {
	if(this.password.equals(pwd)) {
		
		return true;
	}
	else {
	
		return false;
	}
}
	
public ArrayList<Note> getnote() {
		return this.notes ;
	}
	
public void addnote(String title,String description,String notePassword ){

	notes.add(new Note(title,description,notePassword));
		
}
public void viewNotes() {
    if (notes.isEmpty()) {
        System.out.println("No notes available.");
    } else {
    	System.out.println("Your Note:");
        for (int i = 0; i < notes.size(); i++) {
            System.out.println((i + 1) + ". " + notes.get(i).getTitle() + (notes.get(i).isPassword()? " (Password Protected)": ""));
        }
    }
}
public void viewNotDetail(String Title,String notePassword) {
	Note  Note=getNoteByTitle(Title);
	if(Note !=null) {
		
		if(Note.unlockNoteWithPassword(notePassword)) {
			//System.out.println(Note);
			Note.printnote();
		}
		else {
			System.out.println("incorrect password.");
		}
	}
		else {
			System.out.println("Note not founded.");
		}
	}
        private Note getNoteByTitle(String title) {
          for (Note note : notes) {
                if (note.getTitle().equals(title)) {
                       return note;
        }
    }
             return null;
}
         public Note getNoteByIndex(int index) {
           return this.notes.get(index);	
}
            public int getNotesCount() {
	           return this.notes.size();
}
}
  class NotesApp{
	
	private ArrayList<User>users;
	 private User loggedinUser;
	 
	public NotesApp(){
		 this.users=new ArrayList<>();
		 this.loggedinUser=null;
	}
		User getUserByUsername(String username) {
		 for(User user:users) {
			 if(user.getusername().equals(username)) {
				 return user;
			 }
		 }
		 return null;
	 }
	 public void addUser(String username,String password) {
		 if(getUserByUsername(username)==null) {
			users.add(new User(username,password));
			 System.out.println("User"+username+"addded.");
			
		 }else {
			 System.out.println("User already exists.");
		 }
	 }
	 public boolean login(String username,String password) {
		 User user=getUserByUsername(username);
		 if(user!=null&&user.checkpassword(password)) {
			 loggedinUser=user;
			 System.out.println("Login successful.");
			 return true;
		 }
		 else {
			 System.out.println("invalid username or password.");
			 return false;
		 }
	 }
	 public void logout() {
		 if(loggedinUser!=null) {
			System.out.println("User"+loggedinUser.getusername()+"logged out."); 
			loggedinUser=null;
		 }
		 else {
			 System.out.println("no user is currently logged in.");
		 }
	 }
	 public User getLoggedinUser() {
		 return loggedinUser;
	 }
//	 public void viewUsers() {
//		 if(users.isEmpty()) {
//			 System.out.println("No users available.");
//		 }
//		 else {
//			 System.out.println("Available users:");
//			 for(User user:users) {
//				 System.out.println("-"+user.getusername());
//			 }
//		 }
//	}

	 }
  
  public class Miniproject {
	  static void dashLine() {
		  System.out.println("-----------------------------------------------------------------");
	  }
	  static void asteriskLine() {
		  System.out.println("*******************************************************************");
	  }
	
public static void main(String[] args) {
	NotesApp notesapp = new NotesApp();
	Scanner scanner= new Scanner(System.in);
	notesapp.addUser("vino","1234");
	notesapp.addUser("pavi", "4321");
	notesapp.login("vino","1234");
	asteriskLine();{
		System.out.println("                  WELCOME...                 ");
		asteriskLine();
}
	
	 
		notesapp.getLoggedinUser().addnote("good","vino is Good girl", "good");
		notesapp.getLoggedinUser().addnote("bad", "vino is salem girl", "bad");
		notesapp.getLoggedinUser().addnote("Cute","vino is cute girl",null);

	
	while(true) {
		
		System.out.println("\nNotesApp-Menu");
		System.out.println("1.Register");
		System.out.println("2.Login");
		System.out.println("3.Add Note");
		System.out.println("4.View Notes");
		System.out.println("5.Logout");
		System.out.println("6.Exit");
		System.out.println("Choose an option");
		
		int option=scanner.nextInt();
		scanner.nextLine();
		
		switch(option) {
		case 1:
			System.out.println("Enter new username:");
			String username=scanner.nextLine();
			System.out.println("Enter new password:");
			String password=scanner.nextLine();
			notesapp.addUser(username,password);
			break;
			
		case 2:
			System.out.println("Enter a username:");
			username =scanner.nextLine();
			System.out.println("Enter a password:");
			 password=scanner.nextLine();
			notesapp.login(username,password);
			break;
			
		case 3:
			User loggedinUser=notesapp.getLoggedinUser();
			if (loggedinUser != null) {
                System.out.print("Enter note title: ");
                String title = scanner.nextLine();
                System.out.print("Enter note description: ");
                String description = scanner.nextLine();
                System.out.print("Set a password for this note (or press Enter for no password): ");
                String notePassword = scanner.nextLine();
                if (notePassword.isEmpty()) {
                    notePassword = null; // Allow null password
                }
                asteriskLine();{
                loggedinUser.addnote(title, description, notePassword);
               System.out.println("                   Note added...                 ");
               asteriskLine();}
            		 } else {
                System.out.println("Please login to add a note.");
            }
			break;
		case 4:
			loggedinUser=notesapp.getLoggedinUser();
			if(loggedinUser!=null) {
				while(true) {
					loggedinUser.viewNotes();
	                System.out.println("Please enter a note index to view in detail or press 0 for back to the main menu.");
					int viewOption=scanner.nextInt();
					if(viewOption == 0) {
						break;
					}else {
						if(loggedinUser.getNotesCount()>= viewOption ) {
							
							Note selectedNote = loggedinUser.getNoteByIndex(viewOption-1);
							
							if(selectedNote.isPassword()) {
								while(true) {
									System.out.println("Enter a password to view note");
									Scanner sc = new Scanner(System.in);
									String enteredPwd = sc.nextLine();
								if(selectedNote.unlockNoteWithPassword(enteredPwd)) {
										System.out.println("Title : " + selectedNote.getTitle());
										System.out.println("Description : " + selectedNote.getDescription());
										System.out.println("Press 0 for back");
										int exitOption=scanner.nextInt();
										if(exitOption == 0) {
											break;
										}
									}else {
										System.out.println("Incorrect Passoword for the note ");
										System.out.println("Press 0 for back or Press any number for continue");
										int exitOption=scanner.nextInt();
										if(exitOption == 0) {
											break;
										}
									}
								}
								
							}else {
								while(true) {
									System.out.println("Title : " + selectedNote.getTitle());
									System.out.println("Description : " + selectedNote.getDescription());
									System.out.println(" Press 0 for back");
									int exitOption=scanner.nextInt();

									if(exitOption == 0) {
										break;
									}
								}
								
							}
							
							
						}else {
							System.out.println("Please enter a index within a range.");
						}
						
					}
					
				}
			}else {
				 
				System.out.println("Please login to view notes.");
			}
			break;
	
			case 5:
				
				 asteriskLine();{
			notesapp.logout();
			 asteriskLine();}
			break;
			
			
		case 6:
			asteriskLine();{
			System.out.println("                    EXITING...                ");
			asteriskLine();
			return;
			
			}
			
	    default:
	    	asteriskLine();{
	    	System.out.println("Invalid option.Please Try again.");
	    	
			asteriskLine();}
	    	}
	}
}
	}