# android-code-challenge
This repo is for the Android coding interview for new developers :)

Candidates:
Please put build instructions, assumptions and any other notes that you'd like your reviewers to know about in this file
- changed gradle to 7.3 and java to 11
- Dependency Injection with Koin
- SharedPreferences is used to store access token
- Glide for rendering images and crop image to circle

Running the application
- The application contains two main screen: Log in screen and Post List screen
- There's no check on the username and password fields, user can go to Post List screen by simply clicking on the log in button.
  The login api gets called and generate a random token.
- The access token is then stored on SharedPreference, and user is directed to Post List screen


Future Improvements
- Validation on username and password fields
- When user navigates back to sign in screen, clear post list