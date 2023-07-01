import React, { Component } from 'react';
import TodoList from '../components/TodoList';

export default class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      todos: [],
    };
  }

  updateTodoList = (newTodo) => {
    this.setState((prevState) => ({
      todos: [...prevState.todos, newTodo],
    }));
  };

  render() {
    return (
      <div className="container mt-3 mb-3">
        <TodoList todos={this.state.todos} />
      </div>
    );
  }
}
