## :scroll: Description

Doggos is an app that lets you:
 - Browse through dog images (didn't I convince you already to install this??), and order them by breed name.  Clicking on one of the images will take you to a screen with more detail about the dog breed. 
 - Search for a specific breed and see more details about that breed

This app uses the following API https://docs.thedogapi.com/

## 👷 Setup

Import this project on android studio and add your TheDogApi key to the **local.properties** file, like so:

```
api_key="your_api_key_here"
```

## 📋 Requirements

- Show screen with a list of dog breeds image:
  The app opens on an Activity with a Bottom Navigation that allows you to navigate to a Home and Search Fragment. The entry point is the Home Fragment (shocking I know). 
  This Fragment displays data from the thedogapi /images endpoint inside a recycler view. You can switch the RecyclerView display to present one or two items per row, using the layout Button. You can also press the order Button, that will cycle between three diferent ways to order the information displayed, ascending alphabetically, descending alphabetically, and random order. The data is loaded making use of pagination using the [Android Paging Library](https://developer.android.com/topic/libraries/architecture/paging/v3-overview). When you click on one of the RecyclerView items it takes you to a details view.
  
- Show a screen with a list of dog breeds when you search by breed name:
  If you navigate to the search Fragment, you will see a screen with a SearchView at the top. You can enter a search query and get a list of breeds that match your query, displayed in a RecyclerView.
  Each item of that list shows (if present) the breed name, group and origin. When you click on one of the RecyclerView items it takes you to a details view.
  
- Pressing on one of the list elements, should go to the details view:
  As mentioned above, when you click on an item a detail Activity is shown. This activity displays the following info: breed name, breed category, origin, temperament.
  
  
## 🥇 Bonus

- Error handling:
  A toast is displayed with some basic information in case some of the ViewModel functions returns an error.
  
- Offline functionality:
  Use a room database as the single source of truth
  TODO:
   - Replace RxPagingSource with RxRemoteMediator (feature_home)
   - Apply a caching strategy for search feature

- Unit test coverage:
  Contains unit tests for the ViewModels and Model transformations


## :bulb: Technologies used

Room, RxJava, Retrofit, Hilt, [Android Paging Library](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
