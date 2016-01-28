# HeaderAndFooterRecyclerView
a better way to add headerView and footerView to recyclerView(pull-to-refresh, paging loading).RecyclerView works with SwipeRefreshLayout

![](https://github.com/vienan/HeaderAndFooterRecyclerView/blob/master/screenshot/screenshot.gif)


##how to use
 To use this library create your RecyclerView.Adapter classes extending from [BaseNiceRecyclerViewAdapter](https://github.com/vienan/HeaderAndFooterRecyclerView/blob/master/library/src/main/java/com/vienan/baseNiceRecyclerViewAdapter/BaseNiceRecyclerViewAdapter.java).
 
This adapter extends from [Karumi/HeaderRecyclerViewAdapter](https://github.com/Karumi/HeaderRecyclerView),thanks to Karumi

you can see the usage in [MainActivityAdapter.java](https://github.com/vienan/HeaderAndFooterRecyclerView/blob/master/app/src/main/java/com/vienan/recyclerview/adapter/MainActivityAdapter.java)


###if it helps you ,welcome to star and fork


##License

Copyright 2016 D.Clear

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
