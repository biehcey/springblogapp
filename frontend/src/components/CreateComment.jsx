import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import api from '../api';

function CreateComment() {
  const { id } = useParams();
  const [content, setContent] = useState('');
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem('user'));
  const userId = user?.id;

  if (!userId) {
    return <div>You must <a href="/login">log in</a> to comment.</div>;
  }

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!id || isNaN(Number(id))) {
      alert("Invalid post ID");
      return;
    }

    try {
      await api.post('/comments', { content, userId, postId: Number(id) });
      navigate(`/posts/${id}`);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Add Comment</h2>
      <textarea
        value={content}
        onChange={e => setContent(e.target.value)}
        required
        placeholder='Comment'
      />
      <button type="submit">Submit</button>
    </form>
  );
}

export default CreateComment;