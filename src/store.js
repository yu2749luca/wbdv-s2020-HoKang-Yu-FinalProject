import thunk from "redux-thunk";
import {applyMiddleware, combineReducers, createStore} from 'redux'

import reducers from "./reducers";

export default createStore(
    combineReducers(reducers),
    {
        foodList: [],
        selectedCategory: "vegetables",
        shoppingCart: [],
        orders: [],
        user: undefined,
        isLogin: false
    },
    applyMiddleware(thunk)
)