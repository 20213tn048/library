import axios from 'axios';

let URL = 'http://localhost:8080/books/';

const DeleteBook = async(id) =>{
    try{
        const res = await axios.delete(`${URL}${id}`)
        return res.data;
    }catch(error){
        throw error;
    }
}

export default DeleteBook;