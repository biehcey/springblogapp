import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import api from '../api';

function CreateComment() {
  const { id } = useParams(); // Post ID
  const [content, setContent] = useState('');
  const navigate = useNavigate();

  // Kullanıcı bilgisi ve token
  const user = JSON.parse(localStorage.getItem('user'));
  const token = localStorage.getItem('token');

  if (!token || !user?.id) {
    return (
      <div>
        You must <a href="/login">log in</a> to comment.
      </div>
    );
  }

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!id || isNaN(Number(id))) {
      alert("Invalid post ID");
      return;
    }

    try {
      const response = await api.post('/comments', {
        content,
        userId: user.id,
        postId: Number(id)
      });

      console.log('Comment created:', response.data);

      navigate(`/posts/${id}`);
    } catch (err) {
      console.error('Error submitting comment:', err);
      alert("Error submitting comment");
    }
  };

  return (
    <div>
      <h2>Add Comment</h2>
      <form onSubmit={handleSubmit}>
        <textarea
          value={content}
          onChange={e => setContent(e.target.value)}
          required
          placeholder="Write your comment..."
        />
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default CreateComment;
