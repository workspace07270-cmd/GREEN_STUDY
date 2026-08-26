import { createSlice } from "@reduxjs/toolkit";

const list = [
    {id : 1, text : "할 일 1", done : false},
    {id : 2, text : "할 일 2", done : true},
    {id : 3, text : "할 일 3", done : false},
    {id : 4, text : "할 일 4", done : true},
    {id : 5, text : "할 일 5", done : false},
];

export const todoSlice = createSlice({
    name:'todo',
    initialState:{
        todoList : list,
        idx : 6
    },
    reducers:{
        addTodo:(state,action) => {
            state.todoList = [...state.todoList,{id: state.idx++, text: action.payload, done: false}];
        },
        deleteTodo:(state,action) => {
            state.todoList = state.todoList.filter((todo) => todo.id !== action.payload)
        },
        updateTodo:(state,action) => {
            state.todoList = state.todoList.map((todo) => {
                if (todo.id === action.payload) {
                    return {...todo, done: !todo.done};
                }
                return todo;
            });
        },
    }
});


export const {addTodo, deleteTodo, updateTodo} = todoSlice.actions;
export default todoSlice.reducer;