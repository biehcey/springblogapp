import React, {useEffect, useState} from 'react';
import api from '../api';
import { Link } from 'react-router-dom';

function PostList(){
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        api.get('/posts')
        .then(res => setPosts(res.data))
        .catch(console.error);
    }, []);

    return (
        <div>
            <h1>Posts</h1>
            <ul>
                {posts.map(post => (
                    <li key={post.id}>
                        <Link to={'/posts/${post.id}'}>
                        {post.title} by {post.username}
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default PostList;