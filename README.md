# APPWRKItAssigment

This is an Android application that displays a list of items with their status. Users can search the list, view item details, and mark items as "Completed."

## Project Structure

### `com.rsb.appwrkitassigment`

This is the root package of the application and contains the main activities.

- **`MainActivity.kt`**: The main screen of the application. It displays a list of items and includes a search bar to filter the list. It also shows a count of total, pending, and completed items.
- **`DetailActivity.kt`**: This screen shows the details of a selected item. Users can mark a "Pending" item as "Completed" from this screen.

### `com.rsb.appwrkitassigment.UI.adapter`

This package contains the `RecyclerView` adapter for the application.

- **`MyAdapter.kt`**: This adapter is responsible for displaying the list of items in the `RecyclerView`. It handles item clicks and visually disables completed items.

### `com.rsb.appwrkitassigment.model`

This package contains the data model for the application.

- **`ListItemModel.kt`**: This data class represents a single item in the list. It includes the item's ID, title, description, and status.

### `com.rsb.appwrkitassigment.viewmodel`

This package contains the `ViewModel` classes for the application, which handle the business logic and data for the UI.

- **`MainViewModel.kt`**: This `ViewModel` is responsible for loading, searching, and updating the list of items for the `MainActivity`. It also calculates the item counts.
- **`DetailViewModel.kt`**: This `ViewModel` is responsible for loading the details of a specific item and handling the logic to mark an item as "Completed."

### `com.rsb.appwrkitassigment.repository`

This package contains data provider classes for the application.

- **`PreferenceDataStore.kt`**: This class handles the data persistence for the application using Jetpack DataStore. It saves, loads, and updates the list of items.

## Setup and Run

To set up and run this application, follow these steps:

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    ```
2.  **Open in Android Studio:**
    -   Open Android Studio.
    -   Select "Open an existing Android Studio project."
    -   Navigate to the cloned repository and select the project folder.
3.  **Sync Project:**
    -   Android Studio will automatically start syncing the project with Gradle. Wait for the sync to complete. This will download all the necessary dependencies.
4.  **Run the Application:**
    -   Select an emulator or connect a physical device.
    -   Click the "Run" button (the green play icon) in the toolbar.

## Approach and Improvements

### Q: Why did you choose this approach?

**A:** I followed the MVVM (Model-View-ViewModel) architecture with Kotlin and Data Binding for the following reasons:

-   **Separation of Concerns:** MVVM provides a clear separation between the UI (View), the business logic (ViewModel), and the data (Model), making the code easier to maintain, test, and read.
-   **Kotlin:** Kotlin is the recommended language for modern Android development. It reduces boilerplate code compared to Java, improving readability and developer productivity.
-   **Data Binding:** Data Binding allows for a more declarative way to connect the UI to the ViewModel, reducing the amount of glue code needed in the activities and fragments. It also helps in creating a more responsive UI.

### Q: What would you improve with one more day?

**A:** With an additional day, I would focus on the following improvements:

1.  **UI/UX Enhancements:** I would refine the user interface and experience. This could include adding animations, improving the visual design, and ensuring the app is intuitive and user-friendly.
2.  **Unit and UI Testing:** I would write unit tests for the ViewModels to ensure the business logic is correct. I would also add UI tests to automate the testing of user flows and interactions.
3.  **Room Database:** I would replace the current DataStore implementation with Room for local data persistence. Room provides a more robust and scalable solution for managing a local database, especially for more complex data models.
4.  **Add/Delete Functionality:** I would implement the ability for users to add new items to the list and delete existing ones, providing a more complete CRUD (Create, Read, Update, Delete) functionality.
