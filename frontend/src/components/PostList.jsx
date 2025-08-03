import React, { useEffect, useState } from 'react';
import api from '../api';
import { Link } from 'react-router-dom';

function PostList() {
  const [posts, setPosts] = useState(null); 
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    setLoading(true);
    api.get('/posts')
      .then(res => {
        setPosts(res.data);
        setLoading(false);
      })
      .catch(err => {
        console.error(err);
        setError("Failed to load posts");
        setLoading(false);
      });
  }, []);

  if (loading) return <div>Loading...</div>;

  if (error) return <div>{error}</div>;

  if (!posts || posts.length === 0) return <div>No posts found.</div>;

  return (
    <div className="container">
      <h1>Posts</h1>
      <ul className="space-y">
        {posts.map(post => (
          <li key={post.id} className="post-card">
            <Link to={`/posts/${post.id}`} className="post-title">
              {post.title}
            </Link>
            <p className="post-meta">by <span>{post.username}</span></p>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default PostList;
