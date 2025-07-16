import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";

import Home from './components/Home';
import PostList from './components/PostList';
import PostDetail from './components/PostDetail';
import CreatePost from './components/CreatePost';
import CreateComment from './components/CreateComment';
import Register from './components/Register';
import Login from './components/Login';

function App() {
  return (
    <Router>
      <nav>
        <Link to="/">Home</Link> | <Link to="/posts">Posts</Link> | <Link to="/create-post">New Post</Link> | <Link to="/register">Register</Link> | <Link to="/login">Login</Link>
      </nav>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/posts" element={<PostList />} />
        <Route path="/posts/:id" element={<PostDetail />} />
        <Route path="/create-post" element={<CreatePost />} />
        <Route path="/posts/:id/comments" element={<CreateComment />} />
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
      </Routes>
    </Router>
  );
}

export default App;