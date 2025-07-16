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
    return <div className="alert">Invalid post ID</div>;
  }

  if (!post) return <div className="alert">Loading...</div>;

  return (
    <div className="container">
      <h2>{post.title}</h2>
      <p className="post-meta">By {post.username} on {new Date(post.createdAt).toLocaleString()}</p>
      <p>{post.content}</p>

      <h3>Comments</h3>
      {comments.length === 0 ? (
        <p className="post-meta">No comments yet</p>
      ) : (
        <ul className="space-y">
          {comments.map(c => (
            <li key={c.id} className="comment-item">
              <strong>{c.username}</strong>: {c.content} <em>({new Date(c.createdAt).toLocaleString()})</em>
            </li>
          ))}
        </ul>
      )}

      <Link to={`/posts/${id}/comments`} className="post-title">
        Add a Comment
      </Link>
    </div>
  );
}

export default PostDetail;