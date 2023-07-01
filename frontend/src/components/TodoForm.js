import React from 'react';

const TodoForm = ({ handleAddTodo, handleInputChange, addTodoContent }) => {
    return (
        <>
            <h3 className="text-center">Todo Input</h3>
            <div className="card text-center rounded-0 mt-3">
                <div className="card-body">
                    <form onSubmit={handleAddTodo}>
                        <div className="input-group input-group-sm mb-3">
                            <span className="input-group-text">
                                <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" className="bi bi-journal-plus mt-1" viewBox="0 0 16 16">
                                    <path fillRule="evenodd" d="M8 5.5a.5.5 0 0 1 .5.5v1.5H10a.5.5 0 0 1 0 1H8.5V10a.5.5 0 0 1-1 0V8.5H6a.5.5 0 0 1 0-1h1.5V6a.5.5 0 0 1 .5-.5z" />
                                    <path d="M3 0h10a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2v-1h1v1a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H3a1 1 0 0 0-1 1v1H1V2a2 2 0 0 1 2-2z" />
                                    <path d="M1 5v-.5a.5.5 0 0 1 1 0V5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0V8h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0v.5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1z" />
                                </svg>
                            </span>
                            <input type="text" className="form-control"  value={addTodoContent} onChange={handleInputChange} />
                        </div>
                        <button type="submit" className="add-btn btn btn-primary mt-3">
                            Add New Task
                        </button>
                    </form>
                </div>
            </div>
        </>
    );
};

export default TodoForm;
