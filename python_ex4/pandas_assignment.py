# CMP 269: Programming Methods III
# In-Class Assignment: Pandas Series and DataFrames
import pandas as pd

"""
INSTRUCTIONS:
Complete the following 4 tasks using the pandas library. 
Run your script frequently to see how the DataFrames look in the console!
"""

def task_1_series_creation():
    """
    TASK 1: Create a Series
    1. Create a dictionary mapping 4 Lehman building names to their floor counts.
    2. Convert this dictionary into a Pandas Series.
    3. Print the Series.
    """
    print("--- Task 1: Building Series ---")

    buildings = {
        "Gillet": 4,
        "Carman": 3,
        "Music": 3,
        "Library": 4
    }

    building_series = pd.Series(buildings)
    print(building_series)


def task_2_dataframe_creation():
    """
    TASK 2: Create a DataFrame
    1. Create a dictionary of lists with course data.
    2. Convert this into a Pandas DataFrame.
    3. Print the DataFrame.
    """
    print("\n--- Task 2: Course DataFrame ---")

    courses = {
        "CourseCode": ["CMP168", "CMP269", "CMP338"],
        "Credits":    [4, 4, 4],
        "Enrolled":   [25, 30, 20]
    }

    df = pd.DataFrame(courses)
    print(df)


def task_3_data_manipulation():
    """
    TASK 3: Filtering and Math
    1. Recreate the DataFrame from Task 2.
    2. Filter to only courses with more than 20 students enrolled.
    3. Print the total number of students across ALL courses.
    """
    print("\n--- Task 3: Filtering and Math ---")

    courses = {
        "CourseCode": ["CMP168", "CMP269", "CMP338"],
        "Credits":    [4, 4, 4],
        "Enrolled":   [25, 30, 20]
    }

    df = pd.DataFrame(courses)

    # Filter: only rows where Enrolled > 20
    high_enrollment = df[df["Enrolled"] > 20]
    print("Courses with more than 20 students enrolled:")
    print(high_enrollment)

    # Total students across ALL courses (before filtering)
    total_students = df["Enrolled"].sum()
    print(f"\nTotal students across all courses: {total_students}")


def task_4_csv_integration():
    """
    TASK 4: The Pandas CSV Advantage
    1. Create a simple stock DataFrame.
    2. Save it to 'stocks.csv'.
    3. Read it back into df_loaded.
    4. Print df_loaded.
    """
    print("\n--- Task 4: Easy CSV I/O ---")

    stocks = {
        "Symbol": ["AAPL", "GOOG", "MSFT", "AMZN"],
        "Price":  [189.50, 175.20, 415.80, 196.40]
    }

    df = pd.DataFrame(stocks)

    # Save to CSV
    df.to_csv("stocks.csv", index=False)
    print("Saved stocks.csv successfully.")

    # Read it back
    df_loaded = pd.read_csv("stocks.csv")
    print("Loaded from stocks.csv:")
    print(df_loaded)


if __name__ == "__main__":
    task_1_series_creation()
    task_2_dataframe_creation()
    task_3_data_manipulation()
    task_4_csv_integration()