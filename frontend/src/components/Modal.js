import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

function ModalComponent({ todoId, handleCloseModal, handleUpdateTodo }) {
  const [newContent, setNewContent] = useState('');

  const handleContentChange = (e) => {
    setNewContent(e.target.value);
  };

  const handleSaveChanges = () => {
    handleUpdateTodo(todoId, newContent);
  };

  return (
    <Modal show={true} onHide={handleCloseModal}>
      <Modal.Header closeButton>
        <Modal.Title className='text-center w-100'>Todo Güncelleme</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <form>
          <input
          className='form-control'
            type="text"
            value={newContent}
            onChange={handleContentChange}
          />
        </form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="success" onClick={handleSaveChanges}>
          Kaydet
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default ModalComponent;
