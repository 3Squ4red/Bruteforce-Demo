# Bruteforce-Demo
A demo of how **Bruteforce attacks** are made.

First run _SignUp.java_ to enter a name and password. All the information will be saved in the file named _Passwords.txt_. Next run _Login.java_ just to check whether the credentials were entered correclty or not.

***NOTE: IF YOU WANT TO CHANGE YOUR PASSWORD, THEN SIMPLY REMOVE THE LINE CONTAINING YOUR INFORMATION FROM _Passwords.txt_ AND THEN SIGN IN AGAIN.***


The Password guessing option tries all the possible passwords, so in that case there's no need to forcefully terminate the program. Besides the Password guessing does all the work on a single thread, so there's no overhead work to your CPU. But while using Jumbled word decrypter, your computer may slow down a bit because in order to find the correct combination of the letters you entered, it creates **120** threads. Each of which **RANDOMLY** shuffles the letters you entered. So if you enter a wrong combination of letters or a combination of a length less than or greater than that of the real password, **all the 120 threads will keep running until you forcefully terminate the program**. So, to solve this I've added a feature which will automatically terminate the program (and closes all the threads) in a specific time. Here's the detailed information about the timings while using Jumbled Word Decrypter.

* ***60 seconds*** - This message will be shown: *"It's taking longer than usual"*
* ***120 seconds*** - This message will be shown: *"Password still not found. Terminating in 30 seconds"*
* ***150 seconds*** - This message will be shown: *"Terminating...    Time elapsed: 150"*

Now, comes the main part. Run _Bruteforce.java_ and follow the instructions that appears on the console. **ONLY WHILE USING** the Jumbled Word Decrypter option for finding the password, the total time elapsed will be shown **IN SECONDS**. If you find any bug in any file, then simply mail me that bug with a screenshot. Will surely fix it in a day or two. 
**HAVE FUN!**
