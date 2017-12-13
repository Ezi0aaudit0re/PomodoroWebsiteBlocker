Author Aman Nagpal
email - amannagpal4@gmail.com

This project was developed to increase productivity by removing disctractions 

It uses two techniques to do this 

1.) Pomodoro technique 
2.) Website Blocker 


It blocks websites on mac by overwriting the /etc/hosts files 

It blocks the websites for the pomodoro time period and then unblocks them for the rest period. 

The program does that through recursion for 4 times 

The different ways to run the program are 

sudo java Driver options -> This gives the option to set the pomodoro and the rest time 
sudo java Driver websites -> if we want to add websites to the blocklist 
sudo java Driver -> This straight away starts the pomodoro timer 


For presentation purpose the pomodor and rest timers have been set to 1 minute

change these with 

sudo java Driver options

When using this program


To add an extra layer to prevent distractions. I have overriden the ctrl-c command. THe user cannot exit the program by pressing ctlr-c
