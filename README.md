Android Application to load the Job Opportunities from lokal news app test API and display it in the UI.
Implemented Pagination logic and used Caching

Design Pattern Followed: Model-View-ViewModel 
- Tried to follow the Clean Architecture thoughout the project

Libraries Used: 
* Jetpack compose for UI
* Room for Persistent Storage
* Retrofit for handling Network Calls
* Kotlinx Serialization for Serializing & Deserializing the Json
* Paging Library for Pagination

Project Structure:
Divided into 4 Layers
1. Data - (local, remote) -> further into features (jobs)
2. Di (dependency injection)
3. Domain - divided into features (jobs) -> (model, repository)
4. Presentation - (screens, ui, utils)

Screens:
1. Jobs - Displays All Jobs
2. Bookmarks - Display only Bookmarked Jobs
3. Job Details - Display Details of Selected Job and also provide facility to update the bookmark status of Job 
Implemented Features:
* Load the Data from the API using Pagination (one page once) and cached the data in the Room Database
* The data can be loaded offline as well
* User can bookmark a job and that will be persisted as well but will lost if we refresh as we are not updating in the remote db.
