import { useRef } from "react";
import { useDispatch } from "react-redux";
import { addTodo } from "../stores/TodoSlice";

export default function TodoForm() {
  const dispatch = useDispatch();
  const txt = useRef(null);
  const handler = () =>{
    dispatch(addTodo(txt.current.value));
  }
  return (
    <div className="input-group">
      <input type="text" placeholder="할일을 입력하세요" ref={txt} className="form-control"/>
      <button onClick={handler} className="btn btn-primary">Submit</button>
    </div>);
}