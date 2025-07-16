import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import api from '../api';

function PostDetail() {
  const { id } = useParams();
  const [post, setPost] = useState(null);
  const [comments, setComments] = useState([]);

  useEffect(() => {
    if (!id || isNaN(Number(id))) return;

    api.get(`/posts/${id}`)
      .then(res => setPost(res.data))
      .catch(console.error);

    api.get(`/comments/post/${id}`)
      .then(res => setComments(res.data))
      .catch(console.error);
  }, [id]);

  if (!id || isNaN(Number(id))) {
    return <div>Invalid post ID</div>;
  }

  if (!post) return <div>Loading...</div>;

  return (
    <div>
      <h2>{post.title}</h2>
      <p>By {post.username} on {new Date(post.createdAt).toLocaleString()}</p>
      <p>{post.content}</p>

      <h3>Comments</h3>
      {comments.length === 0 ? (
        <p>No comments yet</p>
      ) : (
        <ul>
          {comments.map(c => (
            <li key={c.id}>
              <strong>{c.username}</strong>: {c.content} <em>({new Date(c.createdAt).toLocaleString()})</em>
            </li>
          ))}
        </ul>
      )}

      <Link to={`/posts/${id}/comments`}>Add a Comment</Link>
    </div>
  );
}

export default PostDetail;