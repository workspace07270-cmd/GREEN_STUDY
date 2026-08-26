import { useDispatch } from 'react-redux';
import '../css/todo.css';
import { deleteTodo, updateTodo } from '../stores/TodoSlice';
export default function TodoListItem({ todo }) {
    const dispatch = useDispatch();
    return (
        <tr>
            <td>{todo.id}</td>
            <td><span className={todo.done ? 'done' : ''}>{todo.text}</span></td>
            <td><button className="btn btn-primary" onClick={() => dispatch(updateTodo(todo.id))}>Complete</button></td>
            <td><button className="btn btn-danger" onClick={() => dispatch(deleteTodo(todo.id))}>Delete</button></td>
        </tr>
    );
}