import axios from "axios";

const TODO_URL = "http://localhost:2222/todo/api";

export const createTodoService = async (todo) => {
  const response = await axios.post(`${TODO_URL}/create`, todo);
  return response;
};

export const deleteTodoService = async (id) => {
  const response = await axios.delete(`${TODO_URL}/delete/${id}`);
  return response;
};

export const updateTodoService = (id, updatedTodo, contentType) => {
  return axios.put(`${TODO_URL}/update/${id}`, updatedTodo, { headers: { 'Content-Type': contentType } });
};

export const checkTodoService = async (id) => {
  const response = await axios.put(`${TODO_URL}/checked/${id}`);
  return response;
};

export const allTodoService = async () => {
  const response = await axios.get(`${TODO_URL}/list`);
  return response;
};

export const findByIdService = async (id) => {
  const response = await axios.get(`${TODO_URL}/find/${id}`);
  return response;
};

export const allDeleteService = async () => {
  const response = await axios.delete(`${TODO_URL}/all/delete`);
  return response;
};

export const deleteDoneAllService = async () => {
  const response = await axios.delete(`${TODO_URL}/all/done/delete`);
  return response;
};

export const allDoneListService = async () => {
  const response = await axios.get(`${TODO_URL}/all/done/list`);
  return response;
};

export const allUncompletedListService = async () => {
  const response = await axios.get(`${TODO_URL}/all/uncompleted/list`);
  return response;
};
