import axios from 'axios';

let URL = 'http://localhost:8080/books/'

const getBook = async (id) =>{
    try{
        const res = await axios.get(`${URL}${id}`)
        return res.data;
    }catch(error){
        throw error
    }
}

export default getBook