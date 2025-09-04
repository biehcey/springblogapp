import React, { useState } from 'react';
import api from '../api';
import { useNavigate } from 'react-router-dom';

function CreatePost() {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const navigate = useNavigate();

  // localStorage'dan user bilgisi al
  const user = JSON.parse(localStorage.getItem('user'));
  const token = localStorage.getItem('token');

  // Kullanıcı login değilse uyarı göster
  if (!token || !user) {
    return (
      <div>
        You must <a href="/login">log in</a> to create a post.
      </div>
    );
  }

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await api.post('/posts', { 
        title, 
        content, 
        userId: user.id 
      });

      console.log('Post created:', response.data);

      alert('Post created successfully!');
      navigate('/posts'); // Başarılı ise post listesine yönlendir
    } catch (error) {
      console.error('Error creating post:', error);
      alert('Error creating post');
    }
  };

  return (
    <div>
      <h2>Create New Post</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Title</label><br />
          <input
            type="text"
            value={title}
            onChange={e => setTitle(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Content</label><br />
          <textarea
            value={content}
            onChange={e => setContent(e.target.value)}
            required
          />
        </div>
        <button type="submit">Create Post</button>
      </form>
    </div>
  );
}

export default CreatePost;
