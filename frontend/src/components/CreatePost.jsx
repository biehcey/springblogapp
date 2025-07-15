import React, { useState } from 'react';
import api from '../api';
import { useNavigate } from 'react-router-dom';

function CreatePost(){
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [userId, setUserId] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async(e) => {
        e.preventDefault();
        try{
            await api.post('/posts', {title, content, userId: Number(userId)});
            navigate('/');
        }catch(error){
            console.error(error);
            alert('Error creating post');
        }
    };

    return (
        <div>
            <h2>Create New Post</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Title:</label><br />
                    <input value={title} onChange={e => setTitle(e.target.value)} required />
                </div>
                <div>
                    <label>Content:</label><br />
                    <textarea value={content} onChange={e => setContent(e.target.value)} required />
                </div>
                <div>
                    <label>User ID:</label><br />
                    <input type="number" value={userId} onChange={e => setUserId(e.target.value)} required />
                </div>
                <button type='submit'>Create Post</button>
            </form>
        </div>
    );
}
export default CreatePost;