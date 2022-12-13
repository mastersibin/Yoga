# Yoga

This repo basically serves as backend for yoga application.

In this application, It doesn't take system clock, instead you need to set the month and the year. Its done in order to test the application. So for almost every
endpoint, you need to set the month and year.

I have created the application using 3 tiers.

Controller Layer
Service Layer
Repository Layer




## Description of API endpoints

### "/enroll"

This api basically helps you to enroll a person into the yoga class. It expects few parameters that are mapped to a EnrollDTO.

- name
- age
- batch_id
- month
- year

When you enroll, your batch will be set and also your fees will be paid.

### "/calculateFees"

This api basically helps you to find the fees you need to pay. This fees is calculated on the basis of the month and year you enter and the last month and year you paid the fees.
Sysem assumes that ones you enroll, you don't un-enroll. So fees will be calculated based on that. 
Formula (Year-PrevYear)*6000 + (Month-PrevMonth)*500

There is no concept of late fee.

Parameters

- name
- month
- year

### "/completePayment"

This api basically puts an entry in the fees table based on the month and year you give.

Parameters

- name
- month
- year

### "/allowed"

This api basically based on the fees, tells you whether or not you are allowed in the yoga class. So suppose if you pay the fees on January, you would 
be allowed for January and February. But after that you won't be allowed.

Parameters

- name
- month
- year

### "/setNextBatch"

Batch remains the same throughout the month. So suppose you have morning batch this month and you need evening next month, just use this method before
1st of next month to set your next month's batch.
So we basically run a cron job every month on the 1st. So cron basically sets current batch with the batch you set with this endpoint. If the next batch
is not set the current batch will be continued next month too.


## Running the application

Clone frontend and backend.
You need maven to build and run backend and npm to run frontend.
You need to have mysql database in your system. 
You can change the credentials in application.properties file for mysql. If you don't do this, backend won't connect to database.

Happy Hacking!!
