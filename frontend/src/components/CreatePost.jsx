import React, { useState } from 'react';
import api from '../api';
import { useNavigate } from 'react-router-dom';

function CreatePost() {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const navigate = useNavigate();

  // localStorage'dan user bilgisini al
  const user = JSON.parse(localStorage.getItem('user'));
  const userId = user?.id;

  // userId yoksa uyarı göster ve formu kapat
  if (!userId) {
    return <div className="alert">You must <a href="/login">log in</a> to create a post.</div>;
  }

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await api.post('/posts', { title, content, userId });
      alert("Post created successfully!");
      navigate('/posts');
    } catch (error) {
      console.error(error);
      alert('Error creating post');
    }
  };

  return (
    <div className="container">
      <h2>Create New Post</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Title:</label>
          <input className="form-input" value={title} onChange={e => setTitle(e.target.value)} required />
        </div>
        <div>
          <label>Content:</label>
          <textarea className="form-input" value={content} onChange={e => setContent(e.target.value)} required />
        </div>
        <button type='submit'>Create Post</button>
      </form>
    </div>
  );
}

export default CreatePost;
