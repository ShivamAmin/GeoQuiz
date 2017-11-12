# GeoQuiz

This is a repository of an application built following the 3rd edition of Android Programming: The Big Nerd Ranch Guide. I will add chapters to the chapters list as I complete them.

## Chapter List

### Chapter 1
In this chapter, I learned how to create an application in Android Studio. I also learned how to hard code widgets with attributes, and learned about the different layouts such as Relative Layout and Linear Layout. I also learned about the string resources file, how to add listeners to the buttons, and about toasts. For the challenge in this chapter, I had to learn how to customize the toast using the setGravity function.

### Chapter 2
In this chapter, I learned how to add new classes to my project, and how to import images to use with image buttons. Another thing I learned in this chapter is how to show more than one question and add the ability to iterate through multiple questions, each with their own unique answer. I did this using a super class called Question that holds the structure of each question and create an array in the main class which is of type question and holds the information of each question. For the first challenge in this chapter, I added a listener to the textview, so that the questions will cycle when the text is tapped. For the second challenge in this chapter I added a button to go to the previous question. For the third challenge, I switch the previous and next buttons to imagebuttons.

### Chapter 3
In this chapter, I learned about the activity life cycle. I also learned how to create a dedicated landscape layout as well as how to save information when the user rotates their device and load that information when the rotation is done. For the first challenge in this chapter, I learned how to track which questions were answered so as to not allow the user to reanswer questions. For the second challenge in this chapter, I figured out how to display the percentage score after all the questions were answered.

### Chapter 4
In this chapter, I learned the different methods available to developers to debug applications. One of the most likely places to see what errors you have is looking at the error level of the debug output in Android Studio. This is where you can see information about exception errors, and their stack trace. This is really helpful because different types of errors trigger different errors. For example, if you are trying to access the 5th element in an array that has 4 values, you will get a ArrayOutOfBounds exception and it's stack trace will help you track down which line of code is causing the error. If you are not getting an error, but are getting unexpected results, you can set breakpoints in your code to track variables and iterate through your code line by line. Other options include Android Lint which analyzes your code without running it for errors, Layout Inspector for layout errors, and Allocation Tracking which allows you to tune performance.

### Chapter 5
In this chapter, I learned how to create a new activity to my application. I also learned how to send and receive data from the new activity using intents. I used the new activity to allow the user to cheat. When the user taps the cheat button on the mail activity, a new activity pops up with a confirmation button once the user taps the confirmation button, the answer is displayed after which the user can backout of the activity and choose the answer in the main activity. For the challenges in this chapter, I had to implement what I learned in chapter 3, the activity life cycle, to close 3 loopholes that allowed the users to circumvent the cheater detection implemented previously in the chapter. The first way the user could clear the cheating result was to rotate the device inside the CheatActivity. This was fixed by overriding the onSaveInstanceState to save a boolean indicating weather the answer was shown, and then loading it in the onCreate method. The last two ways the user could clear the cheating result was to rotate the questions in the QuizActivity or rotating the questions until the question they cheated on comes back. I fixed this by adding a new boolean field in the Questions class to hold the weather the user deciced to cheat. Then I updated the QuizActivity to update this value whenever the confirm button is tapped in the CheatActivity.

## Author

* **Shivam Amin** - *Developer* - [Website](https://www.ShivamAmin.com/)

## Resources
* ***Android Programming: The Big Nerd Ranch Guide*** - *Textbook* [Website](https://www.bignerdranch.com/books/android-programming/)
