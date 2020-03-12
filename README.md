# Driver History


## About

A CLI tool to read a list of Drivers and Trips input by a .txt file, and output total distance driven and average rate per Driver.

Example input:

```
Driver (Driver Name)
Trip (Driver Name) (Trip Start Time) (Trip End Time) (Trip Distance)
```

Example output:
```
(Driver Name): (Total Miles) @ (Average MPH)
```

## Requirements
```
JDK 11.0.6
JUnit 4.12 (For Testing Only)
```

## Installation and Running

As this is a Java application, it will first need to be compiled. After cloning or downloading this repository, run Terminal or Console, and change directory to the following path from the root of the project.

```Bash
cd WorkSample/driving-history/src/main/java/worksample/driving_history
```

Execute the following command:

```Bash
javac *.java view/*.java exception/*.java conversion/*.java driverdata/filereader/*.java driverdata/*.java
```

To run the tool, from the WorkSample/driving-history/src/main/java directory, execute the following:

```Bash
java worksample.driving_history.DrivingHistoryCLI
```

## Example

Example text input file:
```
Driver Dan
Driver Lauren
Driver Kumi
Trip Dan 07:15 07:45 17.3
Trip Dan 06:12 06:32 21.8
Trip Lauren 12:01 13:16 42.0
```

Example output:
```
Path to data file: gooddataexample.txt
Print with error report? (Y/N): y
-------------------------------------
No errors to report.
-------------------------------------
Dan: 39.1 miles @ 47 mph
Lauren: 42.0 miles @ 34 mph
Kumi: 0 miles
-------------------------------------
```
## Testing

Tests were written using JUnit, and can be found in the src/test package. Using an IDE such as Eclipse, the following classes can be tested:

- Trip
- TripDistanceTool
- TriRateTool
- TripTimeTool

## Rundown

Once the tool is running, a path to the file location to be read is requested. Optionally, you may choose the run an error report to detect and report any invalid data lines.

The tool will read the text file, and store each line into a String list.
It will then loop through the list. At each index, it will parse each part that is separated by white space, into a String array.

If index [0] in the array is equal to "Driver", the tool will create a new Driver object, with index [1] of the array being the Driver's name. The Driver is then added to a Hash Map as the key, and a value of a new Trip is created. Trip data is defined as a Driver's total time, total distance, and average rate. If there is a duplicate Driver requested, an error will be added, and continue to read the rest of the data in the list.

If index [0] in the array is equal to "Trip", the tool will read index [1] to access the Driver's name, and check that this Driver has already been registered in the map. If the Driver has not been registered, an error will be added, and continue to read the rest of the data in the list. If the Driver is registered, the tool will run "calculateTrip" on the remaining parts of the array, and add the resulting total to the key Driver's value.

Calculate Trip expects 3 values: Trip Start Time, Trip End Time, and Trip Distance. Trip Start Time and Trip End Time combine to make Trip Hours.

Calculate Trip is a combination of 3 methods:

calculateTripHours (TripTimeTool) which parses the String input Start and End times, and returns total hours in BigDecimal format.
This total is then added to the Trip time values that are already present.

calculateTripDistance (TripDistanceTool) parses the String distance and returns a BigDecimal.
This total is then added to the Trip distance values that are already present.

calculateTripRate (TripRateTool) divides the total Trip distance by the total Trip hours.
If the average rate of the new Trip to be added is less than 5 mph or greater than 100 mph, the Trip will not be added to the Trip data.

The new Trip data is returned to the map.

After all data has been read, the map is looped to print the key/value pairs.

If the option to print an error report was selected, the tool will loop through the list of errors and print each one, prior to the Trip data list.

