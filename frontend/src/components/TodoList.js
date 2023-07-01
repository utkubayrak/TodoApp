import React, { useState, useEffect, useRef } from 'react';
import * as TodoService from '../service/TodoService';
import ModalComponent from './Modal';
import TodoItem from './TodoItem';
import TodoForm from './TodoForm';

const TodoList = () => {
    const [todos, setTodos] = useState([]); //Durum yönetimi, todo listesi
    const [showModal, setShowModal] = useState(false); //Modal kontrol durumu 
    const [selectedTodoId, setSelectedTodoId] = useState(null); //Todo kimliği tutma
    const [addTodoContent, setAddTodoContent] = useState(''); // Yeni todo içeriği tutma
    const listRef = useRef(null); //Referans saklamak için
    const [showScrollButton, setShowScrollButton] = useState(false); //Scroll kontrol durumu

    useEffect(() => { //Yaşam döngüsü olayları
        getAllTodos(); //Bileşen yüklenince tüm todoları getir
    }, []);

    useEffect(() => { // scroll görünürlüğünü kontrol, listRef dinleme
        const handleScroll = () => {
            const scrollTop = listRef.current.scrollTop;
            if (scrollTop > 50) {
                setShowScrollButton(true);
            } else {
                setShowScrollButton(false);
            }
        };

        const listElement = listRef.current;

        listElement.addEventListener('scroll', handleScroll);

        return () => {
            listElement.removeEventListener('scroll', handleScroll);
        };
    }, []);

    //ALL TODOS
    const getAllTodos = () => {
        TodoService.allTodoService()
            .then((response) => {
                setTodos(response.data);
            })
            .catch((error) => {
                console.error('Todo listesi alınamadı:', error);
            });
    };

    //ADD
    const handleAddTodo = (event) => {
        event.preventDefault();
        const todo = { content: addTodoContent };
        TodoService.createTodoService(todo)
            .then((response) => {
                const addTodo = response.data;
                console.log('Todo oluşturuldu:', addTodo);
                setAddTodoContent('');
                getAllTodos();
            })
            .catch((error) => {
                console.error('Todo oluşturulamadı:', error);
            });
    };
    //Input dinleme
    const handleInputChange = (event) => {
        setAddTodoContent(event.target.value);
    };
    //MODAL 
    const handleOpenModal = (todoId) => {
        setShowModal(true);
        setSelectedTodoId(todoId); //Todo Id güncelleme için  
    };
    const handleCloseModal = () => {
        setShowModal(false);
        setSelectedTodoId(null);
    };
    //DELETE
    const handleDeleteTodo = (event, todoId) => {
        event.preventDefault();
        TodoService.deleteTodoService(todoId)
            .then(() => {
                getAllTodos(); //Silinmemiş todoları yeniden döndürür
            })
            .catch((error) => {
                console.error('Todo silinemedi:', error);
            });
    };
    //CHECK
    const handleCheckTodo = (todoId) => {
        TodoService.checkTodoService(todoId)
            .then((response) => {
                console.log('Todo güncellendi:', response.data);
                getAllTodos();
            })
            .catch((error) => {
                console.error('Todo güncellenemedi:', error);
            });
    };

    //CHECK EDİLMEMİŞ TODO LİST
    const getUncompletedTodoList = () => {
        TodoService.allUncompletedListService()
            .then((response) => {
                console.log("Check edilmemiş todo list", response.data);
                setTodos(response.data);
            })
            .catch((error) => {
                console.error('Todo listesi alınamadı:', error);
            });
    };
    //CHECK EDİLMİŞ TODO LİST
    const doneTodoList = () => {
        TodoService.allDoneListService()
            .then((response) => {
                console.log("Check edilmiş todo list", response.data);
                setTodos(response.data);
            })
            .catch((error) => {
                console.error('Todo listesi alınamadı:', error);
            });
    };
    //CHECK EDİLMİŞ TODO LİST SİLME
    const doneDeleteTodo = (event) => {
        event.preventDefault();
        TodoService.deleteDoneAllService()
            .then(() => {
                console.log('Check Edilmiş Todo List silindi:');
                getAllTodos();
            })
            .catch((error) => {
                console.error('Check Edilmiş Todo List silinemedi:', error);
            });
    };
    //TÜM TODOLARI SİLME
    const allDeleteTodo = (event) => {
        event.preventDefault();
        TodoService.allDeleteService()
            .then(() => {
                console.log('Todolar silindi');
                getAllTodos();
            })
            .catch((error) => {
                console.error('Todolar silinemedi:', error);
            });
    };
    //UPDATE
    const handleUpdateTodo = (id, newContent) => {
        const newTodo = { id, content: newContent };
        TodoService.updateTodoService(id, newTodo)
            .then((response) => {
                console.log('Todo güncellendi:', response.data);
                getAllTodos();
                handleCloseModal();
            })
            .catch((error) => {
                console.error('Todo güncellenemedi:', error);
            });
    };
    return (
        <>
            <TodoForm
                handleAddTodo={handleAddTodo}
                handleInputChange={handleInputChange}
                addTodoContent={addTodoContent}
            />
            <h3 className="text-center">Todo List</h3>
            <div className="list-buttons border-0">
                <div className="row flex-wrap ">
                    <div className="col-md-4 d-flex justify-content-start ">
                        <button onClick={getAllTodos} className="btn btn-primary">All</button>
                    </div>
                    <div className="col-md-4 d-flex justify-content-center ">
                        <button onClick={doneTodoList} className="btn btn-primary">Done</button>
                    </div>
                    <div className="col-md-4 d-flex justify-content-end ">
                        <button onClick={getUncompletedTodoList} className="btn btn-primary">Todo</button>
                    </div>
                </div>
                <div className="row mt-5">
                    <div className="col">

                        <div className="list-container">
                            <ul className="list-group todo-list-body" ref={listRef}>
                                {todos.map((todo) => (
                                    <TodoItem
                                        todo={todo}
                                        handleOpenModal={handleOpenModal}
                                        handleDeleteTodo={handleDeleteTodo}
                                        handleCheckTodo={handleCheckTodo}
                                        checked={todo.isChecked}
                                    />

                                ))}

                            </ul>
                            {/* Scroll düğmesi */}
                            {showScrollButton && (
                                <button className="scroll-button" onClick={() => listRef.current.scrollTo({ top: 0, behavior: 'smooth' })}>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-up-circle-fill" viewBox="0 0 16 16">
                                        <path d="M16 8A8 8 0 1 0 0 8a8 8 0 0 0 16 0zm-7.5 3.5a.5.5 0 0 1-1 0V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707V11.5z" />
                                    </svg>
                                </button>
                            )}
                        </div>
                    </div>
                </div>
            </div>
            <div className="row flex-wrap delete-buttons">
                <div className="col-md-6 d-flex justify-content-start card-buttons">
                    <button onClick={doneDeleteTodo} className="btn btn-primary">Delete Done Tasks</button>
                </div>
                <div className="col-md-6 d-flex justify-content-end card-buttons">
                    <button onClick={allDeleteTodo} className="btn btn-primary">Delete All Tasks</button>
                </div>
            </div>
            {showModal && (
                <ModalComponent
                    todoId={selectedTodoId}
                    handleCloseModal={handleCloseModal}
                    handleUpdateTodo={handleUpdateTodo}
                />
            )}
        </>
    );
};

export default TodoList;
