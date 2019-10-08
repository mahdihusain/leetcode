Given a date, return the corresponding day of the week for that date.

The input is given as three integers representing the day, month and year respectively.

Return the answer as one of the following values {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.

 

Example 1:

Input: day = 31, month = 8, year = 2019
Output: "Saturday"
Example 2:

Input: day = 18, month = 7, year = 1999
Output: "Sunday"
Example 3:

Input: day = 15, month = 8, year = 1993
Output: "Sunday"
 

Constraints:

The given dates are valid dates between the years 1971 and 2100.

class Solution {
    public String dayOfTheWeek(int day, int month, int year) {
        int totalDays = numDaysOfYears(year) + numDaysOfMonths(month, year) + day;
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        
        int rem = totalDays%7;
        return days[(rem+4)%7];
    }
    
    int numDaysOfYears(int year){
        int yearDiff = year - 1971;
        int rem = yearDiff%4;
        int div = yearDiff/4;
        int leap = 0;
        
        if(rem>=2){
            leap = div + 1;
        }else{
            leap = div;
        }
        
        return leap*366 + (yearDiff-leap)*365;
    }
    
    int numDaysOfMonths(int month, int year){
        boolean leap = false;
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(year%4==0){
            daysInMonth[1] = 29;
        }
        
        int sum = 0;
        for(int i=0; i<month-1; i++){
            sum += daysInMonth[i];
        }
        return sum;
    }
    
}
