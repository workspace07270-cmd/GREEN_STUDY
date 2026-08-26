import {configureStore} from '@reduxjs/toolkit'

import storage from 'redux-persist/lib/storage'
import { persistReducer } from 'redux-persist';
import persistStore from 'redux-persist/es/persistStore';
import TodoSlice from './TodoSlice';

// 로컬 스토리지에 저장하는 방식
// redux-persist를 설치해서 사용

// 로컬 스토리지 셋팅
const persistConfig = {
    key : 'root',
    storage,
}

// 작성한 slice를 persistReducer 등록
const todoPersistReducer = persistReducer(persistConfig,TodoSlice);

export const store = configureStore({
    reducer:{
        todo : todoPersistReducer, 
    }
});

export const persitor = persistStore(store);