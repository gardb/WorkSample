# Driver History


## About

A CLI tool to read a list of Drivers and Trips input by a `.txt` file, and output total distance driven and average rate per Driver.

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

To run the tool, from the `WorkSample/driving-history/src/main/java directory`, execute the following:

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
Lauren: 42 miles @ 34 mph
Dan: 39 miles @ 47 mph
Kumi: 0 miles
-------------------------------------
```
## Testing

Tests were written using JUnit, and can be found in the src/test package. Using an IDE such as Eclipse, the following classes can be tested:

- `Trip`
- `Driver`
- `HistorySorter`
- `TripDistanceTool`
- `TriRateTool`
- `TripTimeTool`

## Rundown

Any data that is input via user or external file is wrapped in a try/catch, to prevent the program from crashing during use or when checking potenitally invalid data. The majority of calculated methods have been designed to return null if data that does meet the criteria is provided, where the catch will look for these null pointers. The following is the basic order of operations:

1. A path to the `.txt` file location to be read is requested from the `Menu` class, and passed on to the `TxtHistoryReader` class. 

2. An option is given to print an error report along with the data.

3. The tool will read the `.txt` file, and store each line into a String List using Scanner. 

4. Looping through the list; at each index, each part that is separated by white space is parsed into a String array. 
 - The tool will read through the String array and perform the calculations before going to the next line in the List.

5. If `index [0]` in the String array is equal to `Driver`, the tool will create a new `Driver` object, with `index [1]` of the array being the driver's name. The `Driver` object is then added to a HashMap as the key, and a value of a new `Trip` object is created.

 - The `Driver` object includes a custom `hashCode` function to allow the object to be accessible in the map by comparing an identical object (in this case, the String `name`).

 - The `Trip` data object is defined as 3 parts: 
  - Total Time (in hours)
  - Total Distance (in miles)
  - Average Rate (in miles per hour)

 - If there is a duplicate `Driver` requested, an error will be added, and continue to read the rest of the data in the list.

6. If `index [0]` in the array is equal to `Trip`, the tool will read `index [1]` to access the driver's name, and check that this driver has already been registered in the map. 
 - If the driver has not been registered, an error will be added, and continue to read the rest of the data in the list. 

 - If the driver is registered, the tool will run `calculateTrip` on the remaining parts of the array, and add the resulting total to the key `Driver`'s value.

 - `calculateTrip` expects 3 values: 
  - Trip Start Time
  - Trip End Time
  - Trip Distance

 - `calculateTrip` is a combination of 3 methods:
  - `calculateTripHours` (TripTimeTool) which parses the String input Start and End times, and returns total hours in BigDecimal format. This total is then added to the time values that are already present in the `Driver`/ `Trip` map.
  - `calculateTripDistance` (TripDistanceTool) parses the String distance and returns a BigDecimal. This total is then added to the `Trip` distance values that are already present.
  - `calculateTripRate` (TripRateTool) divides the total `Trip` distance by the total `Trip` hours. If the average rate of the new `Trip` to be added is less than 5 mph or greater than 100 mph, this `Trip` will not be added to the `Trip` data.

7. After all data has been read and added to the `driverHistory` map, the map sets are put into a sortable LinkedList.

8. `HistorySorter` is a custom comparing tool, that implements Java's `Comparator`. The compare tool expects the `Driver`/`Trip` pair, and compares the total `Trip Distance` values stored in each `Trip` object.
 - `Collections.sort` is called on the LinkedList, using the custom compare tool, and orders the list by `Trip Distance` from greatest to least.

9. The newly sorted list is looped to print the stored key value pair's String data.
 - The driver Object includes `getName` that returns the String name.
 - Trip has a custom `toString` method that checks total distance to determine proper output.

10. If the option to print an error report was selected, the tool will loop through the list of errors and print each one, prior to the Trip data list.
