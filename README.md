# Todolist 

# Features Requirements:

- When the app is opened a list of todo must be shown; (Not Work)
- When there isn't entries an empty message must be shown; (Ok)
- A progress while loading the list must be shown;
- A FloatActionButton must be on right and bottom position to open a new screen where user may create new todo item; (OK)
- App must work in landscape and portrait orientation;(Not Work)
- Todo item fields: Title, Description, CreatedDate, Done. 
- Done is marked when user complete the todo item;
- Description is opcional;
- Done is false by default;
- Title must be checked if was typed before save the todo item;
- When a new item is created it must update the list automatically;
- When user click on some item it will complete the item (mark done as true);
- If user long press the item options must be shown with delete option;

# Architecture Requirements:

- Develop in Kotlin;
- Use MVVM as architecture;
- Use RoomDatabase to store the todo's;
- Use LiveData to keep the app lifecycle aware;
- (Optional) Use DataBinding to show data on UI (RecyclerView items);

# Improve this itens:

- When the app is opened a list of todo must be shown
-- Items are saved in the bank, however every time the application is closed the bank is being deleted.
- App must work in landscape and portrait orientation;
-- app break when you click to new iten in lansacape
- Todo item fields: Title, Description, CreatedDate, Done. 
-- Date never save
- Done is marked when user complete the todo item;
-- Its fine to using a color indicate is fine, but i think need to provide a way to uncheck a item.


